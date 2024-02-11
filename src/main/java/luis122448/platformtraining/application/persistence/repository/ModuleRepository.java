package luis122448.platformtraining.application.persistence.repository;

import luis122448.platformtraining.application.persistence.entity.ModuleEntity;
import luis122448.platformtraining.application.persistence.entity.key.ModuleKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModuleRepository extends JpaRepository<ModuleEntity, ModuleKey> {
}
