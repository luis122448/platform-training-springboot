package luis122448.platformtraining.application.web.controller;

import luis122448.platformtraining.application.domain.model.CommentModel;
import luis122448.platformtraining.application.domain.service.service.CommentService;
import luis122448.platformtraining.util.exception.GenericListServiceException;
import luis122448.platformtraining.util.object.api.ApiResponseList;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static luis122448.platformtraining.application.web.constant.APIConstant.API_CLASS_COMMENT;

@RestController
@RequestMapping(API_CLASS_COMMENT)
public class ClassCommentController {

    private final CommentService commentService;

    public ClassCommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/by-class")
    public ResponseEntity<?> findByIdClass(@RequestParam(name="idClass", defaultValue = "0") Long idClass) throws GenericListServiceException{
        ApiResponseList<CommentModel> responseObject = this.commentService.findByIdClass(idClass);
        return ResponseEntity.ok(responseObject);
    }

}
