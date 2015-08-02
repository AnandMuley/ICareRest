package abm.icare.populators;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import abm.icare.beans.Medicine;
import abm.icare.beans.Patient;
import abm.icare.dtos.MedicineDto;
import abm.icare.dtos.PatientDto;

@Component
public class PatientDataPopulator {

	@Autowired
	private ApplicationContext context;

	public List<MedicineDto> populateMedicineDtos(List<Medicine> medicines) {
		List<MedicineDto> medicineDtos = new ArrayList<MedicineDto>();
		for (Medicine medicine : medicines) {
			medicineDtos.add(populateMedicineDto(medicine));
		}
		return medicineDtos;
	}

	public MedicineDto populateMedicineDto(Medicine medicine) {
		MedicineDto medicineDto = context.getBean(MedicineDto.class);
		medicineDto.setId(medicine.getId());
		medicineDto.setName(medicine.getName());
		return medicineDto;
	}

	public Patient populatePatient(PatientDto patientDto) {
		Patient patient = context.getBean(Patient.class);
		if (!StringUtils.isEmpty(patientDto.getId())) {
			patient.setId(patientDto.getId());
		}
		patient.setEmailId(patientDto.getEmailId());
		patient.setFirstName(patientDto.getFirstName());
		patient.setLastName(patientDto.getLastName());
		patient.setMiddleName(patientDto.getMiddleName());
		patient.setAddrLine1(patientDto.getAddrLine1());
		patient.setAddrLine2(patientDto.getAddrLine2());
		patient.setCity(patientDto.getCity());
		patient.setMobileNo(patientDto.getMobileNo());
		patient.setState(patientDto.getState());
		patient.setZipCode(patientDto.getZipCode());
		return patient;
	}

	public List<PatientDto> populatePatientDtos(List<Patient> patients) {
		List<PatientDto> dtos = new ArrayList<PatientDto>();
		for (Patient patient : patients) {
			dtos.add(populatePatientDto(patient));
		}
		return dtos;
	}

	public PatientDto populatePatientDto(Patient patient) {
		PatientDto patientDto = context.getBean(PatientDto.class);
		patientDto.setAddrLine1(patient.getAddrLine1());
		patientDto.setAddrLine2(patient.getAddrLine2());
		patientDto.setCity(patient.getCity());
		patientDto.setEmailId(patient.getEmailId());
		patientDto.setFirstName(patient.getFirstName());
		patientDto.setId(patient.getId());
		patientDto.setLastName(patient.getLastName());
		patientDto.setMiddleName(patient.getMiddleName());
		patientDto.setMobileNo(patient.getMobileNo());
		patientDto.setState(patient.getState());
		patientDto.setZipCode(patient.getZipCode());
		return patientDto;
	}

}
