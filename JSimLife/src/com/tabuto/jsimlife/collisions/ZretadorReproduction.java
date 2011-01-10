/**
* @author Francesco di Dio
* Date: 27/dic/2010 10.58.29
* Titolo: ZretadorReproduction.java
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
import com.tabuto.jenetic.Dna;
import com.tabuto.jsimlife.JSimLife;
import com.tabuto.jsimlife.Zretador;

/**
 * Class extends CollisionDetector to perform a collisionAction between
 * Zredator Group's elements. When an Horny Zlifes meets another one, born a new ZLife
 * with a merged DNA of its parent.
 * 
 * @see Dna
 * @see Gene
 * 
 * @author tabuto83
 *
 */
public class ZretadorReproduction extends CollisionDetector{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1171546078549060228L;
	public JSimLife jlc;
	//Constructor
	/**
	 * Constructor
	 * @param sp1 Zredator Group
	 * @param j JSimLife game
	 */
	 public ZretadorReproduction(Group<Zretador> sp1, JSimLife j)
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
		Zretador cell1, cell2, newCell;
		cell1 = (Zretador) group1.get(s1);
		cell2 = (Zretador) group1.get(s2);
		Dna newBornDna;
		//TO-DO
		
		if (cell1.isHorny() && cell2.isHorny() && (jlc.getActualCellCount()<jlc.getMaxCellsNumber()))
				{
				newBornDna= Dna.merge(cell1.getZlifeDna(), cell2.getZlifeDna());
				
			
				double tempAngle = cell2.getAngle();
				cell2.setAngleRadians(cell1.getAngle());
				cell1.setAngleRadians(tempAngle);
				newCell = new Zretador(cell1.getDimension(), cell1.getX(),cell1.getY(),newBornDna);
				//newCell.setBored();
				newCell.setAngleRadians( Math.random() * 2 * Math.PI );
				newCell.setName( combineName(cell1.getName(),cell2.getName()));
				newCell.setEnergy(newCell.getHungryEnergy()*0.9); 
				jlc.addZretador(newCell);
				
				cell1.setEnergy( cell1.getEnergy() - cell1.getRiproductionEnergy());
				cell2.setEnergy(cell2.getEnergy() - cell2.getRiproductionEnergy());
				newCell.setGenerationNumber( Math.max(cell1.getGenerationNumber(), cell2.getGenerationNumber())+1);
				newCell.live();
				cell1.live();
				cell2.live();
				
				}
		
		
	  }
		
	 /**
	  * Combine two name and return a String composed through the two name
	  * @param name1 String name of the first Sprite
	  * @param name2 String name of the second Sprite
	  * @return String combined names
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

