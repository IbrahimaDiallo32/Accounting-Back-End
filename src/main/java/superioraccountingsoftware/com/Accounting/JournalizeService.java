package superioraccountingsoftware.com.Accounting;

import java.util.*;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
@Service
public class JournalizeService {

    @Autowired
    private JournalizeRepository journalizeRepository;


    public List<Journalize> findByAccountName (String accountName){
        return journalizeRepository.findByAccountName(accountName);
    }

    public List<Journalize> findByCurrentStatus (String currentStatus){
        return journalizeRepository.findByCurrentStatus(currentStatus);
    }
}
