/**
* @author Francesco di Dio
* Date: 29/dic/2010 16.15.05
* Titolo: RefreshStatisticAction.java
* Versione: 0.1 Rev.a:
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

import com.tabuto.jsimlife.JSLStatistic;


public class RefreshStatisticAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JSLStatistic Statistic;
	
	public RefreshStatisticAction(String title, JSLStatistic s)
	{
		super(title);
		Statistic = s;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
	
		Statistic.calculateStatistics();
		
	}

}
