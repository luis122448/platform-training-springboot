package luis122448.platformtraining.security.application.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import luis122448.platformtraining.security.authentication.auditing.AuditingEntity;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(schema = "public", name = "TBL_COMPANY")
public class CompanyEntity extends AuditingEntity {

    @Id
    @Column(name = "ID_COMPANY",nullable = false)
    private Long idCompany;
    private String ssn;
    private String name;
    private String urlLogo;

}
