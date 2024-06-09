package luis122448.platformtraining.application.persistence.repository;

import luis122448.platformtraining.application.persistence.entity.ModuleEntity;
import luis122448.platformtraining.application.persistence.entity.key.ModuleKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ModuleRepository extends JpaRepository<ModuleEntity, ModuleKey> {

    @Query(value = "SELECT m FROM ModuleEntity m " +
            "WHERE m.idCompany = :idCompany AND EXISTS (" +
            "SELECT 1 FROM UserCourseEntity uc " +
            "INNER JOIN CourseEntity c ON c.idCompany = uc.idCompany AND c.idCourse = uc.idCourse " +
            "WHERE uc.idCompany = :idCompany AND uc.idUser = :idUser AND c.idModule = m.idModule)")
    List<ModuleEntity> findByIdUser(@Param("idCompany") Long idCompany, @Param("idUser") Long idUser);

}
