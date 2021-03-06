package abm.icare.services;

import java.util.Iterator;
import java.util.List;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.springframework.test.util.ReflectionTestUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import abm.icare.actions.SavePatientQueueAction;
import abm.icare.beans.Appointment;
import abm.icare.beans.PatientQueue;
import abm.icare.config.SpringTestNGSupport;
import abm.icare.dataproviders.AppointmentDataProvider;
import abm.icare.dtos.AppointmentDto;
import abm.icare.dtos.PatientQueueDto;
import abm.icare.repositories.PatientQueueRepository;
import abm.icare.utils.BeanCreatorUtil;
import abm.icare.utils.DateUtils;
import abm.icare.utils.DtoCreatorUtil;

public class PatientQueueServiceTest extends SpringTestNGSupport {

	private PatientQueueService patientQueueService;
	private final String QUEUE_ID = "55be228044aebfcd919d77d0";
	private Mockery context;
	private PatientQueueRepository mockPatientQueueRepository;
	private BeanCreatorUtil beanCreatorUtil;
	private DtoCreatorUtil dtoCreatorUtil;

	@BeforeClass
	public void setUp() {
		context = new Mockery();
		beanCreatorUtil = new BeanCreatorUtil();
		dtoCreatorUtil = new DtoCreatorUtil();
		mockPatientQueueRepository = context.mock(PatientQueueRepository.class);
		patientQueueService = new PatientQueueService();
		ReflectionTestUtils.setField(beanCreatorUtil, "context",
				applicationContext);
		ReflectionTestUtils.setField(dtoCreatorUtil, "context",
				applicationContext);
		ReflectionTestUtils.setField(patientQueueService,
				"patientQueueRepository", mockPatientQueueRepository);
		ReflectionTestUtils.setField(patientQueueService, "beanCreatorUtil",
				beanCreatorUtil);
		ReflectionTestUtils.setField(patientQueueService, "dtoCreatorUtil",
				dtoCreatorUtil);
	}

	@Test
	public void shouldMovePatientToOnHoldQueue() {
		// GIVEN
		final String appointmentId = "55ae228044webfcdd19d7720";
		final PatientQueue patientQueue = AppointmentDataProvider
				.createPatientQueue();

		context.checking(new Expectations() {
			{
				oneOf(mockPatientQueueRepository).findOne(with(QUEUE_ID));
				will(returnValue(patientQueue));
				oneOf(mockPatientQueueRepository).save(
						with(any(PatientQueue.class)));
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
	public void shouldMovePatientToOnHoldQueueNothingToMove() {
		// GIVEN
		final String appointmentId = "55ae228044webfcdd19d7722";
		final PatientQueue patientQueue = AppointmentDataProvider
				.createPatientQueue();

		context.checking(new Expectations() {
			{
				oneOf(mockPatientQueueRepository).findOne(with(QUEUE_ID));
				will(returnValue(patientQueue));
				oneOf(mockPatientQueueRepository).save(
						with(any(PatientQueue.class)));
			}
		});
		// WHEN
		PatientQueue patientQueueUpdated = patientQueueService.putOnHold(
				QUEUE_ID, appointmentId);

		// THEN
		Assert.assertEquals(patientQueueUpdated.getLive().size(), 2);
	}

	@Test
	public void shouldMoveAPatientToLiveQueue() {
		// GIVEN
		final String firstAppointment = "55ae228044webfcdd19d7720";
		final PatientQueue patientQueue = AppointmentDataProvider
				.createPatientQueueOnhold();

		context.checking(new Expectations() {
			{
				oneOf(mockPatientQueueRepository).findOne(with(QUEUE_ID));
				will(returnValue(patientQueue));
				oneOf(mockPatientQueueRepository).save(
						with(any(PatientQueue.class)));
			}
		});

		// WHEN moving patient1
		final PatientQueue patientQueueUpdated = patientQueueService
				.moveToLive(QUEUE_ID, firstAppointment);

		// THEN
		Assert.assertEquals(patientQueueUpdated.getOnhold().size(), 1);
		Assert.assertEquals(patientQueueUpdated.getLive().iterator().next()
				.getId(), firstAppointment);

		context.checking(new Expectations() {
			{
				oneOf(mockPatientQueueRepository).findOne(with(QUEUE_ID));
				will(returnValue(patientQueueUpdated));
				oneOf(mockPatientQueueRepository).save(
						with(any(PatientQueue.class)));
			}
		});

		// WHEN moving patient2
		String secondAppointment = "55ae228044webfcdd19d7721";
		PatientQueue patientQueueUpdated2 = patientQueueService.moveToLive(
				QUEUE_ID, secondAppointment);

		// THEN
		Assert.assertEquals(patientQueueUpdated2.getOnhold().size(), 0);
		Iterator<Appointment> itr = patientQueueUpdated2.getLive().iterator();
		Assert.assertEquals(itr.next().getId(), "55ae228044webfcdd19d7720");
		Assert.assertEquals(itr.next().getId(), "55ae228044webfcdd19d7721");
	}

	@Test
	public void createNew() {
		// GIVEN
		final List<AppointmentDto> appointmentDtos = AppointmentDataProvider
				.createAppointmentDtos();
		final String datedOn = DateUtils.getStringDateLater(2);
		final PatientQueue patientQueue = AppointmentDataProvider
				.createPatientQueue();
		patientQueue.setId("PID101");

		context.checking(new Expectations() {
			{
				oneOf(mockPatientQueueRepository).findByDatedOn(with(datedOn));
				will(returnValue(patientQueue));
			}
		});
		// WHEN
		PatientQueueDto actual = patientQueueService.createNew(appointmentDtos,
				datedOn);

		// THEN
		Assert.assertEquals(actual.getId(), "PID101");
	}

	@Test
	public void createNewNoRecordInDB() {
		// GIVEN
		final List<AppointmentDto> appointmentDtos = AppointmentDataProvider
				.createAppointmentDtos();
		final String datedOn = DateUtils.getStringDateLater(2);
		final SavePatientQueueAction savePatientQueueAction = new SavePatientQueueAction();

		context.checking(new Expectations() {
			{
				oneOf(mockPatientQueueRepository).findByDatedOn(with(datedOn));
				will(returnValue(null));
				oneOf(mockPatientQueueRepository).save(
						with(any(PatientQueue.class)));
				will(savePatientQueueAction);
			}
		});
		// WHEN
		PatientQueueDto actual = patientQueueService.createNew(appointmentDtos,
				datedOn);

		// THEN
		Assert.assertEquals(actual.getId(), "PID101");
	}

	@Test
	public void shouldFindPatientQueueByIdAndDatedOn() {

		// GIVEN
		final String datedOn = DateUtils.getStringDateLater(2);
		final PatientQueue patientQueue = AppointmentDataProvider
				.createPatientQueue();
		patientQueue.setId(QUEUE_ID);

		context.checking(new Expectations() {
			{
				oneOf(mockPatientQueueRepository).findByIdAndDatedOn(
						with(QUEUE_ID), with(datedOn));
				will(returnValue(patientQueue));
			}
		});

		// WHEN
		PatientQueueDto patientQueueDto = patientQueueService.findBy(QUEUE_ID,
				datedOn);

		// THEN
		Assert.assertEquals(patientQueueDto.getId(), QUEUE_ID);
	}

	@Test
	public void shouldReturnNullFindPatientQueueByIdAndDatedOn() {

		// GIVEN
		final String datedOn = DateUtils.getStringDateLater(2);

		context.checking(new Expectations() {
			{
				oneOf(mockPatientQueueRepository).findByIdAndDatedOn(
						with(QUEUE_ID), with(datedOn));
				will(returnValue(null));
			}
		});

		// WHEN
		PatientQueueDto patientQueueDto = patientQueueService.findBy(QUEUE_ID,
				datedOn);

		// THEN
		Assert.assertNull(patientQueueDto);
	}

	@Test
	public void shouldHandleOnHoldQueueEmptyScenario() {
		// GIVEN
		final String appointmentId = "55ae228044webfcdd19d7720";

		context.checking(new Expectations() {
			{
				oneOf(mockPatientQueueRepository).findOne(with(QUEUE_ID));
				will(returnValue(new PatientQueue()));
				oneOf(mockPatientQueueRepository).save(
						with(any(PatientQueue.class)));
			}
		});

		// WHEN moving patient from onhold queue to live queue
		final PatientQueue patientQueueUpdated = patientQueueService
				.moveToLive(QUEUE_ID, appointmentId);

		// THEN
		Assert.assertEquals(patientQueueUpdated.getOnhold().size(), 0);

	}

	@Test
	public void shouldNotMovePatientIfNotFound() {
		// GIVEN
		final String appointmentId = "55ae228044webfcdd19d77224";
		final PatientQueue patientQueue = AppointmentDataProvider
				.createPatientQueueOnhold();

		context.checking(new Expectations() {
			{
				oneOf(mockPatientQueueRepository).findOne(with(QUEUE_ID));
				will(returnValue(patientQueue));
				oneOf(mockPatientQueueRepository).save(
						with(any(PatientQueue.class)));
			}
		});

		// WHEN moving patient from onhold queue to live queue
		final PatientQueue patientQueueUpdated = patientQueueService
				.moveToLive(QUEUE_ID, appointmentId);

		// THEN
		Assert.assertEquals(patientQueueUpdated.getOnhold().size(), 2);

	}

	@Test(enabled = false)
	public void shouldMoveAPatientToLiveQueueAndOrderBasedOnRequestSubmittedOn() {
		// GIVEN
		final String appointmentId = "55ae228044webfcdd19d7720";
		final PatientQueue patientQueue = AppointmentDataProvider
				.createPatientQueueOnhold();

		context.checking(new Expectations() {
			{
				oneOf(mockPatientQueueRepository).findOne(with(QUEUE_ID));
				will(returnValue(patientQueue));
				oneOf(mockPatientQueueRepository).save(
						with(any(PatientQueue.class)));
			}
		});

		// WHEN moving patient1
		final PatientQueue patientQueueUpdated = patientQueueService
				.moveToLive(QUEUE_ID, appointmentId);

		// THEN
		Assert.assertEquals(patientQueueUpdated.getOnhold().size(), 1);
		Assert.assertEquals(patientQueueUpdated.getLive().iterator().next()
				.getId(), appointmentId);

	}

}
