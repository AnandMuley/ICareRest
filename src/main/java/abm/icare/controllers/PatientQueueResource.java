package abm.icare.controllers;

import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import abm.icare.beans.PatientQueue;
import abm.icare.services.PatientQueueService;

@Component
@Path("queue")
@Produces(MediaType.APPLICATION_JSON)
public class PatientQueueResource {

	@Autowired
	private PatientQueueService patientQueueService;

	@PUT
	@Path("putonhold")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response putOnHold(@QueryParam("qid") String queueId,
			@QueryParam("aid") String appointmentId) {
		PatientQueue updatedQueue = patientQueueService.putOnHold(queueId,
				appointmentId);
		return Response.ok(updatedQueue).build();
	}

	@PUT
	@Path("movetolive")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response moveToLive(@QueryParam("qid") String queueId,
			@QueryParam("aid") String appointmentId) {
		PatientQueue patientQueue = patientQueueService.moveToLive(queueId,
				appointmentId);
		return Response.ok(patientQueue).build();
	}
}
