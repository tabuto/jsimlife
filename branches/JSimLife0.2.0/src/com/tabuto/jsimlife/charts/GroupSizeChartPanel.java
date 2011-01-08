/**
* @author Francesco di Dio
* Date: 27/dic/2010 14.08.02
* Titolo: GroupSizeChartPanel.java
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
 * Shows a dynamic chart shows current Zlifes Group size.
 * Using the JChart2D library.
 * 
 * @author tabuto83
 * 
 * @see Chart2D
 * @see ITrace2D
 */
public class GroupSizeChartPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	JSLStatistic GameStatistic;
	
	ADataCollector collector1;
	Vector<ADataCollector> collection = new Vector<ADataCollector>();
	Chart2D chart;
	Paint[] colors = {Color.RED,Color.BLUE,Color.ORANGE, Color.YELLOW ,Color.GREEN,Color.MAGENTA,Color.CYAN ,Color.PINK,Color.LIGHT_GRAY };
	
	public GroupSizeChartPanel(JSLStatistic s)
	{
		super();
		GameStatistic = s;
		chart = new Chart2D();
		chart.setBackground(Color.black);
		chart.setForeground(Color.white);
		initChart();
	}
	
	
	protected void initChart()
	{
	    // Create an ITrace: 
	// Note that dynamic charts need limited amount of values!!! 
		
		for(int i=0;i<GameStatistic.getGroupNumber();i++)
		{
		// Note that charts need limited amount of values!!! 
	    ITrace2D trace1 = new Trace2DLtd(500); 
	    trace1.setColor((Color)colors[i]);
	    trace1.setName(GameStatistic.groupNames[i] );
	    //Every second a new Data is collected!
	    collector1 = new GroupSizeDataCollector(trace1, 2000,GameStatistic,i);
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
	
	/**
	 * Class extends ADataCollector, it add point obtains from JSLStatistic and add
	 * it in a Point Collector called TracePoint2D
	 * 
	 * @author tabuto83
	 * 
	 * @see Chart2D
	 * @see TracePoint2D
	 * @see JSLStatistic
	 */
	public class GroupSizeDataCollector extends ADataCollector 
	{
		double initTime = System.currentTimeMillis();
		public JSLStatistic statistic;
		public int groupNumber;
			
		/**
		 * Collector Constructor
		 * @param trace ITrace2D data
		 * @param latency Thread sleep time
		 * @param s Game Statistic 
		 * @param GroupNumber Number of Groups present into the Game
		 */
			public GroupSizeDataCollector(ITrace2D trace, long latency, JSLStatistic s,int GroupNumber) {
				super(trace, latency);
				statistic = s;
				groupNumber = GroupNumber;
				
			}

			@Override
			public ITracePoint2D collectData() {
				
				double currentSize=0.0;
				statistic.calculateStatistics();
				currentSize = statistic.groupSize[groupNumber];
				
				
			    return new TracePoint2D(System.currentTimeMillis()-initTime, currentSize);
			}
	}
	
}
