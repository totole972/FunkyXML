package foo;

import java.io.IOException;
import java.io.InputStream;

import net.sf.json.JSON;
import net.sf.json.xml.XMLSerializer;

import org.apache.commons.io.IOUtils;

public class XmlToJson {
	public static JSON convert(String xmlFile) {
		try {
			InputStream is = XmlToJson.class.getResourceAsStream(xmlFile);
			String xml = IOUtils.toString(is);
			XMLSerializer xmlSerializer = new XMLSerializer();
			return xmlSerializer.read(xml);
		} catch (IOException e) {
			return null;
		}
	}
}
