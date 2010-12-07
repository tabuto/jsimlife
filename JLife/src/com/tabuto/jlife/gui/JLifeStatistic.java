/**
* @author Francesco di Dio
* Date: 02/Dic/2010 20.49.46
* Titolo: JLifeStatistic.java
* Versione: 0.1.11 Rev.a:
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

package com.tabuto.jlife.gui;

import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

import com.tabuto.j2dgf.Group;
import com.tabuto.jlife.JLife;
import com.tabuto.jlife.Zlife;

/**
 * Calculate some statistical value using the Vector Group List provides by JLife Class.
 * @deprecated
 */
public class JLifeStatistic implements Observer {

	public JLife Game;
	private final int GroupNumber=2;
	
	//STATISTICAL DATA
	
	String[] groupNames = new String[GroupNumber];
	
	int[] groupSize = new  int[GroupNumber];
	
	int[] hornyZlifes = new  int[GroupNumber];
	int[] hungryZlifes= new  int[GroupNumber];
	int[] scaryZlifes= new  int[GroupNumber];
	int[] boredZlifes= new  int[GroupNumber];
	//energia media del sistema;
	double[] enthropy= new  double[GroupNumber];
	double[] averageSpeed= new  double[GroupNumber];
	double[] maxEnergy= new  double[GroupNumber];
	double[] minEnergy= new  double[GroupNumber];
	double[] averageRadius= new  double[GroupNumber];
	
	double TotalEnergy=0;
	double TotalSpeed=0;
	double TotalRadius=0;
	
	
	public JLifeStatistic(JLife game)
	{
		Game = game;
	}
	
	/**
	 * Calculate some statistical Parameter and store it in some array.
	 * @param List
	 */
	public void calculateStatistics(Vector<Group<? extends Zlife>> List)
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
		
		
		
	}
 }
	
	/**
	 * This is an Observer class Observe the JLife Game2D Class. WHen JLife class
	 * change his "count" notify it to all its observer. JLifeStatistic simply recalculate
	 * its statistical data recalling the CalculateStatistic Methods
	 */
	@Override
	public void update(Observable arg0, Object arg1) {
		String message = (String) arg1;
		if(message.equalsIgnoreCase("CountChange"))
		{
			//DO SOMETHING
			calculateStatistics(Game.groupList);
			
		}
		
	}
	
	/**
	 * Return a String with all the Calculated Values
	 */
	public String toString()
	{
		//SHOW RESULT
		String result="\n STATISTICAL DATA \n";
		result+="\n Total elements: "+ Game.getActualCellCount()+"\n\n\t" ;
		
		for(int i=0;i<GroupNumber;i++)
			result+= groupNames[i]+"\t";
		
		result+="\n Subtotal: \t";
		for(int i=0;i<GroupNumber;i++)
			result+= groupSize[i] +"\t";
		
		result+="\n Horny: \t";
		for(int i=0;i<GroupNumber;i++)
			result+= hornyZlifes[i] +"\t";
		
		result+="\n Hungry: \t";
		for(int i=0;i<GroupNumber;i++)
			result+= hungryZlifes[i] +"\t";
		
		result+="\n Scary: \t";
		for(int i=0;i<GroupNumber;i++)
			result+= scaryZlifes[i] +"\t";
		
		result+="\n Bored: \t";
		for(int i=0;i<GroupNumber;i++)
			result+= boredZlifes[i] +"\t";
	
		result+="\n Enthropy: \t";
		for(int i=0;i<GroupNumber;i++)
			result+= Math.rint(enthropy[i]*100)/100 +"\t";
		 
		
		result+="\n Speed: \t";
		for(int i=0;i<GroupNumber;i++)
			result+= Math.rint(averageSpeed[i]*100)/100 +"\t";
		
		result+="\n Radius: \t";
		for(int i=0;i<GroupNumber;i++)
			result+= Math.rint(averageRadius[i]*100)/100 +"\t";
		
		return result;
	}

	public void setGame(JLife game)
	{
		Game = game;
	}

}
