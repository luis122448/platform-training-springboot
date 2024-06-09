package luis122448.platformtraining.application.persistence.entity.key;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCourseKey implements Serializable {
    private Long idCompany;
    private Long idUser;
    private Long idCourse;
}
