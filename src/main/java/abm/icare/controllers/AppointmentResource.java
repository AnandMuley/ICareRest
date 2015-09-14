package abm.icare.controllers;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import abm.icare.dtos.AppointmentDto;
import abm.icare.dtos.PatientQueueDto;
import abm.icare.services.AppointmentService;
import abm.icare.services.PatientQueueService;

@Component
@Path("appointment")
@Produces(MediaType.APPLICATION_JSON)
public class AppointmentResource {

	@Autowired
	private AppointmentService appointmentService;

	@Autowired
	private PatientQueueService patientQueueService;

	@POST
	@Path("create")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createNew(AppointmentDto appointmentDto) {
		appointmentService.create(appointmentDto);
		return Response.ok().build();
	}

	@GET
	@Path("findby")
	public Response fetchBy(@QueryParam("qid") String qid,
			@QueryParam("datedOn") String datedOn) {
		PatientQueueDto patientQueueDto = patientQueueService.findBy(qid,
				datedOn);
		if (patientQueueDto == null) {
			List<AppointmentDto> appointmentDtos = appointmentService
					.getAppointmentsFor(datedOn);
			patientQueueDto = patientQueueService.createNew(appointmentDtos,
					datedOn);
		}
		return Response.ok(patientQueueDto).build();
	}

}
