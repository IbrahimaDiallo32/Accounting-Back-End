package superioraccountingsoftware.com.Accounting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
@Service
public class JournalizeService{
    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    JournalizeRepository journalizeRepository;
    public Journalize createJournalEntry(Journalize journalize) {
        return journalizeRepository.save(journalize);
    }
    public List<Journalize> getAllJournalizes() {
        return journalizeRepository.findAll(); //this method is in the MongoRepository class
    }
    public List<Journalize> findByAccountName (String accountName){
        return journalizeRepository.findByAccountName(accountName);
    }
    public List<Journalize> findByCurrentStatus (String status){
        return journalizeRepository.findByStatus(status);
    }

    public List<Journalize> searchAccounts (String query){
        return journalizeRepository.searchByMultipleFields(query);
    }
    public List<Journalize> searchApproved (String query){
        return journalizeRepository.searchByMultipleFieldsAndApproved(query);
    }
    public List<Journalize> searchPending (String query){
        return journalizeRepository.searchByMultipleFieldsAndPending(query);
    }
    public List<Journalize> searchRejected (String query){
        return journalizeRepository.searchByMultipleFieldsAndRejected(query);
    }
}