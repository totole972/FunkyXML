package foo;

import java.io.IOException;
import java.io.InputStream;

import org.json.JSONObject;
import org.json.XML;

/**
 * Provide an XML to JSON converter.
 * @version 2013-03-11
 */
public final class XmlToJson {
    /**
     * Number of indent spaces used in the JSON file.
     */
    private static final int INDENT_SPACES_NUMBER = 4;

    /**
     * Empty private constructor (utility class).
     */
    private XmlToJson() {
    }

    /**
     * Convert an XML file into a JSON one, according to its path.
     * @param xmlFile
     *            Path of the XML file.
     * @return The content of the JSON file.
     */
    public static String convert(final String xmlFile) {
        try {
            InputStream is = XmlToJson.class.getResourceAsStream(xmlFile);
            int ptr = 0;
            StringBuilder builder = new StringBuilder();
            while ((ptr = is.read()) != -1) {
                builder.append((char) ptr);
            }
            String xml = builder.toString();
            JSONObject jsonObject = XML.toJSONObject(xml);
            return jsonObject.toString(INDENT_SPACES_NUMBER);
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
            return null;
        }
    }
}
