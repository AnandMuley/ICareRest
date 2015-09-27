package abm.icare.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import abm.icare.beans.UserBean;
import abm.icare.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public boolean isAuthenticated(String username, String password) {
		boolean authenticated = false;
		UserBean userFound = userRepository.findByUsernameAndPassword(username,
				password);
		if (userFound != null) {
			authenticated = true;
		}
		return authenticated;
	}

}
