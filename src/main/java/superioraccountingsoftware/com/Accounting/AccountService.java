package superioraccountingsoftware.com.Accounting;

import java.util.*;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.util.stream.Collectors;

//Class where most business logic will be written
@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;
    public List<Accounts> getAllAccounts() {
        return accountRepository.findAll(); //this method is in the MongoRepository class
    }
    public Accounts createNewAccount(Accounts account) { //creates new Account
        return accountRepository.save(account);
    }
    public Accounts findByAccountNum(int accountNumber){ //its optional because a user many not be returned
        return accountRepository.findByAccountNumber(accountNumber);
    }
    public List<String> getAllAccountNames(){                            //collecting all account names to display for Ledger of Accounts Selection
        List<Accounts> accounts = accountRepository.findDistinctAccountNames();

        return accounts.stream()
                .map(Accounts::getAccountName)
                .distinct()
                .collect(Collectors.toList());
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
    public List<Accounts> getByAccountBalanceDESC() {
        return accountRepository.findAll(Sort.by(Sort.Order.desc("balance")));
    }
    public void deleteAccountById(ObjectId id) {
        accountRepository.deleteById(id);
    }
    public List<Accounts> searchAccounts (String query){
            return accountRepository.searchByMultipleFields(query);
    }

    @Transactional
    public Accounts patchAccount(int accountNumber, Map<String, Object> updates) {
        // Retrieve the existing account
        Accounts account = accountRepository.findByAccountNumber(accountNumber);

        // Check if the account exists
        if (account == null) {
            throw new RuntimeException("Account with number " + accountNumber + " does not exist.");
        }

        // Proceed with patching the fields
        updates.forEach((key, value) -> {
            try {
                Field field = Accounts.class.getDeclaredField(key); // Get the field by name
                field.setAccessible(true); // Allow access to private fields

                // Handle casting and updating for different data types
                if (field.getType() == Double.class && value instanceof Integer) {
                    field.set(account, ((Integer) value).doubleValue()); // Convert Integer to Double
                } else {
                    field.set(account, value); // Set the field value directly for matching types
                }
            } catch (NoSuchFieldException | IllegalAccessException e) {
                throw new RuntimeException("Failed to update field: " + key, e);
            }
        });

        // Save the updated account back to the database (this should trigger an update)
        return accountRepository.save(account); // Ensure this line persists the changes
    }
}
