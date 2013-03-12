package foo;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

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
        File f = new File(xmlFile);
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
        JSONObject jsonObject = XML.toJSONObject(content);
        return jsonObject.toString(INDENT_SPACES_NUMBER);
    }
}
