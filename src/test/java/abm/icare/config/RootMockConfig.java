package abm.icare.config;

import org.jmock.Mockery;

public interface RootMockConfig {

	Mockery context = new Mockery();
	String PATIENT_FIND_BY_NAME = "patient/findbyname";
	String PATIENT_CREATE = "patient/create";
	String PATIENT_UPDATE = "patient/update";

}
