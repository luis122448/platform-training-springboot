package luis122448.platformtraining.application.persistence.repository;

import luis122448.platformtraining.application.persistence.entity.CourseEntity;
import luis122448.platformtraining.application.persistence.entity.key.CourseKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CourseRepository extends JpaRepository<CourseEntity, CourseKey> {

    @Query(value = "SELECT c " +
            "FROM CourseEntity c " +
            "WHERE c.idCompany = :idCompany AND c.idModule = :idModule AND c.status = 'Y'")
    List<CourseEntity> findByIdModule(@Param("idCompany") Long idCompany, @Param("idModule") Long idModule);

}
