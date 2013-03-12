package foo;

import java.io.File;

public class App {
    
	/**
	 * 
	 * @param args : 
	 * 			args[0] = {xml;json}: définit le format d'entre
	 * 			args[1] : définit le fichier d'entré
	 * 			args[2] : définit le fichier de sortie, console si vide
	 */
	public static void main(String[] args) {
		//vérification parametre
		if (!checkParam(args))
			return;
    			
		String InputFileName = args[1];
		String output = null;
		
		// conversion
		if (args[0].toUpperCase().equals("XML")){
            output = XmlToJson.convert(InputFileName);
		}else if (args[0].toUpperCase().equals("JSON")){
            output = JsonToXml.convert(InputFileName);
		}
    	
		//affichage ou sauvegarde
		if (args.length == 3){
	    	boolean isSaved = FileSaver.save(args[2], output);
	        if (!isSaved){
	        	System.err.println("erreur à l'ecriture du fichier!");
	        	System.out.println(output);
	        }else
	        	System.out.println("ecriture du fichier reussi");
		}else
			System.out.println(output);
	}
	

	/**
	 * this function check if params are correctly input, and if file parameters is appropriate with
	 * the transformation to do in first parameter.
	 * 
	 * @param args : param of main function
	 * @return true if args params are OK, else otherwise.
	 */
	public static boolean checkParam(String[] args) {
		if ( !(args[0].toLowerCase().equals("xml") || args[0].toLowerCase().equals("json")) // vérifie le type de fichier en entré (défini le type de transformation)
				|| args.length<2 || args.length>3 // vérifie le nombre de parametre
				|| !args[1].toLowerCase().endsWith(args[0].toLowerCase()) // vérifie que le fichier en entrée porte la bonne extension
		|| !new File(args[1]).canRead()){ // et qu'il est lisible
			showHelp();
			return false;
		}else
			return true;
	}
	
	/**
	 * just show how to use param
	 */
	private static void showHelp() {
		System.out.println("args[0] = {xml;json}: définit le format d'entre\n" +
			"args[1] : définit le fichier d'entré\n" +
			"args[2] : définit le fichier de sortie, console si vide");
		
	}
}
