package abm.icare.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import abm.icare.beans.Patient;
import abm.icare.config.SpringTestNGSupport;

public class PatientRepositoryTest extends SpringTestNGSupport {

	@Autowired
	private PatientRepository patientRepository;

	@BeforeMethod
	public void setUpData() {
		Patient patient = new Patient();
		patient.setEmailId("rock@gmail.com");
		patient.setFirstName("Rock");
		patient.setLastName("Johnson");
		patient.setMiddleName("Albert");

		Patient patient2 = new Patient();
		patient2.setEmailId("rock@gmail.com");
		patient2.setFirstName("Rocky");
		patient2.setLastName("Atkinson");
		patient2.setMiddleName("Albert");

		patientRepository.save(patient);
		patientRepository.save(patient2);
	}

	@AfterMethod
	public void destroyData() {
		patientRepository.deleteAll();
	}

	@Test
	@Transactional
	public void shouldSearchPatientById() {
		// GIVEN
		String name = "Rock";

		// WHEN
		List<Patient> actual = patientRepository.findByName(name);

		// THEN
		Assert.assertEquals(actual.size(), 2);
	}

}
