package superioraccountingsoftware.com.Accounting;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.bson.types.ObjectId;

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
}
