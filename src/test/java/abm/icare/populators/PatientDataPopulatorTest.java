package abm.icare.populators;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

import abm.icare.beans.Medicine;
import abm.icare.beans.Patient;
import abm.icare.config.SpringTestNGSupport;
import abm.icare.dataproviders.PatientDataProvider;
import abm.icare.dtos.MedicineDto;
import abm.icare.dtos.PatientDto;

public class PatientDataPopulatorTest extends SpringTestNGSupport {

	@Autowired
	private PatientDataPopulator patientDataPopulator;

	@Test
	public void shouldPopulatePatient() {
		// GIVEN
		final PatientDto patientDto = PatientDataProvider.createPatientDto();

		// WHEN
		Patient actual = patientDataPopulator.populatePatient(patientDto);

		// THEN
		Assert.assertEquals(actual.getAddrLine1(), "Address Line 1");
		Assert.assertEquals(actual.getAddrLine2(), "Address Line 2");
		Assert.assertEquals(actual.getCity(), "Mumbai");
		Assert.assertEquals(actual.getEmailId(), "rock@gmail.com");
		Assert.assertEquals(actual.getFirstName(), "Rock");
		Assert.assertEquals(actual.getLastName(), "Johnson");
		Assert.assertEquals(actual.getMiddleName(), "Albert");
		Assert.assertEquals(actual.getMobileNo(), 7823078320l);
		Assert.assertEquals(actual.getState(), "Maharashtra");
		Assert.assertEquals(actual.getZipCode(), "411020");
		Assert.assertEquals(actual.getId(), null);
	}

	@Test
	public void shouldPopulatePatientWithId() {
		// GIVEN
		final PatientDto patientDto = PatientDataProvider.createPatientDto();
		patientDto.setId("UID201");

		// WHEN
		Patient actual = patientDataPopulator.populatePatient(patientDto);

		// THEN
		Assert.assertEquals(actual.getAddrLine1(), "Address Line 1");
		Assert.assertEquals(actual.getAddrLine2(), "Address Line 2");
		Assert.assertEquals(actual.getCity(), "Mumbai");
		Assert.assertEquals(actual.getEmailId(), "rock@gmail.com");
		Assert.assertEquals(actual.getFirstName(), "Rock");
		Assert.assertEquals(actual.getLastName(), "Johnson");
		Assert.assertEquals(actual.getMiddleName(), "Albert");
		Assert.assertEquals(actual.getMobileNo(), 7823078320l);
		Assert.assertEquals(actual.getState(), "Maharashtra");
		Assert.assertEquals(actual.getZipCode(), "411020");
		Assert.assertEquals(actual.getId(), "UID201");
	}

	@Test
	public void shouldPopulatePatientDto() {
		// GIVEN
		final Patient patient = PatientDataProvider.createPatient();

		// WHEN
		PatientDto actual = patientDataPopulator.populatePatientDto(patient);

		// THEN
		Assert.assertEquals(actual.getId(), "UID201");
		Assert.assertEquals(actual.getAddrLine1(), "Address Line 1");
		Assert.assertEquals(actual.getAddrLine2(), "Address Line 2");
		Assert.assertEquals(actual.getCity(), "Mumbai");
		Assert.assertEquals(actual.getEmailId(), "albert@gmail.com");
		Assert.assertEquals(actual.getFirstName(), "Albert");
		Assert.assertEquals(actual.getLastName(), "Einstein");
		Assert.assertEquals(actual.getMiddleName(), "Kevin");
		Assert.assertEquals(actual.getMobileNo(), 7850078600l);
		Assert.assertEquals(actual.getState(), "Maharashtra");
		Assert.assertEquals(actual.getZipCode(), "411030");
	}

	@Test
	public void shouldPopulatePatientDtos() {
		// GIVEN
		final List<Patient> patients = PatientDataProvider.createPatients();

		// WHEN
		List<PatientDto> actual = patientDataPopulator
				.populatePatientDtos(patients);

		// THEN
		Assert.assertEquals(actual.size(), 4);
		Assert.assertEquals(actual.get(0).getId(), "UID100");
		Assert.assertEquals(actual.get(1).getId(), "UID101");
		Assert.assertEquals(actual.get(2).getId(), "UID102");
		Assert.assertEquals(actual.get(3).getId(), "UID103");
		
	}

	@Test
	public void shouldPopulateMedicine() {
		// GIVEN
		final Medicine medicine = new Medicine();
		medicine.setId("MID101");
		medicine.setName("Crocine");

		// WHEN
		MedicineDto actual = patientDataPopulator.populateMedicineDto(medicine);

		// THEN
		Assert.assertEquals(actual.getId(), "MID101");
		Assert.assertEquals(actual.getName(), "Crocine");
	}

	@Test
	public void shouldPopulateMedicineDtos() {
		// GIVEN
		final List<Medicine> medicines = Arrays.asList(new Medicine("MID201",
				"Crocine"), new Medicine("MID202", "Crocold"));

		// WHEN
		List<MedicineDto> actual = patientDataPopulator
				.populateMedicineDtos(medicines);

		// THEN
		Assert.assertEquals(actual.size(), 2);
		Assert.assertEquals(actual.get(0).getId(), "MID201");
		Assert.assertEquals(actual.get(0).getName(), "Crocine");
		Assert.assertEquals(actual.get(1).getId(), "MID202");
		Assert.assertEquals(actual.get(1).getName(), "Crocold");

	}
}
