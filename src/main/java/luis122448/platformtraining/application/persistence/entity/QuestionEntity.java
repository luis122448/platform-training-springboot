package luis122448.platformtraining.application.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import luis122448.platformtraining.application.persistence.entity.enums.TypeQuestionEnum;
import luis122448.platformtraining.application.persistence.entity.key.QuestionKey;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@IdClass(QuestionKey.class)
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(schema = "public",name = "TBL_QUESTION")
public class QuestionEntity {

    @Id
    @Column(name = "ID_COMPANY",nullable = false)
    private Long idCompany;
    @Id
    @Column(name = "ID_QUESTION",nullable = false)
    private Long idQuestion;
    @Column(name = "ID_EXAM",nullable = false)
    private Long idExam;
    @Column(name = "TYPE_QUESTION",nullable = false)
    private TypeQuestionEnum typeQuestion;
    @Column(nullable = false)
    private BigDecimal position;
    @Column(nullable = false, length = 250)
    private String question;
    @Column(nullable = false, length = 250)
    private String alternative1;
    @Column(nullable = false, length = 250)
    private String alternative2;
    @Column(nullable = false, length = 250)
    private String alternative3;
    @Column(nullable = false, length = 250)
    private String alternative4;
    @Column(nullable = false, length = 250)
    private String alternative5;
    @Column(nullable = false)
    private Integer idAnswer;
    @Column(nullable = false)
    private String answer;
    @Column(nullable = false)
    private Integer score;

}
