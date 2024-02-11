package luis122448.platformtraining.security.application.persistence.repository;

import luis122448.platformtraining.security.application.persistence.entity.CompanyEntity;
import luis122448.platformtraining.util.exception.GenericObjectServiceException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CompanyRepository extends JpaRepository<CompanyEntity, Long> {

    Optional<CompanyEntity> findBySsn(@Param("ssn") String ssn) throws GenericObjectServiceException;
}
