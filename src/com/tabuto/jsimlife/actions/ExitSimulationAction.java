/**
* @author Francesco di Dio
* Date: 29/dic/2010 21.10.14
* Titolo: ExitSimulationAction.java
* Versione: 0.2.0 Rev.a:
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
	 * Instanciate new ExitSimulationAction
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
    				root.setGame(null);
    				root.dispose();
    				System.exit(0);  
    			}
    	else
    	{};
		
	}

}
