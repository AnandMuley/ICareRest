package abm.icare.dataproviders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import abm.icare.beans.Visit;
import abm.icare.dtos.VisitDto;

public abstract class VisitDataProvider {

	public static VisitDto createVisitDto() {
		VisitDto visitDto = new VisitDto();
		visitDto.setPatientId("PID101");
		visitDto.getAllergies().add("Peanuts");
		visitDto.getSymptoms().add("Cough and Cold");
		return visitDto;
	}

	public static List<Visit> createVisitDtos() {
		List<String> allergies = Arrays.asList("Peanuts");
		List<String> prescriptions = Arrays.asList("Crocin");
		List<String> symptoms = Arrays.asList("Cough and Cold");
		List<Visit> visits = new ArrayList<Visit>();
		for (int i = 0; i < 2; i++) {
			Visit visit = new Visit();
			visit.setAllergies(allergies);
			visit.setId("VID" + 100 + i);
			visit.setPatientId("PID201");
			visit.setPrescriptions(prescriptions);
			visit.setSymptoms(symptoms);
			visits.add(visit);
		}
		return visits;
	}

}
