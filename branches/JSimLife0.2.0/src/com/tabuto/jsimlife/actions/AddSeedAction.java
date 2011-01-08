/**
* @author Francesco di Dio
* Date: 29/dic/2010 16.20.16
* Titolo: AddSeedAction.java
* Versione: 0.2.0 Rev.a:
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

package com.tabuto.jsimlife.actions;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.tabuto.jsimlife.Seed;
import com.tabuto.jsimlife.JSimLife;

/**
 * Class extends AbstractAction to perform the following Action:
 * Open a Dialog to add some food onto simulation
 * 
 * @author tabuto83
 *
 * @see AbstractAction
 * @see Seed
 * @see JSLGame
 */
public class AddSeedAction extends AbstractAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JSimLife Game;
	/**
	 * Instanciate new AddSeedAction
	 * @param title String for Action's title
	 * @param game JSimLife game class
	 * @see JSimLife
	 */
	public AddSeedAction(String title,JSimLife game)
	{
		super(title);
		Game = game;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		final JFrame addSeedDialog = new JFrame("Create Seed");
		final Dimension DIM = Game.getDimension();
		 //ADD ICON TITLE
	    addSeedDialog.setIconImage(java.awt.Toolkit.getDefaultToolkit().getImage
	    		(this.getClass().getResource("../icons/icon_alpha_48x48.gif")));
		addSeedDialog.setPreferredSize(new Dimension(160, 180));
		addSeedDialog.setResizable(false);
		addSeedDialog.setAlwaysOnTop(true);
		addSeedDialog.setLocation(10, 330);
		addSeedDialog.setLayout( new FlowLayout());
		JLabel labelX, labelY,labelName,labelRadius;
		final JTextField fieldX;
		final JTextField fieldY, fieldName,fieldRadius;
		JButton buttonOk, buttonCancel;
		
		labelX = new JLabel("X Coordinate");
		labelY= new JLabel("Y Coordinate");
		labelName = new JLabel("Density");
		labelRadius = new JLabel("Radius");
		fieldX = new JTextField(4);
		fieldY = new JTextField(4);
		fieldRadius = new JTextField(4);
		fieldName = new JTextField(4);
		buttonOk = new JButton("OK");
		buttonCancel = new JButton("Cancel");
		fieldX.setText( Integer.toString((int)(Math.random()* DIM.getWidth())) );
		fieldY.setText( Integer.toString((int)(Math.random()* DIM.getHeight())) );
		fieldRadius.setText("50");
		fieldName.setText("50");
		addSeedDialog.add(labelName);
		addSeedDialog.add(fieldName);
		addSeedDialog.add(labelRadius);
		addSeedDialog.add(fieldRadius);
		addSeedDialog.add(labelX);
		addSeedDialog.add(fieldX);
		addSeedDialog.add(labelY);
		addSeedDialog.add(fieldY);
		addSeedDialog.add(buttonOk);
		addSeedDialog.add(buttonCancel);
		buttonOk.addActionListener(new ActionListener()
				   {
					public void actionPerformed(ActionEvent actionEvent) 
						{
						Seed newSeed = new Seed( DIM, 
													Integer.valueOf(fieldX.getText() ) ,
													Integer.valueOf(fieldY.getText()),
													Integer.valueOf(fieldRadius.getText()),
													(double)Integer.valueOf(fieldName.getText())
													);
						((JSimLife)Game).addSeed(newSeed);
						addSeedDialog.dispose();
						} 
					});

		buttonCancel.addActionListener(new ActionListener()
		   {
			public void actionPerformed(ActionEvent actionEvent) 
				{
				addSeedDialog.dispose();
				} 
			});
		
		addSeedDialog.pack();
		addSeedDialog.setVisible(true);
		
	}

}
