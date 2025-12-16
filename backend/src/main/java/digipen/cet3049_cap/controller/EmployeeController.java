package digipen.cet3049_cap.controller;

import digipen.cet3049_cap.dto.DepartmentsDTO;
import digipen.cet3049_cap.dto.EmployeesDTO;
import digipen.cet3049_cap.dto.PromotionDTO;
import digipen.cet3049_cap.dto.SimpleEmployeesDTO;
import digipen.cet3049_cap.model.Departments;
import digipen.cet3049_cap.model.Employees;
import digipen.cet3049_cap.service.EmployeesService;
import digipen.cet3049_cap.service.DepartmentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class EmployeeController {
    private final EmployeesService employeesService;
    private final DepartmentsService departmentsService;

    // CHECK SERVER CONNECTION
    @GetMapping("/ping")
    public ResponseEntity<String> ping() {

        return ResponseEntity.ok("pong");
    }

    // ENDPOINT 1 - FIND ALL DEPARTMENTS RECORDS
    @GetMapping("/departments")
    public List<DepartmentsDTO> findAllDepartments() {

        return departmentsService.findAllDepartments();
    }

    // ENDPOINT 2 - FIND FULL EMPLOYEES RECORDS BY ID
    @GetMapping("/employees/{id}")
    public EmployeesDTO findEmployeesRecord(@PathVariable Long id) {

        return employeesService.findByEmpNo(id);
    }

    // ENDPOINT 3 - FIND EMPLOYEES RECORDS BY DEPT NUMBER
    @GetMapping("/employees")
    public Page<SimpleEmployeesDTO> findByDeptNo(
            @RequestParam String deptNo,
            @PageableDefault(size = 20) Pageable pageable) {

        int uiPage = pageable.getPageNumber();
        int springPage = Math.max(uiPage - 1, 0);

        Pageable adjustedPageable = PageRequest.of(
                springPage,
                pageable.getPageSize(),
                pageable.getSort()
        );

        return employeesService.getEmployeesByDeptNo(deptNo, adjustedPageable);
    }

    // ENDPOINT 4 - PROMOTE EMPLOYEE
    @PostMapping(value = "/employees/promote/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<PromotionDTO> promoteEmployee(@PathVariable Long id) {

        List<PromotionDTO> promotionDTOList = new ArrayList<>();

        promotionDTOList.add(employeesService.findByEmpNo(id));

        employeesService.promoteEmployee(id);
    }
}
