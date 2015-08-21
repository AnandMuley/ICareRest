package abm.icare.exceptions;

public class PatientServiceException extends ICareServiceException {

	private static final long serialVersionUID = -1978503957800794659L;

	public PatientServiceException() {
	}

	public PatientServiceException(String message) {
		super(message);
	}

	public PatientServiceException(Throwable cause) {
		super(cause);
	}

	public PatientServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public PatientServiceException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
