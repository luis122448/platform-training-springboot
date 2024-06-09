package luis122448.platformtraining.application.persistence.entity.key;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserClassKey implements Serializable {
    private Long idCompany;
    private Long idUser;
    private Long idClass;
}
