package abm.icare.exceptions;

public class ICareServiceException extends Exception {

	private static final long serialVersionUID = 6167081443201936769L;

	public ICareServiceException() {
	}

	public ICareServiceException(String message) {
		super(message);
	}

	public ICareServiceException(Throwable cause) {
		super(cause);
	}

	public ICareServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public ICareServiceException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
