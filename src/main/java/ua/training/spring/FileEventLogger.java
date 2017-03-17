package ua.training.spring;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * Created by Dmytro_Deinichenko on 3/17/2017.
 */
public class FileEventLogger implements EventLogger{
    private String fileName;
    private File file;

    public FileEventLogger(String fileName) {
        this.fileName = fileName;
    }

    public void init() throws IOException{
        this.file = new File(fileName);
        file.canWrite();
    }

    public void destroy(){

    }

    @Override
    public void logEvent(Event event) {
        File file = new File(fileName);
        try {
            FileUtils.writeStringToFile(file, event.toString(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
