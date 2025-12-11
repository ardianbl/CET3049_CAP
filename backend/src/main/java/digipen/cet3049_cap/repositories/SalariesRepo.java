package digipen.cet3049_cap.repositories;

import digipen.cet3049_cap.model.Salaries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalariesRepo extends JpaRepository<Salaries, Long> {
    @Query("SELECT s FROM Salaries s WHERE s.salariesId.empNo = :empNo")
    List<Salaries> findByEmpNo(@Param("empNo") Long empNo);
}
