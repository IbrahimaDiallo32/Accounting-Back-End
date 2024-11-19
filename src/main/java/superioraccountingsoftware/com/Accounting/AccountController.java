package superioraccountingsoftware.com.Accounting;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.math.BigDecimal;
import java.util.HashMap;
import java.math.RoundingMode;
@RestController
@RequestMapping("/account") //the specified URL the following code wil work for
public class AccountController {
    @Autowired
    private AccountService accountService;

    @GetMapping
    public ResponseEntity<List<Accounts>> getAllAccounts() { //Getting request from user and returning a response
        return new ResponseEntity<>(accountService.getAllAccounts(), HttpStatus.OK); //gets all users from DB and giving it to API Layer
    }

    @GetMapping("/GetByAccountName/{accountName}")
    public ResponseEntity<Accounts> getByAccountName(@PathVariable String accountName) {
        return new ResponseEntity<>(accountService.findByAccountName(accountName), HttpStatus.OK);
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
    @GetMapping("/accountNum/{accountNumber}")
    public ResponseEntity<Accounts> getAccountByNum(@PathVariable int accountNumber) {
        return new ResponseEntity<>(accountService.findByAccountNum(accountNumber), HttpStatus.OK);
    }
    @GetMapping("/accountName")
    public List<String> getAllAccountsByName () {
        return accountService.getAllAccountNames();
    }
    @PostMapping("/create")
    public ResponseEntity<Accounts> createUser(@RequestBody Accounts account) {
        Accounts createdAccount = accountService.createNewAccount(account);
        return ResponseEntity.ok(createdAccount); // Return the created Account and HTTP 200 status
    }
    @PatchMapping("/edit/{accountNumber}")
    public ResponseEntity<Accounts> editAccount(@PathVariable int accountNumber, @RequestBody Map<String, Object> updates) {
        Accounts updatedAccount = accountService.patchAccount(accountNumber, updates);
        return ResponseEntity.ok(updatedAccount);
    }
    @DeleteMapping("/deleteByID/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable ObjectId id) {
        accountService.deleteAccountById(id);
        return ResponseEntity.noContent().build();  // Return 204 No Content on successful deletion
    }
    @GetMapping("/filter")
    public ResponseEntity<List<Accounts>> searchByField(@RequestParam String query){
        return new ResponseEntity<>(accountService.searchAccounts(query), HttpStatus.OK);
    }


    @GetMapping("/balance-sheet") //uses methods in the controller to grab all total balances, which are then referenced in the front end
    public ResponseEntity<Map<String, Object>> getBalanceSheet(){
        List<Accounts> assets = accountService.getAssets();
        List<Accounts> liabilities = accountService.getLiabilities();
        List<Accounts> equity = accountService.getEquity();

        BigDecimal totalAssets = accountService.getTotalAssets();
        BigDecimal totalLiabilities = accountService.getTotalLiabilities();
        BigDecimal totalEquity = accountService.getTotalEquity();

        Map<String, Object> balanceSheet = new HashMap<>();
        balanceSheet.put("assets", assets);
        balanceSheet.put("totalAssets", totalAssets);
        balanceSheet.put("liabilities", liabilities);
        balanceSheet.put("totalLiabilities", totalLiabilities);
        balanceSheet.put("equity", equity);
        balanceSheet.put("totalEquity", totalEquity);
        balanceSheet.put("totalLiabilitiesAndEquity", totalLiabilities.add(totalEquity));

        return ResponseEntity.ok(balanceSheet);
    }

    @GetMapping("/trial-balance")
    public ResponseEntity<Map<String,Object>> getTrialBalance(){
        List<Accounts> assets = accountService.getAssets();
        List<Accounts> liabilities = accountService.getLiabilities();
        List<Accounts> equity = accountService.getEquity();
        List<Accounts> Revenue = accountService.getRevenue();
        List<Accounts> Expense = accountService.getExpense();

        BigDecimal totalAssets = accountService.getTotalAssets();
        BigDecimal totalLiabilities = accountService.getTotalLiabilities();
        BigDecimal totalEquity = accountService.getTotalEquity();
        BigDecimal totalRevenue = accountService.getTotalRevenue();
        BigDecimal totalExpense = accountService.getTotalExpense();

        Map<String, Object> trialBalance = new HashMap<>();
        trialBalance.put("assets", assets);
        trialBalance.put("totalAssets", totalAssets);
        trialBalance.put("liabilities", liabilities);
        trialBalance.put("totalLiabilities", totalLiabilities);
        trialBalance.put("equity", equity);
        trialBalance.put("totalEquity", totalEquity);
        trialBalance.put("revenue", Revenue);
        trialBalance.put("totalRevenue", totalRevenue);
        trialBalance.put("expense", Expense);
        trialBalance.put("totalExpense", totalExpense);
        trialBalance.put("totalDebit", totalExpense.add(totalAssets));
        trialBalance.put("totalCredit", totalLiabilities.add(totalEquity.add(totalRevenue)));

        return ResponseEntity.ok(trialBalance);
    }

    @GetMapping("/income-statement") //this get function is also used for the retained earnings statement with comments next to what was added for it
    public ResponseEntity<Map<String,Object>> getIncomeStatement(){
        List<Accounts> Revenue = accountService.getRevenue();
        List<Accounts> Expense = accountService.getExpense();

        BigDecimal totalRevenue = accountService.getTotalRevenue();
        BigDecimal totalExpense = accountService.getTotalExpense();
        BigDecimal dividends = accountService.getDividens();
        BigDecimal beginingBalance = accountService.getBeginingRetainedEarnings();

        Map<String,Object> incomeStatement = new HashMap<>();
        incomeStatement.put("revenue", Revenue);
        incomeStatement.put("totalRevenue", totalRevenue);
        incomeStatement.put("expense", Expense);
        incomeStatement.put("totalExpense", totalExpense);
        incomeStatement.put("dividends", dividends);
        incomeStatement.put("beginingRetainedEarnings", beginingBalance);
        incomeStatement.put("netIncome", totalRevenue.subtract(totalExpense));

        return ResponseEntity.ok(incomeStatement);
    }
    @GetMapping("/dashboard")
    public ResponseEntity<Map<String,Object>> getDashboardRatios(){
        BigDecimal currentAssets = accountService.getTotalCurrentAssets();
        BigDecimal currentLiabilities = accountService.getTotalCurrentLiabilities();
        BigDecimal totalAssets = accountService.getTotalAssets();
        BigDecimal totalLiabilities = accountService.getTotalLiabilities();
        BigDecimal totalRevenue = accountService.getTotalRevenue();
        BigDecimal equity = accountService.getTotalEquity();
        BigDecimal expenses = accountService.getTotalExpense();

        Map<String,Object> dashboardRatios = new HashMap<>();
        dashboardRatios.put("currentRatio", (currentAssets.divide(currentLiabilities, 5, RoundingMode.HALF_UP)).multiply(BigDecimal.valueOf(100)).setScale(2, RoundingMode.HALF_UP));
        dashboardRatios.put("returnOnAssets", (totalRevenue.subtract(expenses)).divide(totalAssets, 5, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100)).setScale(2, RoundingMode.HALF_UP));
        dashboardRatios.put("netProfitMargin", (totalRevenue.subtract(expenses)).divide(totalRevenue, 5, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100)).setScale(2, RoundingMode.HALF_UP));
        dashboardRatios.put("returnOnEquity", (totalRevenue.subtract(expenses)).divide(equity, 5, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100)).setScale(2, RoundingMode.HALF_UP));
        dashboardRatios.put("assetTurnover", totalRevenue.divide(totalAssets, 5, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100)).setScale(2, RoundingMode.HALF_UP));
        dashboardRatios.put("quickRatio", currentAssets.divide(totalLiabilities, 5, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100)).setScale(2, RoundingMode.HALF_UP));

        return ResponseEntity.ok(dashboardRatios);
    }

}
