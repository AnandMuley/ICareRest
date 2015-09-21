package abm.icare.utils;

import org.springframework.test.util.ReflectionTestUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import abm.icare.beans.PatientQueue;
import abm.icare.config.SpringTestNGSupport;
import abm.icare.dataproviders.AppointmentDataProvider;
import abm.icare.dtos.AppointmentDto;
import abm.icare.dtos.PatientQueueDto;

public class DtoCreatorUtilTest extends SpringTestNGSupport {

	private DtoCreatorUtil dtoCreatorUtil;

	@BeforeMethod
	public void setUp() {
		dtoCreatorUtil = new DtoCreatorUtil();
		ReflectionTestUtils.setField(dtoCreatorUtil, "context",
				applicationContext);
	}

	@Test
	public void shouldCreatePatientQueueDto() throws InterruptedException {
		// GIVEN
		final PatientQueue patientQueue = AppointmentDataProvider
				.patientQueueForDto();

		// WHEN
		PatientQueueDto patientQueueDto = dtoCreatorUtil
				.createPatientQueueDto(patientQueue);

		// THEN
		Assert.assertEquals(patientQueueDto.getId(), "55ae222344zebfcdd19d7721");
		Assert.assertEquals(patientQueueDto.getLive().size(), 1);

		AppointmentDto liveAppointment = patientQueueDto.getLive().iterator()
				.next();
		// Checking for live appointment values
		Assert.assertEquals(liveAppointment.getDatedOn(), "20-Sep-2015");
		Assert.assertEquals(liveAppointment.getEmailId(), "arjun@gmail.com");
		Assert.assertEquals(liveAppointment.getFirstName(), "Arjun");
		Assert.assertEquals(liveAppointment.getId(), "55ae228044webfcdd19d7720");
		Assert.assertEquals(liveAppointment.getLastName(), "Ranawat");
		Assert.assertEquals(liveAppointment.getMobileNo(), 7890098700l);
		Assert.assertEquals(liveAppointment.getName(), "Arjun Ranawat");
		Assert.assertNotNull(liveAppointment.getRequestSubmittedOn());
		Assert.assertEquals(liveAppointment.getFirstName(), "Arjun");

		Assert.assertEquals(patientQueueDto.getOnhold().size(), 1);
		AppointmentDto onholdAppointment = patientQueueDto.getOnhold()
				.iterator().next();
		// Check for onhold appointment values
		Assert.assertEquals(onholdAppointment.getDatedOn(), "20-Sep-2015");
		Assert.assertEquals(onholdAppointment.getEmailId(), "rahul@gmail.com");
		Assert.assertEquals(onholdAppointment.getFirstName(), "Rahul");
		Assert.assertEquals(onholdAppointment.getId(), "55ae222312zebfcdd19d7722");
		Assert.assertEquals(onholdAppointment.getLastName(), "Verma");
		Assert.assertEquals(onholdAppointment.getMobileNo(), 9878978900l);
		Assert.assertEquals(onholdAppointment.getName(), "Rahul Verma");
		Assert.assertNotNull(onholdAppointment.getRequestSubmittedOn());
		Assert.assertEquals(onholdAppointment.getFirstName(), "Rahul");

	}
}
