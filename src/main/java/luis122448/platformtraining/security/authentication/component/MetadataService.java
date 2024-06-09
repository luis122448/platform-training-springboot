package luis122448.platformtraining.security.authentication.component;

import luis122448.platformtraining.application.domain.service.service.ModuleService;
import luis122448.platformtraining.security.application.domain.componnet.SecurityContextInitializer;
import luis122448.platformtraining.security.application.domain.model.MetadataModel;
import luis122448.platformtraining.security.application.domain.service.UserService;
import luis122448.platformtraining.util.exception.GenericAuthServiceException;
import luis122448.platformtraining.util.exception.GenericListServiceException;
import luis122448.platformtraining.util.exception.GenericObjectServiceException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MetadataService {
    private final UserService userService;
    private final ModuleService moduleService;
    public MetadataService(ModuleService moduleService, SecurityContextInitializer securityContextInitializer, UserService userService) {
        this.userService = userService;
        this.moduleService = moduleService;
    }
    public Optional<MetadataModel> initMetadata() throws GenericAuthServiceException {
        try {
            MetadataModel metadataModel = new MetadataModel();
            metadataModel.setUser(this.userService.findByUser().getObject().orElseThrow(
                    () -> new GenericObjectServiceException("USER NOT FOUND")
            ));
            metadataModel.setModules(this.moduleService.findByUser().getList().orElseThrow(
                    () -> new GenericListServiceException("MODULES NOT FOUND")
            ));
            return Optional.of(metadataModel);
        } catch (GenericObjectServiceException | GenericListServiceException e) {
            throw new GenericAuthServiceException(e.getMessage());
        }
    }
}
