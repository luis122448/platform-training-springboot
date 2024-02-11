package luis122448.platformtraining.application.persistence.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import luis122448.platformtraining.application.persistence.entity.enums.TypeCommentEnum;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {

    private Long idCompany;
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

}
