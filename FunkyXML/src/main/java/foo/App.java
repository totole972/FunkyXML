package foo;

public class App {
    public static void main(String[] args) {
    	String xmlFileName = "quiz-moodle-exemple.xml";
    	String jsonFileName = "quiz-moodle-exemple.json";
    	
    	String jsonContent = XmlToJson.convert(xmlFileName);
    	boolean isSaved = FileSaver.save(jsonFileName, jsonContent);
        if (isSaved) {
        	System.out.println("XML -> JSON : Conversion optimale!");
        	
	        String xmlContent = JsonToXml.convert(jsonFileName);
	        isSaved = FileSaver.save(xmlFileName, xmlContent);
	        if (isSaved)
	            System.out.println("JSON -> XML : Conversion jsonxml ok!");
	        else
	            System.out.println("JSON -> XML : Conversion jsonxml echec!");
        }
        else
        	System.out.println("XML -> JSON : C'est nul!");

        /* */
    }
}