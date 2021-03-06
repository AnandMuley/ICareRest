package abm.icare.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import abm.icare.dtos.UserSessionDto;
import abm.icare.services.SessionManagerService;
import abm.icare.services.UserService;

@Component
@Path("user")
public class UserResource {

	@Autowired
	private UserService userService;

	@Autowired
	private ApplicationContext context;

	@Autowired
	private SessionManagerService sessionManagerService;

	@GET
	@Path("authenticate")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response isAuthenticated(@QueryParam("uname") String username,
			@QueryParam("pwd") String password,
			@Context HttpServletRequest httpServletRequest) {
		boolean authorized = userService.isAuthenticated(username, password);
		UserSessionDto sessionDto = context.getBean(UserSessionDto.class);
		if (authorized) {
			httpServletRequest.getSession(true).setAttribute("sessionDto",
					sessionDto);
		}
		return Response.ok(sessionDto).build();
	}
}
