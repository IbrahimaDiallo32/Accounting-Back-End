package superioraccountingsoftware.com.Accounting;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
public class UpdateMeService {
    @Autowired
    private updateMeRepository repository;
    @Autowired
    private MongoTemplate mongoTemplate;
//    public UpdateMe createUpdate(String updateBody, String userID){
//        UpdateMe updateMe = repository.insert(new UpdateMe(updateBody)); //the insert() function returns the data you just put into your database. This loc is better than updateMe = new UpdateMe(updateBody);
//
//        repository.insert(updateMe);
//        mongoTemplate.update(UpdateMe.class)
//                .matching(Criteria.where("userID").is(userID)) //this updates the updateMe column in the database where the userID's match
//                .apply(new Update().push("updateMe").value(updateMe))
//                .first();//this has the job of making the change in the database
//        return updateMe;
//    }
}
