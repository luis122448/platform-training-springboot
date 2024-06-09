package luis122448.platformtraining.security.application.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokenModel {
    private String username;
    private String role;
    private String token;
    private String refreshToken;
}
