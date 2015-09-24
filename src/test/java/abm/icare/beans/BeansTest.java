package abm.icare.beans;

import java.util.Collections;

import org.testng.Assert;
import org.testng.annotations.Test;

public class BeansTest {

	@Test
	public void allBeansShouldHaveToString() {
		// MEDICINE
		Medicine medicine = new Medicine();
		Assert.assertEquals(medicine.toString(), "Medicine [id=" + null
				+ ", name=" + null + "]");

		// APPOINTMENT
		Appointment appointment = new Appointment();
		Assert.assertEquals(appointment.toString(), "Appointment [id=" + null
				+ ", firstName=" + null + ", lastName=" + null + ", mobileNo="
				+ 0 + ", emailId=" + null + ", datedOn=" + null
				+ ", requestSubmittedOn=" + null + "]");

		// PATIENT
		Patient patient = new Patient();
		Assert.assertEquals(patient.toString(), "Patient [id=" + null
				+ ", firstName=" + null + ", middleName=" + null
				+ ", lastName=" + null + ", mobileNo=" + 0 + ", emailId="
				+ null + ", addrLine1=" + null + ", addrLine2=" + null
				+ ", city=" + null + ", state=" + null + ", zipCode=" + null
				+ ", visits=" + patient.getVisits() + "]");

		// PATIENT QUEUE
		PatientQueue patientQueue = new PatientQueue();
		Assert.assertEquals(patientQueue.toString(), "PatientQueue [id=" + null
				+ ", live=" + Collections.EMPTY_LIST + ", onhold="
				+ Collections.EMPTY_LIST + "]");

		// VISIT
		Visit visit = new Visit();
		Assert.assertEquals(visit.toString(), "Visit [id=" + null
				+ ", symptoms=" + null + ", allergies=" + null
				+ ", prescriptions=" + null + ", patientId=" + null
				+ ", visitedOn=" + null + "]");
	}

	@Test
	public void shouldCompareTwoPatientBeansObject() {
		// GIVEN
		Patient patient1 = new Patient();
		patient1.setId("a");
		Patient patient2 = new Patient();
		patient2.setId("b");

		// WHEN
		int result = patient1.compareTo(patient2);

		// THEN
		Assert.assertEquals(result, -1);
	}

}
