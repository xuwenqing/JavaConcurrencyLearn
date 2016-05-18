package ch1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by OGC on 2016/5/18.
 */
public class DataSourceLoader implements Runnable {
    Logger logger = LoggerFactory.getLogger(DataSourceLoader.class);
    public void run() {
        logger.info("DataSource begin loading : " + new Date());
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info("DataSource end loading : " + new Date());
    }
}
