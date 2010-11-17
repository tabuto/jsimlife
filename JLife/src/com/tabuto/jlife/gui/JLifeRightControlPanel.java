/**
* @author Francesco di Dio
* Date: 17/nov/2010 15.26.42
* Titolo: JLifeRightControlPanel.java
* Versione: 0.1.4 Rev.a:
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

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.tabuto.j2dgf.gui.J2DControlPanel;
import com.tabuto.jlife.JLife;


public class JLifeRightControlPanel extends J2DControlPanel{
	
public JLife game;
	/**
	 * 
	 */
	private static final long serialVersionUID = 8686747746289847490L;

	 JButton Count = new JButton("Count");
	 JLabel CellCount = new JLabel("CellCount");
	 JTextField CellCountField = new JTextField(4);
	
	
	public JLifeRightControlPanel(Dimension d, JLife game)
	{
		super(d);
		this.setLayout(new FlowLayout());
		addContent();
		this.game=game;
	}

	protected void addContent()
	{
		
		 this.add(Count);
		 Count.addActionListener(new ActionListener()
			{
	 			public void actionPerformed( ActionEvent action )
						{
	 					   setCellCount();
						}
				});
		 
		 
		 JButton Right = new JButton("Right");
		 this.add(Right);
		 
		 
		 this.add(CellCount);
		 
		
		 this.add(CellCountField);
		 CellCountField.setEditable(false);
		 
	}
	
	private void setCellCount()
	{
		CellCountField.setText(  Integer.toString(game.getActualCellCount() ) );
	}
	
	public void setGame(JLife game)
	{
		this.game = game;
	}
}