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

		JComboBox comboBox = new JComboBox(unixConfig.getModelNames());
		comboBox.setBounds(10, 126, 429, 20);
		UnixHWConfig.add(comboBox);

		JButton btnNewButton = new JButton("Proceed with the selected option");
		btnNewButton.setBounds(485, 125, 279, 23);
		UnixHWConfig.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBox.getSelectedItem().toString().startsWith("Blade")) {
					UnixHWConfig.setVisible(false);
					BladeServers.setVisible(true);
				} else if (comboBox.getSelectedItem().toString().startsWith("Physical")) {
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
		JButton btnLinkToThe = new JButton("Link to the module selections and options");
		btnLinkToThe.setBounds(10, 375, 429, 85);
		btnLinkToThe.setToolTipText(uriOne.toString());
		btnLinkToThe.addActionListener(new OpenUrlAction1());
		UnixHWConfig.add(btnLinkToThe);
		
		JPanel Hadoop = new JPanel();
		frame.getContentPane().add(Hadoop, "name_166765278662121");
		Hadoop.setLayout(null);
		
		JPanel OPOD = new JPanel();
		frame.getContentPane().add(OPOD, "name_166927836447044");
		OPOD.setLayout(null);
		
		JComboBox comboBox_18 = new JComboBox(unixConfig.getStandardHostingOps());
		comboBox_18.setBounds(10, 267, 429, 20);
		UnixHWConfig.add(comboBox_18);
		JPanel TIBCO = new JPanel();
		frame.getContentPane().add(TIBCO, "name_166686193052355");
		TIBCO.setLayout(null);
		JButton button_1 = new JButton("Proceed with the selected option");
		button_1.setBounds(485, 266, 279, 23);
		UnixHWConfig.add(button_1);
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBox_18.getSelectedItem().toString().equals("TIBCO")) {
					UnixHWConfig.setVisible(false);
					TIBCO.setVisible(true);
				}
				if (comboBox_18.getSelectedItem().toString().equals("Hadoop")) {
					UnixHWConfig.setVisible(false);
					Hadoop.setVisible(true);
				}
				if (comboBox_18.getSelectedItem().toString().equals("OPOD")) {
					UnixHWConfig.setVisible(false);
					OPOD.setVisible(true);
				}
			}
		});

		JPanel AltServer = new JPanel();
		frame.getContentPane().add(AltServer, "name_240502931499002");

		JLabel lblNewLabel_2 = new JLabel("TIBCO");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel_2.setBounds(10, 11, 978, 44);
		TIBCO.add(lblNewLabel_2);

		JSeparator separator_7 = new JSeparator();
		separator_7.setBounds(0, 55, 998, 2);
		TIBCO.add(separator_7);

		JLabel label = new JLabel("Required UNIQUE fields to be filled in prior to selecting the SHS Option");
		label.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label.setBounds(10, 69, 479, 26);
		TIBCO.add(label);

		JLabel label_1 = new JLabel("How many Unix Server(s) total are planned?");
		label_1.setBounds(10, 119, 261, 14);
		TIBCO.add(label_1);

		JComboBox comboBox_8 = new JComboBox(tibco.getNumServers());
		comboBox_8.setBounds(353, 116, 241, 20);
		TIBCO.add(comboBox_8);

		JComboBox comboBox_9 = new JComboBox(tibco.getEnvironmentOpts());
		comboBox_9.setBounds(263, 162, 226, 20);
		TIBCO.add(comboBox_9);

		JComboBox comboBox_10 = new JComboBox(tibco.getBusinessUnitsOpts());
		comboBox_10.setBounds(263, 207, 226, 20);
		TIBCO.add(comboBox_10);

		
		JComboBox comboBox_19 = new JComboBox(tibco.getSecurityZoneHudsonOpts());
		comboBox_19.setBounds(263, 350, 226, 20);
		TIBCO.add(comboBox_19);

		JComboBox comboBox_20 = new JComboBox(tibco.getSecurityZoneRochelleOpts());
		comboBox_20.setVisible(false);
		comboBox_20.setBounds(263, 350, 226, 20);
		TIBCO.add(comboBox_20);
		
		JComboBox comboBox_13 = new JComboBox(tibco.getWingLocationOpts());
		comboBox_13.setVisible(false);
		comboBox_13.setBounds(750, 350, 226, 20);
		TIBCO.add(comboBox_13);
		
		JLabel lblWingLocation = new JLabel("Wing Location in the DC:");
		lblWingLocation.setVisible(false);
		lblWingLocation.setBounds(548, 353, 179, 14);
		TIBCO.add(lblWingLocation);
		
		JComboBox comboBox_11 = new JComboBox(tibco.getDataCenterOpts());
		comboBox_11.setBounds(263, 255, 226, 20);
		TIBCO.add(comboBox_11);
		
		JComboBox comboBox_22 = new JComboBox(tibco.getSecurityZoneZeroOpts());
		comboBox_22.setBounds(263, 350, 226, 20);
		TIBCO.add(comboBox_22);
		
		comboBox_11.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (comboBox_11.getSelectedItem().toString().equals("Hudson")) {
					comboBox_19.setVisible(true);
					comboBox_20.setVisible(false);
					comboBox_13.setVisible(true);
					comboBox_22.setVisible(false);
					lblWingLocation.setVisible(true);
				}
				else if (comboBox_11.getSelectedItem().toString().equals("Rochelle")) {
					comboBox_19.setVisible(false);
					comboBox_20.setVisible(true);
					comboBox_13.setVisible(false);
					comboBox_22.setVisible(false);
					lblWingLocation.setVisible(false);
				}
				else {
					comboBox_13.setVisible(false);
					lblWingLocation.setVisible(false);
					comboBox_19.setVisible(false);
					comboBox_22.setVisible(true);
					comboBox_20.setVisible(false);
				}
				
			}
			
		});		
		
		JComboBox comboBox_12 = new JComboBox(tibco.getEnterpriseControllerOpts());
		comboBox_12.setBounds(263, 302, 226, 20);
		TIBCO.add(comboBox_12);

		JLabel label_2 = new JLabel("Environment Selection:");
		label_2.setBounds(10, 165, 161, 14);
		TIBCO.add(label_2);

		JLabel label_3 = new JLabel("Business Unit:");
		label_3.setBounds(10, 210, 112, 14);
		TIBCO.add(label_3);

		JLabel label_4 = new JLabel("Data Center:");
		label_4.setBounds(10, 258, 76, 14);
		TIBCO.add(label_4);

		JLabel label_5 = new JLabel("Enterprise Controller:");
		label_5.setBounds(10, 305, 112, 14);
		TIBCO.add(label_5);

		JLabel label_6 = new JLabel("Security Zone:");
		label_6.setBounds(10, 353, 112, 14);
		TIBCO.add(label_6);

		JComboBox comboBox_14 = new JComboBox(tibco.getTibcoBehaviorOpts());
		comboBox_14.setBounds(263, 400, 226, 20);
		TIBCO.add(comboBox_14);

		JComboBox comboBox_15 = new JComboBox(tibco.getTibcoModuleOpts());
		comboBox_15.setBounds(263, 449, 226, 20);
		TIBCO.add(comboBox_15);

		JComboBox comboBox_16 = new JComboBox(tibco.getMaintenanceWindowOpts());
		comboBox_16.setBounds(263, 495, 226, 20);
		TIBCO.add(comboBox_16);

		JLabel lblTibcoBehavior = new JLabel("Tibco Behavior:");
		lblTibcoBehavior.setBounds(10, 403, 93, 14);
		TIBCO.add(lblTibcoBehavior);

		JLabel lblTibcoModul = new JLabel("Tibco Module:");
		lblTibcoModul.setBounds(10, 452, 112, 14);
		TIBCO.add(lblTibcoModul);

		JLabel lblMaintenanceWindow = new JLabel("Maintenance Window:");
		lblMaintenanceWindow.setBounds(10, 498, 140, 14);
		TIBCO.add(lblMaintenanceWindow);

		JSeparator separator_8 = new JSeparator();
		separator_8.setBounds(0, 544, 998, 2);
		TIBCO.add(separator_8);

		JLabel label_7 = new JLabel("Please select one of the following options");
		label_7.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_7.setBounds(10, 557, 479, 37);
		TIBCO.add(label_7);

		JComboBox comboBox_17 = new JComboBox(tibco.getTibcoOpts());
		comboBox_17.setBounds(20, 607, 418, 20);
		TIBCO.add(comboBox_17);

		JButton button = new JButton("Proceed with the selected option");
		button.setBounds(448, 605, 241, 23);
		TIBCO.add(button);
		
		

		JLabel lblHadoop = new JLabel("Hadoop");
		lblHadoop.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblHadoop.setBounds(10, 11, 978, 43);
		Hadoop.add(lblHadoop);

		JSeparator separator_5 = new JSeparator();
		separator_5.setBounds(0, 65, 998, 2);
		Hadoop.add(separator_5);

		JLabel lblRequiredUniqueFields = new JLabel(
				"Required UNIQUE fields to be filled in prior to selecting the SHS Option");
		lblRequiredUniqueFields.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblRequiredUniqueFields.setBounds(10, 78, 479, 26);
		Hadoop.add(lblRequiredUniqueFields);

		JLabel lblHowManyUnic = new JLabel("How many Unix Server(s) total are planned?");
		lblHowManyUnic.setBounds(10, 125, 259, 14);
		Hadoop.add(lblHowManyUnic);

		JComboBox comboBox_1 = new JComboBox(hadoop.getNumServers());
		comboBox_1.setBounds(314, 122, 226, 20);
		Hadoop.add(comboBox_1);

		JLabel lblEnvironmentSelection = new JLabel("Environment Selection:");
		lblEnvironmentSelection.setBounds(10, 164, 161, 14);
		Hadoop.add(lblEnvironmentSelection);

		JComboBox comboBox_2 = new JComboBox(hadoop.getEnvironmentOpts());
		comboBox_2.setBounds(246, 161, 226, 20);
		Hadoop.add(comboBox_2);

		JLabel lblBusinessUnit = new JLabel("Business Unit:");
		lblBusinessUnit.setBounds(10, 207, 112, 14);
		Hadoop.add(lblBusinessUnit);

		JComboBox comboBox_3 = new JComboBox(hadoop.getBusinessUnitsOpts());
		comboBox_3.setBounds(246, 204, 226, 20);
		Hadoop.add(comboBox_3);

		JLabel lblNewLabel = new JLabel("Data Center:");
		lblNewLabel.setBounds(10, 248, 76, 14);
		Hadoop.add(lblNewLabel);
		
		JComboBox comboBox_6 = new JComboBox(hadoop.getSecurityZoneZeroOpts());
		comboBox_6.setVisible(true);
		comboBox_6.setBounds(246, 331, 226, 20);
		Hadoop.add(comboBox_6);

		JComboBox comboBox_21 = new JComboBox(hadoop.getSecurityZoneHudsonOpts());
		comboBox_21.setVisible(false);
		comboBox_21.setBounds(246, 331, 226, 20);
		Hadoop.add(comboBox_21);
		
		JComboBox comboBox_23 = new JComboBox(hadoop.getSecurityZoneRochelleOpts());
		comboBox_23.setVisible(false);
		comboBox_23.setBounds(246, 331, 226, 20);
		Hadoop.add(comboBox_23);
		

		JLabel lblNewLabel_3 = new JLabel("Wing location in the DC:");
		lblNewLabel_3.setVisible(false);
		lblNewLabel_3.setBounds(602, 248, 161, 14);
		Hadoop.add(lblNewLabel_3);
		
		JComboBox comboBox_24 = new JComboBox(hadoop.getWingLocationOpts());
		comboBox_24.setVisible(false);
		comboBox_24.setBounds(762, 245, 226, 20);
		Hadoop.add(comboBox_24);
		
		JComboBox comboBox_4 = new JComboBox(hadoop.getDataCenterOpts());
		comboBox_4.setBounds(246, 245, 226, 20);
		Hadoop.add(comboBox_4);
		comboBox_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboBox_4.getSelectedItem().toString().equals("Hudson")) {
					comboBox_21.setVisible(true);
					comboBox_23.setVisible(false);
					comboBox_6.setVisible(false);
					comboBox_24.setVisible(true);
					lblNewLabel_3.setVisible(true);
				}
				else if(comboBox_4.getSelectedItem().toString().equals("Rochelle")) {
					comboBox_21.setVisible(false);
					comboBox_23.setVisible(true);
					comboBox_6.setVisible(false);
					comboBox_24.setVisible(false);
					lblNewLabel_3.setVisible(false);
				}
				else {
					comboBox_21.setVisible(false);
					comboBox_23.setVisible(false);
					comboBox_6.setVisible(true);
					comboBox_24.setVisible(false);
					lblNewLabel_3.setVisible(false);
				}
			}
		});

		JLabel lblNewLabel_1 = new JLabel("Enterprise Controller:");
		lblNewLabel_1.setBounds(10, 292, 192, 14);
		Hadoop.add(lblNewLabel_1);

		JComboBox comboBox_5 = new JComboBox(hadoop.getEnterpriseControllerOpts());
		comboBox_5.setBounds(246, 289, 226, 20);
		Hadoop.add(comboBox_5);

		JLabel lblSecurityZone = new JLabel("Security Zone:");
		lblSecurityZone.setBounds(10, 334, 112, 14);
		Hadoop.add(lblSecurityZone);


		JLabel lblPleaseSelectOne = new JLabel("Please select one of the following options");
		lblPleaseSelectOne.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPleaseSelectOne.setBounds(10, 392, 479, 37);
		Hadoop.add(lblPleaseSelectOne);

		JSeparator separator_6 = new JSeparator();
		separator_6.setBounds(0, 379, 998, 2);
		Hadoop.add(separator_6);

		JComboBox comboBox_7 = new JComboBox(hadoop.hadoopOpts());
		comboBox_7.setBounds(20, 440, 418, 20);
		Hadoop.add(comboBox_7);

		JButton btnNewButton_2 = new JButton("Proceed with the selected option");
		btnNewButton_2.setBounds(448, 439, 241, 23);
		Hadoop.add(btnNewButton_2);
		
		
		JLabel lblOpod = new JLabel("OPOD");
		lblOpod.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblOpod.setBounds(10, 11, 978, 43);
		OPOD.add(lblOpod);
		
		JSeparator separator_9 = new JSeparator();
		separator_9.setBounds(0, 52, 998, 2);
		OPOD.add(separator_9);
		
		JLabel label_8 = new JLabel("Required UNIQUE fields to be filled in prior to selecting the SHS Option");
		label_8.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_8.setBounds(10, 65, 479, 26);
		OPOD.add(label_8);
		
		JLabel label_9 = new JLabel("How many Unix Server(s) total are planned?");
		label_9.setBounds(10, 114, 261, 14);
		OPOD.add(label_9);
		
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setBounds(627, 206, 148, 22);
		OPOD.add(textArea_1);
		
		JComboBox comboBox_51 = new JComboBox();
		comboBox_51.setBounds(510, 587, 161, 20);
		OPOD.add(comboBox_51);
		
		JComboBox comboBox_52 = new JComboBox();
		comboBox_52.setVisible(false);
		comboBox_52.setBounds(510, 622, 161, 20);
		OPOD.add(comboBox_52);
		
		JComboBox comboBox_53 = new JComboBox();
		comboBox_53.setVisible(false);
		comboBox_53.setBounds(510, 655, 161, 20);
		OPOD.add(comboBox_53);
		
		JComboBox comboBox_54 = new JComboBox();
		comboBox_54.setVisible(false);
		comboBox_54.setBounds(510, 688, 161, 20);
		OPOD.add(comboBox_54);
		
		JComboBox comboBox_55 = new JComboBox();
		comboBox_55.setVisible(false);
		comboBox_55.setBounds(510, 721, 161, 20);
		OPOD.add(comboBox_55);
		
		JComboBox comboBox_56 = new JComboBox();
		comboBox_56.setVisible(false);
		comboBox_56.setBounds(510, 754, 161, 20);
		OPOD.add(comboBox_56);
		
		JComboBox comboBox_57 = new JComboBox();
		comboBox_57.setVisible(false);
		comboBox_57.setBounds(510, 787, 161, 20);
		OPOD.add(comboBox_57);
		
		JComboBox comboBox_58 = new JComboBox();
		comboBox_58.setVisible(false);
		comboBox_58.setBounds(510, 820, 161, 20);
		OPOD.add(comboBox_58);
		
		JTextArea textArea_2 = new JTextArea();
		textArea_2.setBounds(785, 206, 148, 22);
		OPOD.add(textArea_2);
		//primaryOpts[0] = textArea_2.getText();
		
		JTextArea textArea_4 = new JTextArea();
		textArea_4.setVisible(false);
		textArea_4.setBounds(785, 239, 148, 22);
		OPOD.add(textArea_4);
		
		JTextArea textArea_6 = new JTextArea();
		textArea_6.setVisible(false);
		textArea_6.setBounds(785, 272, 148, 22);
		OPOD.add(textArea_6);
		
		JTextArea textArea_8 = new JTextArea();
		textArea_8.setVisible(false);
		textArea_8.setBounds(785, 305, 148, 22);
		OPOD.add(textArea_8);
		
		JTextArea textArea_10 = new JTextArea();
		textArea_10.setVisible(false);
		textArea_10.setBounds(785, 337, 148, 22);
		OPOD.add(textArea_10);
		
		JTextArea textArea_12 = new JTextArea();
		textArea_12.setVisible(false);
		textArea_12.setBounds(785, 370, 148, 22);
		OPOD.add(textArea_12);
		
		JTextArea textArea_14 = new JTextArea();
		textArea_14.setVisible(false);
		textArea_14.setBounds(785, 403, 148, 22);
		OPOD.add(textArea_14);
		
		JTextArea textArea_16 = new JTextArea();
		textArea_16.setBounds(785, 436, 148, 22);
		textArea_16.setVisible(false);
		OPOD.add(textArea_16);
		
		JTextArea textArea_18 = new JTextArea();
		textArea_18.setBounds(785, 471, 148, 22);
		textArea_18.setVisible(false);
		OPOD.add(textArea_18);
		
		JTextArea textArea_20 = new JTextArea();
		textArea_20.setBounds(785, 504, 148, 22);
		textArea_20.setVisible(false);
		OPOD.add(textArea_20);

		textArea_2.addFocusListener(new FocusListener() {
	
			@Override
			public void focusLost(FocusEvent arg0) {
				comboBox_51.removeAllItems();
				comboBox_52.removeAllItems();
				comboBox_53.removeAllItems();
				comboBox_54.removeAllItems();
				comboBox_55.removeAllItems();
				comboBox_56.removeAllItems();
				comboBox_57.removeAllItems();
				comboBox_58.removeAllItems();
				comboBox_51.addItem(textArea_2.getText());
				comboBox_52.addItem(textArea_2.getText());
				comboBox_53.addItem(textArea_2.getText());
				comboBox_54.addItem(textArea_2.getText());
				comboBox_55.addItem(textArea_2.getText());
				comboBox_56.addItem(textArea_2.getText());
				comboBox_57.addItem(textArea_2.getText());
				comboBox_58.addItem(textArea_2.getText());
				comboBox_51.addItem(textArea_4.getText());
				comboBox_52.addItem(textArea_4.getText());
				comboBox_53.addItem(textArea_4.getText());
				comboBox_54.addItem(textArea_4.getText());
				comboBox_55.addItem(textArea_4.getText());
				comboBox_56.addItem(textArea_4.getText());
				comboBox_57.addItem(textArea_4.getText());
				comboBox_58.addItem(textArea_4.getText());
				comboBox_51.addItem(textArea_6.getText());
				comboBox_52.addItem(textArea_6.getText());
				comboBox_53.addItem(textArea_6.getText());
				comboBox_54.addItem(textArea_6.getText());
				comboBox_55.addItem(textArea_6.getText());
				comboBox_56.addItem(textArea_6.getText());
				comboBox_57.addItem(textArea_6.getText());
				comboBox_58.addItem(textArea_6.getText());
				comboBox_51.addItem(textArea_8.getText());
				comboBox_52.addItem(textArea_8.getText());
				comboBox_53.addItem(textArea_8.getText());
				comboBox_54.addItem(textArea_8.getText());
				comboBox_55.addItem(textArea_8.getText());
				comboBox_56.addItem(textArea_8.getText());
				comboBox_57.addItem(textArea_8.getText());
				comboBox_58.addItem(textArea_8.getText());
				comboBox_51.addItem(textArea_10.getText());
				comboBox_52.addItem(textArea_10.getText());
				comboBox_53.addItem(textArea_10.getText());
				comboBox_54.addItem(textArea_10.getText());
				comboBox_55.addItem(textArea_10.getText());
				comboBox_56.addItem(textArea_10.getText());
				comboBox_57.addItem(textArea_10.getText());
				comboBox_58.addItem(textArea_10.getText());
				comboBox_51.addItem(textArea_12.getText());
				comboBox_52.addItem(textArea_12.getText());
				comboBox_53.addItem(textArea_12.getText());
				comboBox_54.addItem(textArea_12.getText());
				comboBox_55.addItem(textArea_12.getText());
				comboBox_56.addItem(textArea_12.getText());
				comboBox_57.addItem(textArea_12.getText());
				comboBox_58.addItem(textArea_12.getText());
				comboBox_51.addItem(textArea_14.getText());
				comboBox_52.addItem(textArea_14.getText());
				comboBox_53.addItem(textArea_14.getText());
				comboBox_54.addItem(textArea_14.getText());
				comboBox_55.addItem(textArea_14.getText());
				comboBox_56.addItem(textArea_14.getText());
				comboBox_57.addItem(textArea_14.getText());
				comboBox_58.addItem(textArea_14.getText());
				comboBox_51.addItem(textArea_16.getText());
				comboBox_52.addItem(textArea_16.getText());
				comboBox_53.addItem(textArea_16.getText());
				comboBox_54.addItem(textArea_16.getText());
				comboBox_55.addItem(textArea_16.getText());
				comboBox_56.addItem(textArea_16.getText());
				comboBox_57.addItem(textArea_16.getText());
				comboBox_58.addItem(textArea_16.getText());
				comboBox_51.addItem(textArea_18.getText());
				comboBox_52.addItem(textArea_18.getText());
				comboBox_53.addItem(textArea_18.getText());
				comboBox_54.addItem(textArea_18.getText());
				comboBox_55.addItem(textArea_18.getText());
				comboBox_56.addItem(textArea_18.getText());
				comboBox_57.addItem(textArea_18.getText());
				comboBox_58.addItem(textArea_18.getText());
				comboBox_51.addItem(textArea_20.getText());
				comboBox_52.addItem(textArea_20.getText());
				comboBox_53.addItem(textArea_20.getText());
				comboBox_54.addItem(textArea_20.getText());
				comboBox_55.addItem(textArea_20.getText());
				comboBox_56.addItem(textArea_20.getText());
				comboBox_57.addItem(textArea_20.getText());
				comboBox_58.addItem(textArea_20.getText());
			}

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		JTextArea textArea_3 = new JTextArea();
		textArea_3.setVisible(false);
		textArea_3.setBounds(627, 239, 148, 22);
		OPOD.add(textArea_3);
		
		JTextArea textArea_5 = new JTextArea();
		textArea_5.setVisible(false);
		textArea_5.setBounds(627, 272, 148, 22);
		OPOD.add(textArea_5);
		

		
		JTextArea textArea_7 = new JTextArea();
		textArea_7.setVisible(false);
		textArea_7.setBounds(627, 305, 148, 22);
		OPOD.add(textArea_7);
		

		
		JTextArea textArea_9 = new JTextArea();
		textArea_9.setVisible(false);
		textArea_9.setBounds(627, 337, 148, 22);
		OPOD.add(textArea_9);
		

		
		JTextArea textArea_11 = new JTextArea();
		textArea_11.setVisible(false);
		textArea_11.setBounds(627, 370, 148, 22);
		OPOD.add(textArea_11);
		

		
		JTextArea textArea_13 = new JTextArea();
		textArea_13.setVisible(false);
		textArea_13.setBounds(627, 403, 148, 22);
		OPOD.add(textArea_13);
		

		
		JTextArea textArea_15 = new JTextArea();
		textArea_15.setVisible(false);
		textArea_15.setBounds(627, 436, 148, 22);
		OPOD.add(textArea_15);
		

		
		JTextArea textArea_17 = new JTextArea();
		textArea_17.setBounds(627, 471, 148, 22);
		textArea_17.setVisible(false);
		OPOD.add(textArea_17);
		

		
		JTextArea textArea_19 = new JTextArea();
		textArea_19.setBounds(627, 504, 148, 22);
		textArea_19.setVisible(false);
		OPOD.add(textArea_19);
		
		String[] relate = {"","M5000"};
		JComboBox comboBox_43 = new JComboBox(relate);
		comboBox_43.setBounds(339, 587, 161, 20);
		OPOD.add(comboBox_43);
		
		JComboBox comboBox_44 = new JComboBox(relate);
		comboBox_44.setVisible(false);
		comboBox_44.setBounds(339, 622, 161, 20);
		OPOD.add(comboBox_44);
		
		JComboBox comboBox_45 = new JComboBox(relate);
		comboBox_45.setVisible(false);
		comboBox_45.setBounds(339, 655, 161, 20);
		OPOD.add(comboBox_45);
		
		JComboBox comboBox_46 = new JComboBox(relate);
		comboBox_46.setVisible(false);
		comboBox_46.setBounds(339, 688, 161, 20);
		OPOD.add(comboBox_46);
		
		JComboBox comboBox_47 = new JComboBox(relate);
		comboBox_47.setVisible(false);
		comboBox_47.setBounds(339, 721, 161, 20);
		OPOD.add(comboBox_47);
		
		JComboBox comboBox_48 = new JComboBox(relate);
		comboBox_48.setVisible(false);
		comboBox_48.setBounds(339, 754, 161, 20);
		OPOD.add(comboBox_48);
		
		JComboBox comboBox_49 = new JComboBox(relate);
		comboBox_49.setVisible(false);
		comboBox_49.setBounds(339, 787, 161, 20);
		OPOD.add(comboBox_49);
		
		JComboBox comboBox_50 = new JComboBox(relate);
		comboBox_50.setVisible(false);
		comboBox_50.setBounds(339, 820, 161, 20);
		OPOD.add(comboBox_50);
		

		textArea_1.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent arg0) {
				comboBox_43.removeAllItems();
				comboBox_44.removeAllItems();
				comboBox_45.removeAllItems();
				comboBox_46.removeAllItems();
				comboBox_47.removeAllItems();
				comboBox_48.removeAllItems();
				comboBox_49.removeAllItems();
				comboBox_50.removeAllItems();
				comboBox_43.addItem(textArea_1.getText());
				comboBox_44.addItem(textArea_1.getText());
				comboBox_45.addItem(textArea_1.getText());
				comboBox_46.addItem(textArea_1.getText());
				comboBox_47.addItem(textArea_1.getText());
				comboBox_48.addItem(textArea_1.getText());
				comboBox_49.addItem(textArea_1.getText());
				comboBox_50.addItem(textArea_1.getText());
				comboBox_43.addItem(textArea_3.getText());
				comboBox_44.addItem(textArea_3.getText());
				comboBox_45.addItem(textArea_3.getText());
				comboBox_46.addItem(textArea_3.getText());
				comboBox_47.addItem(textArea_3.getText());
				comboBox_48.addItem(textArea_3.getText());
				comboBox_49.addItem(textArea_3.getText());
				comboBox_50.addItem(textArea_3.getText());
				comboBox_43.addItem(textArea_5.getText());
				comboBox_44.addItem(textArea_5.getText());
				comboBox_45.addItem(textArea_5.getText());
				comboBox_46.addItem(textArea_5.getText());
				comboBox_47.addItem(textArea_5.getText());
				comboBox_48.addItem(textArea_5.getText());
				comboBox_49.addItem(textArea_5.getText());
				comboBox_50.addItem(textArea_5.getText());
				comboBox_43.addItem(textArea_7.getText());
				comboBox_44.addItem(textArea_7.getText());
				comboBox_45.addItem(textArea_7.getText());
				comboBox_46.addItem(textArea_7.getText());
				comboBox_47.addItem(textArea_7.getText());
				comboBox_48.addItem(textArea_7.getText());
				comboBox_49.addItem(textArea_7.getText());
				comboBox_50.addItem(textArea_7.getText());
				comboBox_43.addItem(textArea_9.getText());
				comboBox_44.addItem(textArea_9.getText());
				comboBox_45.addItem(textArea_9.getText());
				comboBox_46.addItem(textArea_9.getText());
				comboBox_47.addItem(textArea_9.getText());
				comboBox_48.addItem(textArea_9.getText());
				comboBox_49.addItem(textArea_9.getText());
				comboBox_50.addItem(textArea_9.getText());
				comboBox_43.addItem(textArea_11.getText());
				comboBox_44.addItem(textArea_11.getText());
				comboBox_45.addItem(textArea_11.getText());
				comboBox_46.addItem(textArea_11.getText());
				comboBox_47.addItem(textArea_11.getText());
				comboBox_48.addItem(textArea_11.getText());
				comboBox_49.addItem(textArea_11.getText());
				comboBox_50.addItem(textArea_11.getText());
				comboBox_43.addItem(textArea_13.getText());
				comboBox_44.addItem(textArea_13.getText());
				comboBox_45.addItem(textArea_13.getText());
				comboBox_46.addItem(textArea_13.getText());
				comboBox_47.addItem(textArea_13.getText());
				comboBox_48.addItem(textArea_13.getText());
				comboBox_49.addItem(textArea_13.getText());
				comboBox_50.addItem(textArea_13.getText());
				comboBox_43.addItem(textArea_15.getText());
				comboBox_44.addItem(textArea_15.getText());
				comboBox_45.addItem(textArea_15.getText());
				comboBox_46.addItem(textArea_15.getText());
				comboBox_47.addItem(textArea_15.getText());
				comboBox_48.addItem(textArea_15.getText());
				comboBox_49.addItem(textArea_15.getText());
				comboBox_50.addItem(textArea_15.getText());
				comboBox_43.addItem(textArea_17.getText());
				comboBox_44.addItem(textArea_17.getText());
				comboBox_45.addItem(textArea_17.getText());
				comboBox_46.addItem(textArea_17.getText());
				comboBox_47.addItem(textArea_17.getText());
				comboBox_48.addItem(textArea_17.getText());
				comboBox_49.addItem(textArea_17.getText());
				comboBox_50.addItem(textArea_17.getText());
				comboBox_43.addItem(textArea_19.getText());
				comboBox_44.addItem(textArea_19.getText());
				comboBox_45.addItem(textArea_19.getText());
				comboBox_46.addItem(textArea_19.getText());
				comboBox_47.addItem(textArea_19.getText());
				comboBox_48.addItem(textArea_19.getText());
				comboBox_49.addItem(textArea_19.getText());
				comboBox_50.addItem(textArea_19.getText());
				comboBox_43.addItem("M5000");
				comboBox_44.addItem("M5000");
				comboBox_45.addItem("M5000");
				comboBox_46.addItem("M5000");
				comboBox_47.addItem("M5000");
				comboBox_48.addItem("M5000");
				comboBox_49.addItem("M5000");
				comboBox_50.addItem("M5000");
			}

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		textArea_3.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent arg0) {
				comboBox_43.removeAllItems();
				comboBox_44.removeAllItems();
				comboBox_45.removeAllItems();
				comboBox_46.removeAllItems();
				comboBox_47.removeAllItems();
				comboBox_48.removeAllItems();
				comboBox_49.removeAllItems();
				comboBox_50.removeAllItems();
				comboBox_43.addItem(textArea_1.getText());
				comboBox_44.addItem(textArea_1.getText());
				comboBox_45.addItem(textArea_1.getText());
				comboBox_46.addItem(textArea_1.getText());
				comboBox_47.addItem(textArea_1.getText());
				comboBox_48.addItem(textArea_1.getText());
				comboBox_49.addItem(textArea_1.getText());
				comboBox_50.addItem(textArea_1.getText());
				comboBox_43.addItem(textArea_3.getText());
				comboBox_44.addItem(textArea_3.getText());
				comboBox_45.addItem(textArea_3.getText());
				comboBox_46.addItem(textArea_3.getText());
				comboBox_47.addItem(textArea_3.getText());
				comboBox_48.addItem(textArea_3.getText());
				comboBox_49.addItem(textArea_3.getText());
				comboBox_50.addItem(textArea_3.getText());
				comboBox_43.addItem(textArea_5.getText());
				comboBox_44.addItem(textArea_5.getText());
				comboBox_45.addItem(textArea_5.getText());
				comboBox_46.addItem(textArea_5.getText());
				comboBox_47.addItem(textArea_5.getText());
				comboBox_48.addItem(textArea_5.getText());
				comboBox_49.addItem(textArea_5.getText());
				comboBox_50.addItem(textArea_5.getText());
				comboBox_43.addItem(textArea_7.getText());
				comboBox_44.addItem(textArea_7.getText());
				comboBox_45.addItem(textArea_7.getText());
				comboBox_46.addItem(textArea_7.getText());
				comboBox_47.addItem(textArea_7.getText());
				comboBox_48.addItem(textArea_7.getText());
				comboBox_49.addItem(textArea_7.getText());
				comboBox_50.addItem(textArea_7.getText());
				comboBox_43.addItem(textArea_9.getText());
				comboBox_44.addItem(textArea_9.getText());
				comboBox_45.addItem(textArea_9.getText());
				comboBox_46.addItem(textArea_9.getText());
				comboBox_47.addItem(textArea_9.getText());
				comboBox_48.addItem(textArea_9.getText());
				comboBox_49.addItem(textArea_9.getText());
				comboBox_50.addItem(textArea_9.getText());
				comboBox_43.addItem(textArea_11.getText());
				comboBox_44.addItem(textArea_11.getText());
				comboBox_45.addItem(textArea_11.getText());
				comboBox_46.addItem(textArea_11.getText());
				comboBox_47.addItem(textArea_11.getText());
				comboBox_48.addItem(textArea_11.getText());
				comboBox_49.addItem(textArea_11.getText());
				comboBox_50.addItem(textArea_11.getText());
				comboBox_43.addItem(textArea_13.getText());
				comboBox_44.addItem(textArea_13.getText());
				comboBox_45.addItem(textArea_13.getText());
				comboBox_46.addItem(textArea_13.getText());
				comboBox_47.addItem(textArea_13.getText());
				comboBox_48.addItem(textArea_13.getText());
				comboBox_49.addItem(textArea_13.getText());
				comboBox_50.addItem(textArea_13.getText());
				comboBox_43.addItem(textArea_15.getText());
				comboBox_44.addItem(textArea_15.getText());
				comboBox_45.addItem(textArea_15.getText());
				comboBox_46.addItem(textArea_15.getText());
				comboBox_47.addItem(textArea_15.getText());
				comboBox_48.addItem(textArea_15.getText());
				comboBox_49.addItem(textArea_15.getText());
				comboBox_50.addItem(textArea_15.getText());
				comboBox_43.addItem(textArea_17.getText());
				comboBox_44.addItem(textArea_17.getText());
				comboBox_45.addItem(textArea_17.getText());
				comboBox_46.addItem(textArea_17.getText());
				comboBox_47.addItem(textArea_17.getText());
				comboBox_48.addItem(textArea_17.getText());
				comboBox_49.addItem(textArea_17.getText());
				comboBox_50.addItem(textArea_17.getText());
				comboBox_43.addItem(textArea_19.getText());
				comboBox_44.addItem(textArea_19.getText());
				comboBox_45.addItem(textArea_19.getText());
				comboBox_46.addItem(textArea_19.getText());
				comboBox_47.addItem(textArea_19.getText());
				comboBox_48.addItem(textArea_19.getText());
				comboBox_49.addItem(textArea_19.getText());
				comboBox_50.addItem(textArea_19.getText());
				comboBox_43.addItem("M5000");
				comboBox_44.addItem("M5000");
				comboBox_45.addItem("M5000");
				comboBox_46.addItem("M5000");
				comboBox_47.addItem("M5000");
				comboBox_48.addItem("M5000");
				comboBox_49.addItem("M5000");
				comboBox_50.addItem("M5000");
			}

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				
			}
		});

		textArea_5.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent arg0) {
				comboBox_43.removeAllItems();
				comboBox_44.removeAllItems();
				comboBox_45.removeAllItems();
				comboBox_46.removeAllItems();
				comboBox_47.removeAllItems();
				comboBox_48.removeAllItems();
				comboBox_49.removeAllItems();
				comboBox_50.removeAllItems();
				comboBox_43.addItem(textArea_1.getText());
				comboBox_44.addItem(textArea_1.getText());
				comboBox_45.addItem(textArea_1.getText());
				comboBox_46.addItem(textArea_1.getText());
				comboBox_47.addItem(textArea_1.getText());
				comboBox_48.addItem(textArea_1.getText());
				comboBox_49.addItem(textArea_1.getText());
				comboBox_50.addItem(textArea_1.getText());
				comboBox_43.addItem(textArea_3.getText());
				comboBox_44.addItem(textArea_3.getText());
				comboBox_45.addItem(textArea_3.getText());
				comboBox_46.addItem(textArea_3.getText());
				comboBox_47.addItem(textArea_3.getText());
				comboBox_48.addItem(textArea_3.getText());
				comboBox_49.addItem(textArea_3.getText());
				comboBox_50.addItem(textArea_3.getText());
				comboBox_43.addItem(textArea_5.getText());
				comboBox_44.addItem(textArea_5.getText());
				comboBox_45.addItem(textArea_5.getText());
				comboBox_46.addItem(textArea_5.getText());
				comboBox_47.addItem(textArea_5.getText());
				comboBox_48.addItem(textArea_5.getText());
				comboBox_49.addItem(textArea_5.getText());
				comboBox_50.addItem(textArea_5.getText());
				comboBox_43.addItem(textArea_7.getText());
				comboBox_44.addItem(textArea_7.getText());
				comboBox_45.addItem(textArea_7.getText());
				comboBox_46.addItem(textArea_7.getText());
				comboBox_47.addItem(textArea_7.getText());
				comboBox_48.addItem(textArea_7.getText());
				comboBox_49.addItem(textArea_7.getText());
				comboBox_50.addItem(textArea_7.getText());
				comboBox_43.addItem(textArea_9.getText());
				comboBox_44.addItem(textArea_9.getText());
				comboBox_45.addItem(textArea_9.getText());
				comboBox_46.addItem(textArea_9.getText());
				comboBox_47.addItem(textArea_9.getText());
				comboBox_48.addItem(textArea_9.getText());
				comboBox_49.addItem(textArea_9.getText());
				comboBox_50.addItem(textArea_9.getText());
				comboBox_43.addItem(textArea_11.getText());
				comboBox_44.addItem(textArea_11.getText());
				comboBox_45.addItem(textArea_11.getText());
				comboBox_46.addItem(textArea_11.getText());
				comboBox_47.addItem(textArea_11.getText());
				comboBox_48.addItem(textArea_11.getText());
				comboBox_49.addItem(textArea_11.getText());
				comboBox_50.addItem(textArea_11.getText());
				comboBox_43.addItem(textArea_13.getText());
				comboBox_44.addItem(textArea_13.getText());
				comboBox_45.addItem(textArea_13.getText());
				comboBox_46.addItem(textArea_13.getText());
				comboBox_47.addItem(textArea_13.getText());
				comboBox_48.addItem(textArea_13.getText());
				comboBox_49.addItem(textArea_13.getText());
				comboBox_50.addItem(textArea_13.getText());
				comboBox_43.addItem(textArea_15.getText());
				comboBox_44.addItem(textArea_15.getText());
				comboBox_45.addItem(textArea_15.getText());
				comboBox_46.addItem(textArea_15.getText());
				comboBox_47.addItem(textArea_15.getText());
				comboBox_48.addItem(textArea_15.getText());
				comboBox_49.addItem(textArea_15.getText());
				comboBox_50.addItem(textArea_15.getText());
				comboBox_43.addItem(textArea_17.getText());
				comboBox_44.addItem(textArea_17.getText());
				comboBox_45.addItem(textArea_17.getText());
				comboBox_46.addItem(textArea_17.getText());
				comboBox_47.addItem(textArea_17.getText());
				comboBox_48.addItem(textArea_17.getText());
				comboBox_49.addItem(textArea_17.getText());
				comboBox_50.addItem(textArea_17.getText());
				comboBox_43.addItem(textArea_19.getText());
				comboBox_44.addItem(textArea_19.getText());
				comboBox_45.addItem(textArea_19.getText());
				comboBox_46.addItem(textArea_19.getText());
				comboBox_47.addItem(textArea_19.getText());
				comboBox_48.addItem(textArea_19.getText());
				comboBox_49.addItem(textArea_19.getText());
				comboBox_50.addItem(textArea_19.getText());
				comboBox_43.addItem("M5000");
				comboBox_44.addItem("M5000");
				comboBox_45.addItem("M5000");
				comboBox_46.addItem("M5000");
				comboBox_47.addItem("M5000");
				comboBox_48.addItem("M5000");
				comboBox_49.addItem("M5000");
				comboBox_50.addItem("M5000");
			}

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				
			}
		});

		textArea_7.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent arg0) {
				comboBox_43.removeAllItems();
				comboBox_44.removeAllItems();
				comboBox_45.removeAllItems();
				comboBox_46.removeAllItems();
				comboBox_47.removeAllItems();
				comboBox_48.removeAllItems();
				comboBox_49.removeAllItems();
				comboBox_50.removeAllItems();
				comboBox_43.addItem(textArea_1.getText());
				comboBox_44.addItem(textArea_1.getText());
				comboBox_45.addItem(textArea_1.getText());
				comboBox_46.addItem(textArea_1.getText());
				comboBox_47.addItem(textArea_1.getText());
				comboBox_48.addItem(textArea_1.getText());
				comboBox_49.addItem(textArea_1.getText());
				comboBox_50.addItem(textArea_1.getText());
				comboBox_43.addItem(textArea_3.getText());
				comboBox_44.addItem(textArea_3.getText());
				comboBox_45.addItem(textArea_3.getText());
				comboBox_46.addItem(textArea_3.getText());
				comboBox_47.addItem(textArea_3.getText());
				comboBox_48.addItem(textArea_3.getText());
				comboBox_49.addItem(textArea_3.getText());
				comboBox_50.addItem(textArea_3.getText());
				comboBox_43.addItem(textArea_5.getText());
				comboBox_44.addItem(textArea_5.getText());
				comboBox_45.addItem(textArea_5.getText());
				comboBox_46.addItem(textArea_5.getText());
				comboBox_47.addItem(textArea_5.getText());
				comboBox_48.addItem(textArea_5.getText());
				comboBox_49.addItem(textArea_5.getText());
				comboBox_50.addItem(textArea_5.getText());
				comboBox_43.addItem(textArea_7.getText());
				comboBox_44.addItem(textArea_7.getText());
				comboBox_45.addItem(textArea_7.getText());
				comboBox_46.addItem(textArea_7.getText());
				comboBox_47.addItem(textArea_7.getText());
				comboBox_48.addItem(textArea_7.getText());
				comboBox_49.addItem(textArea_7.getText());
				comboBox_50.addItem(textArea_7.getText());
				comboBox_43.addItem(textArea_9.getText());
				comboBox_44.addItem(textArea_9.getText());
				comboBox_45.addItem(textArea_9.getText());
				comboBox_46.addItem(textArea_9.getText());
				comboBox_47.addItem(textArea_9.getText());
				comboBox_48.addItem(textArea_9.getText());
				comboBox_49.addItem(textArea_9.getText());
				comboBox_50.addItem(textArea_9.getText());
				comboBox_43.addItem(textArea_11.getText());
				comboBox_44.addItem(textArea_11.getText());
				comboBox_45.addItem(textArea_11.getText());
				comboBox_46.addItem(textArea_11.getText());
				comboBox_47.addItem(textArea_11.getText());
				comboBox_48.addItem(textArea_11.getText());
				comboBox_49.addItem(textArea_11.getText());
				comboBox_50.addItem(textArea_11.getText());
				comboBox_43.addItem(textArea_13.getText());
				comboBox_44.addItem(textArea_13.getText());
				comboBox_45.addItem(textArea_13.getText());
				comboBox_46.addItem(textArea_13.getText());
				comboBox_47.addItem(textArea_13.getText());
				comboBox_48.addItem(textArea_13.getText());
				comboBox_49.addItem(textArea_13.getText());
				comboBox_50.addItem(textArea_13.getText());
				comboBox_43.addItem(textArea_15.getText());
				comboBox_44.addItem(textArea_15.getText());
				comboBox_45.addItem(textArea_15.getText());
				comboBox_46.addItem(textArea_15.getText());
				comboBox_47.addItem(textArea_15.getText());
				comboBox_48.addItem(textArea_15.getText());
				comboBox_49.addItem(textArea_15.getText());
				comboBox_50.addItem(textArea_15.getText());
				comboBox_43.addItem(textArea_17.getText());
				comboBox_44.addItem(textArea_17.getText());
				comboBox_45.addItem(textArea_17.getText());
				comboBox_46.addItem(textArea_17.getText());
				comboBox_47.addItem(textArea_17.getText());
				comboBox_48.addItem(textArea_17.getText());
				comboBox_49.addItem(textArea_17.getText());
				comboBox_50.addItem(textArea_17.getText());
				comboBox_43.addItem(textArea_19.getText());
				comboBox_44.addItem(textArea_19.getText());
				comboBox_45.addItem(textArea_19.getText());
				comboBox_46.addItem(textArea_19.getText());
				comboBox_47.addItem(textArea_19.getText());
				comboBox_48.addItem(textArea_19.getText());
				comboBox_49.addItem(textArea_19.getText());
				comboBox_50.addItem(textArea_19.getText());
				comboBox_43.addItem("M5000");
				comboBox_44.addItem("M5000");
				comboBox_45.addItem("M5000");
				comboBox_46.addItem("M5000");
				comboBox_47.addItem("M5000");
				comboBox_48.addItem("M5000");
				comboBox_49.addItem("M5000");
				comboBox_50.addItem("M5000");
			}

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				
			}
		});

		textArea_9.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent arg0) {
				comboBox_43.removeAllItems();
				comboBox_44.removeAllItems();
				comboBox_45.removeAllItems();
				comboBox_46.removeAllItems();
				comboBox_47.removeAllItems();
				comboBox_48.removeAllItems();
				comboBox_49.removeAllItems();
				comboBox_50.removeAllItems();
				comboBox_43.addItem(textArea_1.getText());
				comboBox_44.addItem(textArea_1.getText());
				comboBox_45.addItem(textArea_1.getText());
				comboBox_46.addItem(textArea_1.getText());
				comboBox_47.addItem(textArea_1.getText());
				comboBox_48.addItem(textArea_1.getText());
				comboBox_49.addItem(textArea_1.getText());
				comboBox_50.addItem(textArea_1.getText());
				comboBox_43.addItem(textArea_3.getText());
				comboBox_44.addItem(textArea_3.getText());
				comboBox_45.addItem(textArea_3.getText());
				comboBox_46.addItem(textArea_3.getText());
				comboBox_47.addItem(textArea_3.getText());
				comboBox_48.addItem(textArea_3.getText());
				comboBox_49.addItem(textArea_3.getText());
				comboBox_50.addItem(textArea_3.getText());
				comboBox_43.addItem(textArea_5.getText());
				comboBox_44.addItem(textArea_5.getText());
				comboBox_45.addItem(textArea_5.getText());
				comboBox_46.addItem(textArea_5.getText());
				comboBox_47.addItem(textArea_5.getText());
				comboBox_48.addItem(textArea_5.getText());
				comboBox_49.addItem(textArea_5.getText());
				comboBox_50.addItem(textArea_5.getText());
				comboBox_43.addItem(textArea_7.getText());
				comboBox_44.addItem(textArea_7.getText());
				comboBox_45.addItem(textArea_7.getText());
				comboBox_46.addItem(textArea_7.getText());
				comboBox_47.addItem(textArea_7.getText());
				comboBox_48.addItem(textArea_7.getText());
				comboBox_49.addItem(textArea_7.getText());
				comboBox_50.addItem(textArea_7.getText());
				comboBox_43.addItem(textArea_9.getText());
				comboBox_44.addItem(textArea_9.getText());
				comboBox_45.addItem(textArea_9.getText());
				comboBox_46.addItem(textArea_9.getText());
				comboBox_47.addItem(textArea_9.getText());
				comboBox_48.addItem(textArea_9.getText());
				comboBox_49.addItem(textArea_9.getText());
				comboBox_50.addItem(textArea_9.getText());
				comboBox_43.addItem(textArea_11.getText());
				comboBox_44.addItem(textArea_11.getText());
				comboBox_45.addItem(textArea_11.getText());
				comboBox_46.addItem(textArea_11.getText());
				comboBox_47.addItem(textArea_11.getText());
				comboBox_48.addItem(textArea_11.getText());
				comboBox_49.addItem(textArea_11.getText());
				comboBox_50.addItem(textArea_11.getText());
				comboBox_43.addItem(textArea_13.getText());
				comboBox_44.addItem(textArea_13.getText());
				comboBox_45.addItem(textArea_13.getText());
				comboBox_46.addItem(textArea_13.getText());
				comboBox_47.addItem(textArea_13.getText());
				comboBox_48.addItem(textArea_13.getText());
				comboBox_49.addItem(textArea_13.getText());
				comboBox_50.addItem(textArea_13.getText());
				comboBox_43.addItem(textArea_15.getText());
				comboBox_44.addItem(textArea_15.getText());
				comboBox_45.addItem(textArea_15.getText());
				comboBox_46.addItem(textArea_15.getText());
				comboBox_47.addItem(textArea_15.getText());
				comboBox_48.addItem(textArea_15.getText());
				comboBox_49.addItem(textArea_15.getText());
				comboBox_50.addItem(textArea_15.getText());
				comboBox_43.addItem(textArea_17.getText());
				comboBox_44.addItem(textArea_17.getText());
				comboBox_45.addItem(textArea_17.getText());
				comboBox_46.addItem(textArea_17.getText());
				comboBox_47.addItem(textArea_17.getText());
				comboBox_48.addItem(textArea_17.getText());
				comboBox_49.addItem(textArea_17.getText());
				comboBox_50.addItem(textArea_17.getText());
				comboBox_43.addItem(textArea_19.getText());
				comboBox_44.addItem(textArea_19.getText());
				comboBox_45.addItem(textArea_19.getText());
				comboBox_46.addItem(textArea_19.getText());
				comboBox_47.addItem(textArea_19.getText());
				comboBox_48.addItem(textArea_19.getText());
				comboBox_49.addItem(textArea_19.getText());
				comboBox_50.addItem(textArea_19.getText());
				comboBox_43.addItem("M5000");
				comboBox_44.addItem("M5000");
				comboBox_45.addItem("M5000");
				comboBox_46.addItem("M5000");
				comboBox_47.addItem("M5000");
				comboBox_48.addItem("M5000");
				comboBox_49.addItem("M5000");
				comboBox_50.addItem("M5000");
			}

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				
			}
		});

		textArea_11.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent arg0) {
				comboBox_43.removeAllItems();
				comboBox_44.removeAllItems();
				comboBox_45.removeAllItems();
				comboBox_46.removeAllItems();
				comboBox_47.removeAllItems();
				comboBox_48.removeAllItems();
				comboBox_49.removeAllItems();
				comboBox_50.removeAllItems();
				comboBox_43.addItem(textArea_1.getText());
				comboBox_44.addItem(textArea_1.getText());
				comboBox_45.addItem(textArea_1.getText());
				comboBox_46.addItem(textArea_1.getText());
				comboBox_47.addItem(textArea_1.getText());
				comboBox_48.addItem(textArea_1.getText());
				comboBox_49.addItem(textArea_1.getText());
				comboBox_50.addItem(textArea_1.getText());
				comboBox_43.addItem(textArea_3.getText());
				comboBox_44.addItem(textArea_3.getText());
				comboBox_45.addItem(textArea_3.getText());
				comboBox_46.addItem(textArea_3.getText());
				comboBox_47.addItem(textArea_3.getText());
				comboBox_48.addItem(textArea_3.getText());
				comboBox_49.addItem(textArea_3.getText());
				comboBox_50.addItem(textArea_3.getText());
				comboBox_43.addItem(textArea_5.getText());
				comboBox_44.addItem(textArea_5.getText());
				comboBox_45.addItem(textArea_5.getText());
				comboBox_46.addItem(textArea_5.getText());
				comboBox_47.addItem(textArea_5.getText());
				comboBox_48.addItem(textArea_5.getText());
				comboBox_49.addItem(textArea_5.getText());
				comboBox_50.addItem(textArea_5.getText());
				comboBox_43.addItem(textArea_7.getText());
				comboBox_44.addItem(textArea_7.getText());
				comboBox_45.addItem(textArea_7.getText());
				comboBox_46.addItem(textArea_7.getText());
				comboBox_47.addItem(textArea_7.getText());
				comboBox_48.addItem(textArea_7.getText());
				comboBox_49.addItem(textArea_7.getText());
				comboBox_50.addItem(textArea_7.getText());
				comboBox_43.addItem(textArea_9.getText());
				comboBox_44.addItem(textArea_9.getText());
				comboBox_45.addItem(textArea_9.getText());
				comboBox_46.addItem(textArea_9.getText());
				comboBox_47.addItem(textArea_9.getText());
				comboBox_48.addItem(textArea_9.getText());
				comboBox_49.addItem(textArea_9.getText());
				comboBox_50.addItem(textArea_9.getText());
				comboBox_43.addItem(textArea_11.getText());
				comboBox_44.addItem(textArea_11.getText());
				comboBox_45.addItem(textArea_11.getText());
				comboBox_46.addItem(textArea_11.getText());
				comboBox_47.addItem(textArea_11.getText());
				comboBox_48.addItem(textArea_11.getText());
				comboBox_49.addItem(textArea_11.getText());
				comboBox_50.addItem(textArea_11.getText());
				comboBox_43.addItem(textArea_13.getText());
				comboBox_44.addItem(textArea_13.getText());
				comboBox_45.addItem(textArea_13.getText());
				comboBox_46.addItem(textArea_13.getText());
				comboBox_47.addItem(textArea_13.getText());
				comboBox_48.addItem(textArea_13.getText());
				comboBox_49.addItem(textArea_13.getText());
				comboBox_50.addItem(textArea_13.getText());
				comboBox_43.addItem(textArea_15.getText());
				comboBox_44.addItem(textArea_15.getText());
				comboBox_45.addItem(textArea_15.getText());
				comboBox_46.addItem(textArea_15.getText());
				comboBox_47.addItem(textArea_15.getText());
				comboBox_48.addItem(textArea_15.getText());
				comboBox_49.addItem(textArea_15.getText());
				comboBox_50.addItem(textArea_15.getText());
				comboBox_43.addItem(textArea_17.getText());
				comboBox_44.addItem(textArea_17.getText());
				comboBox_45.addItem(textArea_17.getText());
				comboBox_46.addItem(textArea_17.getText());
				comboBox_47.addItem(textArea_17.getText());
				comboBox_48.addItem(textArea_17.getText());
				comboBox_49.addItem(textArea_17.getText());
				comboBox_50.addItem(textArea_17.getText());
				comboBox_43.addItem(textArea_19.getText());
				comboBox_44.addItem(textArea_19.getText());
				comboBox_45.addItem(textArea_19.getText());
				comboBox_46.addItem(textArea_19.getText());
				comboBox_47.addItem(textArea_19.getText());
				comboBox_48.addItem(textArea_19.getText());
				comboBox_49.addItem(textArea_19.getText());
				comboBox_50.addItem(textArea_19.getText());
				comboBox_43.addItem("M5000");
				comboBox_44.addItem("M5000");
				comboBox_45.addItem("M5000");
				comboBox_46.addItem("M5000");
				comboBox_47.addItem("M5000");
				comboBox_48.addItem("M5000");
				comboBox_49.addItem("M5000");
				comboBox_50.addItem("M5000");
			}

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		

		textArea_13.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent arg0) {
				comboBox_43.removeAllItems();
				comboBox_44.removeAllItems();
				comboBox_45.removeAllItems();
				comboBox_46.removeAllItems();
				comboBox_47.removeAllItems();
				comboBox_48.removeAllItems();
				comboBox_49.removeAllItems();
				comboBox_50.removeAllItems();
				comboBox_43.addItem(textArea_1.getText());
				comboBox_44.addItem(textArea_1.getText());
				comboBox_45.addItem(textArea_1.getText());
				comboBox_46.addItem(textArea_1.getText());
				comboBox_47.addItem(textArea_1.getText());
				comboBox_48.addItem(textArea_1.getText());
				comboBox_49.addItem(textArea_1.getText());
				comboBox_50.addItem(textArea_1.getText());
				comboBox_43.addItem(textArea_3.getText());
				comboBox_44.addItem(textArea_3.getText());
				comboBox_45.addItem(textArea_3.getText());
				comboBox_46.addItem(textArea_3.getText());
				comboBox_47.addItem(textArea_3.getText());
				comboBox_48.addItem(textArea_3.getText());
				comboBox_49.addItem(textArea_3.getText());
				comboBox_50.addItem(textArea_3.getText());
				comboBox_43.addItem(textArea_5.getText());
				comboBox_44.addItem(textArea_5.getText());
				comboBox_45.addItem(textArea_5.getText());
				comboBox_46.addItem(textArea_5.getText());
				comboBox_47.addItem(textArea_5.getText());
				comboBox_48.addItem(textArea_5.getText());
				comboBox_49.addItem(textArea_5.getText());
				comboBox_50.addItem(textArea_5.getText());
				comboBox_43.addItem(textArea_7.getText());
				comboBox_44.addItem(textArea_7.getText());
				comboBox_45.addItem(textArea_7.getText());
				comboBox_46.addItem(textArea_7.getText());
				comboBox_47.addItem(textArea_7.getText());
				comboBox_48.addItem(textArea_7.getText());
				comboBox_49.addItem(textArea_7.getText());
				comboBox_50.addItem(textArea_7.getText());
				comboBox_43.addItem(textArea_9.getText());
				comboBox_44.addItem(textArea_9.getText());
				comboBox_45.addItem(textArea_9.getText());
				comboBox_46.addItem(textArea_9.getText());
				comboBox_47.addItem(textArea_9.getText());
				comboBox_48.addItem(textArea_9.getText());
				comboBox_49.addItem(textArea_9.getText());
				comboBox_50.addItem(textArea_9.getText());
				comboBox_43.addItem(textArea_11.getText());
				comboBox_44.addItem(textArea_11.getText());
				comboBox_45.addItem(textArea_11.getText());
				comboBox_46.addItem(textArea_11.getText());
				comboBox_47.addItem(textArea_11.getText());
				comboBox_48.addItem(textArea_11.getText());
				comboBox_49.addItem(textArea_11.getText());
				comboBox_50.addItem(textArea_11.getText());
				comboBox_43.addItem(textArea_13.getText());
				comboBox_44.addItem(textArea_13.getText());
				comboBox_45.addItem(textArea_13.getText());
				comboBox_46.addItem(textArea_13.getText());
				comboBox_47.addItem(textArea_13.getText());
				comboBox_48.addItem(textArea_13.getText());
				comboBox_49.addItem(textArea_13.getText());
				comboBox_50.addItem(textArea_13.getText());
				comboBox_43.addItem(textArea_15.getText());
				comboBox_44.addItem(textArea_15.getText());
				comboBox_45.addItem(textArea_15.getText());
				comboBox_46.addItem(textArea_15.getText());
				comboBox_47.addItem(textArea_15.getText());
				comboBox_48.addItem(textArea_15.getText());
				comboBox_49.addItem(textArea_15.getText());
				comboBox_50.addItem(textArea_15.getText());
				comboBox_43.addItem(textArea_17.getText());
				comboBox_44.addItem(textArea_17.getText());
				comboBox_45.addItem(textArea_17.getText());
				comboBox_46.addItem(textArea_17.getText());
				comboBox_47.addItem(textArea_17.getText());
				comboBox_48.addItem(textArea_17.getText());
				comboBox_49.addItem(textArea_17.getText());
				comboBox_50.addItem(textArea_17.getText());
				comboBox_43.addItem(textArea_19.getText());
				comboBox_44.addItem(textArea_19.getText());
				comboBox_45.addItem(textArea_19.getText());
				comboBox_46.addItem(textArea_19.getText());
				comboBox_47.addItem(textArea_19.getText());
				comboBox_48.addItem(textArea_19.getText());
				comboBox_49.addItem(textArea_19.getText());
				comboBox_50.addItem(textArea_19.getText());
				comboBox_43.addItem("M5000");
				comboBox_44.addItem("M5000");
				comboBox_45.addItem("M5000");
				comboBox_46.addItem("M5000");
				comboBox_47.addItem("M5000");
				comboBox_48.addItem("M5000");
				comboBox_49.addItem("M5000");
				comboBox_50.addItem("M5000");
			}

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		

		textArea_15.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent arg0) {
				comboBox_43.removeAllItems();
				comboBox_44.removeAllItems();
				comboBox_45.removeAllItems();
				comboBox_46.removeAllItems();
				comboBox_47.removeAllItems();
				comboBox_48.removeAllItems();
				comboBox_49.removeAllItems();
				comboBox_50.removeAllItems();
				comboBox_43.addItem(textArea_1.getText());
				comboBox_44.addItem(textArea_1.getText());
				comboBox_45.addItem(textArea_1.getText());
				comboBox_46.addItem(textArea_1.getText());
				comboBox_47.addItem(textArea_1.getText());
				comboBox_48.addItem(textArea_1.getText());
				comboBox_49.addItem(textArea_1.getText());
				comboBox_50.addItem(textArea_1.getText());
				comboBox_43.addItem(textArea_3.getText());
				comboBox_44.addItem(textArea_3.getText());
				comboBox_45.addItem(textArea_3.getText());
				comboBox_46.addItem(textArea_3.getText());
				comboBox_47.addItem(textArea_3.getText());
				comboBox_48.addItem(textArea_3.getText());
				comboBox_49.addItem(textArea_3.getText());
				comboBox_50.addItem(textArea_3.getText());
				comboBox_43.addItem(textArea_5.getText());
				comboBox_44.addItem(textArea_5.getText());
				comboBox_45.addItem(textArea_5.getText());
				comboBox_46.addItem(textArea_5.getText());
				comboBox_47.addItem(textArea_5.getText());
				comboBox_48.addItem(textArea_5.getText());
				comboBox_49.addItem(textArea_5.getText());
				comboBox_50.addItem(textArea_5.getText());
				comboBox_43.addItem(textArea_7.getText());
				comboBox_44.addItem(textArea_7.getText());
				comboBox_45.addItem(textArea_7.getText());
				comboBox_46.addItem(textArea_7.getText());
				comboBox_47.addItem(textArea_7.getText());
				comboBox_48.addItem(textArea_7.getText());
				comboBox_49.addItem(textArea_7.getText());
				comboBox_50.addItem(textArea_7.getText());
				comboBox_43.addItem(textArea_9.getText());
				comboBox_44.addItem(textArea_9.getText());
				comboBox_45.addItem(textArea_9.getText());
				comboBox_46.addItem(textArea_9.getText());
				comboBox_47.addItem(textArea_9.getText());
				comboBox_48.addItem(textArea_9.getText());
				comboBox_49.addItem(textArea_9.getText());
				comboBox_50.addItem(textArea_9.getText());
				comboBox_43.addItem(textArea_11.getText());
				comboBox_44.addItem(textArea_11.getText());
				comboBox_45.addItem(textArea_11.getText());
				comboBox_46.addItem(textArea_11.getText());
				comboBox_47.addItem(textArea_11.getText());
				comboBox_48.addItem(textArea_11.getText());
				comboBox_49.addItem(textArea_11.getText());
				comboBox_50.addItem(textArea_11.getText());
				comboBox_43.addItem(textArea_13.getText());
				comboBox_44.addItem(textArea_13.getText());
				comboBox_45.addItem(textArea_13.getText());
				comboBox_46.addItem(textArea_13.getText());
				comboBox_47.addItem(textArea_13.getText());
				comboBox_48.addItem(textArea_13.getText());
				comboBox_49.addItem(textArea_13.getText());
				comboBox_50.addItem(textArea_13.getText());
				comboBox_43.addItem(textArea_15.getText());
				comboBox_44.addItem(textArea_15.getText());
				comboBox_45.addItem(textArea_15.getText());
				comboBox_46.addItem(textArea_15.getText());
				comboBox_47.addItem(textArea_15.getText());
				comboBox_48.addItem(textArea_15.getText());
				comboBox_49.addItem(textArea_15.getText());
				comboBox_50.addItem(textArea_15.getText());
				comboBox_43.addItem(textArea_17.getText());
				comboBox_44.addItem(textArea_17.getText());
				comboBox_45.addItem(textArea_17.getText());
				comboBox_46.addItem(textArea_17.getText());
				comboBox_47.addItem(textArea_17.getText());
				comboBox_48.addItem(textArea_17.getText());
				comboBox_49.addItem(textArea_17.getText());
				comboBox_50.addItem(textArea_17.getText());
				comboBox_43.addItem(textArea_19.getText());
				comboBox_44.addItem(textArea_19.getText());
				comboBox_45.addItem(textArea_19.getText());
				comboBox_46.addItem(textArea_19.getText());
				comboBox_47.addItem(textArea_19.getText());
				comboBox_48.addItem(textArea_19.getText());
				comboBox_49.addItem(textArea_19.getText());
				comboBox_50.addItem(textArea_19.getText());
				comboBox_43.addItem("M5000");
				comboBox_44.addItem("M5000");
				comboBox_45.addItem("M5000");
				comboBox_46.addItem("M5000");
				comboBox_47.addItem("M5000");
				comboBox_48.addItem("M5000");
				comboBox_49.addItem("M5000");
				comboBox_50.addItem("M5000");
			}

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		

		textArea_17.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent arg0) {
				comboBox_43.removeAllItems();
				comboBox_44.removeAllItems();
				comboBox_45.removeAllItems();
				comboBox_46.removeAllItems();
				comboBox_47.removeAllItems();
				comboBox_48.removeAllItems();
				comboBox_49.removeAllItems();
				comboBox_50.removeAllItems();
				comboBox_43.addItem(textArea_1.getText());
				comboBox_44.addItem(textArea_1.getText());
				comboBox_45.addItem(textArea_1.getText());
				comboBox_46.addItem(textArea_1.getText());
				comboBox_47.addItem(textArea_1.getText());
				comboBox_48.addItem(textArea_1.getText());
				comboBox_49.addItem(textArea_1.getText());
				comboBox_50.addItem(textArea_1.getText());
				comboBox_43.addItem(textArea_3.getText());
				comboBox_44.addItem(textArea_3.getText());
				comboBox_45.addItem(textArea_3.getText());
				comboBox_46.addItem(textArea_3.getText());
				comboBox_47.addItem(textArea_3.getText());
				comboBox_48.addItem(textArea_3.getText());
				comboBox_49.addItem(textArea_3.getText());
				comboBox_50.addItem(textArea_3.getText());
				comboBox_43.addItem(textArea_5.getText());
				comboBox_44.addItem(textArea_5.getText());
				comboBox_45.addItem(textArea_5.getText());
				comboBox_46.addItem(textArea_5.getText());
				comboBox_47.addItem(textArea_5.getText());
				comboBox_48.addItem(textArea_5.getText());
				comboBox_49.addItem(textArea_5.getText());
				comboBox_50.addItem(textArea_5.getText());
				comboBox_43.addItem(textArea_7.getText());
				comboBox_44.addItem(textArea_7.getText());
				comboBox_45.addItem(textArea_7.getText());
				comboBox_46.addItem(textArea_7.getText());
				comboBox_47.addItem(textArea_7.getText());
				comboBox_48.addItem(textArea_7.getText());
				comboBox_49.addItem(textArea_7.getText());
				comboBox_50.addItem(textArea_7.getText());
				comboBox_43.addItem(textArea_9.getText());
				comboBox_44.addItem(textArea_9.getText());
				comboBox_45.addItem(textArea_9.getText());
				comboBox_46.addItem(textArea_9.getText());
				comboBox_47.addItem(textArea_9.getText());
				comboBox_48.addItem(textArea_9.getText());
				comboBox_49.addItem(textArea_9.getText());
				comboBox_50.addItem(textArea_9.getText());
				comboBox_43.addItem(textArea_11.getText());
				comboBox_44.addItem(textArea_11.getText());
				comboBox_45.addItem(textArea_11.getText());
				comboBox_46.addItem(textArea_11.getText());
				comboBox_47.addItem(textArea_11.getText());
				comboBox_48.addItem(textArea_11.getText());
				comboBox_49.addItem(textArea_11.getText());
				comboBox_50.addItem(textArea_11.getText());
				comboBox_43.addItem(textArea_13.getText());
				comboBox_44.addItem(textArea_13.getText());
				comboBox_45.addItem(textArea_13.getText());
				comboBox_46.addItem(textArea_13.getText());
				comboBox_47.addItem(textArea_13.getText());
				comboBox_48.addItem(textArea_13.getText());
				comboBox_49.addItem(textArea_13.getText());
				comboBox_50.addItem(textArea_13.getText());
				comboBox_43.addItem(textArea_15.getText());
				comboBox_44.addItem(textArea_15.getText());
				comboBox_45.addItem(textArea_15.getText());
				comboBox_46.addItem(textArea_15.getText());
				comboBox_47.addItem(textArea_15.getText());
				comboBox_48.addItem(textArea_15.getText());
				comboBox_49.addItem(textArea_15.getText());
				comboBox_50.addItem(textArea_15.getText());
				comboBox_43.addItem(textArea_17.getText());
				comboBox_44.addItem(textArea_17.getText());
				comboBox_45.addItem(textArea_17.getText());
				comboBox_46.addItem(textArea_17.getText());
				comboBox_47.addItem(textArea_17.getText());
				comboBox_48.addItem(textArea_17.getText());
				comboBox_49.addItem(textArea_17.getText());
				comboBox_50.addItem(textArea_17.getText());
				comboBox_43.addItem(textArea_19.getText());
				comboBox_44.addItem(textArea_19.getText());
				comboBox_45.addItem(textArea_19.getText());
				comboBox_46.addItem(textArea_19.getText());
				comboBox_47.addItem(textArea_19.getText());
				comboBox_48.addItem(textArea_19.getText());
				comboBox_49.addItem(textArea_19.getText());
				comboBox_50.addItem(textArea_19.getText());
				comboBox_43.addItem("M5000");
				comboBox_44.addItem("M5000");
				comboBox_45.addItem("M5000");
				comboBox_46.addItem("M5000");
				comboBox_47.addItem("M5000");
				comboBox_48.addItem("M5000");
				comboBox_49.addItem("M5000");
				comboBox_50.addItem("M5000");
			}

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		

		textArea_19.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent arg0) {
				comboBox_43.removeAllItems();
				comboBox_44.removeAllItems();
				comboBox_45.removeAllItems();
				comboBox_46.removeAllItems();
				comboBox_47.removeAllItems();
				comboBox_48.removeAllItems();
				comboBox_49.removeAllItems();
				comboBox_50.removeAllItems();
				comboBox_43.addItem(textArea_1.getText());
				comboBox_44.addItem(textArea_1.getText());
				comboBox_45.addItem(textArea_1.getText());
				comboBox_46.addItem(textArea_1.getText());
				comboBox_47.addItem(textArea_1.getText());
				comboBox_48.addItem(textArea_1.getText());
				comboBox_49.addItem(textArea_1.getText());
				comboBox_50.addItem(textArea_1.getText());
				comboBox_43.addItem(textArea_3.getText());
				comboBox_44.addItem(textArea_3.getText());
				comboBox_45.addItem(textArea_3.getText());
				comboBox_46.addItem(textArea_3.getText());
				comboBox_47.addItem(textArea_3.getText());
				comboBox_48.addItem(textArea_3.getText());
				comboBox_49.addItem(textArea_3.getText());
				comboBox_50.addItem(textArea_3.getText());
				comboBox_43.addItem(textArea_5.getText());
				comboBox_44.addItem(textArea_5.getText());
				comboBox_45.addItem(textArea_5.getText());
				comboBox_46.addItem(textArea_5.getText());
				comboBox_47.addItem(textArea_5.getText());
				comboBox_48.addItem(textArea_5.getText());
				comboBox_49.addItem(textArea_5.getText());
				comboBox_50.addItem(textArea_5.getText());
				comboBox_43.addItem(textArea_7.getText());
				comboBox_44.addItem(textArea_7.getText());
				comboBox_45.addItem(textArea_7.getText());
				comboBox_46.addItem(textArea_7.getText());
				comboBox_47.addItem(textArea_7.getText());
				comboBox_48.addItem(textArea_7.getText());
				comboBox_49.addItem(textArea_7.getText());
				comboBox_50.addItem(textArea_7.getText());
				comboBox_43.addItem(textArea_9.getText());
				comboBox_44.addItem(textArea_9.getText());
				comboBox_45.addItem(textArea_9.getText());
				comboBox_46.addItem(textArea_9.getText());
				comboBox_47.addItem(textArea_9.getText());
				comboBox_48.addItem(textArea_9.getText());
				comboBox_49.addItem(textArea_9.getText());
				comboBox_50.addItem(textArea_9.getText());
				comboBox_43.addItem(textArea_11.getText());
				comboBox_44.addItem(textArea_11.getText());
				comboBox_45.addItem(textArea_11.getText());
				comboBox_46.addItem(textArea_11.getText());
				comboBox_47.addItem(textArea_11.getText());
				comboBox_48.addItem(textArea_11.getText());
				comboBox_49.addItem(textArea_11.getText());
				comboBox_50.addItem(textArea_11.getText());
				comboBox_43.addItem(textArea_13.getText());
				comboBox_44.addItem(textArea_13.getText());
				comboBox_45.addItem(textArea_13.getText());
				comboBox_46.addItem(textArea_13.getText());
				comboBox_47.addItem(textArea_13.getText());
				comboBox_48.addItem(textArea_13.getText());
				comboBox_49.addItem(textArea_13.getText());
				comboBox_50.addItem(textArea_13.getText());
				comboBox_43.addItem(textArea_15.getText());
				comboBox_44.addItem(textArea_15.getText());
				comboBox_45.addItem(textArea_15.getText());
				comboBox_46.addItem(textArea_15.getText());
				comboBox_47.addItem(textArea_15.getText());
				comboBox_48.addItem(textArea_15.getText());
				comboBox_49.addItem(textArea_15.getText());
				comboBox_50.addItem(textArea_15.getText());
				comboBox_43.addItem(textArea_17.getText());
				comboBox_44.addItem(textArea_17.getText());
				comboBox_45.addItem(textArea_17.getText());
				comboBox_46.addItem(textArea_17.getText());
				comboBox_47.addItem(textArea_17.getText());
				comboBox_48.addItem(textArea_17.getText());
				comboBox_49.addItem(textArea_17.getText());
				comboBox_50.addItem(textArea_17.getText());
				comboBox_43.addItem(textArea_19.getText());
				comboBox_44.addItem(textArea_19.getText());
				comboBox_45.addItem(textArea_19.getText());
				comboBox_46.addItem(textArea_19.getText());
				comboBox_47.addItem(textArea_19.getText());
				comboBox_48.addItem(textArea_19.getText());
				comboBox_49.addItem(textArea_19.getText());
				comboBox_50.addItem(textArea_19.getText());
				comboBox_43.addItem("M5000");
				comboBox_44.addItem("M5000");
				comboBox_45.addItem("M5000");
				comboBox_46.addItem("M5000");
				comboBox_47.addItem("M5000");
				comboBox_48.addItem("M5000");
				comboBox_49.addItem("M5000");
				comboBox_50.addItem("M5000");
			}

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				
			}
		});

		textArea_4.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent arg0) {
				comboBox_51.removeAllItems();
				comboBox_52.removeAllItems();
				comboBox_53.removeAllItems();
				comboBox_54.removeAllItems();
				comboBox_55.removeAllItems();
				comboBox_56.removeAllItems();
				comboBox_57.removeAllItems();
				comboBox_58.removeAllItems();
				comboBox_51.addItem(textArea_2.getText());
				comboBox_52.addItem(textArea_2.getText());
				comboBox_53.addItem(textArea_2.getText());
				comboBox_54.addItem(textArea_2.getText());
				comboBox_55.addItem(textArea_2.getText());
				comboBox_56.addItem(textArea_2.getText());
				comboBox_57.addItem(textArea_2.getText());
				comboBox_58.addItem(textArea_2.getText());
				comboBox_51.addItem(textArea_4.getText());
				comboBox_52.addItem(textArea_4.getText());
				comboBox_53.addItem(textArea_4.getText());
				comboBox_54.addItem(textArea_4.getText());
				comboBox_55.addItem(textArea_4.getText());
				comboBox_56.addItem(textArea_4.getText());
				comboBox_57.addItem(textArea_4.getText());
				comboBox_58.addItem(textArea_4.getText());
				comboBox_51.addItem(textArea_6.getText());
				comboBox_52.addItem(textArea_6.getText());
				comboBox_53.addItem(textArea_6.getText());
				comboBox_54.addItem(textArea_6.getText());
				comboBox_55.addItem(textArea_6.getText());
				comboBox_56.addItem(textArea_6.getText());
				comboBox_57.addItem(textArea_6.getText());
				comboBox_58.addItem(textArea_6.getText());
				comboBox_51.addItem(textArea_8.getText());
				comboBox_52.addItem(textArea_8.getText());
				comboBox_53.addItem(textArea_8.getText());
				comboBox_54.addItem(textArea_8.getText());
				comboBox_55.addItem(textArea_8.getText());
				comboBox_56.addItem(textArea_8.getText());
				comboBox_57.addItem(textArea_8.getText());
				comboBox_58.addItem(textArea_8.getText());
				comboBox_51.addItem(textArea_10.getText());
				comboBox_52.addItem(textArea_10.getText());
				comboBox_53.addItem(textArea_10.getText());
				comboBox_54.addItem(textArea_10.getText());
				comboBox_55.addItem(textArea_10.getText());
				comboBox_56.addItem(textArea_10.getText());
				comboBox_57.addItem(textArea_10.getText());
				comboBox_58.addItem(textArea_10.getText());
				comboBox_51.addItem(textArea_12.getText());
				comboBox_52.addItem(textArea_12.getText());
				comboBox_53.addItem(textArea_12.getText());
				comboBox_54.addItem(textArea_12.getText());
				comboBox_55.addItem(textArea_12.getText());
				comboBox_56.addItem(textArea_12.getText());
				comboBox_57.addItem(textArea_12.getText());
				comboBox_58.addItem(textArea_12.getText());
				comboBox_51.addItem(textArea_14.getText());
				comboBox_52.addItem(textArea_14.getText());
				comboBox_53.addItem(textArea_14.getText());
				comboBox_54.addItem(textArea_14.getText());
				comboBox_55.addItem(textArea_14.getText());
				comboBox_56.addItem(textArea_14.getText());
				comboBox_57.addItem(textArea_14.getText());
				comboBox_58.addItem(textArea_14.getText());
				comboBox_51.addItem(textArea_16.getText());
				comboBox_52.addItem(textArea_16.getText());
				comboBox_53.addItem(textArea_16.getText());
				comboBox_54.addItem(textArea_16.getText());
				comboBox_55.addItem(textArea_16.getText());
				comboBox_56.addItem(textArea_16.getText());
				comboBox_57.addItem(textArea_16.getText());
				comboBox_58.addItem(textArea_16.getText());
				comboBox_51.addItem(textArea_18.getText());
				comboBox_52.addItem(textArea_18.getText());
				comboBox_53.addItem(textArea_18.getText());
				comboBox_54.addItem(textArea_18.getText());
				comboBox_55.addItem(textArea_18.getText());
				comboBox_56.addItem(textArea_18.getText());
				comboBox_57.addItem(textArea_18.getText());
				comboBox_58.addItem(textArea_18.getText());
				comboBox_51.addItem(textArea_20.getText());
				comboBox_52.addItem(textArea_20.getText());
				comboBox_53.addItem(textArea_20.getText());
				comboBox_54.addItem(textArea_20.getText());
				comboBox_55.addItem(textArea_20.getText());
				comboBox_56.addItem(textArea_20.getText());
				comboBox_57.addItem(textArea_20.getText());
				comboBox_58.addItem(textArea_20.getText());
			}

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		textArea_6.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent arg0) {
				comboBox_51.removeAllItems();
				comboBox_52.removeAllItems();
				comboBox_53.removeAllItems();
				comboBox_54.removeAllItems();
				comboBox_55.removeAllItems();
				comboBox_56.removeAllItems();
				comboBox_57.removeAllItems();
				comboBox_58.removeAllItems();
				comboBox_51.addItem(textArea_2.getText());
				comboBox_52.addItem(textArea_2.getText());
				comboBox_53.addItem(textArea_2.getText());
				comboBox_54.addItem(textArea_2.getText());
				comboBox_55.addItem(textArea_2.getText());
				comboBox_56.addItem(textArea_2.getText());
				comboBox_57.addItem(textArea_2.getText());
				comboBox_58.addItem(textArea_2.getText());
				comboBox_51.addItem(textArea_4.getText());
				comboBox_52.addItem(textArea_4.getText());
				comboBox_53.addItem(textArea_4.getText());
				comboBox_54.addItem(textArea_4.getText());
				comboBox_55.addItem(textArea_4.getText());
				comboBox_56.addItem(textArea_4.getText());
				comboBox_57.addItem(textArea_4.getText());
				comboBox_58.addItem(textArea_4.getText());
				comboBox_51.addItem(textArea_6.getText());
				comboBox_52.addItem(textArea_6.getText());
				comboBox_53.addItem(textArea_6.getText());
				comboBox_54.addItem(textArea_6.getText());
				comboBox_55.addItem(textArea_6.getText());
				comboBox_56.addItem(textArea_6.getText());
				comboBox_57.addItem(textArea_6.getText());
				comboBox_58.addItem(textArea_6.getText());
				comboBox_51.addItem(textArea_8.getText());
				comboBox_52.addItem(textArea_8.getText());
				comboBox_53.addItem(textArea_8.getText());
				comboBox_54.addItem(textArea_8.getText());
				comboBox_55.addItem(textArea_8.getText());
				comboBox_56.addItem(textArea_8.getText());
				comboBox_57.addItem(textArea_8.getText());
				comboBox_58.addItem(textArea_8.getText());
				comboBox_51.addItem(textArea_10.getText());
				comboBox_52.addItem(textArea_10.getText());
				comboBox_53.addItem(textArea_10.getText());
				comboBox_54.addItem(textArea_10.getText());
				comboBox_55.addItem(textArea_10.getText());
				comboBox_56.addItem(textArea_10.getText());
				comboBox_57.addItem(textArea_10.getText());
				comboBox_58.addItem(textArea_10.getText());
				comboBox_51.addItem(textArea_12.getText());
				comboBox_52.addItem(textArea_12.getText());
				comboBox_53.addItem(textArea_12.getText());
				comboBox_54.addItem(textArea_12.getText());
				comboBox_55.addItem(textArea_12.getText());
				comboBox_56.addItem(textArea_12.getText());
				comboBox_57.addItem(textArea_12.getText());
				comboBox_58.addItem(textArea_12.getText());
				comboBox_51.addItem(textArea_14.getText());
				comboBox_52.addItem(textArea_14.getText());
				comboBox_53.addItem(textArea_14.getText());
				comboBox_54.addItem(textArea_14.getText());
				comboBox_55.addItem(textArea_14.getText());
				comboBox_56.addItem(textArea_14.getText());
				comboBox_57.addItem(textArea_14.getText());
				comboBox_58.addItem(textArea_14.getText());
				comboBox_51.addItem(textArea_16.getText());
				comboBox_52.addItem(textArea_16.getText());
				comboBox_53.addItem(textArea_16.getText());
				comboBox_54.addItem(textArea_16.getText());
				comboBox_55.addItem(textArea_16.getText());
				comboBox_56.addItem(textArea_16.getText());
				comboBox_57.addItem(textArea_16.getText());
				comboBox_58.addItem(textArea_16.getText());
				comboBox_51.addItem(textArea_18.getText());
				comboBox_52.addItem(textArea_18.getText());
				comboBox_53.addItem(textArea_18.getText());
				comboBox_54.addItem(textArea_18.getText());
				comboBox_55.addItem(textArea_18.getText());
				comboBox_56.addItem(textArea_18.getText());
				comboBox_57.addItem(textArea_18.getText());
				comboBox_58.addItem(textArea_18.getText());
				comboBox_51.addItem(textArea_20.getText());
				comboBox_52.addItem(textArea_20.getText());
				comboBox_53.addItem(textArea_20.getText());
				comboBox_54.addItem(textArea_20.getText());
				comboBox_55.addItem(textArea_20.getText());
				comboBox_56.addItem(textArea_20.getText());
				comboBox_57.addItem(textArea_20.getText());
				comboBox_58.addItem(textArea_20.getText());
			}

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		textArea_8.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent arg0) {
				comboBox_51.removeAllItems();
				comboBox_52.removeAllItems();
				comboBox_53.removeAllItems();
				comboBox_54.removeAllItems();
				comboBox_55.removeAllItems();
				comboBox_56.removeAllItems();
				comboBox_57.removeAllItems();
				comboBox_58.removeAllItems();
				comboBox_51.addItem(textArea_2.getText());
				comboBox_52.addItem(textArea_2.getText());
				comboBox_53.addItem(textArea_2.getText());
				comboBox_54.addItem(textArea_2.getText());
				comboBox_55.addItem(textArea_2.getText());
				comboBox_56.addItem(textArea_2.getText());
				comboBox_57.addItem(textArea_2.getText());
				comboBox_58.addItem(textArea_2.getText());
				comboBox_51.addItem(textArea_4.getText());
				comboBox_52.addItem(textArea_4.getText());
				comboBox_53.addItem(textArea_4.getText());
				comboBox_54.addItem(textArea_4.getText());
				comboBox_55.addItem(textArea_4.getText());
				comboBox_56.addItem(textArea_4.getText());
				comboBox_57.addItem(textArea_4.getText());
				comboBox_58.addItem(textArea_4.getText());
				comboBox_51.addItem(textArea_6.getText());
				comboBox_52.addItem(textArea_6.getText());
				comboBox_53.addItem(textArea_6.getText());
				comboBox_54.addItem(textArea_6.getText());
				comboBox_55.addItem(textArea_6.getText());
				comboBox_56.addItem(textArea_6.getText());
				comboBox_57.addItem(textArea_6.getText());
				comboBox_58.addItem(textArea_6.getText());
				comboBox_51.addItem(textArea_8.getText());
				comboBox_52.addItem(textArea_8.getText());
				comboBox_53.addItem(textArea_8.getText());
				comboBox_54.addItem(textArea_8.getText());
				comboBox_55.addItem(textArea_8.getText());
				comboBox_56.addItem(textArea_8.getText());
				comboBox_57.addItem(textArea_8.getText());
				comboBox_58.addItem(textArea_8.getText());
				comboBox_51.addItem(textArea_10.getText());
				comboBox_52.addItem(textArea_10.getText());
				comboBox_53.addItem(textArea_10.getText());
				comboBox_54.addItem(textArea_10.getText());
				comboBox_55.addItem(textArea_10.getText());
				comboBox_56.addItem(textArea_10.getText());
				comboBox_57.addItem(textArea_10.getText());
				comboBox_58.addItem(textArea_10.getText());
				comboBox_51.addItem(textArea_12.getText());
				comboBox_52.addItem(textArea_12.getText());
				comboBox_53.addItem(textArea_12.getText());
				comboBox_54.addItem(textArea_12.getText());
				comboBox_55.addItem(textArea_12.getText());
				comboBox_56.addItem(textArea_12.getText());
				comboBox_57.addItem(textArea_12.getText());
				comboBox_58.addItem(textArea_12.getText());
				comboBox_51.addItem(textArea_14.getText());
				comboBox_52.addItem(textArea_14.getText());
				comboBox_53.addItem(textArea_14.getText());
				comboBox_54.addItem(textArea_14.getText());
				comboBox_55.addItem(textArea_14.getText());
				comboBox_56.addItem(textArea_14.getText());
				comboBox_57.addItem(textArea_14.getText());
				comboBox_58.addItem(textArea_14.getText());
				comboBox_51.addItem(textArea_16.getText());
				comboBox_52.addItem(textArea_16.getText());
				comboBox_53.addItem(textArea_16.getText());
				comboBox_54.addItem(textArea_16.getText());
				comboBox_55.addItem(textArea_16.getText());
				comboBox_56.addItem(textArea_16.getText());
				comboBox_57.addItem(textArea_16.getText());
				comboBox_58.addItem(textArea_16.getText());
				comboBox_51.addItem(textArea_18.getText());
				comboBox_52.addItem(textArea_18.getText());
				comboBox_53.addItem(textArea_18.getText());
				comboBox_54.addItem(textArea_18.getText());
				comboBox_55.addItem(textArea_18.getText());
				comboBox_56.addItem(textArea_18.getText());
				comboBox_57.addItem(textArea_18.getText());
				comboBox_58.addItem(textArea_18.getText());
				comboBox_51.addItem(textArea_20.getText());
				comboBox_52.addItem(textArea_20.getText());
				comboBox_53.addItem(textArea_20.getText());
				comboBox_54.addItem(textArea_20.getText());
				comboBox_55.addItem(textArea_20.getText());
				comboBox_56.addItem(textArea_20.getText());
				comboBox_57.addItem(textArea_20.getText());
				comboBox_58.addItem(textArea_20.getText());
			}

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		textArea_10.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent arg0) {
				comboBox_51.removeAllItems();
				comboBox_52.removeAllItems();
				comboBox_53.removeAllItems();
				comboBox_54.removeAllItems();
				comboBox_55.removeAllItems();
				comboBox_56.removeAllItems();
				comboBox_57.removeAllItems();
				comboBox_58.removeAllItems();
				comboBox_51.addItem(textArea_2.getText());
				comboBox_52.addItem(textArea_2.getText());
				comboBox_53.addItem(textArea_2.getText());
				comboBox_54.addItem(textArea_2.getText());
				comboBox_55.addItem(textArea_2.getText());
				comboBox_56.addItem(textArea_2.getText());
				comboBox_57.addItem(textArea_2.getText());
				comboBox_58.addItem(textArea_2.getText());
				comboBox_51.addItem(textArea_4.getText());
				comboBox_52.addItem(textArea_4.getText());
				comboBox_53.addItem(textArea_4.getText());
				comboBox_54.addItem(textArea_4.getText());
				comboBox_55.addItem(textArea_4.getText());
				comboBox_56.addItem(textArea_4.getText());
				comboBox_57.addItem(textArea_4.getText());
				comboBox_58.addItem(textArea_4.getText());
				comboBox_51.addItem(textArea_6.getText());
				comboBox_52.addItem(textArea_6.getText());
				comboBox_53.addItem(textArea_6.getText());
				comboBox_54.addItem(textArea_6.getText());
				comboBox_55.addItem(textArea_6.getText());
				comboBox_56.addItem(textArea_6.getText());
				comboBox_57.addItem(textArea_6.getText());
				comboBox_58.addItem(textArea_6.getText());
				comboBox_51.addItem(textArea_8.getText());
				comboBox_52.addItem(textArea_8.getText());
				comboBox_53.addItem(textArea_8.getText());
				comboBox_54.addItem(textArea_8.getText());
				comboBox_55.addItem(textArea_8.getText());
				comboBox_56.addItem(textArea_8.getText());
				comboBox_57.addItem(textArea_8.getText());
				comboBox_58.addItem(textArea_8.getText());
				comboBox_51.addItem(textArea_10.getText());
				comboBox_52.addItem(textArea_10.getText());
				comboBox_53.addItem(textArea_10.getText());
				comboBox_54.addItem(textArea_10.getText());
				comboBox_55.addItem(textArea_10.getText());
				comboBox_56.addItem(textArea_10.getText());
				comboBox_57.addItem(textArea_10.getText());
				comboBox_58.addItem(textArea_10.getText());
				comboBox_51.addItem(textArea_12.getText());
				comboBox_52.addItem(textArea_12.getText());
				comboBox_53.addItem(textArea_12.getText());
				comboBox_54.addItem(textArea_12.getText());
				comboBox_55.addItem(textArea_12.getText());
				comboBox_56.addItem(textArea_12.getText());
				comboBox_57.addItem(textArea_12.getText());
				comboBox_58.addItem(textArea_12.getText());
				comboBox_51.addItem(textArea_14.getText());
				comboBox_52.addItem(textArea_14.getText());
				comboBox_53.addItem(textArea_14.getText());
				comboBox_54.addItem(textArea_14.getText());
				comboBox_55.addItem(textArea_14.getText());
				comboBox_56.addItem(textArea_14.getText());
				comboBox_57.addItem(textArea_14.getText());
				comboBox_58.addItem(textArea_14.getText());
				comboBox_51.addItem(textArea_16.getText());
				comboBox_52.addItem(textArea_16.getText());
				comboBox_53.addItem(textArea_16.getText());
				comboBox_54.addItem(textArea_16.getText());
				comboBox_55.addItem(textArea_16.getText());
				comboBox_56.addItem(textArea_16.getText());
				comboBox_57.addItem(textArea_16.getText());
				comboBox_58.addItem(textArea_16.getText());
				comboBox_51.addItem(textArea_18.getText());
				comboBox_52.addItem(textArea_18.getText());
				comboBox_53.addItem(textArea_18.getText());
				comboBox_54.addItem(textArea_18.getText());
				comboBox_55.addItem(textArea_18.getText());
				comboBox_56.addItem(textArea_18.getText());
				comboBox_57.addItem(textArea_18.getText());
				comboBox_58.addItem(textArea_18.getText());
				comboBox_51.addItem(textArea_20.getText());
				comboBox_52.addItem(textArea_20.getText());
				comboBox_53.addItem(textArea_20.getText());
				comboBox_54.addItem(textArea_20.getText());
				comboBox_55.addItem(textArea_20.getText());
				comboBox_56.addItem(textArea_20.getText());
				comboBox_57.addItem(textArea_20.getText());
				comboBox_58.addItem(textArea_20.getText());
			}

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		textArea_12.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent arg0) {
				comboBox_51.removeAllItems();
				comboBox_52.removeAllItems();
				comboBox_53.removeAllItems();
				comboBox_54.removeAllItems();
				comboBox_55.removeAllItems();
				comboBox_56.removeAllItems();
				comboBox_57.removeAllItems();
				comboBox_58.removeAllItems();
				comboBox_51.addItem(textArea_2.getText());
				comboBox_52.addItem(textArea_2.getText());
				comboBox_53.addItem(textArea_2.getText());
				comboBox_54.addItem(textArea_2.getText());
				comboBox_55.addItem(textArea_2.getText());
				comboBox_56.addItem(textArea_2.getText());
				comboBox_57.addItem(textArea_2.getText());
				comboBox_58.addItem(textArea_2.getText());
				comboBox_51.addItem(textArea_4.getText());
				comboBox_52.addItem(textArea_4.getText());
				comboBox_53.addItem(textArea_4.getText());
				comboBox_54.addItem(textArea_4.getText());
				comboBox_55.addItem(textArea_4.getText());
				comboBox_56.addItem(textArea_4.getText());
				comboBox_57.addItem(textArea_4.getText());
				comboBox_58.addItem(textArea_4.getText());
				comboBox_51.addItem(textArea_6.getText());
				comboBox_52.addItem(textArea_6.getText());
				comboBox_53.addItem(textArea_6.getText());
				comboBox_54.addItem(textArea_6.getText());
				comboBox_55.addItem(textArea_6.getText());
				comboBox_56.addItem(textArea_6.getText());
				comboBox_57.addItem(textArea_6.getText());
				comboBox_58.addItem(textArea_6.getText());
				comboBox_51.addItem(textArea_8.getText());
				comboBox_52.addItem(textArea_8.getText());
				comboBox_53.addItem(textArea_8.getText());
				comboBox_54.addItem(textArea_8.getText());
				comboBox_55.addItem(textArea_8.getText());
				comboBox_56.addItem(textArea_8.getText());
				comboBox_57.addItem(textArea_8.getText());
				comboBox_58.addItem(textArea_8.getText());
				comboBox_51.addItem(textArea_10.getText());
				comboBox_52.addItem(textArea_10.getText());
				comboBox_53.addItem(textArea_10.getText());
				comboBox_54.addItem(textArea_10.getText());
				comboBox_55.addItem(textArea_10.getText());
				comboBox_56.addItem(textArea_10.getText());
				comboBox_57.addItem(textArea_10.getText());
				comboBox_58.addItem(textArea_10.getText());
				comboBox_51.addItem(textArea_12.getText());
				comboBox_52.addItem(textArea_12.getText());
				comboBox_53.addItem(textArea_12.getText());
				comboBox_54.addItem(textArea_12.getText());
				comboBox_55.addItem(textArea_12.getText());
				comboBox_56.addItem(textArea_12.getText());
				comboBox_57.addItem(textArea_12.getText());
				comboBox_58.addItem(textArea_12.getText());
				comboBox_51.addItem(textArea_14.getText());
				comboBox_52.addItem(textArea_14.getText());
				comboBox_53.addItem(textArea_14.getText());
				comboBox_54.addItem(textArea_14.getText());
				comboBox_55.addItem(textArea_14.getText());
				comboBox_56.addItem(textArea_14.getText());
				comboBox_57.addItem(textArea_14.getText());
				comboBox_58.addItem(textArea_14.getText());
				comboBox_51.addItem(textArea_16.getText());
				comboBox_52.addItem(textArea_16.getText());
				comboBox_53.addItem(textArea_16.getText());
				comboBox_54.addItem(textArea_16.getText());
				comboBox_55.addItem(textArea_16.getText());
				comboBox_56.addItem(textArea_16.getText());
				comboBox_57.addItem(textArea_16.getText());
				comboBox_58.addItem(textArea_16.getText());
				comboBox_51.addItem(textArea_18.getText());
				comboBox_52.addItem(textArea_18.getText());
				comboBox_53.addItem(textArea_18.getText());
				comboBox_54.addItem(textArea_18.getText());
				comboBox_55.addItem(textArea_18.getText());
				comboBox_56.addItem(textArea_18.getText());
				comboBox_57.addItem(textArea_18.getText());
				comboBox_58.addItem(textArea_18.getText());
				comboBox_51.addItem(textArea_20.getText());
				comboBox_52.addItem(textArea_20.getText());
				comboBox_53.addItem(textArea_20.getText());
				comboBox_54.addItem(textArea_20.getText());
				comboBox_55.addItem(textArea_20.getText());
				comboBox_56.addItem(textArea_20.getText());
				comboBox_57.addItem(textArea_20.getText());
				comboBox_58.addItem(textArea_20.getText());
			}

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		textArea_14.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent arg0) {
				comboBox_51.removeAllItems();
				comboBox_52.removeAllItems();
				comboBox_53.removeAllItems();
				comboBox_54.removeAllItems();
				comboBox_55.removeAllItems();
				comboBox_56.removeAllItems();
				comboBox_57.removeAllItems();
				comboBox_58.removeAllItems();
				comboBox_51.addItem(textArea_2.getText());
				comboBox_52.addItem(textArea_2.getText());
				comboBox_53.addItem(textArea_2.getText());
				comboBox_54.addItem(textArea_2.getText());
				comboBox_55.addItem(textArea_2.getText());
				comboBox_56.addItem(textArea_2.getText());
				comboBox_57.addItem(textArea_2.getText());
				comboBox_58.addItem(textArea_2.getText());
				comboBox_51.addItem(textArea_4.getText());
				comboBox_52.addItem(textArea_4.getText());
				comboBox_53.addItem(textArea_4.getText());
				comboBox_54.addItem(textArea_4.getText());
				comboBox_55.addItem(textArea_4.getText());
				comboBox_56.addItem(textArea_4.getText());
				comboBox_57.addItem(textArea_4.getText());
				comboBox_58.addItem(textArea_4.getText());
				comboBox_51.addItem(textArea_6.getText());
				comboBox_52.addItem(textArea_6.getText());
				comboBox_53.addItem(textArea_6.getText());
				comboBox_54.addItem(textArea_6.getText());
				comboBox_55.addItem(textArea_6.getText());
				comboBox_56.addItem(textArea_6.getText());
				comboBox_57.addItem(textArea_6.getText());
				comboBox_58.addItem(textArea_6.getText());
				comboBox_51.addItem(textArea_8.getText());
				comboBox_52.addItem(textArea_8.getText());
				comboBox_53.addItem(textArea_8.getText());
				comboBox_54.addItem(textArea_8.getText());
				comboBox_55.addItem(textArea_8.getText());
				comboBox_56.addItem(textArea_8.getText());
				comboBox_57.addItem(textArea_8.getText());
				comboBox_58.addItem(textArea_8.getText());
				comboBox_51.addItem(textArea_10.getText());
				comboBox_52.addItem(textArea_10.getText());
				comboBox_53.addItem(textArea_10.getText());
				comboBox_54.addItem(textArea_10.getText());
				comboBox_55.addItem(textArea_10.getText());
				comboBox_56.addItem(textArea_10.getText());
				comboBox_57.addItem(textArea_10.getText());
				comboBox_58.addItem(textArea_10.getText());
				comboBox_51.addItem(textArea_12.getText());
				comboBox_52.addItem(textArea_12.getText());
				comboBox_53.addItem(textArea_12.getText());
				comboBox_54.addItem(textArea_12.getText());
				comboBox_55.addItem(textArea_12.getText());
				comboBox_56.addItem(textArea_12.getText());
				comboBox_57.addItem(textArea_12.getText());
				comboBox_58.addItem(textArea_12.getText());
				comboBox_51.addItem(textArea_14.getText());
				comboBox_52.addItem(textArea_14.getText());
				comboBox_53.addItem(textArea_14.getText());
				comboBox_54.addItem(textArea_14.getText());
				comboBox_55.addItem(textArea_14.getText());
				comboBox_56.addItem(textArea_14.getText());
				comboBox_57.addItem(textArea_14.getText());
				comboBox_58.addItem(textArea_14.getText());
				comboBox_51.addItem(textArea_16.getText());
				comboBox_52.addItem(textArea_16.getText());
				comboBox_53.addItem(textArea_16.getText());
				comboBox_54.addItem(textArea_16.getText());
				comboBox_55.addItem(textArea_16.getText());
				comboBox_56.addItem(textArea_16.getText());
				comboBox_57.addItem(textArea_16.getText());
				comboBox_58.addItem(textArea_16.getText());
				comboBox_51.addItem(textArea_18.getText());
				comboBox_52.addItem(textArea_18.getText());
				comboBox_53.addItem(textArea_18.getText());
				comboBox_54.addItem(textArea_18.getText());
				comboBox_55.addItem(textArea_18.getText());
				comboBox_56.addItem(textArea_18.getText());
				comboBox_57.addItem(textArea_18.getText());
				comboBox_58.addItem(textArea_18.getText());
				comboBox_51.addItem(textArea_20.getText());
				comboBox_52.addItem(textArea_20.getText());
				comboBox_53.addItem(textArea_20.getText());
				comboBox_54.addItem(textArea_20.getText());
				comboBox_55.addItem(textArea_20.getText());
				comboBox_56.addItem(textArea_20.getText());
				comboBox_57.addItem(textArea_20.getText());
				comboBox_58.addItem(textArea_20.getText());
			}

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		textArea_16.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent arg0) {
				comboBox_51.removeAllItems();
				comboBox_52.removeAllItems();
				comboBox_53.removeAllItems();
				comboBox_54.removeAllItems();
				comboBox_55.removeAllItems();
				comboBox_56.removeAllItems();
				comboBox_57.removeAllItems();
				comboBox_58.removeAllItems();
				comboBox_51.addItem(textArea_2.getText());
				comboBox_52.addItem(textArea_2.getText());
				comboBox_53.addItem(textArea_2.getText());
				comboBox_54.addItem(textArea_2.getText());
				comboBox_55.addItem(textArea_2.getText());
				comboBox_56.addItem(textArea_2.getText());
				comboBox_57.addItem(textArea_2.getText());
				comboBox_58.addItem(textArea_2.getText());
				comboBox_51.addItem(textArea_4.getText());
				comboBox_52.addItem(textArea_4.getText());
				comboBox_53.addItem(textArea_4.getText());
				comboBox_54.addItem(textArea_4.getText());
				comboBox_55.addItem(textArea_4.getText());
				comboBox_56.addItem(textArea_4.getText());
				comboBox_57.addItem(textArea_4.getText());
				comboBox_58.addItem(textArea_4.getText());
				comboBox_51.addItem(textArea_6.getText());
				comboBox_52.addItem(textArea_6.getText());
				comboBox_53.addItem(textArea_6.getText());
				comboBox_54.addItem(textArea_6.getText());
				comboBox_55.addItem(textArea_6.getText());
				comboBox_56.addItem(textArea_6.getText());
				comboBox_57.addItem(textArea_6.getText());
				comboBox_58.addItem(textArea_6.getText());
				comboBox_51.addItem(textArea_8.getText());
				comboBox_52.addItem(textArea_8.getText());
				comboBox_53.addItem(textArea_8.getText());
				comboBox_54.addItem(textArea_8.getText());
				comboBox_55.addItem(textArea_8.getText());
				comboBox_56.addItem(textArea_8.getText());
				comboBox_57.addItem(textArea_8.getText());
				comboBox_58.addItem(textArea_8.getText());
				comboBox_51.addItem(textArea_10.getText());
				comboBox_52.addItem(textArea_10.getText());
				comboBox_53.addItem(textArea_10.getText());
				comboBox_54.addItem(textArea_10.getText());
				comboBox_55.addItem(textArea_10.getText());
				comboBox_56.addItem(textArea_10.getText());
				comboBox_57.addItem(textArea_10.getText());
				comboBox_58.addItem(textArea_10.getText());
				comboBox_51.addItem(textArea_12.getText());
				comboBox_52.addItem(textArea_12.getText());
				comboBox_53.addItem(textArea_12.getText());
				comboBox_54.addItem(textArea_12.getText());
				comboBox_55.addItem(textArea_12.getText());
				comboBox_56.addItem(textArea_12.getText());
				comboBox_57.addItem(textArea_12.getText());
				comboBox_58.addItem(textArea_12.getText());
				comboBox_51.addItem(textArea_14.getText());
				comboBox_52.addItem(textArea_14.getText());
				comboBox_53.addItem(textArea_14.getText());
				comboBox_54.addItem(textArea_14.getText());
				comboBox_55.addItem(textArea_14.getText());
				comboBox_56.addItem(textArea_14.getText());
				comboBox_57.addItem(textArea_14.getText());
				comboBox_58.addItem(textArea_14.getText());
				comboBox_51.addItem(textArea_16.getText());
				comboBox_52.addItem(textArea_16.getText());
				comboBox_53.addItem(textArea_16.getText());
				comboBox_54.addItem(textArea_16.getText());
				comboBox_55.addItem(textArea_16.getText());
				comboBox_56.addItem(textArea_16.getText());
				comboBox_57.addItem(textArea_16.getText());
				comboBox_58.addItem(textArea_16.getText());
				comboBox_51.addItem(textArea_18.getText());
				comboBox_52.addItem(textArea_18.getText());
				comboBox_53.addItem(textArea_18.getText());
				comboBox_54.addItem(textArea_18.getText());
				comboBox_55.addItem(textArea_18.getText());
				comboBox_56.addItem(textArea_18.getText());
				comboBox_57.addItem(textArea_18.getText());
				comboBox_58.addItem(textArea_18.getText());
				comboBox_51.addItem(textArea_20.getText());
				comboBox_52.addItem(textArea_20.getText());
				comboBox_53.addItem(textArea_20.getText());
				comboBox_54.addItem(textArea_20.getText());
				comboBox_55.addItem(textArea_20.getText());
				comboBox_56.addItem(textArea_20.getText());
				comboBox_57.addItem(textArea_20.getText());
				comboBox_58.addItem(textArea_20.getText());
			}

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		textArea_18.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent arg0) {
				comboBox_51.removeAllItems();
				comboBox_52.removeAllItems();
				comboBox_53.removeAllItems();
				comboBox_54.removeAllItems();
				comboBox_55.removeAllItems();
				comboBox_56.removeAllItems();
				comboBox_57.removeAllItems();
				comboBox_58.removeAllItems();
				comboBox_51.addItem(textArea_2.getText());
				comboBox_52.addItem(textArea_2.getText());
				comboBox_53.addItem(textArea_2.getText());
				comboBox_54.addItem(textArea_2.getText());
				comboBox_55.addItem(textArea_2.getText());
				comboBox_56.addItem(textArea_2.getText());
				comboBox_57.addItem(textArea_2.getText());
				comboBox_58.addItem(textArea_2.getText());
				comboBox_51.addItem(textArea_4.getText());
				comboBox_52.addItem(textArea_4.getText());
				comboBox_53.addItem(textArea_4.getText());
				comboBox_54.addItem(textArea_4.getText());
				comboBox_55.addItem(textArea_4.getText());
				comboBox_56.addItem(textArea_4.getText());
				comboBox_57.addItem(textArea_4.getText());
				comboBox_58.addItem(textArea_4.getText());
				comboBox_51.addItem(textArea_6.getText());
				comboBox_52.addItem(textArea_6.getText());
				comboBox_53.addItem(textArea_6.getText());
				comboBox_54.addItem(textArea_6.getText());
				comboBox_55.addItem(textArea_6.getText());
				comboBox_56.addItem(textArea_6.getText());
				comboBox_57.addItem(textArea_6.getText());
				comboBox_58.addItem(textArea_6.getText());
				comboBox_51.addItem(textArea_8.getText());
				comboBox_52.addItem(textArea_8.getText());
				comboBox_53.addItem(textArea_8.getText());
				comboBox_54.addItem(textArea_8.getText());
				comboBox_55.addItem(textArea_8.getText());
				comboBox_56.addItem(textArea_8.getText());
				comboBox_57.addItem(textArea_8.getText());
				comboBox_58.addItem(textArea_8.getText());
				comboBox_51.addItem(textArea_10.getText());
				comboBox_52.addItem(textArea_10.getText());
				comboBox_53.addItem(textArea_10.getText());
				comboBox_54.addItem(textArea_10.getText());
				comboBox_55.addItem(textArea_10.getText());
				comboBox_56.addItem(textArea_10.getText());
				comboBox_57.addItem(textArea_10.getText());
				comboBox_58.addItem(textArea_10.getText());
				comboBox_51.addItem(textArea_12.getText());
				comboBox_52.addItem(textArea_12.getText());
				comboBox_53.addItem(textArea_12.getText());
				comboBox_54.addItem(textArea_12.getText());
				comboBox_55.addItem(textArea_12.getText());
				comboBox_56.addItem(textArea_12.getText());
				comboBox_57.addItem(textArea_12.getText());
				comboBox_58.addItem(textArea_12.getText());
				comboBox_51.addItem(textArea_14.getText());
				comboBox_52.addItem(textArea_14.getText());
				comboBox_53.addItem(textArea_14.getText());
				comboBox_54.addItem(textArea_14.getText());
				comboBox_55.addItem(textArea_14.getText());
				comboBox_56.addItem(textArea_14.getText());
				comboBox_57.addItem(textArea_14.getText());
				comboBox_58.addItem(textArea_14.getText());
				comboBox_51.addItem(textArea_16.getText());
				comboBox_52.addItem(textArea_16.getText());
				comboBox_53.addItem(textArea_16.getText());
				comboBox_54.addItem(textArea_16.getText());
				comboBox_55.addItem(textArea_16.getText());
				comboBox_56.addItem(textArea_16.getText());
				comboBox_57.addItem(textArea_16.getText());
				comboBox_58.addItem(textArea_16.getText());
				comboBox_51.addItem(textArea_18.getText());
				comboBox_52.addItem(textArea_18.getText());
				comboBox_53.addItem(textArea_18.getText());
				comboBox_54.addItem(textArea_18.getText());
				comboBox_55.addItem(textArea_18.getText());
				comboBox_56.addItem(textArea_18.getText());
				comboBox_57.addItem(textArea_18.getText());
				comboBox_58.addItem(textArea_18.getText());
				comboBox_51.addItem(textArea_20.getText());
				comboBox_52.addItem(textArea_20.getText());
				comboBox_53.addItem(textArea_20.getText());
				comboBox_54.addItem(textArea_20.getText());
				comboBox_55.addItem(textArea_20.getText());
				comboBox_56.addItem(textArea_20.getText());
				comboBox_57.addItem(textArea_20.getText());
				comboBox_58.addItem(textArea_20.getText());
			}

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		textArea_20.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent arg0) {
				comboBox_51.removeAllItems();
				comboBox_52.removeAllItems();
				comboBox_53.removeAllItems();
				comboBox_54.removeAllItems();
				comboBox_55.removeAllItems();
				comboBox_56.removeAllItems();
				comboBox_57.removeAllItems();
				comboBox_58.removeAllItems();
				comboBox_51.addItem(textArea_2.getText());
				comboBox_52.addItem(textArea_2.getText());
				comboBox_53.addItem(textArea_2.getText());
				comboBox_54.addItem(textArea_2.getText());
				comboBox_55.addItem(textArea_2.getText());
				comboBox_56.addItem(textArea_2.getText());
				comboBox_57.addItem(textArea_2.getText());
				comboBox_58.addItem(textArea_2.getText());
				comboBox_51.addItem(textArea_4.getText());
				comboBox_52.addItem(textArea_4.getText());
				comboBox_53.addItem(textArea_4.getText());
				comboBox_54.addItem(textArea_4.getText());
				comboBox_55.addItem(textArea_4.getText());
				comboBox_56.addItem(textArea_4.getText());
				comboBox_57.addItem(textArea_4.getText());
				comboBox_58.addItem(textArea_4.getText());
				comboBox_51.addItem(textArea_6.getText());
				comboBox_52.addItem(textArea_6.getText());
				comboBox_53.addItem(textArea_6.getText());
				comboBox_54.addItem(textArea_6.getText());
				comboBox_55.addItem(textArea_6.getText());
				comboBox_56.addItem(textArea_6.getText());
				comboBox_57.addItem(textArea_6.getText());
				comboBox_58.addItem(textArea_6.getText());
				comboBox_51.addItem(textArea_8.getText());
				comboBox_52.addItem(textArea_8.getText());
				comboBox_53.addItem(textArea_8.getText());
				comboBox_54.addItem(textArea_8.getText());
				comboBox_55.addItem(textArea_8.getText());
				comboBox_56.addItem(textArea_8.getText());
				comboBox_57.addItem(textArea_8.getText());
				comboBox_58.addItem(textArea_8.getText());
				comboBox_51.addItem(textArea_10.getText());
				comboBox_52.addItem(textArea_10.getText());
				comboBox_53.addItem(textArea_10.getText());
				comboBox_54.addItem(textArea_10.getText());
				comboBox_55.addItem(textArea_10.getText());
				comboBox_56.addItem(textArea_10.getText());
				comboBox_57.addItem(textArea_10.getText());
				comboBox_58.addItem(textArea_10.getText());
				comboBox_51.addItem(textArea_12.getText());
				comboBox_52.addItem(textArea_12.getText());
				comboBox_53.addItem(textArea_12.getText());
				comboBox_54.addItem(textArea_12.getText());
				comboBox_55.addItem(textArea_12.getText());
				comboBox_56.addItem(textArea_12.getText());
				comboBox_57.addItem(textArea_12.getText());
				comboBox_58.addItem(textArea_12.getText());
				comboBox_51.addItem(textArea_14.getText());
				comboBox_52.addItem(textArea_14.getText());
				comboBox_53.addItem(textArea_14.getText());
				comboBox_54.addItem(textArea_14.getText());
				comboBox_55.addItem(textArea_14.getText());
				comboBox_56.addItem(textArea_14.getText());
				comboBox_57.addItem(textArea_14.getText());
				comboBox_58.addItem(textArea_14.getText());
				comboBox_51.addItem(textArea_16.getText());
				comboBox_52.addItem(textArea_16.getText());
				comboBox_53.addItem(textArea_16.getText());
				comboBox_54.addItem(textArea_16.getText());
				comboBox_55.addItem(textArea_16.getText());
				comboBox_56.addItem(textArea_16.getText());
				comboBox_57.addItem(textArea_16.getText());
				comboBox_58.addItem(textArea_16.getText());
				comboBox_51.addItem(textArea_18.getText());
				comboBox_52.addItem(textArea_18.getText());
				comboBox_53.addItem(textArea_18.getText());
				comboBox_54.addItem(textArea_18.getText());
				comboBox_55.addItem(textArea_18.getText());
				comboBox_56.addItem(textArea_18.getText());
				comboBox_57.addItem(textArea_18.getText());
				comboBox_58.addItem(textArea_18.getText());
				comboBox_51.addItem(textArea_20.getText());
				comboBox_52.addItem(textArea_20.getText());
				comboBox_53.addItem(textArea_20.getText());
				comboBox_54.addItem(textArea_20.getText());
				comboBox_55.addItem(textArea_20.getText());
				comboBox_56.addItem(textArea_20.getText());
				comboBox_57.addItem(textArea_20.getText());
				comboBox_58.addItem(textArea_20.getText());
			}

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
			
		JComboBox opNumbers = new JComboBox(opod.getNumServers());
		opNumbers.setBounds(372, 111, 241, 20);
		OPOD.add(opNumbers);
		opNumbers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(opNumbers.getSelectedItem().toString().equals("1")) {
					textArea_1.setVisible(true);
					textArea_2.setVisible(true);
					textArea_3.setVisible(false);
					textArea_4.setVisible(false);
					textArea_5.setVisible(false);
					textArea_6.setVisible(false);
					textArea_7.setVisible(false);
					textArea_8.setVisible(false);
					textArea_9.setVisible(false);
					textArea_10.setVisible(false);
					textArea_11.setVisible(false);
					textArea_12.setVisible(false);
					textArea_13.setVisible(false);
					textArea_14.setVisible(false);
					textArea_15.setVisible(false);
					textArea_16.setVisible(false);
					textArea_17.setVisible(false);
					textArea_18.setVisible(false);
					textArea_19.setVisible(false);
					textArea_20.setVisible(false);
				}
				if(opNumbers.getSelectedItem().toString().equals("2")) {
					textArea_1.setVisible(true);
					textArea_2.setVisible(true);
					textArea_3.setVisible(true);
					textArea_4.setVisible(true);
					textArea_5.setVisible(false);
					textArea_6.setVisible(false);
					textArea_7.setVisible(false);
					textArea_8.setVisible(false);
					textArea_9.setVisible(false);
					textArea_10.setVisible(false);
					textArea_11.setVisible(false);
					textArea_12.setVisible(false);
					textArea_13.setVisible(false);
					textArea_14.setVisible(false);
					textArea_15.setVisible(false);
					textArea_16.setVisible(false);
					textArea_17.setVisible(false);
					textArea_18.setVisible(false);
					textArea_19.setVisible(false);
					textArea_20.setVisible(false);
				}
				if(opNumbers.getSelectedItem().toString().equals("3")) {
					textArea_1.setVisible(true);
					textArea_2.setVisible(true);
					textArea_3.setVisible(true);
					textArea_4.setVisible(true);
					textArea_5.setVisible(true);
					textArea_6.setVisible(true);
					textArea_7.setVisible(false);
					textArea_8.setVisible(false);
					textArea_9.setVisible(false);
					textArea_10.setVisible(false);
					textArea_11.setVisible(false);
					textArea_12.setVisible(false);
					textArea_13.setVisible(false);
					textArea_14.setVisible(false);
					textArea_15.setVisible(false);
					textArea_16.setVisible(false);
					textArea_17.setVisible(false);
					textArea_18.setVisible(false);
					textArea_19.setVisible(false);
					textArea_20.setVisible(false);
				}
				if(opNumbers.getSelectedItem().toString().equals("4")) {
					textArea_1.setVisible(true);
					textArea_2.setVisible(true);
					textArea_3.setVisible(true);
					textArea_4.setVisible(true);
					textArea_5.setVisible(true);
					textArea_6.setVisible(true);
					textArea_7.setVisible(true);
					textArea_8.setVisible(true);
					textArea_9.setVisible(false);
					textArea_10.setVisible(false);
					textArea_11.setVisible(false);
					textArea_12.setVisible(false);
					textArea_13.setVisible(false);
					textArea_14.setVisible(false);
					textArea_15.setVisible(false);
					textArea_16.setVisible(false);
					textArea_17.setVisible(false);
					textArea_18.setVisible(false);
					textArea_19.setVisible(false);
					textArea_20.setVisible(false);
				}
				if(opNumbers.getSelectedItem().toString().equals("5")) {
					textArea_1.setVisible(true);
					textArea_2.setVisible(true);
					textArea_3.setVisible(true);
					textArea_4.setVisible(true);
					textArea_5.setVisible(true);
					textArea_6.setVisible(true);
					textArea_7.setVisible(true);
					textArea_8.setVisible(true);
					textArea_9.setVisible(true);
					textArea_10.setVisible(true);
					textArea_11.setVisible(false);
					textArea_12.setVisible(false);
					textArea_13.setVisible(false);
					textArea_14.setVisible(false);
					textArea_15.setVisible(false);
					textArea_16.setVisible(false);
					textArea_17.setVisible(false);
					textArea_18.setVisible(false);
					textArea_19.setVisible(false);
					textArea_20.setVisible(false);
				}
				if(opNumbers.getSelectedItem().toString().equals("6")) {
					textArea_1.setVisible(true);
					textArea_2.setVisible(true);
					textArea_3.setVisible(true);
					textArea_4.setVisible(true);
					textArea_5.setVisible(true);
					textArea_6.setVisible(true);
					textArea_7.setVisible(true);
					textArea_8.setVisible(true);
					textArea_9.setVisible(true);
					textArea_10.setVisible(true);
					textArea_11.setVisible(true);
					textArea_12.setVisible(true);
					textArea_13.setVisible(false);
					textArea_14.setVisible(false);
					textArea_15.setVisible(false);
					textArea_16.setVisible(false);
					textArea_17.setVisible(false);
					textArea_18.setVisible(false);
					textArea_19.setVisible(false);
					textArea_20.setVisible(false);
				}
				if(opNumbers.getSelectedItem().toString().equals("7")) {
					textArea_1.setVisible(true);
					textArea_2.setVisible(true);
					textArea_3.setVisible(true);
					textArea_4.setVisible(true);
					textArea_5.setVisible(true);
					textArea_6.setVisible(true);
					textArea_7.setVisible(true);
					textArea_8.setVisible(true);
					textArea_9.setVisible(true);
					textArea_10.setVisible(true);
					textArea_11.setVisible(true);
					textArea_12.setVisible(true);
					textArea_13.setVisible(true);
					textArea_14.setVisible(true);
					textArea_15.setVisible(false);
					textArea_16.setVisible(false);
					textArea_17.setVisible(false);
					textArea_18.setVisible(false);
					textArea_19.setVisible(false);
					textArea_20.setVisible(false);
				}
				if(opNumbers.getSelectedItem().toString().equals("8")) {
					textArea_1.setVisible(true);
					textArea_2.setVisible(true);
					textArea_3.setVisible(true);
					textArea_4.setVisible(true);
					textArea_5.setVisible(true);
					textArea_6.setVisible(true);
					textArea_7.setVisible(true);
					textArea_8.setVisible(true);
					textArea_9.setVisible(true);
					textArea_10.setVisible(true);
					textArea_11.setVisible(true);
					textArea_12.setVisible(true);
					textArea_13.setVisible(true);
					textArea_14.setVisible(true);
					textArea_15.setVisible(true);
					textArea_16.setVisible(true);
					textArea_17.setVisible(false);
					textArea_18.setVisible(false);
					textArea_19.setVisible(false);
					textArea_20.setVisible(false);
				}
				if(opNumbers.getSelectedItem().toString().equals("9")) {
					textArea_1.setVisible(true);
					textArea_2.setVisible(true);
					textArea_3.setVisible(true);
					textArea_4.setVisible(true);
					textArea_5.setVisible(true);
					textArea_6.setVisible(true);
					textArea_7.setVisible(true);
					textArea_8.setVisible(true);
					textArea_9.setVisible(true);
					textArea_10.setVisible(true);
					textArea_11.setVisible(true);
					textArea_12.setVisible(true);
					textArea_13.setVisible(true);
					textArea_14.setVisible(true);
					textArea_15.setVisible(true);
					textArea_16.setVisible(true);
					textArea_17.setVisible(true);
					textArea_18.setVisible(true);
					textArea_19.setVisible(false);
					textArea_20.setVisible(false);
				}
				if(opNumbers.getSelectedItem().toString().equals("10")) {
					textArea_1.setVisible(true);
					textArea_2.setVisible(true);
					textArea_3.setVisible(true);
					textArea_4.setVisible(true);
					textArea_5.setVisible(true);
					textArea_6.setVisible(true);
					textArea_7.setVisible(true);
					textArea_8.setVisible(true);
					textArea_9.setVisible(true);
					textArea_10.setVisible(true);
					textArea_11.setVisible(true);
					textArea_12.setVisible(true);
					textArea_13.setVisible(true);
					textArea_14.setVisible(true);
					textArea_15.setVisible(true);
					textArea_16.setVisible(true);
					textArea_17.setVisible(true);
					textArea_18.setVisible(true);
					textArea_19.setVisible(true);
					textArea_20.setVisible(true);
				}
			}
		});
		
		JLabel label_10 = new JLabel("Environment Selection:");
		label_10.setBounds(10, 163, 161, 14);
		OPOD.add(label_10);
		
		JComboBox comboBox_26 = new JComboBox(opod.getEnvironmentOpts());
		comboBox_26.setBounds(256, 160, 241, 20);
		OPOD.add(comboBox_26);
		
		JComboBox comboBox_27 = new JComboBox(opod.getBusinessUnitsOpts());
		comboBox_27.setBounds(256, 208, 241, 20);
		OPOD.add(comboBox_27);
		
		JLabel label_11 = new JLabel("Business Unit:");
		label_11.setBounds(10, 211, 112, 14);
		OPOD.add(label_11);
		
		JComboBox comboBox_25 = new JComboBox(opod.getSecurityZoneRochelleOpts());
		comboBox_25.setVisible(false);
		comboBox_25.setBounds(256, 339, 241, 20);
		OPOD.add(comboBox_25);
		
		JComboBox comboBox_34 = new JComboBox(opod.getSecurityZoneHudsonOpts());
		comboBox_34.setVisible(false);
		comboBox_34.setBounds(256, 339, 241, 20);
		OPOD.add(comboBox_34);
		
		JComboBox comboBox_28 = new JComboBox(opod.getDataCenterOpts());
		comboBox_28.setBounds(256, 254, 241, 20);
		OPOD.add(comboBox_28);
		
		JComboBox comboBox_30 = new JComboBox(opod.getSecurityZoneZeroOpts());
		comboBox_30.setBounds(256, 339, 241, 20);
		OPOD.add(comboBox_30);
		
		JComboBox comboBox_31 = new JComboBox(opod.getWingLocationOpts());
		comboBox_31.setBounds(256, 383, 241, 20);
		comboBox_31.setVisible(false);
		OPOD.add(comboBox_31);
		
		comboBox_28.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(comboBox_28.getSelectedItem().equals("Hudson")) {
					comboBox_34.setVisible(true);
					comboBox_25.setVisible(false);
					comboBox_30.setVisible(false);
					comboBox_31.setVisible(true);

				}
				else if(comboBox_28.getSelectedItem().equals("Rochelle")) {
					comboBox_34.setVisible(false);
					comboBox_25.setVisible(true);
					comboBox_30.setVisible(false);
					comboBox_31.setVisible(false);
				}
				else {
					comboBox_34.setVisible(false);
					comboBox_25.setVisible(false);
					comboBox_30.setVisible(true);
					comboBox_31.setVisible(false);
				}
			}
			
		});	
		
		JLabel label_12 = new JLabel("Data Center:");
		label_12.setBounds(10, 257, 76, 14);
		OPOD.add(label_12);
		
		JComboBox comboBox_29 = new JComboBox(opod.getEnterpriseControllerOpts());
		comboBox_29.setBounds(256, 296, 241, 20);
		OPOD.add(comboBox_29);
		
		JLabel label_13 = new JLabel("Enterprise Controller:");
		label_13.setBounds(10, 299, 192, 14);
		OPOD.add(label_13);
		
		
		JLabel label_14 = new JLabel("Security Zone:");
		label_14.setBounds(10, 342, 112, 14);
		OPOD.add(label_14);
		
		JLabel lblWingLocationIn = new JLabel("Wing Location in the DC:");
		lblWingLocationIn.setBounds(10, 386, 148, 14);
		OPOD.add(lblWingLocationIn);
		
		
		JLabel lblVpoolStorageArray = new JLabel("vpool Storage Array:");
		lblVpoolStorageArray.setBounds(10, 430, 148, 14);
		OPOD.add(lblVpoolStorageArray);
		
		JComboBox comboBox_32 = new JComboBox(opod.getVpoolStorageOpts());
		comboBox_32.setBounds(256, 430, 241, 20);
		OPOD.add(comboBox_32);
		
		JLabel lblClusterName = new JLabel("Cluster Name:");
		lblClusterName.setBounds(10, 476, 112, 14);
		OPOD.add(lblClusterName);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(256, 471, 241, 22);
		OPOD.add(textArea);
		
		JLabel lblHowManyDatabases = new JLabel("How many Databases (Max. 8)?");
		lblHowManyDatabases.setBounds(10, 521, 214, 14);
		OPOD.add(lblHowManyDatabases);
		
		JTextArea textArea_21 = new JTextArea();
		textArea_21.setBounds(10, 585, 148, 22);
		OPOD.add(textArea_21);
		
		JTextArea textArea_22 = new JTextArea();
		textArea_22.setVisible(false);
		textArea_22.setBounds(10, 620, 148, 22);
		OPOD.add(textArea_22);
		
		JTextArea textArea_23 = new JTextArea();
		textArea_23.setVisible(false);
		textArea_23.setBounds(10, 653, 148, 22);
		OPOD.add(textArea_23);
		
		JTextArea textArea_24 = new JTextArea();
		textArea_24.setVisible(false);
		textArea_24.setBounds(10, 686, 148, 22);
		OPOD.add(textArea_24);
		
		JTextArea textArea_25 = new JTextArea();
		textArea_25.setVisible(false);
		textArea_25.setBounds(10, 719, 148, 22);
		OPOD.add(textArea_25);
		
		JTextArea textArea_26 = new JTextArea();
		textArea_26.setVisible(false);
		textArea_26.setBounds(10, 752, 148, 22);
		OPOD.add(textArea_26);
		
		JTextArea textArea_27 = new JTextArea();
		textArea_27.setVisible(false);
		textArea_27.setBounds(10, 785, 148, 22);
		OPOD.add(textArea_27);
		
		JTextArea textArea_28 = new JTextArea();
		textArea_28.setVisible(false);
		textArea_28.setBounds(10, 818, 148, 22);
		OPOD.add(textArea_28);
		
		JComboBox comboBox_35 = new JComboBox(opod.getVersionOpts());
		comboBox_35.setBounds(168, 587, 161, 20);
		OPOD.add(comboBox_35);
		
		JComboBox comboBox_36 = new JComboBox(opod.getVersionOpts());
		comboBox_36.setVisible(false);
		comboBox_36.setBounds(168, 622, 161, 20);
		OPOD.add(comboBox_36);
		
		JComboBox comboBox_37 = new JComboBox(opod.getVersionOpts());
		comboBox_37.setVisible(false);
		comboBox_37.setBounds(168, 655, 161, 20);
		OPOD.add(comboBox_37);
		
		JComboBox comboBox_38 = new JComboBox(opod.getVersionOpts());
		comboBox_38.setVisible(false);
		comboBox_38.setBounds(168, 688, 161, 20);
		OPOD.add(comboBox_38);
		
		JComboBox comboBox_39 = new JComboBox(opod.getVersionOpts());
		comboBox_39.setVisible(false);
		comboBox_39.setBounds(168, 721, 161, 20);
		OPOD.add(comboBox_39);
		
		JComboBox comboBox_40 = new JComboBox(opod.getVersionOpts());
		comboBox_40.setVisible(false);
		comboBox_40.setBounds(168, 754, 161, 20);
		OPOD.add(comboBox_40);
		
		JComboBox comboBox_41 = new JComboBox(opod.getVersionOpts());
		comboBox_41.setVisible(false);
		comboBox_41.setBounds(168, 787, 161, 20);
		OPOD.add(comboBox_41);
		
		JComboBox comboBox_42 = new JComboBox(opod.getVersionOpts());
		comboBox_42.setVisible(false);
		comboBox_42.setBounds(168, 820, 161, 20);
		OPOD.add(comboBox_42);
		
		

		
		JComboBox comboBox_59 = new JComboBox(opod.getBackupTypeOpts());
		comboBox_59.setBounds(681, 587, 161, 20);
		OPOD.add(comboBox_59);
		
		JComboBox comboBox_60 = new JComboBox(opod.getBackupTypeOpts());
		comboBox_60.setVisible(false);
		comboBox_60.setBounds(681, 622, 161, 20);
		OPOD.add(comboBox_60);
		
		JComboBox comboBox_61 = new JComboBox(opod.getBackupTypeOpts());
		comboBox_61.setVisible(false);
		comboBox_61.setBounds(681, 655, 161, 20);
		OPOD.add(comboBox_61);
		
		JComboBox comboBox_62 = new JComboBox(opod.getBackupTypeOpts());
		comboBox_62.setVisible(false);
		comboBox_62.setBounds(681, 688, 161, 20);
		OPOD.add(comboBox_62);
		
		JComboBox comboBox_63 = new JComboBox(opod.getBackupTypeOpts());
		comboBox_63.setVisible(false);
		comboBox_63.setBounds(681, 721, 161, 20);
		OPOD.add(comboBox_63);
		
		JComboBox comboBox_64 = new JComboBox(opod.getBackupTypeOpts());
		comboBox_64.setVisible(false);
		comboBox_64.setBounds(681, 754, 161, 20);
		OPOD.add(comboBox_64);
		
		JComboBox comboBox_65 = new JComboBox(opod.getBackupTypeOpts());
		comboBox_65.setVisible(false);
		comboBox_65.setBounds(681, 787, 161, 20);
		OPOD.add(comboBox_65);
		
		JComboBox comboBox_66 = new JComboBox(opod.getBackupTypeOpts());
		comboBox_66.setVisible(false);
		comboBox_66.setBounds(681, 820, 161, 20);
		OPOD.add(comboBox_66);
		
		JComboBox comboBox_33 = new JComboBox(opod.getNumDatabasesOpts());
		comboBox_33.setBounds(256, 518, 241, 20);
		OPOD.add(comboBox_33);
		comboBox_33.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboBox_33.getSelectedItem().toString().equals("1")) {
					textArea_21.setVisible(true);
					textArea_22.setVisible(false);
					textArea_23.setVisible(false);
					textArea_24.setVisible(false);
					textArea_25.setVisible(false);
					textArea_26.setVisible(false);
					textArea_27.setVisible(false);
					textArea_28.setVisible(false);
					comboBox_35.setVisible(true);
					comboBox_36.setVisible(false);
					comboBox_37.setVisible(false);
					comboBox_38.setVisible(false);
					comboBox_39.setVisible(false);
					comboBox_40.setVisible(false);
					comboBox_41.setVisible(false);
					comboBox_42.setVisible(false);
					comboBox_43.setVisible(true);
					comboBox_44.setVisible(false);
					comboBox_45.setVisible(false);
					comboBox_46.setVisible(false);
					comboBox_47.setVisible(false);
					comboBox_48.setVisible(false);
					comboBox_49.setVisible(false);
					comboBox_50.setVisible(false);
					comboBox_51.setVisible(true);
					comboBox_52.setVisible(false);
					comboBox_53.setVisible(false);
					comboBox_54.setVisible(false);
					comboBox_55.setVisible(false);
					comboBox_56.setVisible(false);
					comboBox_57.setVisible(false);
					comboBox_58.setVisible(false);
					comboBox_59.setVisible(true);
					comboBox_60.setVisible(false);
					comboBox_61.setVisible(false);
					comboBox_62.setVisible(false);
					comboBox_63.setVisible(false);
					comboBox_64.setVisible(false);
					comboBox_65.setVisible(false);
					comboBox_66.setVisible(false);
				}
				if(comboBox_33.getSelectedItem().toString().equals("2")) {
					textArea_21.setVisible(true);
					textArea_22.setVisible(true);
					textArea_23.setVisible(false);
					textArea_24.setVisible(false);
					textArea_25.setVisible(false);
					textArea_26.setVisible(false);
					textArea_27.setVisible(false);
					textArea_28.setVisible(false);
					comboBox_35.setVisible(true);
					comboBox_36.setVisible(true);
					comboBox_37.setVisible(false);
					comboBox_38.setVisible(false);
					comboBox_39.setVisible(false);
					comboBox_40.setVisible(false);
					comboBox_41.setVisible(false);
					comboBox_42.setVisible(false);
					comboBox_43.setVisible(true);
					comboBox_44.setVisible(true);
					comboBox_45.setVisible(false);
					comboBox_46.setVisible(false);
					comboBox_47.setVisible(false);
					comboBox_48.setVisible(false);
					comboBox_49.setVisible(false);
					comboBox_50.setVisible(false);
					comboBox_51.setVisible(true);
					comboBox_52.setVisible(true);
					comboBox_53.setVisible(false);
					comboBox_54.setVisible(false);
					comboBox_55.setVisible(false);
					comboBox_56.setVisible(false);
					comboBox_57.setVisible(false);
					comboBox_58.setVisible(false);
					comboBox_59.setVisible(true);
					comboBox_60.setVisible(true);
					comboBox_61.setVisible(false);
					comboBox_62.setVisible(false);
					comboBox_63.setVisible(false);
					comboBox_64.setVisible(false);
					comboBox_65.setVisible(false);
					comboBox_66.setVisible(false);
				}
				if(comboBox_33.getSelectedItem().toString().equals("3")) {
					textArea_21.setVisible(true);
					textArea_22.setVisible(true);
					textArea_23.setVisible(true);
					textArea_24.setVisible(false);
					textArea_25.setVisible(false);
					textArea_26.setVisible(false);
					textArea_27.setVisible(false);
					textArea_28.setVisible(false);
					comboBox_35.setVisible(true);
					comboBox_36.setVisible(true);
					comboBox_37.setVisible(true);
					comboBox_38.setVisible(false);
					comboBox_39.setVisible(false);
					comboBox_40.setVisible(false);
					comboBox_41.setVisible(false);
					comboBox_42.setVisible(false);
					comboBox_43.setVisible(true);
					comboBox_44.setVisible(true);
					comboBox_45.setVisible(true);
					comboBox_46.setVisible(false);
					comboBox_47.setVisible(false);
					comboBox_48.setVisible(false);
					comboBox_49.setVisible(false);
					comboBox_50.setVisible(false);
					comboBox_51.setVisible(true);
					comboBox_52.setVisible(true);
					comboBox_53.setVisible(true);
					comboBox_54.setVisible(false);
					comboBox_55.setVisible(false);
					comboBox_56.setVisible(false);
					comboBox_57.setVisible(false);
					comboBox_58.setVisible(false);
					comboBox_59.setVisible(true);
					comboBox_60.setVisible(true);
					comboBox_61.setVisible(true);
					comboBox_62.setVisible(false);
					comboBox_63.setVisible(false);
					comboBox_64.setVisible(false);
					comboBox_65.setVisible(false);
					comboBox_66.setVisible(false);
				}
				if(comboBox_33.getSelectedItem().toString().equals("4")) {
					textArea_21.setVisible(true);
					textArea_22.setVisible(true);
					textArea_23.setVisible(true);
					textArea_24.setVisible(true);
					textArea_25.setVisible(false);
					textArea_26.setVisible(false);
					textArea_27.setVisible(false);
					textArea_28.setVisible(false);
					comboBox_35.setVisible(true);
					comboBox_36.setVisible(true);
					comboBox_37.setVisible(true);
					comboBox_38.setVisible(true);
					comboBox_39.setVisible(false);
					comboBox_40.setVisible(false);
					comboBox_41.setVisible(false);
					comboBox_42.setVisible(false);
					comboBox_43.setVisible(true);
					comboBox_44.setVisible(true);
					comboBox_45.setVisible(true);
					comboBox_46.setVisible(true);
					comboBox_47.setVisible(false);
					comboBox_48.setVisible(false);
					comboBox_49.setVisible(false);
					comboBox_50.setVisible(false);
					comboBox_51.setVisible(true);
					comboBox_52.setVisible(true);
					comboBox_53.setVisible(true);
					comboBox_54.setVisible(true);
					comboBox_55.setVisible(false);
					comboBox_56.setVisible(false);
					comboBox_57.setVisible(false);
					comboBox_58.setVisible(false);
					comboBox_59.setVisible(true);
					comboBox_60.setVisible(true);
					comboBox_61.setVisible(true);
					comboBox_62.setVisible(true);
					comboBox_63.setVisible(false);
					comboBox_64.setVisible(false);
					comboBox_65.setVisible(false);
					comboBox_66.setVisible(false);
				}
				if(comboBox_33.getSelectedItem().toString().equals("5")) {
					textArea_21.setVisible(true);
					textArea_22.setVisible(true);
					textArea_23.setVisible(true);
					textArea_24.setVisible(true);
					textArea_25.setVisible(true);
					textArea_26.setVisible(false);
					textArea_27.setVisible(false);
					textArea_28.setVisible(false);
					comboBox_35.setVisible(true);
					comboBox_36.setVisible(true);
					comboBox_37.setVisible(true);
					comboBox_38.setVisible(true);
					comboBox_39.setVisible(true);
					comboBox_40.setVisible(false);
					comboBox_41.setVisible(false);
					comboBox_42.setVisible(false);
					comboBox_43.setVisible(true);
					comboBox_44.setVisible(true);
					comboBox_45.setVisible(true);
					comboBox_46.setVisible(true);
					comboBox_47.setVisible(true);
					comboBox_48.setVisible(false);
					comboBox_49.setVisible(false);
					comboBox_50.setVisible(false);
					comboBox_51.setVisible(true);
					comboBox_52.setVisible(true);
					comboBox_53.setVisible(true);
					comboBox_54.setVisible(true);
					comboBox_55.setVisible(true);
					comboBox_56.setVisible(false);
					comboBox_57.setVisible(false);
					comboBox_58.setVisible(false);
					comboBox_59.setVisible(true);
					comboBox_60.setVisible(true);
					comboBox_61.setVisible(true);
					comboBox_62.setVisible(true);
					comboBox_63.setVisible(true);
					comboBox_64.setVisible(false);
					comboBox_65.setVisible(false);
					comboBox_66.setVisible(false);
				}
				if(comboBox_33.getSelectedItem().toString().equals("6")) {
					textArea_21.setVisible(true);
					textArea_22.setVisible(true);
					textArea_23.setVisible(true);
					textArea_24.setVisible(true);
					textArea_25.setVisible(true);
					textArea_26.setVisible(true);
					textArea_27.setVisible(false);
					textArea_28.setVisible(false);
					comboBox_35.setVisible(true);
					comboBox_36.setVisible(true);
					comboBox_37.setVisible(true);
					comboBox_38.setVisible(true);
					comboBox_39.setVisible(true);
					comboBox_40.setVisible(true);
					comboBox_41.setVisible(false);
					comboBox_42.setVisible(false);
					comboBox_43.setVisible(true);
					comboBox_44.setVisible(true);
					comboBox_45.setVisible(true);
					comboBox_46.setVisible(true);
					comboBox_47.setVisible(true);
					comboBox_48.setVisible(true);
					comboBox_49.setVisible(false);
					comboBox_50.setVisible(false);
					comboBox_51.setVisible(true);
					comboBox_52.setVisible(true);
					comboBox_53.setVisible(true);
					comboBox_54.setVisible(true);
					comboBox_55.setVisible(true);
					comboBox_56.setVisible(true);
					comboBox_57.setVisible(false);
					comboBox_58.setVisible(false);
					comboBox_59.setVisible(true);
					comboBox_60.setVisible(true);
					comboBox_61.setVisible(true);
					comboBox_62.setVisible(true);
					comboBox_63.setVisible(true);
					comboBox_64.setVisible(true);
					comboBox_65.setVisible(false);
					comboBox_66.setVisible(false);
				}
				if(comboBox_33.getSelectedItem().toString().equals("7")) {
					textArea_21.setVisible(true);
					textArea_22.setVisible(true);
					textArea_23.setVisible(true);
					textArea_24.setVisible(true);
					textArea_25.setVisible(true);
					textArea_26.setVisible(true);
					textArea_27.setVisible(true);
					textArea_28.setVisible(false);
					comboBox_35.setVisible(true);
					comboBox_36.setVisible(true);
					comboBox_37.setVisible(true);
					comboBox_38.setVisible(true);
					comboBox_39.setVisible(true);
					comboBox_40.setVisible(true);
					comboBox_41.setVisible(true);
					comboBox_42.setVisible(false);
					comboBox_43.setVisible(true);
					comboBox_44.setVisible(true);
					comboBox_45.setVisible(true);
					comboBox_46.setVisible(true);
					comboBox_47.setVisible(true);
					comboBox_48.setVisible(true);
					comboBox_49.setVisible(true);
					comboBox_50.setVisible(false);
					comboBox_51.setVisible(true);
					comboBox_52.setVisible(true);
					comboBox_53.setVisible(true);
					comboBox_54.setVisible(true);
					comboBox_55.setVisible(true);
					comboBox_56.setVisible(true);
					comboBox_57.setVisible(true);
					comboBox_58.setVisible(false);
					comboBox_59.setVisible(true);
					comboBox_60.setVisible(true);
					comboBox_61.setVisible(true);
					comboBox_62.setVisible(true);
					comboBox_63.setVisible(true);
					comboBox_64.setVisible(true);
					comboBox_65.setVisible(true);
					comboBox_66.setVisible(false);
				}
				if(comboBox_33.getSelectedItem().toString().equals("8")) {
					textArea_21.setVisible(true);
					textArea_22.setVisible(true);
					textArea_23.setVisible(true);
					textArea_24.setVisible(true);
					textArea_25.setVisible(true);
					textArea_26.setVisible(true);
					textArea_27.setVisible(true);
					textArea_28.setVisible(true);
					comboBox_35.setVisible(true);
					comboBox_36.setVisible(true);
					comboBox_37.setVisible(true);
					comboBox_38.setVisible(true);
					comboBox_39.setVisible(true);
					comboBox_40.setVisible(true);
					comboBox_41.setVisible(true);
					comboBox_42.setVisible(true);
					comboBox_43.setVisible(true);
					comboBox_44.setVisible(true);
					comboBox_45.setVisible(true);
					comboBox_46.setVisible(true);
					comboBox_47.setVisible(true);
					comboBox_48.setVisible(true);
					comboBox_49.setVisible(true);
					comboBox_50.setVisible(true);
					comboBox_51.setVisible(true);
					comboBox_52.setVisible(true);
					comboBox_53.setVisible(true);
					comboBox_54.setVisible(true);
					comboBox_55.setVisible(true);
					comboBox_56.setVisible(true);
					comboBox_57.setVisible(true);
					comboBox_58.setVisible(true);
					comboBox_59.setVisible(true);
					comboBox_60.setVisible(true);
					comboBox_61.setVisible(true);
					comboBox_62.setVisible(true);
					comboBox_63.setVisible(true);
					comboBox_64.setVisible(true);
					comboBox_65.setVisible(true);
					comboBox_66.setVisible(true);
				}

			}
		});
		
		
		JLabel lblLogicalNames = new JLabel("Logical Names");
		lblLogicalNames.setBounds(627, 181, 148, 14);
		OPOD.add(lblLogicalNames);
		
		JLabel lblServerRelationship = new JLabel("Server Relationship");
		lblServerRelationship.setBounds(785, 181, 148, 14);
		OPOD.add(lblServerRelationship);
		
		JLabel lblNewLabel_4 = new JLabel("Database Name");
		lblNewLabel_4.setBounds(10, 560, 148, 14);
		OPOD.add(lblNewLabel_4);
		
		JLabel lblLuns = new JLabel("Version");
		lblLuns.setBounds(168, 562, 161, 14);
		OPOD.add(lblLuns);
		
		JLabel lblLunSizegb = new JLabel("Relationship");
		lblLunSizegb.setBounds(339, 560, 150, 14);
		OPOD.add(lblLunSizegb);
		
		JLabel lblTier = new JLabel("Primary");
		lblTier.setBounds(510, 560, 161, 14);
		OPOD.add(lblTier);
		
		JLabel lblStorageArray = new JLabel("Backup Type");
		lblStorageArray.setBounds(681, 560, 148, 14);
		OPOD.add(lblStorageArray);
		
		JComboBox comboBox_67 = new JComboBox(opod.getOracleSHSModulesOpts());
		comboBox_67.setBounds(1040, 721, 393, 20);
		OPOD.add(comboBox_67);
		
		JButton btnNewButton_1 = new JButton("Proceed with the selected option");
		btnNewButton_1.setBounds(1498, 720, 233, 23);
		OPOD.add(btnNewButton_1);
		
		JTextArea diskGroup1 = new JTextArea();
		diskGroup1.setText("FRA");
		diskGroup1.setBounds(989, 206, 148, 22);
		OPOD.add(diskGroup1);
		
		JTextArea diskGroup2 = new JTextArea();
		diskGroup2.setText("TEMP");
		diskGroup2.setBounds(989, 239, 148, 22);
		OPOD.add(diskGroup2);
		
		JTextArea diskGroup3 = new JTextArea();
		diskGroup3.setText("REDOA");
		diskGroup3.setBounds(989, 272, 148, 22);
		OPOD.add(diskGroup3);
		
		JTextArea diskGroup4 = new JTextArea();
		diskGroup4.setText("REDOB");
		diskGroup4.setBounds(989, 305, 148, 22);
		OPOD.add(diskGroup4);
		
		JTextArea diskGroup5 = new JTextArea();
		diskGroup5.setText("OCRVOTE");
		diskGroup5.setBounds(989, 337, 148, 22);
		OPOD.add(diskGroup5);
		
		JTextArea textArea_34 = new JTextArea();
		textArea_34.setBounds(989, 370, 148, 22);
		OPOD.add(textArea_34);
		
		JTextArea textArea_35 = new JTextArea();
		textArea_35.setBounds(989, 403, 148, 22);
		OPOD.add(textArea_35);
		
		JTextArea textArea_36 = new JTextArea();
		textArea_36.setBounds(989, 436, 148, 22);
		OPOD.add(textArea_36);
		
		JTextArea textArea_37 = new JTextArea();
		textArea_37.setBounds(989, 471, 148, 22);
		OPOD.add(textArea_37);
		
		JTextArea textArea_38 = new JTextArea();
		textArea_38.setBounds(989, 504, 148, 22);
		OPOD.add(textArea_38);
		
		JTextArea textArea_39 = new JTextArea();
		textArea_39.setBounds(989, 537, 148, 22);
		OPOD.add(textArea_39);
		
		JTextArea textArea_40 = new JTextArea();
		textArea_40.setBounds(989, 570, 148, 22);
		OPOD.add(textArea_40);
		
		JTextArea textArea_41 = new JTextArea();
		textArea_41.setBounds(989, 603, 148, 22);
		OPOD.add(textArea_41);
		
		JTextArea textArea_42 = new JTextArea();
		textArea_42.setBounds(989, 636, 148, 22);
		OPOD.add(textArea_42);
		
		JTextArea textArea_43 = new JTextArea();
		textArea_43.setBounds(989, 669, 148, 22);
		OPOD.add(textArea_43);
		
		JTextArea textArea_44 = new JTextArea();
		textArea_44.setText("8");
		textArea_44.setBounds(1147, 206, 148, 22);
		OPOD.add(textArea_44);
		
		JTextArea textArea_45 = new JTextArea();
		textArea_45.setText("8");
		textArea_45.setBounds(1147, 239, 148, 22);
		OPOD.add(textArea_45);
		
		JTextArea textArea_46 = new JTextArea();
		textArea_46.setText("8");
		textArea_46.setBounds(1147, 272, 148, 22);
		OPOD.add(textArea_46);
		
		JTextArea textArea_47 = new JTextArea();
		textArea_47.setText("8");
		textArea_47.setBounds(1147, 305, 148, 22);
		OPOD.add(textArea_47);
		
		JTextArea textArea_48 = new JTextArea();
		textArea_48.setText("2");
		textArea_48.setBounds(1147, 337, 148, 22);
		OPOD.add(textArea_48);
		
		JTextArea textArea_49 = new JTextArea();
		textArea_49.setBounds(1147, 370, 148, 22);
		OPOD.add(textArea_49);
		
		JTextArea textArea_50 = new JTextArea();
		textArea_50.setBounds(1147, 403, 148, 22);
		OPOD.add(textArea_50);
		
		JTextArea textArea_51 = new JTextArea();
		textArea_51.setBounds(1147, 436, 148, 22);
		OPOD.add(textArea_51);
		
		JTextArea textArea_52 = new JTextArea();
		textArea_52.setBounds(1147, 471, 148, 22);
		OPOD.add(textArea_52);
		
		JTextArea textArea_53 = new JTextArea();
		textArea_53.setBounds(1147, 504, 148, 22);
		OPOD.add(textArea_53);
		
		JTextArea textArea_54 = new JTextArea();
		textArea_54.setBounds(1147, 537, 148, 22);
		OPOD.add(textArea_54);
		
		JTextArea textArea_55 = new JTextArea();
		textArea_55.setBounds(1147, 570, 148, 22);
		OPOD.add(textArea_55);
		
		JTextArea textArea_56 = new JTextArea();
		textArea_56.setBounds(1147, 603, 148, 22);
		OPOD.add(textArea_56);
		
		JTextArea textArea_57 = new JTextArea();
		textArea_57.setBounds(1147, 636, 148, 22);
		OPOD.add(textArea_57);
		
		JTextArea textArea_58 = new JTextArea();
		textArea_58.setBounds(1147, 669, 148, 22);
		OPOD.add(textArea_58);
		
		JTextArea textArea_59 = new JTextArea();
		textArea_59.setText("32");
		textArea_59.setBounds(1305, 206, 148, 22);
		OPOD.add(textArea_59);
		
		JTextArea textArea_60 = new JTextArea();
		textArea_60.setText("32");
		textArea_60.setBounds(1305, 239, 148, 22);
		OPOD.add(textArea_60);
		
		JTextArea textArea_61 = new JTextArea();
		textArea_61.setText("32");
		textArea_61.setBounds(1305, 272, 148, 22);
		OPOD.add(textArea_61);
		
		JTextArea textArea_62 = new JTextArea();
		textArea_62.setText("32");
		textArea_62.setBounds(1305, 305, 148, 22);
		OPOD.add(textArea_62);
		
		JTextArea textArea_63 = new JTextArea();
		textArea_63.setText("2");
		textArea_63.setBounds(1305, 337, 148, 22);
		OPOD.add(textArea_63);
		
		JTextArea textArea_64 = new JTextArea();
		textArea_64.setText("0");
		textArea_64.setBounds(1305, 370, 148, 22);
		OPOD.add(textArea_64);
		
		JTextArea textArea_65 = new JTextArea();
		textArea_65.setText("0");
		textArea_65.setBounds(1305, 403, 148, 22);
		OPOD.add(textArea_65);
		
		JTextArea textArea_66 = new JTextArea();
		textArea_66.setText("0");
		textArea_66.setBounds(1305, 436, 148, 22);
		OPOD.add(textArea_66);
		
		JTextArea textArea_67 = new JTextArea();
		textArea_67.setText("0");
		textArea_67.setBounds(1305, 471, 148, 22);
		OPOD.add(textArea_67);
		
		JTextArea textArea_68 = new JTextArea();
		textArea_68.setText("0");
		textArea_68.setBounds(1305, 504, 148, 22);
		OPOD.add(textArea_68);
		
		JTextArea textArea_69 = new JTextArea();
		textArea_69.setText("0");
		textArea_69.setBounds(1305, 537, 148, 22);
		OPOD.add(textArea_69);
		
		JTextArea textArea_70 = new JTextArea();
		textArea_70.setText("0");
		textArea_70.setBounds(1305, 570, 148, 22);
		OPOD.add(textArea_70);
		
		JTextArea textArea_71 = new JTextArea();
		textArea_71.setText("0");
		textArea_71.setBounds(1305, 603, 148, 22);
		OPOD.add(textArea_71);
		
		JTextArea textArea_72 = new JTextArea();
		textArea_72.setText("0");
		textArea_72.setBounds(1305, 636, 148, 22);
		OPOD.add(textArea_72);
		
		JTextArea textArea_73 = new JTextArea();
		textArea_73.setText("0");
		textArea_73.setBounds(1305, 669, 148, 22);
		OPOD.add(textArea_73);
		
		JComboBox comboBox_68 = new JComboBox(opod.getTierOpts());
		comboBox_68.setSelectedItem("Tier 2");
		comboBox_68.setBounds(1463, 208, 148, 20);
		OPOD.add(comboBox_68);
		
		JComboBox comboBox_69 = new JComboBox(opod.getTierOpts());
		comboBox_69.setSelectedItem("Tier 2");
		comboBox_69.setBounds(1463, 241, 148, 20);
		OPOD.add(comboBox_69);
		
		JComboBox comboBox_70 = new JComboBox(opod.getTierOpts());
		comboBox_70.setSelectedItem("Tier 2");
		comboBox_70.setBounds(1463, 274, 148, 20);
		OPOD.add(comboBox_70);
		
		JComboBox comboBox_71 = new JComboBox(opod.getTierOpts());
		comboBox_71.setSelectedItem("Tier 2");
		comboBox_71.setBounds(1463, 307, 148, 20);
		OPOD.add(comboBox_71);
		
		JComboBox comboBox_72 = new JComboBox(opod.getTierOpts());
		comboBox_72.setSelectedItem("Tier 2");
		comboBox_72.setBounds(1463, 339, 148, 20);
		OPOD.add(comboBox_72);
		
		JComboBox comboBox_73 = new JComboBox(opod.getTierOpts());
		comboBox_73.setBounds(1463, 372, 148, 20);
		OPOD.add(comboBox_73);
		
		JComboBox comboBox_74 = new JComboBox(opod.getTierOpts());
		comboBox_74.setBounds(1463, 405, 148, 20);
		OPOD.add(comboBox_74);
		
		JComboBox comboBox_75 = new JComboBox(opod.getTierOpts());
		comboBox_75.setBounds(1463, 438, 148, 20);
		OPOD.add(comboBox_75);
		
		JComboBox comboBox_76 = new JComboBox(opod.getTierOpts());
		comboBox_76.setBounds(1463, 473, 148, 20);
		OPOD.add(comboBox_76);
		
		JComboBox comboBox_77 = new JComboBox(opod.getTierOpts());
		comboBox_77.setBounds(1463, 506, 148, 20);
		OPOD.add(comboBox_77);
		
		JComboBox comboBox_78 = new JComboBox(opod.getTierOpts());
		comboBox_78.setBounds(1463, 539, 148, 20);
		OPOD.add(comboBox_78);
		
		JComboBox comboBox_79 = new JComboBox(opod.getTierOpts());
		comboBox_79.setBounds(1463, 572, 148, 20);
		OPOD.add(comboBox_79);
		
		JComboBox comboBox_80 = new JComboBox(opod.getTierOpts());
		comboBox_80.setBounds(1463, 605, 148, 20);
		OPOD.add(comboBox_80);
		
		JComboBox comboBox_81 = new JComboBox(opod.getTierOpts());
		comboBox_81.setBounds(1463, 638, 148, 20);
		OPOD.add(comboBox_81);
		
		JComboBox comboBox_82 = new JComboBox(opod.getTierOpts());
		comboBox_82.setBounds(1463, 671, 148, 20);
		OPOD.add(comboBox_82);
		
		JComboBox comboBox_83 = new JComboBox(opod.getStorageArrayOpts());
		comboBox_83.setBounds(1621, 208, 214, 20);
		OPOD.add(comboBox_83);
		
		JComboBox comboBox_84 = new JComboBox(opod.getStorageArrayOpts());
		comboBox_84.setBounds(1621, 241, 214, 20);
		OPOD.add(comboBox_84);
		
		JComboBox comboBox_85 = new JComboBox(opod.getStorageArrayOpts());
		comboBox_85.setBounds(1621, 274, 214, 20);
		OPOD.add(comboBox_85);
		
		JComboBox comboBox_86 = new JComboBox(opod.getStorageArrayOpts());
		comboBox_86.setBounds(1621, 307, 214, 20);
		OPOD.add(comboBox_86);
		
		JComboBox comboBox_87 = new JComboBox(opod.getStorageArrayOpts());
		comboBox_87.setBounds(1621, 339, 214, 20);
		OPOD.add(comboBox_87);
		
		JComboBox comboBox_88 = new JComboBox(opod.getStorageArrayOpts());
		comboBox_88.setBounds(1621, 372, 214, 20);
		OPOD.add(comboBox_88);
		
		JComboBox comboBox_89 = new JComboBox(opod.getStorageArrayOpts());
		comboBox_89.setBounds(1621, 405, 214, 20);
		OPOD.add(comboBox_89);
		
		JComboBox comboBox_90 = new JComboBox(opod.getStorageArrayOpts());
		comboBox_90.setBounds(1621, 438, 214, 20);
		OPOD.add(comboBox_90);
		
		JComboBox comboBox_91 = new JComboBox(opod.getStorageArrayOpts());
		comboBox_91.setBounds(1621, 473, 214, 20);
		OPOD.add(comboBox_91);
		
		JComboBox comboBox_92 = new JComboBox(opod.getStorageArrayOpts());
		comboBox_92.setBounds(1621, 506, 214, 20);
		OPOD.add(comboBox_92);
		
		JComboBox comboBox_93 = new JComboBox(opod.getStorageArrayOpts());
		comboBox_93.setBounds(1621, 539, 214, 20);
		OPOD.add(comboBox_93);
		
		JComboBox comboBox_94 = new JComboBox(opod.getStorageArrayOpts());
		comboBox_94.setBounds(1621, 572, 214, 20);
		OPOD.add(comboBox_94);
		
		JComboBox comboBox_95 = new JComboBox(opod.getStorageArrayOpts());
		comboBox_95.setBounds(1621, 605, 214, 20);
		OPOD.add(comboBox_95);
		
		JComboBox comboBox_96 = new JComboBox(opod.getStorageArrayOpts());
		comboBox_96.setBounds(1621, 638, 214, 20);
		OPOD.add(comboBox_96);
		
		JComboBox comboBox_97 = new JComboBox(opod.getStorageArrayOpts());
		comboBox_97.setBounds(1621, 671, 214, 20);
		OPOD.add(comboBox_97);
		
		JLabel lblDiskGroupName = new JLabel("Disk Group Name");
		lblDiskGroupName.setBounds(989, 181, 148, 14);
		OPOD.add(lblDiskGroupName);
		
		JLabel lblLuns_1 = new JLabel("LUNS");
		lblLuns_1.setBounds(1147, 181, 136, 14);
		OPOD.add(lblLuns_1);
		
		JLabel lblLunSizegb_1 = new JLabel("LUN Size (GB)");
		lblLunSizegb_1.setBounds(1305, 181, 148, 14);
		OPOD.add(lblLunSizegb_1);
		
		JLabel lblTier_1 = new JLabel("Tier");
		lblTier_1.setBounds(1463, 181, 148, 14);
		OPOD.add(lblTier_1);
		
		JLabel lblStorageArray_1 = new JLabel("Storage Array");
		lblStorageArray_1.setBounds(1621, 181, 130, 14);
		OPOD.add(lblStorageArray_1);
		
		JLabel lblTotalNewAsm = new JLabel("Total New ASM Disk Groups");
		lblTotalNewAsm.setBounds(1147, 114, 174, 14);
		OPOD.add(lblTotalNewAsm);
		
		textArea_43.setVisible(false);
		textArea_58.setVisible(false);
		textArea_73.setVisible(false);
		comboBox_82.setVisible(false);
		comboBox_97.setVisible(false);
		lblDiskGroupName.setVisible(false);
		lblLuns_1.setVisible(false);
		lblLunSizegb_1.setVisible(false);
		lblTier_1.setVisible(false);
		lblStorageArray_1.setVisible(false);
		diskGroup1.setVisible(false);
		textArea_44.setVisible(false);
		textArea_59.setVisible(false);
		comboBox_68.setVisible(false);
		comboBox_83.setVisible(false);
		diskGroup2.setVisible(false);
		textArea_45.setVisible(false);
		textArea_60.setVisible(false);
		comboBox_69.setVisible(false);
		comboBox_84.setVisible(false);
		diskGroup3.setVisible(false);
		textArea_46.setVisible(false);
		textArea_61.setVisible(false);
		comboBox_70.setVisible(false);
		comboBox_85.setVisible(false);
		diskGroup4.setVisible(false);
		textArea_47.setVisible(false);
		textArea_62.setVisible(false);
		comboBox_71.setVisible(false);
		comboBox_86.setVisible(false);
		diskGroup5.setVisible(false);
		textArea_48.setVisible(false);
		textArea_63.setVisible(false);
		comboBox_72.setVisible(false);
		textArea_34.setVisible(false);
		textArea_49.setVisible(false);
		textArea_64.setVisible(false);
		comboBox_73.setVisible(false);
		comboBox_88.setVisible(false);
		comboBox_87.setVisible(false);
		textArea_35.setVisible(false);
		textArea_50.setVisible(false);
		textArea_65.setVisible(false);
		comboBox_74.setVisible(false);
		comboBox_89.setVisible(false);
		textArea_36.setVisible(false);
		textArea_51.setVisible(false);
		textArea_66.setVisible(false);
		comboBox_75.setVisible(false);
		comboBox_90.setVisible(false);
		textArea_37.setVisible(false);
		textArea_52.setVisible(false);
		textArea_67.setVisible(false);
		comboBox_76.setVisible(false);
		comboBox_91.setVisible(false);
		textArea_38.setVisible(false);
		textArea_53.setVisible(false);
		textArea_68.setVisible(false);
		comboBox_77.setVisible(false);
		comboBox_92.setVisible(false);
		textArea_39.setVisible(false);
		textArea_54.setVisible(false);
		textArea_69.setVisible(false);
		comboBox_78.setVisible(false);
		comboBox_93.setVisible(false);
		textArea_40.setVisible(false);
		textArea_55.setVisible(false);
		textArea_70.setVisible(false);
		comboBox_79.setVisible(false);
		comboBox_94.setVisible(false);
		textArea_41.setVisible(false);
		textArea_56.setVisible(false);
		textArea_71.setVisible(false);
		comboBox_80.setVisible(false);
		comboBox_95.setVisible(false);
		textArea_42.setVisible(false);
		textArea_57.setVisible(false);
		textArea_72.setVisible(false);
		comboBox_81.setVisible(false);
		comboBox_96.setVisible(false);
		
		
		JComboBox comboBox_98 = new JComboBox(opod.numDiskGroupsOpts);
		comboBox_98.setBounds(1305, 111, 261, 20);
		OPOD.add(comboBox_98);
		comboBox_98.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(comboBox_98.getSelectedIndex() == 0) {
					lblDiskGroupName.setVisible(false);
					lblLuns_1.setVisible(false);
					lblLunSizegb_1.setVisible(false);
					lblTier_1.setVisible(false);
					lblStorageArray_1.setVisible(false);
				} else {
					lblDiskGroupName.setVisible(true);
					lblLuns_1.setVisible(true);
					lblLunSizegb_1.setVisible(true);
					lblTier_1.setVisible(true);
					lblStorageArray_1.setVisible(true);
				}
				
				if(comboBox_98.getSelectedIndex() >= 1) {
					diskGroup1.setVisible(true);
					textArea_44.setVisible(true);
					textArea_59.setVisible(true);
					comboBox_68.setVisible(true);
					comboBox_83.setVisible(true);
					
				} else {
					diskGroup1.setVisible(false);
					textArea_44.setVisible(false);
					textArea_59.setVisible(false);
					comboBox_68.setVisible(false);
					comboBox_83.setVisible(false);
				}
				
				if(comboBox_98.getSelectedIndex() >= 2) {
					diskGroup2.setVisible(true);
					textArea_45.setVisible(true);
					textArea_60.setVisible(true);
					comboBox_69.setVisible(true);
					comboBox_84.setVisible(true);
					
				} else {
					diskGroup2.setVisible(false);
					textArea_45.setVisible(false);
					textArea_60.setVisible(false);
					comboBox_69.setVisible(false);
					comboBox_84.setVisible(false);
				}
				
				if(comboBox_98.getSelectedIndex() >= 3) {
					diskGroup3.setVisible(true);
					textArea_46.setVisible(true);
					textArea_61.setVisible(true);
					comboBox_70.setVisible(true);
					comboBox_85.setVisible(true);
					
				} else {
					diskGroup3.setVisible(false);
					textArea_46.setVisible(false);
					textArea_61.setVisible(false);
					comboBox_70.setVisible(false);
					comboBox_85.setVisible(false);
				}
				
				if(comboBox_98.getSelectedIndex() >= 4) {
					diskGroup4.setVisible(true);
					textArea_47.setVisible(true);
					textArea_62.setVisible(true);
					comboBox_71.setVisible(true);
					comboBox_86.setVisible(true);
					
				} else {
					diskGroup4.setVisible(false);
					textArea_47.setVisible(false);
					textArea_62.setVisible(false);
					comboBox_71.setVisible(false);
					comboBox_86.setVisible(false);
				}
				
				
				if(comboBox_98.getSelectedIndex() >= 5) {
					diskGroup5.setVisible(true);
					textArea_48.setVisible(true);
					textArea_63.setVisible(true);
					comboBox_72.setVisible(true);
					comboBox_87.setVisible(true);
					
				} else {
					diskGroup5.setVisible(false);
					textArea_48.setVisible(false);
					textArea_63.setVisible(false);
					comboBox_72.setVisible(false);
					comboBox_87.setVisible(false);
				}
				
				if(comboBox_98.getSelectedIndex() >= 6) {
					textArea_34.setVisible(true);
					textArea_49.setVisible(true);
					textArea_64.setVisible(true);
					comboBox_73.setVisible(true);
					comboBox_88.setVisible(true);
					
				} else {
					textArea_34.setVisible(false);
					textArea_49.setVisible(false);
					textArea_64.setVisible(false);
					comboBox_73.setVisible(false);
					comboBox_88.setVisible(false);
				}
				
				if(comboBox_98.getSelectedIndex() >= 7) {
					textArea_35.setVisible(true);
					textArea_50.setVisible(true);
					textArea_65.setVisible(true);
					comboBox_74.setVisible(true);
					comboBox_89.setVisible(true);
					
				} else {
					textArea_35.setVisible(false);
					textArea_50.setVisible(false);
					textArea_65.setVisible(false);
					comboBox_74.setVisible(false);
					comboBox_89.setVisible(false);
				}
				
				if(comboBox_98.getSelectedIndex() >= 8) {
					textArea_36.setVisible(true);
					textArea_51.setVisible(true);
					textArea_66.setVisible(true);
					comboBox_75.setVisible(true);
					comboBox_90.setVisible(true);
					
				} else {
					textArea_36.setVisible(false);
					textArea_51.setVisible(false);
					textArea_66.setVisible(false);
					comboBox_75.setVisible(false);
					comboBox_90.setVisible(false);
				}
				
				if(comboBox_98.getSelectedIndex() >= 9) {
					textArea_37.setVisible(true);
					textArea_52.setVisible(true);
					textArea_67.setVisible(true);
					comboBox_76.setVisible(true);
					comboBox_91.setVisible(true);
					
				} else {
					textArea_37.setVisible(false);
					textArea_52.setVisible(false);
					textArea_67.setVisible(false);
					comboBox_76.setVisible(false);
					comboBox_91.setVisible(false);
				}
				
				if(comboBox_98.getSelectedIndex() >= 10) {
					textArea_38.setVisible(true);
					textArea_53.setVisible(true);
					textArea_68.setVisible(true);
					comboBox_77.setVisible(true);
					comboBox_92.setVisible(true);
					
				} else {
					textArea_38.setVisible(false);
					textArea_53.setVisible(false);
					textArea_68.setVisible(false);
					comboBox_77.setVisible(false);
					comboBox_92.setVisible(false);
				}
				
				if(comboBox_98.getSelectedIndex() >= 11) {
					textArea_39.setVisible(true);
					textArea_54.setVisible(true);
					textArea_69.setVisible(true);
					comboBox_78.setVisible(true);
					comboBox_93.setVisible(true);
					
				} else {
					textArea_39.setVisible(false);
					textArea_54.setVisible(false);
					textArea_69.setVisible(false);
					comboBox_78.setVisible(false);
					comboBox_93.setVisible(false);
				}
				
				if(comboBox_98.getSelectedIndex() >= 12) {
					textArea_40.setVisible(true);
					textArea_55.setVisible(true);
					textArea_70.setVisible(true);
					comboBox_79.setVisible(true);
					comboBox_94.setVisible(true);
					
				} else {
					textArea_40.setVisible(false);
					textArea_55.setVisible(false);
					textArea_70.setVisible(false);
					comboBox_79.setVisible(false);
					comboBox_94.setVisible(false);
				}
				
				if(comboBox_98.getSelectedIndex() >= 13) {
					textArea_41.setVisible(true);
					textArea_56.setVisible(true);
					textArea_71.setVisible(true);
					comboBox_80.setVisible(true);
					comboBox_95.setVisible(true);
					
				} else {
					textArea_41.setVisible(false);
					textArea_56.setVisible(false);
					textArea_71.setVisible(false);
					comboBox_80.setVisible(false);
					comboBox_95.setVisible(false);
				}
				
				if(comboBox_98.getSelectedIndex() >= 14) {
					textArea_42.setVisible(true);
					textArea_57.setVisible(true);
					textArea_72.setVisible(true);
					comboBox_81.setVisible(true);
					comboBox_96.setVisible(true);
					
				} else {
					textArea_42.setVisible(false);
					textArea_57.setVisible(false);
					textArea_72.setVisible(false);
					comboBox_81.setVisible(false);
					comboBox_96.setVisible(false);
				}
				
				if(comboBox_98.getSelectedIndex() >= 15) {
					textArea_43.setVisible(true);
					textArea_58.setVisible(true);
					textArea_73.setVisible(true);
					comboBox_82.setVisible(true);
					comboBox_97.setVisible(true);
					
				} else {
					textArea_43.setVisible(false);
					textArea_58.setVisible(false);
					textArea_73.setVisible(false);
					comboBox_82.setVisible(false);
					comboBox_97.setVisible(false);
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
