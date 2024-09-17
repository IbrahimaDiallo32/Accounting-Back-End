package superioraccountingsoftware.com.Accounting;
import java.util.*;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//Class where most business logic will be written
@Service
public class UserService {
    @Autowired //this will instantiate the userRepository class for us instead of having to use the constructor
    private UserRepository userRepository;
    public List<User> getAllUsers(){
        System.out.println(userRepository.findAll().toString());
        return userRepository.findAll(); //this method is in the MongoRepository class
    }
    public Optional<User> findId(ObjectId id){ //its optional because a user many not be returned
        return userRepository.findUserById(id);
    }
    public Optional<User> singleUser(String userId){ //its optional because a user many not be returned
        return userRepository.findUserByUserID(userId);
    }
    public Optional<User> firstName(String firstName){ //its optional because a user many not be returned
        return userRepository.findUserByFirstName(firstName);
    }
}
