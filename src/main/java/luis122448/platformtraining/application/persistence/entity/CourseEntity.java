package luis122448.platformtraining.application.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import luis122448.platformtraining.security.authentication.auditing.AuditingEntity;
import luis122448.platformtraining.application.persistence.entity.key.CourseKey;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@IdClass(CourseKey.class)
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(schema = "public",name = "TBL_COURSE")
public class CourseEntity extends AuditingEntity {
    @Id
    @Column(name = "ID_COMPANY", nullable = false)
    private Long idCompany;
    @Id
    @Column(name = "ID_COURSE", nullable = false)
    private Long idCourse;
    @Column(name = "ID_MODULE", nullable = false)
    private Long idModule;
    @Column(name = "ID_TEACHER", nullable = false)
    private Long idTeacher;
    @Column(nullable = false, length = 100)
    private String title;
    @Column(nullable = false, length = 500)
    private String description;
    @Column(name = "MARKDOWN_CONTENT", nullable = true, length = 4000)
    private String markdownContent;
    private String urlIcon;
    private String urlLogo;
    private String urlBackground;
    @Column(name = "ID_EXAM", nullable = false)
    private Long idExam;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "ID_COMPANY", referencedColumnName = "ID_COMPANY", insertable = false, updatable = false),
            @JoinColumn(name = "ID_TEACHER", referencedColumnName = "ID_TEACHER", insertable = false, updatable = false)
    })
    private TeacherEntity teacherEntity;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "ID_COMPANY", referencedColumnName = "ID_COMPANY", insertable = false, updatable = false),
            @JoinColumn(name = "ID_EXAM", referencedColumnName = "ID_EXAM", insertable = false, updatable = false)
    })
    private ExamEntity examEntity;

}
