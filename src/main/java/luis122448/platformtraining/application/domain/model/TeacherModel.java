package luis122448.platformtraining.application.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TeacherModel {

    private Long idTeacher;
    private String name;
    private String lastName;
    private String urlPhoto;
}
