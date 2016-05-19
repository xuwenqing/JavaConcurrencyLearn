package ch2;

/**
 * Created by wenqing on 2016/5/19.
 */
public class Consumer implements Runnable {

    private EventStorage eventStorage;

    public Consumer(EventStorage eventStorage) {
        this.eventStorage = eventStorage;
    }

    public void run() {
        for (int i = 0; i < 100 ; i++) {
            String event = eventStorage.get();
            System.out.printf("消费者获取：%s\n", event);
        }
    }
}
