package ch1;

/**
 * Created by wenqing on 2016/5/18.
 */
public class MyThreadGroup extends ThreadGroup {
    public MyThreadGroup(String name) {
        super(name);
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.printf("The Thread %s has throws an Exception \n", t.getId());
        e.printStackTrace(System.out);
        System.out.printf("线程终止 \n");
        interrupt();
    }
}
