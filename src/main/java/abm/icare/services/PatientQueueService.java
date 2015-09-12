package abm.icare.services;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import abm.icare.beans.Appointment;
import abm.icare.beans.PatientQueue;
import abm.icare.repositories.PatientQueueRepository;

@Service
public class PatientQueueService {

	@Autowired
	private PatientQueueRepository patientQueueRepository;

	public PatientQueue putOnHold(String queueId, String appointmentId) {
		PatientQueue patientQueue = patientQueueRepository.findOne(queueId);
		Iterator<Appointment> iterator = patientQueue.getLive().iterator();
		while (iterator.hasNext()) {
			Appointment appointment = iterator.next();
			if (appointment.getId().equalsIgnoreCase(appointmentId)) {
				patientQueue.getLive().remove(appointment);
				patientQueue.getOnhold().add(appointment);
				break;
			}
		}
		return patientQueue;
	}

	public PatientQueue moveToLive(String queueId, String appointmentId) {
		PatientQueue patientQueue = patientQueueRepository.findOne(queueId);
		Iterator<Appointment> iterator = patientQueue.getOnhold().iterator();
		while (iterator.hasNext()) {
			Appointment appointment = iterator.next();
			if (appointment.getId().equalsIgnoreCase(appointmentId)) {
				patientQueue.getOnhold().remove(appointment);
				patientQueue.getLive().add(appointment);
				break;
			}
		}
		return patientQueue;
	}

}
