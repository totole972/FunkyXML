package foo;

import java.io.FileWriter;
import java.io.IOException;

public class FileSaver {
	public static boolean save(String path, String fileContent) {
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
