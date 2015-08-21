package abm.icare.exceptions;

public class MedicineServiceException extends ICareServiceException {

	private static final long serialVersionUID = -1020088781110323561L;

	public MedicineServiceException() {
		super();
	}

	public MedicineServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public MedicineServiceException(String message) {
		super(message);
	}

	public MedicineServiceException(Throwable cause) {
		super(cause);
	}

}
