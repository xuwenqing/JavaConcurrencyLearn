/**
 * Created by wenqing on 2016/5/14.
 */
public class TestThread {
    public static void main(String[] args) {

        for(int i = 0; i < 20 ; i++) {
            Thread other = new Thread(new SimpleRunnable());
            other.start();
        }
        System.out.println("main thread run over.");
    }
}
