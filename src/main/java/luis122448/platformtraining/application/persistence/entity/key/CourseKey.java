package luis122448.platformtraining.application.persistence.entity.key;

import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseKey implements Serializable {

    private Long idCompany;
    private Long idCourse;

}
