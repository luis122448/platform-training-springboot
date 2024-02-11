package luis122448.platformtraining.application.persistence.mapper;

import luis122448.platformtraining.application.domain.model.CommentModel;
import luis122448.platformtraining.application.persistence.dto.CommentDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    @Mappings({
        @Mapping(target = "commentModelList", ignore = true)
    })
    CommentModel toCommentModel(CommentDTO commentDTO);
    List<CommentModel> toCommentModelList(List<CommentDTO> commentDTOList);

    @InheritInverseConfiguration
    CommentDTO toCommentDto(CommentModel commentModel);
    List<CommentDTO> toCommentDtoList(List<CommentModel> commentModelList);

}
