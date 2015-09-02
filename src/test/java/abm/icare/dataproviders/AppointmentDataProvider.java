package abm.icare.dataproviders;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import abm.icare.beans.Appointment;
import abm.icare.dtos.AppointmentDto;

public abstract class AppointmentDataProvider {

	private final static SimpleDateFormat sdf = new SimpleDateFormat(
			"dd-MMM-yyyy");

	public static Appointment createAppointment() {
		final Appointment appointment = new Appointment();
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, 2);
		appointment.setDatedOn(sdf.format(calendar.getTime()));
		appointment.setEmailId("rahul@gmail.com");
		appointment.setFirstName("Rahul");
		appointment.setLastName("Malhotra");
		appointment.setMobileNo(7890098700l);
		appointment.setRequestSubmittedOn(new Date());
		return appointment;
	}

	public static AppointmentDto createAppointmentDto() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, 2);
		AppointmentDto appointmentDto = new AppointmentDto();
		appointmentDto.setDatedOn(sdf.format(calendar.getTime()));
		appointmentDto.setEmailId("aliva@gmail.com");
		appointmentDto.setFirstName("Aliva");
		appointmentDto.setLastName("Johnson");
		appointmentDto.setMobileNo(8790098700l);
		return appointmentDto;
	}

	public static List<Appointment> createAppointments() {
		Calendar calendarReq = Calendar.getInstance();
		List<Appointment> appointments = new ArrayList<Appointment>();
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, 2);
		for (int i = 0; i < 5; i++) {
			calendarReq.add(Calendar.MINUTE, i);
			final Appointment appointment = new Appointment();
			if (i > 3) {
				calendar.add(Calendar.DAY_OF_MONTH, 3);
			}
			appointment.setDatedOn(sdf.format(calendar.getTime()));
			appointment.setEmailId("rahul@gmail.com");
			appointment.setFirstName("User" + i);
			appointment.setLastName("Malhotra");
			appointment.setMobileNo(7890098700l);
			appointment.setRequestSubmittedOn(calendarReq.getTime());
			appointments.add(appointment);
		}
		return appointments;
	}

}
