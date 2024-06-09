package luis122448.platformtraining.application.domain.service.service;

import luis122448.platformtraining.application.domain.model.ExamModel;
import luis122448.platformtraining.util.exception.GenericObjectServiceException;
import luis122448.platformtraining.util.object.api.ApiResponseObject;

public interface ExamService {
    ApiResponseObject<ExamModel> findByCourse(Long idCourse) throws GenericObjectServiceException;
}
