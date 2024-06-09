package luis122448.platformtraining.security.application.domain.service;

import luis122448.platformtraining.security.application.domain.model.UserModel;
import luis122448.platformtraining.security.application.persistence.entity.UserEntity;
import luis122448.platformtraining.util.exception.GenericAuthServiceException;
import luis122448.platformtraining.util.exception.GenericObjectServiceException;
import luis122448.platformtraining.util.object.api.ApiResponseObject;

import java.util.Optional;

public interface UserService {

    ApiResponseObject<UserModel> findByUser() throws GenericObjectServiceException, GenericAuthServiceException;
}
