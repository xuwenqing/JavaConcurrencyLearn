package ch1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.Deque;

/**
 * Created by OGC on 2016/5/18.
 */
public class EventCleaner extends Thread {

    private Logger logger = LoggerFactory.getLogger(EventCleaner.class);
    private Deque<Event> events;

    public EventCleaner(Deque<Event> events) {
        this.events = events;
        setDaemon(true);
    }

    public void run() {
        while(true) {
            Date date = new Date();
            clean(date);
        }
    }

    private void clean(Date date) {
        if(events.size() == 0)
            return;
        boolean delete = false;
        long difference = 0;
        do {
            Event event = events.getLast();
            difference = date.getTime() - event.getDate().getTime();
            if(difference > 10000) {
                logger.info("Cleaner : " + event.getValue());
                events.removeLast();
                delete = true;
            }
        }while (difference > 10000);

        if(delete) {
            logger.info("Cleaner : size of queue " + events.size());
        }
    }
}
