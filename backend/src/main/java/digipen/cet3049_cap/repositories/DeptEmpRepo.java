package digipen.cet3049_cap.repositories;

import digipen.cet3049_cap.model.DeptEmp;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DeptEmpRepo extends JpaRepository<DeptEmp, Integer> {

    @Query("SELECT de FROM DeptEmp de WHERE de.id.deptNo = :deptNo")
    Page<DeptEmp> findEmployeesByDepartment(@Param("deptNo") String deptNo, Pageable pageable);

    @Query("SELECT de FROM DeptEmp de WHERE de.id.empNo = :empNo " +
            "ORDER BY de.toDate DESC LIMIT 1")
    DeptEmp getLatestDeptEmp(@Param("empNo") Long empNo);
}
