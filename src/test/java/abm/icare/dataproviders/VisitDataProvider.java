package abm.icare.dataproviders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import abm.icare.beans.Visit;
import abm.icare.dtos.VisitDto;

public abstract class VisitDataProvider {

	static Set<String> allergies = new HashSet<String>(Arrays.asList("Peanuts"));
	static Set<String> prescriptions = new HashSet<String>(
			Arrays.asList("Crocin"));
	static Set<String> symptoms = new HashSet<String>(
			Arrays.asList("Cough and Cold"));

	public static VisitDto createVisitDto() {
		VisitDto visitDto = new VisitDto();
		visitDto.setPatientId("PID101");
		visitDto.getAllergies().add("Peanuts");
		visitDto.getSymptoms().add("Cough and Cold");
		visitDto.getPrescriptions().add("Crocin");
		return visitDto;
	}

	public static List<VisitDto> createVisitDtos() {
		List<VisitDto> visitDtos = new ArrayList<VisitDto>();
		for (int i = 0; i < 2; i++) {
			VisitDto visitDto = new VisitDto();
			visitDto.setId("VID10" + i);
			visitDto.setPatientId("PID101");
			visitDto.getAllergies().add("Peanuts");
			visitDto.getSymptoms().add("Cough and Cold");
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
			visit.setAllergies(allergies);
			visit.setId("VID" + 100 + i);
			visit.setPatientId("PID201");
			visit.setPrescriptions(prescriptions);
			visit.setSymptoms(symptoms);
			visit.setVisitedOn(today);
			visits.add(visit);
		}
		return visits;
	}
}
