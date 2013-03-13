package ihm;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import model.FileSaver;

/**
 * Define the user interface used by the application.
 * @version 2013-03-13
 */
public final class UserInterface {
    /**
     * Main frame.
     */
    private JFrame frame;

    /**
     * Path of the file to convert.
     */
    private JTextField pathField;

    /**
     * Field that displays message like errors on converting.
     */
    private JTextField communicationField;

    /**
     * Button used to convert file.
     */
    private JButton convertButton;

    /**
     * Types of converter.
     */
    private enum Converter {
        /**
         * XML to JSON converter.
         */
        XML_TO_JSON {
            @Override
            public String toString() {
                return "XML to JSON converter";
            }
        },
        /**
         * JSON to XML converter.
         */
        JSON_TO_XML {
            @Override
            public String toString() {
                return "JSON to XML converter";
            }
        }
    };

    /**
     * Current type of converter.
     */
    private Converter converter = Converter.XML_TO_JSON;

    /**
     * Launch the application.
     * @param args
     *            null
     */
    public static void main(final String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UserInterface window = new UserInterface();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    private UserInterface() {
        initialize();
    }

    /**
     * Initialize the content of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setTitle("XML <==> JSON converter for Moodle");
        frame.setSize(530, 270);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        setRadioButtons();
        setLabels();
        setTextFields();
        setButtons();
    }

    /**
     * Set radio buttons on the main frame.
     */
    private void setRadioButtons() {
        // Panel containing the radio buttons
        JPanel p = new JPanel();
        p.setBorder(BorderFactory
                .createTitledBorder("Choose a converting type"));
        frame.getContentPane().add(p);
        p.setBounds(15, 15, 160, 90);

        // First radio button
        JRadioButton xmltojsonRadioButton = new JRadioButton("XML to JSON");
        xmltojsonRadioButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                converter = Converter.XML_TO_JSON;
                communicationField.setText("You've just chosen the "
                        + converter + ".");
            }

        });
        p.add(xmltojsonRadioButton);

        // Second radio button
        JRadioButton jsontoxmlRadioButton = new JRadioButton("JSON to XML");
        jsontoxmlRadioButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                converter = Converter.JSON_TO_XML;
                communicationField.setText("You've just chosen the "
                        + converter + ".");
            }

        });
        p.add(jsontoxmlRadioButton);

        // Default radio button selected
        xmltojsonRadioButton.setSelected(true);

        // Button group associated with the radio buttons
        ButtonGroup group = new ButtonGroup();
        group.add(xmltojsonRadioButton);
        group.add(jsontoxmlRadioButton);
    }

    /**
     * Set labels on the main frame.
     */
    private void setLabels() {
        // Description label
        JLabel fileLabel = new JLabel(
                "<html><u>Choose a file to convert:</u></html>");
        fileLabel.setBounds(15, 120, 180, 15);
        frame.getContentPane().add(fileLabel);
    }

    /**
     * Set text fields on the main frame.
     */
    private void setTextFields() {
        // Path field that indicates where is the current file to convert
        pathField = new JTextField();
        pathField.setBounds(15, 150, 375, 25);
        frame.getContentPane().add(pathField);
        pathField.setColumns(10);
        pathField.setEnabled(false);

        // Communication field that indicates the messages (errors, convertions
        // done)
        communicationField = new JTextField();
        communicationField.setBackground(new Color(255, 248, 220));
        communicationField.setEditable(false);
        communicationField.setHorizontalAlignment(JTextField.CENTER);
        communicationField.setFont(new Font(null, Font.BOLD, 12));
        communicationField.setBounds(190, 15, 315, 90);
        frame.getContentPane().add(communicationField);
        communicationField.setColumns(10);
    }

    /**
     * Set buttons on the main frame.
     */
    private void setButtons() {
        // Button that convert the current file when clicked
        convertButton = new JButton("Convert");
        convertButton.setBounds(215, 195, 100, 25);
        convertButton.setEnabled(false);
        frame.getContentPane().add(convertButton);
        convertButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                String[] args = new String[3];
                args[1] = pathField.getText();
                switch (converter) {
                    case XML_TO_JSON:
                        args[0] = "xml";
                        args[2] = args[1].substring(0, args[1].lastIndexOf('.'))
                                + "converted.json";
                        break;
                    case JSON_TO_XML:
                        args[0] = "json";
                        args[2] = args[1].substring(0, args[1].lastIndexOf('.'))
                                + "converted.xml";
                        break;
                    default:
                        break;
                }
                if (!console.App.checkParam(args)) {
                    communicationField
                            .setText("You've just chose a wrong file."
                                    + "<Expected: ." + args[0] + ">");
                } else {
                    String output = null;
                    switch (converter) {
                        case XML_TO_JSON:
                            output = model.XmlToJson.convert(args[1]);
                            break;
                        case JSON_TO_XML:
                            output = model.JsonToXml.convert(args[1]);
                            break;
                        default:
                            break;
                    }
                    boolean isSaved = FileSaver.save(args[2], output);
                    if (!isSaved) {
                        communicationField.setText("An error occurs "
                                + "when converting/saving the file.");
                    } else {
                        communicationField.setText("The file has been "
                                + "converted and saved!");
                    }
                }
            }

        });

        // Button that's used to select a file to convert
        JButton selectButton = new JButton("Select");
        selectButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent arg0) {
                JFileChooser chooser = new JFileChooser();
                int returnVal = chooser.showOpenDialog(frame);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    convertButton.setEnabled(true);
                    pathField.setText(chooser.getSelectedFile().getPath());
                }
            }

        });
        selectButton.setBounds(405, 150, 100, 25);
        frame.getContentPane().add(selectButton);
    }
}
