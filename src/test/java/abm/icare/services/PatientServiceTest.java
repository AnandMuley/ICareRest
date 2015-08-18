package abm.icare.services;

import java.util.Collections;
import java.util.List;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.springframework.test.util.ReflectionTestUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import abm.icare.actions.SavePatientAction;
import abm.icare.beans.Patient;
import abm.icare.config.SpringTestNGSupport;
import abm.icare.dataproviders.PatientDataProvider;
import abm.icare.dtos.PatientDto;
import abm.icare.exceptions.PatientNotFoundException;
import abm.icare.exceptions.PatientServiceException;
import abm.icare.populators.PatientDataPopulator;
import abm.icare.repositories.PatientRepository;

public class PatientServiceTest extends SpringTestNGSupport {

	private PatientRepository mockPatientRepository;
	private PatientService patientService;
	private PatientDataPopulator patientDataPopulator;
	private Mockery context;

	@BeforeMethod
	public void initData() {
		context = new Mockery();
		patientService = new PatientService();
		patientDataPopulator = new PatientDataPopulator();
		mockPatientRepository = context.mock(PatientRepository.class);
		ReflectionTestUtils.setField(patientService, "patientRepository",
				mockPatientRepository);
		ReflectionTestUtils.setField(patientService, "patientDataPopulator",
				patientDataPopulator);
		ReflectionTestUtils.setField(patientDataPopulator, "context",
				applicationContext);
	}

	@Test
	public void shouldFindPatientById() throws PatientServiceException {
		// GIVEN
		final List<Patient> patients = PatientDataProvider.createPatients();
		final String name = "Albert";

		context.checking(new Expectations() {
			{
				oneOf(mockPatientRepository).findByName(with(name));
				will(returnValue(patients));
			}
		});

		// WHEN
		List<PatientDto> actual = patientService.findByName(name);

		// THEN
		Assert.assertEquals(actual.size(), 4);
	}

	@Test(expectedExceptions = PatientNotFoundException.class)
	public void shouldThrowNotPatientFoundException()
			throws PatientServiceException {
		// GIVEN
		final String name = "Albert";

		context.checking(new Expectations() {
			{
				oneOf(mockPatientRepository).findByName(with(name));
				will(returnValue(Collections.emptyList()));
			}
		});

		// WHEN
		patientService.findByName(name);

		// THEN
		// Should throw an exception
	}

	@Test
	public void shouldUpdatePatient() {
		// GIVEN
		final PatientDto patientDto = new PatientDto();
		patientDto.setEmailId("roc@gmail.com");
		patientDto.setFirstName("alia");
		patientDto.setLastName("brewer");
		patientDto.setMiddleName("oliver");

		context.checking(new Expectations() {
			{
				oneOf(mockPatientRepository).save(with(any(Patient.class)));
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
		final PatientDto patientDto = PatientDataProvider.createPatientDto();
		final SavePatientAction savePatientAction = new SavePatientAction();

		context.checking(new Expectations() {
			{
				oneOf(mockPatientRepository).insert(with(any(Patient.class)));
				will(savePatientAction);
			}
		});
		// WHEN
		PatientDto actual = patientService.createPatient(patientDto);

		// THEN
		Assert.assertEquals(actual.getId(), "UID201");

	}

}
