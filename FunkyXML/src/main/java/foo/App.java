package foo;

import net.sf.json.JSON;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
    	String xmlFileName = "blabla.xml";
    	JSON json = XmlToJson.convert(xmlFileName);
    	boolean isSaved = FileSaver.save("blabla.json", json.toString(2));
        if (isSaved)
        	System.out.println("Conversion optimale!");
        else
        	System.out.println("C'est nul!");
    }
}