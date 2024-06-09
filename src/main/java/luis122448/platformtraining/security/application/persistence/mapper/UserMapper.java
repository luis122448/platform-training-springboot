package luis122448.platformtraining.security.application.persistence.mapper;

import luis122448.platformtraining.security.application.domain.model.UserModel;
import luis122448.platformtraining.security.application.persistence.entity.UserEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mappings({
            @Mapping(target = "company", ignore = true)
    })
    UserModel toUserModel(UserEntity userEntity);
    List<UserModel> toUserModelList(List<UserEntity> userEntityList);

    @InheritInverseConfiguration
    UserEntity toUserEntity(UserModel userModel);
    List<UserEntity> toUserEntityList(List<UserModel> userModelList);
}
