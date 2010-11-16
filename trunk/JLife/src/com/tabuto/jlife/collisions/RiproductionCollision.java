/**
* @author Francesco di Dio
* Date: 15/nov/2010 17.32.16
* Titolo: RiproductionCollision.java
* Versione: 0.1.2 Rev.a:
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


public class RiproductionCollision extends CollisionDetector{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1171546078549060228L;
	public JLife jlc;
	//Constructor
	 public RiproductionCollision(Group<Cell> sp1, JLife j)
	 {
		 super(sp1);
		 jlc=j;
	 }
	 //Override CollisionAction
	 public void CollisionAction(int s1, int s2)
	  {
		//Cast to class extends Sprite 
		Cell cell1, cell2, newCell;
		cell1 = (Cell) group1.get(s1);
		cell2 = (Cell) group1.get(s2);
		Dna newBornDna;
		//TO-DO
		
		if (cell1.isRiproduction() && cell1.isRiproduction())
				{
				newBornDna= cell1.getDna().merge( cell2.getDna());
				cell1.setRiproduction(false);
				cell2.setRiproduction(false);
				cell1.age(); cell2.age();
				newCell = new Cell(cell1.getX(),cell2.getY(),newBornDna);
				jlc.addCell(newCell);
				
				}
		
	  }
		

}
