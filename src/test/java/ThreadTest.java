import ch1.PrimeGenerator;
import org.junit.Test;

/**
 * Created by wenqing on 2016/5/17.
 */
public class ThreadTest {

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
}
