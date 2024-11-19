package superioraccountingsoftware.com.Accounting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class EventsService {

    @Autowired
    private EventsRepository eventRepository;

    public Event createNewLog(Event event) {
        return eventRepository.save(event);
    }
    public List<Event> getAllLogs() {
        return eventRepository.findAll(); // Fetch all logs
    }

    public void log(String userId, String createdBy, String eventType, String beforeChange, String afterChange) {
        Event event = new Event();
        event.setUserId(userId);
        event.setBeforeChange(beforeChange);
        event.setAfterChange(afterChange);
        event.setEventType(eventType);
        event.setModifiedBy(createdBy);
        event.setTimestamp(new Date()); // Log the current timestamp

        eventRepository.save(event);
    }
}