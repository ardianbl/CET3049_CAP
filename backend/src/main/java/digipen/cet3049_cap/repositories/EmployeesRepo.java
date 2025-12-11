package digipen.cet3049_cap.repositories;

import digipen.cet3049_cap.model.Employees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeesRepo extends JpaRepository<Employees, Long> {
    @Query("SELECT e FROM Employees e WHERE e.empNo = :empNo")
    List<Employees> findByEmpNo(@Param("empNo") Long empNo);
}
