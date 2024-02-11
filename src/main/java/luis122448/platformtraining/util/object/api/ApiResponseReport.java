package luis122448.platformtraining.util.object.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.sf.jasperreports.engine.JasperPrint;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDateTime;
import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponseReport<T> {

	private Short status;
    private String message;
    private Optional<JasperPrint> jasperPrint;
    // Log
    private String logMessage;
    private String logUser;
    private LocalDateTime logTime;
    public ApiResponseReport(int status, String message, Optional<JasperPrint> list) {
        this.status = (short) status;
        this.message = message;
        this.jasperPrint = list;
        this.logMessage = message;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            this.logUser = authentication.getName();
        } else {
            this.logUser = "Unknown";
        }
        this.logTime = LocalDateTime.now();
    }
    public ApiResponseReport(int status, String message, String logMessage, Optional<JasperPrint> list) {
        this.status = (short) status;
        this.message = message;
        this.jasperPrint = list;
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
