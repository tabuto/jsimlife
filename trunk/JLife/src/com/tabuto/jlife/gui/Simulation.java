/**
* @author Francesco di Dio
* Date: 24/nov/2010 18.38.01
* Titolo: JLifeCanvas.java
* Versione: 0.1.8 Rev.a:
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


import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.swing.JButton;

import javax.swing.JColorChooser;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.JTextField;


import com.tabuto.j2dgf.gui.J2DCanvasPanel;

import com.tabuto.jlife.Cell;
import com.tabuto.jlife.JLife;
import com.tabuto.jlife.Seed;
import com.tabuto.jlife.Zlife;
import com.tabuto.util.Point;



public class Simulation extends J2DCanvasPanel implements Serializable,MouseListener{
	
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
	
	this.addMouseListener(new MouseAdapter() { 
	    public void mousePressed(MouseEvent me) { 
	      Game.selectCell(me.getX(),me.getY(),20);
	      //System.out.println("X: "+me.getX()+" Y: "+me.getY());
	      //System.out.println(Game.getSelectedCell().toString() );
	    } 
	  }); 
	
	}

public void initStuff()
{
	Game.initGame();
}

public void addCell()
{
	JFrameNewCell newCellFrame = new JFrameNewCell(Game);
	newCellFrame.pack();
	newCellFrame.setVisible(true);
}
     											
public void addSeed()
{
	final JFrame addSeedDialog = new JFrame("Create Seed");
	
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
	fieldX.setText( Integer.toString((int)(Math.random()* this.DIM.getWidth())) );
	fieldY.setText( Integer.toString((int)(Math.random()* this.DIM.getHeight())) );
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

@Override
public void mouseClicked(MouseEvent me) {
	// TODO Auto-generated method stub
	
}

@Override
public void mouseEntered(MouseEvent arg0) {
	// TODO Auto-generated method stub
	
}

@Override
public void mouseExited(MouseEvent arg0) {
	// TODO Auto-generated method stub
	
}

@Override
public void mousePressed(MouseEvent me) {
	// TODO Auto-generated method stub

}

@Override
public void mouseReleased(MouseEvent arg0) {
	// TODO Auto-generated method stub
	
}


}//END CLASS