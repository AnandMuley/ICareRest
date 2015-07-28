package abm.icare.populators;

import org.jmock.Expectations;
import org.springframework.context.ApplicationContext;
import org.springframework.test.util.ReflectionTestUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import abm.icare.beans.Patient;
import abm.icare.config.RootMockConfig;
import abm.icare.dataproviders.PatientDataProvider;
import abm.icare.dtos.PatientDto;

public class PatientDataPopulatorTest implements RootMockConfig {

	private PatientDataPopulator patientDataPopulator;
	private ApplicationContext mockApplicationContext;

	@BeforeTest
	public void setUp() {
		mockApplicationContext = context.mock(ApplicationContext.class);
		patientDataPopulator = new PatientDataPopulator();
		ReflectionTestUtils.setField(patientDataPopulator, "context",
				mockApplicationContext);
	}

	@Test
	public void shouldPopulatePatient() {
		// GIVEN
		final PatientDto patientDto = PatientDataProvider.createPatientDto();
		final Patient patient = new Patient();

		context.checking(new Expectations() {
			{
				oneOf(mockApplicationContext).getBean(with(Patient.class));
				will(returnValue(patient));
			}
		});
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
		final Patient patient = new Patient();

		context.checking(new Expectations() {
			{
				oneOf(mockApplicationContext).getBean(with(Patient.class));
				will(returnValue(patient));
			}
		});
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
		final PatientDto patientDto = new PatientDto();

		context.checking(new Expectations() {
			{
				oneOf(mockApplicationContext).getBean(with(PatientDto.class));
				will(returnValue(patientDto));
			}
		});

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

}
