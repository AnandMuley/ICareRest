package abm.icare.config;

import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.sun.jersey.api.core.DefaultResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;
import com.sun.jersey.api.json.JSONConfiguration;
import com.sun.jersey.test.framework.AppDescriptor;
import com.sun.jersey.test.framework.JerseyTest;
import com.sun.jersey.test.framework.LowLevelAppDescriptor;

public abstract class AbstractResourceBaseConfig extends JerseyTest implements
		RootMockConfig {

	protected Mockery context = new Mockery();
	protected LowLevelAppDescriptor appDescriptor;

	@BeforeClass
	public void init() throws Exception {
		// This configuration is required for mocking a class
		context.setImposteriser(ClassImposteriser.INSTANCE);

	}

	@Override
	protected AppDescriptor configure() {
		appDescriptor = new LowLevelAppDescriptor.Builder(
				new DefaultResourceConfig()).build();
		return appDescriptor;
	}

	@AfterClass
	public void tearDown() throws Exception {
		super.tearDown();
	}

	public void setUpData(Object resource) {
		try {
			ResourceConfig config = appDescriptor.getResourceConfig();
			config.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING,
					Boolean.TRUE);
			config.getSingletons().add(resource);

			super.setUp();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
