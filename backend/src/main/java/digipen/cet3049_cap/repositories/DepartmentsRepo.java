package digipen.cet3049_cap.repositories;

import digipen.cet3049_cap.model.Departments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for accessing {@link digipen.cet3049_cap.model.Departments} entities.
 */
@Repository
public interface DepartmentsRepo extends JpaRepository<Departments, String> {
    @Query("SELECT d FROM Departments d WHERE d.deptNo = :deptNo")
    List<Departments> findByDeptNo(@Param("deptNo") String deptNo);
}
