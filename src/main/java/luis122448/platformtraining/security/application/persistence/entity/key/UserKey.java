package luis122448.platformtraining.security.application.persistence.entity.key;

import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserKey implements Serializable {

    private Long idCompany;
    private Long idUser;

}
