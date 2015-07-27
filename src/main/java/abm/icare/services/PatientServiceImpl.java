package abm.icare.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import abm.icare.beans.Patient;
import abm.icare.dtos.PatientDto;
import abm.icare.populators.PatientDataPopulator;
import abm.icare.repositories.PatientRepository;

@Service
public class PatientServiceImpl implements PatientService {

	@Autowired
	private PatientRepository patientRepository;

	@Autowired
	private PatientDataPopulator patientDataPopulator;

	@Override
	public PatientDto findByName(String firstName) {
		Patient patient = patientRepository.findByFirstName(firstName);
		return patientDataPopulator.populatePatientDto(patient);
	}

	@Override
	public PatientDto update(PatientDto patientDto) {
		Patient patient = patientDataPopulator.populatePatient(patientDto);
		patientRepository.save(patient);
		patientDto = patientDataPopulator.populatePatientDto(patient);
		return patientDto;
	}

	@Override
	public PatientDto createPatient(PatientDto patientDto) {
		Patient patient = patientDataPopulator.populatePatient(patientDto);
		patientRepository.insert(patient);
		patientDto.setId(patient.getId());
		return patientDto;
	}
}
