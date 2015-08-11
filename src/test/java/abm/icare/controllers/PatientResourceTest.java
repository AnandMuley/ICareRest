package abm.icare.controllers;

import java.util.List;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jmock.Expectations;
import org.springframework.http.HttpStatus;
import org.springframework.test.util.ReflectionTestUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import abm.icare.config.AbstractResourceBaseConfig;
import abm.icare.constants.AppConstants;
import abm.icare.dataproviders.PatientDataProvider;
import abm.icare.dtos.PatientDto;
import abm.icare.exceptions.PatientNotFoundException;
import abm.icare.exceptions.PatientServiceException;
import abm.icare.services.PatientService;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;

public class PatientResourceTest extends AbstractResourceBaseConfig {

	private PatientService mockPatientService;

	private PatientResource patientResource;

	@BeforeClass
	public void initData() throws Exception {
		patientResource = new PatientResource();
		mockPatientService = context.mock(PatientService.class);
		ReflectionTestUtils.setField(patientResource, "patientService",
				mockPatientService);
		super.setUpData(patientResource);
	}

	@Test
	public void shouldReturnPatient() throws PatientServiceException {
		// GIVEN
		final List<PatientDto> patientDtos = PatientDataProvider
				.createPatientDtos();
		final String name = "Rock";

		context.checking(new Expectations() {
			{
				oneOf(mockPatientService).findByName(with(name));
				will(returnValue(patientDtos));
			}
		});
		// WHEN
		WebResource webResource = resource().path(PATIENT_FIND_BY_NAME)
				.queryParam("name", name);
		ClientResponse response = webResource.get(ClientResponse.class);
		List<PatientDto> actual = response
				.getEntity(new GenericType<List<PatientDto>>() {
				});

		// THEN
		Assert.assertEquals(response.getStatus(), 200);
		Assert.assertEquals(actual.size(), 4);
	}

	@Test
	public void shouldReturnNotFoundStatus() throws PatientServiceException {
		// GIVEN
		final String name = "Rock";

		context.checking(new Expectations() {
			{
				oneOf(mockPatientService).findByName(with(name));
				will(throwException(new PatientNotFoundException(
						AppConstants.EXCEPTION_PATIENT_NOT_FOUND)));
			}
		});
		// WHEN
		WebResource webResource = resource().path(PATIENT_FIND_BY_NAME)
				.queryParam("name", name);
		ClientResponse response = webResource.get(ClientResponse.class);

		// THEN
		Assert.assertEquals(response.getStatus(),
				Response.Status.NOT_FOUND.getStatusCode());
	}

	@Test
	public void shouldReturnInternalServerError()
			throws PatientServiceException {
		// GIVEN
		final String name = "Rock";

		context.checking(new Expectations() {
			{
				oneOf(mockPatientService).findByName(with(name));
				will(throwException(new PatientServiceException(
						"Some error occured")));
			}
		});
		// WHEN
		WebResource webResource = resource().path(PATIENT_FIND_BY_NAME)
				.queryParam("name", name);
		ClientResponse response = webResource.get(ClientResponse.class);

		// THEN
		Assert.assertEquals(response.getStatus(),
				Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
	}

	@Test
	public void shouldCreatePatient() {
		// GIVEN
		final PatientDto patientDto = PatientDataProvider.createPatientDto();
		context.checking(new Expectations() {
			{
				oneOf(mockPatientService).createPatient(with(patientDto));
				will(returnValue(null));
			}
		});
		// WHEN
		ClientResponse response = resource().path(PATIENT_CREATE)
				.type(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.post(ClientResponse.class, patientDto);

		// THEN
		Assert.assertEquals(response.getStatus(),
				Response.Status.CREATED.getStatusCode());
	}

	@Test
	public void shouldUpdatePatient() {
		// GIVEN
		final PatientDto patientDto = PatientDataProvider.createPatientDto();

		context.checking(new Expectations() {
			{
				oneOf(mockPatientService).update(with(patientDto));
			}
		});
		// WHEN
		ClientResponse response = resource().path(PATIENT_UPDATE)
				.type(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.put(ClientResponse.class, patientDto);

		// THEN
		Assert.assertEquals(response.getStatus(), HttpStatus.OK.value());
	}
}
