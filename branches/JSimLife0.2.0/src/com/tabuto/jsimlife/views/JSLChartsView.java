/**
* @author Francesco di Dio
* Date: 03/gen/2011 09.51.56
* Titolo: JSLChartsView.java
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

package com.tabuto.jsimlife.views;

import info.monitorenter.gui.chart.views.ChartPanel;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import com.tabuto.jsimlife.JSLStatistic;
import com.tabuto.jsimlife.charts.EnergyChartPanel;
import com.tabuto.jsimlife.charts.GroupSizeChartPanel;
import com.tabuto.jsimlife.charts.RadiusChartPanel;
import com.tabuto.jsimlife.charts.SpeedChartPanel;

/**
 * Simple frame show a combo box allows users chooses a single chart to view.
 * 
 * @author tabuto83
 * 
 * @see EnergyChartPanel
 * @see GroupSizeChartPanel
 * @see RadiusChartPanel
 * @see SpeedChartPanel
 *
 */
public class JSLChartsView  extends JFrame implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	JComboBox chartChooser;
	
	private JPanel north;
	private ChartPanel panelChart;
	private  com.tabuto.jsimlife.charts.GroupSizeChartPanel gscp;
	
	
	JSLStatistic GameStatistic;

	 Container c;
	
	public JSLChartsView(JSLStatistic s)
	{
		super("Statistics");
		GameStatistic = s;
	
		setAlwaysOnTop(true);
		
		c = this.getContentPane();
		c.setLayout(new BorderLayout());
		
		north=new JPanel( true );
		
		//Create the default chart to set on
		gscp = new GroupSizeChartPanel(GameStatistic);
		
		addChartChooser();
		
		setPreferredSize(new Dimension(500,500));
		this.setSize( 400, 300 );
	    //Set the JFrame Icon
		this.setIconImage(java.awt.Toolkit.getDefaultToolkit().getImage
	        		(this.getClass().getResource("../icons/icon_alpha_48x48.gif")));
		
		this.setVisible( true );

		//ADD panel and chart
		c.add(north,BorderLayout.NORTH);
		panelChart = new ChartPanel(gscp.getChart());
		c.add( panelChart);
	}
	
	/**
	 * Add a JComboBox chartChooser to the north panel of this JFrame
	 */
	private void addChartChooser()
	{
		final String[] choose ={
								"Group Numbers",
								"System Energy",
								"Average Radius",
								"Average Speed"
								//"Groups States", //NOT YET USED!!
								//TODO: add groups states chart
								};
		chartChooser = new JComboBox(choose);
		chartChooser.setSelectedIndex(0);
		chartChooser.addActionListener(this);

		north.add(chartChooser);
	}
	
	/**
	 * The ActionPerformed by this ActionListener
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		chartChooser = (JComboBox)e.getSource();
        String choosedChart = (String)chartChooser.getSelectedItem();
        updateChartPanel(choosedChart);
	}


	/**
	 * Set the current chart to show
	 * @param choosedChart String passed by the chartChooser JComboBox
	 */
	private void updateChartPanel(String choosedChart) {
		
		
		if (choosedChart.equalsIgnoreCase("System Energy"))
		{
			EnergyChartPanel ecp = new EnergyChartPanel(GameStatistic);
			c.remove(panelChart);
				panelChart = new ChartPanel(ecp.getChart());
			
		}
		
		if (choosedChart.equalsIgnoreCase("Group Numbers"))
		{
			GroupSizeChartPanel gscp = new GroupSizeChartPanel(GameStatistic);
			c.remove(panelChart);
				panelChart = new ChartPanel(gscp.getChart());
				//c.add(panelChart);
		}
		
		if (choosedChart.equalsIgnoreCase("Average Speed"))
		{
			SpeedChartPanel scp = new SpeedChartPanel(GameStatistic);
			c.remove(panelChart);
				panelChart = new ChartPanel(scp.getChart());
				//c.add(panelChart);
		}
		
		if (choosedChart.equalsIgnoreCase("Average Radius"))
		{
			RadiusChartPanel scp = new RadiusChartPanel(GameStatistic);
			c.remove(panelChart);
				panelChart = new ChartPanel(scp.getChart());
				//c.add(panelChart);
		}
		
		c.add(panelChart);
		c.validate();
	}


}
