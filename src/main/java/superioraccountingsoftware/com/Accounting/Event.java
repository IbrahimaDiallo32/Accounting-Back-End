package superioraccountingsoftware.com.Accounting;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.bson.types.ObjectId;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Event")
public class Event {

    @Id
    private ObjectId id; // o
    private String userId; //
    private String beforeChange;
    private String afterChange;
    private String eventType;
    private String modifiedBy;
    private Date timestamp; // Time of the event
    private String createdBy; // Who created the event
    private String username;

    public Event(String userId, String beforeChange, String afterChange, String eventType, Date timestamp, String createdBy, String modifiedBy, String username) {
        this.userId = username;
        this.beforeChange = beforeChange;
        this.afterChange = afterChange;
        this.eventType = eventType;
        this.timestamp = timestamp;
        this.createdBy = createdBy;
        this.modifiedBy=modifiedBy;

    }

    // Helper constructor for simple events like USER_CREATED
    public Event(String userId, String eventType, Date timestamp, String createdBy, String username) {
        this.userId = getUsername();
        this.eventType = eventType;
        this.timestamp = timestamp;
        this.createdBy = createdBy;

    }
    public ObjectId getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = (ObjectId) id;
    }
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getEventType() { return eventType; }
    public void setEventType(String eventType) { this.eventType = eventType; }

    public String getModifiedBy() { return modifiedBy; }
    public void setModifiedBy(String modifiedBy) { this.modifiedBy = modifiedBy; }

    public Date getTimestamp() { return timestamp; }
    public void setTimestamp(Date timestamp) { this.timestamp = timestamp; }

    public String getCreatedBy() { return createdBy; }
    public void setCreatedBy(String createdBy) { this.createdBy = createdBy; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

}
