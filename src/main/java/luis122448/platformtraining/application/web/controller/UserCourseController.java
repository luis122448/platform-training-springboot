package luis122448.platformtraining.application.web.controller;

import static luis122448.platformtraining.application.web.constant.APIConstant.*;

import io.swagger.v3.oas.annotations.Operation;
import luis122448.platformtraining.application.domain.model.UserCourseModel;
import luis122448.platformtraining.application.domain.service.service.UserCourseService;
import luis122448.platformtraining.util.exception.GenericListServiceException;
import luis122448.platformtraining.util.exception.GenericObjectServiceException;
import luis122448.platformtraining.util.object.api.ApiResponseList;
import luis122448.platformtraining.util.object.api.ApiResponseObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(API_USER_COURSE)
public class UserCourseController {

    private final UserCourseService userCourseService;

    public UserCourseController(UserCourseService userCourseService) {
        this.userCourseService = userCourseService;
    }

    @Operation(tags = {TAG_USER_APP})
    @GetMapping("/by-course")
    public ResponseEntity<?> findByCourse(@RequestParam(name = "idCourse", defaultValue = "0") Long idCourse) throws GenericObjectServiceException, GenericListServiceException {
        ApiResponseObject<UserCourseModel> responseObject = this.userCourseService.findByCourse(idCourse);
        return ResponseEntity.ok(responseObject);
    }

    @Operation(tags = {TAG_USER_APP})
    @GetMapping("/by-module")
    public ResponseEntity<?> findByModule(@RequestParam(name = "idModule", defaultValue = "0") Long idModule) throws GenericListServiceException{
        ApiResponseList<UserCourseModel> responseList = this.userCourseService.findByModule(idModule);
        return ResponseEntity.ok(responseList);
    }

}