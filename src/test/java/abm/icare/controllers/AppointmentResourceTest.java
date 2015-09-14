package abm.icare.controllers;

import java.util.List;

import javax.ws.rs.core.MediaType;

import org.jmock.Expectations;
import org.springframework.test.util.ReflectionTestUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import abm.icare.config.AbstractResourceBaseConfig;
import abm.icare.dataproviders.AppointmentDataProvider;
import abm.icare.dtos.AppointmentDto;
import abm.icare.dtos.PatientQueueDto;
import abm.icare.services.AppointmentService;
import abm.icare.services.PatientQueueService;
import abm.icare.utils.DateUtils;

import com.sun.jersey.api.client.ClientResponse;

public class AppointmentResourceTest extends AbstractResourceBaseConfig {

	private AppointmentResource appointmentResource;
	private AppointmentService mockAppointmentService;
	private PatientQueueService mockPatientQueueService;

	@BeforeClass
	public void setData() {
		mockPatientQueueService = context.mock(PatientQueueService.class);
		mockAppointmentService = context.mock(AppointmentService.class);
		appointmentResource = new AppointmentResource();
		ReflectionTestUtils.setField(appointmentResource, "appointmentService",
				mockAppointmentService);
		ReflectionTestUtils.setField(appointmentResource,
				"patientQueueService", mockPatientQueueService);
		super.setUpData(appointmentResource);
	}

	@Test
	public void shouldCreateAppointment() {
		// GIVEN
		final AppointmentDto appointmentDto = AppointmentDataProvider
				.createAppointmentDto();

		context.checking(new Expectations() {
			{
				oneOf(mockAppointmentService).create(
						with(any(AppointmentDto.class)));
			}
		});

		// WHEN
		ClientResponse clientResponse = resource().path(APPOINTMENT_CREATE)
				.accept(MediaType.APPLICATION_JSON)
				.type(MediaType.APPLICATION_JSON)
				.post(ClientResponse.class, appointmentDto);

		// THEN
		Assert.assertEquals(clientResponse.getStatus(), 200);
	}

	@Test
	public void shouldGetAppointmentsOnGivenDate() {
		// GIVEN
		final String datedOn = DateUtils.getStringDateLater(2);
		final List<AppointmentDto> appointmentDtos = AppointmentDataProvider
				.createAppointmentDtos();
		final PatientQueueDto patientQueueDto = AppointmentDataProvider
				.createPatientQueueDto(appointmentDtos);

		context.checking(new Expectations() {
			{
				oneOf(mockAppointmentService).getAppointmentsFor(with(datedOn));
				will(returnValue(appointmentDtos));
				oneOf(mockPatientQueueService).createNew(with(appointmentDtos),
						with(datedOn));
				will(returnValue(patientQueueDto));
			}
		});
		// WHEN
		ClientResponse response = resource().path(APPOINTMENT_FETCH_ALL)
				.queryParam("datedOn", datedOn.toString())
				.accept(MediaType.APPLICATION_JSON)
				.type(MediaType.APPLICATION_JSON).get(ClientResponse.class);

		PatientQueueDto patientQueueDtoResponse = response
				.getEntity(PatientQueueDto.class);
		// THEN
		Assert.assertEquals(patientQueueDtoResponse.getLive().size(), 5);
	}

}
