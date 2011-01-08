/**
* @author Francesco di Dio
* Date: 29/dic/2010 23.23.05
* Titolo: JSLStatusBar.java
* Versione: 0.2.0 Rev.a:
*/


/*
 * Copyright (c) 2011 Francesco di Dio.
 * tabuto83@gmail.com 
 * 
 * Permission is hereby granted, free of charge, to any person
 * obtaining a copy of this software and associated documentation
 * files (the "Software"), to deal in the Software without
 * restriction, including without limitation the rights to use,
 * copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following
 * conditions:
 * 
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
 * OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 */

package com.tabuto.jsimlife.views;

import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

import com.tabuto.j2dgf.gui.J2DBottomPanel;
import com.tabuto.jsimlife.JSimLife;

/**
 * Simple Class extends J2DBottomPanel  uses to display several game information:
 * Actual Cell Number and Actual Game Status: PLAY or PAUSE. 
 * J2DBottomPanel auto-update its information when the observer class (JSimLife)
 * changes its state.
 * 
 * @author tabuto83
 * 
 * @see java.util.Observer
 * @see J2DBottomPanel
 * @see JSimLife
 *
 */
public class JSLStatusBar extends J2DBottomPanel implements Observer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	 static JSimLife Game;
	 private JTextField CountLabel, statusLabel;
	 private Border border;
	
	 /**
	  * Build a new instance of this class
	  * @param game JSimLife
	  */
	public JSLStatusBar(JSimLife game) {
		super(game.getDimension());
		Game = game;
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		addCountLabel();
		addStatusLabel();
		
	}

	@Override
	@Deprecated
	protected void addContent() {
		
		
		
	}
	
	/**
	 * Add a count label and a Count Text Field
	 */
	private void addCountLabel()
	{
		CountLabel = new JTextField(5);
		CountLabel.setToolTipText("Actual Cells Number");
		CountLabel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		CountLabel.setEditable(false);
		border = BorderFactory.createBevelBorder(BevelBorder.LOWERED ); 
		CountLabel.setBorder(border);
		CountLabel.setText(Integer.toString(0));
		add(CountLabel);
		
	}
	
	/**
	 * Add a status label
	 */
	private void addStatusLabel()
	{
		statusLabel = new JTextField(4);
		statusLabel.setToolTipText("Actual Simulation State");
		statusLabel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		statusLabel.setEditable(false);
		border = BorderFactory.createBevelBorder(BevelBorder.LOWERED ); 
		statusLabel.setBorder(border);
		statusLabel.setText("PLAY");
		add(statusLabel);
	}
	
	/*
	 * Set the actual cell count into the CountLabel
	 */
	private void setCellCount()
	{
		CountLabel.setText(  Integer.toString(Game.getActualCellCount() ) );
	}
	
	/**
	 * Set the Game that JSLStatusPanel observes. Use this to update the
	 * Game observed by this class when it changed. (For example when create a 
	 * new JSimLife game)
	 * @param game
	 */
	public void setGame(JSimLife game)
	{
		Game = game;
	}
	
	/**
	 * Set the Game status into GameStatus Field
	 * @param status
	 */
	private void setGameStatus(String status)
	{
		statusLabel.setText(status);
	}

	/**
	 * When Game changed its state, JSLBottomPanel update the information displayed
	 * 
	 * @param arg0 not used
	 * @param arg String contains information about observer changes
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable arg0, Object arg) 
	{
		
		if (arg instanceof String)
		{
			String message = (String) arg;
			
			if(message.equalsIgnoreCase("CountChange"))
				{ 
				setCellCount();
				}
			

			if(message.equalsIgnoreCase("activate"))
				{ 
				setGameStatus("PLAY");
				}
			
			if(message.equalsIgnoreCase("deactivate"))
			{ 
			setGameStatus("STOP");
			}
		}
	}

}
