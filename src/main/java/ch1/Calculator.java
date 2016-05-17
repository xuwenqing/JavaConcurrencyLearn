package ch1;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by wenqing on 2016/5/17.
 */
public class Calculator implements Runnable{

    private int num;

    public Calculator(int num) {
        this.num = num;
    }

    public void run() {
        for(int i = 1;i < 10;i++)
            System.out.printf("%s : %d * %d = %d \n", Thread.currentThread().getName(),i, num, num*i);
    }


}
