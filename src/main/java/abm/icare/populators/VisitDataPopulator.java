package abm.icare.populators;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import abm.icare.beans.Visit;
import abm.icare.dtos.VisitDto;

@Component
public class VisitDataPopulator {

	@Autowired
	private ApplicationContext context;

	public Visit populateVisit(VisitDto visitDto) {
		Visit visit = context.getBean(Visit.class);
		if (!StringUtils.isEmpty(visitDto.getId())) {
			visit.setId(visitDto.getId());
		}
		visit.setAllergies(visitDto.getAllergies());
		visit.setPatientId(visitDto.getPatientId());
		visit.setPrescriptions(visitDto.getPrescriptions());
		visit.setSymptoms(visitDto.getSymptoms());
		return visit;
	}

	public List<VisitDto> populateVisits(List<Visit> visits) {
		List<VisitDto> visitDtos = new ArrayList<VisitDto>();
		for (Visit visit : visits) {
			VisitDto visitDto = context.getBean(VisitDto.class);
			visitDto.setId(visit.getId());
			visitDto.setPatientId(visit.getPatientId());
			visitDto.getAllergies().addAll(visit.getAllergies());
			visitDto.getPrescriptions().addAll(visit.getPrescriptions());
			visitDto.getSymptoms().addAll(visit.getSymptoms());
			visitDto.setVisitedOn(visit.getVisitedOn());
			visitDtos.add(visitDto);
		}
		return visitDtos;
	}

}
