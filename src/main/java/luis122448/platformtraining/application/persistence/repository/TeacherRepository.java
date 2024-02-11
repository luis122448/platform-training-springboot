package luis122448.platformtraining.application.persistence.repository;

import luis122448.platformtraining.application.persistence.entity.TeacherEntity;
import luis122448.platformtraining.application.persistence.entity.key.TeacherKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface TeacherRepository extends JpaRepository<TeacherEntity, TeacherKey> {

    @Query(value = "SELECT t FROM TeacherEntity t " +
            "LEFT OUTER JOIN CourseEntity c ON c.idCompany = t.idCompany AND c.idTeacher = t.idTeacher " +
            "WHERE t.idCompany = :idCompany AND c.idCourse = :idCourse")
    Optional<TeacherEntity> findByIdCourse(@Param("idCompany") Long idCompany, @Param("idCourse") Long idCourse);

    @Query(value = "SELECT t FROM TeacherEntity t " +
            "LEFT OUTER JOIN ClassEntity c ON c.idCompany = t.idCompany AND c.idTeacher = t.idTeacher " +
            "WHERE t.idCompany = :idCompany AND c.idClass = :idClass")
    Optional<TeacherEntity> findByIdClass(@Param("idCompany") Long idCompany, @Param("idClass") Long idClass);

}
