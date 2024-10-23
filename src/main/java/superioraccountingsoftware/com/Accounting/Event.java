package superioraccountingsoftware.com.Accounting;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Setter
@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Event")
public class Event {

    @Id
    private String id;  // This will store the random UUID

    private String eventType;
    @Getter
    @Setter
    private String userId;
    private String modifiedBy;
    private Date timestamp;
    private String beforeChange;
    private String afterChange;
    private String username;
    private String password;
    private String newPassword;
    private String confirmPassword;
    private String email;
    @Setter
    @Getter
    private String context;
    @Getter
    @Setter
    private String errorMessage;

    public Event(String userId, String eventType, Date timestamp, String modifiedBy, String beforeChange, String afterChange, String username, String password, String newPassword, String confirmPassword, String email, String Context) {
        this.userId = username;
        this.beforeChange = beforeChange;
        this.afterChange = afterChange;
        this.eventType = eventType;
        this.modifiedBy = username;
        this.timestamp = timestamp;
        this.context = context;

    }

    public Event(String hexString, Object o, Object o1, String userCreated, Object createdDate, String accountCreatedBy, Object o2, String username) {
    }

    public Event(String userId, String action, Date date, String createdBy, String username) {
    }

    // Getters and Setters

}
