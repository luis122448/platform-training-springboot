package luis122448.platformtraining.util.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenericListServiceException extends Exception{
    private String logMessage;
    public GenericListServiceException() {
        super();
    }

    public GenericListServiceException(String message) {
        super(message);
        this.logMessage = message;
    }
    public GenericListServiceException(String message ,String logMessage) {
        super(message);
        this.logMessage = logMessage;
    }

    public GenericListServiceException(String message, Throwable cause) {
        super(message, cause);
        this.logMessage = message;
    }
    public GenericListServiceException(String message ,String logMessage, Throwable cause) {
        super(message, cause);
        this.logMessage = logMessage;
    }

    public GenericListServiceException(Throwable cause) {
        super(cause);
    }

    protected GenericListServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
