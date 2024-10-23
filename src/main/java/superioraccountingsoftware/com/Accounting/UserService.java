package superioraccountingsoftware.com.Accounting;
import java.lang.reflect.Field;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.bson.types.ObjectId;

//Class where most business logic will be written
@Service
public class UserService {
    @Autowired //this will instantiate the userRepository class for us instead of having to use the constructor
    private UserRepository userRepository;
    @Autowired
    private EventsRepository eventsRepository;


    public User createNewUser(User user) {
      return userRepository.save(user);
    }
    public List<User> getAllUsers(){
        return userRepository.findAll(); //this method is in the MongoRepository class
    }
    public Optional<User> findId(ObjectId id){ //its optional because a user many not be returned
        return userRepository.findUserById(id);
    }
    public Optional<User> findUsername(String username){
        return userRepository.findByUsername(username);
    }
    public Optional<User> singleUser(int userId){ //its optional because a user many not be returned
        return userRepository.findUserByUserID(userId);
    }
    public Optional<User> firstName(String firstName){ //its optional because a user many not be returned
        return userRepository.findUserByFirstName(firstName);
    }
    public Optional<User> findEmail(String email){
        return userRepository.findUserByEmail(email);
    }
    public boolean isUsernameAvailable(String username) {
        return !userRepository.existsByUsername(username);
    }

    public User patchUser(String username, Map<String, Object> updates) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User with username " + username + " does not exist."));
        // Proceed with patching the fields as before
        updates.forEach((key, value) -> {
            try {
                Field field = User.class.getDeclaredField(key);
                field.setAccessible(true);
                field.set(user, value); // Handle type conversion if necessary
            } catch (NoSuchFieldException | IllegalAccessException e) {
                throw new RuntimeException("Failed to update field: " + key, e);
            }
        });

        return userRepository.save(user);
    }


    public Event createEvent(String userId, String action, String createdBy, String username) {
        Event newEvent = new Event(
                userId,
                action,
                new Date(), // Current date for createdDate
                createdBy,
                username
        );

        // Save the event in the repository
        return eventsRepository.save(newEvent);
    }
    public Event createNewUser(User user, String accountCreatedBy) {
        user.setCreatedDate(new Date()); // Set the created date
        User savedUser = userRepository.save(user); // Save the user

        // Create and log the event after the user is successfully saved
        return createEvent(
                savedUser.getId().toString(), // Ensure correct userId
                "USER_CREATED",
                accountCreatedBy,
                savedUser.getUsername() // Pass the username for the event
        );
    }

    public void save(Optional<User> user) {
    }

    public User updateUser(Long id, UserUpdateRequest updateRequest) {
        return null;
    }
}
