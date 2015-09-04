package abm.icare.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import abm.icare.beans.Appointment;
import abm.icare.dtos.AppointmentDto;
import abm.icare.repositories.AppointmentRepository;

@Service
public class AppointmentService {

	@Autowired
	private AppointmentRepository appointmentRepository;

	@Autowired
	private ApplicationContext context;

	public AppointmentDto create(AppointmentDto appointmentDto) {
		Appointment appointment = context.getBean(Appointment.class);
		appointment.setDatedOn(appointmentDto.getDatedOn());
		appointment.setEmailId(appointmentDto.getEmailId());
		appointment.setFirstName(appointmentDto.getFirstName());
		appointment.setLastName(appointmentDto.getLastName());
		appointment.setMobileNo(appointmentDto.getMobileNo());
		Date now = new Date();
		appointment.setRequestSubmittedOn(now);
		appointmentRepository.save(appointment);
		appointmentDto.setId(appointment.getId());
		return appointmentDto;
	}

	public List<AppointmentDto> getAppointmentsFor(String datedOn) {
		List<Appointment> appointments = appointmentRepository
				.findByDatedOn(datedOn);
		Collections.sort(appointments);
		List<AppointmentDto> appointmentDtos = new ArrayList<AppointmentDto>();
		for (Appointment appointment : appointments) {
			AppointmentDto appointmentDto = context
					.getBean(AppointmentDto.class);
			appointmentDto.setDatedOn(appointment.getDatedOn());
			appointmentDto.setEmailId(appointment.getEmailId());
			appointmentDto.setFirstName(appointment.getFirstName());
			appointmentDto.setId(appointment.getId());
			appointmentDto.setLastName(appointment.getLastName());
			appointmentDto.setMobileNo(appointment.getMobileNo());
			appointmentDto.setRequestSubmittedOn(appointment
					.getRequestSubmittedOn());
			appointmentDtos.add(appointmentDto);
		}
		return appointmentDtos;
	}

}
