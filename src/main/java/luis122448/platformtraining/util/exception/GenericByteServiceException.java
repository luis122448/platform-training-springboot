package luis122448.platformtraining.util.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenericByteServiceException extends Exception{
    private Integer status;
    private String message;
    private String logMessage;
    public GenericByteServiceException() {
        super();
        this.status = 400;
        this.message = "AN UNKNOWN ERROR HAS OCCURRED";
        this.logMessage = "AN UNKNOWN ERROR HAS OCCURRED";
    }
    public GenericByteServiceException(int status){
        this.status = status;
        if (status == 1) {
            this.message = "SUCCESS";
        } else if (status == 404) {
            this.message = "NO RECORDS FOUND";
        } else {
            this.message = "AN UNKNOWN ERROR HAS OCCURRED";
        }
        this.logMessage = message;
    }
    public GenericByteServiceException(String message) {
        super(message);
        this.status = 400;
        this.message = message;
        this.logMessage = message;
    }
    public GenericByteServiceException(int status, String message) {
        super(message);
        this.status = status;
        this.message = message;
        this.logMessage = message;
    }
    public GenericByteServiceException(String message , String logMessage) {
        super(message);
        this.status = 400;
        this.message = message;
        this.logMessage = logMessage;
    }
    public GenericByteServiceException(String message, Throwable cause) {
        super(message, cause);
        this.status = 400;
        this.message = message;
        if (cause.getCause() != null){
            this.logMessage = cause.getCause().getMessage();
        }
    }
    public GenericByteServiceException(String message , String logMessage, Throwable cause) {
        super(message, cause);
        this.status = 400;
        this.message = message;
        this.logMessage = logMessage;
    }
    public GenericByteServiceException(Throwable cause) {
        super(cause);
        this.status = 400;
        this.message = cause.getMessage();
        if (cause.getCause() != null){
            this.logMessage = cause.getCause().getMessage();
        }
    }
    protected GenericByteServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.status = 400;
        this.message = message;
        this.logMessage = cause.getMessage();
    }

}
