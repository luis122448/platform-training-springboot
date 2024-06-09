package luis122448.platformtraining.security.application.persistence.repository;

import luis122448.platformtraining.security.application.persistence.entity.UserEntity;
import luis122448.platformtraining.util.exception.GenericAuthServiceException;
import luis122448.platformtraining.util.exception.GenericObjectServiceException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("UserSecurityRepository")
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query(value = "SELECT us FROM UserEntity us" +
            " INNER JOIN CompanyEntity ce ON ce.idCompany = us.idCompany" +
            " WHERE ce.company = :company AND us.username = :username")
    Optional<UserEntity> findByCompanyAndUsername(@Param("company") String company,
                                                    @Param("username") String username) throws GenericAuthServiceException;

    Optional<UserEntity> findByIdCompanyAndUsername(@Param("idCompany") Long idCompany,
                                                    @Param("username") String username) throws GenericAuthServiceException;

    Optional<UserEntity> findByIdCompanyAndIdUser(@Param("idCompany") Long idCompany,
                                                    @Param("idUser") Long idUser) throws GenericAuthServiceException;

    @Modifying
    @Query(value = "UPDATE TBL_USER SET secret_code=:secret_code WHERE id_company = :idCompany AND username = :username", nativeQuery = true)
    void updateVerifyCode(@Param("idCompany") Long idCompany, @Param("username") String coduser , @Param("secret_code") String secret_code);

}
