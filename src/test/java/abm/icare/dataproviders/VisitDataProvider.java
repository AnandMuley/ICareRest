package abm.icare.dataproviders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import abm.icare.beans.Visit;
import abm.icare.dtos.VisitDto;

public abstract class VisitDataProvider {

	static Set<String> prescriptions = new TreeSet<String>(
			Arrays.asList("Crocin"));

	public static VisitDto createVisitDto() {
		VisitDto visitDto = new VisitDto();
		visitDto.setPatientId("PID101");
		visitDto.setAllergies("Peanuts");
		visitDto.setSymptoms("Cough and Cold");
		visitDto.getPrescriptions().add("Crocin");
		visitDto.setVisitedOn(new Date());
		return visitDto;
	}

	public static List<VisitDto> createVisitDtos() {
		List<VisitDto> visitDtos = new ArrayList<VisitDto>();
		for (int i = 0; i < 2; i++) {
			VisitDto visitDto = new VisitDto();
			visitDto.setId("VID10" + i);
			visitDto.setPatientId("PID101");
			visitDto.setAllergies("Peanuts");
			visitDto.setSymptoms("Cough and Cold");
			visitDto.getPrescriptions().add("Crocin");
			visitDtos.add(visitDto);
		}
		return visitDtos;
	}

	public static List<Visit> createVisits() {
		Date today = new Date();
		List<Visit> visits = new ArrayList<Visit>();
		for (int i = 0; i < 2; i++) {
			Visit visit = new Visit();
			visit.setAllergies("Peanuts");
			visit.setId("VID" + 100 + i);
			visit.setPatientId("PID201");
			visit.setPrescriptions(prescriptions);
			visit.setSymptoms("Cough and Cold");
			visit.setVisitedOn(today);
			visits.add(visit);
		}
		return visits;
	}
}
