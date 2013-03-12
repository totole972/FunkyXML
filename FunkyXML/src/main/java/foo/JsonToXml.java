package foo;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.Iterator;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.json.JSONObject;
import org.json.XML;

/**
 * Provide a JSON to XML converter.
 * @version 2013-03-11
 */
public final class JsonToXml {    
    /**
     * Empty private constructor (utility class).
     */
    private JsonToXml() {
    }

    /**
     * Convert a JSON file into an XML one, according to its path.
     * @param jsonFile
     *            Path of the JSON file.
     * @return The content of the XML file.
     */
    public static String convert(final String jsonFile) {
        try {
            // First step.
            InputStream is = JsonToXml.class.getResourceAsStream(jsonFile);
            int ptr = 0;
            StringBuilder builder = new StringBuilder();
            while ((ptr = is.read()) != -1) {
                builder.append((char) ptr);
            }
            JSONObject jsonObject = new JSONObject(builder.toString());
            String xmlContent = XML.toString(jsonObject);
            
            // Second step.
            return modify(xmlContent);
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
            return null;
        } catch (JDOMException e) {
            System.err.format("JDOMException: %s%n", e);
            return null;
        }
    }
    
    private static String modify(String xmlContent) throws JDOMException, IOException {
        // Get the JDOM document and its root.
        SAXBuilder sxb = new SAXBuilder();
        Document document = sxb.build(new StringReader(xmlContent));
        Element root = document.getRootElement();
            
        // Get every question of the XML content.
        List<Element> listQuestions = root.getChildren("question");
        Iterator<Element> i = listQuestions.iterator();
        while (i.hasNext()) {
            Element courant = (Element) i.next();
            
            // Update according to the hierarchy: <quiz><question type="xxxx">.
            Element type = courant.getChild("type");
            if (type != null) {
                courant.setAttribute("type", type.getValue());
                courant.removeChild("type");
            }
            
            // Update according to the hierarchy: <quiz><question><questiontext format="xxxx">.
            Element questiontext = courant.getChild("questiontext");
            if (questiontext != null) {
                Element format = questiontext.getChild("format");
                if (format != null) {
                    questiontext.setAttribute("format", format.getValue());
                    questiontext.removeChild("format");
                }
            }
            
            // Update according to the hierarchy: <quiz><question><answer fraction="xxxx">.
            List<Element> listAnswers = courant.getChildren("answer");
            Iterator<Element> it = listAnswers.iterator();
            while (it.hasNext()) {
                Element answer = (Element) it.next();
                
                Element fraction = answer.getChild("fraction");
                if (fraction != null) {
                    answer.setAttribute("fraction", fraction.getValue());
                    answer.removeChild("fraction");
                }
            }
        }
        
        // Convert a JDOM document to String.
        XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat());
        return outputter.outputString(document);
    }
}
