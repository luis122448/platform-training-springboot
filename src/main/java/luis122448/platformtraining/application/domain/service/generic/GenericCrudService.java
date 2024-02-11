package luis122448.platformtraining.application.domain.service.generic;

import luis122448.platformtraining.util.exception.GenericListServiceException;
import luis122448.platformtraining.util.exception.GenericObjectServiceException;
import luis122448.platformtraining.util.object.api.ApiResponseList;
import luis122448.platformtraining.util.object.api.ApiResponseObject;

public interface GenericCrudService<T, P> {

    ApiResponseObject<T> save(T t) throws GenericObjectServiceException;
    ApiResponseObject<T> update(T t) throws GenericObjectServiceException;
    ApiResponseObject<T> delete(P p) throws GenericObjectServiceException;

}
