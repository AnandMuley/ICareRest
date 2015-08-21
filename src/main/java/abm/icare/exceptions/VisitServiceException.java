package abm.icare.exceptions;

public class VisitServiceException extends ICareServiceException {

	private static final long serialVersionUID = -1698227171530022754L;

	public VisitServiceException() {
	}

	public VisitServiceException(String message) {
		super(message);
	}

	public VisitServiceException(Throwable cause) {
		super(cause);
	}

	public VisitServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public VisitServiceException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
