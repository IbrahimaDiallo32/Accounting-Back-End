package superioraccountingsoftware.com.Accounting;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data //this creates getters/setters for methods and ultimately reduces amount of boilerplate code thats written
@AllArgsConstructor
@NoArgsConstructor
@Document(collection="JournalEntries")
public class Journalize {
    private String accountName;
    private double amount;
    private String entryType;
    private String uniqueID;
    private String fileURL;
    private String dateCreated;
    private String completedBy;
    private String status;
    private String dateRejected;
    private String reasonForRejection;
    private String rejectedBy;
    private String comments;
    private String dateApproved;
    private String journalType;
}
