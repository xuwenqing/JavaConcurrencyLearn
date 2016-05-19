package ch2;

import java.util.concurrent.TimeUnit;

/**
 * Created by wenqing on 2016/5/19.
 */
public class PriceWriter implements Runnable {

    private PriceInfo priceInfo;

    public PriceWriter(PriceInfo priceInfo) {
        this.priceInfo = priceInfo;
    }

    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.printf("%s 尝试修改价格\n", Thread.currentThread().getName());
            priceInfo.write(Math.random() * 10, Math.random() * 10);
            System.out.printf("%s 修改价格完成\n", Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
