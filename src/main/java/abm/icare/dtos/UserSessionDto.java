package abm.icare.dtos;

import org.springframework.stereotype.Component;

@Component
public class UserSessionDto {

	private boolean loggedIn;
	private String role;

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "UserSessionDto [loggedIn=" + loggedIn + ", role=" + role + "]";
	}

}
