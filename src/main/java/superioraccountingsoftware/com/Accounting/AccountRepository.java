package superioraccountingsoftware.com.Accounting;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
//this interface talks to the database. Data access layer. Getting/retrieving
@Repository
public interface AccountRepository extends MongoRepository<Accounts, ObjectId> {
    Accounts findByAccountNumber(int accountNumber);
    Accounts findByAccountName(String accountName);
    List<Accounts> findByAccountCategory(String category);
    List<Accounts> findByAccountSubCategory(String category);

    @Query(value = "{}", fields = "{'accountName' : 1}")
    List<Accounts> findDistinctAccountNames();

    @Query("{ $or: [ { 'accountName': { $regex: ?0, $options: 'i' } }," +
            " { 'accountCategory': { $regex: ?0, $options: 'i' } }," +
            " { 'accountSubCategory': { $regex: ?0, $options: 'i' } }," +
            " { $expr: { $regexMatch: { input: { $toString: '$balance' }, regex: ?0 } } }," +
            " { $expr: { $regexMatch: { input: { $toString: '$accountNumber' }, regex: ?0 } } }," +
            " { $expr: { $regexMatch: { input: { $toString: '$order' }, regex: ?0 } } } ] }")
    List<Accounts> searchByMultipleFields(String query);
    void deleteById(ObjectId id);
}
