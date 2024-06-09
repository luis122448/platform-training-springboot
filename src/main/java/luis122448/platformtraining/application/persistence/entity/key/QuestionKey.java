package luis122448.platformtraining.application.persistence.entity.key;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionKey implements Serializable {
    private Long idCompany;
    private Long idQuestion;
}
