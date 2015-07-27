package abm.icare.services;

import org.jmock.Expectations;
import org.springframework.context.ApplicationContext;
import org.springframework.test.util.ReflectionTestUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import abm.icare.actions.SavePatientAction;
import abm.icare.beans.Patient;
import abm.icare.config.RootMockConfig;
import abm.icare.dataproviders.PatientDataProvider;
import abm.icare.dtos.PatientDto;
import abm.icare.populators.PatientDataPopulator;
import abm.icare.repositories.PatientRepository;

public class PatientServiceTest implements RootMockConfig {

	private PatientRepository mockPatientRepository;
	private PatientServiceImpl patientService;
	private PatientDataPopulator patientDataPopulator;
	private ApplicationContext mockApplicationContext;

	@BeforeTest
	public void initData() {
		patientService = new PatientServiceImpl();
		patientDataPopulator = new PatientDataPopulator();
		mockApplicationContext = context.mock(ApplicationContext.class);
		mockPatientRepository = context.mock(PatientRepository.class);
		ReflectionTestUtils.setField(patientService, "patientRepository",
				mockPatientRepository);
		ReflectionTestUtils.setField(patientService, "patientDataPopulator",
				patientDataPopulator);
		ReflectionTestUtils.setField(patientDataPopulator, "context",
				mockApplicationContext);
	}

	@Test
	public void shouldFindPatientById() {
		// GIVEN
		final PatientDto patientDto = new PatientDto();
		final Patient patient = PatientDataProvider.createPatient();
		final String name = "Albert";

		context.checking(new Expectations() {
			{
				oneOf(mockPatientRepository).findByFirstName(with(name));
				will(returnValue(patient));
				oneOf(mockApplicationContext).getBean(with(PatientDto.class));
				will(returnValue(patientDto));
			}
		});

		// WHEN
		PatientDto actual = patientService.findByName(name);

		// THEN
		Assert.assertNotNull(actual);
		Assert.assertEquals(actual.getEmailId(), "albert@gmail.com");
		Assert.assertEquals(actual.getFirstName(), "Albert");
		Assert.assertEquals(actual.getId(), "UID201");
		Assert.assertEquals(actual.getLastName(), "Einstein");
		Assert.assertEquals(actual.getMiddleName(), "Kevin");
	}

	@Test
	public void shouldUpdatePatient() {
		// GIVEN
		final Patient patient = new Patient();
		final PatientDto patientDto = new PatientDto();
		patientDto.setEmailId("roc@gmail.com");
		patientDto.setFirstName("alia");
		patientDto.setLastName("brewer");
		patientDto.setMiddleName("oliver");

		context.checking(new Expectations() {
			{
				oneOf(mockPatientRepository).save(with(patient));
				oneOf(mockApplicationContext).getBean(with(PatientDto.class));
				will(returnValue(patientDto));
				oneOf(mockApplicationContext).getBean(with(Patient.class));
				will(returnValue(patient));
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

	@Test
	public void shouldCreatePatient() {
		// GIVEN
		final Patient patient = new Patient();
		final PatientDto patientDto = PatientDataProvider.createPatientDto();
		final SavePatientAction savePatientAction = new SavePatientAction();

		context.checking(new Expectations() {
			{
				oneOf(mockPatientRepository).insert(with(patient));
				will(savePatientAction);
				oneOf(mockApplicationContext).getBean(with(Patient.class));
				will(returnValue(patient));
			}
		});
		// WHEN
		PatientDto actual = patientService.createPatient(patientDto);

		// THEN
		Assert.assertEquals(actual.getId(), "UID201");

	}

}
