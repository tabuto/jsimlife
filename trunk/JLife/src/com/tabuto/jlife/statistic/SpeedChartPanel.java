/**
* @author Francesco di Dio
* Date: 06/dic/2010 19.22.34
* Titolo: SpeedChartPanel.java
* Versione: 0.10.3 Rev.a:
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

import info.monitorenter.gui.chart.Chart2D;
import info.monitorenter.gui.chart.ITrace2D;
import info.monitorenter.gui.chart.ITracePoint2D;
import info.monitorenter.gui.chart.TracePoint2D;
import info.monitorenter.gui.chart.io.ADataCollector;
import info.monitorenter.gui.chart.traces.Trace2DLtd;

import java.awt.Color;
import java.awt.Paint;
import java.util.Vector;


/**
 * A dynamic chart shows current Zlife's Groups average speed values.
 * @author tabuto83
 *
 */
public class SpeedChartPanel {

	private static final long serialVersionUID = 1L;

	Statistic GameStatistic;
	
	ADataCollector collector1;
	Vector<ADataCollector> collection = new Vector<ADataCollector>();
	Chart2D chart;
	Paint[] colors = {Color.RED,Color.BLUE,Color.ORANGE, Color.YELLOW ,Color.GREEN,Color.MAGENTA,Color.CYAN ,Color.PINK,Color.LIGHT_GRAY };
	public SpeedChartPanel(Statistic s)
	{
		super();
		GameStatistic = s;
		chart = new Chart2D();
		initChart();
	}
	
	
	protected void initChart()
	{
	    // Create an ITrace: 
	// Note that dynamic charts need limited amount of values!!! 
		
		for(int i=0;i<GameStatistic.GroupNumber;i++)
		{
	    ITrace2D trace1 = new Trace2DLtd(500); 
	    trace1.setColor((Color)colors[i]);
	    trace1.setName(GameStatistic.groupNames[i] );
	    collector1 = new SpeedDataCollector(trace1, 1000,GameStatistic,i);
	    collection.add(collector1);
	    chart.addTrace(trace1);
	    collector1.start();
		}

	}
	
	public void start()
	{
			for(int i=0;i<collection.size();i++)
			{
				if(!collection.get(i).isRunning())
					collection.get(i).start();
			}
	}
	
	public void stop()
	{
		for(int i=0;i<collection.size();i++)
		{
			if(!collection.get(i).isRunning())
				collection.get(i).start();
		}
	}
	
	public Chart2D getChart()
	{
		return chart;
	}
	
	public class SpeedDataCollector extends ADataCollector 
	{
		public Statistic statistic;
		public int groupNumber;
			
			public SpeedDataCollector(ITrace2D trace, long latency, Statistic s,int GroupNumber) {
				super(trace, latency);
				statistic = s;
				groupNumber = GroupNumber;
				
			}

			@Override
			public ITracePoint2D collectData() {
				
				double averageSpeed=0.0;
				statistic.calculateStatistics();
				averageSpeed = statistic.averageSpeed[groupNumber];

			    return new TracePoint2D(System.currentTimeMillis(), averageSpeed);
			}
	}
}
