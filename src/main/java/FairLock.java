import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by wenqing on 2016/5/16.
 */

class QueueObject {
    private boolean isNotified = false;

    public synchronized void doWait() throws InterruptedException {
        while(!isNotified) {
            wait();
        }
        isNotified = false;
    }

    public synchronized void doNotify() {
        isNotified = true;
        notify();
    }
}

public class FairLock {

    private Queue<QueueObject> lockQueue = new LinkedList<QueueObject>();
    private boolean isLocked = false;

    public synchronized void lock() {

    }

    public synchronized void unlock() {
        isLocked = false;
        if(lockQueue.size() > 0)
           ;// lockQueue.
    }

}
