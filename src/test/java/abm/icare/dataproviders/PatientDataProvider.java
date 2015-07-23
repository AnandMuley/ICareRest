package abm.icare.dataproviders;

import org.testng.annotations.DataProvider;

import abm.icare.beans.Patient;

public class PatientDataProvider {

	@DataProvider(name = "validPatient")
	public static Object[][] getValidPatient() {
		final Patient patient = new Patient();
		patient.setEmailId("rock@gmail.com");
		patient.setFirstName("Rock");
		patient.setId("ID101");
		patient.setLastName("Johnson");
		patient.setMiddleName("Albert");
		return new Object[][] { { patient } };
	}

}
