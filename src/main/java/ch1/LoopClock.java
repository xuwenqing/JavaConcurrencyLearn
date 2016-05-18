package ch1;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by OGC on 2016/5/18.
 */
public class LoopClock implements Runnable {

    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(new Date());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                System.out.println("LoopClock InterruptedException : " + e.getMessage());
            }
        }

    }
}
