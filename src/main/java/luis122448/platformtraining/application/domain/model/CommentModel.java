package luis122448.platformtraining.application.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import luis122448.platformtraining.application.persistence.entity.enums.TypeCommentEnum;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CommentModel {

    private Long idComment;
    private Long idClass;
    private TypeCommentEnum typeComment;
    private Long registerUser;
    private String username;
    private String urlPhoto;
    private LocalDate registerDate;
    private Integer likeComment;
    private Integer dislikeComment;
    private String markdownContent;
    private List<CommentModel> commentModelList;

}
