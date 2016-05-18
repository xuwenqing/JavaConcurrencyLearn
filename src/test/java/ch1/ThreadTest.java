package ch1;

import org.junit.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Deque;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * Created by wenqing on 2016/5/17.
 */
public class ThreadTest {

    //线程的创建和运行
    @Test
    public void basicTest() {
        for(int i = 1; i<10; i++)
            new Thread(new Calculator(i),"Thread "+i).start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void writeThreadInfo(PrintWriter pw,Thread thread,Thread.State oldStatus) {
        pw.printf("Main : Id %d - %s \n",thread.getId(),thread.getName());
        pw.printf("Main : Priority : %d \n", thread.getPriority());
        pw.printf("Main : old Status : %s \n",oldStatus);
        pw.printf("Main : new Status : %s \n",thread.getState());
        pw.printf("Main : ************************* \n",thread.getState());
    }

    //线程信息的获取和设置
    @Test
    public void threadStateTest() {
        Thread[] threads = new Thread[10];
        Thread.State[] states = new Thread.State[10];
        for (int i = 1; i<=10;i++) {
            threads[i-1] = new Thread(new Calculator(i),"thread "+i);
            threads[i-1].setPriority(i);
        }
        PrintWriter pw = null;
        FileWriter file = null;
        try {
            file = new FileWriter("log.txt");
            pw = new PrintWriter(file);
            for (int i = 1; i<=10;i++) {
                pw.println("Main : Status of thread "+ threads[i-1].getId() + " "+ threads[i-1].getState());
                states[i-1] = threads[i-1].getState();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 1; i<=10;i++) {
            threads[i-1].start();
        }

        boolean isFinish = false;
        while (!isFinish) {
            for(int i = 1; i<=10;i++) {
                writeThreadInfo(pw,threads[i-1],states[i-1]);
                if(threads[i-1].getState() != states[i-1]) {
                    states[i-1] = threads[i-1].getState();
                }
            }

            isFinish = true;
            for(int i = 1; i<10;i++) {
                isFinish = isFinish & threads[i-1].getState() == Thread.State.TERMINATED;
            }

        }
        pw.close();
    }

    //线程的中断
    @Test
    public void interruptTest() {
        Thread t = new Thread(new PrimeGenerator(),"PrimeGenerator Thread");
        t.start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t.interrupt();
    }

    //线程中断的控制
    @Test
    public void interruptExceptionControlTest() {
        FileSearch cFileSearch = new FileSearch("c://","readme.txt");
        Thread cSearchThread = new Thread(cFileSearch,"FileSearch Thread C");
        cSearchThread.start();

        FileSearch dFileSearch = new FileSearch("d://","readme.txt");
        Thread dSearchThread = new Thread(dFileSearch,"FileSearch Thread D");
        dSearchThread.start();

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        cSearchThread.interrupt();
    }

    //线程的休眠
    @Test
    public void loopClockTest() {
        LoopClock loopClock = new LoopClock();
        Thread clockThread = new Thread(loopClock);
        clockThread.start();

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        clockThread.interrupt();
    }

    //等待线程的终止
    @Test
    public void joinTest() {
        DataSourceLoader dataSourceLoader = new DataSourceLoader();
        NetworkingLoader networkingLoader = new NetworkingLoader();

        Thread t1 = new Thread(dataSourceLoader,"DataSourceLoader Thread");
        Thread t2 = new Thread(networkingLoader,"NetworkingLoader Thread");

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.printf("joinTest : resources loading finished %s.\n", new Date());
    }

    @Test
    public void joinTest2() {
        DataSourceLoader dataSourceLoader = new DataSourceLoader();
        NetworkingLoader networkingLoader = new NetworkingLoader();

        Thread t1 = new Thread(dataSourceLoader,"DataSourceLoader Thread");
        Thread t2 = new Thread(networkingLoader,"NetworkingLoader Thread");

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.printf("joinTest : resources loading finished %s.\n", new Date());
    }

    @Test
    public void joinTest3() {
        DataSourceLoader dataSourceLoader = new DataSourceLoader();
        NetworkingLoader networkingLoader = new NetworkingLoader();

        Thread t1 = new Thread(dataSourceLoader,"DataSourceLoader Thread");
        Thread t2 = new Thread(networkingLoader,"NetworkingLoader Thread");

        t1.start();
        t2.start();

        try {
            t2.join(4000);
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.printf("joinTest : resources loading finished %s.\n", new Date());
    }

    //守护线程的创建和运行
    @Test
    public void daemonTest() throws InterruptedException {
        Deque<Event> events =  new ConcurrentLinkedDeque<Event>();

        EventProducer producer = new EventProducer(events);
        for(int i = 0; i < 10; i++)
            new Thread(producer,"Thread Produce " + i).start();
        Thread cleaner = new EventCleaner(events);
        cleaner.start();

        TimeUnit.DAYS.sleep(1);
    }


    //线程组
    @Test
    public void threadGroupTest() throws InterruptedException {

        ThreadGroup group = new ThreadGroup("Search Group");

        SearchTask task = new SearchTask();
        for(int i = 0; i < 10; i++) {
            Thread t = new Thread(group, task);
            t.start();
            Thread.sleep(1000);
        }

        System.out.println(group.activeCount());
        group.list();

        while(group.activeCount()>9) {
            Thread.sleep(1000);
        }

        group.interrupt();

    }

    //自定义线程组以及定义捕获异常
    @Test
    public void threadGroupUncaughtExceptionTest() throws InterruptedException {
        ThreadGroup threadGroup = new MyThreadGroup("thread group test");

        ExceptionGenerator generator = new ExceptionGenerator();
        for(int i = 0; i < 5; i++) {
            Thread t = new Thread(threadGroup,generator);
            t.start();
        }
        TimeUnit.SECONDS.sleep(60);
    }

    //自定义线程工厂
    @Test
    public void threadFactoryTest() throws InterruptedException {
        MyThreadFactory factory = new MyThreadFactory("普通线程");
        Runnable runnable = new NormalRunnable();
        for(int i = 0; i < 5; i++) {
            Thread t = factory.newThread(runnable);
            t.start();
            TimeUnit.SECONDS.sleep(1);
        }
        System.out.printf("各个线程的创建状态 ： \n%s", factory.getStatus());
    }


}
