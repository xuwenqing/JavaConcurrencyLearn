package ch1;

/**
 * Created by wenqing on 2016/5/17.
 */
public class PrimeGenerator implements Runnable {
    private int num = 2;

    public void run() {
        while(true) {
            if(isPrime(num))
                System.out.printf("%d ",num);
            num++;
            if (Thread.currentThread().isInterrupted()) {
                System.out.printf("\n%s \n",Thread.currentThread().getName()+" isInterrupted.");
                return;
            }
        }
    }

    private boolean isPrime(int num) {
        for(int i = 2;i < num/2;i++)
            if(num % i == 0)
                return false;
        return true;
    }

}
