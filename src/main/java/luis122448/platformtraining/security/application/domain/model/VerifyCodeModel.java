package luis122448.platformtraining.security.application.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VerifyCodeModel implements Serializable {
    private String company;
    private String username;
    private String verifyCode;
}
