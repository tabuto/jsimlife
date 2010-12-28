/**
* @author Francesco di Dio
* Date: 27/dic/2010 22.40.29
* Titolo: JLifeShowZlife.java
* Versione: 0.1.13.1 Rev.a:
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
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import com.tabuto.j2dgf.Game2D;
import com.tabuto.jlife.JLife;

/**
 * Simple JFrame to show detailed info about selected Cell such as:
 * Actual Life Parameter and a Genes List.
 * @author tabuto83
 *
 */
public class JLifeShowZlife extends JFrame implements Observer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public JLife Game;
	
	private JLabel nameLabel, positionLabel, directionLabel;
	private JLabel stateLabel, speedLabel, lifeCycleLabel;
	private JLabel energyLabel, radiusLabel, colorLabel;
	private JLabel generationLabel;
	
	private JTextField nameField, positionField, directionField;
	private JTextField stateField, speedField, lifeCycleField;
	private JTextField energyField, radiusField;
	private JTextField generationField;

	private JButton colorButton;
	
	private JButton saveButton;
	
	JTextArea ZlifeDna = new JTextArea();
	//JScrollPane ZlifeDnaScroll = new JScrollPane(ZlifeDna);
	
	private JPanel north,dnaPanel,south;
	private DnaPanel dnaTreePanel;
	JScrollPane ZlifeDnaScroll; 
	
	public JLifeShowZlife(Game2D game)
	{
		super("Zlife's View");
		Game = (JLife)game;
		
		north = new JPanel();
		dnaPanel = new JPanel();
		south = new JPanel();
		
		dnaTreePanel = new DnaPanel();
		dnaPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		dnaPanel.setBackground(Color.WHITE);
		dnaPanel.add(dnaTreePanel);
		ZlifeDnaScroll = new JScrollPane(dnaPanel);
		
		setLayout( new BorderLayout());
		setPreferredSize(new Dimension(250,500));
		setResizable(false);
		initComponent();
		
		setAlwaysOnTop(true);
		
		//ADD ICON
		 this.setIconImage(java.awt.Toolkit.getDefaultToolkit().getImage
	        		(this.getClass().getResource("icon/icon_alpha_48x48.gif")));
	}
	
	//Add and init component
	private void initComponent()
	{
		north.setLayout( new GridLayout(0,2,5,5));
		
		nameLabel = new JLabel("Name");
		positionLabel = new JLabel("Position");
		directionLabel = new JLabel("Direction °");
		stateLabel = new JLabel("State");
		speedLabel = new JLabel("Speed");
		lifeCycleLabel = new JLabel("Life Cycle");
		energyLabel = new JLabel("Energy");
		radiusLabel = new JLabel("Radius");
		colorLabel = new JLabel("Color");
		generationLabel= new JLabel("Generation");
		
		
		
		nameField  = new JTextField(15);
		nameField.setEditable(false);
		positionField  = new JTextField(10);
		positionField.setEditable(false);
		directionField  = new JTextField(4);
		directionField.setEditable(false);
		stateField  = new JTextField(10);
		stateField.setEditable(false);
		speedField  = new JTextField(6);
		speedField.setEditable(false);
		lifeCycleField= new JTextField(3);
		lifeCycleField.setEditable(false);
		energyField  = new JTextField(10);
		energyField.setEditable(false);
		radiusField  = new JTextField(4);
		radiusField.setEditable(false);
		generationField= new JTextField(4);
		generationField.setEditable(false);
		
		colorButton = new JButton("");
		

		
		//ADD NORTH
		north.add(nameLabel);
		north.add(nameField);
		
		north.add(positionLabel);
		north.add(positionField);
		
		north.add(directionLabel);
		north.add(directionField);
		
		north.add(stateLabel);
		north.add(stateField);
		
		north.add(speedLabel);
		north.add(speedField);
		
		north.add(lifeCycleLabel);
		north.add(lifeCycleField);
		
		north.add(energyLabel);
		north.add(energyField);
		
		north.add(radiusLabel);
		north.add(radiusField);
		
		north.add(generationLabel);
		north.add(generationField);
	
		north.add(colorLabel);
		north.add(colorButton);
		
		//ADD DNAPANEL
		//ZlifeDnaScroll.setPreferredSize(new Dimension(450, 250));
		//dnaPanel.add(ZlifeDnaScroll);
		//dnaTreePanel.add(ZlifeDnaScroll);
		//dnaPanel.add(ZlifeDnaScroll);
		//setDnaText();
		//ADD COMMAND BUTTON
		saveButton = new JButton("Save");
		saveButton.addActionListener(new ActionListener()
		   {
			public void actionPerformed(ActionEvent actionEvent) 
				{
					saveDna();
				}
		   });
		south.add(saveButton);
		
		this.add(north,BorderLayout.NORTH);
		this.add(ZlifeDnaScroll,BorderLayout.CENTER);
		this.add(south,BorderLayout.SOUTH);
	}
	
/**
 * Provides a JFileChoser for saving actual selected DNA in a XML file
 */
protected void saveDna() {
	 try {
		  
         JFileChooser fileChooser = new JFileChooser();
         File f = new File(Game.getPath()+"/"+nameField.getText());
	     fileChooser.setCurrentDirectory(f);
         fileChooser.setSelectedFile(f);
         int n = fileChooser.showSaveDialog(this);
         if (n == JFileChooser.APPROVE_OPTION) 
         {        	
         	
         	Game.getSelectedCell().getZlifeDna().toXML(fileChooser.getSelectedFile().getAbsolutePath());
            
         }
        
         
       } catch (Exception ex) {}
		
	}

/**
 * Update the information about selected ZLife
 */
public void refresh()
{
	try{
	nameField.setText( Game.getSelectedCell().getName());
	positionField.setText("["+ (int)Game.getSelectedCell().getPosition().getX() +", "+ (int)Game.getSelectedCell().getPosition().getY() +"]"  );
	directionField.setText(""+ (int) Math.toDegrees( Game.getSelectedCell().getAngle()));
	stateField.setText( Game.getSelectedCell().getState().toString());
	lifeCycleField.setText(""+Game.getSelectedCell().getActualLifeCycle()+"/"+ Game.getSelectedCell().getLifeCycle());
	speedField.setText(""+Game.getSelectedCell().getSpeed());
	energyField.setText(""+Math.rint(Game.getSelectedCell().getEnergy()*100)/100+"/"+Math.rint(Game.getSelectedCell().getMaxEnergy()*100/100) );
	radiusField.setText( ""+Math.rint(Game.getSelectedCell().getRadius()*100)/100);
	generationField.setText(""+Game.getSelectedCell().getGenerationNumber());
	
	colorButton.setBackground(new Color(Game.getSelectedCell().getR(),
										Game.getSelectedCell().getG(),
										Game.getSelectedCell().getB()));
	}
	catch(NullPointerException e)
	{
		e.printStackTrace();
	}

	
}
/**
 * Check if this Frame is visible
 */
private void checkVisibility()
{
	if(!this.isVisible())
	{
		
		this.pack();
		this.setVisible(true);
	}
}
/**
 * Set the Dna text to the JTextPane
 */
private void  setDnaText()
{
	/*
	ZlifeDna.setText( 
			Game.getSelectedCell().getZlifeDna().toString());
ZlifeDna.setCaretPosition(0);
*/ 
	//this.dnaTreePanel.close();
	dnaTreePanel.initialize( Game.getSelectedCell().getZlifeDna().toXML());
}

/**
 * Set the Current Game
 * @param game
 */
public void setGame(JLife game)
{
	Game = game;
	
}


/**
 * When an Observed SPrite change position, refresh  information!
 */
@Override
public void update(Observable arg0, Object arg1) {

		if(arg1 instanceof String)
		{
			String message = (String) arg1;
			if(message.equalsIgnoreCase("SelectionChange"))
			{
				if(Game.getSelectedCell()!=null)
				{
					refresh();
					setDnaText();
					checkVisibility();
				}
				else
				{
					if(this.isVisible())
					{
						this.setVisible(false);
						this.dnaTreePanel.close();
					}
				}
			}
			
			if(message.equalsIgnoreCase("Zlife:ChangeState"))
			{
				if(Game.getSelectedCell()!=null && this.isVisible())
				{
					refresh();
					
				}
			}
			
			if(message.equalsIgnoreCase("SeedSelected"))
			{
				JOptionPane.showMessageDialog(this, 
						"Seed quantity: "+ Game.getSelectedSeed().getQuantity(), 
						"Message", JOptionPane.INFORMATION_MESSAGE);	
			}
			
			
		}
			
	}
	
	

}
