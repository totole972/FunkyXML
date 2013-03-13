package console;

import java.io.File;

import model.FileSaver;
import model.JsonToXml;
import model.XmlToJson;

/**
 * Define the console interface used by the application.
 * @version 2013-03-12
 */
public final class App {
    /**
     * Empty private constructor (utility class).
     */
    private App() {
    }

    /**
     * Launch the application.
     * @param args
     *            0: input format {xml;json}
     *            1: input file
     *            2: output file, console if empty
     */
    public static void main(final String[] args) {
        // Verify parameters
        if (!checkParam(args)) {
            return;
        }

        String inputFileName = args[1];
        String output = null;

        // Convert
        if (args[0].toUpperCase().equals("XML")) {
            output = XmlToJson.convert(inputFileName);
        } else if (args[0].toUpperCase().equals("JSON")) {
            output = JsonToXml.convert(inputFileName);
        }

        // Display or save
        if (args.length == 3) {
            boolean isSaved = FileSaver.save(args[2], output);
            if (!isSaved) {
                System.err.println("An error occurs "
                        + "when converting/saving the file.");
                System.out.println(output);
            } else {
                System.out.println("The file has been converted "
                        + "and saved!");
            }
        } else {
            System.out.println(output);
        }
    }

    /**
     * Check if parameters are correct and if file parameters
     * fit according to the conversion to do in first parameter.
     * @param args
     *            parameters of main function
     * @return true if parameters are correct; false otherwise
     */
    public static boolean checkParam(final String[] args) {
        // 1st and 2nd check: input conversion format
        // 3rd and 4th check: number of parameters
        // 5th check: input file format
        // 6th check: input file readable
        if (!(args[0].toLowerCase().equals("xml")
                || args[0].toLowerCase().equals("json"))
                || args.length < 2
                || args.length > 3
                || !args[1].toLowerCase().endsWith(args[0].toLowerCase())
                || !new File(args[1]).canRead()) {
            showHelp();
            return false;
        } else {
            return true;
        }
    }

    /**
     * Show how to use the main function with correct parameters.
     */
    private static void showHelp() {
        System.out.println("Parameter 1: input format {xml;json}\n"
                + "Parameter 2: input file"
                + "Parameter 3: output file, console if empty");
    }
}
