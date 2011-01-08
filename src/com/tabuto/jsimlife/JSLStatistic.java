/**
* @author Francesco di Dio
* Date: 29/dic/2010 15.24.35
* Titolo: JSLStatistic.java
* Versione: 0.2.0 Rev.a:
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

package com.tabuto.jsimlife;

import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;
import java.util.TimerTask;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import com.tabuto.j2dgf.Group;




/**
 * This Class encapsulate JSimLife model class to set some game statistic
 * @author tabuto83
 *
 */
public class JSLStatistic extends TimerTask implements Observer {

	public JSimLife Game;
	private int GroupNumber;
	private Vector<Group<? extends Zlife>> List;
	
//PUBLIC STATISTICAL DATA
	
	public DefaultTableModel StatisticData = new DefaultTableModel();
	
	public String[] groupNames;
	
	public int[] groupSize;
	
	public int[] hornyZlifes;
	public int[] hungryZlifes;
	public int[] scaryZlifes;
	public int[] boredZlifes;
	//energia media del sistema;
	public double[] enthropy;
	public double[] averageSpeed;
	public double[] maxEnergy;
	public double[] minEnergy;
	public double[] averageRadius;
	
	double TotalEnergy=0;
	double TotalSpeed=0;
	double TotalRadius=0;
	
	private ResourceBundle resource;
	
	/**
	 * Constructor
	 * @param game
	 */
	public JSLStatistic(JSimLife game)
	{
		Game=game;
		GroupNumber = Game.groupList.size();
		List = Game.groupList;
		resource = ResourceBundle.getBundle("StringAndLabels", Game.getConfiguration().getLocale() );
		initArray();
		initTable();
	}
	
	/**
	 * Calculate some statistical Parameter and store it in some array.
	 */
	public void calculateStatistics()
	{
		
		double totalEnergy=0;
		double totalSpeed=0;
		double totalRadius=0;
		
	for(int groupID=0; groupID<List.size();groupID++)
	{
		groupNames[groupID] = List.get(groupID).getGroupName();
		
		groupSize[groupID] = List.get(groupID).size();
		
		hornyZlifes[groupID]=0;
		hungryZlifes[groupID]=0;
		scaryZlifes[groupID]=0;
		boredZlifes[groupID]=0;
		//energia media del sistema;
		enthropy[groupID]=0;
		averageSpeed[groupID]=0;
		maxEnergy[groupID]=0;
		minEnergy[groupID]=10000;
		averageRadius[groupID]=0;
		try{
			
		if(List.get(groupID).get(0)!= null)
		   maxEnergy[groupID]=List.get(groupID).get(0).getEnergy();
		}
		catch (IndexOutOfBoundsException e)
		{
			continue;
		}
		
		for(int i=0;i<List.get(groupID).size();i++)
		{
			switch(List.get(groupID).get(i).getState())
			{
				case BORED:
				{
					boredZlifes[groupID]++;
					break;
				}
				case SCARY:
				{
					scaryZlifes[groupID]++;
					break;
				}
				case HORNY:
				{
					hornyZlifes[groupID]++;
					break;
				}
				case HUNGRY:
				{
					hungryZlifes[groupID]++;
					break;
				}
			}
			totalEnergy+=List.get(groupID).get(i).getEnergy();
			totalSpeed+=List.get(groupID).get(i).getSpeed();
			totalRadius+=List.get(groupID).get(i).getRadius();
			
			//FInd zlife with max energy
			if( List.get(groupID).get(i).getEnergy()> maxEnergy[groupID])
				maxEnergy[groupID]=List.get(groupID).get(i).getEnergy();
			//Find Zlife with min energy
			if( List.get(groupID).get(i).getEnergy()< minEnergy[groupID])
				minEnergy[groupID]=List.get(groupID).get(i).getEnergy();
		
		
		
		enthropy[groupID]= (totalEnergy / List.get(groupID).size());
		averageSpeed[groupID] = (totalSpeed / List.get(groupID).size());
		averageRadius[groupID] = (totalRadius /  List.get(groupID).size());
		}
		totalEnergy=0;
		totalSpeed=0;
		totalRadius=0;
		//Da dividere sul numero di gruppi presenti
		TotalEnergy+=enthropy[groupID];
		TotalSpeed+=averageSpeed[groupID];
		TotalRadius+=averageRadius[groupID];
		
		StatisticData.setRowCount(GroupNumber);
		for (int j=0;j<List.size();j++)
		{
		    setGroupData(j);
		    //StatisticData.notifyAll();
		}
		
		
	}
 }
	
	protected void initArray()
	{
		
		groupNames = new String[GroupNumber];
		groupSize = new  int[GroupNumber];
		
		hornyZlifes = new  int[GroupNumber];
		hungryZlifes= new  int[GroupNumber];
		scaryZlifes= new  int[GroupNumber];
		boredZlifes= new  int[GroupNumber];
		//energia media del sistema;
		enthropy= new  double[GroupNumber];
		averageSpeed= new  double[GroupNumber];
		maxEnergy= new  double[GroupNumber];
		minEnergy= new  double[GroupNumber];
		averageRadius= new  double[GroupNumber];
	}
	
	protected void initTable()
	{
			StatisticData.addColumn("Name");
			StatisticData.addColumn("Total");
			StatisticData.addColumn("Horny");
			StatisticData.addColumn("Hungry");
			StatisticData.addColumn("Bored");
			StatisticData.addColumn("Scary");
			StatisticData.addColumn("Average Speed");
			StatisticData.addColumn("Average Radius");
			StatisticData.addColumn("Average Energy");
		
			StatisticData.setRowCount(GroupNumber);
			
			/*
			Integer[] data = new Integer[8];
			for(int i=0;i<8;i++)
				data[i]=0;
			
			for(int i=0;i<List.size();i++)
			StatisticData.addRow(data);
			*/
		
	}
	
	@Override
	public void run() {
		
		this.calculateStatistics();
	}


	/**
	 * Set the JLife Game2D component
	 * @param game
	 */
	public void setGame(JSimLife game)
	{
		Game = game;
		List = Game.groupList;
		GroupNumber = Game.groupList.size();
	}

	/**
	 * Return a String with all the Calculated Values
	 */
	public String toString()
	{
		//SHOW RESULT
		String result="\n"+ resource.getString( "stat_title" )+"\n";
		result+="\n"+ resource.getString( "stat_total" )+": "+ Game.getActualCellCount()+"\n\n\t" ;
		
		for(int i=0;i<GroupNumber;i++)
			result+= groupNames[i]+"\t";
		
		result+="\n"+ resource.getString( "stat_sub" )+"\t";
		for(int i=0;i<GroupNumber;i++)
			result+= groupSize[i] +"\t";
		
		result+="\n"+ resource.getString( "stat_horny" )  +":  \t";
		for(int i=0;i<GroupNumber;i++)
			result+= hornyZlifes[i] +"\t";
		
		result+="\n"+ resource.getString( "stat_hungry" )  +": \t";
		for(int i=0;i<GroupNumber;i++)
			result+= hungryZlifes[i] +"\t";
		
		result+="\n"+ resource.getString( "stat_scary" )  +":  \t";
		for(int i=0;i<GroupNumber;i++)
			result+= scaryZlifes[i] +"\t";
		
		result+="\n"+ resource.getString( "stat_bored" )  +":  \t";
		for(int i=0;i<GroupNumber;i++)
			result+= boredZlifes[i] +"\t";
	
		result+="\n"+ resource.getString( "stat_enthropy" )  +": \t";
		for(int i=0;i<GroupNumber;i++)
			result+= Math.rint(enthropy[i]*100)/100 +"\t";
		 
		
		result+="\n"+ resource.getString( "stat_speed" )  +":  \t";
		for(int i=0;i<GroupNumber;i++)
			result+= Math.rint(averageSpeed[i]*100)/100 +"\t";
		
		result+="\n"+ resource.getString( "stat_radius" )  +": \t";
		for(int i=0;i<GroupNumber;i++)
			result+= Math.rint(averageRadius[i]*100)/100 +"\t";
		
		return result;
	}
	
	
	protected String[] getGroupData(int groupID)
	{
		String[] output = new String[8];
		output[0]= String.valueOf(groupSize[groupID]);
		output[1]= String.valueOf(hornyZlifes[groupID]);
		output[2]= String.valueOf(hungryZlifes[groupID]);
		output[3]= String.valueOf(boredZlifes[groupID]);
		output[4]= String.valueOf(scaryZlifes[groupID]);
		output[5]= String.valueOf(averageSpeed[groupID]);
		output[6]= String.valueOf(averageRadius[groupID]);
		output[7]= String.valueOf(enthropy[groupID]);
		
		return output;
		
		
	}
	
	protected void setGroupData(int groupID)
	{	
		//StatisticData.setValueAt(aValue, row, column)
		StatisticData.setValueAt(groupNames[groupID], groupID, 0);
		StatisticData.setValueAt(groupSize[groupID], groupID, 1);
		StatisticData.setValueAt(hornyZlifes[groupID], groupID, 2);
		StatisticData.setValueAt(hungryZlifes[groupID], groupID, 3);
		StatisticData.setValueAt(boredZlifes[groupID], groupID, 4);
		StatisticData.setValueAt(scaryZlifes[groupID], groupID, 5);
		StatisticData.setValueAt(averageSpeed[groupID], groupID, 6);
		StatisticData.setValueAt(averageRadius[groupID], groupID, 7);
		StatisticData.setValueAt(enthropy[groupID], groupID, 8);		
	}
	
	protected void clearTableData()
	{
		for(int i=0;i<StatisticData.getRowCount();i++)
			StatisticData.removeRow(i);
	}
	
	public int getGroupNumber()
	{
		return GroupNumber;
	}
	
	/**
	 * This is an Observer class Observe the JLife Game2D Class. WHen JLife class
	 * change his "count" notify it to all its observer. JLifeStatistic simply recalculate
	 * its statistical data recalling the CalculateStatistic Methods
	 */
	@Override
	public void update(Observable o, Object arg) {
		
		String message = (String) arg;
		if(message.equalsIgnoreCase("CountChange"))
		{
			//DO SOMETHING
			calculateStatistics();
			StatisticData.fireTableDataChanged();
			
		}
		
	}
	
	
	
	
	
	
}
