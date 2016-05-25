package cache;

/**
 * Created by wenqing on 2016/5/25.
 */
public interface Computable<A,V> {
    //需要花费大量时间用于计算
    V compute(A args);
}
