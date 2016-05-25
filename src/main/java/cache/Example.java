package cache;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by wenqing on 2016/5/25.
 */
public class Example {

    public static void main(String[] args) {
        Computable<Integer, String> computable = new SimpleComputable<Integer, String>();
        CachePool<Integer, String> cache = new CachePool<Integer, String>(computable);

        int num = 5;
        CountDownLatch startLatch = new CountDownLatch(1);
        CountDownLatch endLatch = new CountDownLatch(num);

        for(int i = 0; i < num; i++) {
            Thread t = new Thread(new RunTask(cache, startLatch, endLatch));
            t.start();
        }

        startLatch.countDown();

        System.out.println(System.currentTimeMillis());

        try {
            endLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(System.currentTimeMillis());


    }
}

class RunTask implements Runnable {

    private CachePool<Integer, String> cache;
    private CountDownLatch startLatch;
    private CountDownLatch endLatch;

    public RunTask(CachePool<Integer, String> cache, CountDownLatch startLatch, CountDownLatch endLatch) {
        this.cache = cache;
        this.startLatch = startLatch;
        this.endLatch = endLatch;
    }


    public void run() {
        try {
            startLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Random random = new Random(10);
        for(int i = 0; i < 100; i++) {
            cache.compute(random.nextInt());
        }
        endLatch.countDown();
    }
}

class SimpleComputable<Integer, String> implements Computable<Integer, String> {

    public String compute(Integer args) {

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return (String) args.toString();
    }
}
