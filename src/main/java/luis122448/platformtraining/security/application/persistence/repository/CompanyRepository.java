package luis122448.platformtraining.security.application.persistence.repository;

import luis122448.platformtraining.security.application.persistence.entity.CompanyEntity;
import luis122448.platformtraining.util.exception.GenericAuthServiceException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("CompanyInfoSecurityRepository")
public interface CompanyRepository extends JpaRepository<CompanyEntity, Long> {

    Optional<CompanyEntity> findByCompany(@Param("company") String company) throws GenericAuthServiceException;
}
