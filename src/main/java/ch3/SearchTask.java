package ch3;

import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by OGC on 2016/5/20.
 */
public class SearchTask implements Runnable{

    private CyclicBarrier barrier;
    private SearchFile searchFile;
    private List<List<String>> results;

    public SearchTask(CyclicBarrier barrier, SearchFile searchFile, List<List<String>> results) {
        this.barrier = barrier;
        this.searchFile = searchFile;
        this.results = results;
    }

    public void run() {
        System.out.printf("%s开始运行 在%s中搜索%s的任务\n", Thread.currentThread().getName(), searchFile.getRoot(), searchFile.getTarget());
        List<String> result = searchFile.getComparedResult();
        results.add(result);
        System.out.printf("%s完成 在%s中搜索%s的任务\n", Thread.currentThread().getName(), searchFile.getRoot(), searchFile.getTarget());
        try {
            barrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}
