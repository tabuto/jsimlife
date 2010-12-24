/**
* @author Francesco di Dio
* Date: 29/nov/2010 17.32.16
* Titolo: RiproductionCollision.java
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

package com.tabuto.jlife.collisions;

import com.tabuto.j2dgf.Group;
import com.tabuto.j2dgf.collision.CollisionDetector;
import com.tabuto.jenetic.Dna;
import com.tabuto.jlife.JLife;
import com.tabuto.jlife.Zlife;


/**
 * Class extends CollisionDetector to perform a collisionAction between
 * Zlifes Group's elements. When an Horny Zlifes meets another one, born a new ZLife
 * with a merged DNA of its parent.
 * 
 * @see Dna
 * @see Gene
 * 
 * @author tabuto83
 *
 */
public class RiproductionCollision extends CollisionDetector{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1171546078549060228L;
	public JLife jlc;
	//Constructor
	/**
	 * Constructor
	 * @param sp1 Zlifes Group
	 */
	 public RiproductionCollision(Group<Zlife> sp1, JLife j)
	 {
		 super(sp1);
		 jlc=j;
	 }
	 
	 
	 //Override CollisionAction
	 /**
	  * Collision Action to perform the reproduction action
	   */
	 public void CollisionAction(int s1, int s2)
	  {
		//Cast to class extends Sprite 
		Zlife cell1, cell2, newCell;
		cell1 = (Zlife) group1.get(s1);
		cell2 = (Zlife) group1.get(s2);
		Dna newBornDna;
		//TO-DO
		
		if (cell1.isHorny() && cell2.isHorny() && (jlc.getActualCellCount()<jlc.getMaxCellsNumber()))
				{
				newBornDna= Dna.merge(cell1.getZlifeDna(), cell2.getZlifeDna());
				
				cell1.age();
				cell2.age();
				//cell1.setBored();
				//cell2.setBored();
				double tempAngle = cell2.getAngle();
				cell2.setAngleRadians(cell1.getAngle());
				cell1.setAngleRadians(tempAngle);
				newCell = new Zlife(cell1.getDimension(), cell1.getX(),cell1.getY(),newBornDna);
				//newCell.setBored();
				newCell.setAngleRadians( Math.random() * 2 * Math.PI );
				newCell.setName( combineName(cell1.getName(),cell2.getName()));
				newCell.setEnergy(20); 
				jlc.addCell(newCell);
				cell1.setEnergy( cell1.getEnergy() - cell1.getRiproductionEnergy());
				cell2.setEnergy(cell2.getEnergy() - cell2.getRiproductionEnergy());
				newCell.live();
				cell1.live();
				cell2.live();
				
				}
		
		else
			//IF SCARY CHANGE DIRECTION
		{
			if(cell1.isScary() || cell2.isScary())
			{
			cell1.setAngleRadians(Math.random()*Math.PI*2);
			cell2.setAngleRadians(Math.random()*Math.PI*2);
			}
		}
		
	  }
		
	 /**
	  * Combine two name and return a String composed through the two name
	  * @param name1 String name of the first Sprite
	  * @param name2 String name of the second Sprite
	  * @return
	  */
	 private String combineName(String name1, String name2)
	 {
		 String name ="";
		 int l = (int)((name1.length() + name2.length())/2);
		 for (int i=0;i<l;i++)
			 
			 if((i%2)==0)
				 if(i<name1.length())
					 name+= name1.charAt(i);
				 else
					 name+= name2.charAt(i);
			 else
				 if(i<name2.length())
					 name+= name2.charAt(i);
				 else
					 name+= name1.charAt(i);
		 
		 return name;
	 }

}