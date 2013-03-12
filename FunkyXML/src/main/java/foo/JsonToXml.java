package foo;

import java.io.IOException;
import java.io.InputStream;

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
            InputStream is = JsonToXml.class.getResourceAsStream(jsonFile);
            int ptr = 0;
            StringBuilder builder = new StringBuilder();
            while ((ptr = is.read()) != -1) {
                builder.append((char) ptr);
            }
            JSONObject jsonObject = new JSONObject(builder.toString());
            return XML.toString(jsonObject);
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
            return null;
        }
    }
}
