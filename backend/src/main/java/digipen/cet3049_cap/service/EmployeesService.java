package digipen.cet3049_cap.service;

import digipen.cet3049_cap.dto.DepartmentsDTO;
import digipen.cet3049_cap.dto.DeptEmpDTO;
import digipen.cet3049_cap.dto.DeptManagerDTO;
import digipen.cet3049_cap.dto.EmployeesDTO;
import digipen.cet3049_cap.dto.PromotionDTO;
import digipen.cet3049_cap.dto.SalariesDTO;
import digipen.cet3049_cap.dto.SimpleEmployeesDTO;
import digipen.cet3049_cap.dto.TitlesDTO;
import digipen.cet3049_cap.model.Departments;
import digipen.cet3049_cap.model.DeptEmp;
import digipen.cet3049_cap.model.DeptManager;
import digipen.cet3049_cap.model.Employees;
import digipen.cet3049_cap.model.Salaries;
import digipen.cet3049_cap.model.Titles;
import digipen.cet3049_cap.repositories.EmployeesRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeesService {
    private final EmployeesRepo employeesRepo;

    @Transactional
    public EmployeesDTO findByEmpNo(Long empNo){
        Employees e = employeesRepo.findById(empNo)
                .orElseThrow(() -> new RuntimeException());

        return EmployeesService.toEmployeesDTO(e);
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
