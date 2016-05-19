package ch2;

/**
 * Created by wenqing on 2016/5/19.
 */
public class ConditionConsumer implements Runnable {
    private ConditionResources resources;

    public ConditionConsumer(ConditionResources resources) {
        this.resources = resources;
    }

    public void run() {
        for (int i = 0; i < 100 ; i++) {
            String event = resources.get();
            System.out.printf("消费者获取：%s\n", event);
        }
    }
}
