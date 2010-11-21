/**
* @author Francesco di Dio
* Date: 20/nov/2010 15.29.36
* Titolo: JLifeBottomPanel.java
* Versione: 0.1.7 Rev.a:
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

import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;

import com.tabuto.j2dgf.gui.J2DBottomPanel;
import com.tabuto.jlife.JLife;


@SuppressWarnings("serial")
public class JLifeBottomPanel extends J2DBottomPanel implements Observer {
	
	public JLifeBottomPanel(Dimension d)
	{
		super(d);
		addContent();
	}

	protected void addContent()
	{
			JLabel StateBar = new JLabel();
			StateBar.setText("  JSimLife v.0.1.7_BETA by Tabuto83");
			this.add(StateBar);
	}
	

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Add update method
		
	}
}