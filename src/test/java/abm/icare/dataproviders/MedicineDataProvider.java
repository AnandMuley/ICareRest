package abm.icare.dataproviders;

import java.util.ArrayList;
import java.util.List;

import abm.icare.beans.Medicine;
import abm.icare.dtos.MedicineDto;

public abstract class MedicineDataProvider {

	public static List<Medicine> getMedicines() {
		List<Medicine> medicines = new ArrayList<Medicine>();
		for (int i = 0; i < 3; i++) {
			medicines.add(new Medicine("MID10" + i, "Croc" + i));
		}
		return medicines;
	}

	public static List<MedicineDto> getMedicineDtos() {
		List<MedicineDto> medicineDtos = new ArrayList<MedicineDto>();
		for (int i = 0; i < 3; i++) {
			MedicineDto medicineDto = new MedicineDto();
			medicineDto.setId("MID10" + i);
			medicineDto.setName("Croc" + i);
			medicineDtos.add(medicineDto);
		}
		return medicineDtos;
	}

}
