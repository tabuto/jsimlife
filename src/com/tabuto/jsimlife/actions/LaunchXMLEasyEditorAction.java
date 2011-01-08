/**
* @author Francesco di Dio
* Date: 03/gen/2011 09.39.27
* Titolo: LaunchXMLEasyEditorAction.java
* Versione: 0.1 Rev.a:
*/


/*
 * Copyright (c) 2011 Francesco di Dio.
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
import com.tabuto.xmlMVC.XMLModel;


public class LaunchXMLEasyEditorAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	JSLMainView mainView;
	
	public LaunchXMLEasyEditorAction(JSLMainView mainview)
	{
		super();
		mainView = mainview;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		new XMLModel(mainView.getPreferences().getPath()+"/.");
	}

}
