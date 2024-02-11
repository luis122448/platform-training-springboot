package luis122448.platformtraining.util.object.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponseList<T> {

	private Short status;
    private String message;
    private Optional<List<T>> list;
    // Log
    private String logMessage;
    private String logUser;
    private LocalDateTime logTime;
    public ApiResponseList(Optional<List<T>> list) {
        this.status = (short) 1;
        this.message = "OK";
        this.list = list;
        this.logMessage = message;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            this.logUser = authentication.getName();
        } else {
            this.logUser = "Unknown";
        }
        this.logTime = LocalDateTime.now();
    }
    public ApiResponseList(int status, String message, Optional<List<T>> list) {
        this.status = (short) status;
        this.message = message;
        this.list = list;
        this.logMessage = message;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            this.logUser = authentication.getName();
        } else {
            this.logUser = "Unknown";
        }
        this.logTime = LocalDateTime.now();
    }
    public ApiResponseList(int status, String message, String logMessage, Optional<List<T>> list) {
        this.status = (short) status;
        this.message = message;
        this.list = list;
        this.logMessage = logMessage;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            this.logUser = authentication.getName();
        } else {
            this.logUser = "Unknown";
        }
        this.logTime = LocalDateTime.now();
    }
}
