package abm.icare.services;

import java.util.List;

import abm.icare.dtos.PatientDto;
import abm.icare.exceptions.PatientServiceException;

public interface PatientService {

	List<PatientDto> findByName(String id) throws PatientServiceException;

	PatientDto update(PatientDto patientDto);

	PatientDto createPatient(PatientDto patientDto);

}
