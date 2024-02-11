package luis122448.platformtraining.application.persistence.mapper;

import luis122448.platformtraining.application.domain.model.UserClassModel;
import luis122448.platformtraining.application.domain.model.UserCourseModel;
import luis122448.platformtraining.application.persistence.dto.UserCourseDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserCourseMapper {

    @Mappings({
            @Mapping(target = "teacherModel", ignore = true),
            @Mapping(target = "userClassModelList", ignore = true)
    })
    UserCourseModel toUserCourseModel(UserCourseDTO userCourseDTO);
    List<UserCourseModel> toUserCourseModelList(List<UserCourseDTO> userCourseDTOList);

    @InheritInverseConfiguration
    UserCourseDTO toUserCourseDto(UserCourseModel userCourseModel);
    List<UserCourseDTO> toUserCourseDtoList(List<UserCourseModel> userCourseModelList);
}
