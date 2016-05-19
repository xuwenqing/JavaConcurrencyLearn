package ch2;

/**
 * Created by wenqing on 2016/5/19.
 */
public class PrintJob implements Runnable {

    private PrintQueue printQueue;

    public PrintJob(PrintQueue printQueue) {
        this.printQueue = printQueue;
    }

    public void run() {
        System.out.printf("线程%s开始打印文档 \n", Thread.currentThread().getName());
        printQueue.print(new Object());
        System.out.printf("线程%s结束打印文档 \n", Thread.currentThread().getName());
    }
}
