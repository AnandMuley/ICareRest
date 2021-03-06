package abm.icare.controllers;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import abm.icare.dtos.PatientDto;
import abm.icare.exceptions.PatientNotFoundException;
import abm.icare.exceptions.PatientServiceException;
import abm.icare.services.PatientService;

@Component
@Path("patient")
public class PatientResource {

	@Autowired
	private PatientService patientService;

	@GET
	@Path("findbyname")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response findPatient(@QueryParam("name") String name) {
		List<PatientDto> patients;
		try {
			patients = patientService.findByName(name);
		} catch (PatientNotFoundException e) {
			return Response.status(Response.Status.NOT_FOUND).build();
		} catch (PatientServiceException e) {
			return Response.serverError().build();
		}
		return Response.ok(patients).build();
	}

	@POST
	@Path("create")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createPatient(PatientDto patientDto) {
		patientService.createPatient(patientDto);
		return Response.status(Response.Status.CREATED).build();
	}

	@PUT
	@Path("update")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updatePatient(PatientDto patientDto) {
		patientService.update(patientDto);
		return Response.ok().build();
	}

}
