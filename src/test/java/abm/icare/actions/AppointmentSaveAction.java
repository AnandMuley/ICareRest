package abm.icare.actions;

import org.hamcrest.Description;
import org.jmock.api.Action;
import org.jmock.api.Invocation;

import abm.icare.beans.Appointment;

public class AppointmentSaveAction implements Action {

	@Override
	public void describeTo(Description description) {
		description.appendText("Saving Appointment");
	}

	@Override
	public Object invoke(Invocation invocation) throws Throwable {
		Appointment appointment = (Appointment) invocation.getParameter(0);
		appointment.setId("APO101");
		return appointment;
	}

}
