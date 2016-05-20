package ch3;

import java.util.concurrent.Semaphore;

/**
 * Created by OGC on 2016/5/20.
 */
public class PrintQueueOne {

    private Semaphore semaphore;

    public PrintQueueOne() {
        semaphore = new Semaphore(1);
    }

    public void print(Object message) {
        semaphore.acquireUninterruptibly();
        long time = (long)(Math.random() * 10000);
        System.out.printf("%s正在打印，持续时间：%s秒\n", Thread.currentThread().getName(), time/1000);
        try {
            Thread.currentThread().sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        semaphore.release();
    }

}
