/**
* @author Francesco di Dio
* Date: 29/nov/2010 15.24.07
* Titolo: JLifeLeftControlPanel.java
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

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;

import com.tabuto.j2dgf.gui.J2DControlPanel;


public class JLifeLeftControlPanel extends J2DControlPanel implements Observer {

	/**
	 * 
	 */
	private Simulation jlc;
	
	private static final long serialVersionUID = -8188812638425919153L;

	public JLifeLeftControlPanel(Dimension d)
	{
		super(d);
		this.setLayout(new FlowLayout());
		addContent();
	}
	
	protected void addContent()
	{
		 JButton AddCell = new JButton("New Zlife");
		 this.add(AddCell);
		 AddCell.addActionListener(new ActionListener()
			{
 			public void actionPerformed( ActionEvent action )
					{
 					jlc.addCell();
					}
			});
		 
		 
		 
		 JButton AddEat = new JButton("New Seed");
		 AddEat.addActionListener(new ActionListener()
			{
			public void actionPerformed( ActionEvent action )
					{
					jlc.addSeed();
					}
			});
		 this.add(AddEat);
	}
	
	public void setCanvasPanel(Simulation j)
	{
		this.jlc=j;
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Add update method
		
	}
	
	
	
}