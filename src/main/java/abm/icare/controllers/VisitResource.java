package abm.icare.controllers;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import abm.icare.dtos.VisitDto;
import abm.icare.services.VisitService;

@Path("visit")
public class VisitResource {

	@Autowired
	private VisitService visitService;

	@POST
	@Path("create")
	public Response createVisit(VisitDto visitDto) {
		visitService.create(visitDto);
		return Response.status(Response.Status.CREATED).build();
	}

}
