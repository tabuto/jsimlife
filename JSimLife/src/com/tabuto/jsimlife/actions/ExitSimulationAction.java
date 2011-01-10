/**
* @author Francesco di Dio
* Date: 29/dic/2010 21.10.14
* Titolo: ExitSimulationAction.java
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
import javax.swing.JOptionPane;
import com.tabuto.jsimlife.views.JSLMainView;


/**
 * Class extends AbstractAction to perform the following Action:
 * Quit the game
 * 
 * @author tabuto83
 *
 * @see AbstractAction
 * @see JSLMainView
 */
public class ExitSimulationAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JSLMainView root;
	
	/**
	 * Instantiate new ExitSimulationAction
	 * @param title String for Action's title
	 * @param mainvew JSLMainView class
	 * @see JSLMainView
	 */
	public ExitSimulationAction(String title, JSLMainView mainview)
	{
		super(title);
		root = mainview;
	}
	
	public void actionPerformed(ActionEvent e) {
		
		if (JOptionPane.showConfirmDialog(root,
    			root.resource.getString( "resetMsg" ),
   			 root.resource.getString( "resetTitle" ),
				 JOptionPane.YES_NO_CANCEL_OPTION,
				 JOptionPane.QUESTION_MESSAGE)==JOptionPane.YES_OPTION)
    			{
					root.stopSimulation();
    				root.setGame(null);
    				root.dispose();
    				System.exit(0);  
    			}
    	else
    	{};
		
	}

}
