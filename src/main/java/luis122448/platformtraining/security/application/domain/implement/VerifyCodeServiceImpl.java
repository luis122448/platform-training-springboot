package luis122448.platformtraining.security.application.domain.implement;

import lombok.extern.slf4j.Slf4j;
import luis122448.platformtraining.security.authentication.component.TOTPUtil;
import luis122448.platformtraining.security.application.domain.model.VerifyCodeModel;
import luis122448.platformtraining.security.application.domain.service.VerifyCodeService;
import luis122448.platformtraining.security.application.persistence.entity.UserEntity;
import luis122448.platformtraining.security.application.persistence.repository.UserRepository;
import luis122448.platformtraining.util.exception.GenericAuthServiceException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
public class VerifyCodeServiceImpl implements VerifyCodeService {
	private final UserRepository userRepository;
	private final TOTPUtil tOTPUtil;
	public VerifyCodeServiceImpl(UserRepository userRepository, TOTPUtil tOTPUtil) {
		this.userRepository = userRepository;
		this.tOTPUtil = tOTPUtil;
	}

	@Transactional
	public Optional<VerifyCodeModel> createCode(Long idCompany, String company, String coduser) throws GenericAuthServiceException {
		String code = this.tOTPUtil.generateCode();
		this.userRepository.updateVerifyCode(idCompany, coduser, code);
		return Optional.of(new VerifyCodeModel(company, coduser, code));
	}
	
	public Optional<UserEntity> verifyCode(String company, String coduser, String secretCode) throws GenericAuthServiceException {
		UserEntity userEntity = this.userRepository.findByCompanyAndUsername(company, coduser).orElseThrow(() -> new GenericAuthServiceException("USER OR COMPANY NOT EXISTS!"));
		if (userEntity.getSecretCode().equals(secretCode) && this.tOTPUtil.verifyCode(secretCode)) {
			return Optional.of(userEntity);
		} else {
			throw new GenericAuthServiceException("INVALID VERIFY CODE!");
		}
	}
}

