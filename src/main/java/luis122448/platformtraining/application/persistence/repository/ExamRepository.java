package luis122448.platformtraining.application.persistence.repository;

import luis122448.platformtraining.application.persistence.entity.ExamEntity;
import luis122448.platformtraining.application.persistence.entity.key.ExamKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ExamRepository extends JpaRepository<ExamEntity, ExamKey>{

    @Query(value = "SELECT e FROM ExamEntity e WHERE e.idCompany = :idCompany " +
            "AND e.idCourse = :idCourse " +
            "AND e.status = 'Y'")
    Optional<ExamEntity> findByIdCourse(@Param("idCompany") Long idCompany,@Param("idCourse") Long idCourse);
}
