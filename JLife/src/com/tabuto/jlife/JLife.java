/**
* @author Francesco di Dio
* Date: 06/Dic/2010 12.05.43
* Titolo: JLife.java
* Versione: 0.1.11 Rev.a:
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
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

import com.tabuto.j2dgf.Game2D;
import com.tabuto.j2dgf.Group;
import com.tabuto.j2dgf.collision.CollisionBoundDetector;
import com.tabuto.j2dgf.collision.CollisionManager;

import com.tabuto.jenetic.Dna;
import com.tabuto.jenetic.Gene;
import com.tabuto.jlife.collisions.EatingCollision;
import com.tabuto.jlife.collisions.RiproductionCollision;
import com.tabuto.jlife.collisions.ZlifeVsZretadorCollision;
import com.tabuto.jlife.collisions.ZretadorRiproduction;


/**
 * Class {@code JLife} represent simple wrapper-class incapsulate object like {@link Cell}
 *  {@link Seed} and Collision.
 * <p>
 * Use J2DGF v.0.7.0
 * 
 * @author tabuto83
 * 
 * @version 0.1.10.3
 * 
 * @see Gene
 * @see Dna
 */
public class JLife extends Game2D implements Serializable,Observer {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1995427995443955111L;
	
	/**
	 * Game name, use it for save the Game
	 */
	//private String Name=""; 
	/**
	 * The actual selected Cell 
	 */
	private Zlife selectedCell;	

	/**
	 * The Directory path where Game file is saved/loaded.
	 * Not yet used
	 */
	private String PATH="";
	
	/**
	 * Group contanis Zlifes and Seeds
	 */
	public  Group<Zlife> cellsGroup = new Group<Zlife>("ZlifeGroup");
	public Group<Seed> seedsGroup = new Group<Seed>("SeedsSprite");
	public Group<Zretador> zretadorGroup = new Group<Zretador>("ZretadorsGroup");
	
	/**
	 * Vector Group List, it used by JLifeStatistic to calculate statistical data
	 * from Sprite's Group
	 */
	public Vector<Group<? extends Zlife>> groupList = new Vector<Group<? extends Zlife>>();
	
	/**
	 * Max ZLifes number
	 */
	private final int MAX_CELL_NUMBER = 250;
	
	/**
	 * Max Zredator number
	 */
	private final int MAX_ZRETADOR_NUMBER = 250;

	/**
	 * Max number of seed element game can show
	 */
	private final int MAX_SEEDS=30;
	
	//COLLISIONS
	EatingCollision eating; //Collision beetween seed and Zlifes
	ZlifeVsZretadorCollision zretadorEating; //Collision Between Zlifes and predator
	RiproductionCollision riproductionZlife; //Collision between Zlifes
	ZretadorRiproduction riproductionZretador; //COllision between Zredators
	CollisionBoundDetector cbdZretador,cbdZlife; //Collision boundDetector

	/**
	 * Jlife Constructor 
	 * @param dim Dimension of the playfield canvas
	 */
	public JLife(Dimension dim)
	{
		super(dim);
	}
	
	/**
	 * Add a Zlife in ZlifeGroup
	 * @param c Zlife
	 */
	public void addCell(Zlife z)
	{
		if(cellsGroup.size()<MAX_CELL_NUMBER)
		{
			
		z.addObserver(this);	
		cellsGroup.add(z);
		setChanged();
		notifyObservers("CountChange");
		}
	}
	
	/**
	 * Add a Zlife in ZlifeGroup
	 * @param c Zlife
	 */
	public void addZretador(Zretador z)
	{
		if(zretadorGroup.size()<MAX_ZRETADOR_NUMBER)
		{
			
		z.addObserver(this);	
		zretadorGroup.add(z);
		setChanged();
		notifyObservers("CountChange");
		}
	}

	/**
	 * Add seed into current Game
	 * @param s Seed
	 */
	public void addSeed(Seed s)
	{
		if (seedsGroup.size()<MAX_SEEDS)
		{
			
			seedsGroup.add(s);
			setChanged();
		}
	}
	
	/**
	 * Add seed into the game
	 * @param x coordinate
	 * @param y coordinate
	 * @param r radius of the seed
	 * @param d density of the seed
	 */
	public void addSeed(int x,int y,int r, double d)
	{
		if( seedsGroup.size()< this.MAX_SEEDS)
			
			      seedsGroup.add( new Seed(this.DIM,x,y,r,d) );

		setChanged();
	}
	
	
	/**
	 * @return the number of the sprite actually active
	 */
	public int getActualCellCount()
	{
		int result=0;
		for(int i=0;i<groupList.size();i++)
			result+= groupList.get(i).size();
		
		return result;
	}
	
	
	/**
	 * Return a Dna from a Zlife
	 * @param z Xlife
	 * @return Dna Zlife's Dna
	 */
	public Dna getDnaFromZlife(Zlife z)
	{
		return z.getZlifeDna();
	}
	
	/**
	 * @return The Game's name
	 */
	public String getName(){return this.Name;}
	
	/**
	 * @return the actual selected zlife
	 */
	public Zlife getSelectedCell()
	{
	  try{
		  if (selectedCell!= null)
			return selectedCell;
		  else
			return null;
	  	}
	  catch(NullPointerException e)
	  {
		  return null;
	  }
	  
	}
	
	/**
	 * @return the actual save directory path
	 */
	public String getPath(){return this.PATH;}
	
	/**
	 * Return the Canvas's playfield Dimension 
	 */
	public Dimension getDimension(){return this.DIM;}
	
	/**
	 * Init the Game's components
	 */
	public void initGame() 
	{
		cm = new CollisionManager();
		
		//Add Group at GroupList
		groupList.add(cellsGroup);
		groupList.add(zretadorGroup);
		
		cbdZlife = new CollisionBoundDetector(cellsGroup,DIM);
		cbdZretador = new CollisionBoundDetector(zretadorGroup,DIM);
		cbdZlife.useReflection();
		cbdZretador.useReflection();
		
		riproductionZlife = new RiproductionCollision(cellsGroup,this);
		riproductionZlife.setDistance(20);
		
		riproductionZretador = new ZretadorRiproduction(zretadorGroup,this);
		riproductionZretador.setDistance(20);
		
		eating = new EatingCollision(cellsGroup, seedsGroup);
		zretadorEating = new ZlifeVsZretadorCollision(cellsGroup,zretadorGroup);
		
		cm.addCollision(cbdZlife);
		cm.addCollision(cbdZretador);
		cm.addCollision(riproductionZlife);
		cm.addCollision(riproductionZretador);
		cm.addCollision(eating);
		cm.addCollision(zretadorEating);
		//cm.start();
	}
	
	/**
	 * @return true if this game is previously saved
	 */
	public boolean isSaved(){return this.saved;}

	/**
	 * Delete all the sprites in all groups
	 */
	public void reset()
	{
		for(int i=0;i<groupList.size();i++)
			groupList.get(i).clear();
		
		setChanged();
		notifyObservers("CountChange");
	}
	
	/**
	 * Select a cell nearby a certain coordinates, using a distance tolerance
	 * @param x
	 * @param y
	 * @param tolerance
	 */
	public void selectCell(int x,int y,int tolerance )
	{
		if(selectedCell==null)
		{
			selectedCell = (Zlife)cellsGroup.getSpriteByPos(x,y,tolerance);	
			if(selectedCell==null)
				selectedCell = (Zlife)zretadorGroup.getSpriteByPos(x,y,tolerance);	
			if(selectedCell!=null)
				{
				selectedCell.select();
				setChanged();
				notifyObservers("SelectionChange");
				}
			else
			{
				setChanged();
				notifyObservers("SelectionChange");
			}
		}
		else
		{
			selectedCell.deselect();
			selectedCell = (Zlife)cellsGroup.getSpriteByPos(x,y,tolerance);
				if(selectedCell==null)
					selectedCell = (Zlife)zretadorGroup.getSpriteByPos(x,y,tolerance);	
			if(selectedCell!=null)
				selectedCell.select();

				setChanged();
				notifyObservers("SelectionChange");
		}
			
	}
	
	/**
	 * set the Name of the JLife game, it is used for save game
	 * @param name String name of Game for save
	 */
	public void setName(String name){this.Name=name;}
	
	
	/**
	 * Set the game state as saved
	 * @param s boolean
	 */
	public void setSaved(boolean s){this.saved=true;setChanged();}
	
	/**
	 * Set the param zlife z as the actual selected cell
	 * @param z Zlife
	 */
	public void setSelectedCell(Zlife z){
		this.selectedCell=z;
		setChanged();
		notifyObservers("SelectionChange");
		}
	
	/**
	 * Set the save Path for this Game
	 * @param p String represent the absolut Path to the save directory
	 */
	public void setPath(String p){this.PATH = p;}
	
	public int getMaxCellsNumber(){return MAX_CELL_NUMBER + MAX_ZRETADOR_NUMBER;}

	/**
	 * Move and drow all the sprite
	 */
	@Override
	public void drawStuff(Graphics g) {
		
	
		cellsGroup.move();
		zretadorGroup.move();
		//cm.run();
		cellsGroup.draw(g);
		seedsGroup.draw(g);
		zretadorGroup.draw(g);
		//if((cellsGroup.size() + zretadorGroup.size()) != cellCount)
		//{
		//	setChanged();
		//	notifyObservers("CountChange");
		//}
		
	}

	/**
	 * This class Observe collision Manager, when CollisionMAnager changeState
	 * it sends a message to this class, the message is sending by calling the
	 * observer update method.
	 * @param arg1 is the parameter passed by the Observable class
	 * 
	 *  @see CollisionManager
	 */
	@Override
	public void update(Observable arg0, Object arg1) {
		
		this.setChanged();
		this.notifyObservers(arg1);
		
		
		if( arg1 instanceof Zlife)
				{
					Zlife z = (Zlife) arg1;
					if (!(z.isAlive()))
					{//z is die
						cellsGroup.remove(z);
						cellsGroup.trimToSize();
						notifyObservers("CountChange");
						Seed s = new Seed(
								z.getDimension(),
								(int)z.getX(),(int)z.getY(),
								z.getRadius()*4,
								z.getRadius()
								);
						this.addSeed(s);
					}
				}
		if( arg1 instanceof Zretador)
		{
			Zretador z = (Zretador) arg1;
			if (!(z.isAlive()))
			{//z is die
				zretadorGroup.remove(z);
				zretadorGroup.trimToSize();
				notifyObservers("CountChange");
				Seed s = new Seed(
						z.getDimension(),
						(int)z.getX(),(int)z.getY(),
						z.getRadius()*4,
						z.getRadius()
						);
				this.addSeed(s);
			}
		}
		
		
		if (arg1 instanceof String)
		{
			//String message = (String) arg1;
			
				
		}
		
	}
	
}//END CLASS
