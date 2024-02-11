package luis122448.platformtraining.security.application.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class UserModel {

    private Long idCompany;
    private Long idUser;
    private String company;
    private String username;
    private String password;
    private boolean isVerifyCode;
    private String secretCode;
    private String role;
    private String name;
    private String lastName;
    private String urlImage;
    private String email;
    private String address;
    private String phone;
    private String comment;
    private LocalDate expirationUsername;
    private LocalDate expirationPassword;
    private String status;
}
