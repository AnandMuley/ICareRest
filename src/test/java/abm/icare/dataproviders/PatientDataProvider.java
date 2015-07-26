package abm.icare.dataproviders;

import abm.icare.dtos.PatientDto;

public abstract class PatientDataProvider {

	public static PatientDto createPatient() {
		PatientDto patientDto = new PatientDto();
		patientDto.setEmailId("rock@gmail.com");
		patientDto.setFirstName("Rock");
		patientDto.setLastName("Johnson");
		patientDto.setMiddleName("Albert");
		return patientDto;
	}

}
