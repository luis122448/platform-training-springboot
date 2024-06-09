package luis122448.platformtraining.security.application.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@ToString
public class UserModel {
    private Long idCompany;
    private Long idUser;
    private String company;
    private String username;
    private String encode;
    private Integer nivel;
    private String secretCode;
    private String role;
    private LocalDate registdate;
    private LocalDate expiredate;
    private String status;

}
