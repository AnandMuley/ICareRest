package abm.icare.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import abm.icare.beans.Patient;
import abm.icare.dtos.PatientDto;
import abm.icare.populators.PatientDataPopulator;
import abm.icare.repositories.PatientRepository;

@Service
public class PatientService {

	@Autowired
	private PatientRepository patientRepository;

	@Autowired
	private PatientDataPopulator patientDataPopulator;

	public PatientDto findById(String id) {
		Patient patient = patientRepository.findOne(id);
		patientDataPopulator.populatePatientDto(patient);
		return patientDataPopulator.getPatientDto();
	}

	public PatientDto update(PatientDto patientDto) {
		patientDataPopulator.populatePatient(patientDto);
		patientRepository.save(patientDataPopulator.getPatient());
		patientDto.setId(patientDataPopulator.getPatient().getId());
		return patientDto;
	}

}
