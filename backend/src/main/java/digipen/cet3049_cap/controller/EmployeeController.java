package digipen.cet3049_cap.controller;

import digipen.cet3049_cap.dto.DepartmentsDTO;
import digipen.cet3049_cap.dto.EmployeesDTO;
import digipen.cet3049_cap.model.Departments;
import digipen.cet3049_cap.model.Employees;
import digipen.cet3049_cap.service.EmployeesService;
import digipen.cet3049_cap.service.DepartmentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class EmployeeController {
    private final EmployeesService employeesService;
    private final DepartmentsService departmentsService;

    // DEBUG
    @GetMapping("/ping")
    public ResponseEntity<String> ping(){
        return ResponseEntity.ok("pong");
    }

    // ENDPOINT 1 - FIND ALL DEPARTMENTS RECORDS
    @GetMapping("/departments")
    public ResponseEntity<?> findAllDepartments(){
        List<DepartmentsDTO> result = departmentsService.findAllDepartments();

        if (result.isEmpty()){
            return ResponseEntity.status(404).body("No departments found");
        }
        return ResponseEntity.ok(result);
    }

    // ENDPOINT 2 - FIND FULL EMPLOYEES RECORDS BY ID
    @GetMapping("/employees")
    public ResponseEntity<?> findEmployeesRecord(@RequestParam Long empNo) {
        EmployeesDTO result = employeesService.findByEmpNo(empNo);

        return ResponseEntity.ok(result);
    }
}
