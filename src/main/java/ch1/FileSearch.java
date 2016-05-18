package ch1;

import java.io.File;
import java.util.Objects;

/**
 * Created by OGC on 2016/5/18.
 */
public class FileSearch implements Runnable {

    private String filepath;
    private String filename;

    public FileSearch(String filepath,String filename) {
        this.filepath = filepath;
        this.filename = filename;
    }

    public void run() {
        File file = new File(filepath);
        try {
            if(file.isDirectory()) {
                processDirectory(file);
            }
            else {
                processFile(file);
            }
        }
        catch (InterruptedException e) {
            System.out.printf("%s is Interrupted.\n",Thread.currentThread().getName());
        }
    }

    private void processDirectory(File directory) throws InterruptedException {
        File[] files = directory.listFiles();
        if(files != null) {
            for(File file : files) {
                if(file.isDirectory()) {
                    processDirectory(file);
                }
                else {
                    processFile(file);
                }
            }
        }
        if(Thread.currentThread().isInterrupted()) {
            throw new InterruptedException();
        }
    }

    private void processFile(File file) throws InterruptedException {
        if(Objects.equals(filename, file.getName())) {
            System.out.printf("%s : %s \n", Thread.currentThread().getName(), file.getAbsolutePath());
        }
    }
}
