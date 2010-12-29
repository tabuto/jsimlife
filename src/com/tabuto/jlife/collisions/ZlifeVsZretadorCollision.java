/**
* @author Francesco di Dio
* Date: 27/dic/2010 10.19.59
* Titolo: ZlifeVsZretadorCollision.java
* Versione: 0.1.13.1 Rev.a:
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
import com.tabuto.jlife.Zlife;
import com.tabuto.jlife.Zretador;

/**
 * Class extends CollisionDetector to perform a collisionAction between
 * Zlifes and Zredator Group's elements. 
 * When an Hungry Zredator is near (<100 pixel) a Zlife, it hunt that ZLife.
 * It following ZLifes until is next to it, then eat it.
 * 
 * 
 * @author tabuto83
 *
 */

public class ZlifeVsZretadorCollision extends CollisionDetector {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7053313800065136619L;
	
	public ZlifeVsZretadorCollision(Group<Zlife> sp1, Group<Zretador> sp2)
	 {
		 super(sp1,sp2);
		 //"Cell "see" Seeds upon 100px of distance"
		 this.setDistance(100);
	 }
	 //Override CollisionAction
	 public void CollisionAction(int s1, int s2)
	  {
		//Cast to class extends Sprite 
		Zlife c;
		Zretador z;
		//if( group1.get(s1) instanceof Zlife)
			c = (Zlife) group1.get(s1);
		
		//if( group2.get(s2) instanceof Zretador)
			z = (Zretador) group2.get(s2);
		
		
		if(z.isHungry())
		{
			if( z.getPosition().getDistance( c.getPosition())<20)
			{
				//Zretador eat ZLife
				z.setEnergy( z.getMaxEnergy() );
				z.setAngleRadians(Math.random()*2*Math.PI);
				z.preda=null;
				if(z.getEnergy()>z.getHungryEnergy())
					z.setBored();
				z.live();
				
				//Zlife hit but not necessary die
				c.setEnergy( c.getEnergy()-( (z.getRadius()+1)*4) );
				return;
			}
			
			if(z.preda==null)
			{
				c.setScary();
				z.preda=c;
				z.moveTo(z.preda);
				z.live();
			}
		}
		
		
	  }
	
}
