package superioraccountingsoftware.com.Accounting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class EventsController {

    @Autowired
    private EventsService eventsService;

    @Autowired
    private UserService userService;

    // Get all event logs
    @GetMapping("/getAll")
    public ResponseEntity<List<Event>> getAllEvents() { //Getting request from user and returning a response
        return new ResponseEntity<>(eventsService.getAllLogs(), HttpStatus.OK); //gets all users from DB and giving it to API Layer
    }

    @PostMapping("/create")
    public ResponseEntity createEvent(@RequestBody Event event) {
        Event createdEvent = eventsService.createNewLog(event);
        return ResponseEntity.ok(createdEvent); // Return user ID
    }

    @PostMapping("/add")
    public ResponseEntity<Event> addUser(@RequestBody User newUser, @RequestParam String accountCreatedBy) {
        Event loggedEvent = userService.createNewUser(newUser, accountCreatedBy);
        return new ResponseEntity<>(loggedEvent, HttpStatus.CREATED);
    }
}
