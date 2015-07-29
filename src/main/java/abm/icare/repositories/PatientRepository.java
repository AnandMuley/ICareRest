package abm.icare.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import abm.icare.beans.Patient;

public interface PatientRepository extends MongoRepository<Patient, String> {

	@Query(value = "{$or:[{'firstName':{$regex:'?0',$options:'i'}},{'lastName':{$regex:'?0',$options:'i'}}]}")
	List<Patient> findByName(String searchTxt);

}
