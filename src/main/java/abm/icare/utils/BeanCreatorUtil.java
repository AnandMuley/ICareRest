package abm.icare.utils;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import abm.icare.beans.Appointment;
import abm.icare.beans.PatientQueue;
import abm.icare.dtos.AppointmentDto;

@Component
public class BeanCreatorUtil {

	@Autowired
	private ApplicationContext context;

	public PatientQueue createPatientQueue(List<AppointmentDto> appointmentDtos) {
		PatientQueue patientQueue = context.getBean(PatientQueue.class);
		Iterator<AppointmentDto> iterator = appointmentDtos.iterator();
		while (iterator.hasNext()) {
			AppointmentDto appointmentDto = (AppointmentDto) iterator.next();
			Appointment appointment = context.getBean(Appointment.class);
			appointment.setFirstName(appointmentDto.getFirstName());
			appointment.setDatedOn(appointmentDto.getDatedOn());
			appointment.setEmailId(appointmentDto.getEmailId());
			appointment.setId(appointmentDto.getId());
			appointment.setName(appointmentDto.getName());
			appointment.setLastName(appointmentDto.getLastName());
			appointment.setMobileNo(appointmentDto.getMobileNo());
			appointment.setRequestSubmittedOn(appointmentDto
					.getRequestSubmittedOn());
			patientQueue.getLive().add(appointment);
		}
		return patientQueue;
	}

}
