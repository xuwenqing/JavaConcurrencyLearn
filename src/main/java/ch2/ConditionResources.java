package ch2;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by wenqing on 2016/5/19.
 */
public class ConditionResources {

    private ReentrantLock lock;
    private Condition condition;
    private Queue<String> resources;
    private int maxSize;

    public ConditionResources(int maxSize) {
        //公平锁，按照等待时间分配锁
        lock = new ReentrantLock(true);
        condition = lock.newCondition();
        resources = new LinkedList<String>();
        this.maxSize = maxSize;
    }

    public String get() {
        lock.lock();
        while (resources.size() == 0) {
            try {
                condition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            String resource = resources.poll();
            condition.signalAll();
            return resource;
        }
        finally {
            lock.unlock();
        }
    }

    public void set(String value) {
        lock.lock();
        while (resources.size() == maxSize) {
            condition.awaitUninterruptibly();//不可被中断
        }
        resources.add(value);
        condition.signalAll();
        lock.unlock();
    }

}
