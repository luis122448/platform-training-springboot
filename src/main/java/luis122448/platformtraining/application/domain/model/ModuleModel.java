package luis122448.platformtraining.application.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ModuleModel {

    private Long idCompany;
    private Long idModule;
    private String title;
    private String description;
    private String markdownContent;
    private String urlIcon;
    private String urlLogo;
    private String urlBackground;

}
