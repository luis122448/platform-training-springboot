package luis122448.platformtraining.security.application.domain.implement;

import luis122448.platformtraining.security.application.domain.model.UserModel;
import luis122448.platformtraining.security.application.domain.service.LoginService;
import luis122448.platformtraining.security.application.persistence.entity.CompanyEntity;
import luis122448.platformtraining.security.application.persistence.entity.UserEntity;
import luis122448.platformtraining.security.application.persistence.entity.key.UserKey;
import luis122448.platformtraining.security.application.persistence.repository.CompanyRepository;
import luis122448.platformtraining.security.application.persistence.repository.UserRepository;
import luis122448.platformtraining.util.exception.GenericAuthServiceException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {
    @Qualifier("UserSecurityRepository")
    private final UserRepository usuarioRepository;
    @Qualifier("CompanyInfoSecurityRepository")
    private final CompanyRepository companyRepository;
    public LoginServiceImpl(UserRepository usuarioRepository, CompanyRepository companyRepository) {
        this.usuarioRepository = usuarioRepository;
        this.companyRepository = companyRepository;
    }

    @Override
    public UserModel login(String company, String username) throws GenericAuthServiceException {
        CompanyEntity companyEntity = this.companyRepository.findByCompany(company).orElseThrow(
                () -> new GenericAuthServiceException("COMPANY SSN INVALID!"));
        UserEntity userEntity = this.usuarioRepository.findByCompanyAndUsername(company, username).orElseThrow(
                () -> new GenericAuthServiceException("USERNAME NOT EXISTS!"));
        return UserModel.builder()
                .idCompany(companyEntity.getIdCompany())
                .idUser(userEntity.getIdUser())
                .company(companyEntity.getCompany())
                .username(userEntity.getUsername())
                .encode(userEntity.getEncode())
                .nivel(userEntity.getNivel())
                .secretCode(userEntity.getSecretCode())
                .role(userEntity.getRole())
                .registdate(userEntity.getRegistdate())
                .expiredate(userEntity.getExpiredate())
                .status("Y")
                .build();
    }
}
