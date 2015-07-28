package abm.icare.services;

import java.util.List;

import abm.icare.dtos.PatientDto;

public interface PatientService {

	List<PatientDto> findByName(String id);

	PatientDto update(PatientDto patientDto);

	PatientDto createPatient(PatientDto patientDto);

}
