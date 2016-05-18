package ch1;

import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by wenqing on 2016/5/18.
 */
public class MyThreadFactory implements ThreadFactory {

    private String name;
    private List<String> stas;
    private AtomicInteger count;

    public MyThreadFactory(String name) {
        this.name = name;
        stas = new CopyOnWriteArrayList<String>();
        count = new AtomicInteger(0);
    }

    public Thread newThread(Runnable r) {
        Thread t = new Thread(r,name + "-" + count.getAndIncrement());
        stas.add(String.format("%s 创建线程ID为 %s 线程名称为 %s 的新线程。\n", new Date(), t.getId(), t.getName()));
        return t;
    }

    public String getStatus() {
        StringBuffer sb = new StringBuffer();
        for(String str : stas) {
            sb.append(str).append("\n");
        }
        return sb.toString();
    }
}
