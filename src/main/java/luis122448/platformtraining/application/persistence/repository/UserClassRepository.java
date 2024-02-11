package luis122448.platformtraining.application.persistence.repository;

import luis122448.platformtraining.application.persistence.dto.UserClassDTO;
import luis122448.platformtraining.application.persistence.entity.UserClassEntity;
import luis122448.platformtraining.application.persistence.entity.key.UserClassKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserClassRepository extends JpaRepository<UserClassEntity, UserClassKey> {

    @Query(value = "SELECT new luis122448.platformtraining.application.persistence.dto.UserClassDTO(" +
            "u.idCompany,u.idUser,u.idClass,c.typeClass,c.position,c.title,c.description,c.markdownContent,c.urlImage,c.idVideo,c.urlVideo," +
            "u.locked,u.begin,u.progress,u.finalized,u.advance,u.requiredTime) " +
            "FROM UserClassEntity u " +
            "INNER JOIN ClassEntity c ON c.idCompany = u.idCompany AND c.idCourse = :idCourse AND c.idClass = u.idClass " +
            "WHERE u.idCompany = :idCompany AND u.idUser = :idUser AND u.status = 'Y'")
    List<UserClassDTO> findByIdCourse(@Param("idCompany") Long idCompany, @Param("idUser") Long idUser, @Param("idCourse") Long idCourse);

    @Query(value = "SELECT new luis122448.platformtraining.application.persistence.dto.UserClassDTO(" +
            "u.idCompany,u.idUser,u.idClass,c.typeClass,c.position,c.title,c.description,c.markdownContent,c.urlImage,c.idVideo,c.urlVideo," +
            "u.locked,u.begin,u.progress,u.finalized,u.advance,u.requiredTime) " +
            "FROM UserClassEntity u " +
            "INNER JOIN ClassEntity c ON c.idCompany = u.idCompany AND c.idClass = u.idClass " +
            "WHERE u.idCompany = :idCompany AND u.idUser = :idUser AND u.idClass = :idClass AND u.status = 'Y'")
    Optional<UserClassDTO> findByIdClass(@Param("idCompany") Long idCompany, @Param("idUser") Long idUser, @Param("idClass") Long idClass);

}
