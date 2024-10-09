package superioraccountingsoftware.com.Accounting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class EventsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EventsRepository eventRepository;

    // Method to log the user creation event
    public Event logEvent(User savedUser) {

        Event event = new Event();
        event.setEventType("USER_CREATED");
        event.setUserId(savedUser.getUsername());
        event.setModifiedBy(savedUser.getUsername());
        event.setTimestamp(new Date());
        event.setBeforeChange("Before");
        event.setAfterChange("After");



        Event savedEvent = eventRepository.save(event);

        System.out.println("Event logged: " + savedEvent); // For debugging
        return savedEvent;
    }
    public Event logUserModification(User updatedUser, String beforeChange, String afterChange) {

        Event event = new Event();
        event.setUserId(updatedUser.getUsername()); // Use the username as the userId
        event.setModifiedBy(updatedUser.getUsername()); // The username of the person modifying
        event.setTimestamp(new Date()); // Set the event timestamp
        event.setEventType("USER_MODIFIED"); // Set the event type
        event.setCreatedBy("SYSTEM"); // Assuming the system or actual user creates the event

        event.setBeforeChange(beforeChange);
        event.setAfterChange(afterChange);

        Event savedEvent = eventRepository.save(event);

        System.out.println("Modification event logged: " + savedEvent);  // Debugging

        return savedEvent;
    }
    String getUserDetailsAsString(User user) {
        return "User[username=" + user.getUsername() +
                ", email=" + user.getEmail() +
                ", lastName=" + user.getLastName() +
                ", accountCreatedDate=" + user.getAccountCreatedDate() +
                ", isActive=" + user.isActive() + "]";
    }
//    public Event logAccountCreation(Accounts newAccount) {
//        Event event = new Event();
//        event.setEventType("ACCOUNT_CREATED");
//        event.setUserId(newAccount.getAccountNumber()); // Assuming account ID or some identifier is used
//        //event.setModifiedBy();
//        event.setTimestamp(new Date());
//        event.setBeforeChange("N/A");
//        event.setAfterChange("Account created: " + newAccount.toString());
//
//        return eventRepository.save(event);
//    }
//

    public List<Event> getAllLogs() {
        return eventRepository.findAll(); // Fetch all logs
    }

    public void logUserModification(User modifiedUser) {
    }
}