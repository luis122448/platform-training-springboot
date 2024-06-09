package luis122448.platformtraining.application.persistence.mapper;

import luis122448.platformtraining.application.domain.model.ModuleModel;
import luis122448.platformtraining.application.persistence.entity.ModuleEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ModuleMapper {

    @Mappings({})
    ModuleModel toModuleModel(ModuleEntity moduleEntity);
    List<ModuleModel> toModuleModelList(List<ModuleEntity> moduleEntityList);

    @InheritInverseConfiguration
    ModuleEntity toModuleEntity(ModuleModel moduleModel);
    List<ModuleEntity> toModuleEntityList(List<ModuleModel> moduleModelList);
}
