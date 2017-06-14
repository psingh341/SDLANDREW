import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

class dataCenterRtn
{
	String[] wingLocationOptions;
	String[] securityZoneOptions;
	boolean addWingLocation;
};

public class Tibco {
	File file = new File("ProgramFiles\\Tibco.txt");
	String[] numServers;
	ArrayList<String> numServersTmp = new ArrayList<String>();
	String[] environmentOpts;
	ArrayList<String> environmentOptsTmp = new ArrayList<String>();
	String[] businessUnitOpts;
	ArrayList<String> businessUnitOptsTmp = new ArrayList<String>();
	String[] dataCenterOpts;
	ArrayList<String> dataCenterOptsTmp = new ArrayList<String>();
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
	String[] tibcoBehaviorOpts;
	ArrayList<String> tibcoBehaviorOptsTmp = new ArrayList<String>();
	String[] tibcoModuleOpts;
	ArrayList<String> tibcoModuleOptsTmp = new ArrayList<String>();
	String[] maintenanceWindowOpts;
	ArrayList<String> maintenanceWindowOptsTmp = new ArrayList<String>();
	String[] tibcoOpts;
	ArrayList<String> tibcoOptsTmp = new ArrayList<String>();
	
	private void setTibcoOptions() {
		numServers = new String[numServersTmp.size()];
		for(int i = 0; i < numServers.length; i++) {
			numServers[i] = numServersTmp.get(i);
		}
		
		environmentOpts = new String[environmentOptsTmp.size()];
		for(int i = 0; i < environmentOpts.length; i++) {
			environmentOpts[i] = environmentOptsTmp.get(i);
		}
		
		businessUnitOpts = new String[businessUnitOptsTmp.size()];
		for(int i = 0; i < businessUnitOpts.length; i++) {
			businessUnitOpts[i] = businessUnitOptsTmp.get(i);
		}
		
		dataCenterOpts = new String[dataCenterOptsTmp.size()];
		for(int i = 0; i < dataCenterOpts.length; i++) {
			dataCenterOpts[i] = dataCenterOptsTmp.get(i);
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
		
		tibcoBehaviorOpts = new String[tibcoBehaviorOptsTmp.size()];
		for(int i = 0; i < tibcoBehaviorOpts.length; i++) {
			tibcoBehaviorOpts[i] = tibcoBehaviorOptsTmp.get(i);
		}
		
		tibcoModuleOpts = new String[tibcoModuleOptsTmp.size()];
		for(int i = 0; i < tibcoModuleOpts.length; i++) {
			tibcoModuleOpts[i] = tibcoModuleOptsTmp.get(i);
		}
		
		maintenanceWindowOpts = new String[maintenanceWindowOptsTmp.size()];
		for(int i = 0; i < maintenanceWindowOpts.length; i++) {
			maintenanceWindowOpts[i] = maintenanceWindowOptsTmp.get(i);
		}
		
		tibcoOpts = new String[tibcoOptsTmp.size()];
		for(int i = 0; i < tibcoOpts.length; i++) {
			tibcoOpts[i] = tibcoOptsTmp.get(i);
		}
	}
	
	private void parseTibcoFile() {
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
			else if(nextLine.equals("business_units")) {
				lineScanner = new Scanner(console.nextLine());
				lineScanner.useDelimiter(", ");
				while (lineScanner.hasNext()) {
					String toAdd = lineScanner.next();
					businessUnitOptsTmp.add(toAdd);
				}
				lineScanner.close();
			}
			else if(nextLine.equals("data_center")) {
				lineScanner = new Scanner(console.nextLine());
				lineScanner.useDelimiter(", ");
				while (lineScanner.hasNext()) {
					String toAdd = lineScanner.next();
					dataCenterOptsTmp.add(toAdd);
				}
				lineScanner.close();
			}
			else if(nextLine.equals("enterprise_controller")) {
				lineScanner = new Scanner(console.nextLine());
				lineScanner.useDelimiter(", ");
				while (lineScanner.hasNext()) {
					String toAdd = lineScanner.next();
					enterpriseControllerOptsTmp.add(toAdd);
				}
				lineScanner.close();
			}
			else if(nextLine.equals("security_zone_0")) {
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
			else if(nextLine.equals("tibco_behavior")) {
				lineScanner = new Scanner(console.nextLine());
				lineScanner.useDelimiter(", ");
				while (lineScanner.hasNext()) {
					String toAdd = lineScanner.next();
					tibcoBehaviorOptsTmp.add(toAdd);
				}
				lineScanner.close();
			}
			else if(nextLine.equals("tibco_module")) {
				lineScanner = new Scanner(console.nextLine());
				lineScanner.useDelimiter(", ");
				while (lineScanner.hasNext()) {
					String toAdd = lineScanner.next();
					tibcoModuleOptsTmp.add(toAdd);
				}
				lineScanner.close();
			}
			else if(nextLine.equals("maintenance_window")) {
				lineScanner = new Scanner(console.nextLine());
				lineScanner.useDelimiter(", ");
				while (lineScanner.hasNext()) {
					String toAdd = lineScanner.next();
					maintenanceWindowOptsTmp.add(toAdd);
				}
				lineScanner.close();
			}
			else if(nextLine.equals("tibco_options")) {
				lineScanner = new Scanner(console.nextLine());
				lineScanner.useDelimiter(", ");
				while (lineScanner.hasNext()) {
					String toAdd = lineScanner.next();
					tibcoOptsTmp.add(toAdd);
				}
				lineScanner.close();
			}
		}
		
		console.close();
		setTibcoOptions();
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
		return dataCenterOpts;
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
	
	public String[] getTibcoBehaviorOpts() {
		return tibcoBehaviorOpts;
	}
	
	public String[] getTibcoModuleOpts() {
		return tibcoModuleOpts;
	}
	
	public String[] getMaintenanceWindowOpts() {
		return maintenanceWindowOpts;
	}
	
	public String[] getTibcoOpts() {
		return tibcoOpts;
	}
	
	public Tibco() {
		parseTibcoFile();
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
