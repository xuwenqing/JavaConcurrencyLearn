package ch1;


import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by wenqing on 2016/5/18.
 */
public class ExceptionGenerator implements Runnable {

    public void run() {
        System.out.printf("线程 %s 开始运行。\n", Thread.currentThread().getId());
        Random random = new Random(Thread.currentThread().getId());
        int result = 1000 / ((int)(random.nextDouble() * 1000));
        while (true) {
            result = 1000 / ((int)(random.nextDouble() * 1000));
            System.out.printf("线程 %s 运行结果 ： %d。\n", Thread.currentThread().getId(), result);
            if(Thread.currentThread().isInterrupted()) {
                System.out.printf("线程 %s 响应中断，停止执行。 \n", Thread.currentThread().getId());
                return;
            }
            Thread.yield();
        }
    }
}
