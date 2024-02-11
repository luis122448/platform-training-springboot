package luis122448.platformtraining.util.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenericObjectServiceException extends Exception {
	private String logMessage;

	public GenericObjectServiceException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GenericObjectServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public GenericObjectServiceException(String message) {
		super(message);
		this.logMessage = message;
	}

	public GenericObjectServiceException(String message, String logMessage) {
		super(message);
		this.logMessage = logMessage;
	}

	public GenericObjectServiceException(String message, Throwable cause) {
		super(message, cause);
		this.logMessage = message;
	}

	public GenericObjectServiceException(String message, String logMessage, Throwable cause) {
		super(message, cause);
		this.logMessage = logMessage;
	}

	public GenericObjectServiceException(Throwable cause) {
		super(cause);
	}

}
