package luis122448.platformtraining.application.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import luis122448.platformtraining.security.authentication.auditing.AuditingEntity;
import luis122448.platformtraining.application.persistence.entity.key.ModuleKey;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@IdClass(ModuleKey.class)
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(schema = "public",name = "TBL_MODULE")
public class ModuleEntity extends AuditingEntity {

    @Id
    @Column(name = "ID_COMPANY", nullable = false)
    private Long idCompany;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_MODULE", nullable = false)
    private Long idModule;
    @Column(nullable = false, length = 100)
    private String title;
    @Column(nullable = false, length = 250)
    private String description;
    @Column(name = "MARKDOWN_CONTENT", nullable = true, length = 4000)
    private String markdownContent;
    private String urlIcon;
    private String urlLogo;
    private String urlBackground;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "ID_COMPANY", referencedColumnName = "ID_COMPANY", insertable = false, updatable = false),
            @JoinColumn(name = "ID_MODULE", referencedColumnName = "ID_MODULE", insertable = false, updatable = false)
    })
    private List<CourseEntity> courseEntities;

}
