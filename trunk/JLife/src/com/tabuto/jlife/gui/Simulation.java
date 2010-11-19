/**
* @author Francesco di Dio
* Date: 07/nov/2010 18.38.01
* Titolo: JLifeCanvas.java
* Versione: 0.1.5 Rev.a:
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
  * Questa Classe Estende J2DCanvasPAnel e rappresenta il livello CONTROL 
  * del MODEL (JLife)
  */

package com.tabuto.jlife.gui;


import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.JTextField;


import com.tabuto.j2dgf.gui.J2DCanvasPanel;

import com.tabuto.jlife.Cell;
import com.tabuto.jlife.JLife;
import com.tabuto.jlife.Seed;
import com.tabuto.jlife.Zlife;



public class Simulation extends J2DCanvasPanel implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3243620944955104850L;

	
	public JLife Game;
	
public Simulation(int width, int height)
	{
	
	super(width, height);
	Game = new JLife(this.DIM);
	Game.setName("NewSimulation");
	}

public void initStuff()
{
	Game.initGame();
}


public void addCell()
	{
		final JFrame addCellDialog = new JFrame("Create Cell");
		addCellDialog.setLayout( new FlowLayout());
		JLabel labelX, labelY,labelName,labelRadius;
		final JTextField fieldX;
		final JTextField fieldY, fieldName,fieldRadius;
		JButton buttonOk, buttonCancel;
		
		labelX = new JLabel("X Coordinate");
		labelY= new JLabel("Y Coordinate");
		labelName = new JLabel("Name");
		labelRadius = new JLabel("Radius");
		fieldX = new JTextField(4);
		fieldY = new JTextField(4);
		fieldRadius = new JTextField(4);
		fieldName = new JTextField(15);
		buttonOk = new JButton("OK");
		buttonCancel = new JButton("Cancel");
		addCellDialog.add(labelName);
		addCellDialog.add(fieldName);
		addCellDialog.add(labelRadius);
		addCellDialog.add(fieldRadius);
		addCellDialog.add(labelX);
		addCellDialog.add(fieldX);
		addCellDialog.add(labelY);
		addCellDialog.add(fieldY);
		addCellDialog.add(buttonOk);
		addCellDialog.add(buttonCancel);
		buttonOk.addActionListener(new ActionListener()
				   {
					public void actionPerformed(ActionEvent actionEvent) 
						{
						Zlife newCell = new Zlife( DIM, 
													Integer.valueOf(fieldX.getText() ) ,
													Integer.valueOf(fieldY.getText()),
													Integer.valueOf(fieldRadius.getText()));
						newCell.setAngleRadians(Math.random()*2*Math.PI);
						newCell.setSpeed(100);
						Game.addCell(newCell);
						addCellDialog.dispose();
						} 
					});
	
		buttonCancel.addActionListener(new ActionListener()
		   {
			public void actionPerformed(ActionEvent actionEvent) 
				{
				addCellDialog.dispose();
				} 
			});
		
		addCellDialog.pack();
		addCellDialog.setVisible(true);
	
	}




public void addSeed()
{
	final JFrame addSeedDialog = new JFrame("Create Seed");
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
					Game.addSeed(newSeed);
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




protected void drawSprite(Graphics g)
{

				this.Game.drawSprite(g);

}

public void saveGame()
{
	 FileOutputStream fos = null;
    ObjectOutputStream out = null;
	 try
	 {
		 fos = new FileOutputStream(Game.getName()+".jsl");
		 out = new ObjectOutputStream(fos);
		 out.writeObject(this.Game);
		 out.close();
		 Game.setSaved(true);
	 }
	  catch(IOException ex)
	  		{
		  		ex.printStackTrace();
			}
}

public void loadGame(String path)
{
	JLife loaded = null;
	FileInputStream fis = null;
    ObjectInputStream in = null;
	try
	 {
	   fis = new FileInputStream(path);
       in = new ObjectInputStream(fis);
	   loaded = (JLife)in.readObject();
       in.close();
       if(loaded instanceof JLife)
    	   this.Game = loaded;
     }
     catch(IOException ex)
     {
	     ex.printStackTrace();
     }
     catch(ClassNotFoundException ex)
     {
     ex.printStackTrace();
     }
}




}