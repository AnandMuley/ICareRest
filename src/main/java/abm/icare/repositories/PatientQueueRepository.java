package abm.icare.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import abm.icare.beans.PatientQueue;


public interface PatientQueueRepository extends
		MongoRepository<PatientQueue, String> {
		
}
