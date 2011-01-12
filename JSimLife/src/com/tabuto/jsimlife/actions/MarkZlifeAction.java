/**
* @author Francesco di Dio
* Date: 11/gen/2011 16.49.08
* Titolo: MarkZlifeAction.java
* Versione: 0.2.0.b3 Rev.a:
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

package com.tabuto.jsimlife.actions;

import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;

import com.tabuto.jsimlife.JSimLife;
import com.tabuto.jsimlife.Zlife;

/**
 * Class extends AbstractAction to perform the following Action:
 * Mark the selected Zlife
 * 
 * @author tabuto83
 *
 * @see AbstractAction
 * @see JSimLife
 * @see Zlife#marked()
 * @see Zlife#marked(Color)
 */
public class MarkZlifeAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	JSimLife Game;
	Zlife zlifeToMark;
	Color markColor;
	JButton colorButton;
	
	/**
	 * Instantiate new MarkZlifeAction
	 * @param game JSimLife game to catch selected Zlife
	 * @param c Color of the marked
	 */
	public MarkZlifeAction(JSimLife game, Color c)
	{
		super();
		Game = game;
		//zlifeToMark = z;
		markColor = c;
	}
	
	/**
	 * 	
	 * Instantiate new MarkZlifeAction
	 * @param game JSimLife game to catch selected Zlife
	 */
	public MarkZlifeAction(JSimLife game)
	{
		super();
		Game = game;
		markColor = Color.green;
	}
	
	/**
	 * Instantiate new MarkZlifeAction taking color from a JButton
	 * @param game JSimLife game to catch selected Zlife
	 * @param button JButton where take the marker color
	 */
	public MarkZlifeAction(JSimLife game, JButton button)
	{
		super();
		Game = game;
		colorButton = button;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		if(colorButton != null)
			markColor = colorButton.getBackground();
			
		
		
		if(Game.getSelectedCell().isMarked())
			Game.getSelectedCell().unmarked();
		else
		Game.getSelectedCell().marked(markColor);
		//zlifeToMark.marked(markColor);
		
	}

}
