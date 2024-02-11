package luis122448.platformtraining.application.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import luis122448.platformtraining.application.domain.model.UserClassModel;
import luis122448.platformtraining.application.domain.service.service.UserClassService;
import luis122448.platformtraining.util.exception.GenericListServiceException;
import luis122448.platformtraining.util.exception.GenericObjectServiceException;
import luis122448.platformtraining.util.object.api.ApiResponseList;
import luis122448.platformtraining.util.object.api.ApiResponseObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static luis122448.platformtraining.application.web.constant.APIConstant.*;

@RestController
@RequestMapping(API_USER_CLASS)
public class UserClassController {

    private final UserClassService userClassService;

    public UserClassController(UserClassService userClassService) {
        this.userClassService = userClassService;
    }

    @Operation(tags = {TAG_USER_APP})
    @GetMapping("/by-class")
    public ResponseEntity<?> findByIdClass(@RequestParam(value = "idClass") Long idClass) throws GenericObjectServiceException, GenericListServiceException{
        ApiResponseObject<UserClassModel> responseObject =  this.userClassService.findByClass(idClass);
        return ResponseEntity.ok(responseObject);
    }

    @Operation(tags = {TAG_USER_APP})
    @GetMapping("/by-course")
    public ResponseEntity<?> findByIdCourse(@RequestParam(value = "idCourse") Long idCourse) throws GenericListServiceException{
        ApiResponseList<UserClassModel> responseObject =  this.userClassService.findByCourse(idCourse);
        return ResponseEntity.ok(responseObject);
    }

    @Operation(tags = {TAG_ADMINISTRATOR_APP})
    @PostMapping("/by-course")
    public ResponseEntity<?> generateByCourse(@RequestParam(value = "idCourse") Long idCourse,
                                              @RequestParam(value = "idUser") Long idUser) throws GenericListServiceException {
        ApiResponseList<UserClassModel> responseObject = this.userClassService.generateByCourse(idCourse,idUser);
        return ResponseEntity.ok(responseObject);
    }

    @Operation(tags = {TAG_USER_APP})
    @PutMapping()
    public ResponseEntity<?> update(@RequestParam(value = "idClass") Long idClass,
                                    @RequestBody UserClassModel userClassModel) throws GenericObjectServiceException {
        ApiResponseObject<UserClassModel> responseObject = this.userClassService.update(idClass, userClassModel);
        return ResponseEntity.ok(responseObject);
    }

}
