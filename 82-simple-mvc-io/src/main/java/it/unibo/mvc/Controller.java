package it.unibo.mvc;

import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
import java.io.IOException;
//import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

/**
 * Application controller. Performs the I/O.
 */
public class Controller {

    private static final String DEFAULT_PATH = System.getProperty("user.home") + File.separator
            + "output.txt";
    private File currentFile = new File(DEFAULT_PATH);

    public void setCurrentFile(File file) {
        final File parent = file.getParentFile();
        if(parent.exists()) {
            currentFile = file;
        } else {
            throw new IllegalArgumentException("Can't save in a folder that doesn't exist");
        }
    }

    public void setCurrentFile(String path) {
        this.setCurrentFile(new File(path));
    }

    public File getCurrentFile() {
        return this.currentFile;
    }

    public String getFilePath() {
        return this.currentFile.getPath();
    }

    public void saveTextOnFile(String save) throws IOException {
        try (final PrintStream ps = new PrintStream(currentFile, StandardCharsets.UTF_8)) {
            ps.println(save);
        }
    }
}
