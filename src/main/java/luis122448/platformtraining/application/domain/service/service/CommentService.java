package luis122448.platformtraining.application.domain.service.service;

import luis122448.platformtraining.application.domain.model.CommentModel;
import luis122448.platformtraining.util.exception.GenericListServiceException;
import luis122448.platformtraining.util.exception.GenericObjectServiceException;
import luis122448.platformtraining.util.object.api.ApiResponseList;
import luis122448.platformtraining.util.object.api.ApiResponseObject;

public interface CommentService {

    ApiResponseList<CommentModel> findByIdClass(Long idClass) throws GenericListServiceException;
    ApiResponseObject<CommentModel> save(Long idCommentRef, CommentModel commentModel) throws GenericObjectServiceException;
    ApiResponseObject<CommentModel> update(Long idComment, Integer likeComment, Integer dislikeComment) throws GenericObjectServiceException;
    ApiResponseObject<CommentModel> delete(Long idComment) throws GenericObjectServiceException;
}
