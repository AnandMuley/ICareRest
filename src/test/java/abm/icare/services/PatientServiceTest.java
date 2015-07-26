package abm.icare.services;

import org.jmock.Expectations;
import org.springframework.test.util.ReflectionTestUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import abm.icare.beans.Patient;
import abm.icare.config.RootTestConfig;
import abm.icare.dtos.PatientDto;
import abm.icare.populators.PatientDataPopulator;
import abm.icare.repositories.PatientRepository;

public class PatientServiceTest extends RootTestConfig {

	private PatientRepository mockPatientRepository;
	private PatientServiceImpl patientService;
	private PatientDataPopulator patientDataPopulator;
	private PatientDto patientDto;
	private Patient patient;

	@BeforeClass
	public void initData() {
		mockPatientRepository = context.mock(PatientRepository.class);
		ReflectionTestUtils.setField(patientService, "patientRepository",
				mockPatientRepository);
	}

	@BeforeTest
	public void setUp() {
		patient = new Patient();
		patientDto = new PatientDto();
		patientService = new PatientServiceImpl();
		patientDataPopulator = new PatientDataPopulator();

		ReflectionTestUtils.setField(patientService, "patientDataPopulator",
				patientDataPopulator);
		ReflectionTestUtils.setField(patientDataPopulator, "patientDto",
				patientDto);
		ReflectionTestUtils.setField(patientDataPopulator, "patient", patient);

	}

	@Test
	public void shouldFindPatientById() {
		// GIVEN
		final Patient patient = null;
		final String id = "PID101";

		context.checking(new Expectations() {
			{
				oneOf(mockPatientRepository).findOne(with(id));
				will(returnValue(patient));
			}
		});

		// WHEN
		PatientDto actual = patientService.findById(id);

		// THEN
		Assert.assertNotNull(actual);
		Assert.assertEquals(actual.getEmailId(), "rock@gmail.com");
		Assert.assertEquals(actual.getFirstName(), "Rock");
		Assert.assertEquals(actual.getId(), "ID101");
		Assert.assertEquals(actual.getLastName(), "Johnson");
		Assert.assertEquals(actual.getMiddleName(), "Albert");
	}

	@Test
	public void shouldUpdatePatient() {
		// GIVEN
		patientDto.setEmailId("roc@gmail.com");
		patientDto.setFirstName("alia");
		patientDto.setLastName("brewer");
		patientDto.setMiddleName("oliver");

		context.checking(new Expectations() {
			{
				oneOf(mockPatientRepository).save(with(patient));
			}
		});

		// WHEN
		PatientDto actual = patientService.update(patientDto);

		// THEN
		Assert.assertNotNull(actual);
		Assert.assertEquals(actual.getEmailId(), "roc@gmail.com");
		Assert.assertEquals(actual.getFirstName(), "alia");
		Assert.assertEquals(actual.getLastName(), "brewer");
		Assert.assertEquals(actual.getMiddleName(), "oliver");
	}

}
