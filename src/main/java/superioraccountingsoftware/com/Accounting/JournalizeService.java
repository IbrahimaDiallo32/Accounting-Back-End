package superioraccountingsoftware.com.Accounting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JournalizeService{

    @Autowired
    JournalizeRepository journalizeRepository;

    public Journalize createJournalEntry(Journalize journalize) {
        return journalizeRepository.save(journalize);
    }

    public List<Journalize> getAllJournalizes() {
        return journalizeRepository.findAll(); //this method is in the MongoRepository class
    }
}
