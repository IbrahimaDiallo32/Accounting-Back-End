package superioraccountingsoftware.com.Accounting;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/hey") //the specified URL the following code wil work for
public class AccountingController {
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() { //Getting request from user and returning a response
        return new ResponseEntity<List<User>>(userService.getAllUsers(), HttpStatus.OK); //gets all users from DB and giving it to API Layer
    }

    @GetMapping("/ID/{id}")
    public ResponseEntity<Optional<User>> getSingleUser(@PathVariable ObjectId id) {
        return new ResponseEntity<Optional<User>>(userService.findId(id), HttpStatus.OK);
    }
    @GetMapping("/userID/{userID}")
    public ResponseEntity<Optional<User>> getSingleUser(@PathVariable String userID) { //passing the information in the getMapping parameters
        return new ResponseEntity<Optional<User>>(userService.singleUser(userID), HttpStatus.OK);
    }
    @GetMapping("/firstName/{firstName}")
    public ResponseEntity<Optional<User>> getByFirstName(@PathVariable String firstName) {
        return new ResponseEntity<Optional<User>>(userService.firstName(firstName), HttpStatus.OK);
    }
}
