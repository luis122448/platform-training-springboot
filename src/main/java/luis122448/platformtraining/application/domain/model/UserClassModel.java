package luis122448.platformtraining.application.domain.model;

import jakarta.persistence.*;
import lombok.*;
import luis122448.platformtraining.application.persistence.entity.CommentEntity;
import luis122448.platformtraining.application.persistence.entity.TeacherEntity;
import luis122448.platformtraining.application.persistence.entity.enums.TypeClassEnum;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserClassModel {

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
    private List<CommentModel> commentModelList;
    private TeacherModel teacherModel;
}
