package abm.icare.services;

import java.util.List;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.springframework.test.util.ReflectionTestUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import abm.icare.actions.AppointmentSaveAction;
import abm.icare.beans.Appointment;
import abm.icare.config.SpringTestNGSupport;
import abm.icare.dataproviders.AppointmentDataProvider;
import abm.icare.dtos.AppointmentDto;
import abm.icare.repositories.AppointmentRepository;
import abm.icare.utils.DateUtils;

public class AppointmentServiceTest extends SpringTestNGSupport {

	private AppointmentService appointmentService;
	private AppointmentRepository mockAppointmentRepository;
	private Mockery context;

	@BeforeClass
	public void init() {
		appointmentService = new AppointmentService();
		context = new Mockery();
		mockAppointmentRepository = context.mock(AppointmentRepository.class);
		ReflectionTestUtils.setField(appointmentService,
				"appointmentRepository", mockAppointmentRepository);
		ReflectionTestUtils.setField(appointmentService, "context",
				applicationContext);
	}

	@Test
	public void shouldCreateAppointment() {
		// GIVEN
		final AppointmentDto appointmentDto = AppointmentDataProvider
				.createAppointmentDto();

		final AppointmentSaveAction appointmentSaveAction = new AppointmentSaveAction();

		context.checking(new Expectations() {
			{
				oneOf(mockAppointmentRepository).save(
						with(any(Appointment.class)));
				will(appointmentSaveAction);
			}
		});
		// WHEN
		appointmentService.create(appointmentDto);

		// THEN
		Assert.assertEquals(appointmentDto.getId(), "APO101");
	}

	@Test
	public void shouldGetAppointmentsForDate() {
		// GIVEN
		final String datedOn = DateUtils.getStringDateLater(2);
		final List<Appointment> appointments = AppointmentDataProvider
				.createAppointments();

		context.checking(new Expectations() {
			{
				oneOf(mockAppointmentRepository).findByDatedOn(with(datedOn));
				will(returnValue(appointments));
			}
		});

		// WHEN
		List<AppointmentDto> actualAppointments = appointmentService
				.getAppointmentsFor(datedOn);

		// THEN
		Assert.assertEquals(actualAppointments.size(), 5);
	}

}