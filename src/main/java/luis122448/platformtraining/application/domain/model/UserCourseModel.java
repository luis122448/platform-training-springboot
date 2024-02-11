package luis122448.platformtraining.application.domain.model;

import jakarta.persistence.*;
import lombok.*;
import luis122448.platformtraining.application.persistence.entity.TeacherEntity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserCourseModel {

    private Long idCompany;
    private Long idUser;
    private Long idCourse;
    private String title;
    private String description;
    private String markdownContent;
    private String urlIcon;
    private String urlLogo;
    private String urlBackground;
    private Boolean locked;
    private Boolean begin;
    private Boolean progress;
    private Boolean finalized;
    private BigDecimal advance;
    private BigDecimal requiredTime;
    private TeacherModel teacherModel;
    private List<UserClassModel> userClassModelList;
}
