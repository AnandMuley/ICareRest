package abm.icare.repositories;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import abm.icare.beans.Appointment;
import abm.icare.beans.PatientQueue;
import abm.icare.config.SpringTestNGSupport;

public class PatientQueueRepositoryTest extends SpringTestNGSupport {

	@Autowired
	private PatientQueueRepository patientQueueRepository;

	private String queueId;

	@BeforeMethod
	public void setUpData() throws InterruptedException {
		Appointment arjun = new Appointment();
		arjun.setId("PID101");
		arjun.setEmailId("arjun@gmail.com");
		arjun.setFirstName("Arjun");
		arjun.setLastName("Pandit");
		arjun.setMobileNo(7890098700l);
		arjun.setRequestSubmittedOn(new Date());

		TimeUnit.SECONDS.sleep(2);

		Appointment rahul = new Appointment();
		rahul.setId("PID102");
		rahul.setEmailId("rahul@gmail.com");
		rahul.setFirstName("Rahul");
		rahul.setLastName("Verma");
		rahul.setMobileNo(7890098700l);
		rahul.setRequestSubmittedOn(new Date());

		PatientQueue queue = new PatientQueue();
		queue.getLive().add(arjun);
		queue.getLive().add(rahul);
		patientQueueRepository.insert(queue);
		queueId = queue.getId();
	}

	@AfterMethod
	public void cleanUp() {
		patientQueueRepository.deleteAll();
	}

	@Test
	public void shouldMovePatientToOnHoldQueue() {
		// GIVEN
		PatientQueue patientQueue = patientQueueRepository.findOne(queueId);

		// WHEN
		Appointment appointment = patientQueue.getLive().iterator().next();
		patientQueue.getLive().remove(appointment);
		patientQueue.getOnhold().add(appointment);
		patientQueueRepository.save(patientQueue);
		PatientQueue updatedQueue = patientQueueRepository.findOne(queueId);

		// THEN
		Assert.assertEquals(updatedQueue.getOnhold().size(), 1);

	}

}
