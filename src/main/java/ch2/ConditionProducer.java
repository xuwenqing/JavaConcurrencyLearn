package ch2;

import java.util.Date;

/**
 * Created by wenqing on 2016/5/19.
 */
public class ConditionProducer implements Runnable {

    private ConditionResources resources;

    public ConditionProducer(ConditionResources resources) {
        this.resources = resources;
    }

    public void run() {
        for (int i = 0; i < 100 ; i++) {
            resources.set(String.format("%s所生产的：%d 日期 ：%s\n", Thread.currentThread().getName(), i, new Date()));
        }
    }
}
