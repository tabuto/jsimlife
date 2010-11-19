/**
* @author Francesco di Dio
* Date: 17/nov/2010 13.32.45
* Titolo: Test.java
* Versione: 0.1 Rev.a:
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

package com.tabuto.jlife;

import java.awt.Dimension;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.tabuto.j2dgf.Group;
import com.tabuto.j2dgf.collision.CollisionBoundDetector;
import com.tabuto.jenetic.Dna;


public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {


		Dimension dim = new Dimension(1024,768);
		
		boolean ACTIVE1 = false;
		double metabolism1 = 0.002;
		double Speed1 = 20.0;
		int energy1 = 100;
		
		boolean ACTIVE2 = false;
		double metabolism2 = 0.001;
		double Speed2 = 20.0;
		int energy2 = 50;
		
		Dna dna1 = new Dna();
		 dna1.add(ACTIVE1,"isALIVE","Cell's Live Status");
		 dna1.add(metabolism1,"metabolism","Cell's metabolism");
		 dna1.add(Speed1,"SPEED","Cell's Speed");
		 dna1.add(energy1,"ENERGY","Cell's ENERGY level");
		 dna1.setName("Dna1");
		 dna1.setParam(0.5);
		 
			Dna dna2 = new Dna();
			 dna2.add(ACTIVE2,"isALIVE","Cell's Live Status");
			 dna2.add(metabolism2,"metabolism","Cell's metabolism");
			 dna2.add(Speed2,"SPEED","Cell's Speed");
			 dna2.add(energy2,"ENERGY","Cell's ENERGY level");
			 dna2.setName("Dna2");
			 dna2.setParam(0.5);
			 
			Dna newBorn = Dna.merge(dna1, dna2);
			
		
		Cell c1 = new Cell(dim,100,100,10);
		Cell c2 = new Cell(dim,100,100,20);
		Dna newBornCell = Dna.merge(c1.getDna(), c2.getDna());
		//Cell c3 = new Cell(c1.getX(),c1.getY(), Dna.merge(c1.getDna(), c2.getDna()));
		//c1.setAngleRadians(Math.PI/5);
		//Cell c2 = new Cell(dim,200,200,10);
		//c2.setAngleRadians(Math.PI/7);
		//Group<Cell> testGroup = new Group<Cell>();
		//testGroup.add(c1);
		//testGroup.add(c2);
		
		//CollisionBoundDetector cbd = new CollisionBoundDetector(testGroup,dim);
		
		//Cell newBorn = new Cell(123,123, c1.getDna().merge(c2.getDna()));
		//newBorn.setAngleRadians(Math.PI / 13);
		
		String output="DNA1"+ dna1.toString() 
					+"\n\nDNA2"+ dna2.toString() +"\n\nNewBorn:"+ newBorn.toString();
		
		System.out.println(output );
		
		/*
		 FileOutputStream fos = null;
	     ObjectOutputStream out = null;
		 try
		 {
			 fos = new FileOutputStream("CollisionBoundD"+".cbd");
			 out = new ObjectOutputStream(fos);
			 out.writeObject(cbd);
			 out.close();
		 }
		  catch(IOException ex)
		  		{
			  		ex.printStackTrace();
	 			}

		  
			CollisionBoundDetector loaded = null;
			FileInputStream fis = null;
		    ObjectInputStream in = null;
			try
			 {
			   fis = new FileInputStream("CollisionBoundD"+".cbd");
		       in = new ObjectInputStream(fis);
			   loaded = (CollisionBoundDetector)in.readObject();
		       in.close();
		     }
		     catch(IOException ex)
		     {
			     ex.printStackTrace();
		     }
		     catch(ClassNotFoundException ex)
		     {
		     ex.printStackTrace();
		     }
		     
		     loaded.useReflection();
		     */
	}

}
