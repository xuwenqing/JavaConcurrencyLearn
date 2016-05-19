package ch2;

import org.junit.Test;

import java.io.PrintWriter;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

/**
 * Created by wenqing on 2016/5/19.
 */
public class SynchronizedTest {
    @Test
    public void waitNotifyTest() {
        EventStorage eventStorage = new EventStorage(10);
        Producer producer = new Producer(eventStorage);
        Consumer consumer = new Consumer(eventStorage);

        Thread t1 = new Thread(producer,"生产者线程");
        Thread t2 = new Thread(consumer,"消费者线程");

        t1.start();
        t2.start();

        try {
            TimeUnit.HOURS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void lockTest() {
        //使用锁实现同步，模拟打印队列
        PrintQueue queue = new PrintQueue();
        PrintJob job = new PrintJob(queue);
        for (int i = 0; i < 20; i++) {
            Thread t = new Thread(job);
            t.start();
        }

        try {
            TimeUnit.HOURS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void readWriteLockTest() {
        //读写锁测试
        //使用读写锁实现同步数据访问,读写者
        //使用ReadWirteLock接口控制对价格对象的访问，价格对象存储两个产品的价格

        PriceInfo priceInfo = new PriceInfo();

        PriceReader priceReader = new PriceReader(priceInfo);
        PriceWriter priceWriter = new PriceWriter(priceInfo);

        Thread[] readers = new Thread[5];
        for (int i = 0; i < 5; i++) {
            readers[i] = new Thread(priceReader);
        }

        Thread writer = new Thread(priceWriter);

        writer.start();
        for (int i = 0; i < 5; i++) {
            readers[i].start();
        }

        try {
            TimeUnit.HOURS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void conditionTest() {
        ConditionResources resources = new ConditionResources(10);
        ConditionProducer producer = new ConditionProducer(resources);
        ConditionConsumer consumer = new ConditionConsumer(resources);

        Thread t1 = new Thread(producer,"生产者线程");
        Thread t2 = new Thread(consumer,"消费者线程");

        t1.start();
        t2.start();

        try {
            TimeUnit.HOURS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
