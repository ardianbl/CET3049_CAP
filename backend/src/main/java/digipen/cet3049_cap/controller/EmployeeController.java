package digipen.cet3049_cap.controller;

import digipen.cet3049_cap.dto.DepartmentsDTO;
import digipen.cet3049_cap.dto.EmployeesDTO;
import digipen.cet3049_cap.dto.PromotionDTO;
import digipen.cet3049_cap.dto.SimpleEmployeesDTO;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * REST controller exposing endpoints for employees and departments used by the frontend.
 * Provides endpoints for ping, listing departments, retrieving employee records,
 * paginated employee queries by department, and promoting employees.
 */
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class EmployeeController {
    private final EmployeesService employeesService;
    private final DepartmentsService departmentsService;

    /**
     * Simple health check endpoint returning a plain "pong" response.
     * @return 200 OK with body "pong"
     */
    @GetMapping("/ping")
    public ResponseEntity<String> ping() {

        return ResponseEntity.ok("pong");
    }

    /**
     * Retrieve all departments as DTOs.
     * @return list of {@link digipen.cet3049_cap.dto.DepartmentsDTO}
     */
    @GetMapping("/departments")
    public List<DepartmentsDTO> findAllDepartments() {

        return departmentsService.findAllDepartments();
    }

    /**
     * Retrieve full employee information by employee number.
     * @param id employee number
     * @return {@link digipen.cet3049_cap.dto.EmployeesDTO} for the given id
     */
    @GetMapping("/employees/{id}")
    public EmployeesDTO findEmployeesRecord(@PathVariable Long id) {

        return employeesService.findByEmpNo(id);
    }

    /**
     * Return a paginated list of simple employee DTOs for a department.
     * @param deptNo department identifier
     * @param pageable pagination parameters (page, size, sort)
     * @return paginated {@link digipen.cet3049_cap.dto.SimpleEmployeesDTO}
     */
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

        /**
         * Promote an employee by applying title/department/salary/manager changes.
         * Expects a {@link digipen.cet3049_cap.dto.PromotionDTO} payload and returns
         * a list containing the previous state and the updated promotion DTOs.
         * @param promotionDTO promotion request data
         * @return list of {@link digipen.cet3049_cap.dto.PromotionDTO}
         */
    @PostMapping(value = "/promote",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PromotionDTO>> promoteEmployee(@RequestBody PromotionDTO promotionDTO) {

        List<PromotionDTO> result = employeesService.promoteEmployee(promotionDTO);
        return ResponseEntity.ok(result);
    }
}
