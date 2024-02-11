package luis122448.platformtraining.util.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenericPageServiceException extends Exception {
    private String logMessage;
    public GenericPageServiceException() {
        super();
    }
    public GenericPageServiceException(String message) {
        super(message);
        this.logMessage = message;
    }
    public GenericPageServiceException(String message,String logMessage) {
        super(message);
        this.logMessage = logMessage;
    }
    public GenericPageServiceException(String message, Throwable cause) {
        super(message, cause);
        this.logMessage = message;
    }
    public GenericPageServiceException(String message,String logMessage, Throwable cause) {
        super(message, cause);
        this.logMessage = logMessage;
    }
    public GenericPageServiceException(Throwable cause) {
        super(cause);
    }
    protected GenericPageServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
