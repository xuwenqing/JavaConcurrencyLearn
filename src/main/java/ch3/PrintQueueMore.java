package ch3;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by OGC on 2016/5/20.
 */
public class PrintQueueMore {
    private Semaphore semaphore;
    private String[] printers;
    private boolean[] used;
    private ReentrantLock printersLock;

    public PrintQueueMore() {
        semaphore = new Semaphore(3);
        printers = new String[3];
        printers[0] = "打印机1";
        printers[1] = "打印机2";
        printers[2] = "打印机3";
        used = new boolean[3];
        printersLock = new ReentrantLock(true);
    }

    public void print(Object message) {
        semaphore.acquireUninterruptibly();
        int pos = getPrinter();
        long time = (long)(Math.random() * 10000);
        System.out.printf("%s正在%s打印，持续时间：%s秒\n", Thread.currentThread().getName(), printers[pos], time/1000);
        try {
            Thread.currentThread().sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        used[pos] = false;
        semaphore.release();
    }

    private int getPrinter() {
        printersLock.lock();
        try {
            int pos = 0;
            for(int i = 0; i < printers.length; i++) {
                if(!used[i]) {
                    used[i] = true;
                    pos = i;
                    break;
                }
            }
            return pos;
        }
        finally {
            printersLock.unlock();
        }
    }
}
