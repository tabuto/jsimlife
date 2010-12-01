/**
* @author Francesco di Dio
* Date: 29/nov/2010 20.49.46
* Titolo: JLifeStatistic.java
* Versione: 0.1.9 Rev.a:
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

import com.tabuto.j2dgf.Group;
import com.tabuto.j2dgf.Sprite;
import com.tabuto.jlife.JLife;
import com.tabuto.jlife.Zlife;


public class JLifeStatistic implements Observer {

	public JLife Game;
	
	//STATISTICAL DATA
	int hornyZlifes=0;
	int hungryZlifes=0;
	int scaryZlifes=0;
	int boredZlifes=0;
	//energia media del sistema;
	double enthropy=0;
	double averageSpeed=0;
	double maxEnergy=0;
	double minEnergy=10000;
	double averageRadius=0;
	
	//TODO: improve Statistical flexibility
	public JLifeStatistic(JLife game)
	{
		Game = game;
	}
	
	public void calculateStatistics(Group<? extends Zlife> group)
	{
		double totalEnergy=0;
		double totalSpeed=0;
		double totalRadius=0;

		hornyZlifes=0;
		hungryZlifes=0;
		scaryZlifes=0;
		boredZlifes=0;
		//energia media del sistema;
		enthropy=0;
		averageSpeed=0;
		maxEnergy=0;
		minEnergy=10000;
		averageRadius=0;
		if(group.get(0)!= null)
		   maxEnergy=group.get(0).getEnergy();
		
		for(int i=0;i<group.size();i++)
		{
			switch(group.get(i).getState())
			{
				case BORED:
				{
					boredZlifes++;
					break;
				}
				case SCARY:
				{
					scaryZlifes++;
					break;
				}
				case HORNY:
				{
					hornyZlifes++;
					break;
				}
				case HUNGRY:
				{
					hungryZlifes++;
					break;
				}
			}
			totalEnergy+=group.get(i).getEnergy();
			totalSpeed+=group.get(i).getSpeed();
			totalRadius+=group.get(i).getRadius();
			
			//FInd zlife with max energy
			if( group.get(i).getEnergy()> maxEnergy)
				maxEnergy=group.get(i).getEnergy();
			//Find Zlife with min energy
			if( group.get(i).getEnergy()< minEnergy)
				minEnergy=group.get(i).getEnergy();
		}
		
		
		enthropy= (totalEnergy / group.size());
		averageSpeed = totalSpeed / group.size();
		averageRadius = totalRadius /  group.size();
	}
	
	
	@Override
	public void update(Observable arg0, Object arg1) {
		String message = (String) arg1;
		if(message.equalsIgnoreCase("CountChange"))
		{
			//DO SOMETHING
			calculateStatistics(Game.cellsGroup);
			
		}
		
	}
	
	public String toString()
	{
		return "\n Statistics:  \t"+
				"\n\n Total Zlife's:  \t" + Game.getActualCellCount() +
				"\n Horny Zlifes:  \t"  + hornyZlifes +
				"\n Hungry Zlifes: \t" + hungryZlifes +
				"\n Scary ZLifes:  \t"  + scaryZlifes +
				"\n Bored Zlifes:  \t"  + boredZlifes +
				"\n Energy Average: \t" + Math.rint(enthropy*100)/100 +
				"\n Speed Average: \t" + Math.rint(averageSpeed*100)/100 +
				"\n Radius Average: \t" + Math.rint(averageRadius*100)/100 +
				"\n Zlife with Max Energy: \t" + Math.rint(maxEnergy*100)/100 +
				"\n Zlifes with Min Energy: \t" + Math.rint(minEnergy*100)/100;
	}

	public void setGame(JLife game)
	{
		this.Game = game;
	}

}
