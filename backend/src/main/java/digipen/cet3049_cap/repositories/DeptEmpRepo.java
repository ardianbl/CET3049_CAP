package digipen.cet3049_cap.repositories;

import digipen.cet3049_cap.model.DeptEmp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeptEmpRepo extends JpaRepository<DeptEmp, Integer> {
    @Query("SELECT de FROM DeptEmp de WHERE de.id.empNo = :empNo")
    List<DeptEmp> findByEmpNo(@Param("empNo") int empNo);
}
