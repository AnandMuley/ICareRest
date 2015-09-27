package abm.icare.controllers;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import abm.icare.services.UserService;

@Component
@Path("user")
public class UserResource {

	@Autowired
	private UserService userService;

	@GET
	@Path("authenticate")
	@Produces(MediaType.APPLICATION_JSON)
	public Response isAuthenticated(@QueryParam("uname") String username,
			@QueryParam("pwd") String password) {
		boolean authentic = userService.isAuthenticated(username, password);
		return Response.ok(authentic).build();
	}

}
