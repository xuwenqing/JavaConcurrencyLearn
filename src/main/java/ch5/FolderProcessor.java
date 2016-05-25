package ch5;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

/**
 * Created by OGC on 2016/5/25.
 */
public class FolderProcessor extends RecursiveTask<List<String>> {

    private String path;
    private String extension;

    public FolderProcessor(String path, String extension) {
        this.path = path;
        this.extension = extension;
    }

    /**
     * The main computation performed by this task.
     */
    @Override
    protected List<String> compute() {
        List<String > results = new LinkedList<String>();

        File file = new File(path);
        if(file.isDirectory()) {
            File[] files = file.listFiles();
            if(files != null) {
                List<FolderProcessor> tasks = new LinkedList<FolderProcessor>();
                for (File f : files) {
                    FolderProcessor processor = new FolderProcessor(f.getAbsolutePath(), extension);
                    tasks.add(processor);
                    processor.fork();
                }
                addResultFromTasks(results, tasks);
            }
        }
        else {
            if(checkExtension(file.getName())) {
                results.add(file.getAbsolutePath());
            }
        }

        return results;
    }

    private void addResultFromTasks(List<String> results, List<FolderProcessor> tasks) {
        for(FolderProcessor task : tasks) {
            results.addAll(task.join());
        }
    }

    private boolean checkExtension(String filename) {
        if(filename.endsWith(extension)) {
            return true;
        }
        return false;
    }
}
