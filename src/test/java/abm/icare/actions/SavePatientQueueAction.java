package abm.icare.actions;

import org.hamcrest.Description;
import org.jmock.api.Action;
import org.jmock.api.Invocation;

import abm.icare.beans.PatientQueue;

public class SavePatientQueueAction implements Action {

	@Override
	public void describeTo(Description description) {
		description.appendText("Saving Patient Queue");
	}

	@Override
	public Object invoke(Invocation invocation) throws Throwable {
		PatientQueue patientQueue = (PatientQueue) invocation.getParameter(0);
		patientQueue.setId("PID101");
		return patientQueue;
	}

}
