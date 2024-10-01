package superioraccountingsoftware.com.Accounting;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//Class where most business logic will be written
@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    public List<Accounts> getAllAccounts() {
        return accountRepository.findAll(); //this method is in the MongoRepository class
    }

}
