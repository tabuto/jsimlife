/**
* @author Francesco di Dio
* Date: 27/dic/2010 19.38.03
* Titolo: StatesChartPanel.java
* Versione: 0.1.13.1 Rev.a:
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

package com.tabuto.jsimlife.charts;

import info.monitorenter.gui.chart.Chart2D;
import info.monitorenter.gui.chart.ITrace2D;
import info.monitorenter.gui.chart.ITracePoint2D;
import info.monitorenter.gui.chart.TracePoint2D;
import info.monitorenter.gui.chart.io.ADataCollector;
import info.monitorenter.gui.chart.traces.Trace2DLtd;

import java.awt.Color;
import java.awt.Paint;
import java.util.Vector;

import com.tabuto.jsimlife.JSLStatistic;


/**
 * A dynamic chart shows current Zlifes states as a number 
 * of Horny, Hungry, bored and scary zlifes
 * 
 * N.B. NOT USED cause a dinamyc chart is not useful for this infomration type.
 * Better use the Statistic Panel at JLifeRigtControlPanel
 * @author tabuto83
 *
 */
public class StatesChartPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	JSLStatistic GameStatistic;
	
	ADataCollector collector1;
	Vector<ADataCollector> collection = new Vector<ADataCollector>();
	Chart2D chart;
	Paint[] colors = {Color.RED,Color.BLUE,Color.ORANGE, Color.YELLOW ,Color.GREEN,Color.MAGENTA,Color.CYAN ,Color.PINK,Color.LIGHT_GRAY };
	public StatesChartPanel(JSLStatistic s)
	{
		super();
		GameStatistic = s;
		chart = new Chart2D();
		chart.setBackground(Color.black);
		chart.setForeground(Color.white);
		initChart();
	}
	
	/**
	 * Create a new Chart2D with statistical data provides by the Statistic class.
	 */
	protected void initChart()
	{
	    // Create an ITrace: 
	// Note that dynamic charts need limited amount of values!!! 
		
		for(int i=0;i<GameStatistic.getGroupNumber();i++)
		{
	    ITrace2D trace1 = new Trace2DLtd(500); 
	    trace1.setColor((Color)colors[i]);
	    trace1.setName(GameStatistic.groupNames[i] );
	    collector1 = new StatesDataCollector(trace1, 2000,GameStatistic,i);
	    collection.add(collector1);
	    chart.addTrace(trace1);
	    collector1.start();
		}

	}
	
	
	/**
	 * Start the Chart thread
	 */
	public void start()
	{
			for(int i=0;i<collection.size();i++)
			{
				if(!collection.get(i).isRunning())
					collection.get(i).start();
			}
	}
	
	/**
	 * Stop the Chart thread
	 */
	public void stop()
	{
		for(int i=0;i<collection.size();i++)
		{
			if(!collection.get(i).isRunning())
				collection.get(i).start();
		}
	}

	/**
	 * @return the Groups Average Speed Chart2D
	 */
	public Chart2D getChart()
	{
		return chart;
	}
	
	/**
	 * A collector of Averages Radius catches in Statistic class
	 * @author user
	 *
	 */
	public class StatesDataCollector extends ADataCollector 
	{
		public JSLStatistic statistic;
		public int groupNumber;
		double initTime = System.currentTimeMillis();
		/**
		 * Collector Constructor
		 * @param trace ITrace2D data
		 * @param latency Thread sleep time
		 * @param s Game Statistic 
		 * @param GroupNumber Number of Groups present into the Game
		 */
			public StatesDataCollector(ITrace2D trace, long latency, JSLStatistic s,int GroupNumber) {
				super(trace, latency);
				statistic = s;
				groupNumber = GroupNumber;
				
			}

			/**
			 * How this collector builds Data's points
			 */
			@Override
			public ITracePoint2D collectData() {
				
				double currentRadius=0.0;
				statistic.calculateStatistics();
				currentRadius = statistic.enthropy[groupNumber];

			    return new TracePoint2D(System.currentTimeMillis()-initTime, currentRadius);
			}
	}
	
}
