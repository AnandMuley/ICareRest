package abm.icare.services;

import java.util.Collections;
import java.util.List;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.util.ReflectionTestUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import abm.icare.beans.Medicine;
import abm.icare.config.SpringTestNGSupport;
import abm.icare.dataproviders.MedicineDataProvider;
import abm.icare.dtos.MedicineDto;
import abm.icare.exceptions.MedicineNotFoundException;
import abm.icare.exceptions.MedicineServiceException;
import abm.icare.populators.PatientDataPopulator;
import abm.icare.repositories.MedicineRepository;

public class MedicineServiceTest extends SpringTestNGSupport {

	private MedicineRepository mockMedicineRepository;

	private MedicineService medicineService;

	@Autowired
	private PatientDataPopulator patientDataPopulator;

	private Mockery context;

	@BeforeMethod
	public void setUp() {
		context = new Mockery();
		mockMedicineRepository = context.mock(MedicineRepository.class);
		medicineService = new MedicineService();

		ReflectionTestUtils.setField(medicineService, "medicineRepository",
				mockMedicineRepository);
		ReflectionTestUtils.setField(medicineService, "patientDataPopulator",
				patientDataPopulator);
	}

	@Test
	public void shouldFindMedicineByName() throws MedicineServiceException {
		// GIVEN
		final String name = "Croc";
		final List<Medicine> medicines = MedicineDataProvider.getMedicines();

		context.checking(new Expectations() {
			{
				oneOf(mockMedicineRepository).fetchBy(with(name));
				will(returnValue(medicines));
			}
		});
		// WHEN
		List<MedicineDto> actual = medicineService.fetchBy(name);

		// THEN
		Assert.assertEquals(actual.size(), 3);
	}

	@Test(expectedExceptions = MedicineNotFoundException.class)
	public void shouldRaiseAnExceptionIfNoneFound()
			throws MedicineServiceException {
		// GIVEN
		final String name = "Demo";

		context.checking(new Expectations() {
			{
				oneOf(mockMedicineRepository).fetchBy(with(name));
				will(returnValue(Collections.emptyList()));
			}
		});
		// WHEN
		medicineService.fetchBy(name);

		// THEN
		// Should throw an exception
	}

}
