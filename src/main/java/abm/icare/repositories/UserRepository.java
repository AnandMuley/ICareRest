package abm.icare.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import abm.icare.beans.UserBean;

public interface UserRepository extends MongoRepository<UserBean, String> {

	UserBean findByUsernameAndPassword(String username, String password);

}
