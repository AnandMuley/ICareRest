package abm.icare.dtos;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

@Component
public class VisitDto {

	private String id;
	private String patientId;
	private Set<String> symptoms = new HashSet<String>();
	private Set<String> allergies = new HashSet<String>();
	private Set<String> prescriptions = new HashSet<String>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public Set<String> getSymptoms() {
		return symptoms;
	}

	public Set<String> getAllergies() {
		return allergies;
	}

	public Set<String> getPrescriptions() {
		return prescriptions;
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
		VisitDto other = (VisitDto) obj;
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
		if (symptoms == null) {
			if (other.symptoms != null)
				return false;
		} else if (!symptoms.equals(other.symptoms))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "VisitDto [id=" + id + ", patientId=" + patientId
				+ ", symptoms=" + symptoms + ", allergies=" + allergies + "]";
	}

}
