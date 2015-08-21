package abm.icare.exceptions;

public class DeleteVisitException extends VisitServiceException {

	private static final long serialVersionUID = -5965613471175276765L;

	public DeleteVisitException() {
	}

	public DeleteVisitException(String message) {
		super(message);
	}

	public DeleteVisitException(Throwable cause) {
		super(cause);
	}

	public DeleteVisitException(String message, Throwable cause) {
		super(message, cause);
	}

	public DeleteVisitException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
