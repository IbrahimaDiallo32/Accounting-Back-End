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

    public void setId(Object id) {
        this.id = (ObjectId) id;
    }
}
