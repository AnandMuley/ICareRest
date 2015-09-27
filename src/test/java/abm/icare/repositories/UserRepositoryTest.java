package abm.icare.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import abm.icare.beans.UserBean;
import abm.icare.config.SpringTestNGSupport;
import abm.icare.constants.UserRole;

public class UserRepositoryTest extends SpringTestNGSupport {

	@Autowired
	private UserRepository userRepository;

	@BeforeClass
	public void setUpData() {
		UserBean userBean = new UserBean();
		userBean.setPassword("Rock@123");
		userBean.setUsername("rock");
		userBean.setUserRole(UserRole.DOCTOR);
		userRepository.save(userBean);
	}

	@Test
	public void shouldFindUserForAuthentication() {
		// GIVEN
		final String username = "rock";
		final String password = "Rock@123";

		// WHEN
		UserBean userBean = userRepository.findByUsernameAndPassword(username,
				password);

		// THEN
		Assert.assertNotNull(userBean);
		Assert.assertEquals(userBean.getUserRole(), UserRole.DOCTOR);
	}

}
