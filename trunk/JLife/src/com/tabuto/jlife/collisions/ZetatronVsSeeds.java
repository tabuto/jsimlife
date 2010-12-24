/**
* @author Francesco di Dio
* Date: 19/dic/2010 11.05.48
* Titolo: ZetatronVsSeeds.java
* Versione: 0.12.2 Rev.a:
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
import com.tabuto.jlife.Zetatron;


/**
 * Class extends CollisionDetector to perform a collisionAction between
 * Zetatron and seed. When a Zetatron eates seed, its energy level grow up to max.
 * 
 * @author tabuto83
 */
public class ZetatronVsSeeds extends CollisionDetector{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * COnstructor
	 * @param sp1 Zetatron Group
	 * @param sp2 Seed Group
	 */
	public ZetatronVsSeeds(Group<Zetatron> sp1, Group<Seed> sp2)
	 {
		 super(sp1,sp2);
		 //"Cell "see" Seeds upon 100px of distance"
		 this.setDistance(100);
	 }
	 //Override CollisionAction
	/**
	 * Collision Action to perform the eating collision action
	 */
	 public void CollisionAction(int s1, int s2)
	  {
		//Cast to class extends Sprite 
		Zetatron cell1;
		Seed seed2;
		cell1 = (Zetatron) group1.get(s1);
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
						//cell1.age();
						cell1.setEnergy(cell1.getMaxEnergy());
						cell1.setAngleRadians(Math.random()*2*Math.PI);
						//cell1.setHorny();
						cell1.live();
					}
				
				
		
	  }

}
