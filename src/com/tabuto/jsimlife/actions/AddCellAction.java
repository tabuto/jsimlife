/**
* @author Francesco di Dio
* Date: 29/dic/2010 23.18.03
* Titolo: AddCellAction.java
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

import com.tabuto.jsimlife.JSimLife;
import com.tabuto.jsimlife.views.JSLNewCellView;

/**
 * Class extends AbstractAction to perform the following Action:
 * Open the JSLNeeCellView
 * 
 * @author tabuto83
 *
 * @see AbstractAction
 * @see JSLNewCellView
 */
public class AddCellAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JSimLife Game;
	
	/**
	 * Instanciate new AddCellAction
	 * @param game JSimLife game class
	 * @see JSimLife
	 */
	public AddCellAction(JSimLife game)
	{
		super();
		Game = game;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		JSLNewCellView newCellFrame = new JSLNewCellView(Game);
		newCellFrame.pack();
		newCellFrame.setVisible(true);
		
	}

	
	
}
