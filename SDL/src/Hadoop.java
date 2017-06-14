import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
public class Hadoop {
	
	File file = new File("ProgramFiles\\Hadoop.txt");
	String[] numServers;
	ArrayList<String> numServersTmp = new ArrayList<String>();
	String[] environmentOpts; 
	ArrayList<String> environmentOptsTmp = new ArrayList<String>();
	String[] datacenterOpts;
	ArrayList<String> datacenterOptsTmp = new ArrayList<String>();
	String[] businessUnitOpts;
	ArrayList<String> businessUnitOptsTmp = new ArrayList<String>();
	String[] enterpriseControllerOpts;
	ArrayList<String> enterpriseControllerOptsTmp = new ArrayList<String>();
	String[] securityZoneZeroOpts;
	ArrayList<String> securityZoneZeroOptsTmp = new ArrayList<String>();
	String[] securityZoneHudsonOpts;
	ArrayList<String> securityZoneHudsonOptsTmp = new ArrayList<String>();
	String[] securityZoneRochelleOpts;
	ArrayList<String> securityZoneRochelleOptsTmp = new ArrayList<String>();
	String[] wingLocationOpts;
	ArrayList<String> wingLocationOptsTmp = new ArrayList<String>();
	String [] hadoopOpts;
	ArrayList<String> hadoopOptsTmp = new ArrayList<String>();

	private void setHadoopOptions() {
		numServers = new String [numServersTmp.size()];
		for (int i = 0; i < numServers.length; i++) {
			numServers[i] = numServersTmp.get(i); 
		}
		environmentOpts= new String [environmentOptsTmp.size()];
		for (int i = 0; i < environmentOpts.length; i++) {
			environmentOpts[i] = environmentOptsTmp.get(i);
		}
		datacenterOpts = new String [datacenterOptsTmp.size()];
		for (int i = 0; i < datacenterOpts.length; i++) {
			datacenterOpts[i] = datacenterOptsTmp.get(i); 
		}
		businessUnitOpts = new String[businessUnitOptsTmp.size()];
		for(int i = 0; i < businessUnitOpts.length; i++) {
			businessUnitOpts[i] = businessUnitOptsTmp.get(i);
		}
		
		enterpriseControllerOpts = new String[enterpriseControllerOptsTmp.size()];
		for(int i = 0; i < enterpriseControllerOpts.length; i++) {
			enterpriseControllerOpts[i] = enterpriseControllerOptsTmp.get(i);
		}
		securityZoneZeroOpts = new String[securityZoneZeroOptsTmp.size()];
		for(int i = 0; i < securityZoneZeroOpts.length; i++) {
			securityZoneZeroOpts[i] = securityZoneZeroOptsTmp.get(i);
		}
		
		securityZoneHudsonOpts = new String[securityZoneHudsonOptsTmp.size()];
		for(int i = 0; i < securityZoneHudsonOpts.length; i++) {
			securityZoneHudsonOpts[i] = securityZoneHudsonOptsTmp.get(i);
		}
		
		securityZoneRochelleOpts = new String[securityZoneRochelleOptsTmp.size()];
		for(int i = 0; i < securityZoneRochelleOpts.length; i++) {
			securityZoneRochelleOpts[i] = securityZoneRochelleOptsTmp.get(i);
		}
		
		wingLocationOpts = new String[wingLocationOptsTmp.size()];
		for(int i = 0; i < wingLocationOpts.length; i++) {
			wingLocationOpts[i] = wingLocationOptsTmp.get(i);
		}
		
		hadoopOpts = new String[hadoopOptsTmp.size()];
		for(int i = 0; i < hadoopOpts.length; i++) {
			hadoopOpts[i] = hadoopOptsTmp.get(i);
		
	
		}
	
	
	}


	private void parseHadoopFile() {
		Scanner console = null;
		String nextLine;
		Scanner lineScanner;
	
		try {
			console = new Scanner(file);
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
		while (console.hasNextLine()) {
			nextLine = console.nextLine();
			
			if(nextLine.equals("num_servers")) {
				lineScanner = new Scanner(console.nextLine());
				lineScanner.useDelimiter(", ");
				while (lineScanner.hasNext()) {
					String toAdd = lineScanner.next();
					numServersTmp.add(toAdd);
				}
				lineScanner.close();
			}
			else if(nextLine.equals("environment")) {
				lineScanner = new Scanner(console.nextLine());
				lineScanner.useDelimiter(", ");
				while (lineScanner.hasNext()) {
					String toAdd = lineScanner.next();
					environmentOptsTmp.add(toAdd);
				}
				lineScanner.close();
			}
			else if(nextLine.equals("Business_Unit")) {
				lineScanner = new Scanner(console.nextLine());
				lineScanner.useDelimiter(", ");
				while (lineScanner.hasNext()) {
					String toAdd = lineScanner.next();
					businessUnitOptsTmp.add(toAdd);
				}
				lineScanner.close();
			}
			else if(nextLine.equals("Data_Center")) {
				lineScanner = new Scanner(console.nextLine());
				lineScanner.useDelimiter(", ");
				while (lineScanner.hasNext()) {
					String toAdd = lineScanner.next();
					datacenterOptsTmp.add(toAdd);
				}
				lineScanner.close();
			}
			else if(nextLine.equals("Enterprise_controller")) {
				lineScanner = new Scanner(console.nextLine());
				lineScanner.useDelimiter(", ");
				while (lineScanner.hasNext()) {
					String toAdd = lineScanner.next();
					enterpriseControllerOptsTmp.add(toAdd);
				}
				lineScanner.close();
			}
			else if(nextLine.equals("Security_Zone_0")) {
				lineScanner = new Scanner(console.nextLine());
				lineScanner.useDelimiter(", ");
				while (lineScanner.hasNext()) {
					String toAdd = lineScanner.next();
					securityZoneZeroOptsTmp.add(toAdd);
				}
				lineScanner.close();
			}
			else if(nextLine.equals("security_zone_hudson")) {
				lineScanner = new Scanner(console.nextLine());
				lineScanner.useDelimiter(", ");
				while (lineScanner.hasNext()) {
					String toAdd = lineScanner.next();
					securityZoneHudsonOptsTmp.add(toAdd);
				}
				lineScanner.close();
			}
			else if(nextLine.equals("security_zone_rochelle")) {
				lineScanner = new Scanner(console.nextLine());
				lineScanner.useDelimiter(", ");
				while (lineScanner.hasNext()) {
					String toAdd = lineScanner.next();
					securityZoneRochelleOptsTmp.add(toAdd);
				}
				lineScanner.close();
			}
			else if(nextLine.equals("wing_location")) {
				lineScanner = new Scanner(console.nextLine());
				lineScanner.useDelimiter(", ");
				while (lineScanner.hasNext()) {
					String toAdd = lineScanner.next();
					wingLocationOptsTmp.add(toAdd);
				}
				lineScanner.close();
			}
			else if(nextLine.equals("hadoop_options")) {
				  lineScanner = new Scanner(console.nextLine());
				  lineScanner.useDelimiter(", ");
				  while (lineScanner.hasNext()) {
						String toAdd = lineScanner.next();
						hadoopOptsTmp.add(toAdd);
				  }
			
			lineScanner.close();
			}
	   }

		console.close();
		setHadoopOptions();
	}

	public String[] getNumServers() {
		return numServers;
	}

	public String[] getEnvironmentOpts() {
		return environmentOpts;
	}

	public String[] getBusinessUnitsOpts() {
		return businessUnitOpts;
	}

	public String[] getDataCenterOpts() {
		return datacenterOpts;
	}

	public String[] getEnterpriseControllerOpts() {
		return enterpriseControllerOpts;
	}

	public String[] getSecurityZoneZeroOpts() {
		return securityZoneZeroOpts;
	}

	public String[] getSecurityZoneHudsonOpts() {
		return securityZoneHudsonOpts;
	}

	public String[] getSecurityZoneRochelleOpts() {
		return securityZoneRochelleOpts;
	}

	public String[] getWingLocationOpts() {
		return wingLocationOpts;
	}
	public String[] hadoopOpts() {
			return hadoopOpts;
	}
	
	public Hadoop() {
		parseHadoopFile();
	}


	public dataCenterRtn dataCenterUpdate(String selectedOption) {
		dataCenterRtn retOpts = new dataCenterRtn();
		
		if(selectedOption.equals("Hudson")) {
			retOpts.addWingLocation = true;
			retOpts.wingLocationOptions = wingLocationOpts;
			retOpts.securityZoneOptions = securityZoneHudsonOpts;
		}
		else if(selectedOption.equals("Rochelle")) {
			retOpts.addWingLocation = false;
			retOpts.securityZoneOptions = securityZoneRochelleOpts;
		}
		else {
			retOpts.addWingLocation = false;
			retOpts.securityZoneOptions = securityZoneZeroOpts;
		}
		
		return retOpts;
	}
}


