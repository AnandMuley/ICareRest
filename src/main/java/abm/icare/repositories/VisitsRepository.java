package abm.icare.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import abm.icare.beans.Visit;

public interface VisitsRepository extends MongoRepository<Visit, String> {

	
}
