import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Opod {

	File file = new File("ProgramFiles\\Opod.txt");
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
	String[] vpoolStorageOpts;
	ArrayList<String> vpoolStorageOptsTmp = new ArrayList<String>();
	String[] numDatabaseOpts;
	ArrayList<String> numDatabaseOptsTmp = new ArrayList<String>();
	String[] versionOpts;
	ArrayList<String> versionOptsTmp = new ArrayList<String>();
	String[] backupTypeOpts;
	ArrayList<String> backupTypeOptsTmp = new ArrayList<String>();
	String[] numDiskGroupsOpts;
	ArrayList<String> numDiskGroupsOptsTmp = new ArrayList<String>();
	String[] oracleSHSModulesOpts;
	ArrayList<String> oracleSHSModulesOptsTmp = new ArrayList<String>();
	String[] tierOpts;
	ArrayList<String> tierOptsTmp = new ArrayList<String>();
	String[] storageArrayOpts;
	ArrayList<String> storageArrayOptsTmp = new ArrayList<String>();
	
	private void setOpodOptions() {
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
		
		vpoolStorageOpts = new String[vpoolStorageOptsTmp.size()];
		for(int i = 0; i < vpoolStorageOpts.length; i++) {
			vpoolStorageOpts[i] = vpoolStorageOptsTmp.get(i);
		}
		
		numDatabaseOpts = new String[numDatabaseOptsTmp.size()];
		for(int i = 0; i < numDatabaseOpts.length; i++) {
			numDatabaseOpts[i] = numDatabaseOptsTmp.get(i);
		}
		
		versionOpts = new String[versionOptsTmp.size()];
		for(int i = 0; i < versionOpts.length; i++) {
			versionOpts[i] = versionOptsTmp.get(i);
		}
		
		backupTypeOpts = new String[backupTypeOptsTmp.size()];
		for(int i = 0; i < backupTypeOpts.length; i++) {
			backupTypeOpts[i] = backupTypeOptsTmp.get(i);
		}
		
		numDiskGroupsOpts = new String[numDiskGroupsOptsTmp.size()];
		for(int i = 0; i < numDiskGroupsOpts.length; i++) {
			numDiskGroupsOpts[i] = numDiskGroupsOptsTmp.get(i);
		}
		
		tierOpts = new String[tierOptsTmp.size()];
		for(int i = 0; i < tierOpts.length; i++) {
			tierOpts[i] = tierOptsTmp.get(i);
		}
		
		storageArrayOpts = new String[storageArrayOptsTmp.size()];
		for(int i = 0; i < storageArrayOpts.length; i++) {
			storageArrayOpts[i] = storageArrayOptsTmp.get(i);
		}
		
		oracleSHSModulesOpts = new String[oracleSHSModulesOptsTmp.size()];
		for(int i = 0; i < oracleSHSModulesOpts.length; i++) {
			oracleSHSModulesOpts[i] = oracleSHSModulesOptsTmp.get(i);
		}
	}
	
	private void parseOpodFile() {
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
			else if(nextLine.equals("environments")) {
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
			else if(nextLine.equals("vpool_storage_array")) {
				lineScanner = new Scanner(console.nextLine());
				lineScanner.useDelimiter(", ");
				while (lineScanner.hasNext()) {
					String toAdd = lineScanner.next();
					vpoolStorageOptsTmp.add(toAdd);
				}
				lineScanner.close();
			}
			else if(nextLine.equals("num_databases")) {
				lineScanner = new Scanner(console.nextLine());
				lineScanner.useDelimiter(", ");
				while (lineScanner.hasNext()) {
					String toAdd = lineScanner.next();
					numDatabaseOptsTmp.add(toAdd);
				}
				lineScanner.close();
			}
			else if(nextLine.equals("version")) {
				lineScanner = new Scanner(console.nextLine());
				lineScanner.useDelimiter(", ");
				while (lineScanner.hasNext()) {
					String toAdd = lineScanner.next();
					versionOptsTmp.add(toAdd);
				}
				lineScanner.close();
			}
			else if(nextLine.equals("backup_type")) {
				lineScanner = new Scanner(console.nextLine());
				lineScanner.useDelimiter(", ");
				while (lineScanner.hasNext()) {
					String toAdd = lineScanner.next();
					backupTypeOptsTmp.add(toAdd);
				}
				lineScanner.close();
			}
			else if(nextLine.equals("num_disk_groups")) {
				lineScanner = new Scanner(console.nextLine());
				lineScanner.useDelimiter(", ");
				while (lineScanner.hasNext()) {
					String toAdd = lineScanner.next();
					numDiskGroupsOptsTmp.add(toAdd);
				}
				lineScanner.close();
			}
			else if(nextLine.equals("oracle_SHS_modules")) {
				lineScanner = new Scanner(console.nextLine());
				lineScanner.useDelimiter(", ");
				while (lineScanner.hasNext()) {
					String toAdd = lineScanner.next();
					oracleSHSModulesOptsTmp.add(toAdd);
				}
				lineScanner.close();
			}
			else if(nextLine.equals("tier")) {
				lineScanner = new Scanner(console.nextLine());
				lineScanner.useDelimiter(", ");
				while (lineScanner.hasNext()) {
					String toAdd = lineScanner.next();
					tierOptsTmp.add(toAdd);
				}
				lineScanner.close();
			}
			else if(nextLine.equals("storage_array")) {
				lineScanner = new Scanner(console.nextLine());
				lineScanner.useDelimiter(", ");
				while (lineScanner.hasNext()) {
					String toAdd = lineScanner.next();
					storageArrayOptsTmp.add(toAdd);
				}
				lineScanner.close();
			}
			else if(nextLine.equals("oracle_SHS_modules")) {
				lineScanner = new Scanner(console.nextLine());
				lineScanner.useDelimiter(", ");
				while (lineScanner.hasNext()) {
					String toAdd = lineScanner.next();
					oracleSHSModulesOptsTmp.add(toAdd);
				}
				lineScanner.close();
			}
		}
		
		console.close();
		setOpodOptions();
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
	
	public String[] getVpoolStorageOpts() {
		return vpoolStorageOpts;
	}
	
	public String[] getNumDatabasesOpts() {
		return numDatabaseOpts;
	}
	
	public String[] getVersionOpts() {
		return versionOpts;
	}
	
	public String[] getBackupTypeOpts() {
		return backupTypeOpts;
	}
	
	public String[] getNumDiskGroupsOpts() {
		return numDiskGroupsOpts;
	}
	
	public String[] getOracleSHSModulesOpts() {
		return oracleSHSModulesOpts;
	}
	
	public String[] getTierOpts() {
		return tierOpts;
	}
	
	public String[] getStorageArrayOpts() {
		return storageArrayOpts;
	}
	
	public Opod() {
		parseOpodFile();
	}
}
