package luis122448.platformtraining.util.object.api;

import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

@Getter
@Setter
public class ApiResponseAuth<T> {

    private Short status;
    private String message;
    private Optional<T> object;

    public ApiResponseAuth(int status, String message, Optional<T> object) {
        this.status = (short) status;
        this.message = message;
        this.object = object;
    }

    public ApiResponseAuth(int status, String message) {
        this.status = (short) status;
        this.message = message;
    }

    public ApiResponseAuth(Optional<T> object) {
        this.status = 1;
        this.message = "Success";
        this.object = object;
    }

}
