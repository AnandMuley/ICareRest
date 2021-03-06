package abm.icare.beans;

import java.util.TreeSet;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Document(collection = "patientqueue")
public class PatientQueue {

	@Id
	private String id;
	private String datedOn;
	private TreeSet<Appointment> live = new TreeSet<Appointment>();
	private TreeSet<Appointment> onhold = new TreeSet<Appointment>();

	public TreeSet<Appointment> getLive() {
		return live;
	}

	public TreeSet<Appointment> getOnhold() {
		return onhold;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDatedOn() {
		return datedOn;
	}

	public void setDatedOn(String datedOn) {
		this.datedOn = datedOn;
	}

	@Override
	public String toString() {
		return "PatientQueue [id=" + id + ", live=" + live + ", onhold="
				+ onhold + "]";
	}

}
