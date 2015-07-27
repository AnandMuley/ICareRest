package abm.icare.actions;

import org.hamcrest.Description;
import org.jmock.api.Action;
import org.jmock.api.Invocation;

import abm.icare.beans.Patient;

public class SavePatientAction implements Action {

	@Override
	public void describeTo(Description description) {
		description.appendText("Saving Patient");
	}

	@Override
	public Object invoke(Invocation invocation) throws Throwable {
		Patient patient = (Patient) invocation.getParameter(0);
		patient.setId("UID201");
		return patient;
	}

}
