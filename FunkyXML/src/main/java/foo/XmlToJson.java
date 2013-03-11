package foo;

import java.io.IOException;
import java.io.InputStream;

import org.json.JSONObject;
import org.json.XML;

public class XmlToJson {
	public static String convert(String xmlFile) {
		try {
			InputStream is = XmlToJson.class.getResourceAsStream(xmlFile);
			int ptr = 0;
			StringBuilder builder = new StringBuilder();
			while ((ptr = is.read()) != -1) {
			    builder.append((char) ptr);
			}
			String xml = builder.toString();
			JSONObject jsonObject = XML.toJSONObject(xml);
			return jsonObject.toString(4);
		} catch (IOException e) {
			return null;
		}
	}
}
