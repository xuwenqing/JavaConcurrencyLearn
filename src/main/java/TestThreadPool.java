import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by wenqing on 2016/5/14.
 */
public class TestThreadPool {
    public static void main(String[] args) {
        //ExecutorService executor = Executors.newCachedThreadPool();
        ExecutorService executor = Executors.newSingleThreadExecutor();
        for(int i = 0; i < 10; i++) {
            SimpleRunnable runnable = new SimpleRunnable();
            executor.execute(runnable);
        }
        executor.shutdown();
        System.out.println("main over");
    }
}
