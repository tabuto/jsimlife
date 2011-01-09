/**
* @author Francesco di Dio
* Date: 29/dic/2010 20.59.02
* Titolo: PauseSimulationAction.java
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

package com.tabuto.jsimlife.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import com.tabuto.jsimlife.JSimLife;
import com.tabuto.jsimlife.views.JSLMainView;

/**
 * Class extends AbstractAction to perform the following Action:
 * Stop the simulation drawing
 * 
 * @author tabuto83
 *
 * @see AbstractAction
 * @see JSLMainView
 */
public class PauseSimulationAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JSLMainView root;
	
	/**
	 * Instantiate new StepSimulationAction
	 * 
	 * @param title String Action title
	 * @param mainview JSLMainView
	 * @see JSimLife
	 */
	public PauseSimulationAction(String title,JSLMainView mainview)
	{
		super(title);
		root = mainview;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		root.stopSimulation();
		
	}

}
