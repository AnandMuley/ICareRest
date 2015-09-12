package abm.icare.services;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.springframework.test.util.ReflectionTestUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import abm.icare.beans.PatientQueue;
import abm.icare.dataproviders.AppointmentDataProvider;
import abm.icare.repositories.PatientQueueRepository;

public class PatientQueueServiceTest {

	private PatientQueueService patientQueueService;
	private final String QUEUE_ID = "55be228044aebfcd919d77d0";
	private Mockery context;
	private PatientQueueRepository mockPatientQueueRepository;

	@BeforeClass
	public void setUp() {
		context = new Mockery();
		mockPatientQueueRepository = context.mock(PatientQueueRepository.class);
		patientQueueService = new PatientQueueService();
		ReflectionTestUtils.setField(patientQueueService,
				"patientQueueRepository", mockPatientQueueRepository);
	}

	@Test
	public void shouldMovePatientToOnHoldQueue() throws InterruptedException {
		// GIVEN
		final String appointmentId = "55ae228044webfcdd19d7720";
		final PatientQueue patientQueue = AppointmentDataProvider
				.createPatientQueue();

		context.checking(new Expectations() {
			{
				oneOf(mockPatientQueueRepository).findOne(with(QUEUE_ID));
				will(returnValue(patientQueue));
			}
		});
		// WHEN
		PatientQueue patientQueueUpdated = patientQueueService.putOnHold(
				QUEUE_ID, appointmentId);

		// THEN
		Assert.assertEquals(patientQueueUpdated.getLive().size(), 1);
		Assert.assertEquals(patientQueueUpdated.getOnhold().iterator().next()
				.getId(), appointmentId);
	}

	@Test
	public void shouldMoveAPatientToLiveQueue() throws InterruptedException {
		// GIVEN
		final String appointmentId = "55ae228044webfcdd19d7720";
		final PatientQueue patientQueue = AppointmentDataProvider
				.createPatientQueueOnhold();

		context.checking(new Expectations() {
			{
				oneOf(mockPatientQueueRepository).findOne(with(QUEUE_ID));
				will(returnValue(patientQueue));
			}
		});

		// WHEN moving patient1
		final PatientQueue patientQueueUpdated = patientQueueService
				.moveToLive(QUEUE_ID, appointmentId);

		// THEN
		Assert.assertEquals(patientQueueUpdated.getOnhold().size(), 1);
		Assert.assertEquals(patientQueueUpdated.getLive().iterator().next()
				.getId(), appointmentId);

		context.checking(new Expectations() {
			{
				oneOf(mockPatientQueueRepository).findOne(with(QUEUE_ID));
				will(returnValue(patientQueueUpdated));
			}
		});

		// WHEN moving patient2
		PatientQueue patientQueueUpdated2 = patientQueueService.moveToLive(
				QUEUE_ID, "55ae228044webfcdd19d7721");

		// THEN
		Assert.assertEquals(patientQueueUpdated2.getOnhold().size(), 0);
		Assert.assertEquals(patientQueueUpdated2.getLive().iterator().next()
				.getId(), appointmentId);
	}

}
