package ch5;

import java.util.List;
import java.util.concurrent.RecursiveAction;

/**
 * Created by OGC on 2016/5/25.
 */
public class Task extends RecursiveAction {

    private List<Product> products;
    private int first;
    private int last;
    private double increament;

    public Task(List<Product> products, int first, int last, double increament) {
        this.products = products;
        this.first = first;
        this.increament = increament;
        this.last = last;
    }

    /**
     * The main computation performed by this task.
     */
    @Override
    protected void compute() {
        int gap = last - first;
        if(gap < 10) {
            updatePrice();
        }
        else {
            int middle = (last + first) / 2;
            System.out.printf("Task: pending tasks: %s\n", getQueuedTaskCount());

            Task t1 = new Task(products, first, middle+1, increament);
            Task t2 = new Task(products, middle+1, last, increament);
            invokeAll(t1, t2);
            Thread.yield();
        }
    }

    private void updatePrice() {
        for(int i = first; i < last; i++) {
            Product product = products.get(i);
            product.setPrice(product.getPrice() * (1 + increament));
        }
    }
}
