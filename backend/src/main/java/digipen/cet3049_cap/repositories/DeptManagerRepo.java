package digipen.cet3049_cap.repositories;

import digipen.cet3049_cap.model.DeptManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeptManagerRepo extends JpaRepository<DeptManager, Long> {
    @Query("SELECT dm FROM DeptManager dm WHERE dm.id.empNo = :empNo")
    List<DeptManager> findByEmpNo(@Param("empNo") Long empNo);
}
