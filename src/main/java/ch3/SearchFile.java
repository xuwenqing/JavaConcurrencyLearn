package ch3;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * Created by OGC on 2016/5/20.
 */
public class SearchFile {
    private final String root;
    private final String target;
    private List<String> results;

    public SearchFile(String root, String target) {
        this.root = root;
        this.target = target;
        this.results = new LinkedList<String>();
    }

    public List<String> getComparedResult() {
        File file = new File(root);
        if(file.isDirectory())
            processDirectory(file);
        else
            processFile(file);
        return results;
    }

    private void processDirectory(File directory) {
        File[] files = directory.listFiles();
        if(files != null) {
            for(File file : files) {
                if(file.isDirectory())
                    processDirectory(file);
                else
                    processFile(file);
            }
        }
    }

    private void processFile(File file) {
        if(Objects.equals(file.getName(), target)) {
            results.add(file.getAbsolutePath());
        }
    }

    public String getRoot() {
        return root;
    }

    public String getTarget() {
        return target;
    }
}
