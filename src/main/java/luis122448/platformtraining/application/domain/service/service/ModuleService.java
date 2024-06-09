package luis122448.platformtraining.application.domain.service.service;

import luis122448.platformtraining.application.domain.model.ModuleModel;
import luis122448.platformtraining.util.exception.GenericListServiceException;
import luis122448.platformtraining.util.object.api.ApiResponseList;

public interface ModuleService {
    ApiResponseList<ModuleModel> findByUser() throws GenericListServiceException;
}
