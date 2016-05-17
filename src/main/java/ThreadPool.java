import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by wenqing on 2016/5/16.
 */
public class ThreadPool {
    //阻塞队列
    private BlockingQueue<Runnable> taskQueue;
    private List<PoolThread> threads;
    private boolean isStopped = false;

    public ThreadPool(int threadSize,int runnableSize) {
        threads = new ArrayList<PoolThread>(threadSize);
        taskQueue = new LinkedBlockingDeque<Runnable>(runnableSize) ;
        for(int i = 0;i < threadSize; i++) {
            PoolThread thread = new PoolThread(taskQueue);
            threads.add(thread);
        }
        for (PoolThread thread : threads) {
            thread.start();
        }
    }

    public void execute(Runnable runnable) {
        if(isStopped)
            throw new IllegalStateException("ThreadPool is stopped");
        taskQueue.add(runnable);
    }

    public void stop() {
        if(!isStopped) {
            isStopped = true;
            for (PoolThread thread : threads) {
                thread.toStop();
            }
        }
    }


}

class PoolThread extends Thread {
    private BlockingQueue<Runnable> taskQueue;
    private boolean isStoped = false;

    public PoolThread(BlockingQueue<Runnable> taskQueue) {
        this.taskQueue = taskQueue;
    }

    @Override
    public void run() {
        while (!isStoped) {
            try {
                Runnable runnable = taskQueue.take();
                runnable.run();
            } catch (InterruptedException e) {
                // 写日志或者报告异常,
                // 但保持线程池运行.
            }
        }
    }

    public void toStop() {
        isStoped = true;
        this.interrupt();
    }

    public boolean isStoped() {
        return isStoped;
    }
}
