package abm.icare.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import abm.icare.beans.Medicine;

public interface MedicinesRepository extends MongoRepository<Medicine, String> {

}
