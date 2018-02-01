package jFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import org.apache.commons.lang3.StringUtils;

import Digivolver.Digimon;
import Digivolver.Digivolution;
import Digivolver.Main;
import Digivolver.Skill;

public class JPanelDisplay extends javax.swing.JPanel implements ActionListener{
	private static final long serialVersionUID = 1L;
	private String menuAction = "dnaDigivolve";
	private JTextField d1TextField;
	private JTextField d2TextField;
	private JLabel lblDigimon1, lblDigimon2;
	private JButton btnDnaDigivolve;
	private JTextPaneOutput txtrResultarea;
	private boolean[] checkType, checkLevel;
	private static String format = "%1$-22s%2$-22s%3$-22s";
	private static String getMaterialformat = "%1$-15s%2$-15s%3$-15s";
	private static String formatDigiv = "%1$-22s%2$-22s%3$-22s%4$-22s";
	private static String formatEl = "%1$-22s%2$-22s%3$-22s%4$-22s%5$-22s";
	private static String formatSkill = "%1$-18s%2$-18s%3$-18s%4$-18s%5$-18s%6$-18s";
	private JCheckBox chckbxVaccine;
	private JCheckBox chckbxVirus;
	private JCheckBox chckbxData;
	private JCheckBox chckbxRookie;
	private JCheckBox chckbxChampion;
	private JCheckBox chckbxUltimate;
	private JCheckBox chckbxMega;
	private JCheckBox chckbxMarkAll;
	private JCheckBox chckbxMarkAllLvl;
	private JLabel lblType;
	private JLabel lblLevel;
	private JFileChooser chooser;
	private buttonHandler handler;
	private JCheckBox chckbxIncludeAntidigivolutions;
	private JSpinner spinnerMin;
	private JSpinner spinnerMax;
	private JLabel lblDp;
	private JLabel label;
	private JMenu mnDigimon;
	private JMenuItem mntmDigivolve_1;
	private JMenuItem mntmAntidigivolve;
	private JMenu mnHelp;
	private JMenuItem mntmAboutMe;
	private JMenu mnFile;
	private JMenuItem mntmSave;
	private JMenuItem mntmExit;
	private JMenuItem mntmGetMaterial;
	private JMenuItem mntmGetMaterials;
	private JMenuItem mntmDigivolve;
	private JSeparator separator;
	protected boolean txtClear;
	Window window;
	Window aboutMe;
	Window preferences;
	private JMenuItem mntmPreferences;
	private JButton btnClear;
	private Color[] color = {Color.BLACK, new Color(0, 127, 0), Color.BLUE, Color.RED, new Color(127, 127, 127)};
	private JMenuItem mntmDigivolveMaterial;
	private JSpinner maxLevelD1;
	private JSpinner maxLevelD2;
	private JLabel lblMaxEl;
	private JLabel lblMaxEl_1;
	private JMenu mnSkill;
	private JMenuItem mntmByDigimon;
	private checkListHandler clHandler;
	private JMenuBar menuBar;
	private JMenu mnDna;
	private JMenuItem mntmByName;

	private void addDigimonLabels(){
		//Sets Digimon 1 label and text field
		lblDigimon1 = new JLabel("Digimon 1");
		lblDigimon1.setHorizontalAlignment(SwingConstants.CENTER);
		lblDigimon1.setBounds(21, 67, 129, 14);
		add(lblDigimon1);
		
		d1TextField = new JTextField();
		d1TextField.setBounds(87, 92, 129, 23);
		add(d1TextField);
		d1TextField.setColumns(10);
		
		//Sets Digimon 2 label and text field
		lblDigimon2 = new JLabel("Digimon 2");
		lblDigimon2.setHorizontalAlignment(SwingConstants.CENTER);
		lblDigimon2.setBounds(21, 155, 129, 14);
		add(lblDigimon2);
		
		d2TextField = new JTextField();
		d2TextField.setBounds(87, 180, 129, 23);
		add(d2TextField);
		d2TextField.setColumns(10);
	}
	
	private void addSubmitButton(){
		//Sets the 'submit' button
		btnDnaDigivolve = new JButton("Confirm");
		btnDnaDigivolve.setBounds(347, 229, 116, 34);
		add(btnDnaDigivolve);
		
		handler = new buttonHandler();
		btnDnaDigivolve.addActionListener(handler);
	}
	
	private void addResultBox(){
		//Sets the result box
		txtrResultarea = new JTextPaneOutput(this);
		txtrResultarea.setFont(new Font("Consolas", Font.PLAIN, 12));
		txtrResultarea.setBounds(21, 300, 832, 295);
		txtrResultarea.setEditable(false);
		JScrollPane scroll = new JScrollPane(txtrResultarea);
		scroll.setBounds(21, 300, 832, 295);
	    scroll.setVisible(true);
		add(scroll);
	}
	
	private void addFilterBoxes(){
		lblType = new JLabel("Type");
		lblType.setToolTipText("Filter the results by type.");
		lblType.setBounds(360, 67, 46, 14);
		add(lblType);
		checkType = new boolean[3];
		
		chckbxRookie = new JCheckBox("Rookie");
		chckbxRookie.setSelected(true);
		chckbxRookie.setBounds(527, 118, 97, 23);
		add(chckbxRookie);
		
		chckbxChampion = new JCheckBox("Champion");
		chckbxChampion.setSelected(true);
		chckbxChampion.setBounds(527, 144, 97, 23);
		add(chckbxChampion);
		
		chckbxUltimate = new JCheckBox("Ultimate");
		chckbxUltimate.setSelected(true);
		chckbxUltimate.setBounds(527, 170, 97, 23);
		add(chckbxUltimate);
		
		chckbxMega = new JCheckBox("Mega");
		chckbxMega.setSelected(true);
		chckbxMega.setBounds(527, 196, 97, 23);
		add(chckbxMega);
		
		lblLevel = new JLabel("Level");
		lblLevel.setToolTipText("Filter the results by level.");
		lblLevel.setBounds(527, 67, 46, 14);
		add(lblLevel);
		checkLevel = new boolean[4];
		
		chckbxVaccine = new JCheckBox("Vaccine");
		chckbxVaccine.setSelected(true);
		chckbxVaccine.setBounds(360, 118, 97, 23);
		add(chckbxVaccine);
		
		chckbxVirus = new JCheckBox("Virus");
		chckbxVirus.setSelected(true);
		chckbxVirus.setBounds(360, 144, 97, 23);
		add(chckbxVirus);
		
		chckbxData = new JCheckBox("Data");
		chckbxData.setSelected(true);
		chckbxData.setBounds(360, 170, 97, 23);
		add(chckbxData);
	}
	
	private void addCheckAllBoxes(){
		//Sets 'mark all' boxes and its handler
		clHandler = new checkListHandler();
		
		chckbxMarkAll = new JCheckBox("");
		chckbxMarkAll.setSelected(true);
		chckbxMarkAll.setToolTipText("Select all.");
		chckbxMarkAll.setBounds(360, 92, 97, 23);
		add(chckbxMarkAll);
		chckbxMarkAll.addActionListener(clHandler);
		
		chckbxMarkAllLvl = new JCheckBox("");
		chckbxMarkAllLvl.setSelected(true);
		chckbxMarkAllLvl.setToolTipText("Select all.");
		chckbxMarkAllLvl.setBounds(527, 92, 97, 23);
		add(chckbxMarkAllLvl);
		chckbxMarkAllLvl.addActionListener(clHandler);
	}
	
	private void addAntiDigivolutionBox(){
		//Antidigivolution contents
		chckbxIncludeAntidigivolutions = new JCheckBox("Include Digivolutions");
		chckbxIncludeAntidigivolutions.setSelected(true);
		chckbxIncludeAntidigivolutions.setBounds(687, 67, 165, 23);
		add(chckbxIncludeAntidigivolutions);
		
		//Visibility of antis and listener
		chckbxIncludeAntidigivolutions.addActionListener(clHandler);
	}
	
	private void addSpinners(){
		spinnerMin = new JSpinner();
		spinnerMin.setModel(new SpinnerNumberModel(0, 0, 99, 1));
		spinnerMin.setBounds(716, 97, 40, 20);
		add(spinnerMin);
		
		spinnerMax = new JSpinner();
		spinnerMax.setModel(new SpinnerNumberModel(99, 0, 99, 1));
		spinnerMax.setBounds(766, 97, 40, 20);
		add(spinnerMax);
		
		lblDp = new JLabel("DP");
		lblDp.setBounds(697, 100, 46, 14);
		add(lblDp);
		
		label = new JLabel("-");
		label.setBounds(758, 100, 46, 14);
		add(label);
		
		maxLevelD1 = new JSpinner();
		maxLevelD1.setToolTipText("The maximum level Digimon 1 will get until its EXP becomes '9999999'.");
		maxLevelD1.setModel(new SpinnerNumberModel(99, 1, 99, 1));
		maxLevelD1.setBounds(265, 93, 40, 20);
		add(maxLevelD1);
		
		maxLevelD2 = new JSpinner();
		maxLevelD2.setToolTipText("The maximum level Digimon 2 will get until its EXP becomes '9999999'.");
		maxLevelD2.setModel(new SpinnerNumberModel(99, 1, 99, 1));
		maxLevelD2.setBounds(265, 181, 40, 20);
		add(maxLevelD2);
		
		lblMaxEl = new JLabel("Max EL");
		lblMaxEl.setBounds(233, 67, 46, 14);
		add(lblMaxEl);
		
		lblMaxEl_1 = new JLabel("Max EL");
		lblMaxEl_1.setBounds(233, 155, 46, 14);
		add(lblMaxEl_1);
	}
	
	private void addMenuBar(){
		menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 882, 21);
		add(menuBar);
	}
	
	private void addMenuOptions(){
		mnFile = new JMenu("File");
		menuBar.add(mnFile);
		addFileOptions();
		
		mnDna = new JMenu("DNA");
		menuBar.add(mnDna);
		addDNAOptions();
		
		mnDigimon = new JMenu("Digimon");
		menuBar.add(mnDigimon);
		addDigimonOptions();
		
		mnSkill = new JMenu("Skill");
		menuBar.add(mnSkill);
		addSkillOptions();
		
		mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		addHelpOptions();
	}
	
	private void addFileOptions(){
		mntmSave = new JMenuItem("Save As...");
		mntmSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		mnFile.add(mntmSave);
		mntmSave.addActionListener(this);
		
		mntmPreferences = new JMenuItem("Preferences");
		mntmPreferences.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, InputEvent.CTRL_MASK));
		mnFile.add(mntmPreferences);
		mntmPreferences.addActionListener(this);
		
		separator = new JSeparator();
		mnFile.add(separator);
		
		mntmExit = new JMenuItem("Exit");
		mntmExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, InputEvent.CTRL_MASK));
		mnFile.add(mntmExit);
		mntmExit.addActionListener(this);
	}
	
	private void addDNAOptions(){
		mntmDigivolve = new JMenuItem("Digivolve 2 Materials");
		mnDna.add(mntmDigivolve);
		mntmDigivolve.addActionListener(this);
		
		mntmDigivolveMaterial = new JMenuItem("Digivolve 1 Material");
		mnDna.add(mntmDigivolveMaterial);
		mntmDigivolveMaterial.addActionListener(this);
		
		mntmGetMaterials = new JMenuItem("Get 2 Materials");
		mnDna.add(mntmGetMaterials);
		mntmGetMaterials.addActionListener(this);
		
		mntmGetMaterial = new JMenuItem("Get 1 Material");
		mnDna.add(mntmGetMaterial);
		mntmGetMaterial.addActionListener(this);
	}
	
	private void addDigimonOptions(){
		mntmDigivolve_1 = new JMenuItem("Digivolve");
		mnDigimon.add(mntmDigivolve_1);
		mntmDigivolve_1.addActionListener(this);
		
		mntmAntidigivolve = new JMenuItem("Antidigivolve");
		mnDigimon.add(mntmAntidigivolve);
		mntmAntidigivolve.addActionListener(this);
	}
	
	private void addSkillOptions(){
		mntmByDigimon = new JMenuItem("By Digimon");
		mnSkill.add(mntmByDigimon);
		mntmByDigimon.addActionListener(this);
		
		mntmByName = new JMenuItem("By Name");
		mnSkill.add(mntmByName);
		mntmByName.addActionListener(this);
	}
	
	private void addHelpOptions(){
		mntmAboutMe = new JMenuItem("About me...");
		mnHelp.add(mntmAboutMe);
		mntmAboutMe.addActionListener(this);
	}
	
	private void addClearButton(){
		btnClear = new JButton("Clear");
		btnClear.setBounds(764, 606, 89, 23);
		add(btnClear);
		btnClear.addActionListener(handler);
	}
	
	public JPanelDisplay() {
		setLayout(null);
		SwingUtilities.getWindowAncestor(JPanelDisplay.this);
		
		//Screen
		addDigimonLabels();
		addSubmitButton();
		addFilterBoxes();
		addCheckAllBoxes();
		addAntiDigivolutionBox();
		addSpinners();
		setInitialState();
		addClearButton();
		addResultBox();
		
		//Menus
		addMenuBar();
		addMenuOptions();
	}
	
	private class buttonHandler implements ActionListener {
		public void printHeader(){
			//BOX CLEAR
			txtrResultarea.clear();
			switch (menuAction) {
			case "dnaDigivolve":
				if(chckbxIncludeAntidigivolutions.isSelected()){
					//OPTION HEADER
					txtrResultarea.println("-------------------------------------------------------------------------------------------------", color[4]);
					txtrResultarea.println("DNA Digivolve "+d1TextField.getText()+" and "+d2TextField.getText());
					
					//TABLE HEADER
					txtrResultarea.println("-------------------------------------------------------------------------------------------------", color[4]);
					txtrResultarea.println(String.format(formatEl, "Digimon", "Type", "Level", "DP", "Max EL"), color[4]);
					txtrResultarea.println("-------------------------------------------------------------------------------------------------", color[4]);
					
				}else{
					//OPTION HEADER
					txtrResultarea.println("-----------------------------------------------------------------------------", color[4]);
					txtrResultarea.println("DNA Digivolve "+d1TextField.getText()+" and "+d2TextField.getText());
					
					//TABLE HEADER
					txtrResultarea.println("-----------------------------------------------------------------------------", color[4]);
					txtrResultarea.println(String.format(formatDigiv, "Digimon", "Type", "Level", "Max EL"), color[4]);
					txtrResultarea.println("-----------------------------------------------------------------------------", color[4]);
				}
				break;
			case "digivolveMaterial":
				//OPTION HEADER
				txtrResultarea.println("-----------------------------------------------------------------------------------------", color[4]);
				txtrResultarea.println("DNA Digivolve "+d1TextField.getText()+" with ALL DIGIMON");
				
				//TABLE HEADER
				txtrResultarea.println("-----------------------------------------------------------------------------------------", color[4]);
				txtrResultarea.println(String.format(getMaterialformat, "Digimon", "Type", "Level") + "\t" + String.format(getMaterialformat, "Digimon", "Type", "Level"), color[4]);
				txtrResultarea.println("-----------------------------------------------------------------------------------------", color[4]);
				break;
			case "getMaterials":
				//OPTION HEADER
				txtrResultarea.println("-----------------------------------------------------------------------------------------", color[4]);
				txtrResultarea.println("Get ALL COMBINATIONS of Digimon that result in "+d1TextField.getText());
				
				//TABLE HEADER
				txtrResultarea.println("-----------------------------------------------------------------------------------------", color[4]);
				txtrResultarea.println(String.format(getMaterialformat, "Digimon", "Type", "Level") + "\t" + String.format(getMaterialformat, "Digimon", "Type", "Level"), color[4]);
				txtrResultarea.println("-----------------------------------------------------------------------------------------", color[4]);
				break;
			case "getMaterial":
				//OPTION HEADER
				txtrResultarea.println("---------------------------------------------------------", color[4]);
				txtrResultarea.println("Get ALL DIGIMON that combined with "+d1TextField.getText()+" result in "+d2TextField.getText());
				
				//TABLE HEADER
				txtrResultarea.println("---------------------------------------------------------", color[4]);
				txtrResultarea.println(String.format(format, "Digimon", "Type", "Level"), color[4]);
				txtrResultarea.println("---------------------------------------------------------", color[4]);
				break;
			case "digimonDigivolve":
				//OPTION HEADER
				txtrResultarea.println("-------------------------------------------------------------------------", color[4]);
				txtrResultarea.println("Digivolve "+d1TextField.getText());
				
				//TABLE HEADER
				txtrResultarea.println("-------------------------------------------------------------------------", color[4]);
				txtrResultarea.println(String.format(formatDigiv, "Digimon", "Type", "Level", "DP"), color[4]);
				txtrResultarea.println("-------------------------------------------------------------------------", color[4]);
				break;
			case "digimonAntievolve":
				//OPTION HEADER
				txtrResultarea.println("-------------------------------------------------------------------------", color[4]);
				txtrResultarea.println("Antidigivolve "+d1TextField.getText());
				
				//TABLE HEADER
				txtrResultarea.println("-------------------------------------------------------------------------", color[4]);
				txtrResultarea.println(String.format(formatDigiv, "Digimon", "Type", "Level", "DP"), color[4]);
				txtrResultarea.println("-------------------------------------------------------------------------", color[4]);
				break;
			case "skillByDigimon":
				//OPTION HEADER
				txtrResultarea.println("---------------------------------------------------------------------------------------------------", color[4]);
				txtrResultarea.println("Get "+d1TextField.getText()+"'s skill");
				
				//TABLE HEADER
				txtrResultarea.println("---------------------------------------------------------------------------------------------------", color[4]);
				txtrResultarea.println(String.format(formatSkill, "Name", "Speciality", "Type", "Target", "MP", "AP"), color[4]);
				txtrResultarea.println("---------------------------------------------------------------------------------------------------", color[4]);
				break;
			default:
				break;
			}
		}
		
		public void printList(ArrayList<Digimon> list){
			ArrayList<Digimon> antievolution = null;
			printHeader();
			String output = "";
			String space;
			int cont = 0;
			for(int i=0; i<list.size(); i++){
				output = String.format(format, list.get(i).getName(), Digimon.convertType(list.get(i).getType()), Digimon.convertLevel(list.get(i).getLevel()))+"\n";
				txtrResultarea.print(output);
				if(chckbxIncludeAntidigivolutions.isSelected()) {
					antievolution = list.get(i).getAntievolutions(((Integer) spinnerMin.getValue()).intValue(), ((Integer) spinnerMax.getValue()).intValue());
					for(int j=0; j<antievolution.size(); j++){
						cont = list.get(i).getLevel()-antievolution.get(j).getLevel();
						space = spaceMaker(cont);
						output = String.format(format, space+"+"+antievolution.get(j).getName(), "", "")+"\n";
						txtrResultarea.print(output, color[cont]);
					}
				}
			}
			txtrResultarea.println();
		}
		
		@SuppressWarnings({ "rawtypes" })
		private void printCombinations(ArrayList[] component){
			ArrayList<Digimon> antievolution = null;
			printHeader();
			String output = "";
			String space = "";
			int cont = 0;
			for(int i=0; i<component[0].size(); i++){
				for(int j=0; j<component.length; j++){
					@SuppressWarnings("unchecked")
					ArrayList<Digimon> list = component[j];
					txtrResultarea.print(String.format(getMaterialformat, list.get(i).getName(), Digimon.convertType(list.get(i).getType()), Digimon.convertLevel(list.get(i).getLevel())));
					txtrResultarea.print("\t");
				}
				txtrResultarea.println();
				if(chckbxIncludeAntidigivolutions.isSelected()) {
					@SuppressWarnings("unchecked")
					ArrayList<Digimon> list = component[0];
					antievolution = list.get(i).getAntievolutions(((Integer) spinnerMin.getValue()).intValue(), ((Integer) spinnerMax.getValue()).intValue());
					for(int j=0; j<antievolution.size(); j++){
						cont = list.get(i).getLevel()-antievolution.get(j).getLevel();
						space = spaceMaker(cont);
						output = String.format(format, space+"+"+antievolution.get(j).getName(), "", "")+"\n";
						txtrResultarea.print(output, color[cont]);
					}
				}
			}
			txtrResultarea.println();
		}
		
		@SuppressWarnings("rawtypes")
		private void printArrayListPairs(ArrayList[] component){
			printHeader();
			String output = "";
			for(int i=0; i<component[0].size(); i++){
				for(int j=0; j<component.length; j++){
					@SuppressWarnings("unchecked")
					ArrayList<Digimon> list = component[j];
					output += String.format(getMaterialformat, list.get(i).getName(), Digimon.convertType(list.get(i).getType()), Digimon.convertLevel(list.get(i).getLevel()));
					output += "\t";
				}
				output += "\n";
			}
			txtrResultarea.println(output);
		}
		
		private String getClosestDigimon(String digimon){
			String closestDigi = null;
			int closestDist = Integer.MAX_VALUE;
			for(int i=0; i<Main.digimonNameList.size(); i++){
				int temp = StringUtils.getLevenshteinDistance(digimon, Main.digimonNameList.get(i));
				if(temp<closestDist){
					closestDist = temp;
					closestDigi = Main.digimonNameList.get(i);
				}
			}
			return closestDigi;
		}
		
		private String spaceMaker(int cont){
			String space="";
			for(int i=0; i<cont; i++){
				space += "  ";
			}
			return space;
		}
				
		private void printDigivolutionList(ArrayList<Digivolution> list){
			printHeader();
			String output = "";
			String space = "";
			Digimon digimon = Main.getDigimonByName(d1TextField.getText());
			int cont = 0;
			for(int i=0; i<list.size(); i++){
				if(i==0){
					output = String.format(formatDigiv, list.get(i).getDigimon().getName(), Digimon.convertType(list.get(i).getDigimon().getType()), Digimon.convertLevel(list.get(i).getDigimon().getLevel()), list.get(i).getMinDp()+(list.get(i).getMaxDp()!=99?"-"+list.get(i).getMaxDp():"+"))+"\n";
					txtrResultarea.print(output);
				}else{
					cont = list.get(i).getDigimon().getLevel() - digimon.getLevel() - 1;
					space = spaceMaker(cont);
					if(cont==0){
						output = String.format(formatDigiv, space+list.get(i).getDigimon().getName(), Digimon.convertType(list.get(i).getDigimon().getType()), Digimon.convertLevel(list.get(i).getDigimon().getLevel()), list.get(i).getMinDp()+(list.get(i).getMaxDp()!=99?"-"+list.get(i).getMaxDp():"+"))+"\n";
						txtrResultarea.print(output);
					}else{
						output = String.format(formatDigiv, space+"+"+list.get(i).getDigimon().getName(), Digimon.convertType(list.get(i).getDigimon().getType()), Digimon.convertLevel(list.get(i).getDigimon().getLevel()), list.get(i).getMinDp()+(list.get(i).getMaxDp()!=99?"-"+list.get(i).getMaxDp():"+"))+"\n";
						txtrResultarea.print(output, color[cont]);
					}
				}
			}
			txtrResultarea.println();
		}
		
		private void printAntievolutionList(ArrayList<Digivolution> list){
			printHeader();
			String output = "";
			String space = "";
			Digimon digimon = Main.getDigimonByName(d1TextField.getText());
			int cont = 0;
			for(int i=0; i<list.size(); i++){
				if(i==0){
					output = String.format(formatDigiv, list.get(i).getDigimon().getName(), Digimon.convertType(list.get(i).getDigimon().getType()), Digimon.convertLevel(list.get(i).getDigimon().getLevel()), list.get(i).getMinDp()+(list.get(i).getMaxDp()!=99?"-"+list.get(i).getMaxDp():"+"))+"\n";
					txtrResultarea.print(output);
				}else{
					cont = digimon.getLevel() - list.get(i).getDigimon().getLevel() - 1;
					space = spaceMaker(cont);
					if(cont==0){
						output = String.format(formatDigiv, space+list.get(i).getDigimon().getName(), Digimon.convertType(list.get(i).getDigimon().getType()), Digimon.convertLevel(list.get(i).getDigimon().getLevel()), list.get(i).getMinDp()+(list.get(i).getMaxDp()!=99?"-"+list.get(i).getMaxDp():"+"))+"\n";
						txtrResultarea.print(output);
					}else{
						output = String.format(formatDigiv, space+"+"+list.get(i).getDigimon().getName(), Digimon.convertType(list.get(i).getDigimon().getType()), Digimon.convertLevel(list.get(i).getDigimon().getLevel()), list.get(i).getMinDp()+(list.get(i).getMaxDp()!=99?"-"+list.get(i).getMaxDp():"+"))+"\n";
						txtrResultarea.print(output, color[cont]);
					}
				}
			}
			txtrResultarea.println();
		}
		
		private void printDigimonSkill(Digimon digimon){
			Skill skill = digimon.getSkill();
			
			printHeader();
			String output = String.format(formatSkill, skill.getName(), skill.getSpeciality(), skill.getType(), skill.getTarget(), skill.getMP(), skill.getAP());
			txtrResultarea.println(output);
			
			txtrResultarea.print("Description: ", color[4]);
			txtrResultarea.println(skill.getDescription()+".");
			
			if(skill.getHint()!=null){
				txtrResultarea.print("Hint: ", color[4]);
				txtrResultarea.println(skill.getHint()+".");
			}
			txtrResultarea.println();
		}
		
		public void actionPerformed(ActionEvent event) {
			if(event.getSource() == btnClear) {
				txtrResultarea.setText("");
			}
			if(event.getSource() == btnDnaDigivolve) {
				//Check type
				checkType[0] = chckbxVaccine.isSelected();
				checkType[1] = chckbxVirus.isSelected();
				checkType[2] = chckbxData.isSelected();
				
				//Check level
				checkLevel[0] = chckbxRookie.isSelected();
				checkLevel[1] = chckbxChampion.isSelected();
				checkLevel[2] = chckbxUltimate.isSelected();
				checkLevel[3] = chckbxMega.isSelected();
				
				String digimon1 = getClosestDigimon(d1TextField.getText().toLowerCase());
				d1TextField.setText(digimon1.toUpperCase());
				if(menuAction.equals("dnaDigivolve")){
					//DNA DIGIVOLUTION
					String digimon2 = getClosestDigimon(d2TextField.getText().toLowerCase());
					d2TextField.setText(digimon2.toUpperCase());
					
					int subject1Index = Main.digimonNameList.indexOf(digimon1);
					int subject2Index = Main.digimonNameList.indexOf(digimon2);
					Digimon subject1 = Main.digimonList.get(subject1Index);
					Digimon subject2 = Main.digimonList.get(subject2Index);
					Digimon result = Main.DNADigivolve(subject1, subject2);
					
					//WRITES OUTPUT
					printHeader();
					if(result!=null) {
						String output = "";
						String space = "";
						Integer el1 = (Integer) maxLevelD1.getValue();
						Integer el2 = (Integer) maxLevelD2.getValue();
						Integer elResult = Math.max(el1, el2) + Math.min(el1, el2)/5;
						ArrayList<Digivolution> list;
						
						if(chckbxIncludeAntidigivolutions.isSelected()){
							list = result.getDigivolutions(0, 99);
							int cont = 0;
							txtrResultarea.println(String.format(formatEl, result.getName(), Digimon.convertType(result.getType()), Digimon.convertLevel(result.getLevel()), "", elResult));
							for(int i=0; i<list.size(); i++){
								if(list.get(i).getDigimon().getLevel()*10+1>elResult) continue;
								cont = list.get(i).getDigimon().getLevel() - result.getLevel();
								space = spaceMaker(cont);
								output = String.format(formatDigiv, space+"+"+list.get(i).getDigimon().getName(), Digimon.convertType(list.get(i).getDigimon().getType()), Digimon.convertLevel(list.get(i).getDigimon().getLevel()), list.get(i).getMinDp()+(list.get(i).getMaxDp()!=99?"-"+list.get(i).getMaxDp():"+"));
								txtrResultarea.println(output, color[cont]);
							}
						}else{
							txtrResultarea.println(String.format(formatDigiv, result.getName(), Digimon.convertType(result.getType()), Digimon.convertLevel(result.getLevel()), elResult));
						}
						txtrResultarea.println();
					}
				}else if(menuAction.equals("getMaterials")){
					//DNA GET FUSION
					int subject1Index = Main.digimonNameList.indexOf(digimon1);
					Digimon subject1 = Main.digimonList.get(subject1Index);
					ArrayList<Digimon>[] result = Main.DNAGetFusion(subject1, checkType, checkLevel);
					
					//Writes Output
					printArrayListPairs(result);
				}else if(menuAction.equals("getMaterial")){
					//DNA GET MATERIAL
					String digimon2 = getClosestDigimon(d2TextField.getText().toLowerCase());
					d2TextField.setText(digimon2.toUpperCase());
					
					int subject1Index = Main.digimonNameList.indexOf(digimon1);
					int subject2Index = Main.digimonNameList.indexOf(digimon2);
					Digimon subject1 = Main.digimonList.get(subject1Index);
					Digimon subject2 = Main.digimonList.get(subject2Index);
					ArrayList<Digimon> result = Main.DNAGetSubject(subject1, subject2, checkType, checkLevel);
					
					//Writes output
					printList(result);
				}else if(menuAction.equals("digimonDigivolve")){
					//DIGIMON GET DIGIVOLUTIONS
					if(chckbxIncludeAntidigivolutions.isSelected()){
						printDigivolutionList(Main.getDigimonByName(digimon1).getDigivolutions(0, 99));
					}else{
						printDigivolutionList(Main.getDigimonByName(digimon1).getNextDigivolution(Main.getDigimonByName(digimon1)));
					}
				}else if(menuAction.equals("digimonAntievolve")){
					//DIGIMON GET ANTIDIGIVOLUTIONS
					if(chckbxIncludeAntidigivolutions.isSelected()){
						printAntievolutionList(Main.getDigimonByName(digimon1).getAntievolutionsDp(0, 99));
					}else{
						printAntievolutionList(Main.getDigimonByName(digimon1).getPreviousDigivolution(Main.getDigimonByName(digimon1)));
					}
				}else if(menuAction.equals("digivolveMaterial")){
					//DNA DIGIVOLVE 1 MATERIAL
					int subject1Index = Main.digimonNameList.indexOf(digimon1);
					Digimon subject1 = Main.digimonList.get(subject1Index);
					ArrayList<Digimon>[] result = Main.DNAGetCombinations(subject1, checkType, checkLevel);
					
					//WRITES OUTPUT
					printCombinations(result);
				}else if(menuAction.equals("skillByDigimon")){
					//SHOW SKILLS BY DIGIMON
					int index = Main.digimonNameList.indexOf(digimon1);
					printDigimonSkill(Main.digimonList.get(index));
				}else if(menuAction.equals("skillsByName")){
					//SHOW SKILLS BY DIGIMON
					int index = Main.digimonNameList.indexOf(digimon1);
					printDigimonSkill(Main.digimonList.get(index));
				}
			}
		}
	}
	
	private class checkListHandler implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			if(event.getSource() == chckbxMarkAll) {
				chckbxVaccine.setSelected(chckbxMarkAll.isSelected());
				chckbxVirus.setSelected(chckbxMarkAll.isSelected());
				chckbxData.setSelected(chckbxMarkAll.isSelected());
			}
			if(event.getSource() == chckbxMarkAllLvl) {
				if(chckbxRookie.isEnabled()) chckbxRookie.setSelected(chckbxMarkAllLvl.isSelected());
				chckbxChampion.setSelected(chckbxMarkAllLvl.isSelected());
				chckbxUltimate.setSelected(chckbxMarkAllLvl.isSelected());
				chckbxMega.setSelected(chckbxMarkAllLvl.isSelected());
			}
			if(event.getSource() == chckbxIncludeAntidigivolutions){
				spinnerMin.setEnabled(chckbxIncludeAntidigivolutions.isSelected());
				spinnerMax.setEnabled(chckbxIncludeAntidigivolutions.isSelected());
				lblDp.setEnabled(chckbxIncludeAntidigivolutions.isSelected());
			}
		}
	}
	
	private void setInitialState(){
		//Everything is false (first screen)
		lblType.setVisible(false);
		lblLevel.setVisible(false);
		chckbxMarkAll.setVisible(false);
		chckbxMarkAllLvl.setVisible(false);
		chckbxVaccine.setVisible(false);
		chckbxVirus.setVisible(false);
		chckbxData.setVisible(false);
		chckbxRookie.setVisible(false);
		chckbxChampion.setVisible(false);
		chckbxUltimate.setVisible(false);
		chckbxMega.setVisible(false);
	}
	
	private void clearScreen(){
		//Levels
		maxLevelD1.setVisible(false);
		maxLevelD2.setVisible(false);
		lblMaxEl.setVisible(false);
		lblMaxEl_1.setVisible(false);
		
		//Digimon 1 and 2 text and label
		lblDigimon1.setText("Digimon 1");
		lblDigimon1.setVisible(false);
		d1TextField.setVisible(false);
		lblDigimon2.setText("Digimon 2");
		lblDigimon2.setVisible(false);
		d2TextField.setVisible(false);
		
		//Boxes
		lblType.setVisible(false);
		lblLevel.setVisible(false);
		chckbxMarkAll.setVisible(false);
		chckbxMarkAllLvl.setVisible(false);
		chckbxVaccine.setVisible(false);
		chckbxVirus.setVisible(false);
		chckbxData.setVisible(false);
		chckbxRookie.setVisible(false);
		chckbxChampion.setVisible(false);
		chckbxUltimate.setVisible(false);
		chckbxMega.setVisible(false);
		chckbxIncludeAntidigivolutions.setVisible(false);
		chckbxIncludeAntidigivolutions.setText("Include Digivolutions.");
		chckbxIncludeAntidigivolutions.setSelected(false);
		spinnerMin.setVisible(false);
		spinnerMax.setVisible(false);
		lblDp.setVisible(false);
		label.setVisible(false);
		spinnerMin.setEnabled(false);
		spinnerMax.setEnabled(false);
		lblDp.setEnabled(false);
		label.setEnabled(false);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource() == mntmExit) {
			System.exit(0);
		}
		if(event.getSource() == mntmSave) {
			chooser = new JFileChooser(); 
			chooser.setCurrentDirectory(new java.io.File("."));
			chooser.setDialogTitle("Save as:");
			if (chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) { 
				try {
					FileWriter writer = new FileWriter(chooser.getSelectedFile());
					writer.write(txtrResultarea.getText());
					writer.close();
				} catch (IOException e) {
					System.err.println("File 'output.txt' not found.");
				}
			}
		}else{
			d1TextField.setText("");
			d2TextField.setText("");
			if(event.getSource() == mntmDigivolve) {
				menuAction="dnaDigivolve";
				clearScreen();
				
				//Levels
				maxLevelD1.setVisible(true);
				maxLevelD2.setVisible(true);
				lblMaxEl.setVisible(true);
				lblMaxEl_1.setVisible(true);
				
				//Digimon 1 and 2 text and label
				lblDigimon1.setText("Digimon 1");
				lblDigimon1.setVisible(true);
				d1TextField.setVisible(true);
				lblDigimon2.setText("Digimon 2");
				lblDigimon2.setVisible(true);
				d2TextField.setVisible(true);
				
				//Boxes
				chckbxIncludeAntidigivolutions.setVisible(true);
				chckbxIncludeAntidigivolutions.setText("Include Digivolutions.");
				chckbxIncludeAntidigivolutions.setSelected(true);
				spinnerMin.setVisible(true);
				spinnerMax.setVisible(true);
				lblDp.setVisible(true);
				label.setVisible(true);
				spinnerMin.setEnabled(true);
				spinnerMax.setEnabled(true);
				lblDp.setEnabled(true);
				label.setEnabled(true);
			}
			if(event.getSource() == mntmDigivolveMaterial) {
				menuAction="digivolveMaterial";
				clearScreen();
				
				//Digimon text and label
				lblDigimon1.setText("Digimon 1");
				lblDigimon1.setVisible(true);
				d1TextField.setVisible(true);
				
				//Boxes
				lblType.setVisible(true);
				lblLevel.setVisible(true);
				chckbxMarkAll.setVisible(true);
				chckbxMarkAllLvl.setVisible(true);
				chckbxVaccine.setVisible(true);
				chckbxVirus.setVisible(true);
				chckbxData.setVisible(true);
				chckbxRookie.setVisible(true);
				chckbxRookie.setEnabled(false);
				chckbxChampion.setVisible(true);
				chckbxUltimate.setVisible(true);
				chckbxMega.setVisible(true);
				chckbxIncludeAntidigivolutions.setVisible(true);
				chckbxIncludeAntidigivolutions.setText("Include Antidigivolutions.");
				chckbxIncludeAntidigivolutions.setSelected(true);
				spinnerMin.setVisible(true);
				spinnerMax.setVisible(true);
				lblDp.setVisible(true);
				label.setVisible(true);
				spinnerMin.setEnabled(true);
				spinnerMax.setEnabled(true);
				lblDp.setEnabled(true);
				label.setEnabled(true);
			}
			if(event.getSource() == mntmGetMaterials) {
				menuAction="getMaterials";
				clearScreen();
				
				//Digimon text and label
				lblDigimon1.setText("Result");
				lblDigimon1.setVisible(true);
				d1TextField.setVisible(true);
				
				//Boxes
				lblType.setVisible(true);
				lblLevel.setVisible(true);
				chckbxMarkAll.setVisible(true);
				chckbxMarkAllLvl.setVisible(true);
				chckbxVaccine.setVisible(true);
				chckbxVirus.setVisible(true);
				chckbxData.setVisible(true);
				chckbxRookie.setVisible(true);
				chckbxRookie.setVisible(true);
				chckbxRookie.setEnabled(false);
				chckbxChampion.setVisible(true);
				chckbxUltimate.setVisible(true);
				chckbxMega.setVisible(true);
			}
			if(event.getSource() == mntmGetMaterial) {
				menuAction="getMaterial";
				clearScreen();
								
				//Digimon 1 and 2 text and label
				lblDigimon1.setText("Digimon 1");
				lblDigimon1.setVisible(true);
				d1TextField.setVisible(true);
				lblDigimon2.setText("Result");
				lblDigimon2.setVisible(true);
				d2TextField.setVisible(true);
				
				//Boxes
				lblType.setVisible(true);
				lblLevel.setVisible(true);
				chckbxMarkAll.setVisible(true);
				chckbxMarkAllLvl.setVisible(true);
				chckbxVaccine.setVisible(true);
				chckbxVirus.setVisible(true);
				chckbxData.setVisible(true);
				chckbxRookie.setVisible(true);
				chckbxRookie.setVisible(true);
				chckbxRookie.setEnabled(false);
				chckbxChampion.setVisible(true);
				chckbxUltimate.setVisible(true);
				chckbxMega.setVisible(true);
				chckbxIncludeAntidigivolutions.setVisible(true);
				chckbxIncludeAntidigivolutions.setText("Include Antidigivolutions.");
				chckbxIncludeAntidigivolutions.setSelected(true);
				spinnerMin.setVisible(true);
				spinnerMax.setVisible(true);
				lblDp.setVisible(true);
				label.setVisible(true);
				spinnerMin.setEnabled(true);
				spinnerMax.setEnabled(true);
				lblDp.setEnabled(true);
				label.setEnabled(true);
			}
			if(event.getSource() == mntmDigivolve_1) {
				menuAction="digimonDigivolve";
				clearScreen();
				
				//Digimon text and label
				lblDigimon1.setText("Digimon 1");
				lblDigimon1.setVisible(true);
				d1TextField.setVisible(true);
				
				//Boxes
				chckbxIncludeAntidigivolutions.setVisible(true);
				chckbxIncludeAntidigivolutions.setText("Include All Digivolutions.");
				chckbxIncludeAntidigivolutions.setSelected(true);
			}
			if(event.getSource() == mntmAntidigivolve) {
				menuAction="digimonAntievolve";
				clearScreen();
				
				//Digimon text and label
				lblDigimon1.setText("Digimon 1");
				lblDigimon1.setVisible(true);
				d1TextField.setVisible(true);
				
				//Boxes
				chckbxIncludeAntidigivolutions.setVisible(true);
				chckbxIncludeAntidigivolutions.setText("Include All Antidigivolutions.");
				chckbxIncludeAntidigivolutions.setSelected(true);
			}
			if(event.getSource() == mntmByDigimon) {
				menuAction="skillByDigimon";
				clearScreen();
					
				//Digimon text and label
				lblDigimon1.setText("Digimon 1");
				lblDigimon1.setVisible(true);
				d1TextField.setVisible(true);
			}
			if(event.getSource() == mntmByName) {
				menuAction="skillsByName";
				clearScreen();
					
				//Digimon text and label
				lblDigimon1.setText("Skill Name");
				lblDigimon1.setVisible(true);
				d1TextField.setVisible(true);
			}
			if(event.getSource() == mntmAboutMe) {
				if(aboutMe==null){
					JPanel p = new JPanelAboutMe();
					JFrame f = new JFrame("About Me");
					f.getContentPane().add(p, BorderLayout.CENTER);
					f.setSize(450, 300);
					f.setResizable(false);
					
					SwingUtilities.invokeLater(new Runnable() {
			            @Override
			            public void run() {
			                f.setVisible(true);
			            }
			        });
				}else{
					aboutMe.setVisible(true);
				}
			}
			if(event.getSource() == mntmPreferences) {
				if(aboutMe==null){
					JPanel p = new JPanelPreferences(this);
					JFrame f = new JFrame("Preferences");
					f.getContentPane().add(p, BorderLayout.CENTER);
					f.setSize(250, 100);
					f.setResizable(false);
					
					SwingUtilities.invokeLater(new Runnable() {
			            @Override
			            public void run() {
			                f.setVisible(true);
			            }
			        });
				}else{
					preferences.setVisible(true);
				}
			}
		}
	}
}
