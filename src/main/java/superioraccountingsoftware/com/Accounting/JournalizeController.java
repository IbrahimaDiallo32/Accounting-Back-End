package superioraccountingsoftware.com.Accounting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import java.util.List;
@RestController
@RequestMapping("/journal")
public class JournalizeController {
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private JournalizeService journalizeService;
    @PostMapping("/create")
    public ResponseEntity<Journalize> createJournal(@RequestBody Journalize journalize) {
        Journalize journalEntry = journalizeService.createJournalEntry(journalize);
        return ResponseEntity.ok(journalEntry); // Return the created user and HTTP 200 status
    }
    @GetMapping("/getAll")
    public ResponseEntity<List<Journalize>> getAllJournal() { //Getting request from user and returning a response
        return new ResponseEntity<>(journalizeService.getAllJournalizes(), HttpStatus.OK); //gets all users from DB and giving it to API Layer
    }
    @GetMapping("/account/{accountName}")
    public ResponseEntity<List<Journalize>> findByAccountName(@PathVariable String accountName){
        return new ResponseEntity<>(journalizeService.findByAccountName(accountName), HttpStatus.OK);
    }
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Journalize>> findByCurrentStatus(@PathVariable String status){
        return new ResponseEntity<>(journalizeService.findByCurrentStatus(status), HttpStatus.OK);
    }

    @PatchMapping("/updateStatus/{uniqueID}")
    public ResponseEntity<String> updateStatus(@PathVariable String uniqueID, @RequestBody StatusUpdateRequest request) {
        try {
            // Define the query to find documents with the specified uniqueID
            Query query = new Query(Criteria.where("uniqueID").is(uniqueID));
            Update update = new Update();

            // Use reflection to add all non-null fields from the request to the Update object
            BeanWrapper src = new BeanWrapperImpl(request);
            for (java.beans.PropertyDescriptor pd : src.getPropertyDescriptors()) {
                String propertyName = pd.getName();
                Object propertyValue = src.getPropertyValue(propertyName);
                if (propertyValue != null && !"class".equals(propertyName)) {
                    update.set(propertyName, propertyValue);
                }
            }

            // Update all matching documents
            var result = mongoTemplate.updateMulti(query, update, Journalize.class);

            if (result.getMatchedCount() > 0) {
                return ResponseEntity.ok("Updated fields for " + result.getModifiedCount() + " entries with uniqueID " + uniqueID);
            } else {
                return ResponseEntity.status(404).body("No entries found with uniqueID " + uniqueID);
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error updating journal entries: " + e.getMessage());
        }
    }
}