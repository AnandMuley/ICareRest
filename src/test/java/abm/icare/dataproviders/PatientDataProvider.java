package abm.icare.dataproviders;

import abm.icare.beans.Patient;
import abm.icare.dtos.PatientDto;

public abstract class PatientDataProvider {

	public static PatientDto createPatientDto() {
		PatientDto patientDto = new PatientDto();
		patientDto.setEmailId("rock@gmail.com");
		patientDto.setFirstName("Rock");
		patientDto.setLastName("Johnson");
		patientDto.setMiddleName("Albert");
		return patientDto;
	}

	public static Patient createPatient() {
		Patient patient = new Patient();
		patient.setEmailId("albert@gmail.com");
		patient.setFirstName("Albert");
		patient.setId("UID201");
		patient.setLastName("Einstein");
		patient.setMiddleName("Kevin");
		return patient;
	}

}
