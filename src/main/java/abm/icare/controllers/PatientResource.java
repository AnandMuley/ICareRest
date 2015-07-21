package abm.icare.controllers;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import abm.icare.beans.Patient;

@Path("patient")
public class PatientResource {

	@GET
	@Path("find")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response findPatient(@QueryParam("id") String id) {
		Patient patient = new Patient();
		patient.setEmailId("rock@gmail.com");
		patient.setFirstName("Rock");
		patient.setId(id);
		patient.setLastName("Johnson");
		patient.setMiddleName("Albert");
		return Response.ok(patient).build();
	}

}
