package luis122448.platformtraining.application.persistence.mapper;

import luis122448.platformtraining.application.domain.model.TeacherModel;
import luis122448.platformtraining.application.persistence.entity.TeacherEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TeacherMapper {

    @Mappings({

    })
    TeacherModel toTeacherModel(TeacherEntity teacherEntity);
    List<TeacherModel> toTeacherModelList(List<TeacherEntity> teacherEntityList);

    @InheritInverseConfiguration
    TeacherEntity toTeacherEntity(TeacherModel teacherModel);
    List<TeacherEntity> toTeacherEntityList(List<TeacherEntity> teacherEntityList);

}
