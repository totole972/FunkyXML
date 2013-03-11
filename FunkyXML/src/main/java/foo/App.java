package foo;

import java.io.File;

import net.sf.json.JSON;

public class App {
	
	/**
	 * 
	 * @param args : 
	 * 			args[0] = {xml;json}: définit le format d'entré
	 * 			args[1] : définit le fichier d'entré
	 * 			args[2] : définit le fichier de sortie, console si vide
	 */
	public static void main(String[] args) {
		if (!checkParam(args))
			return;
    			
		String InputFileName = args[1];
		String output = null;
		
		if (args[0].toUpperCase().equals("XML")){
			JSON json = XmlToJson.convert(InputFileName);
			if (json == null){
				System.err.println("erreur dans la conversion du fichier");
			}else
				output = json.toString();
		}else if (args[0].toUpperCase().equals("JSON")){
			output = JsonToXml.toxml(InputFileName);
		}
    	
		//affichage ou sauvegarde
		if (args.length == 3){
	    	boolean isSaved = FileSaver.save(args[2], output);
	        if (!isSaved){
	        	System.err.println("erreur à l'ecriture du fichier!");
	        	System.out.println(output);
	        }	    
		}else
			System.out.println(output);
	}
	

	private static boolean checkParam(String[] args) {
		if ( !(args[0].toLowerCase().equals("xml") || args[0].toLowerCase().equals("json")) || args.length<2 || args.length>3 || !args[1].toLowerCase().endsWith(args[0].toLowerCase())
		|| !new File(args[1]).canRead()){
			showHelp();
			System.out.println(!args[1].toLowerCase().endsWith("." + args[0].toLowerCase()));
			return false;
		}else if(args.length == 3 && !(args[2].toUpperCase().endsWith(args[0].toLowerCase()) && new File(args[2]).canWrite())){
			showHelp();
			return false;
		}else
			return true;
	}
	
	
	private static void showHelp() {
		System.out.println("args[0] = {xml;json}: définit le format d'entré\n" +
			"args[1] : définit le fichier d'entré\n" +
			"args[2] : définit le fichier de sortie, console si vide");
		
	}
}
