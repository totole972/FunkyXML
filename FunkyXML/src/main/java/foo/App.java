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

        String jsonfile = "../../../test.json";
        String xmlfile = JsonToXml.toxml(jsonfile);
        boolean isSavedxml = FileSaver.save("test.xml", xmlfile);
        if (isSavedxml)
            System.out.println("Conversion jsonxml ok!");
        else
            System.out.println("Conversion jsonxml echec!");
    }
}