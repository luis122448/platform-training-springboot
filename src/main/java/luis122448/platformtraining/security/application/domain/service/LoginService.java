package luis122448.platformtraining.security.application.domain.service;

import luis122448.platformtraining.security.application.domain.model.UserModel;
import luis122448.platformtraining.util.exception.GenericAuthServiceException;

public interface LoginService {
    UserModel login(String company, String coduser) throws GenericAuthServiceException;
}
