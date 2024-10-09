package superioraccountingsoftware.com.Accounting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EventsService {

    @Autowired
    private EventsRepository eventRepository;

    public List<Event> getAllLogs() {
        return eventRepository.findAll(); // Fetch all logs
    }
}