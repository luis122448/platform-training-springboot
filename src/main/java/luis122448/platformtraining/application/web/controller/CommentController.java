package luis122448.platformtraining.application.web.controller;

import luis122448.platformtraining.application.domain.model.CommentModel;
import luis122448.platformtraining.application.domain.service.service.CommentService;
import luis122448.platformtraining.util.exception.GenericObjectServiceException;
import luis122448.platformtraining.util.object.api.ApiResponseObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static luis122448.platformtraining.application.web.constant.APIConstant.*;

@RestController
@RequestMapping(API_COMMENT)
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("")
    public ResponseEntity<?> save(@RequestParam(name = "idCommentRef", defaultValue = "", required = false) Long idCommentRef, @RequestBody CommentModel commentModel) throws GenericObjectServiceException{
        ApiResponseObject<CommentModel> responseObject = this.commentService.save(idCommentRef, commentModel);
        return ResponseEntity.ok(responseObject);
    }

    @PutMapping("")
    public ResponseEntity<?> update(@RequestParam(name = "idComment") Long idComment,
                                  @RequestParam(name = "likeComment", defaultValue = "0") Integer likeComment,
                                  @RequestParam(name = "dislikeComment", defaultValue = "0") Integer dislikeComment) throws GenericObjectServiceException{
        ApiResponseObject<CommentModel> responseObject = this.commentService.update(idComment,likeComment,dislikeComment);
        return ResponseEntity.ok(responseObject);
    }

    @DeleteMapping("")
    public ResponseEntity<?> delete(@RequestParam(name = "idComment") Long idComment) throws GenericObjectServiceException{
        ApiResponseObject<CommentModel> responseObject = this.commentService.delete(idComment);
        return ResponseEntity.ok(responseObject);
    }

}
