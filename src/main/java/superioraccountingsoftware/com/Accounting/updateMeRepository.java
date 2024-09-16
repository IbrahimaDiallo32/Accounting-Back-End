package superioraccountingsoftware.com.Accounting;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface updateMeRepository extends MongoRepository<UpdateMe, ObjectId> {
}
