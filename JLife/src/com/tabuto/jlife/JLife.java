/**
* @author Francesco di Dio
* Date: 17/nov/2010 12.05.43
* Titolo: JLife.java
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
  * Questa classe si occupa di gestire il controllo tra il 
  * MODEL: Cell, Group,CollisionMAnager, ecc...
  * e il 
  * VIEW: com.tabuto.jlife.gui.*
  * QUindi contiene tutti gli oggetti appartenente al gioco, e tutti i metodi necessari
  * al VIEW per controllare il MODEL.
  * Questa classe implementa Serializable, in quanto può essere salvata e caricata!
  */

package com.tabuto.jlife;

import java.awt.Dimension;
import java.awt.Graphics;
import java.io.Serializable;

import com.tabuto.j2dgf.Group;
import com.tabuto.j2dgf.collision.CollisionBoundDetector;
import com.tabuto.j2dgf.collision.CollisionManager;

import com.tabuto.jenetic.Dna;
import com.tabuto.jenetic.Gene;
import com.tabuto.jlife.collisions.EatingCollision;
import com.tabuto.jlife.collisions.RiproductionCollision;


/**
 * Class {@code JLife} represent simple wrapper-class incapsulate object like {@link Cell}
 *  {@link Seed} and Collision.
 * <p>
 * Use J2DGF v.0.6.2
 * 
 * @author tabuto83
 * 
 * @version 0.1.5
 * 
 * @see Gene
 * @see Dna
 */
public class JLife implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1995427995443955111L;
	
	/**
	 * Game Playfield Dimension
	 */
	private Dimension DIM;
	
	/**
	 * Game name, use it for save the Game
	 */
	private String Name=""; 
	/**
	 * The actual selected Cell (Not yet used)
	 */
	private Cell selectedCell;
	
	/**
	 * Max number of seed element game can show
	 */
	private final int MAX_SEEDS=3;
	
	/**
	 * Actual game state, if saved (save action allows) else only saveAs permitted;
	 */
	private boolean saved=false;
	/**
	 * The Directory path where Game file is saved/loaded.
	 * Not yet used
	 */
	private String PATH="";
	
	public Group<Zlife> cellsGroup = new Group<Zlife>("CellsSprite");
	public Group<Seed> seedsGroup = new Group<Seed>("SeedsSprite");
	
	//COLLISIONS
	CollisionManager cm;
	EatingCollision eating;
	RiproductionCollision riproduction;
	CollisionBoundDetector cbd;

	public JLife(Dimension dim)
	{
		DIM=dim;
	}
	
	public void initGame() 
	{
		cm = new CollisionManager();
		cbd = new CollisionBoundDetector(cellsGroup,DIM);
		cbd.useReflection();
		riproduction = new RiproductionCollision(cellsGroup,this);
		eating = new EatingCollision(cellsGroup, seedsGroup);
		cm.addCollision(cbd);
		cm.addCollision(riproduction);
		cm.addCollision(eating);
	}
	
	public void setName(String name){this.Name=name;}
	public String getName(){return this.Name;}
	
	public void addCell(Zlife c)
	{
		cellsGroup.add(c);
	}

	public void addSeed(Seed s)
	{
		seedsGroup.add(s);
	}

	public void reset()
	{
		cellsGroup.clear();
		seedsGroup.clear();
	}


	public boolean isSaved(){return this.saved;}
	public void setSaved(boolean s){this.saved=true;}
	
	public void setSelectedCell(Cell c){this.selectedCell=c;}
	public Cell getSelectedCell(){return selectedCell;}

	public void addSeed(int x,int y,int r, double d)
{
	if( seedsGroup.size()< this.MAX_SEEDS)
			      seedsGroup.add( new Seed(this.DIM,x,y,r,d) );
				
		
}


	public void drawSprite(Graphics g)
{

				cellsGroup.move();
				cm.RunCollisionManager();
				cellsGroup.draw(g);
			    seedsGroup.draw(g);

}

	public Dna getDnaFromCell(Cell c)
	{
		return c.getDna();
	}

	public void setPath(String p){this.PATH = p;}
	
	public String getPath(){return this.PATH;}
	
	public int getActualCellCount()
	{
		return cellsGroup.size();
	}
	
}//END CLASS
