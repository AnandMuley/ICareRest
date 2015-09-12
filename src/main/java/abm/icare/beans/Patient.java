package abm.icare.beans;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Document(collection = "patients")
public class Patient implements Comparable<Patient> {

	@Id
	private String id;
	private String firstName;
	private String middleName;
	private String lastName;
	private long mobileNo;
	private String emailId;
	private String addrLine1;
	private String addrLine2;
	private String city;
	private String state;
	private String zipCode;
	@Transient
	private List<Visit> visits = new ArrayList<Visit>();

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

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
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

	public String getAddrLine1() {
		return addrLine1;
	}

	public void setAddrLine1(String addrLine1) {
		this.addrLine1 = addrLine1;
	}

	public String getAddrLine2() {
		return addrLine2;
	}

	public void setAddrLine2(String addrLine2) {
		this.addrLine2 = addrLine2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public List<Visit> getVisits() {
		return visits;
	}

	@Override
	public String toString() {
		return "Patient [id=" + id + ", firstName=" + firstName
				+ ", middleName=" + middleName + ", lastName=" + lastName
				+ ", mobileNo=" + mobileNo + ", emailId=" + emailId
				+ ", addrLine1=" + addrLine1 + ", addrLine2=" + addrLine2
				+ ", city=" + city + ", state=" + state + ", zipCode="
				+ zipCode + ", visits=" + visits + "]";
	}

	@Override
	public int compareTo(Patient o) {
		// TODO Auto-generated method stub
		return 0;
	}

}
