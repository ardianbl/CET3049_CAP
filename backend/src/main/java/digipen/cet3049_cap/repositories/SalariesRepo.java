package digipen.cet3049_cap.repositories;

import digipen.cet3049_cap.model.DeptEmp;
import digipen.cet3049_cap.model.Salaries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface SalariesRepo extends JpaRepository<Salaries, Long> {

    Optional<Salaries> findTopBySalariesIdEmpNoOrderByToDateDesc(Long empNo);

    @Modifying
    @Query("UPDATE Salaries s SET s.toDate = :endDate " +
            "WHERE s.salariesId.empNo = :empNo AND s.toDate = :openEnded")
    void closeDeptEmpRecord(@Param("empNo") Long empNo,
                            @Param("endDate") LocalDate endDate,
                            @Param("openEnded") LocalDate openEnded);
}
