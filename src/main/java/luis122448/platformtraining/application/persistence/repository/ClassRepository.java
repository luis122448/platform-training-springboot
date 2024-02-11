package luis122448.platformtraining.application.persistence.repository;

import luis122448.platformtraining.application.persistence.entity.ClassEntity;
import luis122448.platformtraining.application.persistence.entity.key.ClassKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClassRepository extends JpaRepository<ClassEntity, ClassKey> {

    @Query(value = "SELECT c FROM ClassEntity c WHERE c.idCompany = :idCompany AND c.idCourse = :idCourse AND c.status = 'Y'")
    List<ClassEntity> findByIdCourse(@Param("idCompany") Long idCompany, @Param("idCourse") Long idCourse);

}
