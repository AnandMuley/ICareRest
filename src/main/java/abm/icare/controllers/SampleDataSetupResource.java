package abm.icare.controllers;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import abm.icare.beans.Medicine;
import abm.icare.beans.UserBean;
import abm.icare.constants.UserRole;
import abm.icare.repositories.MedicineRepository;
import abm.icare.repositories.UserRepository;

@Component
@Path("setup")
public class SampleDataSetupResource {

	@Autowired
	private MedicineRepository medicineRepository;

	@Autowired
	private UserRepository userRepository;

	@GET
	@Path("medicines")
	public Response setUpMedicinesData() {
		Medicine medicine1 = new Medicine("Crocine");
		Medicine medicine2 = new Medicine("Crocold");
		Medicine medicine3 = new Medicine("DCold Total");
		Medicine medicine4 = new Medicine("Betnoveta-C");
		Medicine medicine5 = new Medicine("Lactomicine");
		Medicine medicine6 = new Medicine("Vicks");

		medicineRepository.save(medicine1);
		medicineRepository.save(medicine2);
		medicineRepository.save(medicine3);
		medicineRepository.save(medicine4);
		medicineRepository.save(medicine5);
		medicineRepository.save(medicine6);

		return Response.ok("Medicines Data Setup Successfully !").build();
	}

	@GET
	@Path("users")
	public Response setUpUsers() {
		UserBean doctor = new UserBean();
		doctor.setPassword("Doctor@123");
		doctor.setUsername("doctor");
		doctor.setUserRole(UserRole.DOCTOR);
		UserBean compounder = new UserBean();
		compounder.setPassword("Compounder@123");
		compounder.setUsername("compounder");
		compounder.setUserRole(UserRole.COMPOUNDER);
		userRepository.save(doctor);
		userRepository.save(compounder);
		return Response.ok().build();
	}
}
