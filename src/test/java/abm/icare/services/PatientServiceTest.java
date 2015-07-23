package abm.icare.services;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import org.jmock.Expectations;
import org.springframework.test.util.ReflectionTestUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import abm.icare.beans.Patient;
import abm.icare.config.RootTestConfig;
import abm.icare.dataproviders.PatientDataProvider;
import abm.icare.dtos.PatientDto;
import abm.icare.populators.PatientDataPopulator;
import abm.icare.repositories.PatientRepository;

@Test
public class PatientServiceTest extends RootTestConfig {

	private PatientRepository mockPatientRepository;
	private PatientService patientService;
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
		patientService = new PatientService();
		patientDataPopulator = new PatientDataPopulator();

		ReflectionTestUtils.setField(patientService, "patientDataPopulator",
				patientDataPopulator);
		ReflectionTestUtils.setField(patientDataPopulator, "patientDto",
				patientDto);
		ReflectionTestUtils.setField(patientDataPopulator, "patient", patient);

	}

	@Test(dataProviderClass = PatientDataProvider.class, dataProvider = "validPatient")
	public void shouldFindPatientById(final Patient patient) {
		// GIVEN
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
		assertNotNull(actual);
		assertEquals(actual.getEmailId(), "rock@gmail.com");
		assertEquals(actual.getFirstName(), "Rock");
		assertEquals(actual.getId(), "ID101");
		assertEquals(actual.getLastName(), "Johnson");
		assertEquals(actual.getMiddleName(), "Albert");
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
		assertNotNull(actual);
		assertEquals(actual.getEmailId(), "roc@gmail.com");
		assertEquals(actual.getFirstName(), "alia");
		assertEquals(actual.getLastName(), "brewer");
		assertEquals(actual.getMiddleName(), "oliver");
	}

}
