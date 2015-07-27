package abm.icare.services;

import abm.icare.dtos.PatientDto;

public interface PatientService {

	PatientDto findByName(String id);

	PatientDto update(PatientDto patientDto);

	PatientDto createPatient(PatientDto patientDto);

}
