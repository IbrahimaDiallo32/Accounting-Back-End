package superioraccountingsoftware.com.Accounting;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "ChartOfAccounts")
public class Accounts {
    @Id
    private ObjectId id;
    private String accountName;
    private int accountNumber;
    private String accountDescription;
    private String normalSide;
    private String accountCategory;
    private String accountSubCategory;
    private double initialBalance;
    private double debit;
    private double credit;
    private double balance;
    private String accountCreatedDate;
    private ObjectId userObjectID; // Store the user ObjectId directly
    private int order;
    private String statement;
    private String comment;

    // If you still want a User reference, you can keep it
    private User user;

    // Getters and Setters
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
