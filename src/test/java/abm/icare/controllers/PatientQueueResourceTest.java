package abm.icare.controllers;

import javax.ws.rs.core.MediaType;

import org.jmock.Expectations;
import org.springframework.test.util.ReflectionTestUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import abm.icare.beans.PatientQueue;
import abm.icare.config.AbstractResourceBaseConfig;
import abm.icare.dataproviders.AppointmentDataProvider;
import abm.icare.services.PatientQueueService;

import com.sun.jersey.api.client.ClientResponse;

public class PatientQueueResourceTest extends AbstractResourceBaseConfig {

	private PatientQueueService mockPatientQueueService;

	private PatientQueueResource patientQueueResource;

	@BeforeClass
	public void setData() {
		mockPatientQueueService = context.mock(PatientQueueService.class);
		patientQueueResource = new PatientQueueResource();
		ReflectionTestUtils.setField(patientQueueResource,
				"patientQueueService", mockPatientQueueService);
		super.setUpData(patientQueueResource);
	}

	@Test
	public void shouldPutPatientOnHold() throws InterruptedException {
		// GIVEN
		final String queueId = "55ae222344zebfcdd19d7721";
		final String appointmentId = "55ae222312zebfcdd19d7722";
		final PatientQueue updatedPatientQueue = AppointmentDataProvider
				.puttingAPatientOnHold();

		context.checking(new Expectations() {
			{
				oneOf(mockPatientQueueService)
						.putOnHold(queueId, appointmentId);
				will(returnValue(updatedPatientQueue));
			}
		});
		// WHEN
		ClientResponse response = resource().path(PATIENT_QUEUE_PUT_ONHOLD)
				.queryParam("qid", queueId).queryParam("aid", appointmentId)
				.accept(MediaType.APPLICATION_JSON)
				.type(MediaType.APPLICATION_JSON).put(ClientResponse.class);

		PatientQueue patientQueue = response.getEntity(PatientQueue.class);

		// THEN
		Assert.assertEquals(response.getStatus(), 200);
		Assert.assertEquals(patientQueue.getOnhold().size(), 1);
		Assert.assertEquals(patientQueue.getOnhold().iterator().next().getId(),
				appointmentId);

	}

	@Test
	public void shouldMoveAPatientToLiveQueue() throws InterruptedException {
		// GIVEN
		final String queueId = "55ae222344zebfcdd19d7721";
		final String appointmentId = "55ae228044webfcdd19d7720";
		final PatientQueue updatedPatientQueue = AppointmentDataProvider
				.movingPatientToLiveQueue();

		context.checking(new Expectations() {
			{
				oneOf(mockPatientQueueService)
						.moveToLive(queueId, appointmentId);
				will(returnValue(updatedPatientQueue));
			}
		});
		// WHEN
		ClientResponse response = resource().path(PATIENT_QUEUE_MOVE_TO_LIVE)
				.queryParam("qid", queueId).queryParam("aid", appointmentId)
				.accept(MediaType.APPLICATION_JSON)
				.type(MediaType.APPLICATION_JSON).put(ClientResponse.class);

		PatientQueue patientQueue = response.getEntity(PatientQueue.class);

		// THEN
		Assert.assertEquals(response.getStatus(), 200);
		Assert.assertEquals(patientQueue.getLive().size(), 1);
		Assert.assertEquals(patientQueue.getLive().iterator().next().getId(),
				appointmentId);

	}

}
