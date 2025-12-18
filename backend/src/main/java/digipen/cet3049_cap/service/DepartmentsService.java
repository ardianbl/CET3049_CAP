package digipen.cet3049_cap.service;

import digipen.cet3049_cap.dto.DepartmentsDTO;
import digipen.cet3049_cap.model.Departments;
import digipen.cet3049_cap.repositories.DepartmentsRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Service layer for department-related business logic.
 * Provides methods to retrieve department DTOs used by controllers.
 */
@Service
@RequiredArgsConstructor
public class DepartmentsService {
    private final DepartmentsRepo departmentsRepo;

    /**
     * Retrieve all departments from the repository and map them to DTOs.
     * @return list of {@link digipen.cet3049_cap.dto.DepartmentsDTO}
     */
    @Transactional
    public List<DepartmentsDTO> findAllDepartments(){
        List<Departments> d = departmentsRepo.findAll(Sort.by("deptNo").ascending());

        List<DepartmentsDTO> dtos = new ArrayList<>();
        for (Departments d1 : d){
            dtos.add(DepartmentsService.toDepartmentsDTO(d1));
        }

        return dtos;
    }

    /**
     * Map a {@link digipen.cet3049_cap.model.Departments} entity to its DTO representation.
     * @param d department entity
     * @return department DTO
     */
    public static DepartmentsDTO toDepartmentsDTO(Departments d) {
        DepartmentsDTO dto = new DepartmentsDTO();
        dto.setDeptNo(d.getDeptNo());
        dto.setDeptName(d.getDeptName());

        return dto;
    }
}
