package ch5;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by OGC on 2016/5/25.
 */
public class ProductListGenerator {

    public static List<Product> generate(int size) {
        List<Product> products = new ArrayList<Product>(size);
        for (int i = 0; i < size; i++) {
            Product product = new Product("Product " + i, 10);
            products.add(product);
        }
        return products;
    }
}
