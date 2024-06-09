package luis122448.platformtraining.application.domain.model;

import jakarta.persistence.*;
import lombok.*;
import luis122448.platformtraining.application.persistence.entity.QuestionEntity;
import luis122448.platformtraining.application.persistence.entity.enums.TypeExamEnum;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExamModel {
    private Long idCompany;
    private Long idExam;
    private Long idCourse;
    private String courseTitle;
    private String courseUrlIcon;
    private TypeExamEnum typeExam;
    private String title;
    private String description;
    private String markdownContent;
    private Integer numberQuestions;
    private Integer time;
    private Integer minScore;
    private Integer maxAttempts;
}
