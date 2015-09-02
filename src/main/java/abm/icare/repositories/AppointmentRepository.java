package abm.icare.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import abm.icare.beans.Appointment;

public interface AppointmentRepository extends
		MongoRepository<Appointment, String> {

	@Query(value = "{'datedOn':{$eq:'?0'}}")
	List<Appointment> findByDatedOn(String datedOn);

}
