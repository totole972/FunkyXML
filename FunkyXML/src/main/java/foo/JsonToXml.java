package foo;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import com.sun.org.apache.xml.internal.serialize.XMLSerializer;
import org.json.JSONObject;
import org.json.XML;
import org.w3c.dom.Document;

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
        File f = new File(jsonFile);
        String content="";
        try {
            BufferedReader reader = Files.newBufferedReader(f.toPath(), StandardCharsets.UTF_8);
            String line;
            while ((line = reader.readLine()) != null) {
                content+="\n"+line;
            }
        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
            return null;
        }
        System.out.println(content);
        JSONObject json = new JSONObject(content);
        return XML.toString(json);
    }
}
