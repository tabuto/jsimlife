/**
* @author Francesco di Dio
* Date: 27/dic/2010 18.15.45
* Titolo: JFrameNewCell.java
* Versione: 0.1.13.2 Rev.a:
*/


/*
 * Copyright (c) 2010 Francesco di Dio.
 * tabuto83@gmail.com 
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

 /*
  * Inserisci qui breve descrizione in italiano della classe
  */

package com.tabuto.jlife.gui;




import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

import com.tabuto.jenetic.Dna;
import com.tabuto.jlife.JLife;
import com.tabuto.jlife.Zetatron;
import com.tabuto.jlife.Zlife;
import com.tabuto.jlife.Zretador;



/**
 * A JFRame to insert new ZLifes (Zlifes, Zredator or Zetatron) into Simulation Game
 * @author tabuto83
 *
 */
public class JFrameNewCell extends JFrame implements ActionListener {
	
	//private Game references

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JLife Game;
	//COMPONENT
	JPanel north,dnaPanel,south,east;
	
	JLabel nameLabel,xLabel,yLabel,radiusLabel,dnaParamLabel,energyLabel;
	
	JTextField nameField, xField, yField;
    
	JButton okButton, cancelButton, colorButton;
	JButton saveButton, loadButton;
	
	JSlider radiusSlider,dnaParamSlider,energySlider,maxEnergySlider;
	JSlider hornyEnergySlider,hungryEnergySlider,lifeCycleSlider,metabolismSlider;
	JSlider boredSpeedSlider,riproductionEnergySlider,hungrySpeedSlider;
	JSlider hornySpeedSlider,scarySpeedSlider,ageFactorSlider, directionSlider;
	JSlider multiplierSlider;
	
	JComboBox cellChooser;
	
	JTextArea description;
	JScrollPane descriptionScroll;
	
	Dna ZlifeDna = new Dna();
	
	Zlife newCell;
	Zretador newZretador;
	Zetatron newZetatron;
	
	private ResourceBundle resource;
	
	/**
	 * Build a JFrameNewCell
	 * @param game Game where insert the new ZLife
	 */
	public JFrameNewCell(final JLife game)
	{
		super();
		Game = game;
		resource = ResourceBundle.getBundle("StringAndLabels", Game.getConfiguration().getLocale() );
		this.setTitle( resource.getString( "jfnc_title" ));
		
		//INIT PANELS
		north = new JPanel();
		dnaPanel = new JPanel();
		south = new JPanel();
		east = new JPanel();
		
		setLayout( new BorderLayout());
		//setPreferredSize(new Dimension(460,700));
		
		//INIT BUTTON
		JButton okButton = new JButton(resource.getString( "jfnc_ok" ));
		JButton cancelButton = new JButton(resource.getString( "jfnc_cancel" ));
		JButton saveButton = new JButton(resource.getString( "jfnc_save" ));
		JButton loadButton = new JButton(resource.getString( "jfnc_load" ));
		
		//ADD NORTH COMPONENT
		north.setLayout( new FlowLayout(FlowLayout.LEFT));
		addNameField();
		addXYField();
		addColorChooser();
		addCellChooser();
		
		//ADD DNA PANEL COMPONENT
		dnaPanel.setLayout( new GridLayout(0,4,5,5));
		addRadiusSlider();
		addDnaParamSlider();
		addEnergySlider();
		addMaxEnergySlider();
		addHornyEnergySlider();
		addHungryEnergySlider();
		addLifeCycleSlider();
		addMetabolismSlider();
		addBoredSpeedSlider();
		addRiproductionEnergySlider();
		addHungrySpeedSlider();
		addHornySpeedSlider();
		addScarySpeedSlider();
		addAgeFactorSlider();
		addDirectionSlider();
		
		addMultiplierSlider();
		
		//ADD SOUTH COMPONENT
		south.setLayout( new FlowLayout(FlowLayout.LEFT));
		south.add(okButton);
		south.add(saveButton);
		south.add(loadButton);
		south.add(cancelButton);
		
		
		//INIT DESCRIPTION
		description = new JTextArea();
		description.setEditable(false);
		description.setPreferredSize(new Dimension(170,300) );
		description.setLineWrap(true);
		description.setWrapStyleWord(true);
		Border border = BorderFactory.createBevelBorder(BevelBorder.LOWERED ); 
		description.setBorder(border);
		//descriptionScroll = new JScrollPane(description);
		//descriptionScroll.setPreferredSize(new Dimension(170,300));
		//east.add(descriptionScroll);
		
		//ADD EAST COMPONENT
		east.add(description);
		
		/*
		 * When the okButton pressed, read the JSlider values and create a new DNA
		 * to insert into new ZLifes
		 */
		okButton.addActionListener(new ActionListener()
		   {
			public void actionPerformed(ActionEvent actionEvent) 
				{
				
				//Check values before creates ZLifes
				checkValues();
				//Create a new Zlife
				createZlife();
				//Add the correct Zlife's number into simulation
				addZlifes();
			
				dispose();
				} 
			});
		
		//WHEN CANCEL BUTTON PRESSED EXIT
		cancelButton.addActionListener(new ActionListener()
		   {
			public void actionPerformed(ActionEvent actionEvent) 
				{
				dispose();
				} 
			});
		//WHEN SAVE BUTTON PRESSED SAVE CURRENT CONFIGURATION
		//INTO XML FILE
		saveButton.addActionListener(new ActionListener()
		   {
			public void actionPerformed(ActionEvent actionEvent) 
				{
					saveDna();
				}
		   });
		
		//WHEN LOAD BUTTON PRESSED LOAD CURRENT CONFIGURATION
		loadButton.addActionListener(new ActionListener()
		   {
			public void actionPerformed(ActionEvent actionEvent) 
				{
					loadDna();
				}
		   });
		
		
		//ADD PANELS TO THE JFRAME
		add(north,BorderLayout.NORTH);
		add(dnaPanel,BorderLayout.CENTER);
		add(east,BorderLayout.EAST);
		add(south,BorderLayout.SOUTH);
		
		//ADD ICON
		 this.setIconImage(java.awt.Toolkit.getDefaultToolkit().getImage
	        		(this.getClass().getResource("icon/icon_alpha_48x48.gif")));
		
		//FIRST ZLIFE DESCRIPTION
		 updateDescriptionPanel("Zlife");
		 
	}
	
	/**
	 * Provides a JFileChooser to save a XML Dna file
	 */
	protected void loadDna() {
		try{
		  JFileChooser fileChooser = new JFileChooser();
	      File f = new File(Game.getPath()+"/.");
	      fileChooser.setCurrentDirectory(f);
          int n = fileChooser.showOpenDialog(this);
          if (n == JFileChooser.APPROVE_OPTION) 
          	{	
    			  ZlifeDna.loadXML( 
    					  fileChooser.getSelectedFile().getAbsolutePath()
    					  );
          	}
		}catch (Exception ex) {}
		
          
          //SET THE SLIDER WITH loaded DNA's values
         nameField.setText( ZlifeDna.getName());
          
         dnaParamSlider.setValue((int) (ZlifeDna.getGene("dnaParam").doubleValue() *100 ));
         energySlider.setValue((int) (ZlifeDna.getGene("energy").doubleValue() ));
         maxEnergySlider.setValue((int) (ZlifeDna.getGene("maxEnergy").doubleValue() ));
         hornyEnergySlider.setValue((int) (ZlifeDna.getGene("hornyEnergy").doubleValue() ));
         hungryEnergySlider.setValue((int) (ZlifeDna.getGene("hungryEnergy").doubleValue() ));
         lifeCycleSlider.setValue( (ZlifeDna.getGene("lifeCycle").intValue()));
         metabolismSlider.setValue((int) (ZlifeDna.getGene("metabolism").doubleValue()*1000 ));
         boredSpeedSlider.setValue((int) (ZlifeDna.getGene("BoredSpeed").doubleValue() ));
         riproductionEnergySlider.setValue((int) (ZlifeDna.getGene("riproductionEnergy").doubleValue() ));
         hungrySpeedSlider.setValue((int) (ZlifeDna.getGene("hungrySpeed").doubleValue() ));
         hornySpeedSlider.setValue((int) (ZlifeDna.getGene("hornySpeed").doubleValue() ));
         scarySpeedSlider.setValue((int) (ZlifeDna.getGene("scarySpeed").doubleValue() ));
         int R = ZlifeDna.getGene("R").intValue();
         int G = ZlifeDna.getGene("G").intValue();
         int B = ZlifeDna.getGene("B").intValue();
         colorButton.setBackground(new Color(R,G,B));
         radiusSlider.setValue( (ZlifeDna.getGene("radius").intValue()));
         ageFactorSlider.setValue((int) (ZlifeDna.getGene("ageFactor").doubleValue() *100 ));
		 	
	}

	/**
	 * Provides a JFileChooser to save a XML Dna file
	 */
	protected void saveDna() {
		  try {
			  
	            JFileChooser fileChooser = new JFileChooser();
	            File dir = new File(Game.getPath()+"/"+nameField.getText());
	  	        //fileChooser.setCurrentDirectory(dir);
	            fileChooser.setSelectedFile(dir);
	            int n = fileChooser.showSaveDialog(this);
	            if (n == JFileChooser.APPROVE_OPTION) 
	            {        	
	            	nameField.setText(fileChooser.getSelectedFile().getName());
	            	createZlife();
	            	this.ZlifeDna.toXML(fileChooser.getSelectedFile().getAbsolutePath());
	            }
	           
	            
	          } catch (Exception ex) {}
		
	}

	//ADD COMPONENT METHODS
	
	private void addNameField()
	{
		nameLabel=new JLabel("Zlife-NAME");
		nameField = new JTextField(15);
		nameField.setText("New Name");
		north.add(nameLabel);
		north.add(nameField);
	}
	
	private void addXYField()
	{
		xLabel= new JLabel("X");
		yLabel= new JLabel("Y");
		xField = new JTextField(4);
		yField = new JTextField(4);
		xField.setText( Integer.toString(100) );
		yField.setText( Integer.toString(100) );
		north.add(xLabel);
		north.add(xField);
		north.add(yLabel);
		north.add(yField);
		
	}
	
	private  void addColorChooser()
	{
		int red= (int)(Math.random()*254);
		int green= (int)(Math.random()*254);
		int blue= (int)(Math.random()*254);
		Color CellColor = new Color(red,green,blue);
		final Color startColor = new Color(red,green,blue);
		colorButton = new JButton("");
		colorButton.setBackground(CellColor);
		
		colorButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent actionEvent)
			{
				 Color color = JColorChooser.showDialog(
				 rootPane, resource.getString( "jfnc_selectColor" ),startColor
				  );
				 if (color!= null)
					 colorButton.setBackground(color);
			}
		
		});
		colorButton.setToolTipText(resource.getString( "jfnc_selectColor" ));
		north.add(colorButton);
		
	}
	
	//SLIDER COMPONENTS
	
	private void addRadiusSlider()
	{
		 final int MINRAD=0;
		 final int INITRAD=6;
		 final int MAXRAD=12;
		 radiusLabel=new JLabel(resource.getString( "jfnc_radius" ));
		 
		 radiusSlider = new JSlider(JSlider.HORIZONTAL,
                 MINRAD, MAXRAD, INITRAD);
		
		 radiusSlider.setMajorTickSpacing(6);
		 radiusSlider.setMinorTickSpacing(1);
		 radiusSlider.setPaintTicks(true);
		 radiusSlider.setPaintLabels(true);
		 radiusSlider.setToolTipText(resource.getString( "jfnc_radiusDesc" ));
		 radiusLabel.setToolTipText(resource.getString( "jfnc_radiusDesc" ));
		 
		 dnaPanel.add(radiusLabel);
		 dnaPanel.add(radiusSlider);
		 
	}
	
	private void addDnaParamSlider()
	{
		 final int MIN=0;
		 final int INIT=20;
		 final int MAX=100;
		 JLabel dnaParamLabel=new JLabel(resource.getString( "jfnc_dnaParam" ));
		 dnaParamSlider = new JSlider(JSlider.HORIZONTAL,
                 MIN, MAX, INIT);
		
		 dnaParamSlider.setMajorTickSpacing(25);
		 dnaParamSlider.setMinorTickSpacing(5);
		 dnaParamSlider.setPaintTicks(true);
		 dnaParamSlider.setPaintLabels(true);
		 dnaParamSlider.setToolTipText(resource.getString( "jfnc_dnaParamDesc" ));
		 dnaParamLabel.setToolTipText(resource.getString( "jfnc_dnaParamDesc" ));
		 
		 dnaPanel.add(dnaParamLabel);
		 dnaPanel.add(dnaParamSlider);
		 
	}
	
	private void addEnergySlider()
	{
		 final int MIN=0;
		 final int INIT=25;
		 final int MAX=100;
		 JLabel energyLabel=new JLabel(resource.getString( "jfnc_energy" ));
		 energySlider = new JSlider(JSlider.HORIZONTAL,
                 MIN, MAX, INIT);
		
		 energySlider.setMajorTickSpacing(25);
		 energySlider.setMinorTickSpacing(1);
		 energySlider.setPaintTicks(true);
		 energySlider.setPaintLabels(true);
		 energySlider.setToolTipText(resource.getString( "jfnc_energyDesc" ));
		 energyLabel.setToolTipText(resource.getString( "jfnc_energyDesc" ));
		 
		 dnaPanel.add(energyLabel);
		 dnaPanel.add(energySlider);
	}
	
	private void addMaxEnergySlider()
	{
		 final int MIN=10;
		 final int INIT=140;
		 final int MAX=200;
		 JLabel maxEnergyLabel=new JLabel(resource.getString( "jfnc_maxEnergy" ));
		 maxEnergySlider = new JSlider(JSlider.HORIZONTAL,
                 MIN, MAX, INIT);
		
		 maxEnergySlider.setMajorTickSpacing(50);
		 maxEnergySlider.setMinorTickSpacing(10);
		 maxEnergySlider.setPaintTicks(true);
		 maxEnergySlider.setPaintLabels(true);
		 maxEnergySlider.setToolTipText(resource.getString( "jfnc_maxEnergyDesc" ));
		 maxEnergyLabel.setToolTipText(resource.getString( "jfnc_maxEnergyDesc" ));
		 
		 dnaPanel.add(maxEnergyLabel);
		 dnaPanel.add(maxEnergySlider);
	}
	
	private void addHornyEnergySlider()
	{
		 final int MIN=0;
		 final int INIT=75;
		 final int MAX=200;
		 JLabel hornyEnergyLabel=new JLabel(resource.getString( "jfnc_hornyEnergy" ));
		 hornyEnergySlider = new JSlider(JSlider.HORIZONTAL,
                 MIN, MAX, INIT);
		
		 hornyEnergySlider.setMajorTickSpacing(50);
		 hornyEnergySlider.setMinorTickSpacing(1);
		 hornyEnergySlider.setPaintTicks(true);
		 hornyEnergySlider.setPaintLabels(true);
		 hornyEnergySlider.setToolTipText(resource.getString( "jfnc_hornyEnergyDesc" ));
		 hornyEnergyLabel.setToolTipText(resource.getString( "jfnc_hornyEnergyDesc" ));
		 
		 dnaPanel.add(hornyEnergyLabel);
		 dnaPanel.add(hornyEnergySlider);
	}
	
	private void addHungryEnergySlider()
	{
		 final int MIN=0;
		 final int INIT=60;
		 final int MAX=200;
		 JLabel hungryEnergyLabel=new JLabel(resource.getString( "jfnc_hungryEnergy" ));
		 hungryEnergySlider = new JSlider(JSlider.HORIZONTAL,
                 MIN, MAX, INIT);
		
		 hungryEnergySlider.setMajorTickSpacing(50);
		 hungryEnergySlider.setMinorTickSpacing(1);
		 hungryEnergySlider.setPaintTicks(true);
		 hungryEnergySlider.setPaintLabels(true);
		 hungryEnergySlider.setToolTipText(resource.getString( "jfnc_hungryEnergyDesc" ));
		 hungryEnergyLabel.setToolTipText(resource.getString( "jfnc_hungryEnergyDesc" ));
		 
		 dnaPanel.add(hungryEnergyLabel);
		 dnaPanel.add(hungryEnergySlider);
	}
	
	private void addLifeCycleSlider()
	{
		 final int MIN=0;
		 final int INIT=5;
		 final int MAX=20;
		 JLabel lifeCycleLabel=new JLabel(resource.getString( "jfnc_lifeCycles" ));
		 lifeCycleSlider = new JSlider(JSlider.HORIZONTAL,
                 MIN, MAX, INIT);
		
		 lifeCycleSlider.setMajorTickSpacing(5);
		 lifeCycleSlider.setMinorTickSpacing(1);
		 lifeCycleSlider.setPaintTicks(true);
		 lifeCycleSlider.setPaintLabels(true);
		 lifeCycleSlider.setToolTipText(resource.getString( "jfnc_lifeCyclesDesc" ));
		 lifeCycleLabel.setToolTipText(resource.getString( "jfnc_lifeCyclesDesc" ));
		 
		 dnaPanel.add(lifeCycleLabel);
		 dnaPanel.add(lifeCycleSlider);
	}
	
	private void addMetabolismSlider()
	{
		 final int MIN=1;
		 final int INIT=7;
		 final int MAX=50;
		 JLabel metabolismLabel=new JLabel(resource.getString( "jfnc_metabolism" ));
		 metabolismSlider = new JSlider(JSlider.HORIZONTAL,
                 MIN, MAX, INIT);
		
		 metabolismSlider.setMajorTickSpacing(24);
		 metabolismSlider.setMinorTickSpacing(1);
		 metabolismSlider.setPaintTicks(true);
		 metabolismSlider.setPaintLabels(true);
		 metabolismSlider.setToolTipText(resource.getString( "jfnc_metabolismDesc" ));
		 metabolismLabel.setToolTipText(resource.getString( "jfnc_metabolismDesc" ));
		 
		 dnaPanel.add(metabolismLabel);
		 dnaPanel.add(metabolismSlider);
	}
	
	
	
	private void addBoredSpeedSlider()
	{
		 final int MIN=0;
		 final int INIT=40;
		 final int MAX=100;
		 JLabel boredSpeedLabel=new JLabel(resource.getString( "jfnc_boredSpeed" ));
		 boredSpeedSlider = new JSlider(JSlider.HORIZONTAL,
                 MIN, MAX, INIT);
		
		 boredSpeedSlider.setMajorTickSpacing(25);
		 boredSpeedSlider.setMinorTickSpacing(1);
		 boredSpeedSlider.setPaintTicks(true);
		 boredSpeedSlider.setPaintLabels(true);
		 boredSpeedSlider.setToolTipText(resource.getString( "jfnc_boredSpeedDesc" ));
		 boredSpeedLabel.setToolTipText(resource.getString( "jfnc_boredSpeedDesc" ));
		 
		 dnaPanel.add(boredSpeedLabel);
		 dnaPanel.add(boredSpeedSlider);
	}
	
	private void addRiproductionEnergySlider()
	{
		 final int MIN=0;
		 final int INIT=90;
		 final int MAX=100;
		 JLabel riproductionEnergyLabel=new JLabel(resource.getString( "jfnc_riproductionEnergy" ));
		 riproductionEnergySlider = new JSlider(JSlider.HORIZONTAL,
                 MIN, MAX, INIT);
		
		 riproductionEnergySlider.setMajorTickSpacing(25);
		 riproductionEnergySlider.setMinorTickSpacing(1);
		 riproductionEnergySlider.setPaintTicks(true);
		 riproductionEnergySlider.setPaintLabels(true);
		 riproductionEnergySlider.setToolTipText(resource.getString( "jfnc_riproductionEnergyDesc" ));
		 riproductionEnergyLabel.setToolTipText(resource.getString( "jfnc_riproductionEnergyDesc" ));
		 
		 dnaPanel.add(riproductionEnergyLabel);
		 dnaPanel.add(riproductionEnergySlider);
	}
	
	
	private void addHungrySpeedSlider()
	{
		 final int MIN=0;
		 final int INIT=60;
		 final int MAX=100;
		 JLabel hungrySpeedLabel=new JLabel(resource.getString( "jfnc_hungrySpeed" ));
		 hungrySpeedSlider = new JSlider(JSlider.HORIZONTAL,
                 MIN, MAX, INIT);
		
		 hungrySpeedSlider.setMajorTickSpacing(25);
		 hungrySpeedSlider.setMinorTickSpacing(1);
		 hungrySpeedSlider.setPaintTicks(true);
		 hungrySpeedSlider.setPaintLabels(true);
		 hungrySpeedSlider.setToolTipText(resource.getString( "jfnc_hungrySpeedDesc" ));
		 hungrySpeedLabel.setToolTipText(resource.getString( "jfnc_hungrySpeedDesc" ));
		 
		 dnaPanel.add(hungrySpeedLabel);
		 dnaPanel.add(hungrySpeedSlider);
	}
	
	
	private void addHornySpeedSlider()
	{
		 final int MIN=0;
		 final int INIT=90;
		 final int MAX=100;
		 JLabel hornySpeedLabel=new JLabel(resource.getString( "jfnc_hornySpeed" ));
		 hornySpeedSlider = new JSlider(JSlider.HORIZONTAL,
                 MIN, MAX, INIT);
		
		 hornySpeedSlider.setMajorTickSpacing(25);
		 hornySpeedSlider.setMinorTickSpacing(1);
		 hornySpeedSlider.setPaintTicks(true);
		 hornySpeedSlider.setPaintLabels(true);
		 hornySpeedSlider.setToolTipText(resource.getString( "jfnc_hornySpeedDesc" ));
		 hornySpeedLabel.setToolTipText(resource.getString( "jfnc_hornySpeedDesc" ));
		 
		 dnaPanel.add(hornySpeedLabel);
		 dnaPanel.add(hornySpeedSlider);
	}
	
	
	private void addScarySpeedSlider()
	{
		 final int MIN=0;
		 final int INIT=75;
		 final int MAX=100;
		 JLabel scarySpeedLabel=new JLabel(resource.getString( "jfnc_scarySpeed" ));
		 scarySpeedSlider = new JSlider(JSlider.HORIZONTAL,
                 MIN, MAX, INIT);
		
		 scarySpeedSlider.setMajorTickSpacing(25);
		 scarySpeedSlider.setMinorTickSpacing(1);
		 scarySpeedSlider.setPaintTicks(true);
		 scarySpeedSlider.setPaintLabels(true);
		 scarySpeedSlider.setToolTipText(resource.getString( "jfnc_scarySpeedDesc" ));
		 scarySpeedLabel.setToolTipText(resource.getString( "jfnc_scarySpeedDesc" ));
		 
		 dnaPanel.add(scarySpeedLabel);
		 dnaPanel.add(scarySpeedSlider);
	}
	
	
	private void addAgeFactorSlider()
	{
		 final int MIN=0;
		 final int INIT=10;
		 final int MAX=100;
		 JLabel ageFactorLabel=new JLabel(resource.getString( "jfnc_ageFactor" ));
		 ageFactorSlider = new JSlider(JSlider.HORIZONTAL,
                 MIN, MAX, INIT);
		
		 ageFactorSlider.setMajorTickSpacing(25);
		 ageFactorSlider.setMinorTickSpacing(1);
		 ageFactorSlider.setPaintTicks(true);
		 ageFactorSlider.setPaintLabels(true);
		 ageFactorSlider.setToolTipText(resource.getString( "jfnc_ageFactorDesc" ));
		 ageFactorLabel.setToolTipText(resource.getString( "jfnc_ageFactorDesc" ));
		 
		 dnaPanel.add(ageFactorLabel);
		 dnaPanel.add(ageFactorSlider);
	}
	
	private void addDirectionSlider()
	{
		 final int MIN=0;
		 final int INIT = (int) (Math.random()*360);
		 final int MAX=360;
		 JLabel directionLabel=new JLabel(resource.getString( "jfnc_direction" ));
		 
		 directionSlider = new JSlider(JSlider.HORIZONTAL,
                 MIN, MAX, INIT);
		
		 directionSlider.setMajorTickSpacing(90);
		 directionSlider.setMinorTickSpacing(15);
		 directionSlider.setPaintTicks(true);
		 directionSlider.setPaintLabels(true);
		 directionSlider.setToolTipText(resource.getString( "jfnc_directionDesc" ));
		 directionLabel.setToolTipText(resource.getString( "jfnc_directionDesc" ));
		 
		 dnaPanel.add(directionLabel);
		 dnaPanel.add(directionSlider);
	}
	
	private void addMultiplierSlider()
	{
		 final int MIN=0;
		 final int INIT = 35;
		 final int MAX=60;
		 JLabel multiplierLabel=new JLabel(resource.getString( "jfnc_zlifeNumber" ));
		 
		 multiplierSlider = new JSlider(JSlider.HORIZONTAL,
                 MIN, MAX, INIT);
		
		 multiplierSlider.setMajorTickSpacing(10);
		 multiplierSlider.setMinorTickSpacing(1);
		 multiplierSlider.setPaintTicks(true);
		 multiplierSlider.setPaintLabels(true);
		 multiplierSlider.setToolTipText(resource.getString( "jfnc_zlifeNumberDesc" ));
		 multiplierLabel.setToolTipText(resource.getString( "jfnc_zlifeNumberDesc" ));
		 
		 dnaPanel.add(multiplierLabel);
		 dnaPanel.add(multiplierSlider);
	}
	
	private void addCellChooser()
	{
		final String[] choose ={"Zlife","Zretador","Zetatron"};
		
		cellChooser = new JComboBox(choose);
		cellChooser.setSelectedIndex(0);
		cellChooser.addActionListener(this);
		north.add(cellChooser);

	}

	//WHEN A CELL CHOOSED UPDATE DESCRIPTION
	@Override
	public void actionPerformed(ActionEvent e) {
		cellChooser = (JComboBox)e.getSource();
        String choosedCell = (String)cellChooser.getSelectedItem();
        updateDescriptionPanel(choosedCell);
		
	}
	
	/**
	 * Add the correct Zlife's type and numbers to Simulation
	 */
	protected void addZlifes()
	{
		//Check the Zlife's number to create
		for(int i=0;i< multiplierSlider.getValue()+1;i++)
		{
			int choose = cellChooser.getSelectedIndex();
	
	/*
	 * Choosing the right Zlifes type: ZLife, Zredator or Zetatron?
	 */
			//ZLIFE 
			if(choose==0)
			{
				newCell = new Zlife( Game.getDimension(), 
					Integer.valueOf(xField.getText() ) ,
					Integer.valueOf(yField.getText()),
					ZlifeDna);
			
			if(multiplierSlider.getValue()<2)
				newCell.setAngleDegree( directionSlider.getValue());
			else
				newCell.setAngleRadians(Math.random()*2*Math.PI );
			
			newCell.live();
			newCell.setName( nameField.getText());
			Game.addCell(newCell);
			}
			else
				//ZRETADOR
			if (choose==1)
			{	
				newZretador = new Zretador( Game.getDimension(), 
					Integer.valueOf(xField.getText() ) ,
					Integer.valueOf(yField.getText()),
					ZlifeDna);
			
			if(multiplierSlider.getValue()<2)
				newZretador.setAngleDegree( directionSlider.getValue());
			else
				newZretador.setAngleRadians(Math.random()*2*Math.PI );
			
			newZretador.live();
			newZretador.setName( nameField.getText());
			Game.addZretador(newZretador);
			}
			else
				//ZETATRON
			if (choose==2)
			{	
				
				newZetatron = new Zetatron( Game.getDimension(), 
						Integer.valueOf(xField.getText() ) ,
						Integer.valueOf(yField.getText()),
						ZlifeDna);
				
				if(multiplierSlider.getValue()<2)
					newZetatron.setAngleDegree( directionSlider.getValue());
				else
					newZetatron.setAngleRadians(Math.random()*2*Math.PI );
				
				newZetatron.live();
				newZetatron.setName( nameField.getText());
				Game.addZetatron(newZetatron);
		}
}
	}
	
	/*
	 * Creates the correct Zlife's type and build the right dna
	 */
	protected void createZlife()
	{
		
				ZlifeDna.setName( nameField.getText());
				ZlifeDna.add((double)dnaParamSlider.getValue()/100, "dnaParam", "The Dna fuzzy parameter");
				ZlifeDna.add((double)energySlider.getValue(), "energy", "energy's zlife");
				ZlifeDna.add((double)maxEnergySlider.getValue(), "maxEnergy", "The max energy avaible for zlife");
				ZlifeDna.add((double)hornyEnergySlider.getValue() , "hornyEnergy", "The energy needs to riproduction");
				ZlifeDna.add((double)hungryEnergySlider.getValue(), "hungryEnergy", "The energy because I'm hungry");
				ZlifeDna.add(lifeCycleSlider.getValue(), "lifeCycle", "The number of lifeCycle before die");
				ZlifeDna.add((double)metabolismSlider.getValue()/1000, "metabolism", "The energy for every move");
				ZlifeDna.add((double)boredSpeedSlider.getValue() , "BoredSpeed", "The speed when i'm bored");
				ZlifeDna.add((double)riproductionEnergySlider.getValue() , "riproductionEnergy", "The energy spend for embracing");
				ZlifeDna.add((double)hungrySpeedSlider.getValue(), "hungrySpeed", "Speed when i'm hungry");
				ZlifeDna.add((double)hornySpeedSlider.getValue(), "hornySpeed", "Speed when i'm horny");
				ZlifeDna.add((double)scarySpeedSlider.getValue(), "scarySpeed", "Speed when i'm scary");
				ZlifeDna.add(colorButton.getBackground().getRed(), "R", "Red component of Zlife color");
				ZlifeDna.add(colorButton.getBackground().getGreen(), "G", "Green component of Zlife color");
				ZlifeDna.add(colorButton.getBackground().getBlue(), "B", "Blue component of Zlife color");
				ZlifeDna.add(radiusSlider.getValue(), "radius", "Zlife's radius ");
				ZlifeDna.add((double)ageFactorSlider.getValue()/100 , "ageFactor", "Zlife's age Factor ");
		
			int choose = cellChooser.getSelectedIndex();
			
			/*
			 * Choosing the right Zlifes type: ZLife, Zretador or Zetatron
			 */
			if(choose==0)
			{ //Create ZLIFE
					newCell = new Zlife( Game.getDimension(), 
							Integer.valueOf(xField.getText() ) ,
							Integer.valueOf(yField.getText()),
							ZlifeDna);
					
			}
			else
				if (choose==1)
				{	//CREATE ZRETADOR
					newZretador = new Zretador( Game.getDimension(), 
							Integer.valueOf(xField.getText() ) ,
							Integer.valueOf(yField.getText()),
							ZlifeDna);
				}
				
			else
				if (choose==2)
				{//CREATE ZETATRON
					
					//Add Zetatron's DNA parameters
					//WEIGHTS
					double HN0w0 = 1.6472350208469893;
					double HN0w1 =  1.5259734987014437;
					
					double HN1w0 = 2.6628529707196837;
					double HN1w1 = -4.3804934387500865;
					
					double HN2w0 = -2.530569115172068;
					double HN2w1 = 4.769453786615226 ;
					
					double HN3w0 = -1.8361723886103454;
					double HN3w1 = -0.596045032335354 ;
					
					double HN4w0 = 1.6588830527078904;
					double HN4w1 = 1.9764676548651472;
					
					double ON0w0 = 0.05225449773499675;
					double ON0w1 = -7.567386009338051;
					double ON0w2 = 2.7268222830976008;
					double ON0w3 = -0.6308534288149321;
					double ON0w4 = 1.2607713913426353;
					
					double ON1w0 = -2.112897695747519 ;
					double ON1w1 = 3.493104440667188;
					double ON1w2 = 2.1065398047867707;
					double ON1w3 = 4.019529986557601;
					double ON1w4= -3.6030767528508028;
					
					double ON2w0 = 0.48460613526771457;
					double ON2w1 = 3.2167614616048157;
					double ON2w2 = -5.260496061985509;
					double ON2w3 = -4.254364190317208;
					double ON2w4 = 0.2761967817824116;
						
						//WEIGHTS
						ZlifeDna.add(HN0w0, "HN0w0", "Zetatron Neural Network weight");
						ZlifeDna.add(HN0w1, "HN0w1", "Zetatron Neural Network weight");
						
						ZlifeDna.add(HN1w0, "HN1w0", "Zetatron Neural Network weight");
						ZlifeDna.add(HN1w1, "HN1w1", "Zetatron Neural Network weight");
						
						ZlifeDna.add(HN2w0, "HN2w0", "Zetatron Neural Network weight");
						ZlifeDna.add(HN2w1, "HN2w1", "Zetatron Neural Network weight");
					
						ZlifeDna.add(HN3w0, "HN3w0", "Zetatron Neural Network weight");
						ZlifeDna.add(HN3w1, "HN3w1", "Zetatron Neural Network weight");
						
						ZlifeDna.add(HN4w0, "HN4w0", "Zetatron Neural Network weight");
						ZlifeDna.add(HN4w1, "HN4w1", "Zetatron Neural Network weight");
						
						ZlifeDna.add(ON0w0, "ON0w0", "Zetatron Neural Network weight");
						ZlifeDna.add(ON0w1, "ON0w1", "Zetatron Neural Network weight");
						ZlifeDna.add(ON0w2, "ON0w2", "Zetatron Neural Network weight");
						ZlifeDna.add(ON0w3, "ON0w3", "Zetatron Neural Network weight");
						ZlifeDna.add(ON0w4, "ON0w4", "Zetatron Neural Network weight");
						
						ZlifeDna.add(ON1w0, "ON1w0", "Zetatron Neural Network weight");
						ZlifeDna.add(ON1w1, "ON1w1", "Zetatron Neural Network weight");
						ZlifeDna.add(ON1w2, "ON1w2", "Zetatron Neural Network weight");
						ZlifeDna.add(ON1w3, "ON1w3", "Zetatron Neural Network weight");
						ZlifeDna.add(ON1w4, "ON1w4", "Zetatron Neural Network weight");
						
						ZlifeDna.add(ON2w0, "ON2w0", "Zetatron Neural Network weight");
						ZlifeDna.add(ON2w1, "ON2w1", "Zetatron Neural Network weight");
						ZlifeDna.add(ON2w2, "ON2w2", "Zetatron Neural Network weight");
						ZlifeDna.add(ON2w3, "ON2w3", "Zetatron Neural Network weight");
						ZlifeDna.add(ON2w4, "ON2w4", "Zetatron Neural Network weight");
						
					
					newZetatron = new Zetatron( Game.getDimension(), 
							Integer.valueOf(xField.getText() ) ,
							Integer.valueOf(yField.getText()),
							ZlifeDna);
				}
				
	}
	
	/**
	 * Check if insert values are correct
	 */
	protected void checkValues()
	{
		try
		{
			Integer.valueOf(xField.getText() );
		}
		catch (NumberFormatException e)
		{
			xField.setForeground(Color.red);
			JOptionPane.showMessageDialog(null, 
					resource.getString( "jfnc_inputErrorDesc" ), 
					resource.getString( "jfnc_inputError" ), 
					JOptionPane.ERROR_MESSAGE);
		}
		
		try
		{
			Integer.valueOf(yField.getText() );
		}
		catch (NumberFormatException e)
		{
			yField.setForeground(Color.red);
			JOptionPane.showMessageDialog(null, 
					resource.getString( "jfnc_inputErrorDesc" ), 
					resource.getString( "jfnc_inputError" ), 
					JOptionPane.ERROR_MESSAGE);
		}
	}

	//UPDATE DESCRIPTION METHOD
	/**
	 * Set the description JTextArea using the String Parameter
	 */
	private void updateDescriptionPanel(String choosedCell) {
		
		if (choosedCell.equalsIgnoreCase("Zlife"))
		{
			
			description.setText(Zlife.getDescription());
			nameField.setText("Zlife-NAME");
			
		}
		
		if (choosedCell.equalsIgnoreCase("Zretador"))
		{
			
			description.setText(Zretador.getDescription());
			nameField.setText("Zretador-NAME");
		}
		
		description.setCaretPosition(0);
		
		if (choosedCell.equalsIgnoreCase("Zetatron"))
		{
			
			description.setText(Zetatron.getDescription());
			nameField.setText("Zetatron-NAME");
		}
		
		description.setCaretPosition(0);
		
	}
}
