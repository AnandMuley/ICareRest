package abm.icare.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import abm.icare.beans.Medicine;
import abm.icare.constants.AppConstants;
import abm.icare.dtos.MedicineDto;
import abm.icare.exceptions.MedicineNotFoundException;
import abm.icare.exceptions.MedicineServiceException;
import abm.icare.populators.PatientDataPopulator;
import abm.icare.repositories.MedicineRepository;

@Service
public class MedicineService {

	@Autowired
	private MedicineRepository medicineRepository;

	@Autowired
	private PatientDataPopulator patientDataPopulator;

	public List<MedicineDto> fetchBy(String name)
			throws MedicineServiceException {
		List<Medicine> medicines = medicineRepository.fetchBy(name);
		if (CollectionUtils.isEmpty(medicines)) {
			throw new MedicineNotFoundException(
					AppConstants.EXCEPTION_MEDICINE_NOT_FOUND);
		}
		return patientDataPopulator.populateMedicineDtos(medicines);
	}

}
