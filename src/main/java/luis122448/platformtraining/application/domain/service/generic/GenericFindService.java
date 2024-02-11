package luis122448.platformtraining.application.domain.service.generic;

import luis122448.platformtraining.util.exception.GenericListServiceException;
import luis122448.platformtraining.util.exception.GenericObjectServiceException;
import luis122448.platformtraining.util.object.api.ApiResponseList;
import luis122448.platformtraining.util.object.api.ApiResponseObject;

public interface GenericFindService<T, P> {

    ApiResponseList<T> findByAll(T t) throws GenericListServiceException;
    ApiResponseList<T> findByLike(T t) throws GenericListServiceException;
    ApiResponseObject<T> findById(P p) throws GenericObjectServiceException;
}
