package superioraccountingsoftware.com.Accounting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import java.util.Date;
import java.util.List;

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

    Event event = new Event();

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

    @PostMapping("/event-logs")
    public ResponseEntity<Event> logEvent(@RequestBody User savedUser) {
        event.setUserId(savedUser.getUsername());
        event.setBeforeChange("N/A");
        event.setAfterChange("New user created");
        event.setEventType("USER_CREATED");
        event.setModifiedBy(savedUser.getUsername());
        event.setTimestamp(new Date());
        Event savedEvent = eventRepository.save(event);
        return new ResponseEntity<>(savedEvent, HttpStatus.CREATED);
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
