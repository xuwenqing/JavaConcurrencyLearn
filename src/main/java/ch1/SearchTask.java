package ch1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Date;

import java.util.Random;
import java.util.concurrent.TimeUnit;


/**
 * Created by OGC on 2016/5/18.
 */
public class SearchTask implements Runnable{
    private Logger logger = LoggerFactory.getLogger(SearchTask.class);
    private String result;

    public void run() {
        logger.info(Thread.currentThread().getName() + "beginning run : " + new Date());
        try {
            doWork();
            result = Thread.currentThread().getName();
        } catch (InterruptedException e) {
            logger.error(Thread.currentThread().getName() + " InterruptedException " + e.getMessage());
        }

    }

    private void doWork() throws InterruptedException {
        Random random = new Random(new Date().getTime());
        int next = (int)( random.nextDouble() * 100 );
        logger.info("Thread " + Thread.currentThread().getName() + " time : " + next);
        TimeUnit.SECONDS.sleep(next);
    }
}
