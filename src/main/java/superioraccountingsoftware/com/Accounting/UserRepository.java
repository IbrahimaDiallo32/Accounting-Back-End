package superioraccountingsoftware.com.Accounting;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
//this interface talks to the database. Data access layer. Getting/retrieving
@Repository
public interface UserRepository extends MongoRepository<User, ObjectId> {
    Optional<User> findUserById(ObjectId id);
    Optional<User> findUserByUserID(int userID);
    Optional<User> findUserByFirstName(String firstName);
    Optional<User> findUserByEmail(String email);
    Optional<User> findByUsername(String username);
    boolean existsByUsername(String username);
}
