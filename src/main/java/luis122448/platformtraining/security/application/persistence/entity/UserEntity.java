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
    @Column(name = "USERNAME", nullable = false)
    private String username;
    @Column(name = "ENCODE", nullable = false)
    private String encode;
    private String secretCode;
    private String role;
    private Integer nivel;
    private String name;
    private String lastName;
    private String urlPhoto;
    private String email;
    private String address;
    private String phone;
    private String comment;
    @Column(name = "REGIST_DATE", nullable = true)
    private LocalDate registdate;
    @Column(name = "EXPIRE_DATE", nullable = true)
    private LocalDate expiredate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_COMPANY", referencedColumnName = "ID_COMPANY", insertable = false, updatable = false)
    private CompanyEntity companyEntity;

}
