package abm.icare.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import abm.icare.beans.Patient;

public interface PatientRepository extends MongoRepository<Patient, String> {

}
