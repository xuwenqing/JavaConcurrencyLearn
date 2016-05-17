/**
 * Created by wenqing on 2016/5/16.
 */
public class Lock {
    private boolean isLocked = false;
    private Thread currentThread = null;

    synchronized void lock() throws InterruptedException {
        while(isLocked) {//������ź�
            wait();
        }
        isLocked = true;
        currentThread = Thread.currentThread();
    }

    synchronized void unlock() {
        if(Thread.currentThread() != currentThread) {
            throw new IllegalMonitorStateException("Calling thread has not locked this lock");

        }
        isLocked = false;//����źŶ�ʧ
        currentThread = null;
        notify();
    }
}
