package digipen.cet3049_cap.repositories;

import digipen.cet3049_cap.model.DeptEmp;
import digipen.cet3049_cap.model.Titles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface TitlesRepo extends JpaRepository<Titles, Long> {

    Optional<Titles> findTopByTitlesIdEmpNoOrderByToDateDesc(Long empNo);

    @Modifying
    @Query("UPDATE Titles t SET t.toDate = :endDate " +
            "WHERE t.titlesId.empNo = :empNo AND t.toDate = :openEnded")
    void closeDeptEmpRecord(@Param("empNo") Long empNo,
                            @Param("endDate") LocalDate endDate,
                            @Param("openEnded") LocalDate openEnded);
}
