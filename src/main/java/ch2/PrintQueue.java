package ch2;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by wenqing on 2016/5/19.
 */
public class PrintQueue {
    private final ReentrantLock queueLock = new ReentrantLock();

    public void print(Object content) {
        queueLock.lock();
        try {
            long duration = (long)(Math.random() * 10000);
            int seconds = (int)(duration / 1000);
            System.out.printf("线程%s开始打印，持续时间%d秒\n", Thread.currentThread().getName(), seconds);
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            queueLock.unlock();
        }

    }
}
