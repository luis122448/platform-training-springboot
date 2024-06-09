package luis122448.platformtraining.application.domain.service.implement;

import luis122448.platformtraining.application.domain.model.ModuleModel;
import luis122448.platformtraining.application.domain.service.service.ModuleService;
import luis122448.platformtraining.application.persistence.mapper.ModuleMapper;
import luis122448.platformtraining.application.persistence.repository.ModuleRepository;
import luis122448.platformtraining.security.application.domain.componnet.SecurityContextInitializer;
import luis122448.platformtraining.util.exception.GenericListServiceException;
import luis122448.platformtraining.util.object.api.ApiResponseList;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ModuleServiceImpl implements ModuleService {
    private final ModuleRepository moduleRepository;
    private final ModuleMapper moduleMapper;
    private final SecurityContextInitializer securityContextInitializer;
    public ModuleServiceImpl(ModuleRepository moduleRepository, ModuleMapper moduleMapper, SecurityContextInitializer securityContextInitializer) {
        this.moduleRepository = moduleRepository;
        this.moduleMapper = moduleMapper;
        this.securityContextInitializer = securityContextInitializer;
    }
    @Override
    public ApiResponseList<ModuleModel> findByUser() throws GenericListServiceException {
        Long idCompany = securityContextInitializer.getIdCompany();
        Long idUser = securityContextInitializer.getIdUser();
        List<ModuleModel> list = this.moduleMapper.toModuleModelList(this.moduleRepository.findByIdUser(idCompany, idUser));
        if(list.isEmpty()) {
            throw new GenericListServiceException(404);
        }
        return new ApiResponseList<>(Optional.of(list));
    }
}
