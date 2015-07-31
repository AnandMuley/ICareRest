package abm.icare.beans;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "visits")
public class Visit {

	@Id
	private String id;
	private List<String> symptoms;
	private List<String> allergies;
	private List<String> prescriptions;
	private String patientId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<String> getSymptoms() {
		return symptoms;
	}

	public void setSymptoms(List<String> symptoms) {
		this.symptoms = symptoms;
	}

	public List<String> getAllergies() {
		return allergies;
	}

	public void setAllergies(List<String> allergies) {
		this.allergies = allergies;
	}

	public List<String> getPrescriptions() {
		return prescriptions;
	}

	public void setPrescriptions(List<String> prescriptions) {
		this.prescriptions = prescriptions;
	}

	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((allergies == null) ? 0 : allergies.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((patientId == null) ? 0 : patientId.hashCode());
		result = prime * result
				+ ((prescriptions == null) ? 0 : prescriptions.hashCode());
		result = prime * result
				+ ((symptoms == null) ? 0 : symptoms.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Visit other = (Visit) obj;
		if (allergies == null) {
			if (other.allergies != null)
				return false;
		} else if (!allergies.equals(other.allergies))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (patientId == null) {
			if (other.patientId != null)
				return false;
		} else if (!patientId.equals(other.patientId))
			return false;
		if (prescriptions == null) {
			if (other.prescriptions != null)
				return false;
		} else if (!prescriptions.equals(other.prescriptions))
			return false;
		if (symptoms == null) {
			if (other.symptoms != null)
				return false;
		} else if (!symptoms.equals(other.symptoms))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Visit [id=" + id + ", symptoms=" + symptoms + ", allergies="
				+ allergies + ", prescriptions=" + prescriptions
				+ ", patientId=" + patientId + "]";
	}

}
