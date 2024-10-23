package superioraccountingsoftware.com.Accounting;

import ch.qos.logback.core.LayoutBase;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class EventsController {

    @Autowired
    private EventsService eventsService;

    @Autowired
    private UserService userService;

    @Autowired
    private EventsRepository eventRepository;
    @Autowired
    private AccountRepository accountsRepository;

    Event event = new Event();
    public User savedUser;



    // Get all event logs
    @GetMapping("/events")
    public ResponseEntity<List<Event>> getAllEventLogs() {
        List<Event> logs = eventsService.getAllLogs();
        return ResponseEntity.ok(logs);
    }

    @PostMapping("/add")
    public ResponseEntity<Event> addUser(@RequestBody User newUser, @RequestParam String accountCreatedBy) {
        Event loggedEvent = userService.createNewUser(newUser, accountCreatedBy);
        return new ResponseEntity<>(loggedEvent, HttpStatus.CREATED);
    }

    @PostMapping("/event-logs")
    public ResponseEntity<Event> logEvent(@RequestBody User savedUser) {

        event.setUserId(savedUser.getUsername());
        event.setBeforeChange("N/A");
        event.setAfterChange("New user created");
        event.setEventType("USER_CREATED");
        event.setModifiedBy(savedUser.getUsername());
        event.setTimestamp(new Date());

        Event savedEvent = eventRepository.save(event);
        System.out.println("Event logged: " + savedEvent);

        return new ResponseEntity<>(savedEvent, HttpStatus.CREATED);
    }

    @PostMapping("/modify")
    public ResponseEntity<Event> modifyEvent(@RequestBody Map<String, Object> request) {
        String username = (String) request.get("username");
        Map<String, String> beforeChange = (Map<String, String>) request.get("beforeChange");
        Map<String, String> afterChange = (Map<String, String>) request.get("afterChange");
        String changeDescription = (String) request.get("changeDescription");


        event.setUserId(username);
        event.setBeforeChange(beforeChange.toString()); // You can log this in a different way if needed
        event.setAfterChange(changeDescription);  // Update this as per your logging requirement
        event.setEventType("USER_MODIFIED");
        event.setModifiedBy(username);
        event.setTimestamp(new Date());

        Event savedEvent = eventRepository.save(event);
        System.out.println("Event logged2: " + savedEvent);

        return new ResponseEntity<>(savedEvent, HttpStatus.CREATED);
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> request) {
        String username = request.get("username");

        // Check if user exists
        Optional<User> userOptional = userService.findUsername(username);

        if (userOptional.isPresent()) {
            User savedUser = userOptional.get();

            // Log the login event

            event.setUserId(savedUser.getUsername());
            event.setBeforeChange("~"); // Before the login, no user action was happening
            event.setAfterChange("User Logged In"); // After login, the user logged in
            event.setEventType("USER_LOGIN");
            event.setModifiedBy("~");
            event.setTimestamp(new Date());

            Event savedEvent = eventRepository.save(event);
            System.out.println("Event logged: " + savedEvent);

            return ResponseEntity.ok("Login successful");
        } else {
            // Return 401 if authentication fails
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login failed");
        }
    }
    @PostMapping("/account")
    public ResponseEntity<?> accounts(@RequestBody Accounts accounts) {


        // Log the account creation event
        Event event = new Event();
        event.setUserId(savedUser.getUsername()); // Use the savedUser from your authentication system
        event.setBeforeChange("~");
        event.setAfterChange("Account created");
        event.setEventType("ACC_CREATED");
        event.setModifiedBy(savedUser.getUsername());
        event.setTimestamp(new Date());

        // Save event to repository
        Event savedEvent = eventRepository.save(event);
        System.out.println("Event logged: " + savedEvent);

        return new ResponseEntity<>(savedEvent, HttpStatus.CREATED);
    }



    @PostMapping("/account/{id}")
    public ResponseEntity<?> updateAccount(
            @PathVariable("id") String accountId,
            @RequestBody Map<String, Object> request) {

        // Retrieve the account to be updated from the database
        Optional<Accounts> accountOptional = accountsRepository.findById(new ObjectId(accountId)); // Assuming you're using MongoDB and ObjectId
        if (!accountOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Account not found");
        }

        Accounts accountToUpdate = accountOptional.get();

        // Assuming the request is structured like this: { "accountNumber": "123", ... }
        // Update fields based on the provided request map
        if (request.containsKey("accountNumber")) {
            accountToUpdate.setAccountNumber((Integer) request.get("accountNumber")); // Change to Integer if accountNumber is an integer
        }
        if (request.containsKey("accountDescription")) {
            accountToUpdate.setAccountDescription((String) request.get("accountDescription"));
        }
        // Repeat for other fields that you want to update

        // Now update the account in the database
        Accounts updatedAccount = accountsRepository.save(accountToUpdate);

        // Log the event (ensuring savedUser is initialized properly)
         // Assuming this retrieves the currently logged-in user
        Event event = new Event();
        event.setUserId(savedUser.getUsername());
        event.setBeforeChange("~"); // Add more logic to track changes
        event.setAfterChange("ACCOUNT UPDATED");
        event.setEventType("ACC_MODIFIED");
        event.setModifiedBy(savedUser.getUsername());
        event.setTimestamp(new Date());

        Event savedEvent = eventRepository.save(event);
        System.out.println("Event logged: " + savedEvent);

        eventsService.log("ACCOUNT_MODIFIED", String.valueOf(updatedAccount.getAccountNumber()));

        return ResponseEntity.ok("Account updated successfully");
    }



    @PatchMapping("/users/{id}/deactivate")
    public ResponseEntity<?> deactivateUser(@PathVariable Long id) {
        Optional<User> userOptional = userService.singleUser(Math.toIntExact(id));

        if (!userOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        User user = userOptional.get();
        user.setActive(false);
        userService.save(Optional.of(user));

        Event event = new Event(); // Create a new Event instance
        event.setUserId(user.getUsername());
        event.setBeforeChange("~");
        event.setAfterChange("User Deactivated");
        event.setEventType("USER_DEACTIVATED");
        event.setModifiedBy(user.getUsername());
        event.setTimestamp(new Date());

        Event savedEvent = eventRepository.save(event);
        System.out.println("Event logged: " + savedEvent);

        eventsService.log("ACCOUNT_DEACTIVATED", user.getUsername());
        return ResponseEntity.ok("User account deactivated successfully");
    }

    @PostMapping("/log-error")
    public ResponseEntity<?> logError(@RequestBody ErrorLogRequest errorLogRequest) {
        Event errorLog = new Event(); // Create a new Event instance
        errorLog.setErrorMessage(errorLogRequest.getErrorMessage());
        errorLog.setUserId(errorLogRequest.getUserId());
        errorLog.setContext(errorLogRequest.getContext());
        errorLog.setBeforeChange("~");
        errorLog.setAfterChange("Error logged");
        errorLog.setEventType("ERROR");
        errorLog.setModifiedBy(errorLogRequest.getUserId());
        errorLog.setTimestamp(new Date());

        Event savedEvent = eventRepository.save(errorLog);
        System.out.println("Error Event logged: " + savedEvent);

        return ResponseEntity.ok("Error logged");
    }
}
