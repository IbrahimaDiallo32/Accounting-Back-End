package superioraccountingsoftware.com.Accounting;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.data.mongodb.core.query.Query;

@Service
public class updateUserService {
    @Autowired
    private MongoTemplate mongoTemplate;

    public void updateFirstName(String userID, String newFirstName) {
        Query query = new Query(Criteria.where("userID").is(userID));
        Update update = new Update().set("firstName", newFirstName);
        mongoTemplate.updateFirst(query, update, User.class);
    }

}
