package abm.icare.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import abm.icare.beans.Medicine;
import abm.icare.config.SpringTestNGSupport;

public class MedicineRepositoryTest extends SpringTestNGSupport {

	@Autowired
	private MedicineRepository medicineRepository;

	@BeforeMethod
	public void init() {
		Medicine crocine = new Medicine("Crocine");
		Medicine crocold = new Medicine("Crocold");
		Medicine crocia = new Medicine("Crocia");
		Medicine vicks = new Medicine("Vicks");

		medicineRepository.save(crocine);
		medicineRepository.save(crocold);
		medicineRepository.save(crocia);
		medicineRepository.save(vicks);

	}
	
	@AfterMethod
	public void cleanUp(){
		medicineRepository.deleteAll();
	}

	@Test
	public void shouldFetchMedicine() {
		// GIVEN

		// WHEN
		List<Medicine> actual = medicineRepository.fetchBy("croc");

		// THEN
		Assert.assertEquals(actual.size(), 3);
	}

}
