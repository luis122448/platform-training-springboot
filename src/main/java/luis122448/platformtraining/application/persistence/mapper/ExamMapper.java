package luis122448.platformtraining.application.persistence.mapper;

import luis122448.platformtraining.application.domain.model.ExamModel;
import luis122448.platformtraining.application.persistence.entity.ExamEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ExamMapper {

    @Mappings({
            @Mapping(target = "courseTitle", ignore = true),
            @Mapping(target = "courseUrlIcon", ignore = true),
    })
    ExamModel toExamModel(ExamEntity examEntity);
    List<ExamModel> toExamModelList(List<ExamEntity> examEntityList);

    @InheritInverseConfiguration
    ExamEntity toExamEntity(ExamModel examModel);
    List<ExamEntity> toExamEntityList(List<ExamModel> examModelList);

}
