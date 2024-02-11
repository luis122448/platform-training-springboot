package luis122448.platformtraining.application.persistence.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserCourseDTO {

    private Long idCompany;
    private Long idUser;
    private Long idCourse;
    private String title;
    private String description;
    private String urlIcon;
    private String urlLogo;
    private String urlBackground;
    private Boolean locked;
    private Boolean begin;
    private Boolean progress;
    private Boolean finalized;
    private BigDecimal advance;
    private BigDecimal requiredTime;

}
