package ch3;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by OGC on 2016/5/20.
 */
public class Participate implements Runnable{

    private CountDownLatch latch;

    public Participate(CountDownLatch latch) {
        this.latch = latch;
    }

    public void run() {
        long time = (long) (Math.random() * 10000);
        System.out.printf("%s在%s到达会场，需要等待时间%s秒\n", Thread.currentThread().getName(), new java.util.Date(), time/1000);
        try {
            TimeUnit.SECONDS.sleep(time/1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        latch.countDown();
    }
}
