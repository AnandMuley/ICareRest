package abm.icare.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import abm.icare.beans.Visit;
import abm.icare.dtos.VisitDto;
import abm.icare.exceptions.DeleteVisitException;
import abm.icare.exceptions.UpdateVisitException;
import abm.icare.exceptions.VisitServiceException;
import abm.icare.populators.VisitDataPopulator;
import abm.icare.repositories.VisitsRepository;

@Service
public class VisitService {

	@Autowired
	private VisitsRepository visitsRepository;

	@Autowired
	private VisitDataPopulator visitDataPopulator;

	public void create(VisitDto visitDto) {
		Visit visit = visitDataPopulator.populateVisit(visitDto);
		Date today = new Date();
		visit.setVisitedOn(today);
		visitsRepository.insert(visit);
		visitDto.setId(visit.getId());
	}

	public void update(VisitDto visitDto) throws VisitServiceException {
		if (StringUtils.isEmpty(visitDto.getId())) {
			throw new UpdateVisitException("VisitId should not be null");
		}
		Visit visit = visitDataPopulator.populateVisit(visitDto);
		visitsRepository.save(visit);
	}

	public List<VisitDto> findAllVisitsOf(String patientId) {
		List<Visit> visits = visitsRepository.findByPatientId(patientId);
		return visitDataPopulator.populateVisits(visits);
	}

	public void deleteBy(String visitId) throws VisitServiceException {
		if (StringUtils.isEmpty(visitId)) {
			throw new DeleteVisitException("VisitId should not be null");
		}
		visitsRepository.delete(visitId);
	}

}
