package superioraccountingsoftware.com.Accounting;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface updateUserRepository extends MongoRepository<User, ObjectId>{
    // Custom query to get the first letter of the firstName
    @Query(value = "{ 'firstName': ?0 }", fields = "{ 'firstName': 1 }")
    List<User> findFirstLetterOfFirstName(String firstName);
}
