/**
* @author Francesco di Dio
* Date: 06/dic/2010 13.14.38
* Titolo: GroupSizeDataCollector.java
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

package com.tabuto.jlife.statistic;

import info.monitorenter.gui.chart.ITrace2D;
import info.monitorenter.gui.chart.ITracePoint2D;
import info.monitorenter.gui.chart.TracePoint2D;
import info.monitorenter.gui.chart.io.ADataCollector;


public class GroupSizeDataCollector extends ADataCollector {
public Statistic statistic;
public int groupNumber;
	
	public GroupSizeDataCollector(ITrace2D trace, long latency, Statistic s,int GroupNumber) {
		super(trace, latency);
		statistic = s;
		groupNumber = GroupNumber;
		
	}

	@Override
	public ITracePoint2D collectData() {
		
		double currentSize=0.0;
		statistic.calculateStatistics();
		currentSize = statistic.groupSize[groupNumber];

	    return new TracePoint2D(System.currentTimeMillis(), currentSize);
	}

}
