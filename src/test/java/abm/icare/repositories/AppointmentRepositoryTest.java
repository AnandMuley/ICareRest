package abm.icare.repositories;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import abm.icare.beans.Appointment;
import abm.icare.config.SpringTestNGSupport;
import abm.icare.dataproviders.AppointmentDataProvider;

public class AppointmentRepositoryTest extends SpringTestNGSupport {

	@Autowired
	private AppointmentRepository appointmentRepository;

	@BeforeMethod
	public void setUp() {
		final List<Appointment> appointments = AppointmentDataProvider
				.createAppointments();
		appointmentRepository.save(appointments);
	}

	@AfterMethod
	public void destroy() {
		appointmentRepository.deleteAll();
	}

	@Test
	public void shouldSaveAppointment() {
		// GIVEN
		final Appointment appointment = AppointmentDataProvider
				.createAppointment();

		// WHEN
		appointmentRepository.save(appointment);

		// THEN
		Assert.assertNotNull(appointment.getId());
	}

	@Test
	public void shouldGetListOfAppointments() {
		// GIVEN
		final String date = "05-Sep-2015";

		// WHEN
		List<Appointment> actualAppointments = appointmentRepository
				.findByDatedOn(date);

		// THEN
		Assert.assertEquals(actualAppointments.size(), 4);
		Collections.sort(actualAppointments);
		Assert.assertEquals(actualAppointments.get(0).getFirstName(), "User0");
	}

}
