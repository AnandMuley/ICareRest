package abm.icare.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import abm.icare.beans.PatientQueue;

public interface PatientQueueRepository extends
		MongoRepository<PatientQueue, String> {

	PatientQueue findByDatedOn(String datedOn);

	PatientQueue findByIdAndDatedOn(String id, String datedOn);

}
