package luis122448.platformtraining.security.application.domain.implement;

import luis122448.platformtraining.security.application.domain.model.*;
import luis122448.platformtraining.security.application.domain.service.AuthService;
import luis122448.platformtraining.security.application.domain.service.VerifyCodeService;
import luis122448.platformtraining.security.application.persistence.entity.UserEntity;
import luis122448.platformtraining.security.application.persistence.repository.CompanyRepository;
import luis122448.platformtraining.security.application.persistence.repository.UserRepository;
import luis122448.platformtraining.security.authentication.component.JWTUtils;
import luis122448.platformtraining.security.authentication.component.MetadataService;
import luis122448.platformtraining.security.authentication.user.UserDetailsCustom;
import luis122448.platformtraining.security.authentication.user.UserDetailsServiceCustom;
import luis122448.platformtraining.util.exception.GenericAuthServiceException;
import luis122448.platformtraining.util.object.api.ApiResponseMetadata;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

import static java.util.Objects.isNull;
import static luis122448.platformtraining.security.authentication.constant.AUTHConstant.COMPANY;
import static luis122448.platformtraining.security.authentication.constant.AUTHConstant.USERNAME;

@Service
public class AuthServiceImpl implements AuthService {
	@Qualifier("UserSecurityRepository")
	private final UserRepository usuarioRepository;
	@Qualifier("CompanyInfoSecurityRepository")
	private final CompanyRepository companyRepository;
	private final UserDetailsServiceCustom userDetailsServiceCustom;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	private final MetadataService metadataService;
	private final VerifyCodeService verifyCodeService;
	private final JWTUtils jwtUtils;
	public AuthServiceImpl(UserRepository usuarioRepository, CompanyRepository companyRepository, UserDetailsServiceCustom userDetailsServiceCustom, BCryptPasswordEncoder bCryptPasswordEncoder, MetadataService metadataService, VerifyCodeService verifyCodeService, JWTUtils jwtUtils) {
		this.usuarioRepository = usuarioRepository;
		this.companyRepository = companyRepository;
		this.userDetailsServiceCustom = userDetailsServiceCustom;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		this.metadataService = metadataService;
		this.verifyCodeService = verifyCodeService;
		this.jwtUtils = jwtUtils;
	}

	@Override
	public ApiResponseMetadata<?,?> login(LoginModel loginModel) throws GenericAuthServiceException {
		UserDetailsCustom userDetailsCustom = this.userDetailsServiceCustom.loadUserByUsernameAndCompany(loginModel.getCompany(), loginModel.getUsername());
		if(isNull(userDetailsCustom)){
			throw new GenericAuthServiceException("USER OR COMPANY NOT EXISTS!");
		}
		if(!bCryptPasswordEncoder.matches(loginModel.getPassword(), userDetailsCustom.getPassword())){
			throw new GenericAuthServiceException("PASSWORD INCORRECT!");
		}
		if(userDetailsCustom.getNivel()==0){
			Optional<TokenModel> tokenModel = this.createToken(userDetailsCustom);
			Optional<MetadataModel> metadataModel = this.metadataService.initMetadata();
			return new ApiResponseMetadata<>(tokenModel, metadataModel);
		}
		Optional<VerifyCodeModel> verifyCodeModel = this.verifyCodeService.createCode(userDetailsCustom.getIdCompany(),userDetailsCustom.getCompany(),userDetailsCustom.getUsername());
		return new ApiResponseMetadata<>(verifyCodeModel,Optional.empty());
	}

	@Override
	public ApiResponseMetadata<?,?> verifyCode(VerifyCodeModel verifyCodeModel) throws GenericAuthServiceException {
		UserEntity userEntity = this.verifyCodeService.verifyCode(verifyCodeModel.getCompany(), verifyCodeModel.getUsername(), verifyCodeModel.getVerifyCode()).orElseThrow(
				() -> new GenericAuthServiceException("INVALID VERIFY CODE!")
		);
		UserDetailsCustom userDetailsCustom = this.userDetailsServiceCustom.loadUserByUsernameAndCompany(verifyCodeModel.getCompany(), verifyCodeModel.getUsername());
		Optional<TokenModel> tokenModel = this.createToken(userDetailsCustom);
		Optional<MetadataModel> metadataModel = this.metadataService.initMetadata();
		return new ApiResponseMetadata<>(tokenModel,metadataModel);
	}

	@Override
	public ApiResponseMetadata<?,?> refreshToken(RefreshTokenModel refreshTokenModel) throws GenericAuthServiceException {
		if(!this.jwtUtils.validateJwtToken(refreshTokenModel.getRefreshToken())){
			throw new GenericAuthServiceException("INVALID TOKEN!");
		}
		String token = this.jwtUtils.generateJwtFromTokenRefresh(refreshTokenModel.getRefreshToken());
		Map<String, Object> tokenData = this.jwtUtils.getDataJwtToken(refreshTokenModel.getRefreshToken());
		UserDetailsCustom userDetailsCustom = this.userDetailsServiceCustom.loadUserByUsernameAndCompany(tokenData.get(COMPANY).toString(), tokenData.get(USERNAME).toString());
		Optional<TokenModel> tokenModel = this.createToken(userDetailsCustom);
		Optional<MetadataModel> metadataModel = this.metadataService.initMetadata();
		return new ApiResponseMetadata<>(tokenModel,Optional.empty());
	}

	private Optional<TokenModel> createToken(UserDetailsCustom userDetailsCustom) throws GenericAuthServiceException {
		String token = this.jwtUtils.generateJwtToken(userDetailsCustom.getCompany(), userDetailsCustom.getUsername(), false);
		String refreshToken = this.jwtUtils.generateJwtToken(userDetailsCustom.getCompany(), userDetailsCustom.getUsername(), true);
		return Optional.of(new TokenModel(userDetailsCustom.getUsername(), userDetailsCustom.getRole(), token, refreshToken));
	}

}
