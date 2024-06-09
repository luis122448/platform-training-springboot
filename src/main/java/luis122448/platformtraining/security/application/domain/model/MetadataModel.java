package luis122448.platformtraining.security.application.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import luis122448.platformtraining.application.domain.model.ModuleModel;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MetadataModel {
    private UserModel user;
    private List<ModuleModel> modules;
}
