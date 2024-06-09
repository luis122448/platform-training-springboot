package luis122448.platformtraining.application.domain.service.service;

import luis122448.platformtraining.application.domain.model.UserCourseModel;
import luis122448.platformtraining.util.exception.GenericListServiceException;
import luis122448.platformtraining.util.exception.GenericObjectServiceException;
import luis122448.platformtraining.util.object.api.ApiResponseList;
import luis122448.platformtraining.util.object.api.ApiResponseObject;

public interface UserCourseService {

    ApiResponseList<UserCourseModel> findByModule(Long idModule) throws GenericListServiceException;
    ApiResponseObject<UserCourseModel> findByCourse(Long idCourse) throws GenericObjectServiceException, GenericListServiceException;
    ApiResponseObject<UserCourseModel> save(Long idCourse, Long idUser) throws GenericObjectServiceException;
    ApiResponseList<UserCourseModel> generateAll(Long idModule, Long idUser) throws GenericListServiceException;
    ApiResponseObject<UserCourseModel> update(Long idCourse, UserCourseModel userCourseModel) throws GenericObjectServiceException;

}
