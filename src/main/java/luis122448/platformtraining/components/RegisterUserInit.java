package luis122448.platformtraining.components;

import luis122448.platformtraining.application.domain.model.UserCourseModel;
import luis122448.platformtraining.application.domain.service.service.UserClassService;
import luis122448.platformtraining.application.domain.service.service.UserCourseService;
import luis122448.platformtraining.util.exception.GenericListServiceException;
import luis122448.platformtraining.util.exception.GenericObjectServiceException;
import luis122448.platformtraining.util.object.api.ApiResponseObject;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class RegisterUserInit {

    private final UserCourseService userCourseService;
    private final UserClassService userClassService;

    public RegisterUserInit(UserCourseService userCourseService, UserClassService userClassService) {
        this.userCourseService = userCourseService;
        this.userClassService = userClassService;
    }

    public ApiResponseObject<?> RegisterUserByModule(Long idModule, Long idUser) throws GenericObjectServiceException, GenericListServiceException {
        List<Long> idCurseList = this.userCourseService.generateAll(idModule,idUser).getList().orElseThrow()
                .stream()
                .map(UserCourseModel::getIdCourse)
                .toList();
        idCurseList.forEach(idCourse ->{
            try {
                this.userClassService.generateByCourse(idCourse,idUser);
            } catch (GenericListServiceException e) {
                throw new RuntimeException(e);
            }
        });
        return new ApiResponseObject<>(Optional.empty());
    }

}
