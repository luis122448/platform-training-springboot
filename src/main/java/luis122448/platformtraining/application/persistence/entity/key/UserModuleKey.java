package luis122448.platformtraining.application.persistence.entity.key;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserModuleKey implements Serializable {
        private Long idCompany;
        private Long idUser;
        private Long idModule;
}
