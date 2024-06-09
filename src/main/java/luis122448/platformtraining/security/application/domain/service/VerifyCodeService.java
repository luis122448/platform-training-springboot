package luis122448.platformtraining.security.application.domain.service;

import luis122448.platformtraining.security.application.domain.model.VerifyCodeModel;
import luis122448.platformtraining.security.application.persistence.entity.UserEntity;
import luis122448.platformtraining.util.exception.GenericAuthServiceException;

import java.util.Optional;

public interface VerifyCodeService {
    Optional<VerifyCodeModel> createCode(Long idCompany, String company, String coduser) throws GenericAuthServiceException;
    Optional<UserEntity> verifyCode(String company, String coduser, String code) throws GenericAuthServiceException;
}
