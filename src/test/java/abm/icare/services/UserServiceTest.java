package abm.icare.services;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.springframework.test.util.ReflectionTestUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import abm.icare.beans.UserBean;
import abm.icare.config.SpringTestNGSupport;
import abm.icare.repositories.UserRepository;

public class UserServiceTest extends SpringTestNGSupport {

	private UserRepository mockUserRepository;
	private Mockery context;
	private UserService userService;

	@BeforeClass
	public void setUpData() {
		context = new Mockery();
		mockUserRepository = context.mock(UserRepository.class);
		userService = new UserService();
		ReflectionTestUtils.setField(userService, "userRepository",
				mockUserRepository);
	}

	@Test
	public void shouldReturnTrueIfAuthenticated() {
		// GIVEN
		final UserBean userBean = new UserBean();
		userBean.setPassword("rock@123");
		userBean.setUsername("rock");

		context.checking(new Expectations() {
			{
				oneOf(mockUserRepository).findByUsernameAndPassword(
						userBean.getUsername(), userBean.getPassword());
				will(returnValue(userBean));
			}
		});
		// WHEN
		boolean authenticated = userService.isAuthenticated(
				userBean.getUsername(), userBean.getPassword());

		// THEN
		Assert.assertTrue(authenticated, "User is not registered");
	}

	@Test
	public void shouldReturnFalseIfNotAuthenticated() {
		// GIVEN
		final UserBean userBean = new UserBean();
		userBean.setPassword("rock@123");
		userBean.setUsername("rock");

		context.checking(new Expectations() {
			{
				oneOf(mockUserRepository).findByUsernameAndPassword(
						userBean.getUsername(), userBean.getPassword());
				will(returnValue(null));
			}
		});
		// WHEN
		boolean authenticated = userService.isAuthenticated(
				userBean.getUsername(), userBean.getPassword());

		// THEN
		Assert.assertFalse(authenticated, "User is registered");
	}

}
