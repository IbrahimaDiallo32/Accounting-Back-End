package superioraccountingsoftware.com.Accounting;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection="updateMe")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateMe {
    private ObjectId id;
    private String updateBody;

    public UpdateMe(String updateBody){
        this.updateBody = updateBody;
    }

}
