package luis122448.platformtraining.application.domain.service.generic;

import luis122448.platformtraining.util.exception.GenericListServiceException;
import luis122448.platformtraining.util.exception.GenericObjectServiceException;
import luis122448.platformtraining.util.object.api.ApiResponseList;
import luis122448.platformtraining.util.object.api.ApiResponseObject;

public interface GenericFindByUserService<T, P> {

    ApiResponseList<T> findByGeneral(P p) throws GenericListServiceException;
    ApiResponseObject<T> findByDetail(P p) throws GenericObjectServiceException;
}
