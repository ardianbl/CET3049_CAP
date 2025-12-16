package digipen.cet3049_cap.repositories;

import digipen.cet3049_cap.model.Employees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeesRepo extends JpaRepository<Employees, Long> {
}
