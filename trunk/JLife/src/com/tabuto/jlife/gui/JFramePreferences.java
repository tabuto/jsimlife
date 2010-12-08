/**
* @author Francesco di Dio
* Date: 08/dic/2010 17.33.29
* Titolo: JFramePreferences.java
* Versione: 0.1 Rev.a:
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

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class JFramePreferences extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel north, south;
	private JLabel SimSizeLabel, backgroundcolorLabel;
	private JTextField widthField, heightField;
	
	private JLabel MaxZlifesLabel, MaxZretadorsLabel, MaxSeedsLabel;
	private JTextField MaxZlifesField, MaxZretadorsField, MaxSeedsField;
	
	private JButton colorButton, okButton, cancelButton;
	
	private Configuration config;
	
	public JFramePreferences(Configuration c)
	{
		super("Preferences");
		config = c;
		setPreferredSize(new Dimension(450,250));
		setLayout( new BorderLayout());
		
		north = new JPanel();
		north.setLayout( new GridLayout(0,2,5,5));
		
		south = new JPanel();
		
		
		south.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		//ADD CONFIGURATION COMPONENTS TO NORTH PANEL
		addPanelSizeComponents();
		addColorButton();
		addMaxSpritesComponent();
		
		//ADD COMPONENTS TO THE SOUTH PANEL
		JButton okButton = new JButton("Save");
		JButton cancelButton = new JButton("Cancel");
		
		okButton.addActionListener(new ActionListener()
		   {
			public void actionPerformed(ActionEvent actionEvent) 
				{ 
					
					setConfigValues();
					config.save();
					JOptionPane.showMessageDialog(null, "Restart JSimLife to apply change",
													"Apply Changes",
													JOptionPane.PLAIN_MESSAGE);
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
		
		//ADD ICON
		this.setIconImage(java.awt.Toolkit.getDefaultToolkit().getImage
        		(this.getClass().getResource("icon/icon_alpha_48x48.gif")));
		
		south.add(okButton);
		south.add(cancelButton);
		
		//add(north, BorderLayout.CENTER);
		
		//north.add(labelPanel, BorderLayout.LINE_START );
		//north.add(fieldPanel, BorderLayout.LINE_END );
		
		add(north, BorderLayout.CENTER);
		add(south, BorderLayout.PAGE_END);
		pack();
		setVisible(true);
		
		
	}
	
	
	
	
	
	protected void setConfigValues() 
	{
		config.setPlayfieldDimension( Integer.parseInt(widthField.getText()),  
									  Integer.parseInt(heightField.getText()) );
		
		config.setBackgroundColor(  colorButton.getBackground());
		
		config.setMaxZlifes(Integer.parseInt(MaxZlifesField.getText()));
		
		config.setMaxZretador(Integer.parseInt(MaxZretadorsField.getText()));
		
		config.setMaxSeeds(Integer.parseInt(MaxSeedsField.getText()));
		
	}





	private void addPanelSizeComponents()
	{
		JPanel subGroup = new JPanel();
		subGroup.setLayout(new FlowLayout(FlowLayout.LEFT));
		SimSizeLabel = new JLabel(" Simulation Panel Size");
		widthField = new JTextField(5);
		heightField= new JTextField(5);
		widthField.setText( String.valueOf( (int)config.getPlayfieldDimension().getWidth() ) );
		heightField.setText( String.valueOf((int) config.getPlayfieldDimension().getHeight()) );
		north.add(SimSizeLabel);
		subGroup.add(widthField);
		subGroup.add(heightField);
		
		north.add(subGroup);
	}
	
	
	private void addColorButton()
	{
		JPanel subGroup = new JPanel();
		subGroup.setLayout(new FlowLayout(FlowLayout.LEFT));
		backgroundcolorLabel = new JLabel(" Simulation Background Color");
		final Color panelColor = config.getBackgroundColor();
		colorButton = new JButton("");
		colorButton.setBackground(panelColor);
		
		colorButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent actionEvent)
			{
				 Color color = JColorChooser.showDialog(
				 rootPane, "Select Color",panelColor
				  );
				 if (color!= null)
				 {
					 colorButton.setBackground(color);
					 //config.setBackgroundColor(color );
				 }
			}
		
		});
		colorButton.setToolTipText(" Select Background Color");
		north.add(backgroundcolorLabel);
		subGroup.add(colorButton);
		north.add(subGroup);
		
	}
	
	private void addMaxSpritesComponent()
	{

		JPanel group1 = new JPanel();
		group1.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		JPanel group2 = new JPanel();
		group2.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		JPanel group3 = new JPanel();
		group3.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		MaxZlifesLabel = new JLabel(" Max Zlifes Number");
	    MaxZlifesField = new JTextField(5);
	    
	    MaxZretadorsLabel = new JLabel(" Max Zretadors Number");
	    MaxZretadorsField = new JTextField(5);
	    
	    MaxSeedsLabel = new JLabel(" Max Seeds Number");
	    MaxSeedsField = new JTextField(5);
	    
	    
	    MaxZlifesField.setText( String.valueOf( config.getMaxZlifes())); 
	    MaxZretadorsField.setText( String.valueOf(config.getMaxZretador() ) );
	    MaxSeedsField.setText( String.valueOf(config.getMaxSeeds() ) );
	    
	    north.add(MaxZlifesLabel);
	    group1.add(MaxZlifesField);
	    north.add(group1);
	    
	    north.add(MaxZretadorsLabel);
	    group2.add(MaxZretadorsField);
	    north.add(group2);
	    north.add(MaxSeedsLabel);
	    group3.add(MaxSeedsField);
	    north.add(group3);
	}
	
	

}
