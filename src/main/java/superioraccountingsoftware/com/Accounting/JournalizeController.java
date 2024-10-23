package superioraccountingsoftware.com.Accounting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/journal")
public class JournalizeController {

    @Autowired
    private JournalizeService journalizeService;

    @GetMapping("/account/{accountName}")
    public ResponseEntity<List<Journalize>> findByAccountName(@PathVariable String accountName){
            return new ResponseEntity<>(journalizeService.findByAccountName(accountName), HttpStatus.OK);
    }
    @GetMapping("/status/{currentStatus}")
    public ResponseEntity<List<Journalize>> findByCurrentStatus(@PathVariable String currentStatus){
        return new ResponseEntity<>(journalizeService.findByCurrentStatus(currentStatus), HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<Journalize> createJournal(@RequestBody Journalize journalize) {
        Journalize journalEntry = journalizeService.createJournalEntry(journalize);
        return ResponseEntity.ok(journalEntry); // Return the created user and HTTP 200 status
    }
    @GetMapping("/getAll")
    public ResponseEntity<List<Journalize>> getAllJournal() { //Getting request from user and returning a response
        return new ResponseEntity<>(journalizeService.getAllJournalizes(), HttpStatus.OK); //gets all users from DB and giving it to API Layer
    }
}
