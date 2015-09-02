package abm.icare.beans;

import java.util.Date;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Document(collection = "appointments")
public class Appointment implements Comparable<Appointment> {

	@Id
	private String id;
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

	@Override
	public int compareTo(Appointment o) {
		return this.requestSubmittedOn.compareTo(o.requestSubmittedOn);
	}

	@Override
	public String toString() {
		return "Appointment [id=" + id + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", mobileNo=" + mobileNo
				+ ", emailId=" + emailId + ", datedOn=" + datedOn
				+ ", requestSubmittedOn=" + requestSubmittedOn + "]";
	}

}
