package luis122448.platformtraining.security.application.domain.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import luis122448.platformtraining.security.application.domain.model.UserModel;
import luis122448.platformtraining.security.application.persistence.entity.CompanyEntity;
import luis122448.platformtraining.security.application.persistence.entity.UserEntity;
import luis122448.platformtraining.security.application.persistence.repository.CompanyRepository;
import luis122448.platformtraining.security.application.persistence.repository.UserRepository;
import luis122448.platformtraining.util.exception.GenericObjectServiceException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;

    public UserModel login(String company, String username) throws Exception, GenericObjectServiceException{
        CompanyEntity companyEntity = this.companyRepository.findBySsn(company).orElseThrow(() -> new GenericObjectServiceException("COMPANY SSN INVALID!"));
        UserEntity userEntity = this.userRepository.findByIdCompanyAndUsername(companyEntity.getIdCompany(),username).orElseThrow(() -> new GenericObjectServiceException("USERNAME NOT EXISTS!"));
        return UserModel.builder()
                .idCompany(companyEntity.getIdCompany())
                .idUser(userEntity.getIdUser())
                .company(companyEntity.getSsn())
                .username(userEntity.getUsername())
                .password(userEntity.getPassword())
                .isVerifyCode(userEntity.isVerifyCode())
                .secretCode(userEntity.getSecretCode())
                .role(userEntity.getRole())
                .expirationUsername(userEntity.getExpirationUsername())
                .expirationPassword(userEntity.getExpirationPassword())
                .status("S")
                .build();
    }
}
