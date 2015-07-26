package abm.icare.controllers;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jmock.Expectations;
import org.springframework.beans.factory.annotation.Autowired;
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
		final PatientDto patientDto = PatientDataProvider.createPatient();
		final String uid = "UID101";
		patientDto.setId("UID201");

		context.checking(new Expectations() {
			{
				oneOf(mockPatientService).findById(with(uid));
				will(returnValue(patientDto));
			}
		});
		// WHEN
		WebResource webResource = resource().path(PATIENT_FIND_BY_ID)
				.queryParam("id", uid);
		ClientResponse response = webResource.get(ClientResponse.class);
		PatientDto actual = response.getEntity(PatientDto.class);

		// THEN
		Assert.assertEquals(response.getStatus(), 200);
		Assert.assertEquals(actual.getEmailId(), "rock@gmail.com");
		Assert.assertEquals(actual.getFirstName(), "Rock");
		Assert.assertEquals(actual.getId(), "UID201");
		Assert.assertEquals(actual.getLastName(), "Johnson");
		Assert.assertEquals(actual.getMiddleName(), "Albert");
	}

	@Test
	public void shouldReturnNotFoundStatus() {
		// GIVEN
		final String uid = "UID101";

		context.checking(new Expectations() {
			{
				oneOf(mockPatientService).findById(with(uid));
				will(returnValue(null));
			}
		});
		// WHEN
		WebResource webResource = resource().path(PATIENT_FIND_BY_ID)
				.queryParam("id", uid);
		ClientResponse response = webResource.get(ClientResponse.class);

		// THEN
		Assert.assertEquals(response.getStatus(),
				Response.Status.NOT_FOUND.getStatusCode());
	}

	@Test
	public void shouldCreatePatient() {
		// GIVEN
		final PatientDto patientDto = PatientDataProvider.createPatient();
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
		// assertEquals(response.getStatus(),
		// Response.Status.CREATED.getStatusCode());
	}
}
