package abm.icare.exceptions;

public class UpdateVisitException extends VisitServiceException {

	private static final long serialVersionUID = -223589014190129967L;

	public UpdateVisitException() {
	}

	public UpdateVisitException(String message) {
		super(message);
	}

	public UpdateVisitException(Throwable cause) {
		super(cause);
	}

	public UpdateVisitException(String message, Throwable cause) {
		super(message, cause);
	}

	public UpdateVisitException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
