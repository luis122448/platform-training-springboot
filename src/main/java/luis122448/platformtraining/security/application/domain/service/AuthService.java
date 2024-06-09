package luis122448.platformtraining.security.application.domain.service;

import luis122448.platformtraining.security.application.domain.model.LoginModel;
import luis122448.platformtraining.security.application.domain.model.RefreshTokenModel;
import luis122448.platformtraining.security.application.domain.model.VerifyCodeModel;
import luis122448.platformtraining.util.object.api.ApiResponseAuth;
import luis122448.platformtraining.util.exception.GenericAuthServiceException;
import luis122448.platformtraining.util.object.api.ApiResponseMetadata;

public interface AuthService {
	ApiResponseMetadata<?,?> login(LoginModel loginModel) throws GenericAuthServiceException;

	ApiResponseMetadata<?,?> verifyCode(VerifyCodeModel verifyCodeModel) throws GenericAuthServiceException;

	ApiResponseMetadata<?,?> refreshToken(RefreshTokenModel refreshTokenModel) throws GenericAuthServiceException;
}
