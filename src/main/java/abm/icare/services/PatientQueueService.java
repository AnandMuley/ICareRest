package abm.icare.services;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import abm.icare.beans.Appointment;
import abm.icare.beans.PatientQueue;
import abm.icare.dtos.AppointmentDto;
import abm.icare.dtos.PatientQueueDto;
import abm.icare.repositories.PatientQueueRepository;
import abm.icare.utils.BeanCreatorUtil;
import abm.icare.utils.DtoCreatorUtil;

@Service
public class PatientQueueService {

	@Autowired
	private PatientQueueRepository patientQueueRepository;

	@Autowired
	private BeanCreatorUtil beanCreatorUtil;

	@Autowired
	private DtoCreatorUtil dtoCreatorUtil;

	public PatientQueueDto findBy(String id, String datedOn) {
		PatientQueue patientQueue = patientQueueRepository.findByIdAndDatedOn(
				id, datedOn);
		if (patientQueue != null) {
			return dtoCreatorUtil.createPatientQueueDto(patientQueue);
		}
		return null;
	}

	public PatientQueueDto createNew(List<AppointmentDto> appointmentDtos,
			String datedOn) {
		PatientQueue patientQueue = patientQueueRepository
				.findByDatedOn(datedOn);
		if (patientQueue == null) {
			patientQueue = beanCreatorUtil.createPatientQueue(appointmentDtos);
			patientQueue.setDatedOn(datedOn);
			patientQueueRepository.save(patientQueue);
		}
		PatientQueueDto patientQueueDto = dtoCreatorUtil
				.createPatientQueueDto(patientQueue);
		return patientQueueDto;
	}

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
		patientQueueRepository.save(patientQueue);
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
		patientQueueRepository.save(patientQueue);
		return patientQueue;
	}

}