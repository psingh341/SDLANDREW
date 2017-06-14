import javax.swing.JFrame;
import java.awt.CardLayout;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.SystemColor;
import java.awt.Toolkit;

import javax.swing.JComboBox;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JCheckBox;
import javax.swing.JScrollPane;

public class GUI {

	public JFrame frame;
	public UnixHWConfigs unixConfig = new UnixHWConfigs();
	public Tibco tibco = new Tibco();
	public Hadoop hadoop = new Hadoop();
	public Opod opod = new Opod();
	
	//JLabel lblTibcoWingLocation;
	//JComboBox tibcoWingLocationCB;

	/**
	 * Create the application.
	 * 
	 * @throws URISyntaxException
	 */
	public GUI() throws URISyntaxException {
		initialize();
	}

	// Initialize method to start the gui
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void initialize() throws URISyntaxException {

		// The frame to be used throughout the program
		frame = new JFrame();
		frame.setBounds(100, 100, 1900, 905);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new CardLayout(0, 0));
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 

		// Initial panel that is presented at the start of the program
		JPanel InitView = new JPanel();
		InitView.setBackground(SystemColor.menu);
		frame.getContentPane().add(InitView, "name_933255140938789");
		InitView.setLayout(null);

		// Window button on the first page
		JButton windowsButton = new JButton("Windows");
		windowsButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		windowsButton.setBounds(10, 91, 208, 204);
		InitView.add(windowsButton);

		// Unix button on the first page
		JButton unixButton = new JButton("Unix/Linux");
		unixButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		unixButton.setBounds(261, 91, 208, 204);
		InitView.add(unixButton);

		// Code for linking the sdl sharepoint site to button on the first page
		final URI uri = new URI("http://share.allstate.com/sites/wsteam/SDL/default.aspx");
		class OpenUrlAction implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {
				open(uri);
			}
		}
		JButton sdlWebsiteButton = new JButton("SDL Sharepoint Site");
		sdlWebsiteButton.setBounds(10, 343, 332, 81);
		sdlWebsiteButton.setToolTipText(uri.toString());
		sdlWebsiteButton.addActionListener(new OpenUrlAction());
		InitView.add(sdlWebsiteButton);

		// Label for the Server Design Layout on initial page
		JLabel titleofPrgrm = new JLabel("Server Design Layout - SDL");
		titleofPrgrm.setFont(new Font("Times New Roman", Font.PLAIN, 28));
		titleofPrgrm.setBounds(10, 0, 660, 33);
		InitView.add(titleofPrgrm);

		// Question on the first page for user to select their desired solution
		JLabel initQuest = new JLabel("What is the platform for your solution?");
		initQuest.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		initQuest.setBounds(10, 57, 332, 23);
		InitView.add(initQuest);

		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(0, 44, 998, 2);
		InitView.add(separator_3);

		JSeparator separator_4 = new JSeparator();
		separator_4.setBounds(0, 312, 998, 2);
		InitView.add(separator_4);

		//The unixHWConfig panel
		JPanel UnixHWConfig = new JPanel();
		frame.getContentPane().add(UnixHWConfig, "name_149004331042821");
		UnixHWConfig.setLayout(null);

		JLabel lblUnixHwConfigs = new JLabel("Unix HW Configs");
		lblUnixHwConfigs.setFont(new Font("Tahoma", Font.PLAIN, 34));
		lblUnixHwConfigs.setBounds(10, 11, 978, 57);
		UnixHWConfig.add(lblUnixHwConfigs);

		JSeparator separator = new JSeparator();
		separator.setBounds(0, 66, 998, 2);
		UnixHWConfig.add(separator);

		JLabel lblIfYourSolution = new JLabel(
				"If your solution allows the utilization of the Server Hardware Modules, please select any of these below");
		lblIfYourSolution.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblIfYourSolution.setBounds(10, 79, 687, 25);
		UnixHWConfig.add(lblIfYourSolution);

		JPanel BladeServers = new JPanel();
		frame.getContentPane().add(BladeServers, "name_232621603305537");
		BladeServers.setLayout(null);

		JPanel PhysicalRack = new JPanel();
		frame.getContentPane().add(PhysicalRack, "name_240323611214493");
		PhysicalRack.setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 978, 854);
		PhysicalRack.add(tabbedPane);

		JPanel panel_7 = new JPanel();
		tabbedPane.addTab("New tab", null, panel_7, null);

		JComboBox serverModulesCB = new JComboBox(unixConfig.getModelNames());
		serverModulesCB.setBounds(10, 126, 429, 20);
		UnixHWConfig.add(serverModulesCB);

		JButton serverModulesBtn = new JButton("Proceed with the selected option");
		serverModulesBtn.setBounds(485, 125, 279, 23);
		UnixHWConfig.add(serverModulesBtn);
		serverModulesBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (serverModulesCB.getSelectedItem().toString().startsWith("Blade")) {
					UnixHWConfig.setVisible(false);
					BladeServers.setVisible(true);
				} else if (serverModulesCB.getSelectedItem().toString().startsWith("Physical")) {
					UnixHWConfig.setVisible(false);
					PhysicalRack.setVisible(true);
				}

			}
		});

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(0, 176, 998, 2);
		UnixHWConfig.add(separator_1);

		JLabel lblAnyDesignDeviation = new JLabel(
				"Any design deviation from any of the listed SHS's will dissolve the SHS solution");
		lblAnyDesignDeviation.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAnyDesignDeviation.setBounds(10, 194, 687, 25);
		UnixHWConfig.add(lblAnyDesignDeviation);

		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(0, 344, 998, 2);
		UnixHWConfig.add(separator_2);

		final URI uriOne = new URI("http://share.allstate.com/sites/bhs/shs/Modules/Forms/Allltems.aspx");
		class OpenUrlAction1 implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {
				open(uriOne);
			}
		}
		JButton websiteLinkBtn = new JButton("Link to the module selections and options");
		websiteLinkBtn.setBounds(10, 375, 429, 85);
		websiteLinkBtn.setToolTipText(uriOne.toString());
		websiteLinkBtn.addActionListener(new OpenUrlAction1());
		UnixHWConfig.add(websiteLinkBtn);
		
		JPanel Hadoop = new JPanel();
		frame.getContentPane().add(Hadoop, "name_166765278662121");
		Hadoop.setLayout(null);
		
		JPanel OPOD = new JPanel();
		frame.getContentPane().add(OPOD, "name_166927836447044");
		OPOD.setLayout(null);
		
		JComboBox standardHostingSysCB = new JComboBox(unixConfig.getStandardHostingOps());
		standardHostingSysCB.setBounds(10, 267, 429, 20);
		UnixHWConfig.add(standardHostingSysCB);
		JPanel TIBCO = new JPanel();
		frame.getContentPane().add(TIBCO, "name_166686193052355");
		TIBCO.setLayout(null);
		JButton standardHostingSysBtn = new JButton("Proceed with the selected option");
		standardHostingSysBtn.setBounds(485, 266, 279, 23);
		UnixHWConfig.add(standardHostingSysBtn);
		standardHostingSysBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (standardHostingSysCB.getSelectedItem().toString().equals("TIBCO")) {
					UnixHWConfig.setVisible(false);
					TIBCO.setVisible(true);
				}
				if (standardHostingSysCB.getSelectedItem().toString().equals("Hadoop")) {
					UnixHWConfig.setVisible(false);
					Hadoop.setVisible(true);
				}
				if (standardHostingSysCB.getSelectedItem().toString().equals("OPOD")) {
					UnixHWConfig.setVisible(false);
					OPOD.setVisible(true);
				}
			}
		});

		JPanel AltServer = new JPanel();
		frame.getContentPane().add(AltServer, "name_240502931499002");

		JLabel lblTibcoTitle = new JLabel("TIBCO");
		lblTibcoTitle.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblTibcoTitle.setBounds(10, 11, 978, 44);
		TIBCO.add(lblTibcoTitle);

		JSeparator separator_7 = new JSeparator();
		separator_7.setBounds(0, 55, 998, 2);
		TIBCO.add(separator_7);

		JLabel lblTibcoUniqueFields = new JLabel("Required UNIQUE fields to be filled in prior to selecting the SHS Option");
		lblTibcoUniqueFields.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTibcoUniqueFields.setBounds(10, 69, 479, 26);
		TIBCO.add(lblTibcoUniqueFields);

		JLabel lblTibcoNumServers = new JLabel("How many Unix Server(s) total are planned?");
		lblTibcoNumServers.setBounds(10, 119, 261, 14);
		TIBCO.add(lblTibcoNumServers);

		JComboBox tibcoNumServersCB = new JComboBox(tibco.getNumServers());
		tibcoNumServersCB.setBounds(353, 116, 241, 20);
		TIBCO.add(tibcoNumServersCB);

		JComboBox tibcoEnvironmentCB = new JComboBox(tibco.getEnvironmentOpts());
		tibcoEnvironmentCB.setBounds(263, 162, 226, 20);
		TIBCO.add(tibcoEnvironmentCB);

		JComboBox tibcoBusinessUnitCB = new JComboBox(tibco.getBusinessUnitsOpts());
		tibcoBusinessUnitCB.setBounds(263, 207, 226, 20);
		TIBCO.add(tibcoBusinessUnitCB);

		
		JComboBox tibcoSecurityZoneCB = new JComboBox(tibco.getSecurityZoneZeroOpts());
		tibcoSecurityZoneCB.setBounds(263, 350, 226, 20);
		TIBCO.add(tibcoSecurityZoneCB);
		
		JComboBox tibcoWingLocationCB = new JComboBox(tibco.getWingLocationOpts());
		tibcoWingLocationCB.setVisible(false);
		tibcoWingLocationCB.setBounds(750, 350, 226, 20);
		TIBCO.add(tibcoWingLocationCB);
		
		JLabel lblTibcoWingLocation = new JLabel("Wing Location in the DC:");
		lblTibcoWingLocation.setVisible(false);
		lblTibcoWingLocation.setBounds(548, 353, 179, 14);
		TIBCO.add(lblTibcoWingLocation);
		
		JComboBox tibcoDataCenterCB = new JComboBox(tibco.getDataCenterOpts());
		tibcoDataCenterCB.setBounds(263, 255, 226, 20);
		TIBCO.add(tibcoDataCenterCB);
		
		tibcoDataCenterCB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dataCenterRtn retVals = tibco.dataCenterUpdate(tibcoDataCenterCB.getSelectedItem().toString());

				if(retVals.addWingLocation == true) {
					lblTibcoWingLocation.setVisible(true);
					tibcoWingLocationCB.setVisible(true);
				}
				else {
					lblTibcoWingLocation.setVisible(false);
					tibcoWingLocationCB.setVisible(false);
				}
				
				// Update security zone options
				tibcoSecurityZoneCB.removeAllItems();
				for(int i =0; i < retVals.securityZoneOptions.length; i++) {
					tibcoSecurityZoneCB.addItem(retVals.securityZoneOptions[i]);
				}
			}
			
		});		
		
		JComboBox tibcoEnterpriseControllerCB = new JComboBox(tibco.getEnterpriseControllerOpts());
		tibcoEnterpriseControllerCB.setBounds(263, 302, 226, 20);
		TIBCO.add(tibcoEnterpriseControllerCB);

		JLabel lblTibcoEnvironment = new JLabel("Environment Selection:");
		lblTibcoEnvironment.setBounds(10, 165, 161, 14);
		TIBCO.add(lblTibcoEnvironment);

		JLabel lblTibcoBusinessUnit = new JLabel("Business Unit:");
		lblTibcoBusinessUnit.setBounds(10, 210, 112, 14);
		TIBCO.add(lblTibcoBusinessUnit);

		JLabel lblTibcoDataCenter = new JLabel("Data Center:");
		lblTibcoDataCenter.setBounds(10, 258, 76, 14);
		TIBCO.add(lblTibcoDataCenter);

		JLabel lblTibcoEnterpriseController = new JLabel("Enterprise Controller:");
		lblTibcoEnterpriseController.setBounds(10, 305, 161, 14);
		TIBCO.add(lblTibcoEnterpriseController);

		JLabel lblTibcoSecurityZone = new JLabel("Security Zone:");
		lblTibcoSecurityZone.setBounds(10, 353, 112, 14);
		TIBCO.add(lblTibcoSecurityZone);

		JComboBox tibcoBehaviorCB = new JComboBox(tibco.getTibcoBehaviorOpts());
		tibcoBehaviorCB.setBounds(263, 400, 226, 20);
		TIBCO.add(tibcoBehaviorCB);

		JComboBox tibcoModuleCB = new JComboBox(tibco.getTibcoModuleOpts());
		tibcoModuleCB.setBounds(263, 449, 226, 20);
		TIBCO.add(tibcoModuleCB);

		JComboBox tibcoMaintenanceWindowCB = new JComboBox(tibco.getMaintenanceWindowOpts());
		tibcoMaintenanceWindowCB.setBounds(263, 495, 226, 20);
		TIBCO.add(tibcoMaintenanceWindowCB);

		JLabel lblTibcoBehavior = new JLabel("Tibco Behavior:");
		lblTibcoBehavior.setBounds(10, 403, 93, 14);
		TIBCO.add(lblTibcoBehavior);

		JLabel lblTibcoModule = new JLabel("Tibco Module:");
		lblTibcoModule.setBounds(10, 452, 112, 14);
		TIBCO.add(lblTibcoModule);

		JLabel lblTibcoMaintenanceWindow = new JLabel("Maintenance Window:");
		lblTibcoMaintenanceWindow.setBounds(10, 498, 140, 14);
		TIBCO.add(lblTibcoMaintenanceWindow);

		JSeparator separator_8 = new JSeparator();
		separator_8.setBounds(0, 544, 998, 2);
		TIBCO.add(separator_8);

		JLabel lblTibcoOptions = new JLabel("Please select one of the following options");
		lblTibcoOptions.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTibcoOptions.setBounds(10, 557, 479, 37);
		TIBCO.add(lblTibcoOptions);

		JComboBox tibcoOptionsCB = new JComboBox(tibco.getTibcoOpts());
		tibcoOptionsCB.setBounds(20, 607, 418, 20);
		TIBCO.add(tibcoOptionsCB);

		JButton tibcoOptionsBtn = new JButton("Proceed with the selected option");
		tibcoOptionsBtn.setBounds(448, 605, 241, 23);
		TIBCO.add(tibcoOptionsBtn);
		
		

		JLabel lblHadoopTitle = new JLabel("Hadoop");
		lblHadoopTitle.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblHadoopTitle.setBounds(10, 11, 978, 43);
		Hadoop.add(lblHadoopTitle);

		JSeparator separator_5 = new JSeparator();
		separator_5.setBounds(0, 65, 998, 2);
		Hadoop.add(separator_5);

		JLabel lblHadoopRequiredFields = new JLabel(
				"Required UNIQUE fields to be filled in prior to selecting the SHS Option");
		lblHadoopRequiredFields.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblHadoopRequiredFields.setBounds(10, 78, 479, 26);
		Hadoop.add(lblHadoopRequiredFields);

		JLabel lblHadoopNumServers = new JLabel("How many Unix Server(s) total are planned?");
		lblHadoopNumServers.setBounds(10, 125, 259, 14);
		Hadoop.add(lblHadoopNumServers);

		JComboBox hadoopNumServersCB = new JComboBox(hadoop.getNumServers());
		hadoopNumServersCB.setBounds(314, 122, 226, 20);
		Hadoop.add(hadoopNumServersCB);

		JLabel lblHadoopEnvironmentSelection = new JLabel("Environment Selection:");
		lblHadoopEnvironmentSelection.setBounds(10, 164, 161, 14);
		Hadoop.add(lblHadoopEnvironmentSelection);

		JComboBox hadoopEnvironmentCB = new JComboBox(hadoop.getEnvironmentOpts());
		hadoopEnvironmentCB.setBounds(246, 161, 226, 20);
		Hadoop.add(hadoopEnvironmentCB);

		JLabel lblHadoopBusinessUnit = new JLabel("Business Unit:");
		lblHadoopBusinessUnit.setBounds(10, 207, 112, 14);
		Hadoop.add(lblHadoopBusinessUnit);

		JComboBox hadoopBusinessUnitCB = new JComboBox(hadoop.getBusinessUnitsOpts());
		hadoopBusinessUnitCB.setBounds(246, 204, 226, 20);
		Hadoop.add(hadoopBusinessUnitCB);

		JLabel lblHadoopDataCenter = new JLabel("Data Center:");
		lblHadoopDataCenter.setBounds(10, 248, 76, 14);
		Hadoop.add(lblHadoopDataCenter);
		
		JComboBox hadoopSecurityZoneCB = new JComboBox(hadoop.getSecurityZoneZeroOpts());
		hadoopSecurityZoneCB.setVisible(true);
		hadoopSecurityZoneCB.setBounds(246, 331, 226, 20);
		Hadoop.add(hadoopSecurityZoneCB);

		JLabel lblHadoopWingLocation = new JLabel("Wing location in the DC:");
		lblHadoopWingLocation.setVisible(false);
		lblHadoopWingLocation.setBounds(602, 248, 161, 14);
		Hadoop.add(lblHadoopWingLocation);
		
		JComboBox hadoopWingLocationCB = new JComboBox(hadoop.getWingLocationOpts());
		hadoopWingLocationCB.setVisible(false);
		hadoopWingLocationCB.setBounds(762, 245, 226, 20);
		Hadoop.add(hadoopWingLocationCB);
		
		JComboBox hadoopDataCenterCB = new JComboBox(hadoop.getDataCenterOpts());
		hadoopDataCenterCB.setBounds(246, 245, 226, 20);
		Hadoop.add(hadoopDataCenterCB);
		hadoopDataCenterCB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dataCenterRtn retVals = hadoop.dataCenterUpdate(hadoopDataCenterCB.getSelectedItem().toString());

				if(retVals.addWingLocation == true) {
					lblHadoopWingLocation.setVisible(true);
					hadoopWingLocationCB.setVisible(true);
				}
				else {
					lblHadoopWingLocation.setVisible(false);
					hadoopWingLocationCB.setVisible(false);
				}
				
				// Update security zone options
				hadoopSecurityZoneCB.removeAllItems();
				for(int i =0; i < retVals.securityZoneOptions.length; i++) {
					hadoopSecurityZoneCB.addItem(retVals.securityZoneOptions[i]);
				}
			}
		});

		JLabel lblHadoopEnterpriseController = new JLabel("Enterprise Controller:");
		lblHadoopEnterpriseController.setBounds(10, 292, 192, 14);
		Hadoop.add(lblHadoopEnterpriseController);

		JComboBox hadoopEnterpriseControllerCB = new JComboBox(hadoop.getEnterpriseControllerOpts());
		hadoopEnterpriseControllerCB.setBounds(246, 289, 226, 20);
		Hadoop.add(hadoopEnterpriseControllerCB);

		JLabel lblHadoopSecurityZone = new JLabel("Security Zone:");
		lblHadoopSecurityZone.setBounds(10, 334, 112, 14);
		Hadoop.add(lblHadoopSecurityZone);


		JLabel lblHadoopModules = new JLabel("Please select one of the following options");
		lblHadoopModules.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblHadoopModules.setBounds(10, 392, 479, 37);
		Hadoop.add(lblHadoopModules);

		JSeparator separator_6 = new JSeparator();
		separator_6.setBounds(0, 379, 998, 2);
		Hadoop.add(separator_6);

		JComboBox hadoopModulesCB = new JComboBox(hadoop.hadoopOpts());
		hadoopModulesCB.setBounds(20, 440, 418, 20);
		Hadoop.add(hadoopModulesCB);

		JButton hadoopModulesBtn = new JButton("Proceed with the selected option");
		hadoopModulesBtn.setBounds(448, 439, 241, 23);
		Hadoop.add(hadoopModulesBtn);
		
		
		JLabel lblOpodTitle = new JLabel("OPOD");
		lblOpodTitle.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblOpodTitle.setBounds(10, 11, 978, 43);
		OPOD.add(lblOpodTitle);
		
		JSeparator separator_9 = new JSeparator();
		separator_9.setBounds(0, 52, 998, 2);
		OPOD.add(separator_9);
		
		JLabel lblOpodUniqueFields = new JLabel("Required UNIQUE fields to be filled in prior to selecting the SHS Option");
		lblOpodUniqueFields.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblOpodUniqueFields.setBounds(10, 65, 479, 26);
		OPOD.add(lblOpodUniqueFields);
		
		JLabel lblOpodNumServers = new JLabel("How many Unix Server(s) total are planned?");
		lblOpodNumServers.setBounds(10, 114, 261, 14);
		OPOD.add(lblOpodNumServers);
		
		JTextArea opodLogicalName1 = new JTextArea();
		opodLogicalName1.setBounds(627, 206, 148, 22);
		OPOD.add(opodLogicalName1);
		
		JComboBox opodPrimaryCB1 = new JComboBox();
		opodPrimaryCB1.setBounds(510, 587, 161, 20);
		OPOD.add(opodPrimaryCB1);
		
		JComboBox opodPrimaryCB2 = new JComboBox();
		opodPrimaryCB2.setVisible(false);
		opodPrimaryCB2.setBounds(510, 622, 161, 20);
		OPOD.add(opodPrimaryCB2);
		
		JComboBox opodPrimaryCB3 = new JComboBox();
		opodPrimaryCB3.setVisible(false);
		opodPrimaryCB3.setBounds(510, 655, 161, 20);
		OPOD.add(opodPrimaryCB3);
		
		JComboBox opodPrimaryCB4 = new JComboBox();
		opodPrimaryCB4.setVisible(false);
		opodPrimaryCB4.setBounds(510, 688, 161, 20);
		OPOD.add(opodPrimaryCB4);
		
		JComboBox opodPrimaryCB5 = new JComboBox();
		opodPrimaryCB5.setVisible(false);
		opodPrimaryCB5.setBounds(510, 721, 161, 20);
		OPOD.add(opodPrimaryCB5);
		
		JComboBox opodPrimaryCB6 = new JComboBox();
		opodPrimaryCB6.setVisible(false);
		opodPrimaryCB6.setBounds(510, 754, 161, 20);
		OPOD.add(opodPrimaryCB6);
		
		JComboBox opodPrimaryCB7 = new JComboBox();
		opodPrimaryCB7.setVisible(false);
		opodPrimaryCB7.setBounds(510, 787, 161, 20);
		OPOD.add(opodPrimaryCB7);
		
		JComboBox opodPrimaryCB8 = new JComboBox();
		opodPrimaryCB8.setVisible(false);
		opodPrimaryCB8.setBounds(510, 820, 161, 20);
		OPOD.add(opodPrimaryCB8);
		
		JTextArea opodServerRelationship1 = new JTextArea();
		opodServerRelationship1.setBounds(785, 206, 148, 22);
		OPOD.add(opodServerRelationship1);
		//primaryOpts[0] = textArea_2.getText();
		
		JTextArea opodServerRelationship2 = new JTextArea();
		opodServerRelationship2.setVisible(false);
		opodServerRelationship2.setBounds(785, 239, 148, 22);
		OPOD.add(opodServerRelationship2);
		
		JTextArea opodServerRelationship3 = new JTextArea();
		opodServerRelationship3.setVisible(false);
		opodServerRelationship3.setBounds(785, 272, 148, 22);
		OPOD.add(opodServerRelationship3);
		
		JTextArea opodServerRelationship4 = new JTextArea();
		opodServerRelationship4.setVisible(false);
		opodServerRelationship4.setBounds(785, 305, 148, 22);
		OPOD.add(opodServerRelationship4);
		
		JTextArea opodServerRelationship5 = new JTextArea();
		opodServerRelationship5.setVisible(false);
		opodServerRelationship5.setBounds(785, 337, 148, 22);
		OPOD.add(opodServerRelationship5);
		
		JTextArea opodServerRelationship6 = new JTextArea();
		opodServerRelationship6.setVisible(false);
		opodServerRelationship6.setBounds(785, 370, 148, 22);
		OPOD.add(opodServerRelationship6);
		
		JTextArea opodServerRelationship7 = new JTextArea();
		opodServerRelationship7.setVisible(false);
		opodServerRelationship7.setBounds(785, 403, 148, 22);
		OPOD.add(opodServerRelationship7);
		
		JTextArea opodServerRelationship8 = new JTextArea();
		opodServerRelationship8.setBounds(785, 436, 148, 22);
		opodServerRelationship8.setVisible(false);
		OPOD.add(opodServerRelationship8);
		
		JTextArea opodServerRelationship9 = new JTextArea();
		opodServerRelationship9.setBounds(785, 471, 148, 22);
		opodServerRelationship9.setVisible(false);
		OPOD.add(opodServerRelationship9);
		
		JTextArea opodServerRelationship10 = new JTextArea();
		opodServerRelationship10.setBounds(785, 504, 148, 22);
		opodServerRelationship10.setVisible(false);
		OPOD.add(opodServerRelationship10);

		opodServerRelationship1.addFocusListener(new FocusListener() {
	
			@Override
			public void focusLost(FocusEvent arg0) {
				opodPrimaryCB1.removeAllItems();
				opodPrimaryCB2.removeAllItems();
				opodPrimaryCB3.removeAllItems();
				opodPrimaryCB4.removeAllItems();
				opodPrimaryCB5.removeAllItems();
				opodPrimaryCB6.removeAllItems();
				opodPrimaryCB7.removeAllItems();
				opodPrimaryCB8.removeAllItems();
				opodPrimaryCB1.addItem(opodServerRelationship1.getText());
				opodPrimaryCB2.addItem(opodServerRelationship1.getText());
				opodPrimaryCB3.addItem(opodServerRelationship1.getText());
				opodPrimaryCB4.addItem(opodServerRelationship1.getText());
				opodPrimaryCB5.addItem(opodServerRelationship1.getText());
				opodPrimaryCB6.addItem(opodServerRelationship1.getText());
				opodPrimaryCB7.addItem(opodServerRelationship1.getText());
				opodPrimaryCB8.addItem(opodServerRelationship1.getText());
				opodPrimaryCB1.addItem(opodServerRelationship2.getText());
				opodPrimaryCB2.addItem(opodServerRelationship2.getText());
				opodPrimaryCB3.addItem(opodServerRelationship2.getText());
				opodPrimaryCB4.addItem(opodServerRelationship2.getText());
				opodPrimaryCB5.addItem(opodServerRelationship2.getText());
				opodPrimaryCB6.addItem(opodServerRelationship2.getText());
				opodPrimaryCB7.addItem(opodServerRelationship2.getText());
				opodPrimaryCB8.addItem(opodServerRelationship2.getText());
				opodPrimaryCB1.addItem(opodServerRelationship3.getText());
				opodPrimaryCB2.addItem(opodServerRelationship3.getText());
				opodPrimaryCB3.addItem(opodServerRelationship3.getText());
				opodPrimaryCB4.addItem(opodServerRelationship3.getText());
				opodPrimaryCB5.addItem(opodServerRelationship3.getText());
				opodPrimaryCB6.addItem(opodServerRelationship3.getText());
				opodPrimaryCB7.addItem(opodServerRelationship3.getText());
				opodPrimaryCB8.addItem(opodServerRelationship3.getText());
				opodPrimaryCB1.addItem(opodServerRelationship4.getText());
				opodPrimaryCB2.addItem(opodServerRelationship4.getText());
				opodPrimaryCB3.addItem(opodServerRelationship4.getText());
				opodPrimaryCB4.addItem(opodServerRelationship4.getText());
				opodPrimaryCB5.addItem(opodServerRelationship4.getText());
				opodPrimaryCB6.addItem(opodServerRelationship4.getText());
				opodPrimaryCB7.addItem(opodServerRelationship4.getText());
				opodPrimaryCB8.addItem(opodServerRelationship4.getText());
				opodPrimaryCB1.addItem(opodServerRelationship5.getText());
				opodPrimaryCB2.addItem(opodServerRelationship5.getText());
				opodPrimaryCB3.addItem(opodServerRelationship5.getText());
				opodPrimaryCB4.addItem(opodServerRelationship5.getText());
				opodPrimaryCB5.addItem(opodServerRelationship5.getText());
				opodPrimaryCB6.addItem(opodServerRelationship5.getText());
				opodPrimaryCB7.addItem(opodServerRelationship5.getText());
				opodPrimaryCB8.addItem(opodServerRelationship5.getText());
				opodPrimaryCB1.addItem(opodServerRelationship6.getText());
				opodPrimaryCB2.addItem(opodServerRelationship6.getText());
				opodPrimaryCB3.addItem(opodServerRelationship6.getText());
				opodPrimaryCB4.addItem(opodServerRelationship6.getText());
				opodPrimaryCB5.addItem(opodServerRelationship6.getText());
				opodPrimaryCB6.addItem(opodServerRelationship6.getText());
				opodPrimaryCB7.addItem(opodServerRelationship6.getText());
				opodPrimaryCB8.addItem(opodServerRelationship6.getText());
				opodPrimaryCB1.addItem(opodServerRelationship7.getText());
				opodPrimaryCB2.addItem(opodServerRelationship7.getText());
				opodPrimaryCB3.addItem(opodServerRelationship7.getText());
				opodPrimaryCB4.addItem(opodServerRelationship7.getText());
				opodPrimaryCB5.addItem(opodServerRelationship7.getText());
				opodPrimaryCB6.addItem(opodServerRelationship7.getText());
				opodPrimaryCB7.addItem(opodServerRelationship7.getText());
				opodPrimaryCB8.addItem(opodServerRelationship7.getText());
				opodPrimaryCB1.addItem(opodServerRelationship8.getText());
				opodPrimaryCB2.addItem(opodServerRelationship8.getText());
				opodPrimaryCB3.addItem(opodServerRelationship8.getText());
				opodPrimaryCB4.addItem(opodServerRelationship8.getText());
				opodPrimaryCB5.addItem(opodServerRelationship8.getText());
				opodPrimaryCB6.addItem(opodServerRelationship8.getText());
				opodPrimaryCB7.addItem(opodServerRelationship8.getText());
				opodPrimaryCB8.addItem(opodServerRelationship8.getText());
				opodPrimaryCB1.addItem(opodServerRelationship9.getText());
				opodPrimaryCB2.addItem(opodServerRelationship9.getText());
				opodPrimaryCB3.addItem(opodServerRelationship9.getText());
				opodPrimaryCB4.addItem(opodServerRelationship9.getText());
				opodPrimaryCB5.addItem(opodServerRelationship9.getText());
				opodPrimaryCB6.addItem(opodServerRelationship9.getText());
				opodPrimaryCB7.addItem(opodServerRelationship9.getText());
				opodPrimaryCB8.addItem(opodServerRelationship9.getText());
				opodPrimaryCB1.addItem(opodServerRelationship10.getText());
				opodPrimaryCB2.addItem(opodServerRelationship10.getText());
				opodPrimaryCB3.addItem(opodServerRelationship10.getText());
				opodPrimaryCB4.addItem(opodServerRelationship10.getText());
				opodPrimaryCB5.addItem(opodServerRelationship10.getText());
				opodPrimaryCB6.addItem(opodServerRelationship10.getText());
				opodPrimaryCB7.addItem(opodServerRelationship10.getText());
				opodPrimaryCB8.addItem(opodServerRelationship10.getText());
			}

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		JTextArea opodLogicalName2 = new JTextArea();
		opodLogicalName2.setVisible(false);
		opodLogicalName2.setBounds(627, 239, 148, 22);
		OPOD.add(opodLogicalName2);
		
		JTextArea opodLogicalName3 = new JTextArea();
		opodLogicalName3.setVisible(false);
		opodLogicalName3.setBounds(627, 272, 148, 22);
		OPOD.add(opodLogicalName3);
		

		
		JTextArea opodLogicalName4 = new JTextArea();
		opodLogicalName4.setVisible(false);
		opodLogicalName4.setBounds(627, 305, 148, 22);
		OPOD.add(opodLogicalName4);
		

		
		JTextArea opodLogicalName5 = new JTextArea();
		opodLogicalName5.setVisible(false);
		opodLogicalName5.setBounds(627, 337, 148, 22);
		OPOD.add(opodLogicalName5);
		

		
		JTextArea opodLogicalName6 = new JTextArea();
		opodLogicalName6.setVisible(false);
		opodLogicalName6.setBounds(627, 370, 148, 22);
		OPOD.add(opodLogicalName6);
		

		
		JTextArea opodLogicalName7 = new JTextArea();
		opodLogicalName7.setVisible(false);
		opodLogicalName7.setBounds(627, 403, 148, 22);
		OPOD.add(opodLogicalName7);
		

		
		JTextArea opodLogicalName8 = new JTextArea();
		opodLogicalName8.setVisible(false);
		opodLogicalName8.setBounds(627, 436, 148, 22);
		OPOD.add(opodLogicalName8);
		

		
		JTextArea opodLogicalName9 = new JTextArea();
		opodLogicalName9.setBounds(627, 471, 148, 22);
		opodLogicalName9.setVisible(false);
		OPOD.add(opodLogicalName9);
		

		
		JTextArea opodLogicalName10 = new JTextArea();
		opodLogicalName10.setBounds(627, 504, 148, 22);
		opodLogicalName10.setVisible(false);
		OPOD.add(opodLogicalName10);
		
		String[] relate = {"","M5000"};
		JComboBox opodRelationshipCB1 = new JComboBox(relate);
		opodRelationshipCB1.setBounds(339, 587, 161, 20);
		OPOD.add(opodRelationshipCB1);
		
		JComboBox opodRelationshipCB2 = new JComboBox(relate);
		opodRelationshipCB2.setVisible(false);
		opodRelationshipCB2.setBounds(339, 622, 161, 20);
		OPOD.add(opodRelationshipCB2);
		
		JComboBox opodRelationshipCB3 = new JComboBox(relate);
		opodRelationshipCB3.setVisible(false);
		opodRelationshipCB3.setBounds(339, 655, 161, 20);
		OPOD.add(opodRelationshipCB3);
		
		JComboBox opodRelationshipCB4 = new JComboBox(relate);
		opodRelationshipCB4.setVisible(false);
		opodRelationshipCB4.setBounds(339, 688, 161, 20);
		OPOD.add(opodRelationshipCB4);
		
		JComboBox opodRelationshipCB5 = new JComboBox(relate);
		opodRelationshipCB5.setVisible(false);
		opodRelationshipCB5.setBounds(339, 721, 161, 20);
		OPOD.add(opodRelationshipCB5);
		
		JComboBox opodRelationshipCB6 = new JComboBox(relate);
		opodRelationshipCB6.setVisible(false);
		opodRelationshipCB6.setBounds(339, 754, 161, 20);
		OPOD.add(opodRelationshipCB6);
		
		JComboBox opodRelationshipCB7 = new JComboBox(relate);
		opodRelationshipCB7.setVisible(false);
		opodRelationshipCB7.setBounds(339, 787, 161, 20);
		OPOD.add(opodRelationshipCB7);
		
		JComboBox opodRelationshipCB8 = new JComboBox(relate);
		opodRelationshipCB8.setVisible(false);
		opodRelationshipCB8.setBounds(339, 820, 161, 20);
		OPOD.add(opodRelationshipCB8);
		

		opodLogicalName1.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent arg0) {
				opodRelationshipCB1.removeAllItems();
				opodRelationshipCB2.removeAllItems();
				opodRelationshipCB3.removeAllItems();
				opodRelationshipCB4.removeAllItems();
				opodRelationshipCB5.removeAllItems();
				opodRelationshipCB6.removeAllItems();
				opodRelationshipCB7.removeAllItems();
				opodRelationshipCB8.removeAllItems();
				opodRelationshipCB1.addItem(opodLogicalName1.getText());
				opodRelationshipCB2.addItem(opodLogicalName1.getText());
				opodRelationshipCB3.addItem(opodLogicalName1.getText());
				opodRelationshipCB4.addItem(opodLogicalName1.getText());
				opodRelationshipCB5.addItem(opodLogicalName1.getText());
				opodRelationshipCB6.addItem(opodLogicalName1.getText());
				opodRelationshipCB7.addItem(opodLogicalName1.getText());
				opodRelationshipCB8.addItem(opodLogicalName1.getText());
				opodRelationshipCB1.addItem(opodLogicalName2.getText());
				opodRelationshipCB2.addItem(opodLogicalName2.getText());
				opodRelationshipCB3.addItem(opodLogicalName2.getText());
				opodRelationshipCB4.addItem(opodLogicalName2.getText());
				opodRelationshipCB5.addItem(opodLogicalName2.getText());
				opodRelationshipCB6.addItem(opodLogicalName2.getText());
				opodRelationshipCB7.addItem(opodLogicalName2.getText());
				opodRelationshipCB8.addItem(opodLogicalName2.getText());
				opodRelationshipCB1.addItem(opodLogicalName3.getText());
				opodRelationshipCB2.addItem(opodLogicalName3.getText());
				opodRelationshipCB3.addItem(opodLogicalName3.getText());
				opodRelationshipCB4.addItem(opodLogicalName3.getText());
				opodRelationshipCB5.addItem(opodLogicalName3.getText());
				opodRelationshipCB6.addItem(opodLogicalName3.getText());
				opodRelationshipCB7.addItem(opodLogicalName3.getText());
				opodRelationshipCB8.addItem(opodLogicalName3.getText());
				opodRelationshipCB1.addItem(opodLogicalName4.getText());
				opodRelationshipCB2.addItem(opodLogicalName4.getText());
				opodRelationshipCB3.addItem(opodLogicalName4.getText());
				opodRelationshipCB4.addItem(opodLogicalName4.getText());
				opodRelationshipCB5.addItem(opodLogicalName4.getText());
				opodRelationshipCB6.addItem(opodLogicalName4.getText());
				opodRelationshipCB7.addItem(opodLogicalName4.getText());
				opodRelationshipCB8.addItem(opodLogicalName4.getText());
				opodRelationshipCB1.addItem(opodLogicalName5.getText());
				opodRelationshipCB2.addItem(opodLogicalName5.getText());
				opodRelationshipCB3.addItem(opodLogicalName5.getText());
				opodRelationshipCB4.addItem(opodLogicalName5.getText());
				opodRelationshipCB5.addItem(opodLogicalName5.getText());
				opodRelationshipCB6.addItem(opodLogicalName5.getText());
				opodRelationshipCB7.addItem(opodLogicalName5.getText());
				opodRelationshipCB8.addItem(opodLogicalName5.getText());
				opodRelationshipCB1.addItem(opodLogicalName6.getText());
				opodRelationshipCB2.addItem(opodLogicalName6.getText());
				opodRelationshipCB3.addItem(opodLogicalName6.getText());
				opodRelationshipCB4.addItem(opodLogicalName6.getText());
				opodRelationshipCB5.addItem(opodLogicalName6.getText());
				opodRelationshipCB6.addItem(opodLogicalName6.getText());
				opodRelationshipCB7.addItem(opodLogicalName6.getText());
				opodRelationshipCB8.addItem(opodLogicalName6.getText());
				opodRelationshipCB1.addItem(opodLogicalName7.getText());
				opodRelationshipCB2.addItem(opodLogicalName7.getText());
				opodRelationshipCB3.addItem(opodLogicalName7.getText());
				opodRelationshipCB4.addItem(opodLogicalName7.getText());
				opodRelationshipCB5.addItem(opodLogicalName7.getText());
				opodRelationshipCB6.addItem(opodLogicalName7.getText());
				opodRelationshipCB7.addItem(opodLogicalName7.getText());
				opodRelationshipCB8.addItem(opodLogicalName7.getText());
				opodRelationshipCB1.addItem(opodLogicalName8.getText());
				opodRelationshipCB2.addItem(opodLogicalName8.getText());
				opodRelationshipCB3.addItem(opodLogicalName8.getText());
				opodRelationshipCB4.addItem(opodLogicalName8.getText());
				opodRelationshipCB5.addItem(opodLogicalName8.getText());
				opodRelationshipCB6.addItem(opodLogicalName8.getText());
				opodRelationshipCB7.addItem(opodLogicalName8.getText());
				opodRelationshipCB8.addItem(opodLogicalName8.getText());
				opodRelationshipCB1.addItem(opodLogicalName9.getText());
				opodRelationshipCB2.addItem(opodLogicalName9.getText());
				opodRelationshipCB3.addItem(opodLogicalName9.getText());
				opodRelationshipCB4.addItem(opodLogicalName9.getText());
				opodRelationshipCB5.addItem(opodLogicalName9.getText());
				opodRelationshipCB6.addItem(opodLogicalName9.getText());
				opodRelationshipCB7.addItem(opodLogicalName9.getText());
				opodRelationshipCB8.addItem(opodLogicalName9.getText());
				opodRelationshipCB1.addItem(opodLogicalName10.getText());
				opodRelationshipCB2.addItem(opodLogicalName10.getText());
				opodRelationshipCB3.addItem(opodLogicalName10.getText());
				opodRelationshipCB4.addItem(opodLogicalName10.getText());
				opodRelationshipCB5.addItem(opodLogicalName10.getText());
				opodRelationshipCB6.addItem(opodLogicalName10.getText());
				opodRelationshipCB7.addItem(opodLogicalName10.getText());
				opodRelationshipCB8.addItem(opodLogicalName10.getText());
				opodRelationshipCB1.addItem("M5000");
				opodRelationshipCB2.addItem("M5000");
				opodRelationshipCB3.addItem("M5000");
				opodRelationshipCB4.addItem("M5000");
				opodRelationshipCB5.addItem("M5000");
				opodRelationshipCB6.addItem("M5000");
				opodRelationshipCB7.addItem("M5000");
				opodRelationshipCB8.addItem("M5000");
			}

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		opodLogicalName2.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent arg0) {
				opodRelationshipCB1.removeAllItems();
				opodRelationshipCB2.removeAllItems();
				opodRelationshipCB3.removeAllItems();
				opodRelationshipCB4.removeAllItems();
				opodRelationshipCB5.removeAllItems();
				opodRelationshipCB6.removeAllItems();
				opodRelationshipCB7.removeAllItems();
				opodRelationshipCB8.removeAllItems();
				opodRelationshipCB1.addItem(opodLogicalName1.getText());
				opodRelationshipCB2.addItem(opodLogicalName1.getText());
				opodRelationshipCB3.addItem(opodLogicalName1.getText());
				opodRelationshipCB4.addItem(opodLogicalName1.getText());
				opodRelationshipCB5.addItem(opodLogicalName1.getText());
				opodRelationshipCB6.addItem(opodLogicalName1.getText());
				opodRelationshipCB7.addItem(opodLogicalName1.getText());
				opodRelationshipCB8.addItem(opodLogicalName1.getText());
				opodRelationshipCB1.addItem(opodLogicalName2.getText());
				opodRelationshipCB2.addItem(opodLogicalName2.getText());
				opodRelationshipCB3.addItem(opodLogicalName2.getText());
				opodRelationshipCB4.addItem(opodLogicalName2.getText());
				opodRelationshipCB5.addItem(opodLogicalName2.getText());
				opodRelationshipCB6.addItem(opodLogicalName2.getText());
				opodRelationshipCB7.addItem(opodLogicalName2.getText());
				opodRelationshipCB8.addItem(opodLogicalName2.getText());
				opodRelationshipCB1.addItem(opodLogicalName3.getText());
				opodRelationshipCB2.addItem(opodLogicalName3.getText());
				opodRelationshipCB3.addItem(opodLogicalName3.getText());
				opodRelationshipCB4.addItem(opodLogicalName3.getText());
				opodRelationshipCB5.addItem(opodLogicalName3.getText());
				opodRelationshipCB6.addItem(opodLogicalName3.getText());
				opodRelationshipCB7.addItem(opodLogicalName3.getText());
				opodRelationshipCB8.addItem(opodLogicalName3.getText());
				opodRelationshipCB1.addItem(opodLogicalName4.getText());
				opodRelationshipCB2.addItem(opodLogicalName4.getText());
				opodRelationshipCB3.addItem(opodLogicalName4.getText());
				opodRelationshipCB4.addItem(opodLogicalName4.getText());
				opodRelationshipCB5.addItem(opodLogicalName4.getText());
				opodRelationshipCB6.addItem(opodLogicalName4.getText());
				opodRelationshipCB7.addItem(opodLogicalName4.getText());
				opodRelationshipCB8.addItem(opodLogicalName4.getText());
				opodRelationshipCB1.addItem(opodLogicalName5.getText());
				opodRelationshipCB2.addItem(opodLogicalName5.getText());
				opodRelationshipCB3.addItem(opodLogicalName5.getText());
				opodRelationshipCB4.addItem(opodLogicalName5.getText());
				opodRelationshipCB5.addItem(opodLogicalName5.getText());
				opodRelationshipCB6.addItem(opodLogicalName5.getText());
				opodRelationshipCB7.addItem(opodLogicalName5.getText());
				opodRelationshipCB8.addItem(opodLogicalName5.getText());
				opodRelationshipCB1.addItem(opodLogicalName6.getText());
				opodRelationshipCB2.addItem(opodLogicalName6.getText());
				opodRelationshipCB3.addItem(opodLogicalName6.getText());
				opodRelationshipCB4.addItem(opodLogicalName6.getText());
				opodRelationshipCB5.addItem(opodLogicalName6.getText());
				opodRelationshipCB6.addItem(opodLogicalName6.getText());
				opodRelationshipCB7.addItem(opodLogicalName6.getText());
				opodRelationshipCB8.addItem(opodLogicalName6.getText());
				opodRelationshipCB1.addItem(opodLogicalName7.getText());
				opodRelationshipCB2.addItem(opodLogicalName7.getText());
				opodRelationshipCB3.addItem(opodLogicalName7.getText());
				opodRelationshipCB4.addItem(opodLogicalName7.getText());
				opodRelationshipCB5.addItem(opodLogicalName7.getText());
				opodRelationshipCB6.addItem(opodLogicalName7.getText());
				opodRelationshipCB7.addItem(opodLogicalName7.getText());
				opodRelationshipCB8.addItem(opodLogicalName7.getText());
				opodRelationshipCB1.addItem(opodLogicalName8.getText());
				opodRelationshipCB2.addItem(opodLogicalName8.getText());
				opodRelationshipCB3.addItem(opodLogicalName8.getText());
				opodRelationshipCB4.addItem(opodLogicalName8.getText());
				opodRelationshipCB5.addItem(opodLogicalName8.getText());
				opodRelationshipCB6.addItem(opodLogicalName8.getText());
				opodRelationshipCB7.addItem(opodLogicalName8.getText());
				opodRelationshipCB8.addItem(opodLogicalName8.getText());
				opodRelationshipCB1.addItem(opodLogicalName9.getText());
				opodRelationshipCB2.addItem(opodLogicalName9.getText());
				opodRelationshipCB3.addItem(opodLogicalName9.getText());
				opodRelationshipCB4.addItem(opodLogicalName9.getText());
				opodRelationshipCB5.addItem(opodLogicalName9.getText());
				opodRelationshipCB6.addItem(opodLogicalName9.getText());
				opodRelationshipCB7.addItem(opodLogicalName9.getText());
				opodRelationshipCB8.addItem(opodLogicalName9.getText());
				opodRelationshipCB1.addItem(opodLogicalName10.getText());
				opodRelationshipCB2.addItem(opodLogicalName10.getText());
				opodRelationshipCB3.addItem(opodLogicalName10.getText());
				opodRelationshipCB4.addItem(opodLogicalName10.getText());
				opodRelationshipCB5.addItem(opodLogicalName10.getText());
				opodRelationshipCB6.addItem(opodLogicalName10.getText());
				opodRelationshipCB7.addItem(opodLogicalName10.getText());
				opodRelationshipCB8.addItem(opodLogicalName10.getText());
				opodRelationshipCB1.addItem("M5000");
				opodRelationshipCB2.addItem("M5000");
				opodRelationshipCB3.addItem("M5000");
				opodRelationshipCB4.addItem("M5000");
				opodRelationshipCB5.addItem("M5000");
				opodRelationshipCB6.addItem("M5000");
				opodRelationshipCB7.addItem("M5000");
				opodRelationshipCB8.addItem("M5000");
			}

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				
			}
		});

		opodLogicalName3.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent arg0) {
				opodRelationshipCB1.removeAllItems();
				opodRelationshipCB2.removeAllItems();
				opodRelationshipCB3.removeAllItems();
				opodRelationshipCB4.removeAllItems();
				opodRelationshipCB5.removeAllItems();
				opodRelationshipCB6.removeAllItems();
				opodRelationshipCB7.removeAllItems();
				opodRelationshipCB8.removeAllItems();
				opodRelationshipCB1.addItem(opodLogicalName1.getText());
				opodRelationshipCB2.addItem(opodLogicalName1.getText());
				opodRelationshipCB3.addItem(opodLogicalName1.getText());
				opodRelationshipCB4.addItem(opodLogicalName1.getText());
				opodRelationshipCB5.addItem(opodLogicalName1.getText());
				opodRelationshipCB6.addItem(opodLogicalName1.getText());
				opodRelationshipCB7.addItem(opodLogicalName1.getText());
				opodRelationshipCB8.addItem(opodLogicalName1.getText());
				opodRelationshipCB1.addItem(opodLogicalName2.getText());
				opodRelationshipCB2.addItem(opodLogicalName2.getText());
				opodRelationshipCB3.addItem(opodLogicalName2.getText());
				opodRelationshipCB4.addItem(opodLogicalName2.getText());
				opodRelationshipCB5.addItem(opodLogicalName2.getText());
				opodRelationshipCB6.addItem(opodLogicalName2.getText());
				opodRelationshipCB7.addItem(opodLogicalName2.getText());
				opodRelationshipCB8.addItem(opodLogicalName2.getText());
				opodRelationshipCB1.addItem(opodLogicalName3.getText());
				opodRelationshipCB2.addItem(opodLogicalName3.getText());
				opodRelationshipCB3.addItem(opodLogicalName3.getText());
				opodRelationshipCB4.addItem(opodLogicalName3.getText());
				opodRelationshipCB5.addItem(opodLogicalName3.getText());
				opodRelationshipCB6.addItem(opodLogicalName3.getText());
				opodRelationshipCB7.addItem(opodLogicalName3.getText());
				opodRelationshipCB8.addItem(opodLogicalName3.getText());
				opodRelationshipCB1.addItem(opodLogicalName4.getText());
				opodRelationshipCB2.addItem(opodLogicalName4.getText());
				opodRelationshipCB3.addItem(opodLogicalName4.getText());
				opodRelationshipCB4.addItem(opodLogicalName4.getText());
				opodRelationshipCB5.addItem(opodLogicalName4.getText());
				opodRelationshipCB6.addItem(opodLogicalName4.getText());
				opodRelationshipCB7.addItem(opodLogicalName4.getText());
				opodRelationshipCB8.addItem(opodLogicalName4.getText());
				opodRelationshipCB1.addItem(opodLogicalName5.getText());
				opodRelationshipCB2.addItem(opodLogicalName5.getText());
				opodRelationshipCB3.addItem(opodLogicalName5.getText());
				opodRelationshipCB4.addItem(opodLogicalName5.getText());
				opodRelationshipCB5.addItem(opodLogicalName5.getText());
				opodRelationshipCB6.addItem(opodLogicalName5.getText());
				opodRelationshipCB7.addItem(opodLogicalName5.getText());
				opodRelationshipCB8.addItem(opodLogicalName5.getText());
				opodRelationshipCB1.addItem(opodLogicalName6.getText());
				opodRelationshipCB2.addItem(opodLogicalName6.getText());
				opodRelationshipCB3.addItem(opodLogicalName6.getText());
				opodRelationshipCB4.addItem(opodLogicalName6.getText());
				opodRelationshipCB5.addItem(opodLogicalName6.getText());
				opodRelationshipCB6.addItem(opodLogicalName6.getText());
				opodRelationshipCB7.addItem(opodLogicalName6.getText());
				opodRelationshipCB8.addItem(opodLogicalName6.getText());
				opodRelationshipCB1.addItem(opodLogicalName7.getText());
				opodRelationshipCB2.addItem(opodLogicalName7.getText());
				opodRelationshipCB3.addItem(opodLogicalName7.getText());
				opodRelationshipCB4.addItem(opodLogicalName7.getText());
				opodRelationshipCB5.addItem(opodLogicalName7.getText());
				opodRelationshipCB6.addItem(opodLogicalName7.getText());
				opodRelationshipCB7.addItem(opodLogicalName7.getText());
				opodRelationshipCB8.addItem(opodLogicalName7.getText());
				opodRelationshipCB1.addItem(opodLogicalName8.getText());
				opodRelationshipCB2.addItem(opodLogicalName8.getText());
				opodRelationshipCB3.addItem(opodLogicalName8.getText());
				opodRelationshipCB4.addItem(opodLogicalName8.getText());
				opodRelationshipCB5.addItem(opodLogicalName8.getText());
				opodRelationshipCB6.addItem(opodLogicalName8.getText());
				opodRelationshipCB7.addItem(opodLogicalName8.getText());
				opodRelationshipCB8.addItem(opodLogicalName8.getText());
				opodRelationshipCB1.addItem(opodLogicalName9.getText());
				opodRelationshipCB2.addItem(opodLogicalName9.getText());
				opodRelationshipCB3.addItem(opodLogicalName9.getText());
				opodRelationshipCB4.addItem(opodLogicalName9.getText());
				opodRelationshipCB5.addItem(opodLogicalName9.getText());
				opodRelationshipCB6.addItem(opodLogicalName9.getText());
				opodRelationshipCB7.addItem(opodLogicalName9.getText());
				opodRelationshipCB8.addItem(opodLogicalName9.getText());
				opodRelationshipCB1.addItem(opodLogicalName10.getText());
				opodRelationshipCB2.addItem(opodLogicalName10.getText());
				opodRelationshipCB3.addItem(opodLogicalName10.getText());
				opodRelationshipCB4.addItem(opodLogicalName10.getText());
				opodRelationshipCB5.addItem(opodLogicalName10.getText());
				opodRelationshipCB6.addItem(opodLogicalName10.getText());
				opodRelationshipCB7.addItem(opodLogicalName10.getText());
				opodRelationshipCB8.addItem(opodLogicalName10.getText());
				opodRelationshipCB1.addItem("M5000");
				opodRelationshipCB2.addItem("M5000");
				opodRelationshipCB3.addItem("M5000");
				opodRelationshipCB4.addItem("M5000");
				opodRelationshipCB5.addItem("M5000");
				opodRelationshipCB6.addItem("M5000");
				opodRelationshipCB7.addItem("M5000");
				opodRelationshipCB8.addItem("M5000");
			}

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				
			}
		});

		opodLogicalName4.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent arg0) {
				opodRelationshipCB1.removeAllItems();
				opodRelationshipCB2.removeAllItems();
				opodRelationshipCB3.removeAllItems();
				opodRelationshipCB4.removeAllItems();
				opodRelationshipCB5.removeAllItems();
				opodRelationshipCB6.removeAllItems();
				opodRelationshipCB7.removeAllItems();
				opodRelationshipCB8.removeAllItems();
				opodRelationshipCB1.addItem(opodLogicalName1.getText());
				opodRelationshipCB2.addItem(opodLogicalName1.getText());
				opodRelationshipCB3.addItem(opodLogicalName1.getText());
				opodRelationshipCB4.addItem(opodLogicalName1.getText());
				opodRelationshipCB5.addItem(opodLogicalName1.getText());
				opodRelationshipCB6.addItem(opodLogicalName1.getText());
				opodRelationshipCB7.addItem(opodLogicalName1.getText());
				opodRelationshipCB8.addItem(opodLogicalName1.getText());
				opodRelationshipCB1.addItem(opodLogicalName2.getText());
				opodRelationshipCB2.addItem(opodLogicalName2.getText());
				opodRelationshipCB3.addItem(opodLogicalName2.getText());
				opodRelationshipCB4.addItem(opodLogicalName2.getText());
				opodRelationshipCB5.addItem(opodLogicalName2.getText());
				opodRelationshipCB6.addItem(opodLogicalName2.getText());
				opodRelationshipCB7.addItem(opodLogicalName2.getText());
				opodRelationshipCB8.addItem(opodLogicalName2.getText());
				opodRelationshipCB1.addItem(opodLogicalName3.getText());
				opodRelationshipCB2.addItem(opodLogicalName3.getText());
				opodRelationshipCB3.addItem(opodLogicalName3.getText());
				opodRelationshipCB4.addItem(opodLogicalName3.getText());
				opodRelationshipCB5.addItem(opodLogicalName3.getText());
				opodRelationshipCB6.addItem(opodLogicalName3.getText());
				opodRelationshipCB7.addItem(opodLogicalName3.getText());
				opodRelationshipCB8.addItem(opodLogicalName3.getText());
				opodRelationshipCB1.addItem(opodLogicalName4.getText());
				opodRelationshipCB2.addItem(opodLogicalName4.getText());
				opodRelationshipCB3.addItem(opodLogicalName4.getText());
				opodRelationshipCB4.addItem(opodLogicalName4.getText());
				opodRelationshipCB5.addItem(opodLogicalName4.getText());
				opodRelationshipCB6.addItem(opodLogicalName4.getText());
				opodRelationshipCB7.addItem(opodLogicalName4.getText());
				opodRelationshipCB8.addItem(opodLogicalName4.getText());
				opodRelationshipCB1.addItem(opodLogicalName5.getText());
				opodRelationshipCB2.addItem(opodLogicalName5.getText());
				opodRelationshipCB3.addItem(opodLogicalName5.getText());
				opodRelationshipCB4.addItem(opodLogicalName5.getText());
				opodRelationshipCB5.addItem(opodLogicalName5.getText());
				opodRelationshipCB6.addItem(opodLogicalName5.getText());
				opodRelationshipCB7.addItem(opodLogicalName5.getText());
				opodRelationshipCB8.addItem(opodLogicalName5.getText());
				opodRelationshipCB1.addItem(opodLogicalName6.getText());
				opodRelationshipCB2.addItem(opodLogicalName6.getText());
				opodRelationshipCB3.addItem(opodLogicalName6.getText());
				opodRelationshipCB4.addItem(opodLogicalName6.getText());
				opodRelationshipCB5.addItem(opodLogicalName6.getText());
				opodRelationshipCB6.addItem(opodLogicalName6.getText());
				opodRelationshipCB7.addItem(opodLogicalName6.getText());
				opodRelationshipCB8.addItem(opodLogicalName6.getText());
				opodRelationshipCB1.addItem(opodLogicalName7.getText());
				opodRelationshipCB2.addItem(opodLogicalName7.getText());
				opodRelationshipCB3.addItem(opodLogicalName7.getText());
				opodRelationshipCB4.addItem(opodLogicalName7.getText());
				opodRelationshipCB5.addItem(opodLogicalName7.getText());
				opodRelationshipCB6.addItem(opodLogicalName7.getText());
				opodRelationshipCB7.addItem(opodLogicalName7.getText());
				opodRelationshipCB8.addItem(opodLogicalName7.getText());
				opodRelationshipCB1.addItem(opodLogicalName8.getText());
				opodRelationshipCB2.addItem(opodLogicalName8.getText());
				opodRelationshipCB3.addItem(opodLogicalName8.getText());
				opodRelationshipCB4.addItem(opodLogicalName8.getText());
				opodRelationshipCB5.addItem(opodLogicalName8.getText());
				opodRelationshipCB6.addItem(opodLogicalName8.getText());
				opodRelationshipCB7.addItem(opodLogicalName8.getText());
				opodRelationshipCB8.addItem(opodLogicalName8.getText());
				opodRelationshipCB1.addItem(opodLogicalName9.getText());
				opodRelationshipCB2.addItem(opodLogicalName9.getText());
				opodRelationshipCB3.addItem(opodLogicalName9.getText());
				opodRelationshipCB4.addItem(opodLogicalName9.getText());
				opodRelationshipCB5.addItem(opodLogicalName9.getText());
				opodRelationshipCB6.addItem(opodLogicalName9.getText());
				opodRelationshipCB7.addItem(opodLogicalName9.getText());
				opodRelationshipCB8.addItem(opodLogicalName9.getText());
				opodRelationshipCB1.addItem(opodLogicalName10.getText());
				opodRelationshipCB2.addItem(opodLogicalName10.getText());
				opodRelationshipCB3.addItem(opodLogicalName10.getText());
				opodRelationshipCB4.addItem(opodLogicalName10.getText());
				opodRelationshipCB5.addItem(opodLogicalName10.getText());
				opodRelationshipCB6.addItem(opodLogicalName10.getText());
				opodRelationshipCB7.addItem(opodLogicalName10.getText());
				opodRelationshipCB8.addItem(opodLogicalName10.getText());
				opodRelationshipCB1.addItem("M5000");
				opodRelationshipCB2.addItem("M5000");
				opodRelationshipCB3.addItem("M5000");
				opodRelationshipCB4.addItem("M5000");
				opodRelationshipCB5.addItem("M5000");
				opodRelationshipCB6.addItem("M5000");
				opodRelationshipCB7.addItem("M5000");
				opodRelationshipCB8.addItem("M5000");
			}

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				
			}
		});

		opodLogicalName5.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent arg0) {
				opodRelationshipCB1.removeAllItems();
				opodRelationshipCB2.removeAllItems();
				opodRelationshipCB3.removeAllItems();
				opodRelationshipCB4.removeAllItems();
				opodRelationshipCB5.removeAllItems();
				opodRelationshipCB6.removeAllItems();
				opodRelationshipCB7.removeAllItems();
				opodRelationshipCB8.removeAllItems();
				opodRelationshipCB1.addItem(opodLogicalName1.getText());
				opodRelationshipCB2.addItem(opodLogicalName1.getText());
				opodRelationshipCB3.addItem(opodLogicalName1.getText());
				opodRelationshipCB4.addItem(opodLogicalName1.getText());
				opodRelationshipCB5.addItem(opodLogicalName1.getText());
				opodRelationshipCB6.addItem(opodLogicalName1.getText());
				opodRelationshipCB7.addItem(opodLogicalName1.getText());
				opodRelationshipCB8.addItem(opodLogicalName1.getText());
				opodRelationshipCB1.addItem(opodLogicalName2.getText());
				opodRelationshipCB2.addItem(opodLogicalName2.getText());
				opodRelationshipCB3.addItem(opodLogicalName2.getText());
				opodRelationshipCB4.addItem(opodLogicalName2.getText());
				opodRelationshipCB5.addItem(opodLogicalName2.getText());
				opodRelationshipCB6.addItem(opodLogicalName2.getText());
				opodRelationshipCB7.addItem(opodLogicalName2.getText());
				opodRelationshipCB8.addItem(opodLogicalName2.getText());
				opodRelationshipCB1.addItem(opodLogicalName3.getText());
				opodRelationshipCB2.addItem(opodLogicalName3.getText());
				opodRelationshipCB3.addItem(opodLogicalName3.getText());
				opodRelationshipCB4.addItem(opodLogicalName3.getText());
				opodRelationshipCB5.addItem(opodLogicalName3.getText());
				opodRelationshipCB6.addItem(opodLogicalName3.getText());
				opodRelationshipCB7.addItem(opodLogicalName3.getText());
				opodRelationshipCB8.addItem(opodLogicalName3.getText());
				opodRelationshipCB1.addItem(opodLogicalName4.getText());
				opodRelationshipCB2.addItem(opodLogicalName4.getText());
				opodRelationshipCB3.addItem(opodLogicalName4.getText());
				opodRelationshipCB4.addItem(opodLogicalName4.getText());
				opodRelationshipCB5.addItem(opodLogicalName4.getText());
				opodRelationshipCB6.addItem(opodLogicalName4.getText());
				opodRelationshipCB7.addItem(opodLogicalName4.getText());
				opodRelationshipCB8.addItem(opodLogicalName4.getText());
				opodRelationshipCB1.addItem(opodLogicalName5.getText());
				opodRelationshipCB2.addItem(opodLogicalName5.getText());
				opodRelationshipCB3.addItem(opodLogicalName5.getText());
				opodRelationshipCB4.addItem(opodLogicalName5.getText());
				opodRelationshipCB5.addItem(opodLogicalName5.getText());
				opodRelationshipCB6.addItem(opodLogicalName5.getText());
				opodRelationshipCB7.addItem(opodLogicalName5.getText());
				opodRelationshipCB8.addItem(opodLogicalName5.getText());
				opodRelationshipCB1.addItem(opodLogicalName6.getText());
				opodRelationshipCB2.addItem(opodLogicalName6.getText());
				opodRelationshipCB3.addItem(opodLogicalName6.getText());
				opodRelationshipCB4.addItem(opodLogicalName6.getText());
				opodRelationshipCB5.addItem(opodLogicalName6.getText());
				opodRelationshipCB6.addItem(opodLogicalName6.getText());
				opodRelationshipCB7.addItem(opodLogicalName6.getText());
				opodRelationshipCB8.addItem(opodLogicalName6.getText());
				opodRelationshipCB1.addItem(opodLogicalName7.getText());
				opodRelationshipCB2.addItem(opodLogicalName7.getText());
				opodRelationshipCB3.addItem(opodLogicalName7.getText());
				opodRelationshipCB4.addItem(opodLogicalName7.getText());
				opodRelationshipCB5.addItem(opodLogicalName7.getText());
				opodRelationshipCB6.addItem(opodLogicalName7.getText());
				opodRelationshipCB7.addItem(opodLogicalName7.getText());
				opodRelationshipCB8.addItem(opodLogicalName7.getText());
				opodRelationshipCB1.addItem(opodLogicalName8.getText());
				opodRelationshipCB2.addItem(opodLogicalName8.getText());
				opodRelationshipCB3.addItem(opodLogicalName8.getText());
				opodRelationshipCB4.addItem(opodLogicalName8.getText());
				opodRelationshipCB5.addItem(opodLogicalName8.getText());
				opodRelationshipCB6.addItem(opodLogicalName8.getText());
				opodRelationshipCB7.addItem(opodLogicalName8.getText());
				opodRelationshipCB8.addItem(opodLogicalName8.getText());
				opodRelationshipCB1.addItem(opodLogicalName9.getText());
				opodRelationshipCB2.addItem(opodLogicalName9.getText());
				opodRelationshipCB3.addItem(opodLogicalName9.getText());
				opodRelationshipCB4.addItem(opodLogicalName9.getText());
				opodRelationshipCB5.addItem(opodLogicalName9.getText());
				opodRelationshipCB6.addItem(opodLogicalName9.getText());
				opodRelationshipCB7.addItem(opodLogicalName9.getText());
				opodRelationshipCB8.addItem(opodLogicalName9.getText());
				opodRelationshipCB1.addItem(opodLogicalName10.getText());
				opodRelationshipCB2.addItem(opodLogicalName10.getText());
				opodRelationshipCB3.addItem(opodLogicalName10.getText());
				opodRelationshipCB4.addItem(opodLogicalName10.getText());
				opodRelationshipCB5.addItem(opodLogicalName10.getText());
				opodRelationshipCB6.addItem(opodLogicalName10.getText());
				opodRelationshipCB7.addItem(opodLogicalName10.getText());
				opodRelationshipCB8.addItem(opodLogicalName10.getText());
				opodRelationshipCB1.addItem("M5000");
				opodRelationshipCB2.addItem("M5000");
				opodRelationshipCB3.addItem("M5000");
				opodRelationshipCB4.addItem("M5000");
				opodRelationshipCB5.addItem("M5000");
				opodRelationshipCB6.addItem("M5000");
				opodRelationshipCB7.addItem("M5000");
				opodRelationshipCB8.addItem("M5000");
			}

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				
			}
		});

		opodLogicalName6.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent arg0) {
				opodRelationshipCB1.removeAllItems();
				opodRelationshipCB2.removeAllItems();
				opodRelationshipCB3.removeAllItems();
				opodRelationshipCB4.removeAllItems();
				opodRelationshipCB5.removeAllItems();
				opodRelationshipCB6.removeAllItems();
				opodRelationshipCB7.removeAllItems();
				opodRelationshipCB8.removeAllItems();
				opodRelationshipCB1.addItem(opodLogicalName1.getText());
				opodRelationshipCB2.addItem(opodLogicalName1.getText());
				opodRelationshipCB3.addItem(opodLogicalName1.getText());
				opodRelationshipCB4.addItem(opodLogicalName1.getText());
				opodRelationshipCB5.addItem(opodLogicalName1.getText());
				opodRelationshipCB6.addItem(opodLogicalName1.getText());
				opodRelationshipCB7.addItem(opodLogicalName1.getText());
				opodRelationshipCB8.addItem(opodLogicalName1.getText());
				opodRelationshipCB1.addItem(opodLogicalName2.getText());
				opodRelationshipCB2.addItem(opodLogicalName2.getText());
				opodRelationshipCB3.addItem(opodLogicalName2.getText());
				opodRelationshipCB4.addItem(opodLogicalName2.getText());
				opodRelationshipCB5.addItem(opodLogicalName2.getText());
				opodRelationshipCB6.addItem(opodLogicalName2.getText());
				opodRelationshipCB7.addItem(opodLogicalName2.getText());
				opodRelationshipCB8.addItem(opodLogicalName2.getText());
				opodRelationshipCB1.addItem(opodLogicalName3.getText());
				opodRelationshipCB2.addItem(opodLogicalName3.getText());
				opodRelationshipCB3.addItem(opodLogicalName3.getText());
				opodRelationshipCB4.addItem(opodLogicalName3.getText());
				opodRelationshipCB5.addItem(opodLogicalName3.getText());
				opodRelationshipCB6.addItem(opodLogicalName3.getText());
				opodRelationshipCB7.addItem(opodLogicalName3.getText());
				opodRelationshipCB8.addItem(opodLogicalName3.getText());
				opodRelationshipCB1.addItem(opodLogicalName4.getText());
				opodRelationshipCB2.addItem(opodLogicalName4.getText());
				opodRelationshipCB3.addItem(opodLogicalName4.getText());
				opodRelationshipCB4.addItem(opodLogicalName4.getText());
				opodRelationshipCB5.addItem(opodLogicalName4.getText());
				opodRelationshipCB6.addItem(opodLogicalName4.getText());
				opodRelationshipCB7.addItem(opodLogicalName4.getText());
				opodRelationshipCB8.addItem(opodLogicalName4.getText());
				opodRelationshipCB1.addItem(opodLogicalName5.getText());
				opodRelationshipCB2.addItem(opodLogicalName5.getText());
				opodRelationshipCB3.addItem(opodLogicalName5.getText());
				opodRelationshipCB4.addItem(opodLogicalName5.getText());
				opodRelationshipCB5.addItem(opodLogicalName5.getText());
				opodRelationshipCB6.addItem(opodLogicalName5.getText());
				opodRelationshipCB7.addItem(opodLogicalName5.getText());
				opodRelationshipCB8.addItem(opodLogicalName5.getText());
				opodRelationshipCB1.addItem(opodLogicalName6.getText());
				opodRelationshipCB2.addItem(opodLogicalName6.getText());
				opodRelationshipCB3.addItem(opodLogicalName6.getText());
				opodRelationshipCB4.addItem(opodLogicalName6.getText());
				opodRelationshipCB5.addItem(opodLogicalName6.getText());
				opodRelationshipCB6.addItem(opodLogicalName6.getText());
				opodRelationshipCB7.addItem(opodLogicalName6.getText());
				opodRelationshipCB8.addItem(opodLogicalName6.getText());
				opodRelationshipCB1.addItem(opodLogicalName7.getText());
				opodRelationshipCB2.addItem(opodLogicalName7.getText());
				opodRelationshipCB3.addItem(opodLogicalName7.getText());
				opodRelationshipCB4.addItem(opodLogicalName7.getText());
				opodRelationshipCB5.addItem(opodLogicalName7.getText());
				opodRelationshipCB6.addItem(opodLogicalName7.getText());
				opodRelationshipCB7.addItem(opodLogicalName7.getText());
				opodRelationshipCB8.addItem(opodLogicalName7.getText());
				opodRelationshipCB1.addItem(opodLogicalName8.getText());
				opodRelationshipCB2.addItem(opodLogicalName8.getText());
				opodRelationshipCB3.addItem(opodLogicalName8.getText());
				opodRelationshipCB4.addItem(opodLogicalName8.getText());
				opodRelationshipCB5.addItem(opodLogicalName8.getText());
				opodRelationshipCB6.addItem(opodLogicalName8.getText());
				opodRelationshipCB7.addItem(opodLogicalName8.getText());
				opodRelationshipCB8.addItem(opodLogicalName8.getText());
				opodRelationshipCB1.addItem(opodLogicalName9.getText());
				opodRelationshipCB2.addItem(opodLogicalName9.getText());
				opodRelationshipCB3.addItem(opodLogicalName9.getText());
				opodRelationshipCB4.addItem(opodLogicalName9.getText());
				opodRelationshipCB5.addItem(opodLogicalName9.getText());
				opodRelationshipCB6.addItem(opodLogicalName9.getText());
				opodRelationshipCB7.addItem(opodLogicalName9.getText());
				opodRelationshipCB8.addItem(opodLogicalName9.getText());
				opodRelationshipCB1.addItem(opodLogicalName10.getText());
				opodRelationshipCB2.addItem(opodLogicalName10.getText());
				opodRelationshipCB3.addItem(opodLogicalName10.getText());
				opodRelationshipCB4.addItem(opodLogicalName10.getText());
				opodRelationshipCB5.addItem(opodLogicalName10.getText());
				opodRelationshipCB6.addItem(opodLogicalName10.getText());
				opodRelationshipCB7.addItem(opodLogicalName10.getText());
				opodRelationshipCB8.addItem(opodLogicalName10.getText());
				opodRelationshipCB1.addItem("M5000");
				opodRelationshipCB2.addItem("M5000");
				opodRelationshipCB3.addItem("M5000");
				opodRelationshipCB4.addItem("M5000");
				opodRelationshipCB5.addItem("M5000");
				opodRelationshipCB6.addItem("M5000");
				opodRelationshipCB7.addItem("M5000");
				opodRelationshipCB8.addItem("M5000");
			}

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		

		opodLogicalName7.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent arg0) {
				opodRelationshipCB1.removeAllItems();
				opodRelationshipCB2.removeAllItems();
				opodRelationshipCB3.removeAllItems();
				opodRelationshipCB4.removeAllItems();
				opodRelationshipCB5.removeAllItems();
				opodRelationshipCB6.removeAllItems();
				opodRelationshipCB7.removeAllItems();
				opodRelationshipCB8.removeAllItems();
				opodRelationshipCB1.addItem(opodLogicalName1.getText());
				opodRelationshipCB2.addItem(opodLogicalName1.getText());
				opodRelationshipCB3.addItem(opodLogicalName1.getText());
				opodRelationshipCB4.addItem(opodLogicalName1.getText());
				opodRelationshipCB5.addItem(opodLogicalName1.getText());
				opodRelationshipCB6.addItem(opodLogicalName1.getText());
				opodRelationshipCB7.addItem(opodLogicalName1.getText());
				opodRelationshipCB8.addItem(opodLogicalName1.getText());
				opodRelationshipCB1.addItem(opodLogicalName2.getText());
				opodRelationshipCB2.addItem(opodLogicalName2.getText());
				opodRelationshipCB3.addItem(opodLogicalName2.getText());
				opodRelationshipCB4.addItem(opodLogicalName2.getText());
				opodRelationshipCB5.addItem(opodLogicalName2.getText());
				opodRelationshipCB6.addItem(opodLogicalName2.getText());
				opodRelationshipCB7.addItem(opodLogicalName2.getText());
				opodRelationshipCB8.addItem(opodLogicalName2.getText());
				opodRelationshipCB1.addItem(opodLogicalName3.getText());
				opodRelationshipCB2.addItem(opodLogicalName3.getText());
				opodRelationshipCB3.addItem(opodLogicalName3.getText());
				opodRelationshipCB4.addItem(opodLogicalName3.getText());
				opodRelationshipCB5.addItem(opodLogicalName3.getText());
				opodRelationshipCB6.addItem(opodLogicalName3.getText());
				opodRelationshipCB7.addItem(opodLogicalName3.getText());
				opodRelationshipCB8.addItem(opodLogicalName3.getText());
				opodRelationshipCB1.addItem(opodLogicalName4.getText());
				opodRelationshipCB2.addItem(opodLogicalName4.getText());
				opodRelationshipCB3.addItem(opodLogicalName4.getText());
				opodRelationshipCB4.addItem(opodLogicalName4.getText());
				opodRelationshipCB5.addItem(opodLogicalName4.getText());
				opodRelationshipCB6.addItem(opodLogicalName4.getText());
				opodRelationshipCB7.addItem(opodLogicalName4.getText());
				opodRelationshipCB8.addItem(opodLogicalName4.getText());
				opodRelationshipCB1.addItem(opodLogicalName5.getText());
				opodRelationshipCB2.addItem(opodLogicalName5.getText());
				opodRelationshipCB3.addItem(opodLogicalName5.getText());
				opodRelationshipCB4.addItem(opodLogicalName5.getText());
				opodRelationshipCB5.addItem(opodLogicalName5.getText());
				opodRelationshipCB6.addItem(opodLogicalName5.getText());
				opodRelationshipCB7.addItem(opodLogicalName5.getText());
				opodRelationshipCB8.addItem(opodLogicalName5.getText());
				opodRelationshipCB1.addItem(opodLogicalName6.getText());
				opodRelationshipCB2.addItem(opodLogicalName6.getText());
				opodRelationshipCB3.addItem(opodLogicalName6.getText());
				opodRelationshipCB4.addItem(opodLogicalName6.getText());
				opodRelationshipCB5.addItem(opodLogicalName6.getText());
				opodRelationshipCB6.addItem(opodLogicalName6.getText());
				opodRelationshipCB7.addItem(opodLogicalName6.getText());
				opodRelationshipCB8.addItem(opodLogicalName6.getText());
				opodRelationshipCB1.addItem(opodLogicalName7.getText());
				opodRelationshipCB2.addItem(opodLogicalName7.getText());
				opodRelationshipCB3.addItem(opodLogicalName7.getText());
				opodRelationshipCB4.addItem(opodLogicalName7.getText());
				opodRelationshipCB5.addItem(opodLogicalName7.getText());
				opodRelationshipCB6.addItem(opodLogicalName7.getText());
				opodRelationshipCB7.addItem(opodLogicalName7.getText());
				opodRelationshipCB8.addItem(opodLogicalName7.getText());
				opodRelationshipCB1.addItem(opodLogicalName8.getText());
				opodRelationshipCB2.addItem(opodLogicalName8.getText());
				opodRelationshipCB3.addItem(opodLogicalName8.getText());
				opodRelationshipCB4.addItem(opodLogicalName8.getText());
				opodRelationshipCB5.addItem(opodLogicalName8.getText());
				opodRelationshipCB6.addItem(opodLogicalName8.getText());
				opodRelationshipCB7.addItem(opodLogicalName8.getText());
				opodRelationshipCB8.addItem(opodLogicalName8.getText());
				opodRelationshipCB1.addItem(opodLogicalName9.getText());
				opodRelationshipCB2.addItem(opodLogicalName9.getText());
				opodRelationshipCB3.addItem(opodLogicalName9.getText());
				opodRelationshipCB4.addItem(opodLogicalName9.getText());
				opodRelationshipCB5.addItem(opodLogicalName9.getText());
				opodRelationshipCB6.addItem(opodLogicalName9.getText());
				opodRelationshipCB7.addItem(opodLogicalName9.getText());
				opodRelationshipCB8.addItem(opodLogicalName9.getText());
				opodRelationshipCB1.addItem(opodLogicalName10.getText());
				opodRelationshipCB2.addItem(opodLogicalName10.getText());
				opodRelationshipCB3.addItem(opodLogicalName10.getText());
				opodRelationshipCB4.addItem(opodLogicalName10.getText());
				opodRelationshipCB5.addItem(opodLogicalName10.getText());
				opodRelationshipCB6.addItem(opodLogicalName10.getText());
				opodRelationshipCB7.addItem(opodLogicalName10.getText());
				opodRelationshipCB8.addItem(opodLogicalName10.getText());
				opodRelationshipCB1.addItem("M5000");
				opodRelationshipCB2.addItem("M5000");
				opodRelationshipCB3.addItem("M5000");
				opodRelationshipCB4.addItem("M5000");
				opodRelationshipCB5.addItem("M5000");
				opodRelationshipCB6.addItem("M5000");
				opodRelationshipCB7.addItem("M5000");
				opodRelationshipCB8.addItem("M5000");
			}

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		

		opodLogicalName8.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent arg0) {
				opodRelationshipCB1.removeAllItems();
				opodRelationshipCB2.removeAllItems();
				opodRelationshipCB3.removeAllItems();
				opodRelationshipCB4.removeAllItems();
				opodRelationshipCB5.removeAllItems();
				opodRelationshipCB6.removeAllItems();
				opodRelationshipCB7.removeAllItems();
				opodRelationshipCB8.removeAllItems();
				opodRelationshipCB1.addItem(opodLogicalName1.getText());
				opodRelationshipCB2.addItem(opodLogicalName1.getText());
				opodRelationshipCB3.addItem(opodLogicalName1.getText());
				opodRelationshipCB4.addItem(opodLogicalName1.getText());
				opodRelationshipCB5.addItem(opodLogicalName1.getText());
				opodRelationshipCB6.addItem(opodLogicalName1.getText());
				opodRelationshipCB7.addItem(opodLogicalName1.getText());
				opodRelationshipCB8.addItem(opodLogicalName1.getText());
				opodRelationshipCB1.addItem(opodLogicalName2.getText());
				opodRelationshipCB2.addItem(opodLogicalName2.getText());
				opodRelationshipCB3.addItem(opodLogicalName2.getText());
				opodRelationshipCB4.addItem(opodLogicalName2.getText());
				opodRelationshipCB5.addItem(opodLogicalName2.getText());
				opodRelationshipCB6.addItem(opodLogicalName2.getText());
				opodRelationshipCB7.addItem(opodLogicalName2.getText());
				opodRelationshipCB8.addItem(opodLogicalName2.getText());
				opodRelationshipCB1.addItem(opodLogicalName3.getText());
				opodRelationshipCB2.addItem(opodLogicalName3.getText());
				opodRelationshipCB3.addItem(opodLogicalName3.getText());
				opodRelationshipCB4.addItem(opodLogicalName3.getText());
				opodRelationshipCB5.addItem(opodLogicalName3.getText());
				opodRelationshipCB6.addItem(opodLogicalName3.getText());
				opodRelationshipCB7.addItem(opodLogicalName3.getText());
				opodRelationshipCB8.addItem(opodLogicalName3.getText());
				opodRelationshipCB1.addItem(opodLogicalName4.getText());
				opodRelationshipCB2.addItem(opodLogicalName4.getText());
				opodRelationshipCB3.addItem(opodLogicalName4.getText());
				opodRelationshipCB4.addItem(opodLogicalName4.getText());
				opodRelationshipCB5.addItem(opodLogicalName4.getText());
				opodRelationshipCB6.addItem(opodLogicalName4.getText());
				opodRelationshipCB7.addItem(opodLogicalName4.getText());
				opodRelationshipCB8.addItem(opodLogicalName4.getText());
				opodRelationshipCB1.addItem(opodLogicalName5.getText());
				opodRelationshipCB2.addItem(opodLogicalName5.getText());
				opodRelationshipCB3.addItem(opodLogicalName5.getText());
				opodRelationshipCB4.addItem(opodLogicalName5.getText());
				opodRelationshipCB5.addItem(opodLogicalName5.getText());
				opodRelationshipCB6.addItem(opodLogicalName5.getText());
				opodRelationshipCB7.addItem(opodLogicalName5.getText());
				opodRelationshipCB8.addItem(opodLogicalName5.getText());
				opodRelationshipCB1.addItem(opodLogicalName6.getText());
				opodRelationshipCB2.addItem(opodLogicalName6.getText());
				opodRelationshipCB3.addItem(opodLogicalName6.getText());
				opodRelationshipCB4.addItem(opodLogicalName6.getText());
				opodRelationshipCB5.addItem(opodLogicalName6.getText());
				opodRelationshipCB6.addItem(opodLogicalName6.getText());
				opodRelationshipCB7.addItem(opodLogicalName6.getText());
				opodRelationshipCB8.addItem(opodLogicalName6.getText());
				opodRelationshipCB1.addItem(opodLogicalName7.getText());
				opodRelationshipCB2.addItem(opodLogicalName7.getText());
				opodRelationshipCB3.addItem(opodLogicalName7.getText());
				opodRelationshipCB4.addItem(opodLogicalName7.getText());
				opodRelationshipCB5.addItem(opodLogicalName7.getText());
				opodRelationshipCB6.addItem(opodLogicalName7.getText());
				opodRelationshipCB7.addItem(opodLogicalName7.getText());
				opodRelationshipCB8.addItem(opodLogicalName7.getText());
				opodRelationshipCB1.addItem(opodLogicalName8.getText());
				opodRelationshipCB2.addItem(opodLogicalName8.getText());
				opodRelationshipCB3.addItem(opodLogicalName8.getText());
				opodRelationshipCB4.addItem(opodLogicalName8.getText());
				opodRelationshipCB5.addItem(opodLogicalName8.getText());
				opodRelationshipCB6.addItem(opodLogicalName8.getText());
				opodRelationshipCB7.addItem(opodLogicalName8.getText());
				opodRelationshipCB8.addItem(opodLogicalName8.getText());
				opodRelationshipCB1.addItem(opodLogicalName9.getText());
				opodRelationshipCB2.addItem(opodLogicalName9.getText());
				opodRelationshipCB3.addItem(opodLogicalName9.getText());
				opodRelationshipCB4.addItem(opodLogicalName9.getText());
				opodRelationshipCB5.addItem(opodLogicalName9.getText());
				opodRelationshipCB6.addItem(opodLogicalName9.getText());
				opodRelationshipCB7.addItem(opodLogicalName9.getText());
				opodRelationshipCB8.addItem(opodLogicalName9.getText());
				opodRelationshipCB1.addItem(opodLogicalName10.getText());
				opodRelationshipCB2.addItem(opodLogicalName10.getText());
				opodRelationshipCB3.addItem(opodLogicalName10.getText());
				opodRelationshipCB4.addItem(opodLogicalName10.getText());
				opodRelationshipCB5.addItem(opodLogicalName10.getText());
				opodRelationshipCB6.addItem(opodLogicalName10.getText());
				opodRelationshipCB7.addItem(opodLogicalName10.getText());
				opodRelationshipCB8.addItem(opodLogicalName10.getText());
				opodRelationshipCB1.addItem("M5000");
				opodRelationshipCB2.addItem("M5000");
				opodRelationshipCB3.addItem("M5000");
				opodRelationshipCB4.addItem("M5000");
				opodRelationshipCB5.addItem("M5000");
				opodRelationshipCB6.addItem("M5000");
				opodRelationshipCB7.addItem("M5000");
				opodRelationshipCB8.addItem("M5000");
			}

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		

		opodLogicalName9.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent arg0) {
				opodRelationshipCB1.removeAllItems();
				opodRelationshipCB2.removeAllItems();
				opodRelationshipCB3.removeAllItems();
				opodRelationshipCB4.removeAllItems();
				opodRelationshipCB5.removeAllItems();
				opodRelationshipCB6.removeAllItems();
				opodRelationshipCB7.removeAllItems();
				opodRelationshipCB8.removeAllItems();
				opodRelationshipCB1.addItem(opodLogicalName1.getText());
				opodRelationshipCB2.addItem(opodLogicalName1.getText());
				opodRelationshipCB3.addItem(opodLogicalName1.getText());
				opodRelationshipCB4.addItem(opodLogicalName1.getText());
				opodRelationshipCB5.addItem(opodLogicalName1.getText());
				opodRelationshipCB6.addItem(opodLogicalName1.getText());
				opodRelationshipCB7.addItem(opodLogicalName1.getText());
				opodRelationshipCB8.addItem(opodLogicalName1.getText());
				opodRelationshipCB1.addItem(opodLogicalName2.getText());
				opodRelationshipCB2.addItem(opodLogicalName2.getText());
				opodRelationshipCB3.addItem(opodLogicalName2.getText());
				opodRelationshipCB4.addItem(opodLogicalName2.getText());
				opodRelationshipCB5.addItem(opodLogicalName2.getText());
				opodRelationshipCB6.addItem(opodLogicalName2.getText());
				opodRelationshipCB7.addItem(opodLogicalName2.getText());
				opodRelationshipCB8.addItem(opodLogicalName2.getText());
				opodRelationshipCB1.addItem(opodLogicalName3.getText());
				opodRelationshipCB2.addItem(opodLogicalName3.getText());
				opodRelationshipCB3.addItem(opodLogicalName3.getText());
				opodRelationshipCB4.addItem(opodLogicalName3.getText());
				opodRelationshipCB5.addItem(opodLogicalName3.getText());
				opodRelationshipCB6.addItem(opodLogicalName3.getText());
				opodRelationshipCB7.addItem(opodLogicalName3.getText());
				opodRelationshipCB8.addItem(opodLogicalName3.getText());
				opodRelationshipCB1.addItem(opodLogicalName4.getText());
				opodRelationshipCB2.addItem(opodLogicalName4.getText());
				opodRelationshipCB3.addItem(opodLogicalName4.getText());
				opodRelationshipCB4.addItem(opodLogicalName4.getText());
				opodRelationshipCB5.addItem(opodLogicalName4.getText());
				opodRelationshipCB6.addItem(opodLogicalName4.getText());
				opodRelationshipCB7.addItem(opodLogicalName4.getText());
				opodRelationshipCB8.addItem(opodLogicalName4.getText());
				opodRelationshipCB1.addItem(opodLogicalName5.getText());
				opodRelationshipCB2.addItem(opodLogicalName5.getText());
				opodRelationshipCB3.addItem(opodLogicalName5.getText());
				opodRelationshipCB4.addItem(opodLogicalName5.getText());
				opodRelationshipCB5.addItem(opodLogicalName5.getText());
				opodRelationshipCB6.addItem(opodLogicalName5.getText());
				opodRelationshipCB7.addItem(opodLogicalName5.getText());
				opodRelationshipCB8.addItem(opodLogicalName5.getText());
				opodRelationshipCB1.addItem(opodLogicalName6.getText());
				opodRelationshipCB2.addItem(opodLogicalName6.getText());
				opodRelationshipCB3.addItem(opodLogicalName6.getText());
				opodRelationshipCB4.addItem(opodLogicalName6.getText());
				opodRelationshipCB5.addItem(opodLogicalName6.getText());
				opodRelationshipCB6.addItem(opodLogicalName6.getText());
				opodRelationshipCB7.addItem(opodLogicalName6.getText());
				opodRelationshipCB8.addItem(opodLogicalName6.getText());
				opodRelationshipCB1.addItem(opodLogicalName7.getText());
				opodRelationshipCB2.addItem(opodLogicalName7.getText());
				opodRelationshipCB3.addItem(opodLogicalName7.getText());
				opodRelationshipCB4.addItem(opodLogicalName7.getText());
				opodRelationshipCB5.addItem(opodLogicalName7.getText());
				opodRelationshipCB6.addItem(opodLogicalName7.getText());
				opodRelationshipCB7.addItem(opodLogicalName7.getText());
				opodRelationshipCB8.addItem(opodLogicalName7.getText());
				opodRelationshipCB1.addItem(opodLogicalName8.getText());
				opodRelationshipCB2.addItem(opodLogicalName8.getText());
				opodRelationshipCB3.addItem(opodLogicalName8.getText());
				opodRelationshipCB4.addItem(opodLogicalName8.getText());
				opodRelationshipCB5.addItem(opodLogicalName8.getText());
				opodRelationshipCB6.addItem(opodLogicalName8.getText());
				opodRelationshipCB7.addItem(opodLogicalName8.getText());
				opodRelationshipCB8.addItem(opodLogicalName8.getText());
				opodRelationshipCB1.addItem(opodLogicalName9.getText());
				opodRelationshipCB2.addItem(opodLogicalName9.getText());
				opodRelationshipCB3.addItem(opodLogicalName9.getText());
				opodRelationshipCB4.addItem(opodLogicalName9.getText());
				opodRelationshipCB5.addItem(opodLogicalName9.getText());
				opodRelationshipCB6.addItem(opodLogicalName9.getText());
				opodRelationshipCB7.addItem(opodLogicalName9.getText());
				opodRelationshipCB8.addItem(opodLogicalName9.getText());
				opodRelationshipCB1.addItem(opodLogicalName10.getText());
				opodRelationshipCB2.addItem(opodLogicalName10.getText());
				opodRelationshipCB3.addItem(opodLogicalName10.getText());
				opodRelationshipCB4.addItem(opodLogicalName10.getText());
				opodRelationshipCB5.addItem(opodLogicalName10.getText());
				opodRelationshipCB6.addItem(opodLogicalName10.getText());
				opodRelationshipCB7.addItem(opodLogicalName10.getText());
				opodRelationshipCB8.addItem(opodLogicalName10.getText());
				opodRelationshipCB1.addItem("M5000");
				opodRelationshipCB2.addItem("M5000");
				opodRelationshipCB3.addItem("M5000");
				opodRelationshipCB4.addItem("M5000");
				opodRelationshipCB5.addItem("M5000");
				opodRelationshipCB6.addItem("M5000");
				opodRelationshipCB7.addItem("M5000");
				opodRelationshipCB8.addItem("M5000");
			}

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		

		opodLogicalName10.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent arg0) {
				opodRelationshipCB1.removeAllItems();
				opodRelationshipCB2.removeAllItems();
				opodRelationshipCB3.removeAllItems();
				opodRelationshipCB4.removeAllItems();
				opodRelationshipCB5.removeAllItems();
				opodRelationshipCB6.removeAllItems();
				opodRelationshipCB7.removeAllItems();
				opodRelationshipCB8.removeAllItems();
				opodRelationshipCB1.addItem(opodLogicalName1.getText());
				opodRelationshipCB2.addItem(opodLogicalName1.getText());
				opodRelationshipCB3.addItem(opodLogicalName1.getText());
				opodRelationshipCB4.addItem(opodLogicalName1.getText());
				opodRelationshipCB5.addItem(opodLogicalName1.getText());
				opodRelationshipCB6.addItem(opodLogicalName1.getText());
				opodRelationshipCB7.addItem(opodLogicalName1.getText());
				opodRelationshipCB8.addItem(opodLogicalName1.getText());
				opodRelationshipCB1.addItem(opodLogicalName2.getText());
				opodRelationshipCB2.addItem(opodLogicalName2.getText());
				opodRelationshipCB3.addItem(opodLogicalName2.getText());
				opodRelationshipCB4.addItem(opodLogicalName2.getText());
				opodRelationshipCB5.addItem(opodLogicalName2.getText());
				opodRelationshipCB6.addItem(opodLogicalName2.getText());
				opodRelationshipCB7.addItem(opodLogicalName2.getText());
				opodRelationshipCB8.addItem(opodLogicalName2.getText());
				opodRelationshipCB1.addItem(opodLogicalName3.getText());
				opodRelationshipCB2.addItem(opodLogicalName3.getText());
				opodRelationshipCB3.addItem(opodLogicalName3.getText());
				opodRelationshipCB4.addItem(opodLogicalName3.getText());
				opodRelationshipCB5.addItem(opodLogicalName3.getText());
				opodRelationshipCB6.addItem(opodLogicalName3.getText());
				opodRelationshipCB7.addItem(opodLogicalName3.getText());
				opodRelationshipCB8.addItem(opodLogicalName3.getText());
				opodRelationshipCB1.addItem(opodLogicalName4.getText());
				opodRelationshipCB2.addItem(opodLogicalName4.getText());
				opodRelationshipCB3.addItem(opodLogicalName4.getText());
				opodRelationshipCB4.addItem(opodLogicalName4.getText());
				opodRelationshipCB5.addItem(opodLogicalName4.getText());
				opodRelationshipCB6.addItem(opodLogicalName4.getText());
				opodRelationshipCB7.addItem(opodLogicalName4.getText());
				opodRelationshipCB8.addItem(opodLogicalName4.getText());
				opodRelationshipCB1.addItem(opodLogicalName5.getText());
				opodRelationshipCB2.addItem(opodLogicalName5.getText());
				opodRelationshipCB3.addItem(opodLogicalName5.getText());
				opodRelationshipCB4.addItem(opodLogicalName5.getText());
				opodRelationshipCB5.addItem(opodLogicalName5.getText());
				opodRelationshipCB6.addItem(opodLogicalName5.getText());
				opodRelationshipCB7.addItem(opodLogicalName5.getText());
				opodRelationshipCB8.addItem(opodLogicalName5.getText());
				opodRelationshipCB1.addItem(opodLogicalName6.getText());
				opodRelationshipCB2.addItem(opodLogicalName6.getText());
				opodRelationshipCB3.addItem(opodLogicalName6.getText());
				opodRelationshipCB4.addItem(opodLogicalName6.getText());
				opodRelationshipCB5.addItem(opodLogicalName6.getText());
				opodRelationshipCB6.addItem(opodLogicalName6.getText());
				opodRelationshipCB7.addItem(opodLogicalName6.getText());
				opodRelationshipCB8.addItem(opodLogicalName6.getText());
				opodRelationshipCB1.addItem(opodLogicalName7.getText());
				opodRelationshipCB2.addItem(opodLogicalName7.getText());
				opodRelationshipCB3.addItem(opodLogicalName7.getText());
				opodRelationshipCB4.addItem(opodLogicalName7.getText());
				opodRelationshipCB5.addItem(opodLogicalName7.getText());
				opodRelationshipCB6.addItem(opodLogicalName7.getText());
				opodRelationshipCB7.addItem(opodLogicalName7.getText());
				opodRelationshipCB8.addItem(opodLogicalName7.getText());
				opodRelationshipCB1.addItem(opodLogicalName8.getText());
				opodRelationshipCB2.addItem(opodLogicalName8.getText());
				opodRelationshipCB3.addItem(opodLogicalName8.getText());
				opodRelationshipCB4.addItem(opodLogicalName8.getText());
				opodRelationshipCB5.addItem(opodLogicalName8.getText());
				opodRelationshipCB6.addItem(opodLogicalName8.getText());
				opodRelationshipCB7.addItem(opodLogicalName8.getText());
				opodRelationshipCB8.addItem(opodLogicalName8.getText());
				opodRelationshipCB1.addItem(opodLogicalName9.getText());
				opodRelationshipCB2.addItem(opodLogicalName9.getText());
				opodRelationshipCB3.addItem(opodLogicalName9.getText());
				opodRelationshipCB4.addItem(opodLogicalName9.getText());
				opodRelationshipCB5.addItem(opodLogicalName9.getText());
				opodRelationshipCB6.addItem(opodLogicalName9.getText());
				opodRelationshipCB7.addItem(opodLogicalName9.getText());
				opodRelationshipCB8.addItem(opodLogicalName9.getText());
				opodRelationshipCB1.addItem(opodLogicalName10.getText());
				opodRelationshipCB2.addItem(opodLogicalName10.getText());
				opodRelationshipCB3.addItem(opodLogicalName10.getText());
				opodRelationshipCB4.addItem(opodLogicalName10.getText());
				opodRelationshipCB5.addItem(opodLogicalName10.getText());
				opodRelationshipCB6.addItem(opodLogicalName10.getText());
				opodRelationshipCB7.addItem(opodLogicalName10.getText());
				opodRelationshipCB8.addItem(opodLogicalName10.getText());
				opodRelationshipCB1.addItem("M5000");
				opodRelationshipCB2.addItem("M5000");
				opodRelationshipCB3.addItem("M5000");
				opodRelationshipCB4.addItem("M5000");
				opodRelationshipCB5.addItem("M5000");
				opodRelationshipCB6.addItem("M5000");
				opodRelationshipCB7.addItem("M5000");
				opodRelationshipCB8.addItem("M5000");
			}

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				
			}
		});

		opodServerRelationship2.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent arg0) {
				opodPrimaryCB1.removeAllItems();
				opodPrimaryCB2.removeAllItems();
				opodPrimaryCB3.removeAllItems();
				opodPrimaryCB4.removeAllItems();
				opodPrimaryCB5.removeAllItems();
				opodPrimaryCB6.removeAllItems();
				opodPrimaryCB7.removeAllItems();
				opodPrimaryCB8.removeAllItems();
				opodPrimaryCB1.addItem(opodServerRelationship1.getText());
				opodPrimaryCB2.addItem(opodServerRelationship1.getText());
				opodPrimaryCB3.addItem(opodServerRelationship1.getText());
				opodPrimaryCB4.addItem(opodServerRelationship1.getText());
				opodPrimaryCB5.addItem(opodServerRelationship1.getText());
				opodPrimaryCB6.addItem(opodServerRelationship1.getText());
				opodPrimaryCB7.addItem(opodServerRelationship1.getText());
				opodPrimaryCB8.addItem(opodServerRelationship1.getText());
				opodPrimaryCB1.addItem(opodServerRelationship2.getText());
				opodPrimaryCB2.addItem(opodServerRelationship2.getText());
				opodPrimaryCB3.addItem(opodServerRelationship2.getText());
				opodPrimaryCB4.addItem(opodServerRelationship2.getText());
				opodPrimaryCB5.addItem(opodServerRelationship2.getText());
				opodPrimaryCB6.addItem(opodServerRelationship2.getText());
				opodPrimaryCB7.addItem(opodServerRelationship2.getText());
				opodPrimaryCB8.addItem(opodServerRelationship2.getText());
				opodPrimaryCB1.addItem(opodServerRelationship3.getText());
				opodPrimaryCB2.addItem(opodServerRelationship3.getText());
				opodPrimaryCB3.addItem(opodServerRelationship3.getText());
				opodPrimaryCB4.addItem(opodServerRelationship3.getText());
				opodPrimaryCB5.addItem(opodServerRelationship3.getText());
				opodPrimaryCB6.addItem(opodServerRelationship3.getText());
				opodPrimaryCB7.addItem(opodServerRelationship3.getText());
				opodPrimaryCB8.addItem(opodServerRelationship3.getText());
				opodPrimaryCB1.addItem(opodServerRelationship4.getText());
				opodPrimaryCB2.addItem(opodServerRelationship4.getText());
				opodPrimaryCB3.addItem(opodServerRelationship4.getText());
				opodPrimaryCB4.addItem(opodServerRelationship4.getText());
				opodPrimaryCB5.addItem(opodServerRelationship4.getText());
				opodPrimaryCB6.addItem(opodServerRelationship4.getText());
				opodPrimaryCB7.addItem(opodServerRelationship4.getText());
				opodPrimaryCB8.addItem(opodServerRelationship4.getText());
				opodPrimaryCB1.addItem(opodServerRelationship5.getText());
				opodPrimaryCB2.addItem(opodServerRelationship5.getText());
				opodPrimaryCB3.addItem(opodServerRelationship5.getText());
				opodPrimaryCB4.addItem(opodServerRelationship5.getText());
				opodPrimaryCB5.addItem(opodServerRelationship5.getText());
				opodPrimaryCB6.addItem(opodServerRelationship5.getText());
				opodPrimaryCB7.addItem(opodServerRelationship5.getText());
				opodPrimaryCB8.addItem(opodServerRelationship5.getText());
				opodPrimaryCB1.addItem(opodServerRelationship6.getText());
				opodPrimaryCB2.addItem(opodServerRelationship6.getText());
				opodPrimaryCB3.addItem(opodServerRelationship6.getText());
				opodPrimaryCB4.addItem(opodServerRelationship6.getText());
				opodPrimaryCB5.addItem(opodServerRelationship6.getText());
				opodPrimaryCB6.addItem(opodServerRelationship6.getText());
				opodPrimaryCB7.addItem(opodServerRelationship6.getText());
				opodPrimaryCB8.addItem(opodServerRelationship6.getText());
				opodPrimaryCB1.addItem(opodServerRelationship7.getText());
				opodPrimaryCB2.addItem(opodServerRelationship7.getText());
				opodPrimaryCB3.addItem(opodServerRelationship7.getText());
				opodPrimaryCB4.addItem(opodServerRelationship7.getText());
				opodPrimaryCB5.addItem(opodServerRelationship7.getText());
				opodPrimaryCB6.addItem(opodServerRelationship7.getText());
				opodPrimaryCB7.addItem(opodServerRelationship7.getText());
				opodPrimaryCB8.addItem(opodServerRelationship7.getText());
				opodPrimaryCB1.addItem(opodServerRelationship8.getText());
				opodPrimaryCB2.addItem(opodServerRelationship8.getText());
				opodPrimaryCB3.addItem(opodServerRelationship8.getText());
				opodPrimaryCB4.addItem(opodServerRelationship8.getText());
				opodPrimaryCB5.addItem(opodServerRelationship8.getText());
				opodPrimaryCB6.addItem(opodServerRelationship8.getText());
				opodPrimaryCB7.addItem(opodServerRelationship8.getText());
				opodPrimaryCB8.addItem(opodServerRelationship8.getText());
				opodPrimaryCB1.addItem(opodServerRelationship9.getText());
				opodPrimaryCB2.addItem(opodServerRelationship9.getText());
				opodPrimaryCB3.addItem(opodServerRelationship9.getText());
				opodPrimaryCB4.addItem(opodServerRelationship9.getText());
				opodPrimaryCB5.addItem(opodServerRelationship9.getText());
				opodPrimaryCB6.addItem(opodServerRelationship9.getText());
				opodPrimaryCB7.addItem(opodServerRelationship9.getText());
				opodPrimaryCB8.addItem(opodServerRelationship9.getText());
				opodPrimaryCB1.addItem(opodServerRelationship10.getText());
				opodPrimaryCB2.addItem(opodServerRelationship10.getText());
				opodPrimaryCB3.addItem(opodServerRelationship10.getText());
				opodPrimaryCB4.addItem(opodServerRelationship10.getText());
				opodPrimaryCB5.addItem(opodServerRelationship10.getText());
				opodPrimaryCB6.addItem(opodServerRelationship10.getText());
				opodPrimaryCB7.addItem(opodServerRelationship10.getText());
				opodPrimaryCB8.addItem(opodServerRelationship10.getText());
			}

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		opodServerRelationship3.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent arg0) {
				opodPrimaryCB1.removeAllItems();
				opodPrimaryCB2.removeAllItems();
				opodPrimaryCB3.removeAllItems();
				opodPrimaryCB4.removeAllItems();
				opodPrimaryCB5.removeAllItems();
				opodPrimaryCB6.removeAllItems();
				opodPrimaryCB7.removeAllItems();
				opodPrimaryCB8.removeAllItems();
				opodPrimaryCB1.addItem(opodServerRelationship1.getText());
				opodPrimaryCB2.addItem(opodServerRelationship1.getText());
				opodPrimaryCB3.addItem(opodServerRelationship1.getText());
				opodPrimaryCB4.addItem(opodServerRelationship1.getText());
				opodPrimaryCB5.addItem(opodServerRelationship1.getText());
				opodPrimaryCB6.addItem(opodServerRelationship1.getText());
				opodPrimaryCB7.addItem(opodServerRelationship1.getText());
				opodPrimaryCB8.addItem(opodServerRelationship1.getText());
				opodPrimaryCB1.addItem(opodServerRelationship2.getText());
				opodPrimaryCB2.addItem(opodServerRelationship2.getText());
				opodPrimaryCB3.addItem(opodServerRelationship2.getText());
				opodPrimaryCB4.addItem(opodServerRelationship2.getText());
				opodPrimaryCB5.addItem(opodServerRelationship2.getText());
				opodPrimaryCB6.addItem(opodServerRelationship2.getText());
				opodPrimaryCB7.addItem(opodServerRelationship2.getText());
				opodPrimaryCB8.addItem(opodServerRelationship2.getText());
				opodPrimaryCB1.addItem(opodServerRelationship3.getText());
				opodPrimaryCB2.addItem(opodServerRelationship3.getText());
				opodPrimaryCB3.addItem(opodServerRelationship3.getText());
				opodPrimaryCB4.addItem(opodServerRelationship3.getText());
				opodPrimaryCB5.addItem(opodServerRelationship3.getText());
				opodPrimaryCB6.addItem(opodServerRelationship3.getText());
				opodPrimaryCB7.addItem(opodServerRelationship3.getText());
				opodPrimaryCB8.addItem(opodServerRelationship3.getText());
				opodPrimaryCB1.addItem(opodServerRelationship4.getText());
				opodPrimaryCB2.addItem(opodServerRelationship4.getText());
				opodPrimaryCB3.addItem(opodServerRelationship4.getText());
				opodPrimaryCB4.addItem(opodServerRelationship4.getText());
				opodPrimaryCB5.addItem(opodServerRelationship4.getText());
				opodPrimaryCB6.addItem(opodServerRelationship4.getText());
				opodPrimaryCB7.addItem(opodServerRelationship4.getText());
				opodPrimaryCB8.addItem(opodServerRelationship4.getText());
				opodPrimaryCB1.addItem(opodServerRelationship5.getText());
				opodPrimaryCB2.addItem(opodServerRelationship5.getText());
				opodPrimaryCB3.addItem(opodServerRelationship5.getText());
				opodPrimaryCB4.addItem(opodServerRelationship5.getText());
				opodPrimaryCB5.addItem(opodServerRelationship5.getText());
				opodPrimaryCB6.addItem(opodServerRelationship5.getText());
				opodPrimaryCB7.addItem(opodServerRelationship5.getText());
				opodPrimaryCB8.addItem(opodServerRelationship5.getText());
				opodPrimaryCB1.addItem(opodServerRelationship6.getText());
				opodPrimaryCB2.addItem(opodServerRelationship6.getText());
				opodPrimaryCB3.addItem(opodServerRelationship6.getText());
				opodPrimaryCB4.addItem(opodServerRelationship6.getText());
				opodPrimaryCB5.addItem(opodServerRelationship6.getText());
				opodPrimaryCB6.addItem(opodServerRelationship6.getText());
				opodPrimaryCB7.addItem(opodServerRelationship6.getText());
				opodPrimaryCB8.addItem(opodServerRelationship6.getText());
				opodPrimaryCB1.addItem(opodServerRelationship7.getText());
				opodPrimaryCB2.addItem(opodServerRelationship7.getText());
				opodPrimaryCB3.addItem(opodServerRelationship7.getText());
				opodPrimaryCB4.addItem(opodServerRelationship7.getText());
				opodPrimaryCB5.addItem(opodServerRelationship7.getText());
				opodPrimaryCB6.addItem(opodServerRelationship7.getText());
				opodPrimaryCB7.addItem(opodServerRelationship7.getText());
				opodPrimaryCB8.addItem(opodServerRelationship7.getText());
				opodPrimaryCB1.addItem(opodServerRelationship8.getText());
				opodPrimaryCB2.addItem(opodServerRelationship8.getText());
				opodPrimaryCB3.addItem(opodServerRelationship8.getText());
				opodPrimaryCB4.addItem(opodServerRelationship8.getText());
				opodPrimaryCB5.addItem(opodServerRelationship8.getText());
				opodPrimaryCB6.addItem(opodServerRelationship8.getText());
				opodPrimaryCB7.addItem(opodServerRelationship8.getText());
				opodPrimaryCB8.addItem(opodServerRelationship8.getText());
				opodPrimaryCB1.addItem(opodServerRelationship9.getText());
				opodPrimaryCB2.addItem(opodServerRelationship9.getText());
				opodPrimaryCB3.addItem(opodServerRelationship9.getText());
				opodPrimaryCB4.addItem(opodServerRelationship9.getText());
				opodPrimaryCB5.addItem(opodServerRelationship9.getText());
				opodPrimaryCB6.addItem(opodServerRelationship9.getText());
				opodPrimaryCB7.addItem(opodServerRelationship9.getText());
				opodPrimaryCB8.addItem(opodServerRelationship9.getText());
				opodPrimaryCB1.addItem(opodServerRelationship10.getText());
				opodPrimaryCB2.addItem(opodServerRelationship10.getText());
				opodPrimaryCB3.addItem(opodServerRelationship10.getText());
				opodPrimaryCB4.addItem(opodServerRelationship10.getText());
				opodPrimaryCB5.addItem(opodServerRelationship10.getText());
				opodPrimaryCB6.addItem(opodServerRelationship10.getText());
				opodPrimaryCB7.addItem(opodServerRelationship10.getText());
				opodPrimaryCB8.addItem(opodServerRelationship10.getText());
			}

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		opodServerRelationship4.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent arg0) {
				opodPrimaryCB1.removeAllItems();
				opodPrimaryCB2.removeAllItems();
				opodPrimaryCB3.removeAllItems();
				opodPrimaryCB4.removeAllItems();
				opodPrimaryCB5.removeAllItems();
				opodPrimaryCB6.removeAllItems();
				opodPrimaryCB7.removeAllItems();
				opodPrimaryCB8.removeAllItems();
				opodPrimaryCB1.addItem(opodServerRelationship1.getText());
				opodPrimaryCB2.addItem(opodServerRelationship1.getText());
				opodPrimaryCB3.addItem(opodServerRelationship1.getText());
				opodPrimaryCB4.addItem(opodServerRelationship1.getText());
				opodPrimaryCB5.addItem(opodServerRelationship1.getText());
				opodPrimaryCB6.addItem(opodServerRelationship1.getText());
				opodPrimaryCB7.addItem(opodServerRelationship1.getText());
				opodPrimaryCB8.addItem(opodServerRelationship1.getText());
				opodPrimaryCB1.addItem(opodServerRelationship2.getText());
				opodPrimaryCB2.addItem(opodServerRelationship2.getText());
				opodPrimaryCB3.addItem(opodServerRelationship2.getText());
				opodPrimaryCB4.addItem(opodServerRelationship2.getText());
				opodPrimaryCB5.addItem(opodServerRelationship2.getText());
				opodPrimaryCB6.addItem(opodServerRelationship2.getText());
				opodPrimaryCB7.addItem(opodServerRelationship2.getText());
				opodPrimaryCB8.addItem(opodServerRelationship2.getText());
				opodPrimaryCB1.addItem(opodServerRelationship3.getText());
				opodPrimaryCB2.addItem(opodServerRelationship3.getText());
				opodPrimaryCB3.addItem(opodServerRelationship3.getText());
				opodPrimaryCB4.addItem(opodServerRelationship3.getText());
				opodPrimaryCB5.addItem(opodServerRelationship3.getText());
				opodPrimaryCB6.addItem(opodServerRelationship3.getText());
				opodPrimaryCB7.addItem(opodServerRelationship3.getText());
				opodPrimaryCB8.addItem(opodServerRelationship3.getText());
				opodPrimaryCB1.addItem(opodServerRelationship4.getText());
				opodPrimaryCB2.addItem(opodServerRelationship4.getText());
				opodPrimaryCB3.addItem(opodServerRelationship4.getText());
				opodPrimaryCB4.addItem(opodServerRelationship4.getText());
				opodPrimaryCB5.addItem(opodServerRelationship4.getText());
				opodPrimaryCB6.addItem(opodServerRelationship4.getText());
				opodPrimaryCB7.addItem(opodServerRelationship4.getText());
				opodPrimaryCB8.addItem(opodServerRelationship4.getText());
				opodPrimaryCB1.addItem(opodServerRelationship5.getText());
				opodPrimaryCB2.addItem(opodServerRelationship5.getText());
				opodPrimaryCB3.addItem(opodServerRelationship5.getText());
				opodPrimaryCB4.addItem(opodServerRelationship5.getText());
				opodPrimaryCB5.addItem(opodServerRelationship5.getText());
				opodPrimaryCB6.addItem(opodServerRelationship5.getText());
				opodPrimaryCB7.addItem(opodServerRelationship5.getText());
				opodPrimaryCB8.addItem(opodServerRelationship5.getText());
				opodPrimaryCB1.addItem(opodServerRelationship6.getText());
				opodPrimaryCB2.addItem(opodServerRelationship6.getText());
				opodPrimaryCB3.addItem(opodServerRelationship6.getText());
				opodPrimaryCB4.addItem(opodServerRelationship6.getText());
				opodPrimaryCB5.addItem(opodServerRelationship6.getText());
				opodPrimaryCB6.addItem(opodServerRelationship6.getText());
				opodPrimaryCB7.addItem(opodServerRelationship6.getText());
				opodPrimaryCB8.addItem(opodServerRelationship6.getText());
				opodPrimaryCB1.addItem(opodServerRelationship7.getText());
				opodPrimaryCB2.addItem(opodServerRelationship7.getText());
				opodPrimaryCB3.addItem(opodServerRelationship7.getText());
				opodPrimaryCB4.addItem(opodServerRelationship7.getText());
				opodPrimaryCB5.addItem(opodServerRelationship7.getText());
				opodPrimaryCB6.addItem(opodServerRelationship7.getText());
				opodPrimaryCB7.addItem(opodServerRelationship7.getText());
				opodPrimaryCB8.addItem(opodServerRelationship7.getText());
				opodPrimaryCB1.addItem(opodServerRelationship8.getText());
				opodPrimaryCB2.addItem(opodServerRelationship8.getText());
				opodPrimaryCB3.addItem(opodServerRelationship8.getText());
				opodPrimaryCB4.addItem(opodServerRelationship8.getText());
				opodPrimaryCB5.addItem(opodServerRelationship8.getText());
				opodPrimaryCB6.addItem(opodServerRelationship8.getText());
				opodPrimaryCB7.addItem(opodServerRelationship8.getText());
				opodPrimaryCB8.addItem(opodServerRelationship8.getText());
				opodPrimaryCB1.addItem(opodServerRelationship9.getText());
				opodPrimaryCB2.addItem(opodServerRelationship9.getText());
				opodPrimaryCB3.addItem(opodServerRelationship9.getText());
				opodPrimaryCB4.addItem(opodServerRelationship9.getText());
				opodPrimaryCB5.addItem(opodServerRelationship9.getText());
				opodPrimaryCB6.addItem(opodServerRelationship9.getText());
				opodPrimaryCB7.addItem(opodServerRelationship9.getText());
				opodPrimaryCB8.addItem(opodServerRelationship9.getText());
				opodPrimaryCB1.addItem(opodServerRelationship10.getText());
				opodPrimaryCB2.addItem(opodServerRelationship10.getText());
				opodPrimaryCB3.addItem(opodServerRelationship10.getText());
				opodPrimaryCB4.addItem(opodServerRelationship10.getText());
				opodPrimaryCB5.addItem(opodServerRelationship10.getText());
				opodPrimaryCB6.addItem(opodServerRelationship10.getText());
				opodPrimaryCB7.addItem(opodServerRelationship10.getText());
				opodPrimaryCB8.addItem(opodServerRelationship10.getText());
			}

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		opodServerRelationship5.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent arg0) {
				opodPrimaryCB1.removeAllItems();
				opodPrimaryCB2.removeAllItems();
				opodPrimaryCB3.removeAllItems();
				opodPrimaryCB4.removeAllItems();
				opodPrimaryCB5.removeAllItems();
				opodPrimaryCB6.removeAllItems();
				opodPrimaryCB7.removeAllItems();
				opodPrimaryCB8.removeAllItems();
				opodPrimaryCB1.addItem(opodServerRelationship1.getText());
				opodPrimaryCB2.addItem(opodServerRelationship1.getText());
				opodPrimaryCB3.addItem(opodServerRelationship1.getText());
				opodPrimaryCB4.addItem(opodServerRelationship1.getText());
				opodPrimaryCB5.addItem(opodServerRelationship1.getText());
				opodPrimaryCB6.addItem(opodServerRelationship1.getText());
				opodPrimaryCB7.addItem(opodServerRelationship1.getText());
				opodPrimaryCB8.addItem(opodServerRelationship1.getText());
				opodPrimaryCB1.addItem(opodServerRelationship2.getText());
				opodPrimaryCB2.addItem(opodServerRelationship2.getText());
				opodPrimaryCB3.addItem(opodServerRelationship2.getText());
				opodPrimaryCB4.addItem(opodServerRelationship2.getText());
				opodPrimaryCB5.addItem(opodServerRelationship2.getText());
				opodPrimaryCB6.addItem(opodServerRelationship2.getText());
				opodPrimaryCB7.addItem(opodServerRelationship2.getText());
				opodPrimaryCB8.addItem(opodServerRelationship2.getText());
				opodPrimaryCB1.addItem(opodServerRelationship3.getText());
				opodPrimaryCB2.addItem(opodServerRelationship3.getText());
				opodPrimaryCB3.addItem(opodServerRelationship3.getText());
				opodPrimaryCB4.addItem(opodServerRelationship3.getText());
				opodPrimaryCB5.addItem(opodServerRelationship3.getText());
				opodPrimaryCB6.addItem(opodServerRelationship3.getText());
				opodPrimaryCB7.addItem(opodServerRelationship3.getText());
				opodPrimaryCB8.addItem(opodServerRelationship3.getText());
				opodPrimaryCB1.addItem(opodServerRelationship4.getText());
				opodPrimaryCB2.addItem(opodServerRelationship4.getText());
				opodPrimaryCB3.addItem(opodServerRelationship4.getText());
				opodPrimaryCB4.addItem(opodServerRelationship4.getText());
				opodPrimaryCB5.addItem(opodServerRelationship4.getText());
				opodPrimaryCB6.addItem(opodServerRelationship4.getText());
				opodPrimaryCB7.addItem(opodServerRelationship4.getText());
				opodPrimaryCB8.addItem(opodServerRelationship4.getText());
				opodPrimaryCB1.addItem(opodServerRelationship5.getText());
				opodPrimaryCB2.addItem(opodServerRelationship5.getText());
				opodPrimaryCB3.addItem(opodServerRelationship5.getText());
				opodPrimaryCB4.addItem(opodServerRelationship5.getText());
				opodPrimaryCB5.addItem(opodServerRelationship5.getText());
				opodPrimaryCB6.addItem(opodServerRelationship5.getText());
				opodPrimaryCB7.addItem(opodServerRelationship5.getText());
				opodPrimaryCB8.addItem(opodServerRelationship5.getText());
				opodPrimaryCB1.addItem(opodServerRelationship6.getText());
				opodPrimaryCB2.addItem(opodServerRelationship6.getText());
				opodPrimaryCB3.addItem(opodServerRelationship6.getText());
				opodPrimaryCB4.addItem(opodServerRelationship6.getText());
				opodPrimaryCB5.addItem(opodServerRelationship6.getText());
				opodPrimaryCB6.addItem(opodServerRelationship6.getText());
				opodPrimaryCB7.addItem(opodServerRelationship6.getText());
				opodPrimaryCB8.addItem(opodServerRelationship6.getText());
				opodPrimaryCB1.addItem(opodServerRelationship7.getText());
				opodPrimaryCB2.addItem(opodServerRelationship7.getText());
				opodPrimaryCB3.addItem(opodServerRelationship7.getText());
				opodPrimaryCB4.addItem(opodServerRelationship7.getText());
				opodPrimaryCB5.addItem(opodServerRelationship7.getText());
				opodPrimaryCB6.addItem(opodServerRelationship7.getText());
				opodPrimaryCB7.addItem(opodServerRelationship7.getText());
				opodPrimaryCB8.addItem(opodServerRelationship7.getText());
				opodPrimaryCB1.addItem(opodServerRelationship8.getText());
				opodPrimaryCB2.addItem(opodServerRelationship8.getText());
				opodPrimaryCB3.addItem(opodServerRelationship8.getText());
				opodPrimaryCB4.addItem(opodServerRelationship8.getText());
				opodPrimaryCB5.addItem(opodServerRelationship8.getText());
				opodPrimaryCB6.addItem(opodServerRelationship8.getText());
				opodPrimaryCB7.addItem(opodServerRelationship8.getText());
				opodPrimaryCB8.addItem(opodServerRelationship8.getText());
				opodPrimaryCB1.addItem(opodServerRelationship9.getText());
				opodPrimaryCB2.addItem(opodServerRelationship9.getText());
				opodPrimaryCB3.addItem(opodServerRelationship9.getText());
				opodPrimaryCB4.addItem(opodServerRelationship9.getText());
				opodPrimaryCB5.addItem(opodServerRelationship9.getText());
				opodPrimaryCB6.addItem(opodServerRelationship9.getText());
				opodPrimaryCB7.addItem(opodServerRelationship9.getText());
				opodPrimaryCB8.addItem(opodServerRelationship9.getText());
				opodPrimaryCB1.addItem(opodServerRelationship10.getText());
				opodPrimaryCB2.addItem(opodServerRelationship10.getText());
				opodPrimaryCB3.addItem(opodServerRelationship10.getText());
				opodPrimaryCB4.addItem(opodServerRelationship10.getText());
				opodPrimaryCB5.addItem(opodServerRelationship10.getText());
				opodPrimaryCB6.addItem(opodServerRelationship10.getText());
				opodPrimaryCB7.addItem(opodServerRelationship10.getText());
				opodPrimaryCB8.addItem(opodServerRelationship10.getText());
			}

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		opodServerRelationship6.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent arg0) {
				opodPrimaryCB1.removeAllItems();
				opodPrimaryCB2.removeAllItems();
				opodPrimaryCB3.removeAllItems();
				opodPrimaryCB4.removeAllItems();
				opodPrimaryCB5.removeAllItems();
				opodPrimaryCB6.removeAllItems();
				opodPrimaryCB7.removeAllItems();
				opodPrimaryCB8.removeAllItems();
				opodPrimaryCB1.addItem(opodServerRelationship1.getText());
				opodPrimaryCB2.addItem(opodServerRelationship1.getText());
				opodPrimaryCB3.addItem(opodServerRelationship1.getText());
				opodPrimaryCB4.addItem(opodServerRelationship1.getText());
				opodPrimaryCB5.addItem(opodServerRelationship1.getText());
				opodPrimaryCB6.addItem(opodServerRelationship1.getText());
				opodPrimaryCB7.addItem(opodServerRelationship1.getText());
				opodPrimaryCB8.addItem(opodServerRelationship1.getText());
				opodPrimaryCB1.addItem(opodServerRelationship2.getText());
				opodPrimaryCB2.addItem(opodServerRelationship2.getText());
				opodPrimaryCB3.addItem(opodServerRelationship2.getText());
				opodPrimaryCB4.addItem(opodServerRelationship2.getText());
				opodPrimaryCB5.addItem(opodServerRelationship2.getText());
				opodPrimaryCB6.addItem(opodServerRelationship2.getText());
				opodPrimaryCB7.addItem(opodServerRelationship2.getText());
				opodPrimaryCB8.addItem(opodServerRelationship2.getText());
				opodPrimaryCB1.addItem(opodServerRelationship3.getText());
				opodPrimaryCB2.addItem(opodServerRelationship3.getText());
				opodPrimaryCB3.addItem(opodServerRelationship3.getText());
				opodPrimaryCB4.addItem(opodServerRelationship3.getText());
				opodPrimaryCB5.addItem(opodServerRelationship3.getText());
				opodPrimaryCB6.addItem(opodServerRelationship3.getText());
				opodPrimaryCB7.addItem(opodServerRelationship3.getText());
				opodPrimaryCB8.addItem(opodServerRelationship3.getText());
				opodPrimaryCB1.addItem(opodServerRelationship4.getText());
				opodPrimaryCB2.addItem(opodServerRelationship4.getText());
				opodPrimaryCB3.addItem(opodServerRelationship4.getText());
				opodPrimaryCB4.addItem(opodServerRelationship4.getText());
				opodPrimaryCB5.addItem(opodServerRelationship4.getText());
				opodPrimaryCB6.addItem(opodServerRelationship4.getText());
				opodPrimaryCB7.addItem(opodServerRelationship4.getText());
				opodPrimaryCB8.addItem(opodServerRelationship4.getText());
				opodPrimaryCB1.addItem(opodServerRelationship5.getText());
				opodPrimaryCB2.addItem(opodServerRelationship5.getText());
				opodPrimaryCB3.addItem(opodServerRelationship5.getText());
				opodPrimaryCB4.addItem(opodServerRelationship5.getText());
				opodPrimaryCB5.addItem(opodServerRelationship5.getText());
				opodPrimaryCB6.addItem(opodServerRelationship5.getText());
				opodPrimaryCB7.addItem(opodServerRelationship5.getText());
				opodPrimaryCB8.addItem(opodServerRelationship5.getText());
				opodPrimaryCB1.addItem(opodServerRelationship6.getText());
				opodPrimaryCB2.addItem(opodServerRelationship6.getText());
				opodPrimaryCB3.addItem(opodServerRelationship6.getText());
				opodPrimaryCB4.addItem(opodServerRelationship6.getText());
				opodPrimaryCB5.addItem(opodServerRelationship6.getText());
				opodPrimaryCB6.addItem(opodServerRelationship6.getText());
				opodPrimaryCB7.addItem(opodServerRelationship6.getText());
				opodPrimaryCB8.addItem(opodServerRelationship6.getText());
				opodPrimaryCB1.addItem(opodServerRelationship7.getText());
				opodPrimaryCB2.addItem(opodServerRelationship7.getText());
				opodPrimaryCB3.addItem(opodServerRelationship7.getText());
				opodPrimaryCB4.addItem(opodServerRelationship7.getText());
				opodPrimaryCB5.addItem(opodServerRelationship7.getText());
				opodPrimaryCB6.addItem(opodServerRelationship7.getText());
				opodPrimaryCB7.addItem(opodServerRelationship7.getText());
				opodPrimaryCB8.addItem(opodServerRelationship7.getText());
				opodPrimaryCB1.addItem(opodServerRelationship8.getText());
				opodPrimaryCB2.addItem(opodServerRelationship8.getText());
				opodPrimaryCB3.addItem(opodServerRelationship8.getText());
				opodPrimaryCB4.addItem(opodServerRelationship8.getText());
				opodPrimaryCB5.addItem(opodServerRelationship8.getText());
				opodPrimaryCB6.addItem(opodServerRelationship8.getText());
				opodPrimaryCB7.addItem(opodServerRelationship8.getText());
				opodPrimaryCB8.addItem(opodServerRelationship8.getText());
				opodPrimaryCB1.addItem(opodServerRelationship9.getText());
				opodPrimaryCB2.addItem(opodServerRelationship9.getText());
				opodPrimaryCB3.addItem(opodServerRelationship9.getText());
				opodPrimaryCB4.addItem(opodServerRelationship9.getText());
				opodPrimaryCB5.addItem(opodServerRelationship9.getText());
				opodPrimaryCB6.addItem(opodServerRelationship9.getText());
				opodPrimaryCB7.addItem(opodServerRelationship9.getText());
				opodPrimaryCB8.addItem(opodServerRelationship9.getText());
				opodPrimaryCB1.addItem(opodServerRelationship10.getText());
				opodPrimaryCB2.addItem(opodServerRelationship10.getText());
				opodPrimaryCB3.addItem(opodServerRelationship10.getText());
				opodPrimaryCB4.addItem(opodServerRelationship10.getText());
				opodPrimaryCB5.addItem(opodServerRelationship10.getText());
				opodPrimaryCB6.addItem(opodServerRelationship10.getText());
				opodPrimaryCB7.addItem(opodServerRelationship10.getText());
				opodPrimaryCB8.addItem(opodServerRelationship10.getText());
			}

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		opodServerRelationship7.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent arg0) {
				opodPrimaryCB1.removeAllItems();
				opodPrimaryCB2.removeAllItems();
				opodPrimaryCB3.removeAllItems();
				opodPrimaryCB4.removeAllItems();
				opodPrimaryCB5.removeAllItems();
				opodPrimaryCB6.removeAllItems();
				opodPrimaryCB7.removeAllItems();
				opodPrimaryCB8.removeAllItems();
				opodPrimaryCB1.addItem(opodServerRelationship1.getText());
				opodPrimaryCB2.addItem(opodServerRelationship1.getText());
				opodPrimaryCB3.addItem(opodServerRelationship1.getText());
				opodPrimaryCB4.addItem(opodServerRelationship1.getText());
				opodPrimaryCB5.addItem(opodServerRelationship1.getText());
				opodPrimaryCB6.addItem(opodServerRelationship1.getText());
				opodPrimaryCB7.addItem(opodServerRelationship1.getText());
				opodPrimaryCB8.addItem(opodServerRelationship1.getText());
				opodPrimaryCB1.addItem(opodServerRelationship2.getText());
				opodPrimaryCB2.addItem(opodServerRelationship2.getText());
				opodPrimaryCB3.addItem(opodServerRelationship2.getText());
				opodPrimaryCB4.addItem(opodServerRelationship2.getText());
				opodPrimaryCB5.addItem(opodServerRelationship2.getText());
				opodPrimaryCB6.addItem(opodServerRelationship2.getText());
				opodPrimaryCB7.addItem(opodServerRelationship2.getText());
				opodPrimaryCB8.addItem(opodServerRelationship2.getText());
				opodPrimaryCB1.addItem(opodServerRelationship3.getText());
				opodPrimaryCB2.addItem(opodServerRelationship3.getText());
				opodPrimaryCB3.addItem(opodServerRelationship3.getText());
				opodPrimaryCB4.addItem(opodServerRelationship3.getText());
				opodPrimaryCB5.addItem(opodServerRelationship3.getText());
				opodPrimaryCB6.addItem(opodServerRelationship3.getText());
				opodPrimaryCB7.addItem(opodServerRelationship3.getText());
				opodPrimaryCB8.addItem(opodServerRelationship3.getText());
				opodPrimaryCB1.addItem(opodServerRelationship4.getText());
				opodPrimaryCB2.addItem(opodServerRelationship4.getText());
				opodPrimaryCB3.addItem(opodServerRelationship4.getText());
				opodPrimaryCB4.addItem(opodServerRelationship4.getText());
				opodPrimaryCB5.addItem(opodServerRelationship4.getText());
				opodPrimaryCB6.addItem(opodServerRelationship4.getText());
				opodPrimaryCB7.addItem(opodServerRelationship4.getText());
				opodPrimaryCB8.addItem(opodServerRelationship4.getText());
				opodPrimaryCB1.addItem(opodServerRelationship5.getText());
				opodPrimaryCB2.addItem(opodServerRelationship5.getText());
				opodPrimaryCB3.addItem(opodServerRelationship5.getText());
				opodPrimaryCB4.addItem(opodServerRelationship5.getText());
				opodPrimaryCB5.addItem(opodServerRelationship5.getText());
				opodPrimaryCB6.addItem(opodServerRelationship5.getText());
				opodPrimaryCB7.addItem(opodServerRelationship5.getText());
				opodPrimaryCB8.addItem(opodServerRelationship5.getText());
				opodPrimaryCB1.addItem(opodServerRelationship6.getText());
				opodPrimaryCB2.addItem(opodServerRelationship6.getText());
				opodPrimaryCB3.addItem(opodServerRelationship6.getText());
				opodPrimaryCB4.addItem(opodServerRelationship6.getText());
				opodPrimaryCB5.addItem(opodServerRelationship6.getText());
				opodPrimaryCB6.addItem(opodServerRelationship6.getText());
				opodPrimaryCB7.addItem(opodServerRelationship6.getText());
				opodPrimaryCB8.addItem(opodServerRelationship6.getText());
				opodPrimaryCB1.addItem(opodServerRelationship7.getText());
				opodPrimaryCB2.addItem(opodServerRelationship7.getText());
				opodPrimaryCB3.addItem(opodServerRelationship7.getText());
				opodPrimaryCB4.addItem(opodServerRelationship7.getText());
				opodPrimaryCB5.addItem(opodServerRelationship7.getText());
				opodPrimaryCB6.addItem(opodServerRelationship7.getText());
				opodPrimaryCB7.addItem(opodServerRelationship7.getText());
				opodPrimaryCB8.addItem(opodServerRelationship7.getText());
				opodPrimaryCB1.addItem(opodServerRelationship8.getText());
				opodPrimaryCB2.addItem(opodServerRelationship8.getText());
				opodPrimaryCB3.addItem(opodServerRelationship8.getText());
				opodPrimaryCB4.addItem(opodServerRelationship8.getText());
				opodPrimaryCB5.addItem(opodServerRelationship8.getText());
				opodPrimaryCB6.addItem(opodServerRelationship8.getText());
				opodPrimaryCB7.addItem(opodServerRelationship8.getText());
				opodPrimaryCB8.addItem(opodServerRelationship8.getText());
				opodPrimaryCB1.addItem(opodServerRelationship9.getText());
				opodPrimaryCB2.addItem(opodServerRelationship9.getText());
				opodPrimaryCB3.addItem(opodServerRelationship9.getText());
				opodPrimaryCB4.addItem(opodServerRelationship9.getText());
				opodPrimaryCB5.addItem(opodServerRelationship9.getText());
				opodPrimaryCB6.addItem(opodServerRelationship9.getText());
				opodPrimaryCB7.addItem(opodServerRelationship9.getText());
				opodPrimaryCB8.addItem(opodServerRelationship9.getText());
				opodPrimaryCB1.addItem(opodServerRelationship10.getText());
				opodPrimaryCB2.addItem(opodServerRelationship10.getText());
				opodPrimaryCB3.addItem(opodServerRelationship10.getText());
				opodPrimaryCB4.addItem(opodServerRelationship10.getText());
				opodPrimaryCB5.addItem(opodServerRelationship10.getText());
				opodPrimaryCB6.addItem(opodServerRelationship10.getText());
				opodPrimaryCB7.addItem(opodServerRelationship10.getText());
				opodPrimaryCB8.addItem(opodServerRelationship10.getText());
			}

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		opodServerRelationship8.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent arg0) {
				opodPrimaryCB1.removeAllItems();
				opodPrimaryCB2.removeAllItems();
				opodPrimaryCB3.removeAllItems();
				opodPrimaryCB4.removeAllItems();
				opodPrimaryCB5.removeAllItems();
				opodPrimaryCB6.removeAllItems();
				opodPrimaryCB7.removeAllItems();
				opodPrimaryCB8.removeAllItems();
				opodPrimaryCB1.addItem(opodServerRelationship1.getText());
				opodPrimaryCB2.addItem(opodServerRelationship1.getText());
				opodPrimaryCB3.addItem(opodServerRelationship1.getText());
				opodPrimaryCB4.addItem(opodServerRelationship1.getText());
				opodPrimaryCB5.addItem(opodServerRelationship1.getText());
				opodPrimaryCB6.addItem(opodServerRelationship1.getText());
				opodPrimaryCB7.addItem(opodServerRelationship1.getText());
				opodPrimaryCB8.addItem(opodServerRelationship1.getText());
				opodPrimaryCB1.addItem(opodServerRelationship2.getText());
				opodPrimaryCB2.addItem(opodServerRelationship2.getText());
				opodPrimaryCB3.addItem(opodServerRelationship2.getText());
				opodPrimaryCB4.addItem(opodServerRelationship2.getText());
				opodPrimaryCB5.addItem(opodServerRelationship2.getText());
				opodPrimaryCB6.addItem(opodServerRelationship2.getText());
				opodPrimaryCB7.addItem(opodServerRelationship2.getText());
				opodPrimaryCB8.addItem(opodServerRelationship2.getText());
				opodPrimaryCB1.addItem(opodServerRelationship3.getText());
				opodPrimaryCB2.addItem(opodServerRelationship3.getText());
				opodPrimaryCB3.addItem(opodServerRelationship3.getText());
				opodPrimaryCB4.addItem(opodServerRelationship3.getText());
				opodPrimaryCB5.addItem(opodServerRelationship3.getText());
				opodPrimaryCB6.addItem(opodServerRelationship3.getText());
				opodPrimaryCB7.addItem(opodServerRelationship3.getText());
				opodPrimaryCB8.addItem(opodServerRelationship3.getText());
				opodPrimaryCB1.addItem(opodServerRelationship4.getText());
				opodPrimaryCB2.addItem(opodServerRelationship4.getText());
				opodPrimaryCB3.addItem(opodServerRelationship4.getText());
				opodPrimaryCB4.addItem(opodServerRelationship4.getText());
				opodPrimaryCB5.addItem(opodServerRelationship4.getText());
				opodPrimaryCB6.addItem(opodServerRelationship4.getText());
				opodPrimaryCB7.addItem(opodServerRelationship4.getText());
				opodPrimaryCB8.addItem(opodServerRelationship4.getText());
				opodPrimaryCB1.addItem(opodServerRelationship5.getText());
				opodPrimaryCB2.addItem(opodServerRelationship5.getText());
				opodPrimaryCB3.addItem(opodServerRelationship5.getText());
				opodPrimaryCB4.addItem(opodServerRelationship5.getText());
				opodPrimaryCB5.addItem(opodServerRelationship5.getText());
				opodPrimaryCB6.addItem(opodServerRelationship5.getText());
				opodPrimaryCB7.addItem(opodServerRelationship5.getText());
				opodPrimaryCB8.addItem(opodServerRelationship5.getText());
				opodPrimaryCB1.addItem(opodServerRelationship6.getText());
				opodPrimaryCB2.addItem(opodServerRelationship6.getText());
				opodPrimaryCB3.addItem(opodServerRelationship6.getText());
				opodPrimaryCB4.addItem(opodServerRelationship6.getText());
				opodPrimaryCB5.addItem(opodServerRelationship6.getText());
				opodPrimaryCB6.addItem(opodServerRelationship6.getText());
				opodPrimaryCB7.addItem(opodServerRelationship6.getText());
				opodPrimaryCB8.addItem(opodServerRelationship6.getText());
				opodPrimaryCB1.addItem(opodServerRelationship7.getText());
				opodPrimaryCB2.addItem(opodServerRelationship7.getText());
				opodPrimaryCB3.addItem(opodServerRelationship7.getText());
				opodPrimaryCB4.addItem(opodServerRelationship7.getText());
				opodPrimaryCB5.addItem(opodServerRelationship7.getText());
				opodPrimaryCB6.addItem(opodServerRelationship7.getText());
				opodPrimaryCB7.addItem(opodServerRelationship7.getText());
				opodPrimaryCB8.addItem(opodServerRelationship7.getText());
				opodPrimaryCB1.addItem(opodServerRelationship8.getText());
				opodPrimaryCB2.addItem(opodServerRelationship8.getText());
				opodPrimaryCB3.addItem(opodServerRelationship8.getText());
				opodPrimaryCB4.addItem(opodServerRelationship8.getText());
				opodPrimaryCB5.addItem(opodServerRelationship8.getText());
				opodPrimaryCB6.addItem(opodServerRelationship8.getText());
				opodPrimaryCB7.addItem(opodServerRelationship8.getText());
				opodPrimaryCB8.addItem(opodServerRelationship8.getText());
				opodPrimaryCB1.addItem(opodServerRelationship9.getText());
				opodPrimaryCB2.addItem(opodServerRelationship9.getText());
				opodPrimaryCB3.addItem(opodServerRelationship9.getText());
				opodPrimaryCB4.addItem(opodServerRelationship9.getText());
				opodPrimaryCB5.addItem(opodServerRelationship9.getText());
				opodPrimaryCB6.addItem(opodServerRelationship9.getText());
				opodPrimaryCB7.addItem(opodServerRelationship9.getText());
				opodPrimaryCB8.addItem(opodServerRelationship9.getText());
				opodPrimaryCB1.addItem(opodServerRelationship10.getText());
				opodPrimaryCB2.addItem(opodServerRelationship10.getText());
				opodPrimaryCB3.addItem(opodServerRelationship10.getText());
				opodPrimaryCB4.addItem(opodServerRelationship10.getText());
				opodPrimaryCB5.addItem(opodServerRelationship10.getText());
				opodPrimaryCB6.addItem(opodServerRelationship10.getText());
				opodPrimaryCB7.addItem(opodServerRelationship10.getText());
				opodPrimaryCB8.addItem(opodServerRelationship10.getText());
			}

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		opodServerRelationship9.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent arg0) {
				opodPrimaryCB1.removeAllItems();
				opodPrimaryCB2.removeAllItems();
				opodPrimaryCB3.removeAllItems();
				opodPrimaryCB4.removeAllItems();
				opodPrimaryCB5.removeAllItems();
				opodPrimaryCB6.removeAllItems();
				opodPrimaryCB7.removeAllItems();
				opodPrimaryCB8.removeAllItems();
				opodPrimaryCB1.addItem(opodServerRelationship1.getText());
				opodPrimaryCB2.addItem(opodServerRelationship1.getText());
				opodPrimaryCB3.addItem(opodServerRelationship1.getText());
				opodPrimaryCB4.addItem(opodServerRelationship1.getText());
				opodPrimaryCB5.addItem(opodServerRelationship1.getText());
				opodPrimaryCB6.addItem(opodServerRelationship1.getText());
				opodPrimaryCB7.addItem(opodServerRelationship1.getText());
				opodPrimaryCB8.addItem(opodServerRelationship1.getText());
				opodPrimaryCB1.addItem(opodServerRelationship2.getText());
				opodPrimaryCB2.addItem(opodServerRelationship2.getText());
				opodPrimaryCB3.addItem(opodServerRelationship2.getText());
				opodPrimaryCB4.addItem(opodServerRelationship2.getText());
				opodPrimaryCB5.addItem(opodServerRelationship2.getText());
				opodPrimaryCB6.addItem(opodServerRelationship2.getText());
				opodPrimaryCB7.addItem(opodServerRelationship2.getText());
				opodPrimaryCB8.addItem(opodServerRelationship2.getText());
				opodPrimaryCB1.addItem(opodServerRelationship3.getText());
				opodPrimaryCB2.addItem(opodServerRelationship3.getText());
				opodPrimaryCB3.addItem(opodServerRelationship3.getText());
				opodPrimaryCB4.addItem(opodServerRelationship3.getText());
				opodPrimaryCB5.addItem(opodServerRelationship3.getText());
				opodPrimaryCB6.addItem(opodServerRelationship3.getText());
				opodPrimaryCB7.addItem(opodServerRelationship3.getText());
				opodPrimaryCB8.addItem(opodServerRelationship3.getText());
				opodPrimaryCB1.addItem(opodServerRelationship4.getText());
				opodPrimaryCB2.addItem(opodServerRelationship4.getText());
				opodPrimaryCB3.addItem(opodServerRelationship4.getText());
				opodPrimaryCB4.addItem(opodServerRelationship4.getText());
				opodPrimaryCB5.addItem(opodServerRelationship4.getText());
				opodPrimaryCB6.addItem(opodServerRelationship4.getText());
				opodPrimaryCB7.addItem(opodServerRelationship4.getText());
				opodPrimaryCB8.addItem(opodServerRelationship4.getText());
				opodPrimaryCB1.addItem(opodServerRelationship5.getText());
				opodPrimaryCB2.addItem(opodServerRelationship5.getText());
				opodPrimaryCB3.addItem(opodServerRelationship5.getText());
				opodPrimaryCB4.addItem(opodServerRelationship5.getText());
				opodPrimaryCB5.addItem(opodServerRelationship5.getText());
				opodPrimaryCB6.addItem(opodServerRelationship5.getText());
				opodPrimaryCB7.addItem(opodServerRelationship5.getText());
				opodPrimaryCB8.addItem(opodServerRelationship5.getText());
				opodPrimaryCB1.addItem(opodServerRelationship6.getText());
				opodPrimaryCB2.addItem(opodServerRelationship6.getText());
				opodPrimaryCB3.addItem(opodServerRelationship6.getText());
				opodPrimaryCB4.addItem(opodServerRelationship6.getText());
				opodPrimaryCB5.addItem(opodServerRelationship6.getText());
				opodPrimaryCB6.addItem(opodServerRelationship6.getText());
				opodPrimaryCB7.addItem(opodServerRelationship6.getText());
				opodPrimaryCB8.addItem(opodServerRelationship6.getText());
				opodPrimaryCB1.addItem(opodServerRelationship7.getText());
				opodPrimaryCB2.addItem(opodServerRelationship7.getText());
				opodPrimaryCB3.addItem(opodServerRelationship7.getText());
				opodPrimaryCB4.addItem(opodServerRelationship7.getText());
				opodPrimaryCB5.addItem(opodServerRelationship7.getText());
				opodPrimaryCB6.addItem(opodServerRelationship7.getText());
				opodPrimaryCB7.addItem(opodServerRelationship7.getText());
				opodPrimaryCB8.addItem(opodServerRelationship7.getText());
				opodPrimaryCB1.addItem(opodServerRelationship8.getText());
				opodPrimaryCB2.addItem(opodServerRelationship8.getText());
				opodPrimaryCB3.addItem(opodServerRelationship8.getText());
				opodPrimaryCB4.addItem(opodServerRelationship8.getText());
				opodPrimaryCB5.addItem(opodServerRelationship8.getText());
				opodPrimaryCB6.addItem(opodServerRelationship8.getText());
				opodPrimaryCB7.addItem(opodServerRelationship8.getText());
				opodPrimaryCB8.addItem(opodServerRelationship8.getText());
				opodPrimaryCB1.addItem(opodServerRelationship9.getText());
				opodPrimaryCB2.addItem(opodServerRelationship9.getText());
				opodPrimaryCB3.addItem(opodServerRelationship9.getText());
				opodPrimaryCB4.addItem(opodServerRelationship9.getText());
				opodPrimaryCB5.addItem(opodServerRelationship9.getText());
				opodPrimaryCB6.addItem(opodServerRelationship9.getText());
				opodPrimaryCB7.addItem(opodServerRelationship9.getText());
				opodPrimaryCB8.addItem(opodServerRelationship9.getText());
				opodPrimaryCB1.addItem(opodServerRelationship10.getText());
				opodPrimaryCB2.addItem(opodServerRelationship10.getText());
				opodPrimaryCB3.addItem(opodServerRelationship10.getText());
				opodPrimaryCB4.addItem(opodServerRelationship10.getText());
				opodPrimaryCB5.addItem(opodServerRelationship10.getText());
				opodPrimaryCB6.addItem(opodServerRelationship10.getText());
				opodPrimaryCB7.addItem(opodServerRelationship10.getText());
				opodPrimaryCB8.addItem(opodServerRelationship10.getText());
			}

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		opodServerRelationship10.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent arg0) {
				opodPrimaryCB1.removeAllItems();
				opodPrimaryCB2.removeAllItems();
				opodPrimaryCB3.removeAllItems();
				opodPrimaryCB4.removeAllItems();
				opodPrimaryCB5.removeAllItems();
				opodPrimaryCB6.removeAllItems();
				opodPrimaryCB7.removeAllItems();
				opodPrimaryCB8.removeAllItems();
				opodPrimaryCB1.addItem(opodServerRelationship1.getText());
				opodPrimaryCB2.addItem(opodServerRelationship1.getText());
				opodPrimaryCB3.addItem(opodServerRelationship1.getText());
				opodPrimaryCB4.addItem(opodServerRelationship1.getText());
				opodPrimaryCB5.addItem(opodServerRelationship1.getText());
				opodPrimaryCB6.addItem(opodServerRelationship1.getText());
				opodPrimaryCB7.addItem(opodServerRelationship1.getText());
				opodPrimaryCB8.addItem(opodServerRelationship1.getText());
				opodPrimaryCB1.addItem(opodServerRelationship2.getText());
				opodPrimaryCB2.addItem(opodServerRelationship2.getText());
				opodPrimaryCB3.addItem(opodServerRelationship2.getText());
				opodPrimaryCB4.addItem(opodServerRelationship2.getText());
				opodPrimaryCB5.addItem(opodServerRelationship2.getText());
				opodPrimaryCB6.addItem(opodServerRelationship2.getText());
				opodPrimaryCB7.addItem(opodServerRelationship2.getText());
				opodPrimaryCB8.addItem(opodServerRelationship2.getText());
				opodPrimaryCB1.addItem(opodServerRelationship3.getText());
				opodPrimaryCB2.addItem(opodServerRelationship3.getText());
				opodPrimaryCB3.addItem(opodServerRelationship3.getText());
				opodPrimaryCB4.addItem(opodServerRelationship3.getText());
				opodPrimaryCB5.addItem(opodServerRelationship3.getText());
				opodPrimaryCB6.addItem(opodServerRelationship3.getText());
				opodPrimaryCB7.addItem(opodServerRelationship3.getText());
				opodPrimaryCB8.addItem(opodServerRelationship3.getText());
				opodPrimaryCB1.addItem(opodServerRelationship4.getText());
				opodPrimaryCB2.addItem(opodServerRelationship4.getText());
				opodPrimaryCB3.addItem(opodServerRelationship4.getText());
				opodPrimaryCB4.addItem(opodServerRelationship4.getText());
				opodPrimaryCB5.addItem(opodServerRelationship4.getText());
				opodPrimaryCB6.addItem(opodServerRelationship4.getText());
				opodPrimaryCB7.addItem(opodServerRelationship4.getText());
				opodPrimaryCB8.addItem(opodServerRelationship4.getText());
				opodPrimaryCB1.addItem(opodServerRelationship5.getText());
				opodPrimaryCB2.addItem(opodServerRelationship5.getText());
				opodPrimaryCB3.addItem(opodServerRelationship5.getText());
				opodPrimaryCB4.addItem(opodServerRelationship5.getText());
				opodPrimaryCB5.addItem(opodServerRelationship5.getText());
				opodPrimaryCB6.addItem(opodServerRelationship5.getText());
				opodPrimaryCB7.addItem(opodServerRelationship5.getText());
				opodPrimaryCB8.addItem(opodServerRelationship5.getText());
				opodPrimaryCB1.addItem(opodServerRelationship6.getText());
				opodPrimaryCB2.addItem(opodServerRelationship6.getText());
				opodPrimaryCB3.addItem(opodServerRelationship6.getText());
				opodPrimaryCB4.addItem(opodServerRelationship6.getText());
				opodPrimaryCB5.addItem(opodServerRelationship6.getText());
				opodPrimaryCB6.addItem(opodServerRelationship6.getText());
				opodPrimaryCB7.addItem(opodServerRelationship6.getText());
				opodPrimaryCB8.addItem(opodServerRelationship6.getText());
				opodPrimaryCB1.addItem(opodServerRelationship7.getText());
				opodPrimaryCB2.addItem(opodServerRelationship7.getText());
				opodPrimaryCB3.addItem(opodServerRelationship7.getText());
				opodPrimaryCB4.addItem(opodServerRelationship7.getText());
				opodPrimaryCB5.addItem(opodServerRelationship7.getText());
				opodPrimaryCB6.addItem(opodServerRelationship7.getText());
				opodPrimaryCB7.addItem(opodServerRelationship7.getText());
				opodPrimaryCB8.addItem(opodServerRelationship7.getText());
				opodPrimaryCB1.addItem(opodServerRelationship8.getText());
				opodPrimaryCB2.addItem(opodServerRelationship8.getText());
				opodPrimaryCB3.addItem(opodServerRelationship8.getText());
				opodPrimaryCB4.addItem(opodServerRelationship8.getText());
				opodPrimaryCB5.addItem(opodServerRelationship8.getText());
				opodPrimaryCB6.addItem(opodServerRelationship8.getText());
				opodPrimaryCB7.addItem(opodServerRelationship8.getText());
				opodPrimaryCB8.addItem(opodServerRelationship8.getText());
				opodPrimaryCB1.addItem(opodServerRelationship9.getText());
				opodPrimaryCB2.addItem(opodServerRelationship9.getText());
				opodPrimaryCB3.addItem(opodServerRelationship9.getText());
				opodPrimaryCB4.addItem(opodServerRelationship9.getText());
				opodPrimaryCB5.addItem(opodServerRelationship9.getText());
				opodPrimaryCB6.addItem(opodServerRelationship9.getText());
				opodPrimaryCB7.addItem(opodServerRelationship9.getText());
				opodPrimaryCB8.addItem(opodServerRelationship9.getText());
				opodPrimaryCB1.addItem(opodServerRelationship10.getText());
				opodPrimaryCB2.addItem(opodServerRelationship10.getText());
				opodPrimaryCB3.addItem(opodServerRelationship10.getText());
				opodPrimaryCB4.addItem(opodServerRelationship10.getText());
				opodPrimaryCB5.addItem(opodServerRelationship10.getText());
				opodPrimaryCB6.addItem(opodServerRelationship10.getText());
				opodPrimaryCB7.addItem(opodServerRelationship10.getText());
				opodPrimaryCB8.addItem(opodServerRelationship10.getText());
			}

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
			
		JComboBox opodNumServersCB = new JComboBox(opod.getNumServers());
		opodNumServersCB.setBounds(372, 111, 241, 20);
		OPOD.add(opodNumServersCB);
		opodNumServersCB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*if(opodNumServersCB.getSelectedItem().toString().equals("1")) {
					opodLogicalName1.setVisible(true);
					opodServerRelationship1.setVisible(true);
					opodLogicalName2.setVisible(false);
					opodServerRelationship2.setVisible(false);
					opodLogicalName3.setVisible(false);
					opodServerRelationship3.setVisible(false);
					opodLogicalName4.setVisible(false);
					opodServerRelationship4.setVisible(false);
					opodLogicalName5.setVisible(false);
					opodServerRelationship5.setVisible(false);
					opodLogicalName6.setVisible(false);
					opodServerRelationship6.setVisible(false);
					opodLogicalName7.setVisible(false);
					opodServerRelationship7.setVisible(false);
					opodLogicalName8.setVisible(false);
					opodServerRelationship8.setVisible(false);
					opodLogicalName9.setVisible(false);
					opodServerRelationship9.setVisible(false);
					opodLogicalName10.setVisible(false);
					opodServerRelationship10.setVisible(false);
				}
				if(opodNumServersCB.getSelectedItem().toString().equals("2")) {
					opodLogicalName1.setVisible(true);
					opodServerRelationship1.setVisible(true);
					opodLogicalName2.setVisible(true);
					opodServerRelationship2.setVisible(true);
					opodLogicalName3.setVisible(false);
					opodServerRelationship3.setVisible(false);
					opodLogicalName4.setVisible(false);
					opodServerRelationship4.setVisible(false);
					opodLogicalName5.setVisible(false);
					opodServerRelationship5.setVisible(false);
					opodLogicalName6.setVisible(false);
					opodServerRelationship6.setVisible(false);
					opodLogicalName7.setVisible(false);
					opodServerRelationship7.setVisible(false);
					opodLogicalName8.setVisible(false);
					opodServerRelationship8.setVisible(false);
					opodLogicalName9.setVisible(false);
					opodServerRelationship9.setVisible(false);
					opodLogicalName10.setVisible(false);
					opodServerRelationship10.setVisible(false);
				}
				if(opodNumServersCB.getSelectedItem().toString().equals("3")) {
					opodLogicalName1.setVisible(true);
					opodServerRelationship1.setVisible(true);
					opodLogicalName2.setVisible(true);
					opodServerRelationship2.setVisible(true);
					opodLogicalName3.setVisible(true);
					opodServerRelationship3.setVisible(true);
					opodLogicalName4.setVisible(false);
					opodServerRelationship4.setVisible(false);
					opodLogicalName5.setVisible(false);
					opodServerRelationship5.setVisible(false);
					opodLogicalName6.setVisible(false);
					opodServerRelationship6.setVisible(false);
					opodLogicalName7.setVisible(false);
					opodServerRelationship7.setVisible(false);
					opodLogicalName8.setVisible(false);
					opodServerRelationship8.setVisible(false);
					opodLogicalName9.setVisible(false);
					opodServerRelationship9.setVisible(false);
					opodLogicalName10.setVisible(false);
					opodServerRelationship10.setVisible(false);
				}
				if(opodNumServersCB.getSelectedItem().toString().equals("4")) {
					opodLogicalName1.setVisible(true);
					opodServerRelationship1.setVisible(true);
					opodLogicalName2.setVisible(true);
					opodServerRelationship2.setVisible(true);
					opodLogicalName3.setVisible(true);
					opodServerRelationship3.setVisible(true);
					opodLogicalName4.setVisible(true);
					opodServerRelationship4.setVisible(true);
					opodLogicalName5.setVisible(false);
					opodServerRelationship5.setVisible(false);
					opodLogicalName6.setVisible(false);
					opodServerRelationship6.setVisible(false);
					opodLogicalName7.setVisible(false);
					opodServerRelationship7.setVisible(false);
					opodLogicalName8.setVisible(false);
					opodServerRelationship8.setVisible(false);
					opodLogicalName9.setVisible(false);
					opodServerRelationship9.setVisible(false);
					opodLogicalName10.setVisible(false);
					opodServerRelationship10.setVisible(false);
				}
				if(opodNumServersCB.getSelectedItem().toString().equals("5")) {
					opodLogicalName1.setVisible(true);
					opodServerRelationship1.setVisible(true);
					opodLogicalName2.setVisible(true);
					opodServerRelationship2.setVisible(true);
					opodLogicalName3.setVisible(true);
					opodServerRelationship3.setVisible(true);
					opodLogicalName4.setVisible(true);
					opodServerRelationship4.setVisible(true);
					opodLogicalName5.setVisible(true);
					opodServerRelationship5.setVisible(true);
					opodLogicalName6.setVisible(false);
					opodServerRelationship6.setVisible(false);
					opodLogicalName7.setVisible(false);
					opodServerRelationship7.setVisible(false);
					opodLogicalName8.setVisible(false);
					opodServerRelationship8.setVisible(false);
					opodLogicalName9.setVisible(false);
					opodServerRelationship9.setVisible(false);
					opodLogicalName10.setVisible(false);
					opodServerRelationship10.setVisible(false);
				}
				if(opodNumServersCB.getSelectedItem().toString().equals("6")) {
					opodLogicalName1.setVisible(true);
					opodServerRelationship1.setVisible(true);
					opodLogicalName2.setVisible(true);
					opodServerRelationship2.setVisible(true);
					opodLogicalName3.setVisible(true);
					opodServerRelationship3.setVisible(true);
					opodLogicalName4.setVisible(true);
					opodServerRelationship4.setVisible(true);
					opodLogicalName5.setVisible(true);
					opodServerRelationship5.setVisible(true);
					opodLogicalName6.setVisible(true);
					opodServerRelationship6.setVisible(true);
					opodLogicalName7.setVisible(false);
					opodServerRelationship7.setVisible(false);
					opodLogicalName8.setVisible(false);
					opodServerRelationship8.setVisible(false);
					opodLogicalName9.setVisible(false);
					opodServerRelationship9.setVisible(false);
					opodLogicalName10.setVisible(false);
					opodServerRelationship10.setVisible(false);
				}
				if(opodNumServersCB.getSelectedItem().toString().equals("7")) {
					opodLogicalName1.setVisible(true);
					opodServerRelationship1.setVisible(true);
					opodLogicalName2.setVisible(true);
					opodServerRelationship2.setVisible(true);
					opodLogicalName3.setVisible(true);
					opodServerRelationship3.setVisible(true);
					opodLogicalName4.setVisible(true);
					opodServerRelationship4.setVisible(true);
					opodLogicalName5.setVisible(true);
					opodServerRelationship5.setVisible(true);
					opodLogicalName6.setVisible(true);
					opodServerRelationship6.setVisible(true);
					opodLogicalName7.setVisible(true);
					opodServerRelationship7.setVisible(true);
					opodLogicalName8.setVisible(false);
					opodServerRelationship8.setVisible(false);
					opodLogicalName9.setVisible(false);
					opodServerRelationship9.setVisible(false);
					opodLogicalName10.setVisible(false);
					opodServerRelationship10.setVisible(false);
				}
				if(opodNumServersCB.getSelectedItem().toString().equals("8")) {
					opodLogicalName1.setVisible(true);
					opodServerRelationship1.setVisible(true);
					opodLogicalName2.setVisible(true);
					opodServerRelationship2.setVisible(true);
					opodLogicalName3.setVisible(true);
					opodServerRelationship3.setVisible(true);
					opodLogicalName4.setVisible(true);
					opodServerRelationship4.setVisible(true);
					opodLogicalName5.setVisible(true);
					opodServerRelationship5.setVisible(true);
					opodLogicalName6.setVisible(true);
					opodServerRelationship6.setVisible(true);
					opodLogicalName7.setVisible(true);
					opodServerRelationship7.setVisible(true);
					opodLogicalName8.setVisible(true);
					opodServerRelationship8.setVisible(true);
					opodLogicalName9.setVisible(false);
					opodServerRelationship9.setVisible(false);
					opodLogicalName10.setVisible(false);
					opodServerRelationship10.setVisible(false);
				}
				if(opodNumServersCB.getSelectedItem().toString().equals("9")) {
					opodLogicalName1.setVisible(true);
					opodServerRelationship1.setVisible(true);
					opodLogicalName2.setVisible(true);
					opodServerRelationship2.setVisible(true);
					opodLogicalName3.setVisible(true);
					opodServerRelationship3.setVisible(true);
					opodLogicalName4.setVisible(true);
					opodServerRelationship4.setVisible(true);
					opodLogicalName5.setVisible(true);
					opodServerRelationship5.setVisible(true);
					opodLogicalName6.setVisible(true);
					opodServerRelationship6.setVisible(true);
					opodLogicalName7.setVisible(true);
					opodServerRelationship7.setVisible(true);
					opodLogicalName8.setVisible(true);
					opodServerRelationship8.setVisible(true);
					opodLogicalName9.setVisible(true);
					opodServerRelationship9.setVisible(true);
					opodLogicalName10.setVisible(false);
					opodServerRelationship10.setVisible(false);
				}
				if(opodNumServersCB.getSelectedItem().toString().equals("10")) {
					opodLogicalName1.setVisible(true);
					opodServerRelationship1.setVisible(true);
					opodLogicalName2.setVisible(true);
					opodServerRelationship2.setVisible(true);
					opodLogicalName3.setVisible(true);
					opodServerRelationship3.setVisible(true);
					opodLogicalName4.setVisible(true);
					opodServerRelationship4.setVisible(true);
					opodLogicalName5.setVisible(true);
					opodServerRelationship5.setVisible(true);
					opodLogicalName6.setVisible(true);
					opodServerRelationship6.setVisible(true);
					opodLogicalName7.setVisible(true);
					opodServerRelationship7.setVisible(true);
					opodLogicalName8.setVisible(true);
					opodServerRelationship8.setVisible(true);
					opodLogicalName9.setVisible(true);
					opodServerRelationship9.setVisible(true);
					opodLogicalName10.setVisible(true);
					opodServerRelationship10.setVisible(true);
				}*/
			}
		});
		
		JLabel lblOpodEnironmnet = new JLabel("Environment Selection:");
		lblOpodEnironmnet.setBounds(10, 163, 161, 14);
		OPOD.add(lblOpodEnironmnet);
		
		JComboBox opodEnvironmentSelectionCB = new JComboBox(opod.getEnvironmentOpts());
		opodEnvironmentSelectionCB.setBounds(256, 160, 241, 20);
		OPOD.add(opodEnvironmentSelectionCB);
		
		JComboBox opodBusinessUnitCB = new JComboBox(opod.getBusinessUnitsOpts());
		opodBusinessUnitCB.setBounds(256, 208, 241, 20);
		OPOD.add(opodBusinessUnitCB);
		
		JLabel lblOpodBusinessUnit = new JLabel("Business Unit:");
		lblOpodBusinessUnit.setBounds(10, 211, 112, 14);
		OPOD.add(lblOpodBusinessUnit);
		
		JComboBox opodSecurityZoneCB = new JComboBox(opod.getSecurityZoneZeroOpts());
		opodSecurityZoneCB.setBounds(256, 339, 241, 20);
		OPOD.add(opodSecurityZoneCB);
		
		JComboBox opodDataCenterCB = new JComboBox(opod.getDataCenterOpts());
		opodDataCenterCB.setBounds(256, 254, 241, 20);
		OPOD.add(opodDataCenterCB);
		
		JLabel lblOpodWingLocation = new JLabel("Wing Location in the DC:");
		lblOpodWingLocation.setBounds(10, 386, 148, 14);
		lblOpodWingLocation.setVisible(false);
		OPOD.add(lblOpodWingLocation);
		
		JComboBox opodWingLocationCB = new JComboBox(opod.getWingLocationOpts());
		opodWingLocationCB.setBounds(256, 383, 241, 20);
		opodWingLocationCB.setVisible(false);
		OPOD.add(opodWingLocationCB);
		
		opodDataCenterCB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dataCenterRtn retVals = opod.dataCenterUpdate(opodDataCenterCB.getSelectedItem().toString());

				if(retVals.addWingLocation == true) {
					lblOpodWingLocation.setVisible(true);
					opodWingLocationCB.setVisible(true);
				}
				else {
					lblOpodWingLocation.setVisible(false);
					opodWingLocationCB.setVisible(false);
				}
				
				// Update security zone options
				opodSecurityZoneCB.removeAllItems();
				for(int i =0; i < retVals.securityZoneOptions.length; i++) {
					opodSecurityZoneCB.addItem(retVals.securityZoneOptions[i]);
				}
			}
			
		});	
		
		JLabel lblOpodDataCenter = new JLabel("Data Center:");
		lblOpodDataCenter.setBounds(10, 257, 76, 14);
		OPOD.add(lblOpodDataCenter);
		
		JComboBox opodEnterpriseControllerCB = new JComboBox(opod.getEnterpriseControllerOpts());
		opodEnterpriseControllerCB.setBounds(256, 296, 241, 20);
		OPOD.add(opodEnterpriseControllerCB);
		
		JLabel lblOpodEnterpriseController = new JLabel("Enterprise Controller:");
		lblOpodEnterpriseController.setBounds(10, 299, 192, 14);
		OPOD.add(lblOpodEnterpriseController);
		
		
		JLabel lblOpodSecurityZone = new JLabel("Security Zone:");
		lblOpodSecurityZone.setBounds(10, 342, 112, 14);
		OPOD.add(lblOpodSecurityZone);
		
		JLabel lblOpodVpoolStorageArray = new JLabel("vpool Storage Array:");
		lblOpodVpoolStorageArray.setBounds(10, 430, 148, 14);
		OPOD.add(lblOpodVpoolStorageArray);
		
		JComboBox opodVpoolStorageArrayCB = new JComboBox(opod.getVpoolStorageOpts());
		opodVpoolStorageArrayCB.setBounds(256, 430, 241, 20);
		OPOD.add(opodVpoolStorageArrayCB);
		
		JLabel lblOpodClusterName = new JLabel("Cluster Name:");
		lblOpodClusterName.setBounds(10, 476, 112, 14);
		OPOD.add(lblOpodClusterName);
		
		JTextArea opodClusterNameTA = new JTextArea();
		opodClusterNameTA.setBounds(256, 471, 241, 22);
		OPOD.add(opodClusterNameTA);
		
		JLabel lblOpodNumDatabases = new JLabel("How many Databases (Max. 8)?");
		lblOpodNumDatabases.setBounds(10, 521, 214, 14);
		OPOD.add(lblOpodNumDatabases);
		
		JTextArea opodDatabaseNameTA1 = new JTextArea();
		opodDatabaseNameTA1.setBounds(10, 585, 148, 22);
		OPOD.add(opodDatabaseNameTA1);
		
		JTextArea opodDatabaseNameTA2 = new JTextArea();
		opodDatabaseNameTA2.setVisible(false);
		opodDatabaseNameTA2.setBounds(10, 620, 148, 22);
		OPOD.add(opodDatabaseNameTA2);
		
		JTextArea opodDatabaseNameTA3 = new JTextArea();
		opodDatabaseNameTA3.setVisible(false);
		opodDatabaseNameTA3.setBounds(10, 653, 148, 22);
		OPOD.add(opodDatabaseNameTA3);
		
		JTextArea opodDatabaseNameTA4 = new JTextArea();
		opodDatabaseNameTA4.setVisible(false);
		opodDatabaseNameTA4.setBounds(10, 686, 148, 22);
		OPOD.add(opodDatabaseNameTA4);
		
		JTextArea opodDatabaseNameTA5 = new JTextArea();
		opodDatabaseNameTA5.setVisible(false);
		opodDatabaseNameTA5.setBounds(10, 719, 148, 22);
		OPOD.add(opodDatabaseNameTA5);
		
		JTextArea opodDatabaseNameTA6 = new JTextArea();
		opodDatabaseNameTA6.setVisible(false);
		opodDatabaseNameTA6.setBounds(10, 752, 148, 22);
		OPOD.add(opodDatabaseNameTA6);
		
		JTextArea opodDatabaseNameTA7 = new JTextArea();
		opodDatabaseNameTA7.setVisible(false);
		opodDatabaseNameTA7.setBounds(10, 785, 148, 22);
		OPOD.add(opodDatabaseNameTA7);
		
		JTextArea opodDatabaseNameTA8 = new JTextArea();
		opodDatabaseNameTA8.setVisible(false);
		opodDatabaseNameTA8.setBounds(10, 818, 148, 22);
		OPOD.add(opodDatabaseNameTA8);
		
		JComboBox opodVersionCB1 = new JComboBox(opod.getVersionOpts());
		opodVersionCB1.setBounds(168, 587, 161, 20);
		OPOD.add(opodVersionCB1);
		
		JComboBox opodVersionCB2 = new JComboBox(opod.getVersionOpts());
		opodVersionCB2.setVisible(false);
		opodVersionCB2.setBounds(168, 622, 161, 20);
		OPOD.add(opodVersionCB2);
		
		JComboBox opodVersionCB3 = new JComboBox(opod.getVersionOpts());
		opodVersionCB3.setVisible(false);
		opodVersionCB3.setBounds(168, 655, 161, 20);
		OPOD.add(opodVersionCB3);
		
		JComboBox opodVersionCB4 = new JComboBox(opod.getVersionOpts());
		opodVersionCB4.setVisible(false);
		opodVersionCB4.setBounds(168, 688, 161, 20);
		OPOD.add(opodVersionCB4);
		
		JComboBox opodVersionCB5 = new JComboBox(opod.getVersionOpts());
		opodVersionCB5.setVisible(false);
		opodVersionCB5.setBounds(168, 721, 161, 20);
		OPOD.add(opodVersionCB5);
		
		JComboBox opodVersionCB6 = new JComboBox(opod.getVersionOpts());
		opodVersionCB6.setVisible(false);
		opodVersionCB6.setBounds(168, 754, 161, 20);
		OPOD.add(opodVersionCB6);
		
		JComboBox opodVersionCB7 = new JComboBox(opod.getVersionOpts());
		opodVersionCB7.setVisible(false);
		opodVersionCB7.setBounds(168, 787, 161, 20);
		OPOD.add(opodVersionCB7);
		
		JComboBox opodVersionCB8 = new JComboBox(opod.getVersionOpts());
		opodVersionCB8.setVisible(false);
		opodVersionCB8.setBounds(168, 820, 161, 20);
		OPOD.add(opodVersionCB8);
		
		

		
		JComboBox opodBackupTypeCB1 = new JComboBox(opod.getBackupTypeOpts());
		opodBackupTypeCB1.setBounds(681, 587, 161, 20);
		OPOD.add(opodBackupTypeCB1);
		
		JComboBox opodBackupTypeCB2 = new JComboBox(opod.getBackupTypeOpts());
		opodBackupTypeCB2.setVisible(false);
		opodBackupTypeCB2.setBounds(681, 622, 161, 20);
		OPOD.add(opodBackupTypeCB2);
		
		JComboBox opodBackupTypeCB3 = new JComboBox(opod.getBackupTypeOpts());
		opodBackupTypeCB3.setVisible(false);
		opodBackupTypeCB3.setBounds(681, 655, 161, 20);
		OPOD.add(opodBackupTypeCB3);
		
		JComboBox opodBackupTypeCB4 = new JComboBox(opod.getBackupTypeOpts());
		opodBackupTypeCB4.setVisible(false);
		opodBackupTypeCB4.setBounds(681, 688, 161, 20);
		OPOD.add(opodBackupTypeCB4);
		
		JComboBox opodBackupTypeCB5 = new JComboBox(opod.getBackupTypeOpts());
		opodBackupTypeCB5.setVisible(false);
		opodBackupTypeCB5.setBounds(681, 721, 161, 20);
		OPOD.add(opodBackupTypeCB5);
		
		JComboBox opodBackupTypeCB6 = new JComboBox(opod.getBackupTypeOpts());
		opodBackupTypeCB6.setVisible(false);
		opodBackupTypeCB6.setBounds(681, 754, 161, 20);
		OPOD.add(opodBackupTypeCB6);
		
		JComboBox opodBackupTypeCB7 = new JComboBox(opod.getBackupTypeOpts());
		opodBackupTypeCB7.setVisible(false);
		opodBackupTypeCB7.setBounds(681, 787, 161, 20);
		OPOD.add(opodBackupTypeCB7);
		
		JComboBox opodBackupTypeCB8 = new JComboBox(opod.getBackupTypeOpts());
		opodBackupTypeCB8.setVisible(false);
		opodBackupTypeCB8.setBounds(681, 820, 161, 20);
		OPOD.add(opodBackupTypeCB8);
		
		JComboBox opodNumDatabasesCB = new JComboBox(opod.getNumDatabasesOpts());
		opodNumDatabasesCB.setBounds(256, 518, 241, 20);
		OPOD.add(opodNumDatabasesCB);
		opodNumDatabasesCB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(opodNumDatabasesCB.getSelectedItem().toString().equals("1")) {
					opodDatabaseNameTA1.setVisible(true);
					opodDatabaseNameTA2.setVisible(false);
					opodDatabaseNameTA3.setVisible(false);
					opodDatabaseNameTA4.setVisible(false);
					opodDatabaseNameTA5.setVisible(false);
					opodDatabaseNameTA6.setVisible(false);
					opodDatabaseNameTA7.setVisible(false);
					opodDatabaseNameTA8.setVisible(false);
					opodVersionCB1.setVisible(true);
					opodVersionCB2.setVisible(false);
					opodVersionCB3.setVisible(false);
					opodVersionCB4.setVisible(false);
					opodVersionCB5.setVisible(false);
					opodVersionCB6.setVisible(false);
					opodVersionCB7.setVisible(false);
					opodVersionCB8.setVisible(false);
					opodRelationshipCB1.setVisible(true);
					opodRelationshipCB2.setVisible(false);
					opodRelationshipCB3.setVisible(false);
					opodRelationshipCB4.setVisible(false);
					opodRelationshipCB5.setVisible(false);
					opodRelationshipCB6.setVisible(false);
					opodRelationshipCB7.setVisible(false);
					opodRelationshipCB8.setVisible(false);
					opodPrimaryCB1.setVisible(true);
					opodPrimaryCB2.setVisible(false);
					opodPrimaryCB3.setVisible(false);
					opodPrimaryCB4.setVisible(false);
					opodPrimaryCB5.setVisible(false);
					opodPrimaryCB6.setVisible(false);
					opodPrimaryCB7.setVisible(false);
					opodPrimaryCB8.setVisible(false);
					opodBackupTypeCB1.setVisible(true);
					opodBackupTypeCB2.setVisible(false);
					opodBackupTypeCB3.setVisible(false);
					opodBackupTypeCB4.setVisible(false);
					opodBackupTypeCB5.setVisible(false);
					opodBackupTypeCB6.setVisible(false);
					opodBackupTypeCB7.setVisible(false);
					opodBackupTypeCB8.setVisible(false);
				}
				if(opodNumDatabasesCB.getSelectedItem().toString().equals("2")) {
					opodDatabaseNameTA1.setVisible(true);
					opodDatabaseNameTA2.setVisible(true);
					opodDatabaseNameTA3.setVisible(false);
					opodDatabaseNameTA4.setVisible(false);
					opodDatabaseNameTA5.setVisible(false);
					opodDatabaseNameTA6.setVisible(false);
					opodDatabaseNameTA7.setVisible(false);
					opodDatabaseNameTA8.setVisible(false);
					opodVersionCB1.setVisible(true);
					opodVersionCB2.setVisible(true);
					opodVersionCB3.setVisible(false);
					opodVersionCB4.setVisible(false);
					opodVersionCB5.setVisible(false);
					opodVersionCB6.setVisible(false);
					opodVersionCB7.setVisible(false);
					opodVersionCB8.setVisible(false);
					opodRelationshipCB1.setVisible(true);
					opodRelationshipCB2.setVisible(true);
					opodRelationshipCB3.setVisible(false);
					opodRelationshipCB4.setVisible(false);
					opodRelationshipCB5.setVisible(false);
					opodRelationshipCB6.setVisible(false);
					opodRelationshipCB7.setVisible(false);
					opodRelationshipCB8.setVisible(false);
					opodPrimaryCB1.setVisible(true);
					opodPrimaryCB2.setVisible(true);
					opodPrimaryCB3.setVisible(false);
					opodPrimaryCB4.setVisible(false);
					opodPrimaryCB5.setVisible(false);
					opodPrimaryCB6.setVisible(false);
					opodPrimaryCB7.setVisible(false);
					opodPrimaryCB8.setVisible(false);
					opodBackupTypeCB1.setVisible(true);
					opodBackupTypeCB2.setVisible(true);
					opodBackupTypeCB3.setVisible(false);
					opodBackupTypeCB4.setVisible(false);
					opodBackupTypeCB5.setVisible(false);
					opodBackupTypeCB6.setVisible(false);
					opodBackupTypeCB7.setVisible(false);
					opodBackupTypeCB8.setVisible(false);
				}
				if(opodNumDatabasesCB.getSelectedItem().toString().equals("3")) {
					opodDatabaseNameTA1.setVisible(true);
					opodDatabaseNameTA2.setVisible(true);
					opodDatabaseNameTA3.setVisible(true);
					opodDatabaseNameTA4.setVisible(false);
					opodDatabaseNameTA5.setVisible(false);
					opodDatabaseNameTA6.setVisible(false);
					opodDatabaseNameTA7.setVisible(false);
					opodDatabaseNameTA8.setVisible(false);
					opodVersionCB1.setVisible(true);
					opodVersionCB2.setVisible(true);
					opodVersionCB3.setVisible(true);
					opodVersionCB4.setVisible(false);
					opodVersionCB5.setVisible(false);
					opodVersionCB6.setVisible(false);
					opodVersionCB7.setVisible(false);
					opodVersionCB8.setVisible(false);
					opodRelationshipCB1.setVisible(true);
					opodRelationshipCB2.setVisible(true);
					opodRelationshipCB3.setVisible(true);
					opodRelationshipCB4.setVisible(false);
					opodRelationshipCB5.setVisible(false);
					opodRelationshipCB6.setVisible(false);
					opodRelationshipCB7.setVisible(false);
					opodRelationshipCB8.setVisible(false);
					opodPrimaryCB1.setVisible(true);
					opodPrimaryCB2.setVisible(true);
					opodPrimaryCB3.setVisible(true);
					opodPrimaryCB4.setVisible(false);
					opodPrimaryCB5.setVisible(false);
					opodPrimaryCB6.setVisible(false);
					opodPrimaryCB7.setVisible(false);
					opodPrimaryCB8.setVisible(false);
					opodBackupTypeCB1.setVisible(true);
					opodBackupTypeCB2.setVisible(true);
					opodBackupTypeCB3.setVisible(true);
					opodBackupTypeCB4.setVisible(false);
					opodBackupTypeCB5.setVisible(false);
					opodBackupTypeCB6.setVisible(false);
					opodBackupTypeCB7.setVisible(false);
					opodBackupTypeCB8.setVisible(false);
				}
				if(opodNumDatabasesCB.getSelectedItem().toString().equals("4")) {
					opodDatabaseNameTA1.setVisible(true);
					opodDatabaseNameTA2.setVisible(true);
					opodDatabaseNameTA3.setVisible(true);
					opodDatabaseNameTA4.setVisible(true);
					opodDatabaseNameTA5.setVisible(false);
					opodDatabaseNameTA6.setVisible(false);
					opodDatabaseNameTA7.setVisible(false);
					opodDatabaseNameTA8.setVisible(false);
					opodVersionCB1.setVisible(true);
					opodVersionCB2.setVisible(true);
					opodVersionCB3.setVisible(true);
					opodVersionCB4.setVisible(true);
					opodVersionCB5.setVisible(false);
					opodVersionCB6.setVisible(false);
					opodVersionCB7.setVisible(false);
					opodVersionCB8.setVisible(false);
					opodRelationshipCB1.setVisible(true);
					opodRelationshipCB2.setVisible(true);
					opodRelationshipCB3.setVisible(true);
					opodRelationshipCB4.setVisible(true);
					opodRelationshipCB5.setVisible(false);
					opodRelationshipCB6.setVisible(false);
					opodRelationshipCB7.setVisible(false);
					opodRelationshipCB8.setVisible(false);
					opodPrimaryCB1.setVisible(true);
					opodPrimaryCB2.setVisible(true);
					opodPrimaryCB3.setVisible(true);
					opodPrimaryCB4.setVisible(true);
					opodPrimaryCB5.setVisible(false);
					opodPrimaryCB6.setVisible(false);
					opodPrimaryCB7.setVisible(false);
					opodPrimaryCB8.setVisible(false);
					opodBackupTypeCB1.setVisible(true);
					opodBackupTypeCB2.setVisible(true);
					opodBackupTypeCB3.setVisible(true);
					opodBackupTypeCB4.setVisible(true);
					opodBackupTypeCB5.setVisible(false);
					opodBackupTypeCB6.setVisible(false);
					opodBackupTypeCB7.setVisible(false);
					opodBackupTypeCB8.setVisible(false);
				}
				if(opodNumDatabasesCB.getSelectedItem().toString().equals("5")) {
					opodDatabaseNameTA1.setVisible(true);
					opodDatabaseNameTA2.setVisible(true);
					opodDatabaseNameTA3.setVisible(true);
					opodDatabaseNameTA4.setVisible(true);
					opodDatabaseNameTA5.setVisible(true);
					opodDatabaseNameTA6.setVisible(false);
					opodDatabaseNameTA7.setVisible(false);
					opodDatabaseNameTA8.setVisible(false);
					opodVersionCB1.setVisible(true);
					opodVersionCB2.setVisible(true);
					opodVersionCB3.setVisible(true);
					opodVersionCB4.setVisible(true);
					opodVersionCB5.setVisible(true);
					opodVersionCB6.setVisible(false);
					opodVersionCB7.setVisible(false);
					opodVersionCB8.setVisible(false);
					opodRelationshipCB1.setVisible(true);
					opodRelationshipCB2.setVisible(true);
					opodRelationshipCB3.setVisible(true);
					opodRelationshipCB4.setVisible(true);
					opodRelationshipCB5.setVisible(true);
					opodRelationshipCB6.setVisible(false);
					opodRelationshipCB7.setVisible(false);
					opodRelationshipCB8.setVisible(false);
					opodPrimaryCB1.setVisible(true);
					opodPrimaryCB2.setVisible(true);
					opodPrimaryCB3.setVisible(true);
					opodPrimaryCB4.setVisible(true);
					opodPrimaryCB5.setVisible(true);
					opodPrimaryCB6.setVisible(false);
					opodPrimaryCB7.setVisible(false);
					opodPrimaryCB8.setVisible(false);
					opodBackupTypeCB1.setVisible(true);
					opodBackupTypeCB2.setVisible(true);
					opodBackupTypeCB3.setVisible(true);
					opodBackupTypeCB4.setVisible(true);
					opodBackupTypeCB5.setVisible(true);
					opodBackupTypeCB6.setVisible(false);
					opodBackupTypeCB7.setVisible(false);
					opodBackupTypeCB8.setVisible(false);
				}
				if(opodNumDatabasesCB.getSelectedItem().toString().equals("6")) {
					opodDatabaseNameTA1.setVisible(true);
					opodDatabaseNameTA2.setVisible(true);
					opodDatabaseNameTA3.setVisible(true);
					opodDatabaseNameTA4.setVisible(true);
					opodDatabaseNameTA5.setVisible(true);
					opodDatabaseNameTA6.setVisible(true);
					opodDatabaseNameTA7.setVisible(false);
					opodDatabaseNameTA8.setVisible(false);
					opodVersionCB1.setVisible(true);
					opodVersionCB2.setVisible(true);
					opodVersionCB3.setVisible(true);
					opodVersionCB4.setVisible(true);
					opodVersionCB5.setVisible(true);
					opodVersionCB6.setVisible(true);
					opodVersionCB7.setVisible(false);
					opodVersionCB8.setVisible(false);
					opodRelationshipCB1.setVisible(true);
					opodRelationshipCB2.setVisible(true);
					opodRelationshipCB3.setVisible(true);
					opodRelationshipCB4.setVisible(true);
					opodRelationshipCB5.setVisible(true);
					opodRelationshipCB6.setVisible(true);
					opodRelationshipCB7.setVisible(false);
					opodRelationshipCB8.setVisible(false);
					opodPrimaryCB1.setVisible(true);
					opodPrimaryCB2.setVisible(true);
					opodPrimaryCB3.setVisible(true);
					opodPrimaryCB4.setVisible(true);
					opodPrimaryCB5.setVisible(true);
					opodPrimaryCB6.setVisible(true);
					opodPrimaryCB7.setVisible(false);
					opodPrimaryCB8.setVisible(false);
					opodBackupTypeCB1.setVisible(true);
					opodBackupTypeCB2.setVisible(true);
					opodBackupTypeCB3.setVisible(true);
					opodBackupTypeCB4.setVisible(true);
					opodBackupTypeCB5.setVisible(true);
					opodBackupTypeCB6.setVisible(true);
					opodBackupTypeCB7.setVisible(false);
					opodBackupTypeCB8.setVisible(false);
				}
				if(opodNumDatabasesCB.getSelectedItem().toString().equals("7")) {
					opodDatabaseNameTA1.setVisible(true);
					opodDatabaseNameTA2.setVisible(true);
					opodDatabaseNameTA3.setVisible(true);
					opodDatabaseNameTA4.setVisible(true);
					opodDatabaseNameTA5.setVisible(true);
					opodDatabaseNameTA6.setVisible(true);
					opodDatabaseNameTA7.setVisible(true);
					opodDatabaseNameTA8.setVisible(false);
					opodVersionCB1.setVisible(true);
					opodVersionCB2.setVisible(true);
					opodVersionCB3.setVisible(true);
					opodVersionCB4.setVisible(true);
					opodVersionCB5.setVisible(true);
					opodVersionCB6.setVisible(true);
					opodVersionCB7.setVisible(true);
					opodVersionCB8.setVisible(false);
					opodRelationshipCB1.setVisible(true);
					opodRelationshipCB2.setVisible(true);
					opodRelationshipCB3.setVisible(true);
					opodRelationshipCB4.setVisible(true);
					opodRelationshipCB5.setVisible(true);
					opodRelationshipCB6.setVisible(true);
					opodRelationshipCB7.setVisible(true);
					opodRelationshipCB8.setVisible(false);
					opodPrimaryCB1.setVisible(true);
					opodPrimaryCB2.setVisible(true);
					opodPrimaryCB3.setVisible(true);
					opodPrimaryCB4.setVisible(true);
					opodPrimaryCB5.setVisible(true);
					opodPrimaryCB6.setVisible(true);
					opodPrimaryCB7.setVisible(true);
					opodPrimaryCB8.setVisible(false);
					opodBackupTypeCB1.setVisible(true);
					opodBackupTypeCB2.setVisible(true);
					opodBackupTypeCB3.setVisible(true);
					opodBackupTypeCB4.setVisible(true);
					opodBackupTypeCB5.setVisible(true);
					opodBackupTypeCB6.setVisible(true);
					opodBackupTypeCB7.setVisible(true);
					opodBackupTypeCB8.setVisible(false);
				}
				if(opodNumDatabasesCB.getSelectedItem().toString().equals("8")) {
					opodDatabaseNameTA1.setVisible(true);
					opodDatabaseNameTA2.setVisible(true);
					opodDatabaseNameTA3.setVisible(true);
					opodDatabaseNameTA4.setVisible(true);
					opodDatabaseNameTA5.setVisible(true);
					opodDatabaseNameTA6.setVisible(true);
					opodDatabaseNameTA7.setVisible(true);
					opodDatabaseNameTA8.setVisible(true);
					opodVersionCB1.setVisible(true);
					opodVersionCB2.setVisible(true);
					opodVersionCB3.setVisible(true);
					opodVersionCB4.setVisible(true);
					opodVersionCB5.setVisible(true);
					opodVersionCB6.setVisible(true);
					opodVersionCB7.setVisible(true);
					opodVersionCB8.setVisible(true);
					opodRelationshipCB1.setVisible(true);
					opodRelationshipCB2.setVisible(true);
					opodRelationshipCB3.setVisible(true);
					opodRelationshipCB4.setVisible(true);
					opodRelationshipCB5.setVisible(true);
					opodRelationshipCB6.setVisible(true);
					opodRelationshipCB7.setVisible(true);
					opodRelationshipCB8.setVisible(true);
					opodPrimaryCB1.setVisible(true);
					opodPrimaryCB2.setVisible(true);
					opodPrimaryCB3.setVisible(true);
					opodPrimaryCB4.setVisible(true);
					opodPrimaryCB5.setVisible(true);
					opodPrimaryCB6.setVisible(true);
					opodPrimaryCB7.setVisible(true);
					opodPrimaryCB8.setVisible(true);
					opodBackupTypeCB1.setVisible(true);
					opodBackupTypeCB2.setVisible(true);
					opodBackupTypeCB3.setVisible(true);
					opodBackupTypeCB4.setVisible(true);
					opodBackupTypeCB5.setVisible(true);
					opodBackupTypeCB6.setVisible(true);
					opodBackupTypeCB7.setVisible(true);
					opodBackupTypeCB8.setVisible(true);
				}

			}
		});
		
		
		JLabel lblLogicalNames = new JLabel("Logical Names");
		lblLogicalNames.setBounds(627, 181, 148, 14);
		OPOD.add(lblLogicalNames);
		
		JLabel lblServerRelationship = new JLabel("Server Relationship");
		lblServerRelationship.setBounds(785, 181, 148, 14);
		OPOD.add(lblServerRelationship);
		
		JLabel lblOpodDatabaseName = new JLabel("Database Name");
		lblOpodDatabaseName.setBounds(10, 560, 148, 14);
		OPOD.add(lblOpodDatabaseName);
		
		JLabel lblOpodVersion = new JLabel("Version");
		lblOpodVersion.setBounds(168, 562, 161, 14);
		OPOD.add(lblOpodVersion);
		
		JLabel lblOpodRelationship = new JLabel("Relationship");
		lblOpodRelationship.setBounds(339, 560, 150, 14);
		OPOD.add(lblOpodRelationship);
		
		JLabel lblOpodPrimary = new JLabel("Primary");
		lblOpodPrimary.setBounds(510, 560, 161, 14);
		OPOD.add(lblOpodPrimary);
		
		JLabel lblOpodBackupType = new JLabel("Backup Type");
		lblOpodBackupType.setBounds(681, 560, 148, 14);
		OPOD.add(lblOpodBackupType);
		
		JComboBox opodModulesCB = new JComboBox(opod.getOracleSHSModulesOpts());
		opodModulesCB.setBounds(1040, 721, 393, 20);
		OPOD.add(opodModulesCB);
		
		JButton opodModulesBtn = new JButton("Proceed with the selected option");
		opodModulesBtn.setBounds(1498, 720, 233, 23);
		OPOD.add(opodModulesBtn);
		
		JTextArea opodDiskGroupNameTA1 = new JTextArea();
		opodDiskGroupNameTA1.setText("FRA");
		opodDiskGroupNameTA1.setBounds(989, 206, 148, 22);
		OPOD.add(opodDiskGroupNameTA1);
		
		JTextArea opodDiskGroupNameTA2 = new JTextArea();
		opodDiskGroupNameTA2.setText("TEMP");
		opodDiskGroupNameTA2.setBounds(989, 239, 148, 22);
		OPOD.add(opodDiskGroupNameTA2);
		
		JTextArea opodDiskGroupNameTA3 = new JTextArea();
		opodDiskGroupNameTA3.setText("REDOA");
		opodDiskGroupNameTA3.setBounds(989, 272, 148, 22);
		OPOD.add(opodDiskGroupNameTA3);
		
		JTextArea opodDiskGroupNameTA4 = new JTextArea();
		opodDiskGroupNameTA4.setText("REDOB");
		opodDiskGroupNameTA4.setBounds(989, 305, 148, 22);
		OPOD.add(opodDiskGroupNameTA4);
		
		JTextArea opodDiskGroupNameTA5 = new JTextArea();
		opodDiskGroupNameTA5.setText("OCRVOTE");
		opodDiskGroupNameTA5.setBounds(989, 337, 148, 22);
		OPOD.add(opodDiskGroupNameTA5);
		
		JTextArea opodDiskGroupNameTA6 = new JTextArea();
		opodDiskGroupNameTA6.setBounds(989, 370, 148, 22);
		OPOD.add(opodDiskGroupNameTA6);
		
		JTextArea opodDiskGroupNameTA7 = new JTextArea();
		opodDiskGroupNameTA7.setBounds(989, 403, 148, 22);
		OPOD.add(opodDiskGroupNameTA7);
		
		JTextArea opodDiskGroupNameTA8 = new JTextArea();
		opodDiskGroupNameTA8.setBounds(989, 436, 148, 22);
		OPOD.add(opodDiskGroupNameTA8);
		
		JTextArea opodDiskGroupNameTA9 = new JTextArea();
		opodDiskGroupNameTA9.setBounds(989, 471, 148, 22);
		OPOD.add(opodDiskGroupNameTA9);
		
		JTextArea opodDiskGroupNameTA10 = new JTextArea();
		opodDiskGroupNameTA10.setBounds(989, 504, 148, 22);
		OPOD.add(opodDiskGroupNameTA10);
		
		JTextArea opodDiskGroupNameTA11 = new JTextArea();
		opodDiskGroupNameTA11.setBounds(989, 537, 148, 22);
		OPOD.add(opodDiskGroupNameTA11);
		
		JTextArea opodDiskGroupNameTA12 = new JTextArea();
		opodDiskGroupNameTA12.setBounds(989, 570, 148, 22);
		OPOD.add(opodDiskGroupNameTA12);
		
		JTextArea opodDiskGroupNameTA13 = new JTextArea();
		opodDiskGroupNameTA13.setBounds(989, 603, 148, 22);
		OPOD.add(opodDiskGroupNameTA13);
		
		JTextArea opodDiskGroupNameTA14 = new JTextArea();
		opodDiskGroupNameTA14.setBounds(989, 636, 148, 22);
		OPOD.add(opodDiskGroupNameTA14);
		
		JTextArea opodDiskGroupNameTA15 = new JTextArea();
		opodDiskGroupNameTA15.setBounds(989, 669, 148, 22);
		OPOD.add(opodDiskGroupNameTA15);
		
		JTextArea opodLunsTA1 = new JTextArea();
		opodLunsTA1.setText("8");
		opodLunsTA1.setBounds(1147, 206, 148, 22);
		OPOD.add(opodLunsTA1);
		
		JTextArea opodLunsTA2 = new JTextArea();
		opodLunsTA2.setText("8");
		opodLunsTA2.setBounds(1147, 239, 148, 22);
		OPOD.add(opodLunsTA2);
		
		JTextArea opodLunsTA3 = new JTextArea();
		opodLunsTA3.setText("8");
		opodLunsTA3.setBounds(1147, 272, 148, 22);
		OPOD.add(opodLunsTA3);
		
		JTextArea opodLunsTA4 = new JTextArea();
		opodLunsTA4.setText("8");
		opodLunsTA4.setBounds(1147, 305, 148, 22);
		OPOD.add(opodLunsTA4);
		
		JTextArea opodLunsTA5 = new JTextArea();
		opodLunsTA5.setText("2");
		opodLunsTA5.setBounds(1147, 337, 148, 22);
		OPOD.add(opodLunsTA5);
		
		JTextArea opodLunsTA6 = new JTextArea();
		opodLunsTA6.setBounds(1147, 370, 148, 22);
		OPOD.add(opodLunsTA6);
		
		JTextArea opodLunsTA7 = new JTextArea();
		opodLunsTA7.setBounds(1147, 403, 148, 22);
		OPOD.add(opodLunsTA7);
		
		JTextArea opodLunsTA8 = new JTextArea();
		opodLunsTA8.setBounds(1147, 436, 148, 22);
		OPOD.add(opodLunsTA8);
		
		JTextArea opodLunsTA9 = new JTextArea();
		opodLunsTA9.setBounds(1147, 471, 148, 22);
		OPOD.add(opodLunsTA9);
		
		JTextArea opodLunsTA10 = new JTextArea();
		opodLunsTA10.setBounds(1147, 504, 148, 22);
		OPOD.add(opodLunsTA10);
		
		JTextArea opodLunsTA11 = new JTextArea();
		opodLunsTA11.setBounds(1147, 537, 148, 22);
		OPOD.add(opodLunsTA11);
		
		JTextArea opodLunsTA12 = new JTextArea();
		opodLunsTA12.setBounds(1147, 570, 148, 22);
		OPOD.add(opodLunsTA12);
		
		JTextArea opodLunsTA13 = new JTextArea();
		opodLunsTA13.setBounds(1147, 603, 148, 22);
		OPOD.add(opodLunsTA13);
		
		JTextArea opodLunsTA14 = new JTextArea();
		opodLunsTA14.setBounds(1147, 636, 148, 22);
		OPOD.add(opodLunsTA14);
		
		JTextArea opodLunsTA15 = new JTextArea();
		opodLunsTA15.setBounds(1147, 669, 148, 22);
		OPOD.add(opodLunsTA15);
		
		JTextArea opodLunSizeTA1 = new JTextArea();
		opodLunSizeTA1.setText("32");
		opodLunSizeTA1.setBounds(1305, 206, 148, 22);
		OPOD.add(opodLunSizeTA1);
		
		JTextArea opodLunSizeTA2 = new JTextArea();
		opodLunSizeTA2.setText("32");
		opodLunSizeTA2.setBounds(1305, 239, 148, 22);
		OPOD.add(opodLunSizeTA2);
		
		JTextArea opodLunSizeTA3 = new JTextArea();
		opodLunSizeTA3.setText("32");
		opodLunSizeTA3.setBounds(1305, 272, 148, 22);
		OPOD.add(opodLunSizeTA3);
		
		JTextArea opodLunSizeTA4 = new JTextArea();
		opodLunSizeTA4.setText("32");
		opodLunSizeTA4.setBounds(1305, 305, 148, 22);
		OPOD.add(opodLunSizeTA4);
		
		JTextArea opodLunSizeTA5 = new JTextArea();
		opodLunSizeTA5.setText("2");
		opodLunSizeTA5.setBounds(1305, 337, 148, 22);
		OPOD.add(opodLunSizeTA5);
		
		JTextArea opodLunSizeTA6 = new JTextArea();
		opodLunSizeTA6.setText("0");
		opodLunSizeTA6.setBounds(1305, 370, 148, 22);
		OPOD.add(opodLunSizeTA6);
		
		JTextArea opodLunSizeTA7 = new JTextArea();
		opodLunSizeTA7.setText("0");
		opodLunSizeTA7.setBounds(1305, 403, 148, 22);
		OPOD.add(opodLunSizeTA7);
		
		JTextArea opodLunSizeTA8 = new JTextArea();
		opodLunSizeTA8.setText("0");
		opodLunSizeTA8.setBounds(1305, 436, 148, 22);
		OPOD.add(opodLunSizeTA8);
		
		JTextArea opodLunSizeTA9 = new JTextArea();
		opodLunSizeTA9.setText("0");
		opodLunSizeTA9.setBounds(1305, 471, 148, 22);
		OPOD.add(opodLunSizeTA9);
		
		JTextArea opodLunSizeTA10 = new JTextArea();
		opodLunSizeTA10.setText("0");
		opodLunSizeTA10.setBounds(1305, 504, 148, 22);
		OPOD.add(opodLunSizeTA10);
		
		JTextArea opodLunSizeTA11 = new JTextArea();
		opodLunSizeTA11.setText("0");
		opodLunSizeTA11.setBounds(1305, 537, 148, 22);
		OPOD.add(opodLunSizeTA11);
		
		JTextArea opodLunSizeTA12 = new JTextArea();
		opodLunSizeTA12.setText("0");
		opodLunSizeTA12.setBounds(1305, 570, 148, 22);
		OPOD.add(opodLunSizeTA12);
		
		JTextArea opodLunSizeTA13 = new JTextArea();
		opodLunSizeTA13.setText("0");
		opodLunSizeTA13.setBounds(1305, 603, 148, 22);
		OPOD.add(opodLunSizeTA13);
		
		JTextArea opodLunSizeTA14 = new JTextArea();
		opodLunSizeTA14.setText("0");
		opodLunSizeTA14.setBounds(1305, 636, 148, 22);
		OPOD.add(opodLunSizeTA14);
		
		JTextArea opodLunSizeTA15 = new JTextArea();
		opodLunSizeTA15.setText("0");
		opodLunSizeTA15.setBounds(1305, 669, 148, 22);
		OPOD.add(opodLunSizeTA15);
		
		JComboBox opodTierCB1 = new JComboBox(opod.getTierOpts());
		opodTierCB1.setSelectedItem("Tier 2");
		opodTierCB1.setBounds(1463, 208, 148, 20);
		OPOD.add(opodTierCB1);
		
		JComboBox opodTierCB2 = new JComboBox(opod.getTierOpts());
		opodTierCB2.setSelectedItem("Tier 2");
		opodTierCB2.setBounds(1463, 241, 148, 20);
		OPOD.add(opodTierCB2);
		
		JComboBox opodTierCB3 = new JComboBox(opod.getTierOpts());
		opodTierCB3.setSelectedItem("Tier 2");
		opodTierCB3.setBounds(1463, 274, 148, 20);
		OPOD.add(opodTierCB3);
		
		JComboBox opodTierCB4 = new JComboBox(opod.getTierOpts());
		opodTierCB4.setSelectedItem("Tier 2");
		opodTierCB4.setBounds(1463, 307, 148, 20);
		OPOD.add(opodTierCB4);
		
		JComboBox opodTierCB5 = new JComboBox(opod.getTierOpts());
		opodTierCB5.setSelectedItem("Tier 2");
		opodTierCB5.setBounds(1463, 339, 148, 20);
		OPOD.add(opodTierCB5);
		
		JComboBox opodTierCB6 = new JComboBox(opod.getTierOpts());
		opodTierCB6.setBounds(1463, 372, 148, 20);
		OPOD.add(opodTierCB6);
		
		JComboBox opodTierCB7 = new JComboBox(opod.getTierOpts());
		opodTierCB7.setBounds(1463, 405, 148, 20);
		OPOD.add(opodTierCB7);
		
		JComboBox opodTierCB8 = new JComboBox(opod.getTierOpts());
		opodTierCB8.setBounds(1463, 438, 148, 20);
		OPOD.add(opodTierCB8);
		
		JComboBox opodTierCB9 = new JComboBox(opod.getTierOpts());
		opodTierCB9.setBounds(1463, 473, 148, 20);
		OPOD.add(opodTierCB9);
		
		JComboBox opodTierCB10 = new JComboBox(opod.getTierOpts());
		opodTierCB10.setBounds(1463, 506, 148, 20);
		OPOD.add(opodTierCB10);
		
		JComboBox opodTierCB11 = new JComboBox(opod.getTierOpts());
		opodTierCB11.setBounds(1463, 539, 148, 20);
		OPOD.add(opodTierCB11);
		
		JComboBox opodTierCB12 = new JComboBox(opod.getTierOpts());
		opodTierCB12.setBounds(1463, 572, 148, 20);
		OPOD.add(opodTierCB12);
		
		JComboBox opodTierCB13 = new JComboBox(opod.getTierOpts());
		opodTierCB13.setBounds(1463, 605, 148, 20);
		OPOD.add(opodTierCB13);
		
		JComboBox opodTierCB14 = new JComboBox(opod.getTierOpts());
		opodTierCB14.setBounds(1463, 638, 148, 20);
		OPOD.add(opodTierCB14);
		
		JComboBox opodTierCB15 = new JComboBox(opod.getTierOpts());
		opodTierCB15.setBounds(1463, 671, 148, 20);
		OPOD.add(opodTierCB15);
		
		JComboBox opodStorageArrayCB1 = new JComboBox(opod.getStorageArrayOpts());
		opodStorageArrayCB1.setBounds(1621, 208, 214, 20);
		OPOD.add(opodStorageArrayCB1);
		
		JComboBox opodStorageArrayCB2 = new JComboBox(opod.getStorageArrayOpts());
		opodStorageArrayCB2.setBounds(1621, 241, 214, 20);
		OPOD.add(opodStorageArrayCB2);
		
		JComboBox opodStorageArrayCB3 = new JComboBox(opod.getStorageArrayOpts());
		opodStorageArrayCB3.setBounds(1621, 274, 214, 20);
		OPOD.add(opodStorageArrayCB3);
		
		JComboBox opodStorageArrayCB4 = new JComboBox(opod.getStorageArrayOpts());
		opodStorageArrayCB4.setBounds(1621, 307, 214, 20);
		OPOD.add(opodStorageArrayCB4);
		
		JComboBox opodStorageArrayCB5 = new JComboBox(opod.getStorageArrayOpts());
		opodStorageArrayCB5.setBounds(1621, 339, 214, 20);
		OPOD.add(opodStorageArrayCB5);
		
		JComboBox opodStorageArrayCB6 = new JComboBox(opod.getStorageArrayOpts());
		opodStorageArrayCB6.setBounds(1621, 372, 214, 20);
		OPOD.add(opodStorageArrayCB6);
		
		JComboBox opodStorageArrayCB7 = new JComboBox(opod.getStorageArrayOpts());
		opodStorageArrayCB7.setBounds(1621, 405, 214, 20);
		OPOD.add(opodStorageArrayCB7);
		
		JComboBox opodStorageArrayCB8 = new JComboBox(opod.getStorageArrayOpts());
		opodStorageArrayCB8.setBounds(1621, 438, 214, 20);
		OPOD.add(opodStorageArrayCB8);
		
		JComboBox opodStorageArrayCB9 = new JComboBox(opod.getStorageArrayOpts());
		opodStorageArrayCB9.setBounds(1621, 473, 214, 20);
		OPOD.add(opodStorageArrayCB9);
		
		JComboBox opodStorageArrayCB10 = new JComboBox(opod.getStorageArrayOpts());
		opodStorageArrayCB10.setBounds(1621, 506, 214, 20);
		OPOD.add(opodStorageArrayCB10);
		
		JComboBox opodStorageArrayCB11 = new JComboBox(opod.getStorageArrayOpts());
		opodStorageArrayCB11.setBounds(1621, 539, 214, 20);
		OPOD.add(opodStorageArrayCB11);
		
		JComboBox opodStorageArrayCB12 = new JComboBox(opod.getStorageArrayOpts());
		opodStorageArrayCB12.setBounds(1621, 572, 214, 20);
		OPOD.add(opodStorageArrayCB12);
		
		JComboBox opodStorageArrayCB13 = new JComboBox(opod.getStorageArrayOpts());
		opodStorageArrayCB13.setBounds(1621, 605, 214, 20);
		OPOD.add(opodStorageArrayCB13);
		
		JComboBox opodStorageArrayCB14 = new JComboBox(opod.getStorageArrayOpts());
		opodStorageArrayCB14.setBounds(1621, 638, 214, 20);
		OPOD.add(opodStorageArrayCB14);
		
		JComboBox opodStorageArrayCB15 = new JComboBox(opod.getStorageArrayOpts());
		opodStorageArrayCB15.setBounds(1621, 671, 214, 20);
		OPOD.add(opodStorageArrayCB15);
		
		JLabel lblOpodDiskGroupName = new JLabel("Disk Group Name");
		lblOpodDiskGroupName.setBounds(989, 181, 148, 14);
		OPOD.add(lblOpodDiskGroupName);
		
		JLabel lblOpodLuns = new JLabel("LUNS");
		lblOpodLuns.setBounds(1147, 181, 136, 14);
		OPOD.add(lblOpodLuns);
		
		JLabel lblOpodLunSize = new JLabel("LUN Size (GB)");
		lblOpodLunSize.setBounds(1305, 181, 148, 14);
		OPOD.add(lblOpodLunSize);
		
		JLabel lblOpodTier = new JLabel("Tier");
		lblOpodTier.setBounds(1463, 181, 148, 14);
		OPOD.add(lblOpodTier);
		
		JLabel lblOpodStorageArray = new JLabel("Storage Array");
		lblOpodStorageArray.setBounds(1621, 181, 130, 14);
		OPOD.add(lblOpodStorageArray);
		
		JLabel lblOpodNumDiskGroups = new JLabel("Total New ASM Disk Groups");
		lblOpodNumDiskGroups.setBounds(1121, 114, 174, 14);
		OPOD.add(lblOpodNumDiskGroups);
		
		opodDiskGroupNameTA15.setVisible(false);
		opodLunsTA15.setVisible(false);
		opodLunSizeTA15.setVisible(false);
		opodTierCB15.setVisible(false);
		opodStorageArrayCB15.setVisible(false);
		lblOpodDiskGroupName.setVisible(false);
		lblOpodLuns.setVisible(false);
		lblOpodLunSize.setVisible(false);
		lblOpodTier.setVisible(false);
		lblOpodStorageArray.setVisible(false);
		opodDiskGroupNameTA1.setVisible(false);
		opodLunsTA1.setVisible(false);
		opodLunSizeTA1.setVisible(false);
		opodTierCB1.setVisible(false);
		opodStorageArrayCB1.setVisible(false);
		opodDiskGroupNameTA2.setVisible(false);
		opodLunsTA2.setVisible(false);
		opodLunSizeTA2.setVisible(false);
		opodTierCB2.setVisible(false);
		opodStorageArrayCB2.setVisible(false);
		opodDiskGroupNameTA3.setVisible(false);
		opodLunsTA3.setVisible(false);
		opodLunSizeTA3.setVisible(false);
		opodTierCB3.setVisible(false);
		opodStorageArrayCB3.setVisible(false);
		opodDiskGroupNameTA4.setVisible(false);
		opodLunsTA4.setVisible(false);
		opodLunSizeTA4.setVisible(false);
		opodTierCB4.setVisible(false);
		opodStorageArrayCB4.setVisible(false);
		opodDiskGroupNameTA5.setVisible(false);
		opodLunsTA5.setVisible(false);
		opodLunSizeTA5.setVisible(false);
		opodTierCB5.setVisible(false);
		opodDiskGroupNameTA6.setVisible(false);
		opodLunsTA6.setVisible(false);
		opodLunSizeTA6.setVisible(false);
		opodTierCB6.setVisible(false);
		opodStorageArrayCB6.setVisible(false);
		opodStorageArrayCB5.setVisible(false);
		opodDiskGroupNameTA7.setVisible(false);
		opodLunsTA7.setVisible(false);
		opodLunSizeTA7.setVisible(false);
		opodTierCB7.setVisible(false);
		opodStorageArrayCB7.setVisible(false);
		opodDiskGroupNameTA8.setVisible(false);
		opodLunsTA8.setVisible(false);
		opodLunSizeTA8.setVisible(false);
		opodTierCB8.setVisible(false);
		opodStorageArrayCB8.setVisible(false);
		opodDiskGroupNameTA9.setVisible(false);
		opodLunsTA9.setVisible(false);
		opodLunSizeTA9.setVisible(false);
		opodTierCB9.setVisible(false);
		opodStorageArrayCB9.setVisible(false);
		opodDiskGroupNameTA10.setVisible(false);
		opodLunsTA10.setVisible(false);
		opodLunSizeTA10.setVisible(false);
		opodTierCB10.setVisible(false);
		opodStorageArrayCB10.setVisible(false);
		opodDiskGroupNameTA11.setVisible(false);
		opodLunsTA11.setVisible(false);
		opodLunSizeTA11.setVisible(false);
		opodTierCB11.setVisible(false);
		opodStorageArrayCB11.setVisible(false);
		opodDiskGroupNameTA12.setVisible(false);
		opodLunsTA12.setVisible(false);
		opodLunSizeTA12.setVisible(false);
		opodTierCB12.setVisible(false);
		opodStorageArrayCB12.setVisible(false);
		opodDiskGroupNameTA13.setVisible(false);
		opodLunsTA13.setVisible(false);
		opodLunSizeTA13.setVisible(false);
		opodTierCB13.setVisible(false);
		opodStorageArrayCB13.setVisible(false);
		opodDiskGroupNameTA14.setVisible(false);
		opodLunsTA14.setVisible(false);
		opodLunSizeTA14.setVisible(false);
		opodTierCB14.setVisible(false);
		opodStorageArrayCB14.setVisible(false);
		
		
		JComboBox opodNumDiskGroupsCB = new JComboBox(opod.numDiskGroupsOpts);
		opodNumDiskGroupsCB.setBounds(1305, 111, 261, 20);
		OPOD.add(opodNumDiskGroupsCB);
		opodNumDiskGroupsCB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(opodNumDiskGroupsCB.getSelectedIndex() == 0) {
					lblOpodDiskGroupName.setVisible(false);
					lblOpodLuns.setVisible(false);
					lblOpodLunSize.setVisible(false);
					lblOpodTier.setVisible(false);
					lblOpodStorageArray.setVisible(false);
				} else {
					lblOpodDiskGroupName.setVisible(true);
					lblOpodLuns.setVisible(true);
					lblOpodLunSize.setVisible(true);
					lblOpodTier.setVisible(true);
					lblOpodStorageArray.setVisible(true);
				}
				
				if(opodNumDiskGroupsCB.getSelectedIndex() >= 1) {
					opodDiskGroupNameTA1.setVisible(true);
					opodLunsTA1.setVisible(true);
					opodLunSizeTA1.setVisible(true);
					opodTierCB1.setVisible(true);
					opodStorageArrayCB1.setVisible(true);
					
				} else {
					opodDiskGroupNameTA1.setVisible(false);
					opodLunsTA1.setVisible(false);
					opodLunSizeTA1.setVisible(false);
					opodTierCB1.setVisible(false);
					opodStorageArrayCB1.setVisible(false);
				}
				
				if(opodNumDiskGroupsCB.getSelectedIndex() >= 2) {
					opodDiskGroupNameTA2.setVisible(true);
					opodLunsTA2.setVisible(true);
					opodLunSizeTA2.setVisible(true);
					opodTierCB2.setVisible(true);
					opodStorageArrayCB2.setVisible(true);
					
				} else {
					opodDiskGroupNameTA2.setVisible(false);
					opodLunsTA2.setVisible(false);
					opodLunSizeTA2.setVisible(false);
					opodTierCB2.setVisible(false);
					opodStorageArrayCB2.setVisible(false);
				}
				
				if(opodNumDiskGroupsCB.getSelectedIndex() >= 3) {
					opodDiskGroupNameTA3.setVisible(true);
					opodLunsTA3.setVisible(true);
					opodLunSizeTA3.setVisible(true);
					opodTierCB3.setVisible(true);
					opodStorageArrayCB3.setVisible(true);
					
				} else {
					opodDiskGroupNameTA3.setVisible(false);
					opodLunsTA3.setVisible(false);
					opodLunSizeTA3.setVisible(false);
					opodTierCB3.setVisible(false);
					opodStorageArrayCB3.setVisible(false);
				}
				
				if(opodNumDiskGroupsCB.getSelectedIndex() >= 4) {
					opodDiskGroupNameTA4.setVisible(true);
					opodLunsTA4.setVisible(true);
					opodLunSizeTA4.setVisible(true);
					opodTierCB4.setVisible(true);
					opodStorageArrayCB4.setVisible(true);
					
				} else {
					opodDiskGroupNameTA4.setVisible(false);
					opodLunsTA4.setVisible(false);
					opodLunSizeTA4.setVisible(false);
					opodTierCB4.setVisible(false);
					opodStorageArrayCB4.setVisible(false);
				}
				
				
				if(opodNumDiskGroupsCB.getSelectedIndex() >= 5) {
					opodDiskGroupNameTA5.setVisible(true);
					opodLunsTA5.setVisible(true);
					opodLunSizeTA5.setVisible(true);
					opodTierCB5.setVisible(true);
					opodStorageArrayCB5.setVisible(true);
					
				} else {
					opodDiskGroupNameTA5.setVisible(false);
					opodLunsTA5.setVisible(false);
					opodLunSizeTA5.setVisible(false);
					opodTierCB5.setVisible(false);
					opodStorageArrayCB5.setVisible(false);
				}
				
				if(opodNumDiskGroupsCB.getSelectedIndex() >= 6) {
					opodDiskGroupNameTA6.setVisible(true);
					opodLunsTA6.setVisible(true);
					opodLunSizeTA6.setVisible(true);
					opodTierCB6.setVisible(true);
					opodStorageArrayCB6.setVisible(true);
					
				} else {
					opodDiskGroupNameTA6.setVisible(false);
					opodLunsTA6.setVisible(false);
					opodLunSizeTA6.setVisible(false);
					opodTierCB6.setVisible(false);
					opodStorageArrayCB6.setVisible(false);
				}
				
				if(opodNumDiskGroupsCB.getSelectedIndex() >= 7) {
					opodDiskGroupNameTA7.setVisible(true);
					opodLunsTA7.setVisible(true);
					opodLunSizeTA7.setVisible(true);
					opodTierCB7.setVisible(true);
					opodStorageArrayCB7.setVisible(true);
					
				} else {
					opodDiskGroupNameTA7.setVisible(false);
					opodLunsTA7.setVisible(false);
					opodLunSizeTA7.setVisible(false);
					opodTierCB7.setVisible(false);
					opodStorageArrayCB7.setVisible(false);
				}
				
				if(opodNumDiskGroupsCB.getSelectedIndex() >= 8) {
					opodDiskGroupNameTA8.setVisible(true);
					opodLunsTA8.setVisible(true);
					opodLunSizeTA8.setVisible(true);
					opodTierCB8.setVisible(true);
					opodStorageArrayCB8.setVisible(true);
					
				} else {
					opodDiskGroupNameTA8.setVisible(false);
					opodLunsTA8.setVisible(false);
					opodLunSizeTA8.setVisible(false);
					opodTierCB8.setVisible(false);
					opodStorageArrayCB8.setVisible(false);
				}
				
				if(opodNumDiskGroupsCB.getSelectedIndex() >= 9) {
					opodDiskGroupNameTA9.setVisible(true);
					opodLunsTA9.setVisible(true);
					opodLunSizeTA9.setVisible(true);
					opodTierCB9.setVisible(true);
					opodStorageArrayCB9.setVisible(true);
					
				} else {
					opodDiskGroupNameTA9.setVisible(false);
					opodLunsTA9.setVisible(false);
					opodLunSizeTA9.setVisible(false);
					opodTierCB9.setVisible(false);
					opodStorageArrayCB9.setVisible(false);
				}
				
				if(opodNumDiskGroupsCB.getSelectedIndex() >= 10) {
					opodDiskGroupNameTA10.setVisible(true);
					opodLunsTA10.setVisible(true);
					opodLunSizeTA10.setVisible(true);
					opodTierCB10.setVisible(true);
					opodStorageArrayCB10.setVisible(true);
					
				} else {
					opodDiskGroupNameTA10.setVisible(false);
					opodLunsTA10.setVisible(false);
					opodLunSizeTA10.setVisible(false);
					opodTierCB10.setVisible(false);
					opodStorageArrayCB10.setVisible(false);
				}
				
				if(opodNumDiskGroupsCB.getSelectedIndex() >= 11) {
					opodDiskGroupNameTA11.setVisible(true);
					opodLunsTA11.setVisible(true);
					opodLunSizeTA11.setVisible(true);
					opodTierCB11.setVisible(true);
					opodStorageArrayCB11.setVisible(true);
					
				} else {
					opodDiskGroupNameTA11.setVisible(false);
					opodLunsTA11.setVisible(false);
					opodLunSizeTA11.setVisible(false);
					opodTierCB11.setVisible(false);
					opodStorageArrayCB11.setVisible(false);
				}
				
				if(opodNumDiskGroupsCB.getSelectedIndex() >= 12) {
					opodDiskGroupNameTA12.setVisible(true);
					opodLunsTA12.setVisible(true);
					opodLunSizeTA12.setVisible(true);
					opodTierCB12.setVisible(true);
					opodStorageArrayCB12.setVisible(true);
					
				} else {
					opodDiskGroupNameTA12.setVisible(false);
					opodLunsTA12.setVisible(false);
					opodLunSizeTA12.setVisible(false);
					opodTierCB12.setVisible(false);
					opodStorageArrayCB12.setVisible(false);
				}
				
				if(opodNumDiskGroupsCB.getSelectedIndex() >= 13) {
					opodDiskGroupNameTA13.setVisible(true);
					opodLunsTA13.setVisible(true);
					opodLunSizeTA13.setVisible(true);
					opodTierCB13.setVisible(true);
					opodStorageArrayCB13.setVisible(true);
					
				} else {
					opodDiskGroupNameTA13.setVisible(false);
					opodLunsTA13.setVisible(false);
					opodLunSizeTA13.setVisible(false);
					opodTierCB13.setVisible(false);
					opodStorageArrayCB13.setVisible(false);
				}
				
				if(opodNumDiskGroupsCB.getSelectedIndex() >= 14) {
					opodDiskGroupNameTA14.setVisible(true);
					opodLunsTA14.setVisible(true);
					opodLunSizeTA14.setVisible(true);
					opodTierCB14.setVisible(true);
					opodStorageArrayCB14.setVisible(true);
					
				} else {
					opodDiskGroupNameTA14.setVisible(false);
					opodLunsTA14.setVisible(false);
					opodLunSizeTA14.setVisible(false);
					opodTierCB14.setVisible(false);
					opodStorageArrayCB14.setVisible(false);
				}
				
				if(opodNumDiskGroupsCB.getSelectedIndex() >= 15) {
					opodDiskGroupNameTA15.setVisible(true);
					opodLunsTA15.setVisible(true);
					opodLunSizeTA15.setVisible(true);
					opodTierCB15.setVisible(true);
					opodStorageArrayCB15.setVisible(true);
					
				} else {
					opodDiskGroupNameTA15.setVisible(false);
					opodLunsTA15.setVisible(false);
					opodLunSizeTA15.setVisible(false);
					opodTierCB15.setVisible(false);
					opodStorageArrayCB15.setVisible(false);
				}
			}
			
		});	
		
		
		

		JTabbedPane BladeServerPages = new JTabbedPane(JTabbedPane.TOP);
		BladeServerPages.setBounds(10, 11, 978, 854);
		BladeServers.add(BladeServerPages);

		JPanel panel = new JPanel();
		BladeServerPages.addTab("New tab", null, panel, null);

		JPanel panel_1 = new JPanel();
		BladeServerPages.addTab("New tab", null, panel_1, null);

		JPanel panel_2 = new JPanel();
		BladeServerPages.addTab("New tab", null, panel_2, null);

		JPanel panel_3 = new JPanel();
		BladeServerPages.addTab("New tab", null, panel_3, null);

		JPanel panel_4 = new JPanel();
		BladeServerPages.addTab("New tab", null, panel_4, null);

		JPanel panel_5 = new JPanel();
		BladeServerPages.addTab("New tab", null, panel_5, null);

		JPanel panel_6 = new JPanel();
		BladeServerPages.addTab("New tab", null, panel_6, null);

		// Adds the selection of choices after user selects Windows
		windowsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object[] options = { "Yes", "No", "Cancel" };
				int n = JOptionPane.showOptionDialog(frame, "Would you like to save it", null,
						JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[2]);

			}
		});

		// Adds the selection of choices after user selects Linux
		unixButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object[] options = { "Yes", "No", "Cancel" };
				int n = JOptionPane.showOptionDialog(frame, "Would you like to save it", null,
						JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[2]);
				if (n == 1) {
					UnixHWConfig.setVisible(true);
					InitView.setVisible(false);
				}
			}
		});
		InitView.setVisible(true);
	}

	/**
	 * Private helper method responsible for opening url
	 * 
	 * @param uri
	 *            The url to use
	 */
	private static void open(URI uri) {
		if (Desktop.isDesktopSupported()) {
			try {
				Desktop.getDesktop().browse(uri);
			} catch (IOException e) {
				/* TODO: error handling */ }
		} else {
			/* TODO: error handling */ }
	}
}
