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
import org.springframework.http.HttpStatus;

import abm.icare.dtos.VisitDto;
import abm.icare.exceptions.VisitServiceException;
import abm.icare.services.VisitService;

@Path("visit")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class VisitResource {

	@Autowired
	private VisitService visitService;

	@POST
	@Path("create")
	public Response createVisit(VisitDto visitDto) {
		visitService.create(visitDto);
		return Response.status(Response.Status.CREATED).build();
	}

	@PUT
	@Path("update")
	public Response updateVisit(VisitDto visitDto) {
		try {
			visitService.update(visitDto);
		} catch (VisitServiceException e) {
			return Response.status(HttpStatus.BAD_REQUEST.value()).build();
		}
		return Response.ok().build();
	}

	@GET
	@Path("findall")
	public Response findAllBy(@QueryParam("pid") String patientId) {
		List<VisitDto> visits = visitService.findAllVisitsOf(patientId);
		return Response.ok(visits).build();
	}

}
