import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JComboBox;

public class UnixHWConfigs {

	File file = new File("ProgramFiles\\UnixHWConfig.txt");
	String[] modelNames;
	ArrayList<String> modelNamesAlternate = new ArrayList<String>();
	String[] standHostOps;
	ArrayList<String> standHostOpsAlt = new ArrayList<String>();

	public String[] getModelNames() {
		return modelNames;
	}
	
	public String[] getStandardHostingOps() {
		return standHostOps;
	}
	
	public void parse_UnixHWConfigFile() {
		Scanner console = null;
		String nextLine;
		Scanner lineScanner;
		
		try {
			console = new Scanner(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while (console.hasNextLine()) {
			nextLine = console.nextLine();
			if(nextLine.equals("Server Hardware Modules")) {
				lineScanner = new Scanner(console.nextLine());
				lineScanner.useDelimiter(", ");
				while (lineScanner.hasNext()) {
					String toAdd = lineScanner.next();
					modelNamesAlternate.add(toAdd);
				}
				lineScanner.close();
			}
			else if(nextLine.equals("Standard Hosting Sys. Options")) {
				lineScanner = new Scanner(console.nextLine());
				lineScanner.useDelimiter(", ");
				while (lineScanner.hasNext()) {
					String toAdd = lineScanner.next();
					standHostOpsAlt.add(toAdd);
				}
				lineScanner.close();
			}
		}
		modelNames = new String[modelNamesAlternate.size()];
		for(int i = 0; i < modelNames.length; i++) {
			modelNames[i] = modelNamesAlternate.get(i);
		}
		standHostOps = new String[standHostOpsAlt.size()];
		for(int i = 0; i < standHostOps.length; i++) {
			standHostOps[i] = standHostOpsAlt.get(i);
		}
		console.close();
	}
	
	public UnixHWConfigs() {
		parse_UnixHWConfigFile();
	}
}
