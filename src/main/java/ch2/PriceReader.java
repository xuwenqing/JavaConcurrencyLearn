package ch2;

import java.util.concurrent.TimeUnit;

/**
 * Created by wenqing on 2016/5/19.
 */
public class PriceReader implements Runnable {

    private PriceInfo priceInfo;

    public PriceReader(PriceInfo priceInfo) {
        this.priceInfo = priceInfo;
    }

    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.printf("%s get Price1 : %f\n", Thread.currentThread().getName(), priceInfo.getPrice1());
            System.out.printf("%s get Price2 : %f\n", Thread.currentThread().getName(), priceInfo.getPrice2());
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
