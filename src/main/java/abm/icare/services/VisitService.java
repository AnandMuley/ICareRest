package abm.icare.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import abm.icare.beans.Visit;
import abm.icare.dtos.VisitDto;
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
		visitsRepository.insert(visit);
		visitDto.setId(visit.getId());
	}

	public void update(VisitDto visitDto) {
		Visit visit = visitDataPopulator.populateVisit(visitDto);
		visitsRepository.save(visit);
	}

	public List<VisitDto> findAllVisitsOf(String patientId) {
		List<Visit> visits = visitsRepository.findByPatientId(patientId);
		return visitDataPopulator.populateVisits(visits);
	}

	public void deleteBy(String visitId) {

	}

}
