package foo;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {
	private String jsonfile = "/home/menestrel/test.json";
    private String xmlfile = JsonToXml.convert(jsonfile);
    private String xmlsource = "/home/menestrel/test.xml";
    private String jsonfileconvert = XmlToJson.convert(xmlsource);
    private String[] testargs = {"json","/home/menestrel/test.json","/home/menestrel/test.xml"};
	
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(AppTest.class);
    }

    public void testParametres()
    {
        assertTrue(App.checkParam(testargs));
    }


    public void testLectureJson()
    {
        assertTrue(xmlfile != null);
    }

    public void testApp()
    {
        boolean isSavedxml = FileSaver.save("/home/menestrel/test.xml", xmlfile);
        assertTrue(isSavedxml);
        assertTrue(jsonfileconvert!=null);
        boolean issavedjson = FileSaver.save("/home/menestrel/testjasonback.json",jsonfileconvert);
        assertTrue(issavedjson);
    }
}
