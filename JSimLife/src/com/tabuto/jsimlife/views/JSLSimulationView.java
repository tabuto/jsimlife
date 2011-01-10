/**
* @author Francesco di Dio
* Date: 29/dic/2010 16.59.42
* Titolo: JSLSimulationView.java
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

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.Serializable;
import com.tabuto.j2dgf.gui.J2DCanvasPanel;
import com.tabuto.jsimlife.JSimLife;

/**
 * Class that extends J2DCanvasPanel in order to display the game sprites.
 * It implements a simple mouse listener to allow users select the Cells displayed.
 * This panel is added into JSLMainView Frame
 * 
 * @author tabuto83
 * 
 * @see J2DCanvasPanel
 * @see Sprite
 * @see Game2D
 * @see JSLMainView
 *	
 */
public class JSLSimulationView extends J2DCanvasPanel implements Serializable,MouseListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JSimLife Game;
	
	 /**
	  * Instantiate a new JSLSimulationView passing the game's Dimension and
	  * the Game2D to drawing
	  * @param d
	  * @param game
	  */
	 public JSLSimulationView(Dimension d, JSimLife game) 
	 {
		super(d);
		Game = game;
		
		//ADD a mouse listener to select cells or seeds
		this.addMouseListener(new MouseAdapter() { 
		    public void mousePressed(MouseEvent me) { 
		       ((JSimLife)Game).selectCell(me.getX(),me.getY(),20);
		       
		       Game.selectSeed(me.getX(), me.getY(), 20);
		    	        
		    } 
		  }); 
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
	
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		
		
	}
	 
	 
	 

	 
	
}
