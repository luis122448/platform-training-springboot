package luis122448.platformtraining.security.application.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import luis122448.platformtraining.security.application.persistence.entity.key.UserKey;
import luis122448.platformtraining.security.authentication.auditing.AuditingEntity;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Entity
@IdClass(UserKey.class)
@Table(schema = "public",name ="TBL_USER")
public class UserEntity extends AuditingEntity {

    @Id
    @Column(name = "ID_COMPANY", nullable = false)
    private Long idCompany;
    @Id
    @Column(name = "ID_USER", nullable = false)
    private Long idUser;
    private String username;
    private String password;
    private boolean isVerifyCode;
    private String secretCode;
    private String role;
    private String name;
    private String lastName;
    private String urlPhoto;
    private String email;
    private String address;
    private String phone;
    private String comment;
    private LocalDate expirationUsername;
    private LocalDate expirationPassword;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_COMPANY", referencedColumnName = "ID_COMPANY", insertable = false, updatable = false)
    private CompanyEntity companyEntity;

}
