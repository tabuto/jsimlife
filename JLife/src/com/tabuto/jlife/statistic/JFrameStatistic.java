/**
* @author Francesco di Dio
* Date: 06/dic/2010 16.59.47
* Titolo: JFrameStatistic.java
* Versione: 0.1.10.3 Rev.a:
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


import info.monitorenter.gui.chart.views.ChartPanel;

import java.awt.BorderLayout;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;



/**
 * A JFRame to show, using a JComboBox, several dynamic chart type 
 * shows Statistic information
 * 
 * @author tabuto83
 * 
 * @version 0.1.10.3
 *
 */
public class JFrameStatistic extends JFrame implements ActionListener{

	JComboBox chartChooser;
	
	private JPanel north;
	private ChartPanel panelChart;
	private  GroupSizeChartPanel gscp;
	
	
	Statistic GameStatistic;

	 Container c;
	/**
	 * 
	 */
	private static final long serialVersionUID = -7506591100511422140L;

	public JFrameStatistic(Statistic s) 
	{
		super("Statistics");
		GameStatistic = s;
	
		c = this.getContentPane();
		c.setLayout(new BorderLayout());
		
		north=new JPanel( true );
		
		//Create the default chart to set on
		gscp = new GroupSizeChartPanel(GameStatistic);
		
		addChartChooser();
		
		setPreferredSize(new Dimension(500,500));
		this.setSize( 300, 200 );
	    //Set the JFrame Icon
		this.setIconImage(java.awt.Toolkit.getDefaultToolkit().getImage
	        		(this.getClass().getResource("icon_alpha_48x48.gif")));
		
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
								//"Groups States", //NOT USED!!
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
	 * Set the current correct chart
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
