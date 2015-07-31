package abm.icare.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import abm.icare.repositories.MedicinesRepository;

@Service
public class MedicineService {

	@Autowired
	private MedicinesRepository medicinesRepository;

}
