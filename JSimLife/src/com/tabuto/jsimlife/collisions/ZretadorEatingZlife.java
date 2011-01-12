/**
* @author Francesco di Dio
* Date: 27/dic/2010 10.19.59
* Titolo: ZretadorEatingZlife.java
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
import com.tabuto.jsimlife.Zlife;
import com.tabuto.jsimlife.Zretador;

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

public class ZretadorEatingZlife extends CollisionDetector {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7053313800065136619L;
	
	public ZretadorEatingZlife(Group<Zretador> sp1, Group<Zlife> sp2)
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
	
		z = (Zretador) group1.get(s1);	
		c = (Zlife) group2.get(s2);		
			
		z.ZretadorEatingZlife(z, c);
	  }
	
}

