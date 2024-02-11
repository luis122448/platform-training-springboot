package luis122448.platformtraining.application.persistence.repository;

import luis122448.platformtraining.application.persistence.dto.CommentDTO;
import luis122448.platformtraining.application.persistence.entity.CommentEntity;
import luis122448.platformtraining.application.persistence.entity.key.CommentKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<CommentEntity, CommentKey>{

    @Query("SELECT new luis122448.platformtraining.application.persistence.dto.CommentDTO(" +
            "c.idCompany,c.idComment,c.idClass,c.typeComment,c.registerUser,u.username,u.urlPhoto,c.registerDate,c.likeComment,c.dislikeComment,c.markdownContent) " +
            "FROM CommentEntity c " +
            "LEFT OUTER JOIN UserEntity u ON u.idCompany = c.idCompany AND u.idUser = c.registerUser " +
            "WHERE c.idCompany = :idCompany AND c.idClass = :idClass AND c.idCommentRef IS NULL AND c.status = 'Y'")
    List<CommentDTO> findByIdClass(@Param("idCompany") Long idCompany, @Param("idClass") Long idClass);

    @Query("SELECT new luis122448.platformtraining.application.persistence.dto.CommentDTO(" +
            "c.idCompany,c.idComment,c.idClass,c.typeComment,c.registerUser,u.username,u.urlPhoto,c.registerDate,c.likeComment,c.dislikeComment,c.markdownContent) " +
            "FROM CommentEntity c " +
            "LEFT OUTER JOIN UserEntity u ON u.idCompany = c.idCompany AND u.idUser = c.registerUser " +
            "WHERE c.idCompany = :idCompany AND c.idCommentRef = :idCommentRef AND c.status = 'Y'")
    List<CommentDTO> findByIdCommentRef(@Param("idCompany") Long idCompany, @Param("idCommentRef") Long idCommentRef);

}
