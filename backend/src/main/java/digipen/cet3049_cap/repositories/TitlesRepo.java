package digipen.cet3049_cap.repositories;

import digipen.cet3049_cap.model.Titles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TitlesRepo extends JpaRepository<Titles, Long> {
    @Query("SELECT t FROM Titles t WHERE t.titlesId.empNo = :empNo")
    List<Titles> findByEmpNo(@Param("empNo") Long empNo);
}
