package abm.icare.exceptions;

public class MedicineNotFoundException extends MedicineServiceException {

	private static final long serialVersionUID = 5999943229679502402L;

	public MedicineNotFoundException() {
		super();
	}

	public MedicineNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public MedicineNotFoundException(String message) {
		super(message);
	}

	public MedicineNotFoundException(Throwable cause) {
		super(cause);
	}

}
