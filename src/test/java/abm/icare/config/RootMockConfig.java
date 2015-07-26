package abm.icare.config;

import org.jmock.Mockery;

public interface RootMockConfig {

	Mockery context = new Mockery();
	String PATIENT_FIND_BY_ID = "patient/findbyid";
	String PATIENT_CREATE = "patient/create";

}
