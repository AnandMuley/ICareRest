package abm.icare.exceptions;

public class PatientNotFoundException extends PatientServiceException {

	private static final long serialVersionUID = 3058380594886682445L;

	public PatientNotFoundException(String message) {
		super(message);
	}

}
