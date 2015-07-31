package abm.icare.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import abm.icare.beans.Patient;
import abm.icare.constants.AppConstants;
import abm.icare.dtos.PatientDto;
import abm.icare.exceptions.PatientNotFoundException;
import abm.icare.exceptions.PatientServiceException;
import abm.icare.populators.PatientDataPopulator;
import abm.icare.repositories.PatientRepository;

@Service
public class PatientService {

	@Autowired
	private PatientRepository patientRepository;

	@Autowired
	private PatientDataPopulator patientDataPopulator;

	public List<PatientDto> findByName(String searchTxt)
			throws PatientServiceException {
		List<Patient> patientsFound = patientRepository.findByName(searchTxt);
		if (CollectionUtils.isEmpty(patientsFound)) {
			throw new PatientNotFoundException(
					AppConstants.EXCEPTION_PATIENT_NOT_FOUND);
		}
		return patientDataPopulator.populatePatientDtos(patientsFound);
	}

	public PatientDto update(PatientDto patientDto) {
		Patient patient = patientDataPopulator.populatePatient(patientDto);
		patientRepository.save(patient);
		return patientDto;
	}

	public PatientDto createPatient(PatientDto patientDto) {
		Patient patient = patientDataPopulator.populatePatient(patientDto);
		patientRepository.insert(patient);
		patientDto.setId(patient.getId());
		return patientDto;
	}
}
