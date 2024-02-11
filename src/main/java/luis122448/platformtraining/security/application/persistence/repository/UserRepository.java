package luis122448.platformtraining.security.application.persistence.repository;

import luis122448.platformtraining.security.application.persistence.entity.UserEntity;
import luis122448.platformtraining.util.exception.GenericObjectServiceException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByIdCompanyAndUsername(@Param("idCompany") Long idCompany,
                                                    @Param("username") String username) throws GenericObjectServiceException;

}
