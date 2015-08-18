package abm.icare.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import abm.icare.beans.Visit;

public interface VisitsRepository extends MongoRepository<Visit, String> {

	List<Visit> findByPatientId(String patientId);

}
