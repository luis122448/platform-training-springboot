package luis122448.platformtraining.application.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import luis122448.platformtraining.security.authentication.auditing.AuditingEntity;
import luis122448.platformtraining.application.persistence.entity.key.TeacherKey;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@IdClass(TeacherKey.class)
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(schema = "public",name = "TBL_TEACHER")
public class TeacherEntity extends AuditingEntity {

    @Id
    @Column(name = "ID_COMPANY", nullable = false)
    private Long idCompany;
    @Id
    @Column(name = "ID_TEACHER", nullable = false)
    private Long idTeacher;
    private String name;
    private String lastName;
    private String urlPhoto;
}
