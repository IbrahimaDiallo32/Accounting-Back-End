package superioraccountingsoftware.com.Accounting;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
//this interface talks to the database. Data access layer. Getting/retrieving
@Repository
public interface JournalizeRepository extends MongoRepository<Journalize, ObjectId> {
    List<Journalize> findByAccountName(String accountName);
    List<Journalize> findByStatus(String status);
}
