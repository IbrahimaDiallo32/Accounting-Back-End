package superioraccountingsoftware.com.Accounting;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;



@Repository
public interface EventsRepository extends MongoRepository<Event, ObjectId> {

    // Find Event by ObjectId
    Optional<Event> findById(ObjectId id);

}