package ch3;

import java.util.concurrent.CountDownLatch;

/**
 * Created by OGC on 2016/5/20.
 */
public class VideoConference implements Runnable {

    private CountDownLatch latch;

    public VideoConference(CountDownLatch latch) {
        this.latch = latch;
    }

    public void run() {
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("参会者到齐，开始开会。" + new java.util.Date());
    }
}
