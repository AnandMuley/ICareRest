package abm.icare.controllers;

import java.util.Collections;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import abm.icare.dtos.MedicineDto;
import abm.icare.exceptions.MedicineNotFoundException;
import abm.icare.exceptions.MedicineServiceException;
import abm.icare.services.MedicineService;

@Component
@Path("medicine")
public class MedicineResource {

	@Autowired
	private MedicineService medicineService;

	@GET
	@Path("searchbyname")
	@Produces(MediaType.APPLICATION_JSON)
	public Response searchByName(@QueryParam("name") String name) {
		List<MedicineDto> medicineDtos = Collections.emptyList();
		try {
			medicineDtos = medicineService.fetchBy(name);
		} catch (MedicineNotFoundException e) {
			return Response.status(Response.Status.NOT_FOUND).build();
		} catch (MedicineServiceException e) {
			return Response.serverError().build();
		}
		return Response.ok(medicineDtos).build();
	}

}
