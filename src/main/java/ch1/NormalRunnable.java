package ch1;

import java.util.concurrent.TimeUnit;

/**
 * Created by wenqing on 2016/5/18.
 */
public class NormalRunnable implements Runnable {
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
