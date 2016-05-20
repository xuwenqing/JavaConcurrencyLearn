package ch3;

import java.util.List;

/**
 * Created by OGC on 2016/5/20.
 */
public class ResultProcessTask implements Runnable{
    private List<List<String>> results;

    public ResultProcessTask(List<List<String>> results) {
        this.results = results;
    }

    public void run() {
        System.out.printf("%s 聚合输出任务开始运行\n", Thread.currentThread().getName());
        for (List<String > result : results) {
            for(String file : result) {
                System.out.println(file);
            }
        }
    }
}
