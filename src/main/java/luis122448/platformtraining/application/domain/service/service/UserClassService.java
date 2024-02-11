package luis122448.platformtraining.application.domain.service.service;

import luis122448.platformtraining.application.domain.model.UserClassModel;
import luis122448.platformtraining.util.exception.GenericListServiceException;
import luis122448.platformtraining.util.exception.GenericObjectServiceException;
import luis122448.platformtraining.util.object.api.ApiResponseList;
import luis122448.platformtraining.util.object.api.ApiResponseObject;

public interface UserClassService{
    ApiResponseList<UserClassModel> findByCourse(Long idCourse) throws GenericListServiceException;
    ApiResponseObject<UserClassModel> findByClass(Long idClass) throws GenericObjectServiceException, GenericListServiceException;
    ApiResponseObject<UserClassModel> save(Long idClass, Long idUser) throws GenericObjectServiceException;
    ApiResponseList<UserClassModel> generateByCourse(Long idCourse, Long idUser) throws GenericListServiceException;
    ApiResponseObject<UserClassModel> update(Long idClass, UserClassModel userClassModel) throws GenericObjectServiceException;
}
