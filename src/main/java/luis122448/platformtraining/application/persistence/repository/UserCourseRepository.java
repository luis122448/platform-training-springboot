package luis122448.platformtraining.application.persistence.repository;
import luis122448.platformtraining.application.persistence.dto.UserCourseDTO;
import luis122448.platformtraining.application.persistence.entity.UserCourseEntity;
import luis122448.platformtraining.application.persistence.entity.key.UserCourseKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserCourseRepository extends JpaRepository<UserCourseEntity, UserCourseKey> {

    @Query(value = "SELECT  new luis122448.platformtraining.application.persistence.dto.UserCourseDTO(" +
            "u.idCompany,u.idUser,u.idCourse,c.title,c.description,c.urlIcon,c.urlLogo,c.urlBackground,u.locked,u.begin,u.progress,u.finalized,u.advance,u.requiredTime) " +
            "FROM UserCourseEntity u " +
            "LEFT OUTER JOIN CourseEntity c ON c.idCompany = u.idCompany AND c.idModule = :idModule " +
            "WHERE u.idCompany = :idCompany AND u.idUser = :idUser AND u.status = 'Y'")
    List<UserCourseDTO> findByIdModule(@Param("idCompany") Long idCompany, @Param("idUser") Long idUser, @Param("idModule") Long idModule);

    @Query(value = "SELECT new luis122448.platformtraining.application.persistence.dto.UserCourseDTO( " +
            "u.idCompany,u.idUser,u.idCourse,c.title,c.description,c.urlIcon,c.urlLogo,c.urlBackground,u.locked,u.begin,u.progress,u.finalized,u.advance,u.requiredTime) " +
            "FROM UserCourseEntity u " +
            "LEFT OUTER JOIN CourseEntity c ON c.idCompany = u.idCompany AND c.idCourse = u.idCourse " +
            "WHERE u.idCompany = :idCompany AND u.idUser = :idUser AND u.idCourse = :idCourse AND u.status = 'Y'")
    Optional<UserCourseDTO> findByIdCourse(@Param("idCompany") Long idCompany, @Param("idUser") Long idUser, @Param("idCourse") Long idCourse);

}
