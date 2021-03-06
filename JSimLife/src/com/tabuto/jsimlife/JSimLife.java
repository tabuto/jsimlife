/**
* @author Francesco di Dio
* Date: 27/Dic/2010 12.05.43
* Titolo: JLife.java
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

 /*
  * Questa classe si occupa di gestire il controllo tra il 
  * MODEL: Cell, Group,CollisionMAnager, ecc...
  * e il 
  * VIEW: com.tabuto.jsimlife.views.*
  * QUindi contiene tutti gli oggetti appartenente al gioco, e tutti i metodi necessari
  * al VIEW per controllare il MODEL.
  * Questa classe implementa Serializable, in quanto può essere salvata e caricata!
  */

package com.tabuto.jsimlife;

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
import com.tabuto.jsimlife.collisions.ZetatronEatingSeed;
import com.tabuto.jsimlife.collisions.ZetatronEatingZretador;
import com.tabuto.jsimlife.collisions.ZlifeEatingSeed;
import com.tabuto.jsimlife.collisions.ZlifeReproduction;
import com.tabuto.jsimlife.collisions.ZetatronReproduction;
import com.tabuto.jsimlife.collisions.ZretadorEatingZlife;
import com.tabuto.jsimlife.collisions.ZretadorReproduction;




import com.tabuto.jsimlife.Configuration;


/**
 * Class {@code JLife} represent simple wrapper-class encapsulate objects like {@link Zlife}
 *  {@link Seed} and Collision.
 *  
 * <p>
 * Use J2DGF v.0.7.3
 * 
 * @author tabuto83
 * 
 * @version 0.2.0
 * 
 * @see Gene
 * @see Dna
 * @see Group
 * @see CollisionAction
 * @see CollisionManager
 */
public class JSimLife extends Game2D implements Serializable,Observer {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1995427995443955111L;
	
	
	/**
	 * Game Configuration
	 */
	Configuration config;
	
	/**
	 * Game name, use it for save the Game
	 */
	//private String Name=""; 
	/**
	 * The actual selected Cell 
	 */
	private Zlife selectedCell;	

	/**
	 * The actual selected Seed
	 */
	private Seed selectedSeed;
	
	/**
	 * Group contanis Zlifes
	 */
	public  Group<Zlife> cellsGroup = new Group<Zlife>("Zlifes");
	/**
	 * Group contanis Seed
	 */
	public Group<Seed> seedsGroup = new Group<Seed>("SeedsSprite");
	/**
	 * Group contanis Zretador
	 */
	public Group<Zretador> zretadorGroup = new Group<Zretador>("Zretadors");
	/**
	 * Group contanis Zetatrons
	 */
	public Group<Zetatron> zetatronGroup = new Group<Zetatron>("Zetatrons");
	/**
	 * Vector Group List, it used by JLifeStatistic to calculate statistical data
	 * from Sprite's Group. It contains all Zlife's group
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
	 * Max Zetatron number
	 */
	private final int MAX_ZETATRON_NUMBER = 125;
	
	
	/**
	 * Max number of seed element game can show
	 */
	private final int MAX_SEEDS=30;
	
	/**
	 * Collision between Zlifes and seed
	 */
	ZlifeEatingSeed zlifeEating; //Collision beetween seed and Zlifes
	
	/**
	 * Collision between Zlifes when they hornies
	 */
	ZlifeReproduction reproductionZlife; //Collision between Zlifes
	
	/**
	 * Collision between Zretadors and Zlifes when Zretadors hungries
	 */
	ZretadorEatingZlife zretadorEating; //Collision Between Zlifes and predator
	/**
	 * Collision between Zretador when they hornies
	 */
	ZretadorReproduction reproductionZretador; //COllision between Zredators
	
		//ZETATRON
	/**
	 * Collision between Zetatron and seed
	 */
	ZetatronEatingSeed zetatronSeedEating;
	/**
	 * Collision between Zetatron and Zretadors when Zetatron hungries
	 */
	ZetatronEatingZretador zetatronEating;
	
	/**
	 * Collision between Zlifes when they hornies
	 */
	ZetatronReproduction reproductionZetatron;
	
	/**
	 * Collision between all Zlifes and Simulation Panel boundary
	 */
	CollisionBoundDetector cbdZretador,cbdZlife, cbdZetatron; //Collision boundDetector

	/**
	 * Build a new JSimLife instance 
	 * @param dim Dimension of the playfield canvas
	 * @see Game2D
	 */
	public JSimLife(Dimension dim)
	{
		super(dim);
	}
	
	/**
	 * Override activate to notify observers (Like bottomPanel) if Game is active!
	 */
	@Override
	public void activate()
	{
		super.activate();
		setChanged();
		notifyObservers("activate");
	}
	
	/**
	 * Add a Zlife in ZlifeGroup
	 * @param z Zlife
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
	 * Add a Zetatron in ZetatronGroup
	 * @param z Zetatron
	 */
	public void addZetatron(Zetatron z)
	{
		if(zetatronGroup.size()<MAX_ZETATRON_NUMBER)
		{
			
		z.addObserver(this);	
		zetatronGroup.add(z);
		setChanged();
		notifyObservers("CountChange");
		}
	}
	
	/**
	 * Add a Zretador in ZretadorGroup
	 * @param z Zretador
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
			setChanged();
			notifyObservers("AddSeed");
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
		notifyObservers("AddSeed");
	}
	
	/**
	 * Override deactivate to notify observers (Like bottomPanel) if Game is active!
	 */
	@Override
	public void deactivate()
	{
		super.deactivate();
		setChanged();
		notifyObservers("deactivate");
	}
	
	/**
	 * @return the number of the sprite actually active
	 */
	public int getActualCellCount()
	{
		int result=0;
		
		for(int i=0;i<groupList.size();i++)
			{
				groupList.get(i).trimToSize();
				result+= groupList.get(i).size();
			}
		
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
	
	public Configuration getConfiguration()
	{
		return this.config;
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
	 * @return the actual selected seed
	 */
	public Seed getSelectedSeed()
	{
	  try{
		  if (selectedSeed!= null)
			return selectedSeed;
		  else
			return null;
	  	}
	  catch(NullPointerException e)
	  {
		  return null;
	  }
	  
	}
	
	
	/**
	 * Return the Canvas's playfield Dimension 
	 */
	public Dimension getDimension(){return this.DIM;}
	
	/**
	 * Init the Game's components:
	 * 
	 */
	public void initGame() 
	{
		//Create collision manager
		//cm = new CollisionManager();
		//Collision Manager is already created by the Game2D class
		
		//Add Group at GroupList
		groupList.add(cellsGroup);
		groupList.add(zretadorGroup);
		groupList.add(zetatronGroup);
		
		//Create Collision Bound Detector for each Sprite Group
		cbdZlife = new CollisionBoundDetector(cellsGroup,DIM);
		cbdZretador = new CollisionBoundDetector(zretadorGroup,DIM);
		cbdZetatron = new CollisionBoundDetector(zetatronGroup,DIM);
		cbdZlife.useReflection();
		cbdZretador.useReflection();
		cbdZetatron.useReflection();
		
		//Create collision detector between sprites
			//ZLIFE
		reproductionZlife = new ZlifeReproduction(cellsGroup,this);
		reproductionZlife.setDistance(20);
		zlifeEating = new ZlifeEatingSeed(cellsGroup, seedsGroup);
		
			//ZRETADOR
		reproductionZretador = new ZretadorReproduction(zretadorGroup,this);
		reproductionZretador.setDistance(20);
		zretadorEating = new ZretadorEatingZlife(zretadorGroup,cellsGroup);
		
			//ZETATRON
		reproductionZetatron = new ZetatronReproduction(zetatronGroup,this);
		reproductionZetatron.setDistance(20);
		zetatronSeedEating = new ZetatronEatingSeed(zetatronGroup, seedsGroup);
		zetatronEating =  new ZetatronEatingZretador(zetatronGroup,zretadorGroup);
		
		//Register COllision to CollisionManager
			//ZLIFE
		cm.addCollision(cbdZlife);
		cm.addCollision(reproductionZlife);
		cm.addCollision(zlifeEating);
		//ZRETADOR
		cm.addCollision(cbdZretador);
		cm.addCollision(reproductionZretador);
		cm.addCollision(zretadorEating);
		//ZETATRON
		cm.addCollision(cbdZetatron);
		cm.addCollision(reproductionZetatron);
		cm.addCollision(zetatronEating);
		cm.addCollision(zetatronSeedEating);
		
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
		deactivate();
		for(int i=0;i<groupList.size();i++)
			groupList.get(i).clear();
		
		seedsGroup.clear();
		
		setChanged();
		notifyObservers("CountChange");
		activate();
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
			if(selectedCell==null)
				selectedCell = (Zlife)zetatronGroup.getSpriteByPos(x,y,tolerance);
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
	 * Select a Seed nearby a certain coordinates, using a distance tolerance
	 * @param x
	 * @param y
	 * @param tolerance
	 */
	public void selectSeed(int x, int y, int tolerance)
	{
		Seed selectedSeed = seedsGroup.getSpriteByPos(x, y, tolerance);
		if (selectedSeed != null)
		{
			this.selectedSeed=selectedSeed;
			setChanged();
			notifyObservers("SeedSelected");
		}
	}
	
	/**
	 * @param config Game Configuration
	 * @see Configuration
	 */
	public void setConfiguration(Configuration config)
	{
		this.config = config;
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
	public void setSaved(boolean s)
	{
		this.saved=true;
		setChanged();
		notifyObservers("GameSaved");
	}
	
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
	 * Clear all the sprite group, stop the CollisionManager and all the 
	 * collisionDetector thread
	 */
	public void shutdown()
	{
		deactivate();
		for(int i=0;i<groupList.size();i++)
		groupList.get(i).clear();
		cm.shutdown();
	}
	
	
	/**
	 * @return the max cells number as the sum of Zlife, Zredator and Zetatron's max number
	 */
	public int getMaxCellsNumber(){return MAX_CELL_NUMBER + MAX_ZRETADOR_NUMBER+MAX_ZETATRON_NUMBER;}

	/**
	 * Moves and draws all sprites
	 */
	@Override
	public void drawStuff(Graphics g) {
		
	
		cellsGroup.move();
		zretadorGroup.move();
		zetatronGroup.move();
		//cm.run();
		cellsGroup.draw(g);
		seedsGroup.draw(g);
		zretadorGroup.draw(g);
		zetatronGroup.draw(g);
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
		
		 if( arg1 instanceof Zetatron)
		{
			Zetatron z = (Zetatron) arg1;
			if (!(z.isAlive()))
			{//z is die
				zetatronGroup.remove(z);
				zetatronGroup.trimToSize();
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
		
		
		if (arg1 instanceof String)
		{
			//String message = (String) arg1;
			
				
		}
		
	}
	
}//END CLASS

