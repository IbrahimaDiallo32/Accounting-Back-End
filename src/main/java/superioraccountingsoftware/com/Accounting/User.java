package superioraccountingsoftware.com.Accounting;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data //this creates getters/setters for methods and ultimately reduces amount of boilerplate code thats written
@AllArgsConstructor
@NoArgsConstructor
@Document(collection="Users") //this line is what will reference the MongoDB Collection Name
public class User {
    @Id
    private ObjectId id; //ObjectId is a class commonly used in MongoDB to uniquely identify documents within a collection making it globally unique.
    private String userID; //it matters what you name these variables, they match up with the database variable names
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;
    private String accountCreatedDate;
    private String accountType;
    private String address;
    private String dateOfBirth;
    private boolean isActive;
    private List<UpdateMe> updateMe;
}
