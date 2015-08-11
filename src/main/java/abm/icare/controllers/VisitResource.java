package abm.icare.controllers;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import abm.icare.dtos.VisitDto;

@Path("visit")
public class VisitResource {

	@POST
	@Path("create")
	public Response createVisit(VisitDto visitDto){
		return Response.ok().build();
	}
	

}
