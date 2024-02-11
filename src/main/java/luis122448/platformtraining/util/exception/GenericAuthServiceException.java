package luis122448.platformtraining.util.exception;

import lombok.Getter;
import lombok.Setter;

public class GenericAuthServiceException extends Exception{
    private String logMessage;
    public GenericAuthServiceException() {
        super();
    }

    public GenericAuthServiceException(String message) {
        super(message);
        this.logMessage = message;
    }
    public GenericAuthServiceException(String message ,String logMessage) {
        super(message);
        this.logMessage = logMessage;
    }

    public GenericAuthServiceException(String message, Throwable cause) {
        super(message, cause);
        this.logMessage = message;
    }
    public GenericAuthServiceException(String message ,String logMessage, Throwable cause) {
        super(message, cause);
        this.logMessage = logMessage;
    }

    public GenericAuthServiceException(Throwable cause) {
        super(cause);
    }

    protected GenericAuthServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
