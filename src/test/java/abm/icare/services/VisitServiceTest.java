package abm.icare.services;

import java.util.List;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.springframework.test.util.ReflectionTestUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import abm.icare.actions.SaveVisitAction;
import abm.icare.beans.Visit;
import abm.icare.config.SpringTestNGSupport;
import abm.icare.dataproviders.VisitDataProvider;
import abm.icare.dtos.VisitDto;
import abm.icare.exceptions.DeleteVisitException;
import abm.icare.exceptions.UpdateVisitException;
import abm.icare.exceptions.VisitServiceException;
import abm.icare.populators.VisitDataPopulator;
import abm.icare.repositories.VisitsRepository;

public class VisitServiceTest extends SpringTestNGSupport {

	private VisitService visitService;
	private VisitsRepository mockVisitsRepository;
	private VisitDataPopulator visitDataPopulator;
	private Mockery context;

	@BeforeMethod
	public void setUp() {
		context = new Mockery();
		mockVisitsRepository = context.mock(VisitsRepository.class);
		visitDataPopulator = new VisitDataPopulator();
		ReflectionTestUtils.setField(visitDataPopulator, "context",
				applicationContext);
		visitService = new VisitService();
		ReflectionTestUtils.setField(visitService, "visitsRepository",
				mockVisitsRepository);
		ReflectionTestUtils.setField(visitService, "visitDataPopulator",
				visitDataPopulator);
	}

	@Test
	public void shouldCreateVisit() {
		// GIVEN
		VisitDto visitDto = VisitDataProvider.createVisitDto();
		final Visit visitToInsert = visitDataPopulator.populateVisit(visitDto);
		final SaveVisitAction saveVisitAction = new SaveVisitAction();

		context.checking(new Expectations() {
			{
				oneOf(mockVisitsRepository).insert(with(visitToInsert));
				will(saveVisitAction);
			}
		});
		// WHEN
		visitService.create(visitDto);

		// THEN
		Assert.assertEquals(visitDto.getId(), "VID101");

	}

	@Test
	public void shouldUpdateVisit() throws VisitServiceException {
		// GIVEN
		VisitDto visitDto = VisitDataProvider.createVisitDto();
		visitDto.setId("VID201");

		context.checking(new Expectations() {
			{
				oneOf(mockVisitsRepository).save(with(any(Visit.class)));
			}
		});
		// WHEN
		visitService.update(visitDto);

		// THEN
		// Should update visit
	}

	@Test(expectedExceptions = UpdateVisitException.class)
	public void shouldNotUpdateVisitNullVisitId() throws VisitServiceException {
		// GIVEN
		VisitDto visitDto = VisitDataProvider.createVisitDto();

		context.checking(new Expectations() {
			{
				oneOf(mockVisitsRepository).save(with(any(Visit.class)));
			}
		});
		// WHEN
		visitService.update(visitDto);

		// THEN
		// Should not update visit as visitId is null
	}

	@Test(expectedExceptions = UpdateVisitException.class)
	public void shouldNotUpdateVisitEmptyVisitId() throws VisitServiceException {
		// GIVEN
		VisitDto visitDto = VisitDataProvider.createVisitDto();
		visitDto.setId("");

		context.checking(new Expectations() {
			{
				oneOf(mockVisitsRepository).save(with(any(Visit.class)));
			}
		});
		// WHEN
		visitService.update(visitDto);

		// THEN
		// Should not update visit as visitId is Empty
	}

	@Test
	public void shouldFindAllVisits() {
		// GIVEN
		final String patientId = "PID201";
		final List<Visit> visits = VisitDataProvider.createVisits();

		context.checking(new Expectations() {
			{
				oneOf(mockVisitsRepository).findByPatientId(patientId);
				will(returnValue(visits));
			}
		});

		// WHEN
		List<VisitDto> actual = visitService.findAllVisitsOf(patientId);

		// THEN
		Assert.assertEquals(actual.size(), 2);
		for (VisitDto visitDto : actual) {
			Assert.assertEquals(visitDto.getAllergies().size(), 1);
			Assert.assertNotNull(visitDto.getId());
			Assert.assertEquals(visitDto.getPatientId(), patientId);
			Assert.assertEquals(visitDto.getPrescriptions().size(), 1);
			Assert.assertEquals(visitDto.getSymptoms().size(), 1);
			Assert.assertNotNull(visitDto.getVisitedOn());
		}
	}

	@Test
	public void shouldDeleteVisit() throws VisitServiceException {
		// GIVEN
		final String visitId = "VID101";

		context.checking(new Expectations() {
			{
				oneOf(mockVisitsRepository).delete(visitId);
			}
		});
		// WHEN
		visitService.deleteBy(visitId);

		// THEN
		// Should delete visit
	}

	@Test(expectedExceptions = DeleteVisitException.class)
	public void shouldNotDeleteVisitWhenNullVisitId()
			throws VisitServiceException {
		// GIVEN
		final String visitId = null;

		// WHEN
		visitService.deleteBy(visitId);

		// THEN
		// Should throw an exception as visitId is null
	}

	@Test(expectedExceptions = DeleteVisitException.class)
	public void shouldNotDeleteVisitWhenEmptyVisitId()
			throws VisitServiceException {
		// GIVEN
		final String visitId = "";

		// WHEN
		visitService.deleteBy(visitId);

		// THEN
		// Should throw an exception as visitId is null
	}
}
