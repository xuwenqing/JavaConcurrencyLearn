/**
 * Created by wenqing on 2016/5/14.
 */
public class SimpleRunnable implements Runnable {

    private int timeout = 10;
    private static int count = 0;
    private final int id = count++;

    public void run() {
        while (timeout-- > 0) {
            System.out.print("id" + id + "----" + timeout);
            if(timeout == 0)
                System.out.println();
            Thread.yield();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
