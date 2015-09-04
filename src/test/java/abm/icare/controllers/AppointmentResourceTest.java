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
import abm.icare.services.AppointmentService;
import abm.icare.utils.DateUtils;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;

public class AppointmentResourceTest extends AbstractResourceBaseConfig {

	private AppointmentResource appointmentResource;
	private AppointmentService mockAppointmentService;

	@BeforeClass
	public void setData() {
		mockAppointmentService = context.mock(AppointmentService.class);
		appointmentResource = new AppointmentResource();
		ReflectionTestUtils.setField(appointmentResource, "appointmentService",
				mockAppointmentService);
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

		context.checking(new Expectations() {
			{
				oneOf(mockAppointmentService).getAppointmentsFor(with(datedOn));
				will(returnValue(appointmentDtos));
			}
		});
		// WHEN
		ClientResponse response = resource().path(APPOINTMENT_FETCH_ALL)
				.queryParam("datedOn", datedOn.toString())
				.accept(MediaType.APPLICATION_JSON)
				.type(MediaType.APPLICATION_JSON).get(ClientResponse.class);

		List<AppointmentDto> appointments = response
				.getEntity(new GenericType<List<AppointmentDto>>() {
				});
		// THEN
		Assert.assertEquals(appointments.size(), 5);
	}

}
