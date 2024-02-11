package luis122448.platformtraining.application.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import luis122448.platformtraining.security.authentication.auditing.AuditingEntity;
import luis122448.platformtraining.application.persistence.entity.key.UserCourseKey;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
@IdClass(UserCourseKey.class)
@Entity
@Table(schema = "public", name = "TBL_USER_COURSE")
public class UserCourseEntity extends AuditingEntity {

    @Id
    @Column(name = "ID_COMPANY", nullable = false)
    private Long idCompany;
    @Id
    @Column(name = "ID_USER", nullable = false)
    private Long idUser;
    @Id
    @Column(name = "ID_COURSE", nullable = false)
    private Long idCourse;
    // Audit
    @Column(name = "USER_REGISTER")
    private Long userRegister;
    private LocalDate registerDate;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate expirationDate;
    private Boolean locked;
    // Stats
    private Boolean begin;
    private Boolean progress;
    private Boolean finalized;
    private BigDecimal advance;
    private BigDecimal requiredTime;
    // Eval
    private Long idLastExam;

}
