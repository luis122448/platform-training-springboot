package luis122448.platformtraining.application.persistence.entity.key;

import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClassKey implements Serializable {
    private Long idCompany;
    private Long idClass;
}
