package luis122448.platformtraining.util.object.archive;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ColumnInfo {

    private Integer index;
    private String name;
    private Boolean required;
}
