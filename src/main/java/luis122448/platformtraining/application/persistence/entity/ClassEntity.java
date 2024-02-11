package luis122448.platformtraining.application.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import luis122448.platformtraining.security.authentication.auditing.AuditingEntity;
import luis122448.platformtraining.application.persistence.entity.enums.TypeClassEnum;
import luis122448.platformtraining.application.persistence.entity.key.ClassKey;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@IdClass(ClassKey.class)
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(schema = "public",name = "TBL_CLASS")
public class ClassEntity extends AuditingEntity{

    @Id
    @Column(name = "ID_COMPANY",nullable = false)
    private Long idCompany;
    @Id
    @Column(name = "ID_CLASS",nullable = false)
    private Long idClass;
    @Column(name = "ID_COURSE",nullable = false)
    private Long idCourse;
    @Column(name = "ID_TEACHER", nullable = false)
    private Long idTeacher;
    @Column(nullable = false)
    private BigDecimal position;
    @Column(name = "TYPE_CLASS", nullable = false)
    @Enumerated(EnumType.STRING)
    private TypeClassEnum typeClass;
    @Column(nullable = false, length = 100)
    private String title;
    @Column(nullable = false, length = 250)
    private String description;
    @Column(name = "MARKDOWN_CONTENT", nullable = true, length = 4000)
    private String markdownContent;
    private String urlImage;
    private String idVideo;
    private String urlVideo;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "ID_COMPANY", referencedColumnName = "ID_COMPANY", insertable = false, updatable = false),
            @JoinColumn(name = "ID_CLASS", referencedColumnName = "ID_CLASS", insertable = false, updatable = false)
    })
    @OrderBy("likeComment DESC")
    private List<CommentEntity> commentEntityList;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "ID_COMPANY", referencedColumnName = "ID_COMPANY", insertable = false, updatable = false),
            @JoinColumn(name = "ID_TEACHER", referencedColumnName = "ID_TEACHER", insertable = false, updatable = false)
    })
    private TeacherEntity teacherEntity;
}
