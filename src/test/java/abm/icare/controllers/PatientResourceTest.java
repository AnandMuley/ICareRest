package abm.icare.controllers;

import java.util.List;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jmock.Expectations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.test.util.ReflectionTestUtils;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import abm.icare.config.RootMockConfig;
import abm.icare.dataproviders.PatientDataProvider;
import abm.icare.dtos.PatientDto;
import abm.icare.services.PatientService;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.core.DefaultResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;
import com.sun.jersey.api.json.JSONConfiguration;
import com.sun.jersey.test.framework.AppDescriptor;
import com.sun.jersey.test.framework.JerseyTest;
import com.sun.jersey.test.framework.LowLevelAppDescriptor;

public class PatientResourceTest extends JerseyTest implements RootMockConfig {

	private PatientService mockPatientService;
	private LowLevelAppDescriptor appDescriptor;

	@Autowired
	private PatientResource patientResource;

	@BeforeClass
	public void init() throws Exception {
		patientResource = new PatientResource();
		mockPatientService = context.mock(PatientService.class);
		ReflectionTestUtils.setField(patientResource, "patientService",
				mockPatientService);
		ResourceConfig config = appDescriptor.getResourceConfig();
		config.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING,
				Boolean.TRUE);
		config.getSingletons().add(patientResource);
		super.setUp();
	}

	@Override
	protected AppDescriptor configure() {
		appDescriptor = new LowLevelAppDescriptor.Builder(
				new DefaultResourceConfig()).build();
		return appDescriptor;
	}

	@AfterClass
	public void tearDown() throws Exception {
		super.tearDown();
	}

	@Test
	public void shouldReturnPatient() {
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
	public void shouldReturnNotFoundStatus() {
		// GIVEN
		final String name = "Rock";

		context.checking(new Expectations() {
			{
				oneOf(mockPatientService).findByName(with(name));
				will(returnValue(null));
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
