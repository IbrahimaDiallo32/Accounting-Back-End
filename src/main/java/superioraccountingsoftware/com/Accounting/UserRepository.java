package superioraccountingsoftware.com.Accounting;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//this interface talks to the database. Data access layer. Getting/retrieving
@Repository
public interface UserRepository extends MongoRepository<User, ObjectId> {
    Optional<User> findUserById(ObjectId id);
    Optional<User> findUserByUserID(String userID);
    Optional<User> findUserByFirstName(String firstName);
}
