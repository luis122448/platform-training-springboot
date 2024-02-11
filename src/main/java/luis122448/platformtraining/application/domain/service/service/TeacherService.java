package luis122448.platformtraining.application.domain.service.service;

import luis122448.platformtraining.application.domain.model.TeacherModel;
import luis122448.platformtraining.application.domain.service.generic.GenericCrudService;
import luis122448.platformtraining.application.persistence.entity.key.TeacherKey;
import luis122448.platformtraining.util.exception.GenericObjectServiceException;
import luis122448.platformtraining.util.object.api.ApiResponseObject;

public interface TeacherService extends GenericCrudService<TeacherModel, TeacherKey> {

    ApiResponseObject<TeacherModel> findByIdTeacher(Long idTeacher) throws GenericObjectServiceException;
}
