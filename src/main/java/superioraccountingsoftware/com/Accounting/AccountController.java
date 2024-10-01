package superioraccountingsoftware.com.Accounting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account") //the specified URL the following code wil work for
public class AccountController {
    @Autowired
    private AccountService accountService;

    @GetMapping
    public ResponseEntity<List<Accounts>> getAllAccounts() { //Getting request from user and returning a response
        return new ResponseEntity<>(accountService.getAllAccounts(), HttpStatus.OK); //gets all users from DB and giving it to API Layer
    }
}
