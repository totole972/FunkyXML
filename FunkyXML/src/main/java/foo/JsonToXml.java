package foo;

import java.io.IOException;
import java.io.InputStream;

import org.json.JSONObject;
import org.json.XML;

public class JsonToXml {
	public static String convert(String jsonFile) {
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
