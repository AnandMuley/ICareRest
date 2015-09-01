package abm.icare.beans;

import java.util.Date;
import java.util.Set;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Document(collection = "visits")
public class Visit {

	@Id
	private String id;
	private String symptoms;
	private String allergies;
	private Set<String> prescriptions;
	private String patientId;
	private Date visitedOn;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSymptoms() {
		return symptoms;
	}

	public void setSymptoms(String symptoms) {
		this.symptoms = symptoms;
	}

	public String getAllergies() {
		return allergies;
	}

	public void setAllergies(String allergies) {
		this.allergies = allergies;
	}

	public Set<String> getPrescriptions() {
		return prescriptions;
	}

	public void setPrescriptions(Set<String> prescriptions) {
		this.prescriptions = prescriptions;
	}

	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public Date getVisitedOn() {
		return visitedOn;
	}

	public void setVisitedOn(Date visitedOn) {
		this.visitedOn = visitedOn;
	}

	@Override
	public String toString() {
		return "Visit [id=" + id + ", symptoms=" + symptoms + ", allergies="
				+ allergies + ", prescriptions=" + prescriptions
				+ ", patientId=" + patientId + ", visitedOn=" + visitedOn + "]";
	}

}
