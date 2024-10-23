package superioraccountingsoftware.com.Accounting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Journalize")
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
}
