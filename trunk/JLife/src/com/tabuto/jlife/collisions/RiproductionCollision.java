/**
* @author Francesco di Dio
* Date: 17/nov/2010 17.32.16
* Titolo: RiproductionCollision.java
* Versione: 0.1.5 Rev.a:
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
import com.tabuto.jlife.Cell;
import com.tabuto.jlife.JLife;
import com.tabuto.jlife.Zlife;


public class RiproductionCollision extends CollisionDetector{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1171546078549060228L;
	public JLife jlc;
	//Constructor
	 public RiproductionCollision(Group<Zlife> sp1, JLife j)
	 {
		 super(sp1);
		 jlc=j;
	 }
	 //Override CollisionAction
	 public void CollisionAction(int s1, int s2)
	  {
		//Cast to class extends Sprite 
		Zlife cell1, cell2, newCell;
		cell1 = (Zlife) group1.get(s1);
		cell2 = (Zlife) group1.get(s2);
		Dna newBornDna;
		//TO-DO
		
		if (cell1.isHorny() && cell2.isHorny())
				{
				newBornDna= Dna.merge(cell1.getZlifeDna(), cell2.getZlifeDna());
				cell1.setEnergy( cell1.getEnergy() - cell1.getRiproductionEnergy());
				cell2.setEnergy(cell2.getEnergy() - cell2.getRiproductionEnergy());
				//cell1.age();
				//cell2.age();
				cell1.setBored();
				cell2.setBored();
				double tempAngle = cell2.getAngle();
				cell2.setAngleRadians(cell1.getAngle());
				cell1.setAngleRadians(tempAngle);
				
				newCell = new Zlife(cell1.getDimension(), cell1.getX(),cell1.getY(),newBornDna);
				//newCell.setBored();
				newCell.setAngleRadians( Math.random() * 2 * Math.PI );
				newCell.setSpeed( (int) newCell.getBoredSpeed());
				jlc.addCell(newCell);
				cell1.move();
				cell2.move();
				
				}
		
	  }
		

}