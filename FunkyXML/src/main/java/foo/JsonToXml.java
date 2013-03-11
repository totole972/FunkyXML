package foo;



import net.sf.json.JSONObject;
import net.sf.json.xml.XMLSerializer;
import org.apache.commons.io.IOUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Created with IntelliJ IDEA.
 * User: totole972
 * Date: 11/03/13
 * Time: 4:37 PM
 * To change this template use File | Settings | File Templates.
 */
public class JsonToXml {
    public static String toxml(String origin)
    {

            //Charset charset = Charset.forName("UTF_8");
            //Path file = FileSystems.getDefault().getPath(origin);
            File f = new File(origin);


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
            /*InputStream is = JsonToXml.class.getResourceAsStream(origin);
            if(is.available()!=0)
            {
                System.out.println("file exists");
            }      */
            JSONObject json = JSONObject.fromObject(content);
            XMLSerializer xs = new XMLSerializer();
            String xml = xs.write(json);
            return xml;



    }
}
