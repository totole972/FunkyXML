package foo;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Save a file on hard disk, according to its path and its content.
 * @version 2013-03-11
 */
public final class FileSaver {
    /**
     * Empty private constructor (utility class).
     */
    private FileSaver() {
    }

    /**
     * Save a file according to its output path and its content.
     * @param path
     *            Output path on hard disk.
     * @param fileContent
     *            Content of the file.
     * @return True if the file has been saved, and false otherwise.
     */
    public static boolean save(final String path, final String fileContent) {
        try {
            FileWriter file = new FileWriter(path);
            file.write(fileContent);
            file.flush();
            file.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
