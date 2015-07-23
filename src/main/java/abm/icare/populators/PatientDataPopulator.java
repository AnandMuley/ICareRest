package abm.icare.populators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import abm.icare.beans.Patient;
import abm.icare.dtos.PatientDto;

@Component
public class PatientDataPopulator extends DataPopulator {

	@Autowired
	private Patient patient;

	@Autowired
	private PatientDto patientDto;

	public void populatePatient(PatientDto patientDto) {
		patient.setEmailId(patientDto.getEmailId());
		patient.setFirstName(patientDto.getFirstName());
		patient.setLastName(patientDto.getLastName());
		patient.setMiddleName(patientDto.getMiddleName());
	}

	public void populatePatientDto(Patient patient) {
		patientDto.setEmailId(patient.getEmailId());
		patientDto.setFirstName(patient.getFirstName());
		patientDto.setId(patient.getId());
		patientDto.setLastName(patient.getLastName());
		patientDto.setMiddleName(patient.getMiddleName());
	}

	public Patient getPatient() {
		return patient;
	}

	public PatientDto getPatientDto() {
		return patientDto;
	}

}
