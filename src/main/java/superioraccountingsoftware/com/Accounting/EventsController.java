package superioraccountingsoftware.com.Accounting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/event-logs")
public class EventsController {

    @Autowired
    private EventsService eventsService;

    @Autowired
    private UserService userService;

    // Get all event logs
    @GetMapping
    public ResponseEntity<List<Event>> getAllEventLogs() {
        List<Event> logs = eventsService.getAllLogs();
        System.out.println("Fetched logs from MongoDB: " + logs); // Debugging
        return ResponseEntity.ok(logs);
    }

    // Add a new user and log the event
    @PostMapping("/add")
    public ResponseEntity<User> addUser(@RequestBody User newUser) {
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
//    @PatchMapping("/modify")
//    public ResponseEntity<User> modifyUser(@RequestBody User updatedUser) {
//        User modifiedUser = userService.updateUser(updatedUser);
//        eventsService.logUserModification(modifiedUser);
//        return ResponseEntity.ok(modifiedUser);
//    }
}