package cache;

import java.util.concurrent.*;

/**
 * Created by wenqing on 2016/5/25.
 */
public class CachePool<A, V> implements Computable<A, V>{

    private ConcurrentHashMap<A, Future<V>> cache;
    private Computable<A,V> computable;

    public CachePool(Computable<A, V> computable) {
        this.computable = computable;
        this.cache = new ConcurrentHashMap<A, Future<V>>();
    }

    public V compute(final A args) {
        while (true) {
            Future<V> f = cache.get(args);
            if(f == null) {
                Callable<V> callable = new Callable<V>() {
                    public V call() throws Exception {
                        return computable.compute(args);
                    }
                };
                FutureTask<V> task = new FutureTask<V>(callable);
                f = cache.putIfAbsent(args, task);
                if(f == null) {
                    f = task;
                    task.run();
                }
            }
            try {
                //×èÈû
                return f.get();
            } catch (InterruptedException e) {
                cache.remove(f);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
