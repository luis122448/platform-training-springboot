package luis122448.platformtraining.application.domain.service.implement;

import luis122448.platformtraining.application.domain.model.CommentModel;
import luis122448.platformtraining.application.domain.service.service.CommentService;
import luis122448.platformtraining.application.persistence.entity.CommentEntity;
import luis122448.platformtraining.application.persistence.entity.enums.TypeCommentEnum;
import luis122448.platformtraining.application.persistence.entity.key.CommentKey;
import luis122448.platformtraining.application.persistence.mapper.CommentMapper;
import luis122448.platformtraining.application.persistence.repository.CommentRepository;
import luis122448.platformtraining.security.authentication.user.UserDetailsCustom;
import luis122448.platformtraining.util.exception.GenericListServiceException;
import luis122448.platformtraining.util.exception.GenericObjectServiceException;
import luis122448.platformtraining.util.object.api.ApiResponseList;
import luis122448.platformtraining.util.object.api.ApiResponseObject;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
    private final Long idCompany;
    private final Long idUser;
    public CommentServiceImpl(CommentRepository commentRepository, CommentMapper commentMapper) {
        this.commentRepository = commentRepository;
        this.commentMapper = commentMapper;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetailsCustom userDetailsCustom) {
            this.idCompany = userDetailsCustom.getIdCompany();
            this.idUser = userDetailsCustom.getIdUser();
        } else {
            this.idCompany = 0L;
            this.idUser = 0L;
        }
    }

    @Override
    public ApiResponseList<CommentModel> findByIdClass(Long idClass) throws GenericListServiceException {
        List<CommentModel> commentModelList = this.commentMapper.toCommentModelList(this.commentRepository.findByIdClass(this.idCompany,idClass));
        commentModelList.forEach(commentModel -> {
            commentModel.setCommentModelList(this.commentMapper.toCommentModelList(this.commentRepository.findByIdCommentRef(this.idCompany,commentModel.getIdComment())));
        });
        return new ApiResponseList<CommentModel>(Optional.of(commentModelList));
    }

    @Override
    public ApiResponseObject<CommentModel> update(Long idComment, Integer likeComment, Integer dislikeComment) throws GenericObjectServiceException{
        CommentEntity commentEntity = this.commentRepository.findById(new CommentKey(this.idCompany, idComment)).orElseThrow(
                () -> new GenericObjectServiceException("Comment not found")
        );
        commentEntity.setLikeComment(commentEntity.getLikeComment() + likeComment);
        commentEntity.setDislikeComment(commentEntity.getDislikeComment() + dislikeComment);
        this.commentRepository.save(commentEntity);
        return new ApiResponseObject<CommentModel>(Optional.empty());
    }

    @Override
    public ApiResponseObject<CommentModel> save(Long idCommentRef, CommentModel commentModel) throws GenericObjectServiceException {
        CommentEntity commentEntity = CommentEntity.builder()
                .idCompany(this.idCompany)
                .idComment(null)
                .idClass(commentModel.getIdClass())
                .typeComment(commentModel.getTypeComment())
                .registerUser(this.idUser)
                .registerDate(LocalDate.now())
                .likeComment(0)
                .dislikeComment(0)
                .markdownContent(commentModel.getMarkdownContent())
                .idCommentRef(idCommentRef == 0L ? null : idCommentRef)
                .build();
        commentEntity.setStatus("Y");
        this.commentRepository.save(commentEntity);
        return new ApiResponseObject<CommentModel>(Optional.empty());
    }

    @Override
    public ApiResponseObject<CommentModel> delete(Long idComment) throws GenericObjectServiceException {
        this.commentRepository.deleteById(new CommentKey(this.idCompany,idComment));
        return new ApiResponseObject<CommentModel>(Optional.empty());
    }
}
