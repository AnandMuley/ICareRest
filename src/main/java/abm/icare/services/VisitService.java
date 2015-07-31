package abm.icare.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import abm.icare.dtos.VisitDto;
import abm.icare.repositories.VisitsRepository;

@Service
public class VisitService {

	@Autowired
	private VisitsRepository visitsRepository;

	public void create(VisitDto visitDto) {

	}

	public void update(VisitDto visitDto) {

	}

	public List<VisitDto> findAllVisitsOf(String patientId) {
		return null;
	}

	public void deleteBy(String visitId) {

	}

}
