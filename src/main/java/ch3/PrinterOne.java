package ch3;


/**
 * Created by OGC on 2016/5/20.
 */
public class PrinterOne implements Runnable {

    private PrintQueueOne printQueue;

    public PrinterOne(PrintQueueOne printQueue) {
        this.printQueue = printQueue;
    }

    public void run() {
        System.out.printf("%s开始打印%s\n", Thread.currentThread().getName(), new java.util.Date());
        printQueue.print(new java.util.Date());
        System.out.printf("%s结束打印%s\n", Thread.currentThread().getName(), new java.util.Date());
    }
}
