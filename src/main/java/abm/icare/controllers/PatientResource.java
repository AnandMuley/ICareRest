package abm.icare.controllers;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import abm.icare.dtos.PatientDto;
import abm.icare.services.PatientService;

@Component
@Path("patient")
public class PatientResource {

	@Autowired
	private PatientService patientService;

	@GET
	@Path("findbyid")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response findPatient(@QueryParam("id") String id) {
		PatientDto patient = patientService.findById(id);
		return Response.ok(patient).build();
	}

	@PUT
	@Path("update")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updatePatient(PatientDto patientDto) {
		return Response.ok().build();
	}

}
