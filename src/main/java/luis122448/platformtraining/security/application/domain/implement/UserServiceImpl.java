package luis122448.platformtraining.security.application.domain.implement;

import luis122448.platformtraining.security.application.domain.componnet.SecurityContextInitializer;
import luis122448.platformtraining.security.application.domain.model.UserModel;
import luis122448.platformtraining.security.application.domain.service.UserService;
import luis122448.platformtraining.security.application.persistence.mapper.UserMapper;
import luis122448.platformtraining.security.application.persistence.repository.UserRepository;
import luis122448.platformtraining.util.exception.GenericAuthServiceException;
import luis122448.platformtraining.util.exception.GenericObjectServiceException;
import luis122448.platformtraining.util.object.api.ApiResponseObject;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final SecurityContextInitializer securityContextInitializer;
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, SecurityContextInitializer securityContextInitializer) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.securityContextInitializer = securityContextInitializer;
    }
    @Override
    public ApiResponseObject<UserModel> findByUser() throws GenericObjectServiceException, GenericAuthServiceException {
        Long idCompany = securityContextInitializer.getIdCompany();
        Long idUser = securityContextInitializer.getIdUser();
        UserModel userModel =  this.userMapper.toUserModel(this.userRepository.findByIdCompanyAndIdUser(idCompany, idUser)
                .orElseThrow(() -> new GenericObjectServiceException("USER NOT FOUND")));
        return new ApiResponseObject<>(Optional.of(userModel));
    }
}
