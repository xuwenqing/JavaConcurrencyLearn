package ch1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.Deque;
import java.util.concurrent.TimeUnit;

/**
 * Created by OGC on 2016/5/18.
 */
public class EventProducer implements Runnable {
    private Logger logger = LoggerFactory.getLogger(EventProducer.class);
    private Deque<Event> events;

    public EventProducer(Deque<Event> events) {
        this.events = events;
    }

    public void run() {
        for(int i = 0; i < 1000; i++) {
            Event event = new Event();
            event.setDate(new Date());
            event.setValue(String.format("%s generated value : %s", Thread.currentThread().getName(), new Date()));
            events.addFirst(event);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                logger.error(e.getStackTrace()+"");
            }
        }
    }
}
