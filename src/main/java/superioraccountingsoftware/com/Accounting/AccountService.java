package superioraccountingsoftware.com.Accounting;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

//Class where most business logic will be written
@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    public List<Accounts> getAllAccounts() {
        return accountRepository.findAll(); //this method is in the MongoRepository class
    }
    public List<Accounts> getByAccountNumberASC(){
        return accountRepository.findAll(Sort.by(Sort.Order.asc("accountNumber")));
    }
    public List<Accounts> getByAccountNumberDESC(){
        return accountRepository.findAll(Sort.by(Sort.Order.desc("accountNumber")));
    }
    public List<Accounts> getByAccountNameASC(){
        return accountRepository.findAll(Sort.by(Sort.Order.asc("accountName")));
    }
    public List<Accounts> getByAccountNameDESC(){
        return accountRepository.findAll(Sort.by(Sort.Order.desc("accountName")));
    }
    public List<Accounts> getByAccountOrderASC(){
        return accountRepository.findAll(Sort.by(Sort.Order.asc("order")));
    }
    public List<Accounts> getByAccountOrderDESC(){
        return accountRepository.findAll(Sort.by(Sort.Order.desc("order")));
    }
    public List<Accounts> getByAccountCategoryASC(){
        return accountRepository.findAll(Sort.by(Sort.Order.asc("accountCategory")));
    }
    public List<Accounts> getByAccountCategoryDESC(){
        return accountRepository.findAll(Sort.by(Sort.Order.desc("accountCategory")));
    }
    public List<Accounts> getByAccountSubCategoryASC(){
        return accountRepository.findAll(Sort.by(Sort.Order.asc("accountSubCategory")));
    }
    public List<Accounts> getByAccountSubCategoryDESC(){
        return accountRepository.findAll(Sort.by(Sort.Order.desc("accountSubCategory")));
    }
    public List<Accounts> getByAccountBalanceASC(){
        return accountRepository.findAll(Sort.by(Sort.Order.asc("balance")));
    }
    public List<Accounts> getByAccountBalanceDESC(){
        return accountRepository.findAll(Sort.by(Sort.Order.desc("balance")));
    }
}
