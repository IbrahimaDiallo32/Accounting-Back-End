package superioraccountingsoftware.com.Accounting;
public class AccountUserRequest {
    private Accounts accounts;
    private User savedUser;

    // Getters and setters
    public Accounts getAccounts() {
        return accounts;
    }

    public void setAccounts(Accounts accounts) {
        this.accounts = accounts;
    }

    public User getSavedUser() {
        return savedUser;
    }

    public void setSavedUser(User savedUser) {
        this.savedUser = savedUser;
    }
}
