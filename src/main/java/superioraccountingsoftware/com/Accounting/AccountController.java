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

    @GetMapping("/accountNumberASC")
    public ResponseEntity<List<Accounts>> getByAccountNumberASC() { //Getting request from user and returning a response
        return new ResponseEntity<>(accountService.getByAccountNumberASC(), HttpStatus.OK);
    }

    @GetMapping("/accountNumberDESC")
    public ResponseEntity<List<Accounts>> getByAccountNumberDESC() { //Getting request from user and returning a response
        return new ResponseEntity<>(accountService.getByAccountNumberDESC(), HttpStatus.OK);
    }

    @GetMapping("/accountNameASC")
    public ResponseEntity<List<Accounts>> getByAccountNameASC() { //Getting request from user and returning a response
        return new ResponseEntity<>(accountService.getByAccountNameASC(), HttpStatus.OK);
    }

    @GetMapping("/accountNameDESC")
    public ResponseEntity<List<Accounts>> getByAccountNameDESC() { //Getting request from user and returning a response
        return new ResponseEntity<>(accountService.getByAccountNameDESC(), HttpStatus.OK);
    }
    @GetMapping("/orderASC")
    public ResponseEntity<List<Accounts>> getByAccountOrderASC() { //Getting request from user and returning a response
        return new ResponseEntity<>(accountService.getByAccountOrderASC(), HttpStatus.OK);
    }
    @GetMapping("/orderDESC")
    public ResponseEntity<List<Accounts>> getByAccountOrderDESC() { //Getting request from user and returning a response
        return new ResponseEntity<>(accountService.getByAccountOrderDESC(), HttpStatus.OK);
    }
    @GetMapping("/accountCategoryASC")
    public ResponseEntity<List<Accounts>> getByAccountCategoryASC() { //Getting request from user and returning a response
        return new ResponseEntity<>(accountService.getByAccountCategoryASC(), HttpStatus.OK);
    }

    @GetMapping("/accountCategoryDESC")
    public ResponseEntity<List<Accounts>> getByAccountCategoryDESC() { //Getting request from user and returning a response
        return new ResponseEntity<>(accountService.getByAccountCategoryDESC(), HttpStatus.OK);
    }
    @GetMapping("/accountSubCategoryASC")
    public ResponseEntity<List<Accounts>> getByAccountSubCategoryASC() { //Getting request from user and returning a response
        return new ResponseEntity<>(accountService.getByAccountSubCategoryASC(), HttpStatus.OK);
    }

    @GetMapping("/accountSubCategoryDESC")
    public ResponseEntity<List<Accounts>> getByAccountSubCategoryDESC() { //Getting request from user and returning a response
        return new ResponseEntity<>(accountService.getByAccountSubCategoryDESC(), HttpStatus.OK);
    }
    @GetMapping("/balanceASC")
    public ResponseEntity<List<Accounts>> getByAccountBalanceASC() { //Getting request from user and returning a response
        return new ResponseEntity<>(accountService.getByAccountBalanceASC(), HttpStatus.OK);
    }
    @GetMapping("/balanceDESC")
    public ResponseEntity<List<Accounts>> getByAccountBalanceDESC() { //Getting request from user and returning a response
        return new ResponseEntity<>(accountService.getByAccountBalanceDESC(), HttpStatus.OK);
    }
}