package abm.icare.dataproviders;

import java.util.ArrayList;
import java.util.List;

import abm.icare.beans.Patient;
import abm.icare.dtos.PatientDto;

public abstract class PatientDataProvider {

	public static PatientDto createPatientDto() {
		PatientDto patientDto = new PatientDto();
		patientDto.setAddrLine1("Address Line 1");
		patientDto.setAddrLine2("Address Line 2");
		patientDto.setCity("Mumbai");
		patientDto.setEmailId("rock@gmail.com");
		patientDto.setFirstName("Rock");
		patientDto.setLastName("Johnson");
		patientDto.setMiddleName("Albert");
		patientDto.setMobileNo(7823078320l);
		patientDto.setState("Maharashtra");
		patientDto.setZipCode("411020");
		return patientDto;
	}

	public static Patient createPatient() {
		Patient patient = new Patient();
		patient.setAddrLine1("Address Line 1");
		patient.setAddrLine2("Address Line 2");
		patient.setCity("Mumbai");
		patient.setEmailId("albert@gmail.com");
		patient.setFirstName("Albert");
		patient.setId("UID201");
		patient.setLastName("Einstein");
		patient.setMiddleName("Kevin");
		patient.setMobileNo(7850078600l);
		patient.setState("Maharashtra");
		patient.setZipCode("411030");
		return patient;
	}

	public static List<PatientDto> createPatientDtos() {
		List<PatientDto> dtos = new ArrayList<PatientDto>();
		for (int i = 0; i < 4; i++) {
			PatientDto patientDto = createPatientDto();
			patientDto.setId("PID20" + i);
			dtos.add(patientDto);
		}
		return dtos;
	}

	public static List<Patient> createPatients() {
		List<Patient> patients = new ArrayList<Patient>();
		for (int i = 0; i < 4; i++) {
			Patient patient = createPatient();
			patient.setId("UID10" + i);
			patients.add(patient);
		}
		return patients;
	}

}
