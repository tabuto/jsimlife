/**
* @author Francesco di Dio
* Date: 07/nov/2010 18.38.01
* Titolo: JLifeCanvas.java
* Versione: 0.1.1 Rev.a:
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

package com.tabuto.jlife.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Vector;

import com.tabuto.j2dgf.Group;
import com.tabuto.j2dgf.Sprite;
import com.tabuto.j2dgf.collision.CollisionBoundDetector;
import com.tabuto.j2dgf.collision.CollisionDetector;
import com.tabuto.j2dgf.gui.J2DCanvasPanel;
import com.tabuto.jenetic.Dna;
import com.tabuto.jlife.Cell;
import com.tabuto.jlife.Seed;



public class JLifeCanvas extends J2DCanvasPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3243620944955104850L;

	private Cell selectedCell;
	private final int MAX_SEEDS=3;
	
	public Group<Cell> cellsGroup = new Group<Cell>("CellsSprite");
	public Group<Seed> seedsGroup = new Group<Seed>("SeedsSprite");
	
	
	//COLLISIONS
	EatingCollision eating;
	RiproductionCollision riproduction;
	CollisionBoundDetector cbd;
	
public JLifeCanvas(int width, int height)
	{
	
	super(width, height);
	
	}

public void initStuff()
{
	cbd = new CollisionBoundDetector(cellsGroup,DIM);
	cbd.useReflection();
	riproduction = new RiproductionCollision(cellsGroup,this);
	eating = new EatingCollision(cellsGroup, seedsGroup);
	cm.addCollision(cbd);
	cm.addCollision(riproduction);
	cm.addCollision(eating);
	
	//Add 4 cell and 1 seed
	this.addNewCells(4);
	
	Cell newCell = new Cell(this.DIM, 
			   512, 100,
			   5);
  newCell.setSpeed(100);
  newCell.setAngleRadians(Math.PI*0.5 );
  
	 cellsGroup.add(newCell);
	
	this.addSeed(512, 512, 40, 15);
	//this.addSeed((int) (1+ Math.random()*800), 
	//		     (int) (1+ Math.random()*600),
	//		     (int)(1+ Math.random()*20),
	//		     (int)(1+ Math.random()*45));
	
	
}

public void addCell(double x, double y, Dna newDna)
	{
		Cell newCell = new Cell(x,y,newDna);
		cellsGroup.add(newCell);
	}

public void addNewCells(int cellNumber)
{
	     for(int i=0;i<cellNumber;i++)
	         { Cell newCell = new Cell(this.DIM, 
					   Math.random()*DIM.width ,
 					   Math.random()*DIM.height,
 					   (int) (2+Math.random()*10));
	         newCell.setSpeed(80);
	         newCell.setAngleRadians(Math.random()*2 * Math.PI );
	         
	    	 cellsGroup.add(newCell);
			}

}

public void setSelectedCell(Cell c){this.selectedCell=c;}
public Cell getSelectedCell(){return selectedCell;}

public void addSeed(int x,int y,int r, double d)
{
	if( seedsGroup.size()< this.MAX_SEEDS)
			      seedsGroup.add( new Seed(this.DIM,x,y,r,d) );
				
		
}


protected void drawSprite(Graphics g)
{

				cellsGroup.move();
				cm.RunCollisionManager();
				cellsGroup.draw(g);
			seedsGroup.draw(g);

}

//INNER CLASS EATINGCOLLISION

//CollisionDetector: What does it do when a collision checked?
public class EatingCollision extends CollisionDetector{

	//Constructor
	 public EatingCollision(Group<Cell> sp1, Group<Seed> sp2)
	 {
		 super(sp1,sp2);
	 }
	 //Override CollisionAction
	 public void CollisionAction(int s1, int s2)
	  {
		//Cast to class extends Sprite 
		Cell cell1;
		Seed seed2;
		cell1 = (Cell) group1.get(s1);
		seed2 = (Seed) group2.get(s2);
		
		//TO-DO
		
		//if (cell1.isAlive() && cell1.isHungry())
		//if (cell1.isAlive() )
				{
				cell1.setEnergy(100);
				cell1.setHangry(false);
				seed2.eatMe();
				}
		
	  }
 
}


//INNER CLASS RIPRODUCTIONCOLLISION

//CollisionDetector: What does it do when a collision checked?
public class RiproductionCollision extends CollisionDetector{

	public JLifeCanvas jlc;
	//Constructor
	 public RiproductionCollision(Group<Cell> sp1, JLifeCanvas j)
	 {
		 super(sp1);
		 jlc=j;
	 }
	 //Override CollisionAction
	 public void CollisionAction(int s1, int s2)
	  {
		//Cast to class extends Sprite 
		Cell cell1, cell2;
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
				jlc.addCell(cell1.getX(),cell2.getY(),newBornDna);
				
				}
		
	  }

		
	 
}



}