/**
* @author Francesco di Dio
* Date: 29/nov/2010 18.15.45
* Titolo: JFrameNewCell.java
* Versione: 0.1.9 Rev.a:
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
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import com.tabuto.jenetic.Dna;
import com.tabuto.jlife.JLife;
import com.tabuto.jlife.Zlife;



public class JFrameNewCell extends JFrame {
	
	//private Game references

	private final JLife Game;
	//COMPONENT
	JPanel north,dnaPanel,south;
	
	JLabel nameLabel,xLabel,yLabel,radiusLabel,dnaParamLabel,energyLabel;
	JTextField nameField, xField, yField;
    JButton okButton, cancelButton, colorButton;
	JSlider radiusSlider,dnaParamSlider,energySlider,maxEnergySlider;
	JSlider hornyEnergySlider,hungryEnergySlider,lifeCycleSlider,metabolismSlider;
	JSlider boredSpeedSlider,riproductionEnergySlider,hungrySpeedSlider;
	JSlider hornySpeedSlider,scarySpeedSlider,ageFactorSlider, directionSlider;
	
	JSlider multiplierSlider;
	
	
	public JFrameNewCell(final JLife Game)
	{
		super("New Cell");
		this.Game = Game;
		
		north = new JPanel();
		dnaPanel = new JPanel();
		south = new JPanel();
		
		setLayout( new BorderLayout());
	

		//setPreferredSize(new Dimension(460,700));
		
		JButton okButton = new JButton("OK");
		JButton cancelButton = new JButton("Cancel");
		
		north.setLayout( new FlowLayout(FlowLayout.LEFT));
		addNameField();
		addXYField();
		addColorChooser();
		
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
		
		south.setLayout( new FlowLayout(FlowLayout.LEFT));
		south.add(okButton);
		south.add(cancelButton);
		
		okButton.addActionListener(new ActionListener()
		   {
			public void actionPerformed(ActionEvent actionEvent) 
				{
				
				/*
				newCell.setDnaParam(dnaParamSlider.getValue()/100 );
				newCell.setEnergy(energySlider.getValue() );
				newCell.setMaxEnergy(maxEnergySlider.getValue() );
				newCell.setHornyEnergy(hornyEnergySlider.getValue() );
				newCell.setLifeCycle(lifeCycleSlider.getValue() );
				newCell.setMetabolism(metabolismSlider.getValue()/1000 );
				newCell.setBoredSpeed(boredSpeedSlider.getValue() );
				newCell.setRiproductionEnergy(riproductionEnergySlider.getValue() );
				newCell.setHungrySpeed(hungrySpeedSlider.getValue() );
				newCell.setHornySpeed(hornySpeedSlider.getValue() );
				newCell.setScarySpeed(scarySpeedSlider.getValue() );
				newCell.setAgeFactor(ageFactorSlider.getValue()/100 );
				*/
		for(int i=0;i< multiplierSlider.getValue();i++)
		{
				Dna ZlifeDna = new Dna();
				
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
				
					Zlife newCell = new Zlife( Game.getDimension(), 
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
				dispose();
				} 
			});
		
		cancelButton.addActionListener(new ActionListener()
		   {
			public void actionPerformed(ActionEvent actionEvent) 
				{
				dispose();
				} 
			});
		
		add(north,BorderLayout.NORTH);
		add(dnaPanel,BorderLayout.CENTER);
		add(south,BorderLayout.SOUTH);
		
	}
	
	private void addNameField()
	{
		nameLabel=new JLabel("Name");
		nameField = new JTextField(15);
		north.add(nameLabel);
		north.add(nameField);
		
		
	}
	
	private void addXYField()
	{
		xLabel= new JLabel("X");
		yLabel= new JLabel("Y");
		xField = new JTextField(4);
		yField = new JTextField(4);
		north.add(xLabel);
		north.add(xField);
		north.add(yLabel);
		north.add(yField);
		
	}
	

	
	private  void addColorChooser()
	{
		Color CellColor = new Color(125,125,125);
		final Color startColor = new Color(125,125,125);
		colorButton = new JButton("");
		colorButton.setBackground(CellColor);
		
		colorButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent actionEvent)
			{
				 Color color = JColorChooser.showDialog(
				 rootPane, "Select Color",startColor
				  );
				 if (color!= null)
					 colorButton.setBackground(color);
			}
		
		});
		colorButton.setToolTipText("Select Color");
		north.add(colorButton);
		
	}
	
	//SLIDER COMPONENTS
	
	private void addRadiusSlider()
	{
		 final int MINRAD=0;
		 final int INITRAD=6;
		 final int MAXRAD=12;
		 radiusLabel=new JLabel("Radius");
		 
		 radiusSlider = new JSlider(JSlider.HORIZONTAL,
                 MINRAD, MAXRAD, INITRAD);
		
		 radiusSlider.setMajorTickSpacing(6);
		 radiusSlider.setMinorTickSpacing(1);
		 radiusSlider.setPaintTicks(true);
		 radiusSlider.setPaintLabels(true);
		 radiusSlider.setToolTipText("Select the Zlife's radius size");
		 radiusLabel.setToolTipText("Select the Zlife's radius size");
		 
		 dnaPanel.add(radiusLabel);
		 dnaPanel.add(radiusSlider);
		 
	}
	
	private void addDnaParamSlider()
	{
		 final int MIN=0;
		 final int INIT=20;
		 final int MAX=100;
		 JLabel dnaParamLabel=new JLabel("Dna Parameter");
		 dnaParamSlider = new JSlider(JSlider.HORIZONTAL,
                 MIN, MAX, INIT);
		
		 dnaParamSlider.setMajorTickSpacing(25);
		 dnaParamSlider.setMinorTickSpacing(5);
		 dnaParamSlider.setPaintTicks(true);
		 dnaParamSlider.setPaintLabels(true);
		 dnaParamSlider.setToolTipText("Select the DNA's variability parameter");
		 dnaParamLabel.setToolTipText("Select the DNA's variability parameter");
		 
		 dnaPanel.add(dnaParamLabel);
		 dnaPanel.add(dnaParamSlider);
		 
	}
	
	private void addEnergySlider()
	{
		 final int MIN=0;
		 final int INIT=70;
		 final int MAX=100;
		 JLabel energyLabel=new JLabel("Energy");
		 energySlider = new JSlider(JSlider.HORIZONTAL,
                 MIN, MAX, INIT);
		
		 energySlider.setMajorTickSpacing(25);
		 energySlider.setMinorTickSpacing(1);
		 energySlider.setPaintTicks(true);
		 energySlider.setPaintLabels(true);
		 energySlider.setToolTipText("Starting Zlife's energy");
		 energyLabel.setToolTipText("Starting Zlife's energy");
		 
		 dnaPanel.add(energyLabel);
		 dnaPanel.add(energySlider);
	}
	
	private void addMaxEnergySlider()
	{
		 final int MIN=10;
		 final int INIT=100;
		 final int MAX=200;
		 JLabel maxEnergyLabel=new JLabel("Max Energy");
		 maxEnergySlider = new JSlider(JSlider.HORIZONTAL,
                 MIN, MAX, INIT);
		
		 maxEnergySlider.setMajorTickSpacing(50);
		 maxEnergySlider.setMinorTickSpacing(10);
		 maxEnergySlider.setPaintTicks(true);
		 maxEnergySlider.setPaintLabels(true);
		 maxEnergySlider.setToolTipText("Max Zlife's energy");
		 maxEnergyLabel.setToolTipText("Max Zlife's energy");
		 
		 dnaPanel.add(maxEnergyLabel);
		 dnaPanel.add(maxEnergySlider);
	}
	
	private void addHornyEnergySlider()
	{
		 final int MIN=0;
		 final int INIT=90;
		 final int MAX=200;
		 JLabel hornyEnergyLabel=new JLabel("Horny Energy");
		 hornyEnergySlider = new JSlider(JSlider.HORIZONTAL,
                 MIN, MAX, INIT);
		
		 hornyEnergySlider.setMajorTickSpacing(50);
		 hornyEnergySlider.setMinorTickSpacing(1);
		 hornyEnergySlider.setPaintTicks(true);
		 hornyEnergySlider.setPaintLabels(true);
		 hornyEnergySlider.setToolTipText("Energy higher than which ZLife is Horny");
		 hornyEnergyLabel.setToolTipText("Energy higher than ZLife is Horny");
		 
		 dnaPanel.add(hornyEnergyLabel);
		 dnaPanel.add(hornyEnergySlider);
	}
	
	private void addHungryEnergySlider()
	{
		 final int MIN=0;
		 final int INIT=30;
		 final int MAX=200;
		 JLabel hungryEnergyLabel=new JLabel("Hungry Energy");
		 hungryEnergySlider = new JSlider(JSlider.HORIZONTAL,
                 MIN, MAX, INIT);
		
		 hungryEnergySlider.setMajorTickSpacing(50);
		 hungryEnergySlider.setMinorTickSpacing(1);
		 hungryEnergySlider.setPaintTicks(true);
		 hungryEnergySlider.setPaintLabels(true);
		 hungryEnergySlider.setToolTipText("Energy below which ZLife is hungry");
		 hungryEnergyLabel.setToolTipText("Energy below which ZLife is hungry");
		 
		 dnaPanel.add(hungryEnergyLabel);
		 dnaPanel.add(hungryEnergySlider);
	}
	
	private void addLifeCycleSlider()
	{
		 final int MIN=0;
		 final int INIT=5;
		 final int MAX=20;
		 JLabel lifeCycleLabel=new JLabel("Life Cycles");
		 lifeCycleSlider = new JSlider(JSlider.HORIZONTAL,
                 MIN, MAX, INIT);
		
		 lifeCycleSlider.setMajorTickSpacing(5);
		 lifeCycleSlider.setMinorTickSpacing(1);
		 lifeCycleSlider.setPaintTicks(true);
		 lifeCycleSlider.setPaintLabels(true);
		 lifeCycleSlider.setToolTipText("Every Zlife spend one lifeCycle when it change his state.How many cycle it can be spend before it dies?");
		 lifeCycleLabel.setToolTipText("Every Zlife spend one lifeCycle when it change his state.How many cycle it can be spend before it dies?");
		 
		 dnaPanel.add(lifeCycleLabel);
		 dnaPanel.add(lifeCycleSlider);
	}
	
	private void addMetabolismSlider()
	{
		 final int MIN=1;
		 final int INIT=2;
		 final int MAX=50;
		 JLabel metabolismLabel=new JLabel("Metabolism");
		 metabolismSlider = new JSlider(JSlider.HORIZONTAL,
                 MIN, MAX, INIT);
		
		 metabolismSlider.setMajorTickSpacing(24);
		 metabolismSlider.setMinorTickSpacing(1);
		 metabolismSlider.setPaintTicks(true);
		 metabolismSlider.setPaintLabels(true);
		 metabolismSlider.setToolTipText("Energy spends by Zlife for each move");
		 metabolismLabel.setToolTipText("Energy spends by Zlife for each move");
		 
		 dnaPanel.add(metabolismLabel);
		 dnaPanel.add(metabolismSlider);
	}
	
	
	
	private void addBoredSpeedSlider()
	{
		 final int MIN=0;
		 final int INIT=40;
		 final int MAX=100;
		 JLabel boredSpeedLabel=new JLabel("Bored Speed");
		 boredSpeedSlider = new JSlider(JSlider.HORIZONTAL,
                 MIN, MAX, INIT);
		
		 boredSpeedSlider.setMajorTickSpacing(25);
		 boredSpeedSlider.setMinorTickSpacing(1);
		 boredSpeedSlider.setPaintTicks(true);
		 boredSpeedSlider.setPaintLabels(true);
		 boredSpeedSlider.setToolTipText("Zlife's Speed when its state is Bored");
		 boredSpeedLabel.setToolTipText("Zlife's Speed when its state is Bored");
		 
		 dnaPanel.add(boredSpeedLabel);
		 dnaPanel.add(boredSpeedSlider);
	}
	
	private void addRiproductionEnergySlider()
	{
		 final int MIN=0;
		 final int INIT=90;
		 final int MAX=100;
		 JLabel riproductionEnergyLabel=new JLabel("Riproduction Energy");
		 riproductionEnergySlider = new JSlider(JSlider.HORIZONTAL,
                 MIN, MAX, INIT);
		
		 riproductionEnergySlider.setMajorTickSpacing(25);
		 riproductionEnergySlider.setMinorTickSpacing(1);
		 riproductionEnergySlider.setPaintTicks(true);
		 riproductionEnergySlider.setPaintLabels(true);
		 riproductionEnergySlider.setToolTipText("How much energy spends Zlife every time it's riproducing itself?");
		 riproductionEnergyLabel.setToolTipText("How much energy spends Zlife every time it's riproducing itself?");
		 
		 dnaPanel.add(riproductionEnergyLabel);
		 dnaPanel.add(riproductionEnergySlider);
	}
	
	
	private void addHungrySpeedSlider()
	{
		 final int MIN=0;
		 final int INIT=60;
		 final int MAX=100;
		 JLabel hungrySpeedLabel=new JLabel("Hungry Speed");
		 hungrySpeedSlider = new JSlider(JSlider.HORIZONTAL,
                 MIN, MAX, INIT);
		
		 hungrySpeedSlider.setMajorTickSpacing(25);
		 hungrySpeedSlider.setMinorTickSpacing(1);
		 hungrySpeedSlider.setPaintTicks(true);
		 hungrySpeedSlider.setPaintLabels(true);
		 hungrySpeedSlider.setToolTipText("Speed when Zlife is hungry");
		 hungrySpeedLabel.setToolTipText("Speed when Zlife is hungry");
		 
		 dnaPanel.add(hungrySpeedLabel);
		 dnaPanel.add(hungrySpeedSlider);
	}
	
	
	private void addHornySpeedSlider()
	{
		 final int MIN=0;
		 final int INIT=90;
		 final int MAX=100;
		 JLabel hornySpeedLabel=new JLabel("Horny Speed");
		 hornySpeedSlider = new JSlider(JSlider.HORIZONTAL,
                 MIN, MAX, INIT);
		
		 hornySpeedSlider.setMajorTickSpacing(25);
		 hornySpeedSlider.setMinorTickSpacing(1);
		 hornySpeedSlider.setPaintTicks(true);
		 hornySpeedSlider.setPaintLabels(true);
		 hornySpeedSlider.setToolTipText("Speed when Zlife is horny");
		 hornySpeedLabel.setToolTipText("Speed when Zlife is horny");
		 
		 dnaPanel.add(hornySpeedLabel);
		 dnaPanel.add(hornySpeedSlider);
	}
	
	
	private void addScarySpeedSlider()
	{
		 final int MIN=0;
		 final int INIT=100;
		 final int MAX=100;
		 JLabel scarySpeedLabel=new JLabel("Scary Speed");
		 scarySpeedSlider = new JSlider(JSlider.HORIZONTAL,
                 MIN, MAX, INIT);
		
		 scarySpeedSlider.setMajorTickSpacing(25);
		 scarySpeedSlider.setMinorTickSpacing(1);
		 scarySpeedSlider.setPaintTicks(true);
		 scarySpeedSlider.setPaintLabels(true);
		 scarySpeedSlider.setToolTipText("Speed when Zlife is scary");
		 scarySpeedLabel.setToolTipText("Speed when Zlife is scary");
		 
		 dnaPanel.add(scarySpeedLabel);
		 dnaPanel.add(scarySpeedSlider);
	}
	
	
	private void addAgeFactorSlider()
	{
		 final int MIN=0;
		 final int INIT=10;
		 final int MAX=100;
		 JLabel ageFactorLabel=new JLabel("Age Factor");
		 ageFactorSlider = new JSlider(JSlider.HORIZONTAL,
                 MIN, MAX, INIT);
		
		 ageFactorSlider.setMajorTickSpacing(25);
		 ageFactorSlider.setMinorTickSpacing(1);
		 ageFactorSlider.setPaintTicks(true);
		 ageFactorSlider.setPaintLabels(true);
		 ageFactorSlider.setToolTipText("When Zlife spends lifeCycle its MaxEnergy decreases,how much?");
		 ageFactorLabel.setToolTipText("When Zlife spends lifeCycle its MaxEnergy decreases,how much?");
		 
		 dnaPanel.add(ageFactorLabel);
		 dnaPanel.add(ageFactorSlider);
	}
	
	private void addDirectionSlider()
	{
		 final int MIN=0;
		 final int INIT = (int) (Math.random()*360);
		 final int MAX=360;
		 JLabel directionLabel=new JLabel("Direction °");
		 
		 directionSlider = new JSlider(JSlider.HORIZONTAL,
                 MIN, MAX, INIT);
		
		 directionSlider.setMajorTickSpacing(90);
		 directionSlider.setMinorTickSpacing(15);
		 directionSlider.setPaintTicks(true);
		 directionSlider.setPaintLabels(true);
		 directionSlider.setToolTipText("Start Zlife's direction");
		 directionLabel.setToolTipText("Start Zlife's direction");
		 
		 dnaPanel.add(directionLabel);
		 dnaPanel.add(directionSlider);
	}
	
	private void addMultiplierSlider()
	{
		 final int MIN=1;
		 final int INIT = 5;
		 final int MAX=10;
		 JLabel multiplierLabel=new JLabel("Zlife Number");
		 
		 multiplierSlider = new JSlider(JSlider.HORIZONTAL,
                 MIN, MAX, INIT);
		
		 multiplierSlider.setMajorTickSpacing(5);
		 multiplierSlider.setMinorTickSpacing(1);
		 multiplierSlider.setPaintTicks(true);
		 multiplierSlider.setPaintLabels(true);
		 multiplierSlider.setToolTipText("How many ZLife you want to add to Game?");
		 multiplierLabel.setToolTipText("How many ZLife you want to add to Game?");
		 
		 dnaPanel.add(multiplierLabel);
		 dnaPanel.add(multiplierSlider);
	}
	
}
