package abm.icare.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

import abm.icare.beans.Patient;
import abm.icare.config.SpringTestNGSupport;

public class PatientRepositoryTest extends SpringTestNGSupport {

	@Autowired
	private PatientRepository patientRepository;

	@Test
	public void shouldSearchPatientById() {
		// GIVEN
		Patient patient = new Patient();
		patient.setEmailId("rock@gmail.com");
		patient.setFirstName("Rock");
		patient.setLastName("Johnson");
		patient.setMiddleName("Albert");
		patientRepository.save(patient);

		Assert.assertNotNull(patient.getId());

		// WHEN
		Patient actual = patientRepository.findOne(patient.getId());

		// THEN
		Assert.assertEquals(actual.getEmailId(), "rock@gmail.com");
	}

}
