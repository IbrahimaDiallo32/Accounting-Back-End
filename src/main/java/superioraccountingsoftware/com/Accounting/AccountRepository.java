package superioraccountingsoftware.com.Accounting;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
//this interface talks to the database. Data access layer. Getting/retrieving
@Repository
public interface AccountRepository extends MongoRepository<Accounts, ObjectId> {
    Accounts findByAccountNumber(int accountNumber);
    void deleteById(ObjectId id);
}
