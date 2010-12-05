/**
* @author Francesco di Dio
* Date: 29/nov/2010 17.31.59
* Titolo: EatingCollision.java
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

package com.tabuto.jlife.collisions;

import com.tabuto.j2dgf.Group;
import com.tabuto.j2dgf.collision.CollisionDetector;
import com.tabuto.jlife.Seed;
import com.tabuto.jlife.Zlife;


public class EatingCollision extends CollisionDetector{
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = -6009905497615581521L;
	
	public EatingCollision(Group<Zlife> sp1, Group<Seed> sp2)
	 {
		 super(sp1,sp2);
		 //"Cell "see" Seeds upon 100px of distance"
		 this.setDistance(100);
	 }
	 //Override CollisionAction
	 public void CollisionAction(int s1, int s2)
	  {
		//Cast to class extends Sprite 
		Zlife cell1;
		Seed seed2;
		cell1 = (Zlife) group1.get(s1);
		seed2 = (Seed) group2.get(s2);
		
		//TO-DO
		
		//if (cell1.isAlive() && cell1.isHungry())
		//if (cell1.isAlive() )
				
					if (cell1.isBored())
					{
						cell1.setSeedPosition( seed2.getPosition() ) ;
					}
				
					if (cell1.isHungry())
					{
						seed2.eatMe();
						cell1.age();
						cell1.setEnergy(cell1.getMaxEnergy());
						cell1.setAngleRadians(Math.random()*2*Math.PI);
						//cell1.setHorny();
						cell1.live();
					}
				
				
		
	  }


}
