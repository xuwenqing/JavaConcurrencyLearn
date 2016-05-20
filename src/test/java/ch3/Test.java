package ch3;

import java.util.concurrent.CountDownLatch;

/**
 * Created by wenqing on 2016/5/19.
 */
public class Test {
    //1.使用semaphore实现一个打印机的管理
    @org.junit.Test
    public void testPrintOne() throws InterruptedException {
        PrintQueueOne printqueue = new PrintQueueOne();
        PrinterOne printer = new PrinterOne(printqueue);

        Thread[] threads = new Thread[5];
        for (int i = 0; i < threads.length; i++)
            threads[i] = new Thread(printer);
        for (int i = 0; i < threads.length; i++)
            threads[i].start();

        for (int i = 0; i < threads.length; i++)
            threads[i].join();
        System.out.println("打印完成");
    }


    //2.使用semaphore实现3个打印机的管理
    @org.junit.Test
    public void testPrintMore() throws InterruptedException {
        PrintQueueMore printqueue = new PrintQueueMore();
        PrinterMore printer = new PrinterMore(printqueue);

        Thread[] threads = new Thread[10];
        for (int i = 0; i < threads.length; i++)
            threads[i] = new Thread(printer);
        for (int i = 0; i < threads.length; i++)
            threads[i].start();

        for (int i = 0; i < threads.length; i++)
            threads[i].join();
        System.out.println("打印完成");
    }

    //3.使用CountDownLatch实现视频会议系统
    //3.1 会议线程，等待会议人数全部到达，CountDownLatch.await开始会议
    //3.2 参与者线程，随机制定到达时间，然后CountDownLatch--
    @org.junit.Test
    public void countDownLatchTest() throws InterruptedException {

        Thread[] participates = new Thread[5];

        CountDownLatch latch = new CountDownLatch(participates.length);

        VideoConference videoConference = new VideoConference(latch);
        Thread conference = new Thread(videoConference);

        Participate participate = new Participate(latch);
        for (int i = 0; i < participates.length; i++)
            participates[i] = new Thread(participate,"参会者"+i);

        conference.start();
        for (int i = 0; i < participates.length; i++)
            participates[i].start();

        for (int i = 0; i < participates.length; i++)
            participates[i].join();
        conference.join();

        System.out.println("完成");

    }

    //4.在集合点的同步
}
