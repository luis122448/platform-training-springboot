package luis122448.platformtraining.application.persistence.mapper;

import luis122448.platformtraining.application.domain.model.UserClassModel;
import luis122448.platformtraining.application.persistence.dto.UserClassDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserClassMapper {

    @Mappings({
            @Mapping(target = "commentModelList", ignore = true),
            @Mapping(target = "teacherModel", ignore = true)
    })
    UserClassModel toUserClassModel(UserClassDTO userClassDTO);
    List<UserClassModel> toUserClassModelList(List<UserClassDTO> userClassDTOList);

    @InheritInverseConfiguration
    UserClassDTO toUserClassDto(UserClassModel userClassModel);
    List<UserClassDTO> toUserClassDtoList(List<UserClassModel> userClassModelList);
}
