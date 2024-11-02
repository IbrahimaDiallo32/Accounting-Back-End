package superioraccountingsoftware.com.Accounting;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/hey") //the specified URL the following code wil work for
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EventsService eventsService;

    @PostMapping("/create")
    public ResponseEntity createUser(@RequestBody User user) {
        User createdUser = userService.createNewUser(user);
        return ResponseEntity.ok(createdUser); // Return user ID
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() { //Getting request from user and returning a response
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK); //gets all users from DB and giving it to API Layer
    }

    @GetMapping("/ID/{id}")
    public ResponseEntity<Optional<User>> getSingleUser(@PathVariable ObjectId id) {
        return new ResponseEntity<>(userService.findId(id), HttpStatus.OK);
    }
    @GetMapping("/username/{username}")
    public ResponseEntity<Optional<User>> getUserByUsername(@PathVariable String username) {
        return new ResponseEntity<>(userService.findUsername(username), HttpStatus.OK);
    }
    @GetMapping("/email/{email}")
    public ResponseEntity<Optional<User>> getUserByEmail(@PathVariable String email) {
        return new ResponseEntity<>(userService.findEmail(email), HttpStatus.OK);
    }
    @GetMapping("/userID/{userID}")
    public ResponseEntity<Optional<User>> getSingleUser(@PathVariable int userID) { //passing the information in the getMapping parameters
        return new ResponseEntity<>(userService.singleUser(userID), HttpStatus.OK);
    }
    @GetMapping("/firstName/{firstName}")
    public ResponseEntity<Optional<User>> getByFirstName(@PathVariable String firstName) {
        return new ResponseEntity<>(userService.firstName(firstName), HttpStatus.OK);
    }
    @GetMapping("/check-username")
    public ResponseEntity<Boolean> checkUsername(@RequestParam String username) {
        boolean isAvailable = userService.isUsernameAvailable(username);
        return ResponseEntity.ok(isAvailable);
    }

    @PatchMapping("/edit/{username}")
    public ResponseEntity<?> editUser(@PathVariable String username, @RequestBody User updatedUser) {
        User existingUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Capture the before-change state
        String beforeChange = existingUser.toString();

        // Update the user's details
        existingUser.setFirstName(updatedUser.getFirstName());
        existingUser.setLastName(updatedUser.getLastName());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setAccountType(updatedUser.getAccountType());
        existingUser.setAddress(updatedUser.getAddress());
        userRepository.save(existingUser);

        // Capture the after-change state
        String afterChange = existingUser.toString();

        // Log the event
        eventsService.log(existingUser.getId().toString(), "currentUsername", "USER_MODIFIED", beforeChange, afterChange);

        return ResponseEntity.ok("User updated successfully");
    }

}
