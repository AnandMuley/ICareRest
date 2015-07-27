package abm.icare.populators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import abm.icare.beans.Patient;
import abm.icare.dtos.PatientDto;

@Component
public class PatientDataPopulator {

	@Autowired
	private ApplicationContext context;

	public Patient populatePatient(PatientDto patientDto) {
		Patient patient = context.getBean(Patient.class);
		if (!StringUtils.isEmpty(patientDto.getId())) {
			patient.setId(patientDto.getId());
		}
		patient.setEmailId(patientDto.getEmailId());
		patient.setFirstName(patientDto.getFirstName());
		patient.setLastName(patientDto.getLastName());
		patient.setMiddleName(patientDto.getMiddleName());
		return patient;
	}

	public PatientDto populatePatientDto(Patient patient) {
		PatientDto patientDto = context.getBean(PatientDto.class);
		patientDto.setEmailId(patient.getEmailId());
		patientDto.setFirstName(patient.getFirstName());
		patientDto.setId(patient.getId());
		patientDto.setLastName(patient.getLastName());
		patientDto.setMiddleName(patient.getMiddleName());
		return patientDto;
	}

}
