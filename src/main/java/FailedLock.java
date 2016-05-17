import java.util.concurrent.locks.*;

/**
 * Created by wenqing on 2016/5/16.
 * À¿À¯”Î«∂Ã◊π‹≥ÃÀ¯
 */
public class FailedLock {
    private Object lockObject = new Object();
    private boolean isLocked = false;
    private Thread currentThread = null;

    public synchronized void lock() throws InterruptedException {
        while(isLocked) {
            synchronized (lockObject) {
                lockObject.wait();
            }
        }
        isLocked = true;
        currentThread = Thread.currentThread();
    }

    public synchronized void unLock() {
        isLocked = false;
        synchronized (lockObject) {
            lockObject.notify();
        }

    }

    public static void main(String[] args) throws InterruptedException {
        FailedLock failedLock = new FailedLock();
        failedLock.lock();
        failedLock.lock();
        failedLock.unLock();

//        Runnable runnable = new MyRunnable();
//        Thread t1 = new Thread(runnable);
//        Thread t2 = new Thread(runnable);
    }
}

class MyRunnable implements java.lang.Runnable {

    private FailedLock failedLock;

    public MyRunnable(FailedLock failedLock) {
        this.failedLock = failedLock;
    }

    public void run() {

    }
}
