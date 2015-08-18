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
		final SaveVisitAction saveVisitAction = new SaveVisitAction();

		context.checking(new Expectations() {
			{
				oneOf(mockVisitsRepository).insert(with(any(Visit.class)));
				will(saveVisitAction);
			}
		});
		// WHEN
		visitService.create(visitDto);

		// THEN
		Assert.assertEquals(visitDto.getId(), "VID101");

	}

	@Test
	public void shouldUpdateVisit() {
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

	}

	@Test
	public void shouldFindAllVisits() {
		// GIVEN
		final String patientId = "PID101";
		final List<Visit> visits = VisitDataProvider.createVisitDtos();

		context.checking(new Expectations(){
			{
				oneOf(mockVisitsRepository).findByPatientId(patientId);
				will(returnValue(visits));
			}
		});
		// WHEN
		List<VisitDto> actual = visitService.findAllVisitsOf(patientId);

		// THEN
		Assert.assertEquals(actual.size(), 2);
	}

}
