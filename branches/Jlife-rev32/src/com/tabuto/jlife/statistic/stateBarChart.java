/**
* @author Francesco di Dio
* Date: 05/dic/2010 20.48.12
* Titolo: stateBarChart.java
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

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;

import javax.swing.JPanel;
import javax.xml.bind.PropertyException;

import org.jCharts.chartData.ChartDataException;
import org.jCharts.chartData.PieChartDataSet;
import org.jCharts.nonAxisChart.PieChart2D;
import org.jCharts.properties.ChartProperties;
import org.jCharts.properties.LegendProperties;
import org.jCharts.properties.PieChart2DProperties;


public class stateBarChart extends JPanel{
	
	private JPanel panel;
	Statistic statistic;
	Font axisFont = new Font("Helvetica", Font.PLAIN,  10);
	
	String[] labelName={"Horny","Hungry","Scary","Bored"};
	
	Color hornyColor=new Color(255,125,0);
	Color hungryColor = new Color(0,125,255);
	Color scaryColor = new Color(125,255,0);
	Color boredColor = new Color(0,255,125);
	
	Paint[] paints = {hornyColor, hungryColor, scaryColor,boredColor};

	PieChart2DProperties pieChart2DProperties;
	LegendProperties legendProperties;
	ChartProperties chartProperties;
	
	public stateBarChart(Statistic s) throws ChartDataException, PropertyException

	{
		super();
		statistic = s;
		this.panel=new JPanel( true );
		this.panel.setSize( 500, 500 );
		this.add( this.panel );
		this.setVisible( true );
	}
	
	public void initComponent() throws ChartDataException,PropertyException
	{
		this.pieChart2DProperties = new PieChart2DProperties();
	    this.legendProperties= new LegendProperties();
	    this.chartProperties= new ChartProperties();


	}

	
	public void draw()
	{

		 statistic.calculateStatistics();
         int GroupNumber = statistic.GroupNumber;
         double[] DATA = new double[4];
         
         
         int[] horny  = new  int[GroupNumber];
         int[] hungry = new  int[GroupNumber];
         int[] scary  = new  int[GroupNumber];
         int[] bored  = new  int[GroupNumber];
         
         PieChart2D pieChart2D;
	
         //DATA
         	//for(int j=0;j<GroupNumber;j++)
         		{
         			//FILL DATA
         			DATA[0]=horny[0];
         			DATA[1]=hungry[0];
         			DATA[2]=scary[0];
         			DATA[3]=bored[0];
         			
         			try {
						PieChartDataSet pieChartDataSet = new PieChartDataSet( statistic.groupNames[0], DATA,
						        labelName, paints, this.pieChart2DProperties );
						
						  Dimension dimension= this.getSize();
					      pieChart2D = new PieChart2D( pieChartDataSet,
					                                              this.legendProperties,
					                                              this.chartProperties,
					                                              (int) dimension.getWidth(),
					                                              (int) (dimension.getHeight() ));

					      
					      pieChart2D.setGraphics2D( (Graphics2D) this.panel.getGraphics());
					      pieChart2D.render();

					  
					} catch (ChartDataException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					
					} catch (org.jCharts.properties.PropertyException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

         			
         		}
			
         
					
	}

}
