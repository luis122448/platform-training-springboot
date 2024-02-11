package luis122448.platformtraining.util.object.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDateTime;
import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponsePage<T> {

	private Short status;
    private String message;
    private Optional<Page<T>> page;
    // Log
    private String logMessage;
    private String logUser;
    private LocalDateTime logTime;
    public ApiResponsePage(int status, String message, Optional<Page<T>> page) {
        this.status = (short) status;
        this.message = message;
        this.page = page;
        this.logMessage = message;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            this.logUser = authentication.getName();
        } else {
            this.logUser = "Unknown";
        }
        this.logTime = LocalDateTime.now();
    }
    public ApiResponsePage(int status, String message, String logMessage, Optional<Page<T>> page) {
        this.status = (short) status;
        this.message = message;
        this.page = page;
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
