package superioraccountingsoftware.com.Accounting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/hello")
public class UpdateMeController {
    @Autowired
    private UpdateMeService updateMeService;

//    @PostMapping
//    public ResponseEntity<UpdateMe> createUpdate(@RequestBody Map<String, String> newMap) {
//        return new ResponseEntity<UpdateMe>(updateMeService.createUpdate(newMap.get("updateBody"), newMap.get("userID")), HttpStatus.CREATED);
//    }
}
