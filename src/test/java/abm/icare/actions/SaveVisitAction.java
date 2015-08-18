package abm.icare.actions;

import org.hamcrest.Description;
import org.jmock.api.Action;
import org.jmock.api.Invocation;

import abm.icare.beans.Visit;

public class SaveVisitAction implements Action {

	@Override
	public void describeTo(Description description) {
		description.appendText("Saving Visit...");
	}

	@Override
	public Object invoke(Invocation invocation) throws Throwable {
		Visit visit = (Visit) invocation.getParameter(0);
		visit.setId("VID101");
		return visit;
	}

}
