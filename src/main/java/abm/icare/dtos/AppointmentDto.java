package abm.icare.dtos;

import java.util.Date;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class AppointmentDto {

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

}
