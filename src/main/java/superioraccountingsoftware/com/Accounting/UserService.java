package superioraccountingsoftware.com.Accounting;
import java.lang.reflect.Field;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.bson.types.ObjectId;
import org.springframework.transaction.annotation.Transactional;

//Class where most business logic will be written
@Service
public class UserService {
    @Autowired //this will instantiate the userRepository class for us instead of having to use the constructor
    private UserRepository userRepository;

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

    @Transactional
    public User patchUser(String username, Map<String, Object> updates) {
        // Retrieve the existing user
        Optional<User> optionalUser = userRepository.findByUsername(username);

        // Check if the user exists
        if (!optionalUser.isPresent()) {
            throw new RuntimeException("User with username " + username + " does not exist.");
        }

        User user = optionalUser.get(); // Get the actual User object

        // Proceed with patching the fields
        updates.forEach((key, value) -> {
            try {
                Field field = User.class.getDeclaredField(key); // Get the field by name
                field.setAccessible(true); // Allow access to private fields

                // Handle casting and updating for different data types
                if (field.getType() == Double.class && value instanceof Integer) {
                    field.set(user, ((Integer) value).doubleValue()); // Convert Integer to Double if necessary
                } else {
                    field.set(user, value); // Set the field value directly for matching types
                }
            } catch (NoSuchFieldException | IllegalAccessException e) {
                throw new RuntimeException("Failed to update field: " + key, e);
            }
        });

        // Save the updated user back to the database (this should trigger an update)
        return userRepository.save(user); // Ensure this line persists the changes
    }

}
