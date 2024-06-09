package luis122448.platformtraining.application.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import luis122448.platformtraining.application.persistence.entity.enums.TypeExamEnum;
import luis122448.platformtraining.application.persistence.entity.key.ExamKey;
import luis122448.platformtraining.security.authentication.auditing.AuditingEntity;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@IdClass(ExamKey.class)
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(schema = "public",name = "TBL_EXAM")
public class ExamEntity extends AuditingEntity {

        @Id
        @Column(name = "ID_COMPANY",nullable = false)
        private Long idCompany;
        @Id
        @Column(name = "ID_EXAM",nullable = false)
        private Long idExam;
        @Column(name = "ID_COURSE",nullable = false)
        private Long idCourse;
        @Column(name = "TYPE_EXAM", nullable = false)
        @Enumerated(EnumType.STRING)
        private TypeExamEnum typeExam;
        @Column(nullable = false, length = 100)
        private String title;
        @Column(nullable = false, length = 250)
        private String description;
        @Column(name = "MARKDOWN_CONTENT", nullable = true, length = 4000)
        private String markdownContent;
        @Column(name = "NUMBER_QUESTIONS", nullable = false)
        private Integer numberQuestions;
        @Column(nullable = false)
        private Integer time;
        @Column(nullable = false)
        private Integer minScore;
        @Column(name = "MAX_ATTEMPTS", nullable = false)
        private Integer maxAttempts;
        @OneToMany(fetch = FetchType.LAZY)
        @JoinColumns({
                @JoinColumn(name = "ID_COMPANY", referencedColumnName = "ID_COMPANY", insertable = false, updatable = false),
                @JoinColumn(name = "ID_EXAM", referencedColumnName = "ID_EXAM", insertable = false, updatable = false)
        })
        @OrderBy("position ASC")
        private List<QuestionEntity> questionEntityList;

}
