package luis122448.platformtraining.util.object.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDateTime;
import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponseByte<T> {

	private Short status;
    private String message;
    private Optional<byte[]> bytes;
    // MetaData
    private String name;
    private String format;
    private String extension;
    // Log
    private String logMessage;
    private String logUser;
    private LocalDateTime logTime;
    public ApiResponseByte(int status, String message, Optional<byte[]> bytes, String format) {
        this.status = (short) status;
        this.message = message;
        this.bytes = bytes;
        this.format =  format;
        this.logMessage = message;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            this.logUser = authentication.getName();
        } else {
            this.logUser = "Unknown";
        }
        this.logTime = LocalDateTime.now();
    }
    public ApiResponseByte(int status, String message, String logMessage, Optional<byte[]> bytes) {
        this.status = (short) status;
        this.message = message;
        this.bytes = bytes;
        this.logMessage = logMessage;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            this.logUser = authentication.getName();
        } else {
            this.logUser = "Unknown";
        }
        this.logTime = LocalDateTime.now();
    }

    public ApiResponseByte(int status, String message, Optional<byte[]> bytes, String name, String format, String extension) {
        this.status = (short) status;
        this.message = message;
        this.bytes = bytes;
        this.name = name;
        this.format = format;
        this.extension = extension;
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
