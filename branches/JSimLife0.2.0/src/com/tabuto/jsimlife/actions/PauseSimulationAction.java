/**
* @author Francesco di Dio
* Date: 29/dic/2010 20.59.02
* Titolo: PauseSimulationAction.java
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

import com.tabuto.jsimlife.views.JSLMainView;


public class PauseSimulationAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JSLMainView root;
	
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
