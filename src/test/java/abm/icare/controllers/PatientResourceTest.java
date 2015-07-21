package abm.icare.controllers;

import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import abm.icare.beans.Patient;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.test.framework.JerseyTest;

public class PatientResourceTest extends JerseyTest {

	public PatientResourceTest() {
		super("abm.icare.controllers");
	}

	@BeforeClass
	public void setUp() throws Exception {
		super.setUp();
	}

	@AfterClass
	public void tearDown() throws Exception {
		super.tearDown();
	}

	@Test
	public void shouldReturnPatient() {
		// GIVEN

		// WHEN
		WebResource webResource = resource().path("patient/find").queryParam("id",
				"UID101");
		ClientResponse response = webResource.get(ClientResponse.class);
		Patient patient = response.getEntity(Patient.class);

		// THEN
		assertEquals(response.getStatus(), 200);
		assertEquals(patient.getEmailId(), "rock@gmail.com");
		assertEquals(patient.getFirstName(), "Rock");
		assertEquals(patient.getId(), "UID101");
		assertEquals(patient.getLastName(), "Johnson");
		assertEquals(patient.getMiddleName(), "Albert");
	}
}
