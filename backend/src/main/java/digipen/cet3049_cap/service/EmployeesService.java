package digipen.cet3049_cap.service;

import digipen.cet3049_cap.dto.*;
import digipen.cet3049_cap.exception.DepartmentNotFoundException;
import digipen.cet3049_cap.exception.DuplicateException;
import digipen.cet3049_cap.model.*;
import digipen.cet3049_cap.model.id.DeptEmpId;
import digipen.cet3049_cap.model.id.DeptManagerId;
import digipen.cet3049_cap.model.id.SalariesId;
import digipen.cet3049_cap.model.id.TitlesId;
import digipen.cet3049_cap.repositories.*;
import digipen.cet3049_cap.exception.EmployeeNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeesService {
    private final EmployeesRepo employeesRepo;
    private final DepartmentsRepo departmentsRepo;
    private final DeptEmpRepo deptEmpRepo;
    private final DeptManagerRepo deptManagerRepo;
    private final SalariesRepo salariesRepo;
    private final TitlesRepo titlesRepo;

    @Transactional
    public EmployeesDTO findByEmpNo(Long empNo) {
        Employees e = employeesRepo.findById(empNo)
                .orElseThrow(() -> new EmployeeNotFoundException());

        return EmployeesService.toEmployeesDTO(e);
    }

    @Transactional
    public Page<SimpleEmployeesDTO> getEmployeesByDeptNo(String deptNo, Pageable pageable) {

        // HANDLE NON-EXISTENT DEPT RECORD
        if (departmentsRepo.findByDeptNo(deptNo).isEmpty())
            throw new DepartmentNotFoundException();

        Page<DeptEmp> deptEmpPage = deptEmpRepo.findEmployeesByDepartment(deptNo, pageable);

        // HANDLE PAGE NUMBER OUT OF BOUNDS
        if (deptEmpPage.isEmpty())
            throw new NumberFormatException("Page number is out of range.");

        return deptEmpPage.map(deptEmp -> {
            Employees employees = deptEmp.getEmployee();
            return new SimpleEmployeesDTO(employees.getEmpNo(), employees.getFirstName(), employees.getLastName(), employees.getHireDate());
        });
    }

    @Transactional
    public List<PromotionDTO> promoteEmployee(PromotionDTO promotionDTO) {

        final LocalDate OPEN_ENDED = LocalDate.of(9999, 1, 1);
        LocalDate newStartDate = promotionDTO.getStartDate();
        LocalDate endDate = newStartDate.minusDays(1);

        List<PromotionDTO> promotionDTOList = new ArrayList<>();

        Long empNo = promotionDTO.getEmpNo();
        String newTitle = promotionDTO.getNewTitle();
        String newDeptNo = promotionDTO.getNewDeptNo();
        BigDecimal newSalary = promotionDTO.getNewSalary();
        boolean toManager = Boolean.TRUE.equals(promotionDTO.getToManager());

        // ======== VALIDATIONS ========
        // EMPLOYEE VALIDATION
        Employees emp = employeesRepo.findById(empNo)
                .orElseThrow(() -> new EmployeeNotFoundException());

        if (!isCurrentEmployee(empNo))
            throw new IllegalStateException("Employee is no longer in the company.");

        // DEPARTMENT VALIDATION
        DeptEmp currDeptEmp = deptEmpRepo
                .findTopByDeptEmpIdEmpNoOrderByToDateDesc(empNo)
                .orElseThrow(() -> new IllegalStateException("No Dept Employee record found."));

        newDeptNo = (newDeptNo != null) ? newDeptNo : currDeptEmp.getDeptEmpId().getDeptNo();

        Departments dept = departmentsRepo.findById(newDeptNo)
                .orElseThrow(() -> new DepartmentNotFoundException());


        // GETTING CURRENT EMPLOYEE RECORDS
        Salaries currSal = salariesRepo
                .findTopBySalariesIdEmpNoOrderByToDateDesc(empNo)
                .orElseThrow(() -> new IllegalStateException("No salary record found."));
        Titles currTtl = titlesRepo
                .findTopByTitlesIdEmpNoOrderByToDateDesc(empNo)
                .orElseThrow(() -> new IllegalStateException("No title record found."));

        DeptManager currDeptMan = deptManagerRepo
                .findTopByDeptManagerIdDeptNoOrderByToDateDesc(newDeptNo)
                .orElse(null);

        // ======== DUPLICATE GUARDS ========
        if (Objects.equals(currTtl.getTitlesId().getTitle(), newTitle)
                && Objects.equals(currSal.getSalary(), newSalary)
                && Objects.equals(currDeptEmp.getDeptEmpId().getDeptNo(), newDeptNo)
                && (!toManager || (currDeptMan != null && !currDeptMan.getDeptManagerId().getEmpNo().equals(empNo))))
        {
            throw new IllegalStateException("Duplicate promotion data.");
        }

        // CHECKS FOR CHANGES IN EMPLOYEE RECORD
        boolean duplicateTitle = false;
        boolean duplicateDeptEmp =  false;
        boolean duplicateDeptMan = false;

        for (DeptEmp de : emp.getDeptEmp()) {
            if (de.getDeptEmpId().getDeptNo().equalsIgnoreCase(newDeptNo)) {
                duplicateDeptEmp = true;
                break;
            }
        }

        for (DeptManager dm : emp.getDeptManager()) {
            if (dm.getDeptManagerId().getDeptNo().equalsIgnoreCase(newDeptNo)) {
                duplicateDeptMan = true;
                break;
            }
        }

        for (Titles t : emp.getTitles()) {
            if (t.getTitlesId().getTitle().equals(newTitle)) {
                duplicateTitle = true;
                break;
            }
        }

        // ======== INSERT CURRENT STATE INTO DTO LIST ========
        promotionDTOList.add(new PromotionDTO(
                empNo,
                currTtl.getTitlesId().getTitle(),
                currDeptEmp.getDeptEmpId().getDeptNo(),
                currSal.getSalary(),
                currDeptEmp.getFromDate(),
                false
        ));


        // ======== UPDATE SALARY ========
        if (newSalary != null
                && !Objects.equals(newSalary, currSal.getSalary())
        ) {
            salariesRepo.closeDeptEmpRecord(empNo, endDate, OPEN_ENDED);

            SalariesId newSalId = new SalariesId(empNo, newStartDate);
            Salaries newSal = new Salaries(newSalId, newSalary, OPEN_ENDED);
            salariesRepo.save(newSal);
        }

        // ======== UPDATE TITLE ========
        if (newTitle != null
                && !Objects.equals(newTitle, currTtl.getTitlesId().getTitle())
                && !duplicateTitle
        ) {
            titlesRepo.closeDeptEmpRecord(empNo, endDate, OPEN_ENDED);

            TitlesId newTtlId = new TitlesId(empNo, newTitle, newStartDate);
            Titles newTtl = new Titles(newTtlId, OPEN_ENDED);
            titlesRepo.save(newTtl);
        }

        // ======== UPDATE DEPT EMPLOYEE ========
        if (!Objects.equals(newDeptNo, currDeptEmp.getDeptEmpId().getDeptNo())
                && !duplicateDeptEmp
        ) {
            deptEmpRepo.closeDeptEmpRecord(empNo, endDate, OPEN_ENDED);

            DeptEmpId newDeptEmpId = new DeptEmpId(empNo, newDeptNo);
            DeptEmp newDeptEmp = new DeptEmp(newDeptEmpId, newStartDate, OPEN_ENDED);
            deptEmpRepo.save(newDeptEmp);
        }

        // ======== UPDATE DEPT MANAGER ========
        if (toManager && !duplicateDeptMan) {
            if (currDeptMan != null &&
                    currDeptMan.getDeptManagerId().getEmpNo().equals(empNo)) {
                throw new DuplicateException("Dept Manager");
            }

            deptManagerRepo.closeDeptEmpRecord(empNo, endDate, OPEN_ENDED);

            DeptManagerId newDeptManId = new DeptManagerId(empNo, newDeptNo);
            DeptManager newDeptMan = new DeptManager(newDeptManId, newStartDate, OPEN_ENDED);
            deptManagerRepo.save(newDeptMan);
        }

        // ======== INSERT NEW DTO INTO DTO LIST ========
        promotionDTOList.add(promotionDTO);

        return promotionDTOList;
    }

    // HELPER FUNCTIONS
    private boolean isCurrentEmployee(Long empNo) {
        DeptEmp de = deptEmpRepo
                .findTopByDeptEmpIdEmpNoOrderByToDateDesc(empNo)
                .orElseThrow(() -> new IllegalStateException("No Dept Employee record found."));

        return de.getToDate().equals(LocalDate.of(9999, 1, 1));
    }

    public static EmployeesDTO toEmployeesDTO(Employees e) {
        EmployeesDTO dto = new EmployeesDTO();
        dto.setEmpNo(e.getEmpNo());
        dto.setFirstName(e.getFirstName());
        dto.setLastName(e.getLastName());
        dto.setGender(e.getGender());
        dto.setBirthDate(e.getBirthDate());
        dto.setHireDate(e.getHireDate());

        // Sort related fields before mapping
        dto.setTitles(
                e.getTitles()
                        .stream()
                        .sorted(Comparator.comparing(t -> t.getTitlesId().getFromDate()))   // sort by fromDate
                        .map(t -> {
                            TitlesDTO d = new TitlesDTO();
                            d.setTitle(t.getTitlesId().getTitle());
                            d.setFromDate(t.getTitlesId().getFromDate());
                            d.setToDate(t.getToDate());
                            return d;
                        })
                        .collect(Collectors.toList())
        );

        dto.setSalaries(
                e.getSalaries()
                        .stream()
                        .sorted(Comparator.comparing(s -> s.getSalariesId().getFromDate()))
                        .map(s -> {
                            SalariesDTO d = new SalariesDTO();
                            d.setSalary(s.getSalary());
                            d.setFromDate(s.getSalariesId().getFromDate());
                            d.setToDate(s.getToDate());
                            return d;
                        })
                        .collect(Collectors.toList())
        );

        dto.setDeptEmp(
                e.getDeptEmp()
                        .stream()
                        .sorted(Comparator.comparing(DeptEmp::getFromDate))
                        .map(d -> {
                            DeptEmpDTO x = new DeptEmpDTO();
                            x.setDeptNo(d.getDeptEmpId().getDeptNo());
                            x.setFromDate(d.getFromDate());
                            x.setToDate(d.getToDate());
                            return x;
                        })
                        .collect(Collectors.toList())
        );

        dto.setDeptManager(
                e.getDeptManager()
                        .stream()
                        .sorted(Comparator.comparing(DeptManager::getFromDate))
                        .map(d -> {
                            DeptManagerDTO x = new DeptManagerDTO();
                            x.setDeptNo(d.getDeptManagerId().getDeptNo());
                            x.setFromDate(d.getFromDate());
                            x.setToDate(d.getToDate());
                            return x;
                        })
                        .collect(Collectors.toList())
        );

        return dto;
    }

}
