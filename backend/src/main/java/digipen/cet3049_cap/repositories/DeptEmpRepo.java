package digipen.cet3049_cap.repositories;

import digipen.cet3049_cap.model.DeptEmp;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

/**
 * Repository for accessing {@link digipen.cet3049_cap.model.DeptEmp} records.
 */
@Repository
public interface DeptEmpRepo extends JpaRepository<DeptEmp, Integer> {

    @Query("SELECT de FROM DeptEmp de WHERE de.deptEmpId.deptNo = :deptNo")
    Page<DeptEmp> findEmployeesByDepartment(@Param("deptNo") String deptNo, Pageable pageable);

    Optional<DeptEmp> findTopByDeptEmpIdEmpNoOrderByToDateDesc(Long empNo);

    @Modifying
    @Query("UPDATE DeptEmp de SET de.toDate = :endDate " +
            "WHERE de.deptEmpId.empNo = :empNo AND de.toDate = :openEnded")
    void closeDeptEmpRecord(@Param("empNo") Long empNo,
                            @Param("endDate")LocalDate endDate,
                            @Param("openEnded") LocalDate openEnded);
}
