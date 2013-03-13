package IHM;
import foo.FileSaver;

import java.awt.EventQueue;

import javax.swing.*;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class Funkyxmlihm {

	private JFrame frmXmlToJson;
	private JTextField pathField;
	private JTextField textField_1;
	public int type=0;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Funkyxmlihm window = new Funkyxmlihm();
					window.frmXmlToJson.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Funkyxmlihm() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmXmlToJson = new JFrame();
		frmXmlToJson.setTitle("XML to JSON");
		frmXmlToJson.setBounds(100, 100, 738, 512);
		frmXmlToJson.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmXmlToJson.getContentPane().setLayout(null);
		
		JRadioButton xmltojsonRadioButton = new JRadioButton("XML to JSON");
		xmltojsonRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				type=1;
				System.out.println(type);

			}
		});
		xmltojsonRadioButton.setBounds(51, 82, 127, 25);
		frmXmlToJson.getContentPane().add(xmltojsonRadioButton);
		
		JRadioButton jsontoxmlRadioButton = new JRadioButton("JSON to XML");
		jsontoxmlRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				type=2;
				System.out.println(type);
			}
		});
		jsontoxmlRadioButton.setBounds(51, 122, 127, 25);
		frmXmlToJson.getContentPane().add(jsontoxmlRadioButton);
		
		ButtonGroup group = new ButtonGroup();
		group.add(xmltojsonRadioButton);
		group.add(jsontoxmlRadioButton);
		
		JLabel lblNewLabel = new JLabel("Choose a converting type:");
		lblNewLabel.setBounds(37, 41, 181, 16);
		frmXmlToJson.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Choose a file to convert :");
		lblNewLabel_1.setBounds(37, 248, 181, 16);
		frmXmlToJson.getContentPane().add(lblNewLabel_1);
		
		pathField = new JTextField();
		pathField.setBounds(51, 277, 485, 22);
		frmXmlToJson.getContentPane().add(pathField);
		pathField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBackground(new Color(255, 248, 220));
		textField_1.setEditable(false);
		textField_1.setBounds(345, 41, 314, 187);
		frmXmlToJson.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnNewButton = new JButton("Convert");
		btnNewButton.setBounds(261, 369, 97, 25);
		frmXmlToJson.getContentPane().add(btnNewButton);

        btnNewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String[] args= new String[3];
                args[1]=  pathField.getText();
                switch(type)
                {
                    case 1 : args[0]="xml";
                        args[2]=  args[1].substring(0,args[1].lastIndexOf('.'))+"convert.json";
                        break;
                    case 2 : args[0]="json";
                        args[2]=  args[1].substring(0,args[1].lastIndexOf('.'))+"convert.xml";
                        break;
                    default : args[0]="0";
                }
                if(!foo.App.checkParam(args))
                {
                     textField_1.setText("Les paramètres sont erronés");
                }
                else
                {
                    String output = null;
                    switch(type)
                    {
                        case 1 : output=foo.XmlToJson.convert(args[1]);
                            break;
                        case 2 : output=foo.JsonToXml.convert(args[1]);
                            break;
                        default : textField_1.setText("Les paramètres sont erronés");
                    }
                    boolean isSaved = FileSaver.save(args[2], output);
                    if (!isSaved){
                        textField_1.setText("erreur à l'ecriture du fichier! : " + args[2]);
                        System.err.println("erreur à l'ecriture du fichier!");
                        //System.out.println(output);
                    }else
                        JOptionPane.showMessageDialog(frmXmlToJson,"Réussite  l'ecriture du fichier! : " + args[2]);
                        textField_1.setText(output);
                        //System.out.println("ecriture du fichier reussi");
                }


            }
        });

		JButton btnNewButton_1 = new JButton("Select");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser chooser = new JFileChooser();
			    // Note: source for ExampleFileFilter can be found in FileChooserDemo,
			    // under the demo/jfc directory in the JDK.
			    int returnVal = chooser.showOpenDialog(frmXmlToJson);
			    if(returnVal == JFileChooser.APPROVE_OPTION) {
			       System.out.println("You chose to open this file: " +
			            chooser.getSelectedFile().getPath());
			       pathField.setText(chooser.getSelectedFile().getPath());
			    }
			}
		});
		btnNewButton_1.setBounds(548, 276, 97, 25);
		frmXmlToJson.getContentPane().add(btnNewButton_1);


	}
}
