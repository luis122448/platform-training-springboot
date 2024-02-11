package luis122448.platformtraining.application.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import luis122448.platformtraining.security.authentication.auditing.AuditingEntity;
import luis122448.platformtraining.application.persistence.entity.enums.TypeCommentEnum;
import luis122448.platformtraining.application.persistence.entity.key.CommentKey;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@IdClass(CommentKey.class)
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(schema = "public",name = "TBL_COMMENT")
public class CommentEntity extends AuditingEntity {

    @Id
    @Column(name = "ID_COMPANY", nullable = false)
    private Long idCompany;
    @Id
    @Column(name = "ID_COMMENT", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_TBL_COMMENT_ID_COMMENT")
    private Long idComment;
    @Column(name = "ID_CLASS", nullable = false)
    private Long idClass;
    @Column(name = "TYPE_COMMENT", nullable = false)
    @Enumerated(EnumType.STRING)
    private TypeCommentEnum typeComment;
    private Long registerUser;
    private LocalDate registerDate;
    private Integer likeComment;
    private Integer dislikeComment;
    private String markdownContent;
    @Column(name = "ID_COMMENT_REF")
    private Long idCommentRef;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "ID_COMPANY", referencedColumnName = "ID_COMPANY", insertable = false, updatable = false),
            @JoinColumn(name = "ID_CLASS", referencedColumnName = "ID_CLASS", insertable = false, updatable = false)
    })
    @JsonIgnore
    private ClassEntity classEntity;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "ID_COMPANY", referencedColumnName = "ID_COMPANY", insertable = false, updatable = false),
            @JoinColumn(name = "ID_COMMENT_REF", referencedColumnName = "ID_COMMENT", insertable = false, updatable = false)
    })
    @OrderBy("likeComment DESC")
    private List<CommentEntity> commentEntityList;

}
