package digipen.cet3049_cap.service;

import digipen.cet3049_cap.dto.*;
import digipen.cet3049_cap.exception.DepartmentNotFoundException;
import digipen.cet3049_cap.model.Departments;
import digipen.cet3049_cap.model.DeptEmp;
import digipen.cet3049_cap.model.DeptManager;
import digipen.cet3049_cap.model.Employees;
import digipen.cet3049_cap.repositories.DepartmentsRepo;
import digipen.cet3049_cap.repositories.DeptEmpRepo;
import digipen.cet3049_cap.repositories.EmployeesRepo;
import digipen.cet3049_cap.exception.EmployeeNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeesService {
    private final EmployeesRepo employeesRepo;
    private final DepartmentsRepo departmentsRepo;
    private final DeptEmpRepo deptEmpRepo;

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
    public void promoteEmployee(Long empNo) {
        Employees e = employeesRepo.findById(empNo)
                .orElseThrow(() -> new EmployeeNotFoundException());


    }

    // HELPER FUNCTIONS
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
                            x.setDeptNo(d.getId().getDeptNo());
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
                            x.setDeptNo(d.getId().getDeptNo());
                            x.setFromDate(d.getFromDate());
                            x.setToDate(d.getToDate());
                            return x;
                        })
                        .collect(Collectors.toList())
        );

        return dto;
    }

}
