package ch2;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by wenqing on 2016/5/19.
 */
public class EventStorage {
        private Queue<String> events;
    private int maxSize;

    public EventStorage(int maxSize) {
        this.maxSize = maxSize;
        events = new LinkedList<String>();
    }

    public synchronized void set(String event) {
        while(events.size() == maxSize) {
            try {
                System.out.printf("%s 开始等待 \n", Thread.currentThread().getName());
                wait();
                System.out.printf("%s 被唤醒 \n", Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        events.add(event);
        notifyAll();
    }

    public synchronized String get() {
        while (events.size() == 0) {
            try {
                System.out.printf("%s 开始等待 \n", Thread.currentThread().getName());
                wait();
                System.out.printf("%s 被唤醒 \n", Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        String event = events.poll();
        notifyAll();
        return event;
    }
}
