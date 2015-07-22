package abm.icare.config;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

@ContextConfiguration(locations = { "classpath:config/spring-test-config.xml" })
public class SpringTestNGSupport extends AbstractTestNGSpringContextTests{

}
