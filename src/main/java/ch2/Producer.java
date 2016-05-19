package ch2;

import java.util.Date;

/**
 * Created by wenqing on 2016/5/19.
 */
public class Producer implements Runnable {

    private EventStorage eventStorage;

    public Producer(EventStorage eventStorage) {
        this.eventStorage = eventStorage;
    }

    public void run() {
        for (int i = 0; i < 100 ; i++) {
            eventStorage.set(String.format("%s所生产的：%d 日期 ：%s\n", Thread.currentThread().getName(), i, new Date()));
        }
    }
}
