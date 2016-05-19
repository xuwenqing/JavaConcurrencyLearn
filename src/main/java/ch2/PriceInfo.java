package ch2;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by wenqing on 2016/5/19.
 */
public class PriceInfo {
    private double price1;
    private double price2;
    private ReentrantReadWriteLock lock;

    public PriceInfo() {
        price1 = price2 = 0;
        lock = new ReentrantReadWriteLock();
    }

    public double getPrice1() {
        lock.readLock().lock();
        try {
            return price1;
        }
        finally {
            lock.readLock().unlock();
        }
    }

    public double getPrice2() {
        lock.readLock().lock();
        try {
            return price2;
        }
        finally {
            lock.readLock().unlock();
        }
    }

    public void write(double price1, double price2) {
        lock.writeLock().lock();
        this.price1 = price1;
        this.price2 = price2;
        lock.writeLock().unlock();
    }

}
