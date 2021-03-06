/**
* @author Francesco di Dio
* Date: 27/dic/2010 11.05.48
* Titolo: ZetatronVsSeeds.java
* Versione: 0.2.0 Rev.a:
*/


/*
 * Copyright (c) 2011 Francesco di Dio.
 * tabuto83@gmail.com 
 * 
 * Permission is hereby granted, free of charge, to any person
 * obtaining a copy of this software and associated documentation
 * files (the "Software"), to deal in the Software without
 * restriction, including without limitation the rights to use,
 * copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following
 * conditions:
 * 
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
 * OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 */

package com.tabuto.jsimlife.collisions;

import com.tabuto.j2dgf.Group;
import com.tabuto.j2dgf.collision.CollisionDetector;
import com.tabuto.jsimlife.Seed;
import com.tabuto.jsimlife.Zetatron;


/**
 * Class extends CollisionDetector to perform a collisionAction between
 * Zetatron and seed. When a Zetatron eates seed, its energy level grow up to max.
 * 
 * @author tabuto83
 * @see CollisionDetector
 */
public class ZetatronEatingSeed extends CollisionDetector{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Constructor
	 * @param sp1 Zetatron Group
	 * @param sp2 Seed Group
	 */
	public ZetatronEatingSeed(Group<Zetatron> sp1, Group<Seed> sp2)
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
				
		cell1.ZetatronEatingSeed(cell1, seed2);
	  }
}
