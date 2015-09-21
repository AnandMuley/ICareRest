package abm.icare.dataproviders;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import abm.icare.beans.Appointment;
import abm.icare.beans.PatientQueue;
import abm.icare.dtos.AppointmentDto;
import abm.icare.dtos.PatientQueueDto;

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

	public static List<AppointmentDto> createAppointmentDtos() {
		Calendar calendarReq = Calendar.getInstance();
		List<AppointmentDto> appointments = new ArrayList<AppointmentDto>();
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, 2);
		for (int i = 0; i < 5; i++) {
			calendarReq.add(Calendar.MINUTE, i);
			final AppointmentDto appointment = new AppointmentDto();
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

	public static PatientQueue createPatientQueue() throws InterruptedException {
		PatientQueue patientQueue = new PatientQueue();
		Appointment appointment = new Appointment();
		appointment.setId("55ae228044webfcdd19d7720");
		appointment.setRequestSubmittedOn(new Date());
		TimeUnit.SECONDS.sleep(2);
		Appointment appointment2 = new Appointment();
		appointment2.setId("55ae228044webfcdd19d7721");
		appointment2.setRequestSubmittedOn(new Date());
		patientQueue.getLive().add(appointment);
		patientQueue.getLive().add(appointment2);
		return patientQueue;
	}

	public static PatientQueue patientQueueForDto() throws InterruptedException {
		PatientQueue patientQueue = new PatientQueue();
		patientQueue.setId("55ae222344zebfcdd19d7721");
		Appointment appointment = new Appointment();
		appointment.setDatedOn("20-Sep-2015");
		appointment.setEmailId("arjun@gmail.com");
		appointment.setFirstName("Arjun");
		appointment.setId("55ae228044webfcdd19d7720");
		appointment.setLastName("Ranawat");
		appointment.setMobileNo(7890098700l);
		appointment.setName("Arjun Ranawat");
		appointment.setRequestSubmittedOn(new Date());
		TimeUnit.SECONDS.sleep(2);
		Appointment appointment2 = new Appointment();
		appointment2.setDatedOn("20-Sep-2015");
		appointment2.setEmailId("rahul@gmail.com");
		appointment2.setFirstName("Rahul");
		appointment2.setId("55ae222312zebfcdd19d7722");
		appointment2.setLastName("Verma");
		appointment2.setMobileNo(9878978900l);
		appointment2.setName("Rahul Verma");
		appointment2.setRequestSubmittedOn(new Date());
		patientQueue.getLive().add(appointment);
		patientQueue.getOnhold().add(appointment2);
		return patientQueue;
	}

	public static PatientQueue movingPatientToLiveQueue()
			throws InterruptedException {
		PatientQueue patientQueue = new PatientQueue();
		patientQueue.setId("55ae222344zebfcdd19d7721");
		Appointment appointment = new Appointment();
		appointment.setId("55ae228044webfcdd19d7720");
		appointment.setRequestSubmittedOn(new Date());
		TimeUnit.SECONDS.sleep(2);
		Appointment appointment2 = new Appointment();
		appointment2.setId("55ae222312zebfcdd19d7722");
		appointment2.setRequestSubmittedOn(new Date());
		patientQueue.getLive().add(appointment);
		patientQueue.getOnhold().add(appointment2);
		return patientQueue;
	}

	public static PatientQueue puttingAPatientOnHold()
			throws InterruptedException {
		PatientQueue patientQueue = new PatientQueue();
		patientQueue.setId("55ae222344zebfcdd19d7721");
		Appointment appointment = new Appointment();
		appointment.setId("55ae228044webfcdd19d7720");
		appointment.setRequestSubmittedOn(new Date());
		TimeUnit.SECONDS.sleep(2);
		Appointment appointment2 = new Appointment();
		appointment2.setId("55ae222312zebfcdd19d7722");
		appointment2.setRequestSubmittedOn(new Date());
		patientQueue.getLive().add(appointment);
		patientQueue.getOnhold().add(appointment2);
		return patientQueue;
	}

	public static List<AppointmentDto> createAppointmentsDtos()
			throws InterruptedException {
		AppointmentDto appointment = new AppointmentDto();
		appointment.setId("55ae228044webfcdd19d7720");
		appointment.setRequestSubmittedOn(new Date());
		TimeUnit.SECONDS.sleep(2);
		AppointmentDto appointment2 = new AppointmentDto();
		appointment2.setId("55ae228044webfcdd19d7721");
		appointment2.setRequestSubmittedOn(new Date());
		List<AppointmentDto> appointmentDtos = new ArrayList<AppointmentDto>();
		appointmentDtos.add(appointment);
		appointmentDtos.add(appointment2);
		return appointmentDtos;
	}

	public static PatientQueue createPatientQueueOnhold()
			throws InterruptedException {
		PatientQueue patientQueue = new PatientQueue();
		Appointment appointment = new Appointment();
		appointment.setId("55ae228044webfcdd19d7720");
		appointment.setRequestSubmittedOn(new Date());
		TimeUnit.SECONDS.sleep(2);
		Appointment appointment2 = new Appointment();
		appointment2.setId("55ae228044webfcdd19d7721");
		appointment2.setRequestSubmittedOn(new Date());
		patientQueue.getOnhold().add(appointment);
		patientQueue.getOnhold().add(appointment2);
		return patientQueue;
	}

	public static PatientQueueDto createPatientQueueDto(
			List<AppointmentDto> appointmentDtos) {
		PatientQueueDto patientQueueDto = new PatientQueueDto();
		patientQueueDto.setId("QID101");
		patientQueueDto.getLive().addAll(appointmentDtos);
		return patientQueueDto;
	}
}
