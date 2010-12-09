/**
* @author Francesco di Dio
* Date: 09/dic/2010 15.29.36
* Titolo: JLifeBottomPanel.java
* Versione: 0.1.12.1 Rev.a:
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

import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

import com.tabuto.j2dgf.gui.J2DBottomPanel;
import com.tabuto.jlife.JLife;

/**
 * Simple Class to show a bottom panel showing game status values
 */
@SuppressWarnings("serial")
public class JLifeBottomPanel extends J2DBottomPanel implements Observer {

	 static JLife game;
	 private JTextField CountLabel;
	 private Border border;
	
	/*
	 * The constructor takes a Dimension and a Game parameter to know its size
	 * and to show the Game values
	 */
	public JLifeBottomPanel( Dimension d, JLife game)
	{
		super(d);
		this.game = game;
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		addCountLabel();
	}

	@Deprecated
	protected void addContent()
	{
			JLabel StateBar = new JLabel();
			StateBar.setText("  JSimLife v.0.1.11_BETA by Tabuto83");
			this.add(StateBar);
	}
	

	/*
	 * Add a count label
	 */
	private void addCountLabel()
	{
		CountLabel = new JTextField(4);
		CountLabel.setToolTipText("Actual Cells Number");
		CountLabel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		CountLabel.setEditable(false);
		border = BorderFactory.createBevelBorder(BevelBorder.LOWERED ); 
		CountLabel.setBorder(border);
		CountLabel.setText(Integer.toString(0));
		add(CountLabel);
	}
	
	public void setGame(JLife g)
	{
		game = g;
	}
	
	/*
	 * Set the actual cell count into the CountLabel
	 */
	private void setCellCount()
	{
		CountLabel.setText(  Integer.toString(game.getActualCellCount() ) );
	}
	
	/*
	 * JLifeBottomPanel is an observer of Game, when JLife(Game2d) change his count,
	 * JLIfeBottomPanel update the actual cell count
	 * (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable arg0, Object arg) {
	

		if (arg instanceof String)
		{
			String message = (String) arg;
			if(message.equalsIgnoreCase("CountChange"))
				{ 
				setCellCount();
				}
		}
		
	}
}
