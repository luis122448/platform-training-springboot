package luis122448.platformtraining.application.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import luis122448.platformtraining.application.persistence.entity.key.UserModuleKey;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@IdClass(UserModuleKey.class)
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(schema = "public",name = "TBL_USER_MODULE")
public class UserModuleEntity {

    @Id
    @Column(name = "ID_COMPANY", nullable = false)
    private Long idCompany;
    @Id
    @Column(name = "ID_USER", nullable = false)
    private Long idUser;
    @Id
    @Column(name = "ID_MODULE", nullable = false)
    private Long idModule;
    // Audit
    @Column(name = "USER_REGISTER")
    private Long userRegister;
    private LocalDate registerDate;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate expirationDate;
    // Stats
    private Boolean locked;
    private Boolean begin;
    private Boolean progress;
    private Boolean finalized;
    private BigDecimal advance;
    private BigDecimal requiredTime;

}
