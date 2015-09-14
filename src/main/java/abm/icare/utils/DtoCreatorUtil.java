package abm.icare.utils;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import abm.icare.beans.Appointment;
import abm.icare.beans.PatientQueue;
import abm.icare.constants.AppConstants;
import abm.icare.dtos.AppointmentDto;
import abm.icare.dtos.PatientQueueDto;

@Component
public class DtoCreatorUtil {

	@Autowired
	private ApplicationContext context;

	public PatientQueueDto createPatientQueueDto(PatientQueue patientQueue) {
		PatientQueueDto patientQueueDto = context
				.getBean(PatientQueueDto.class);
		Iterator<Appointment> iterator = patientQueue.getLive().iterator();
		while (iterator.hasNext()) {
			Appointment appointment = iterator.next();
			AppointmentDto appointmentDto = context
					.getBean(AppointmentDto.class);
			appointmentDto.setDatedOn(appointment.getDatedOn());
			appointmentDto.setEmailId(appointment.getEmailId());
			appointmentDto.setFirstName(appointment.getFirstName());
			appointmentDto.setId(appointment.getId());
			appointmentDto.setLastName(appointment.getLastName());
			appointmentDto.setMobileNo(appointment.getMobileNo());
			appointmentDto.setName(appointment.getFirstName()
					+ AppConstants.WHITE_SPACE + appointment.getLastName());
			appointmentDto.setRequestSubmittedOn(appointment
					.getRequestSubmittedOn());
			patientQueueDto.getLive().add(appointmentDto);
		}
		patientQueueDto.setId(patientQueue.getId());
		return patientQueueDto;
	}

}
