package abm.icare.exceptions;

public class PatientNotFoundException extends PatientServiceException {

	private static final long serialVersionUID = 3058380594886682445L;

	public PatientNotFoundException() {
	}

	public PatientNotFoundException(String message) {
		super(message);
	}

	public PatientNotFoundException(Throwable cause) {
		super(cause);
	}

	public PatientNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public PatientNotFoundException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
