/**
* @author Francesco di Dio
* Date: 29/dic/2010 22.01.47
* Titolo: JSLNewCellView.java
* Versione: 0.2.0 Rev.a:
*/


/*
 * Copyright (c) 2011 Francesco di Dio.
 * tabuto83@gmail.com 
 * 
 * Permission is hereby granted, free of charge, to any person
 * obtaining a copy of this software and associated documentation
 * files (the "Software"), to deal in the Software without
 * restriction, including without limitation the rights to use,
 * copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following
 * conditions:
 * 
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
 * OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 */

package com.tabuto.jsimlife.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import com.tabuto.jenetic.Dna;
import com.tabuto.jsimlife.JSimLife;
import com.tabuto.jsimlife.Zetatron;
import com.tabuto.jsimlife.Zlife;
import com.tabuto.jsimlife.Zretador;
import com.tabuto.jsimlife.actions.CreateNewZlifeAction;
import com.tabuto.jsimlife.actions.LoadDnaAction;
import com.tabuto.jsimlife.actions.SaveDnaAction;


/**
 * Class display the Frame contains all the parameters to create a new Zlife's type.
 * Some components are {@code public} in order to allow some ActionClass performs they actions.
 * 
 * @author tabuto83
 *
 */
public class JSLNewCellView extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public final JSimLife Game;
	//COMPONENT
	JPanel north,dnaPanel,south,east;
	
	JLabel nameLabel,xLabel,yLabel,radiusLabel,dnaParamLabel,energyLabel;
	
	public JTextField nameField, xField, yField;
    public JButton colorButton;
	JButton okButton, cancelButton;
	JButton saveButton, loadButton;
	
	public JSlider radiusSlider,dnaParamSlider,energySlider,maxEnergySlider;
	public JSlider hornyEnergySlider,hungryEnergySlider,lifeCycleSlider,metabolismSlider;
	public JSlider boredSpeedSlider,riproductionEnergySlider,hungrySpeedSlider;
	public JSlider hornySpeedSlider,scarySpeedSlider,ageFactorSlider, directionSlider;
	public JSlider multiplierSlider;
	
	public JComboBox cellChooser;
	
	JTextArea description;
	JScrollPane descriptionScroll;
	
	public Dna ZlifeDna = new Dna();
	
	public Zlife newCell;
	public Zretador newZretador;
	public Zetatron newZetatron;
	
	public ResourceBundle resource;

	/**
	 * Create a new JSLNewCellView and add the created Zlife into JSimLife 'game'
	 * @param game JSimLife
	 * @see JSimLife
	 */
	public JSLNewCellView(final JSimLife game)
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
		okButton.addActionListener(new CreateNewZlifeAction(this));
		cancelButton.addActionListener(new ActionListener()
		   {
			public void actionPerformed(ActionEvent actionEvent) 
				{
				dispose();
				} 
			});
		saveButton.addActionListener(new SaveDnaAction(Game.getConfiguration(),ZlifeDna));
		loadButton.addActionListener(new LoadDnaAction(this));
		
		
		//ADD PANELS TO THE JFRAME
		add(north,BorderLayout.NORTH);
		add(dnaPanel,BorderLayout.CENTER);
		add(east,BorderLayout.EAST);
		add(south,BorderLayout.SOUTH);
		
		//ADD ICON
		 this.setIconImage(java.awt.Toolkit.getDefaultToolkit().getImage
	        		(this.getClass().getResource("../icons/icon_alpha_48x48.gif")));
		
		//FIRST ZLIFE DESCRIPTION
		 updateDescriptionPanel("Zlife");
	}
	
	//ADD & INIT COMPONENT
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
