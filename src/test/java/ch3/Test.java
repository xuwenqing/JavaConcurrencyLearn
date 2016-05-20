package ch3;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

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

        Thread[] participates = new Thread[10];

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

    //4.分开执行任务，最后聚合，CyclicBarrier
    @org.junit.Test
    public void cyclicBarrierTest() {
        int searchCount = 4;

        List<List<String>> results = new LinkedList<List<String>>();
        ResultProcessTask resultProcessTask = new ResultProcessTask(results);
        CyclicBarrier barrier = new CyclicBarrier(searchCount, resultProcessTask);

        SearchFile search1 = new SearchFile("c:/","readme.txt");
        SearchFile search2 = new SearchFile("c:/","log.txt");
        SearchFile search3 = new SearchFile("d:/","log.txt");
        SearchFile search4 = new SearchFile("d:/","readme.txt");

        SearchTask task1 = new SearchTask(barrier, search1, results);
        SearchTask task2 = new SearchTask(barrier, search2, results);
        SearchTask task3 = new SearchTask(barrier, search3, results);
        SearchTask task4 = new SearchTask(barrier, search4, results);

        Thread t1 = new Thread(task1);
        Thread t2 = new Thread(task2);
        Thread t3 = new Thread(task3);
        Thread t4 = new Thread(task4);

        t1.start();
        t2.start();
        t3.start();
        t4.start();

        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("完成") ;

    }

}
