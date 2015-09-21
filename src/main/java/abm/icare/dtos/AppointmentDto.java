package abm.icare.dtos;

import java.util.Date;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@JsonIgnoreProperties({ "message", "isSuccess","onholdqueue" })
public class AppointmentDto implements Comparable<AppointmentDto> {

	private String id;
	private String name;
	private String firstName;
	private String lastName;
	private long mobileNo;
	private String emailId;
	private String datedOn;
	private Date requestSubmittedOn;

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

	public Date getRequestSubmittedOn() {
		return requestSubmittedOn;
	}

	public void setRequestSubmittedOn(Date requestSubmittedOn) {
		this.requestSubmittedOn = requestSubmittedOn;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(long mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((datedOn == null) ? 0 : datedOn.hashCode());
		result = prime * result + ((emailId == null) ? 0 : emailId.hashCode());
		result = prime * result
				+ ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + (int) (mobileNo ^ (mobileNo >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime
				* result
				+ ((requestSubmittedOn == null) ? 0 : requestSubmittedOn
						.hashCode());
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
		AppointmentDto other = (AppointmentDto) obj;
		if (datedOn == null) {
			if (other.datedOn != null)
				return false;
		} else if (!datedOn.equals(other.datedOn))
			return false;
		if (emailId == null) {
			if (other.emailId != null)
				return false;
		} else if (!emailId.equals(other.emailId))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (mobileNo != other.mobileNo)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (requestSubmittedOn == null) {
			if (other.requestSubmittedOn != null)
				return false;
		} else if (!requestSubmittedOn.equals(other.requestSubmittedOn))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AppointmentDto [id=" + id + ", name=" + name + ", firstName="
				+ firstName + ", lastName=" + lastName + ", mobileNo="
				+ mobileNo + ", emailId=" + emailId + ", datedOn=" + datedOn
				+ ", requestSubmittedOn=" + requestSubmittedOn + "]";
	}

	@Override
	public int compareTo(AppointmentDto o) {
		return this.requestSubmittedOn.compareTo(o.requestSubmittedOn);
	}

}
