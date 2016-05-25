package ch5;

import org.junit.Test;

import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

/**
 * Created by OGC on 2016/5/25.
 */
public class ForkJoin {

    @Test
    public void test1() {
        List<Product> products = ProductListGenerator.generate(100000);

        Task task = new Task(products, 0, products.size(), 0.2);
        ForkJoinPool pool = new ForkJoinPool();
        pool.execute(task);

        do {
            System.out.printf("Main: Action Count: %d\n", pool.getActiveThreadCount());
            System.out.printf("Main: Steal Count: %d\n", pool.getStealCount());
            System.out.printf("Main: Parallelism: %d\n", pool.getParallelism());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (!task.isDone());

        pool.shutdown();
        if(task.isCompletedNormally()) {
            System.out.printf("Main: Task CompletedNormally\n");
        }

        for(int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            if(product.getPrice() != 12)
                System.out.printf("Main: Name : %s , Price : %f\n", product.getName(), product.getPrice());
        }
        System.out.printf("Main: end\n");
    }

    @Test
    public void test2() {
        ForkJoinPool pool = new ForkJoinPool();
        FolderProcessor task1 = new FolderProcessor("c:/", ".txt");
        FolderProcessor task2 = new FolderProcessor("d:/", ".txt");
        FolderProcessor task3 = new FolderProcessor("c:/", ".log");
        FolderProcessor task4 = new FolderProcessor("d:/", ".log");

        pool.execute(task1);
        pool.execute(task2);
        pool.execute(task3);
        pool.execute(task4);

        do {

            System.out.printf("Main: Parallelism: %d\n", pool.getParallelism());
            System.out.printf("Main: QueuedTask Count: %d\n", pool.getQueuedTaskCount());
            System.out.printf("Main: Action Count: %d\n", pool.getActiveThreadCount());
            System.out.printf("Main: Steal Count: %d\n", pool.getStealCount());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (!task1.isDone() || !task2.isDone() || !task3.isDone() || !task4.isDone());

        pool.shutdown();

        List<String> result1 = task1.join();
        List<String> result2 = task2.join();
        List<String> result3 = task3.join();
        List<String> result4 = task4.join();

        System.out.printf("result1 fount %d files.\n", result1.size());
        System.out.printf("result2 fount %d files.\n", result2.size());
        System.out.printf("result3 fount %d files.\n", result3.size());
        System.out.printf("result4 fount %d files.\n", result4.size());


    }
}
