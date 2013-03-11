package foo;


import com.sun.xml.internal.bind.v2.runtime.XMLSerializer;

/**
 * Created with IntelliJ IDEA.
 * User: totole972
 * Date: 11/03/13
 * Time: 4:37 PM
 * To change this template use File | Settings | File Templates.
 */
public class JsonToXml {
    public static void toxml(String origin)
    {
        JSONObject json = JSONObject.fromObject("{\"name\":\"json\",\"bool\":true,\"int\":1}");
        String xml = XMLSerializer.write(json);
    }
}
