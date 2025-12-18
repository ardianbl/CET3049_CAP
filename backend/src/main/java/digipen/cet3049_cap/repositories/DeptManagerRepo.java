package digipen.cet3049_cap.repositories;

import digipen.cet3049_cap.model.DeptManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface DeptManagerRepo extends JpaRepository<DeptManager, Long> {
    @Query("SELECT dm FROM DeptManager dm WHERE dm.deptManagerId.empNo = :empNo")
    List<DeptManager> findByEmpNo(@Param("empNo") Long empNo);

//    @Query("SELECT dm FROM DeptManager dm WHERE dm.deptManagerId.deptNo = :deptNo " +
//            "ORDER BY dm.toDate DESC LIMIT 1")
//    DeptManager getLatestDeptManager(@Param("deptNo") String deptNo);

    Optional<DeptManager> findTopByDeptManagerIdDeptNoOrderByToDateDesc(String deptNo);

    @Modifying
    @Query("UPDATE DeptManager dm SET dm.toDate = :endDate " +
            "WHERE dm.deptManagerId.empNo = :empNo AND dm.toDate = :openEnded")
    void closeDeptEmpRecord(@Param("empNo") Long empNo,
                            @Param("endDate") LocalDate endDate,
                            @Param("openEnded") LocalDate openEnded);
}
