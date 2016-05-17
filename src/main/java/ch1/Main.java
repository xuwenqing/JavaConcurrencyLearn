package ch1;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by wenqing on 2016/5/17.
 */
public class Main {

    private static void writeThreadInfo(PrintWriter pw,Thread thread,Thread.State oldStatus) {
        pw.printf("Main : Id %d - %s \n",thread.getId(),thread.getName());
        pw.printf("Main : Priority : %d \n", thread.getPriority());
        pw.printf("Main : old Status : %s \n",oldStatus);
        pw.printf("Main : new Status : %s \n",thread.getState());
        pw.printf("Main : ************************* \n",thread.getState());
    }

    public static void main(String[] args) {
        Thread[] threads = new Thread[10];
        Thread.State[] states = new Thread.State[10];
        for (int i = 1; i<=10;i++) {
            threads[i-1] = new Thread(new Calculator(i),"thread "+i);
            threads[i-1].setPriority(i);
        }
        PrintWriter pw = null;
        FileWriter file = null;
        try {
            file = new FileWriter("log.txt");
            pw = new PrintWriter(file);
            for (int i = 1; i<=10;i++) {
                pw.println("Main : Status of thread "+ threads[i-1].getId() + " "+ threads[i-1].getState());
                states[i-1] = threads[i-1].getState();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 1; i<=10;i++) {
            threads[i-1].start();
        }

        boolean isFinish = false;
        while (!isFinish) {
            for(int i = 1; i<=10;i++) {
                writeThreadInfo(pw,threads[i-1],states[i-1]);
                if(threads[i-1].getState() != states[i-1]) {
                    states[i-1] = threads[i-1].getState();
                }
            }

            isFinish = true;
            for(int i = 1; i<10;i++) {
                isFinish = isFinish & threads[i-1].getState() == Thread.State.TERMINATED;
            }

        }
        pw.close();
    }
}
