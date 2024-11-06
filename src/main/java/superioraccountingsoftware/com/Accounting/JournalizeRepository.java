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
public interface JournalizeRepository extends MongoRepository<Journalize, ObjectId> {
    List<Journalize> findByAccountName(String accountName);
    List<Journalize> findByStatus(String status);

    @Query("{ $or: [ { 'accountName': { $regex: ?0, $options: 'i' } }," + //search for all journal entries
            "{ 'status': { $regex: ?0, $options: 'i' } }," +
            " { $expr: { $regexMatch: { input: { $toString: '$amount' }, regex: ?0 } } }," +
            " { $expr: { $regexMatch: { input: { $toString: '$uniqueID' }, regex: ?0 } } } ] }")
    List<Journalize> searchByMultipleFields(String query);

    @Query("{ $and: [ { 'status': 'Approved' }, { $or: [ " +            //used to search on approved page
            "{ 'accountName': { $regex: ?0, $options: 'i' } }, " +
            "{ $expr: { $regexMatch: { input: { $toString: '$amount' }, regex: ?0 } } }, " +
            "{ $expr: { $regexMatch: { input: { $toString: '$uniqueID' }, regex: ?0 } } } " +
            "] } ] }")
    List<Journalize> searchByMultipleFieldsAndApproved(String query);

    @Query("{ $and: [ { 'status': 'Pending' }, { $or: [ " +             //used to search on pending page
            "{ 'accountName': { $regex: ?0, $options: 'i' } }, " +
            "{ $expr: { $regexMatch: { input: { $toString: '$amount' }, regex: ?0 } } }, " +
            "{ $expr: { $regexMatch: { input: { $toString: '$uniqueID' }, regex: ?0 } } } " +
            "] } ] }")
    List<Journalize> searchByMultipleFieldsAndPending(String query);

    @Query("{ $and: [ { 'status': 'Rejected' }, { $or: [ " +        //used to search on rejected page
            "{ 'accountName': { $regex: ?0, $options: 'i' } }, " +
            "{ $expr: { $regexMatch: { input: { $toString: '$amount' }, regex: ?0 } } }, " +
            "{ $expr: { $regexMatch: { input: { $toString: '$uniqueID' }, regex: ?0 } } } " +
            "] } ] }")
    List<Journalize> searchByMultipleFieldsAndRejected(String query);
}
