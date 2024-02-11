package luis122448.platformtraining.application.persistence.dto;

import lombok.*;
import luis122448.platformtraining.application.persistence.entity.enums.TypeClassEnum;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserClassDTO {

    private Long idCompany;
    private Long idUser;
    private Long idClass;
    private TypeClassEnum typeClass;
    private BigDecimal position;
    private String title;
    private String description;
    private String markdownContent;
    private String urlImage;
    private String idVideo;
    private String urlVideo;
    private Boolean locked;
    private Boolean begin;
    private Boolean progress;
    private Boolean finalized;
    private BigDecimal advance;
    private BigDecimal requiredTime;

}
