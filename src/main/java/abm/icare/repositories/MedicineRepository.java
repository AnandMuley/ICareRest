package abm.icare.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import abm.icare.beans.Medicine;

public interface MedicineRepository extends MongoRepository<Medicine, String> {

	@Query(value = "{'name':{$regex:'?0',$options:'i'}}")
	List<Medicine> fetchBy(String name);

}
