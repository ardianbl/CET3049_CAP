package digipen.cet3049_cap.service;

import digipen.cet3049_cap.dto.DepartmentsDTO;
import digipen.cet3049_cap.dto.EmployeesDTO;
import digipen.cet3049_cap.model.Departments;
import digipen.cet3049_cap.model.Employees;
import digipen.cet3049_cap.repositories.DepartmentsRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentsService {
    private final DepartmentsRepo departmentsRepo;

    @Transactional
    public List<DepartmentsDTO> findAllDepartments(){
        List<Departments> d = departmentsRepo.findAll(Sort.by("deptNo").ascending());

        List<DepartmentsDTO> dtos = new ArrayList<>();
        for (Departments d1 : d){
            dtos.add(DepartmentsService.toDepartmentsDTO(d1));
        }

        return dtos;
    }

    public static DepartmentsDTO toDepartmentsDTO(Departments d) {
        DepartmentsDTO dto = new DepartmentsDTO();
        dto.setDeptNo(d.getDeptNo());
        dto.setDeptName(d.getDeptName());

        return dto;
    }
}
