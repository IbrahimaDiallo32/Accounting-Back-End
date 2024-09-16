package superioraccountingsoftware.com.Accounting;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/updateUser")
public class updateUserController {
    @Autowired
    private updateUserService updateUserService;

    @PatchMapping("/{userID}")
    public ResponseEntity<Void> updateFirstName(@PathVariable String userID, @RequestParam String firstName) {
        updateUserService.updateFirstName(userID, firstName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
