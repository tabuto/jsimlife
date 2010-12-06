/**
* @author Francesco di Dio
* Date: 05/dic/2010 16.59.47
* Titolo: JFrameStatistic.java
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

import info.monitorenter.gui.chart.Chart2D;
import info.monitorenter.gui.chart.ITrace2D;
import info.monitorenter.gui.chart.io.ADataCollector;
import info.monitorenter.gui.chart.traces.Trace2DLtd;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.image.BufferedImage;
import java.awt.Image;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.xml.bind.PropertyException;

import org.jCharts.chartData.ChartDataException;
import org.jCharts.chartData.PieChartDataSet;
import org.jCharts.nonAxisChart.PieChart2D;
import org.jCharts.properties.ChartProperties;
import org.jCharts.properties.LegendProperties;
import org.jCharts.properties.PieChart2DProperties;

import com.tabuto.jlife.JLife;



public class JFrameStatistic extends JFrame{

	private stateBarChart graphPanel;
	private  JPanel panel;
	Statistic GameStatistic;

	/**
	 * 
	 */
	private static final long serialVersionUID = -7506591100511422140L;

	public JFrameStatistic(Statistic s) 
	{
		super("Statistics");
		GameStatistic = s;
		setPreferredSize(new Dimension(500,500));
		this.setSize( 500, 500 );
		this.panel=new JPanel( true );
		this.panel.setSize(400,400);
		this.getContentPane().add( this.panel );
		this.setVisible( true );
		setIgnoreRepaint(true);
		panel.setIgnoreRepaint(true);
		
		//this.setPreferredSize(new Dimension(600,600));
		try {
			initComponent();
			
		} catch (PropertyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ChartDataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (org.jCharts.properties.PropertyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	

		

    }
	
		
	public void initComponent() throws ChartDataException, PropertyException, org.jCharts.properties.PropertyException
	{
		
		
		drawChart2();
		//repaint();
	}
	
	
	private void drawChart() throws ChartDataException, PropertyException, org.jCharts.properties.PropertyException
	{
		 GameStatistic.calculateStatistics();
         int GroupNumber = GameStatistic.GroupNumber;
         double[] DATA = new double[4];
         
     	String[] labelName={"Horny","Hungry","Scary","Bored"};
    	
    	Color hornyColor=new Color(255,125,0);
    	Color hungryColor = new Color(0,125,255);
    	Color scaryColor = new Color(125,255,0);
    	Color boredColor = new Color(0,255,125);
    	
    	Paint[] paints = {hornyColor, hungryColor, scaryColor,boredColor};
         
         
         int[] horny  = new  int[GroupNumber];
         int[] hungry = new  int[GroupNumber];
         int[] scary  = new  int[GroupNumber];
         int[] bored  = new  int[GroupNumber];
         
         PieChart2D pieChart2D;
	
         //DATA
         	//for(int j=0;j<GroupNumber;j++)
         			//FILL DATA
         			DATA[0]=(double) GameStatistic.hornyZlifes[0];
         			DATA[1]=(double) GameStatistic.hungryZlifes[0];
         			DATA[2]=(double) GameStatistic.scaryZlifes[0];
         			DATA[3]=(double) GameStatistic.boredZlifes[0];
         			
         			DATA[0]=23d;
         			DATA[1]=33d;
         			DATA[2]=29d;
         			DATA[3]=51d;
         				
         				PieChart2DProperties pieChart2DProperties=new PieChart2DProperties();
						PieChartDataSet pieChartDataSet = new PieChartDataSet( "Zlifes", DATA,
						        labelName, paints, pieChart2DProperties );
						
						  Dimension dimension= this.getSize();
					      pieChart2D = new PieChart2D( pieChartDataSet,
					                                              new LegendProperties(),
					                                              new ChartProperties(),
					                                             350,
					                                              350);

					      
					      pieChart2D.setGraphics2D( (Graphics2D) this.panel.getGraphics());
					      pieChart2D.render();

					  
	}
		
	
		
	public void drawChart2()
	{
		 Chart2D chart = new Chart2D();
		    // Create an ITrace: 
		// Note that dynamic charts need limited amount of values!!! 
		    ITrace2D trace1 = new Trace2DLtd(200); 
		    trace1.setColor(Color.RED);
		    
		    ITrace2D trace2 = new Trace2DLtd(200); 
		    trace1.setColor(Color.BLUE);
		    
		    chart.addTrace(trace1);
		    chart.addTrace(trace2);
		    
		    getContentPane().add(chart);

		    
		   ADataCollector collector1 = new GroupSizeDataCollector(trace1, 100,GameStatistic,0);
		   ADataCollector collector2 = new GroupSizeDataCollector(trace2, 100,GameStatistic,1);

		   collector1.start();
		   collector2.start();


		    
		    

	}


}
