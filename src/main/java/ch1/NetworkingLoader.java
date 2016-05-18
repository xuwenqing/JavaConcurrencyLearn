package ch1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by OGC on 2016/5/18.
 */
public class NetworkingLoader implements Runnable {
    Logger logger = LoggerFactory.getLogger(NetworkingLoader.class);
    public void run() {
        logger.info("Network begin connecting : " +  new Date());
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info("Network end connecting : " + new Date());
    }
}
