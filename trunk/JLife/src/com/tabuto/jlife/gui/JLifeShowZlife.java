/**
* @author Francesco di Dio
* Date: 29/nov/2010 22.40.29
* Titolo: JLifeShowZlife.java
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
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.tabuto.j2dgf.Game2D;
import com.tabuto.jlife.JLife;


public class JLifeShowZlife extends JFrame implements Observer {

	public JLife Game;
	
	private JLabel nameLabel, positionLabel, directionLabel;
	private JLabel stateLabel, speedLabel, lifeCycleLabel;
	private JLabel energyLabel, radiusLabel, colorLabel;
	
	private JTextField nameField, positionField, directionField;
	private JTextField stateField, speedField, lifeCycleField;
	private JTextField energyField, radiusField;

	private JButton colorButton;
	
	JTextArea ZlifeDna = new JTextArea();
	JScrollPane ZlifeDnaScroll = new JScrollPane(ZlifeDna);
	
	private JPanel north,dnaPanel,south;
	
	public JLifeShowZlife(Game2D game)
	{
		super("Zlife's View");
		Game = (JLife)game;
		
		north = new JPanel();
		dnaPanel = new JPanel();
		south = new JPanel();
		
		setLayout( new BorderLayout());
		setPreferredSize(new Dimension(250,500));
		setResizable(false);
		initComponent();
		setAlwaysOnTop(true);
	}
	
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
	
		north.add(colorLabel);
		north.add(colorButton);
		
		//ADD DNAPANEL
		ZlifeDnaScroll.setPreferredSize(new Dimension(250, 250));
		dnaPanel.add(ZlifeDnaScroll);
		
		
		
		this.add(north,BorderLayout.NORTH);
		this.add(dnaPanel,BorderLayout.CENTER);
		this.add(south,BorderLayout.SOUTH);
	}
	

public void refresh()
{
	nameField.setText( Game.getSelectedCell().getName());
	positionField.setText("["+ (int)Game.getSelectedCell().getPosition().getX() +", "+ (int)Game.getSelectedCell().getPosition().getY() +"]"  );
	directionField.setText(""+ (int) Math.toDegrees( Game.getSelectedCell().getAngle()));
	stateField.setText( Game.getSelectedCell().getState().toString());
	lifeCycleField.setText(""+Game.getSelectedCell().getActualLifeCycle()+"/"+ Game.getSelectedCell().getLifeCycle());
	speedField.setText(""+Game.getSelectedCell().getSpeed());
	energyField.setText(""+Math.rint(Game.getSelectedCell().getEnergy()*100)/100+"/"+Math.rint(Game.getSelectedCell().getMaxEnergy()*100/100) );
	radiusField.setText( ""+Math.rint(Game.getSelectedCell().getRadius()*100)/100);
	
	colorButton.setBackground(new Color(Game.getSelectedCell().getR(),
										Game.getSelectedCell().getG(),
										Game.getSelectedCell().getB()));
	

	
}

private void checkVisibility()
{
	if(!this.isVisible())
	{
		this.pack();
		this.setVisible(true);
	}
}

private void  setDnaText()
{
	ZlifeDna.setText( 
			Game.getSelectedCell().getZlifeDna().toString());
ZlifeDna.setCaretPosition(0);
}

public void setGame(JLife game)
{
	Game = game;
	
}

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
					}
				}
			}
			if(message.equalsIgnoreCase("Zlife:Move"))
			{
				if(Game.getSelectedCell()!=null && this.isVisible())
				{
					refresh();
					
				}
			}
		}
			
	}
	
	

}
