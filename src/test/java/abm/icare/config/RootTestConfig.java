package abm.icare.config;

import org.jmock.Mockery;
import org.junit.BeforeClass;

public class RootTestConfig {

	protected Mockery context;

	@BeforeClass
	public void init() {
		context = new Mockery();
	}

}
