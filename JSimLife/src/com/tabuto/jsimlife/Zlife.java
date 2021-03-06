/**
* @author Francesco di Dio
* Date: 27/dic/2010 23.39.33
* Titolo: Zlife.java
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

package com.tabuto.jsimlife;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.io.Serializable;
import com.tabuto.j2dgf.Sprite;
import com.tabuto.jenetic.Dna;
import com.tabuto.jenetic.Gene;
import com.tabuto.util.Point;



/**
 * Class {@code Zlife} represent an elementary life's form able to reproduce itself
 * using a {@link Dna} to transmit its Genetic Code!
 * Cell life is very simple, it move in search of food, because moving equals spend energy
 * When its energy is less than a hungryEnergy parameter, it can eat.
 * When its energy is more than a hornyEnergy, it's able to reproduce 
 * itself if it collides by another Cell.
 * <p>
 * Use Jenetic2.1
 * Use J2DGF v.0.7.0
 * 
 * @author tabuto83
 * 
 * @version 0.2.0
 * 
 * @see Gene
 * @see Dna
 * @see Sprite
 */
public class Zlife extends Sprite implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1705433665252708243L;
	
	protected Dna ZlifeDna;
	
	protected double dnaParam;
	
	protected double energy;
	
	//TODO: why not delete maxEnergy?
	protected double maxEnergy;
	
	protected double hornyEnergy;
	
	protected double hungryEnergy;
	
	protected int lifeCycle;
	
	protected double metabolism;
	
	protected double BoredSpeed;

	protected double riproductionEnergy;
	
	protected double hungrySpeed;
	
	protected double hornySpeed;
	
	protected double scarySpeed;
	
	protected int radius;
	
	protected double ageFactor;
	
	protected int R;
	protected int G;
	protected int B;
	
	//ACTUAL-PARAMETER: NO-DNA
	public enum CellState  {NULL, HUNGRY, HORNY, BORED, SCARY}
	public CellState state = CellState.BORED;
	protected boolean alive=true;
	protected boolean SELECTED = false;
	protected Color zlifeColor;
	protected int actualLifeCycle=0; //the actual age of Zlife
	

	boolean marked = false;
	Color markedColor = Color.GREEN;
	/**
	 * Store position of last seed meet by Zlife
	 */
	protected Point seedPosition = new Point();
	/**
	 * generationNumber is incremented to every reproduction
	 */
	protected int generationNumber = 0;
	
	//private boolean attacked=false;
	
	protected double realMetabolism;

	//CONSTRUCTOR
	/**
	 * Make a new Zlife using the {@link Dna} passed him as parameter.
	 * @param d Dimension of the Game playfield
	 * @param x double Coordinate
	 * @param y double coordinate
	 * @param zlifeDna Zlife's Dna
	 * @see Dna
	 */
	public Zlife(Dimension d, double x, double y, Dna zlifeDna)
	{
		super(d,x,y);
		this.setZlifeDna(zlifeDna);
		loadDnaGenes();
		
		setAlive(true);
		setZlifeColor(R,G,B);
		setMaxOffset(getRadius(), getRadius(), getRadius(), getRadius());
		setState(CellState.BORED);
		
		realMetabolism = Math.sqrt(getRadius()) * getMetabolism();
	}
	
	
//PUBLIC METHODS	
	/**
	 * This method simulate the Zlife Age's process 
	 */
	public void age()
	{
		if(actualLifeCycle<=lifeCycle)
		{
			int colorFactor = (int)(255/(lifeCycle*2+1));
			setMaxEnergy( getMaxEnergy() - getMaxEnergy()*getAgeFactor());
			this.setZlifeColor(this.R + colorFactor, 
				      	  	   this.G + colorFactor,
				               this.B + colorFactor);
			actualLifeCycle++;
		}
		else
			{setAlive(false); Deactivate();}
	}

	/**
	 * Deselect this Zlife
	 */
	public void deselect(){SELECTED=false;}
	
	
	
	/**
	 * @return the Actual Life Cycle
	 */
	public int getActualLifeCycle()
	{
		return this.actualLifeCycle;
	}
	
	/**
	 * @return the Zlife age factor 
	 */
	public double getAgeFactor(){return ageFactor;}
	
	/**
	 * @return the Zlife's Color Blue Component
	 */
	public int getB() {
		return B;
	}
	
	/**
	 * @return the boredSpeed
	 */
	public double getBoredSpeed() {
		return BoredSpeed;
	}
	
	/**
	 * @return a String with a brief user friendly Zlife's description 
	 */
	public static String getDescription()
	{
		return "Zlife is a simple artificial life's form. It live its life" +
				"crossing for 4 Life State: Bored, Horny, Scary and Hungry." +
				"\nIt change its state when energy is under/over certain values.\n" +
				"Its energy decrease when it moves and increase when it eats." +
				"Every change state its LifeCycle decreases, and when it turn to zero" +
				"it die.";
	}
	
	/**
	 * @return the dnaParam
	 * @see {@link Dna#getParam()}
	 */
	public double getDnaParam() {
		return dnaParam;
	}

	/**
	 * @return the energy
	 */
	public double getEnergy() {
		return energy;
	}
	
	/**
	 * @return the Zlife's Color Green Component
	 */
	public int getG() {
	return G;
	}
	
	/**
	 * @return The generation Number
	 * @see Zlife#generationNumber
	 */
	public int getGenerationNumber()
	{
		return generationNumber;
	}
		
	/**
	 * @return the hornyEnergy
	 */
	public double getHornyEnergy() {
		return hornyEnergy;
	}


	/**
	 * @return the hornySpeed
	 */
	public double getHornySpeed() {
		return hornySpeed;
	}

	/**
	 * @return the hungrySpeed
	 */
	public double getHungrySpeed() {
		return hungrySpeed;
	}

	/**
	 * @return the hungryEnergy
	 */
	public double getHungryEnergy() {
		return hungryEnergy;
	}

	
	/**
	 * @return the lifeCycle
	 */
	public int getLifeCycle() {
		return lifeCycle;
	}

	/**
	 * @return the maxEnergy
	 */
	public double getMaxEnergy() {
		return maxEnergy;
	}

	/**
	 * @return the metabolism
	 */
	public double getMetabolism() {
		return metabolism;
	}


	/**
	 * @return the COlor's Sprite red Component 
	 */
	public int getR() {
		return R;
	}

	/**
	 * @return the radius
	 */
	public int getRadius(){
		return radius;
	}

	/**
	 * @return the riproductionEnergy
	 */
	public double getRiproductionEnergy() {
		return riproductionEnergy;
	}

	/**
	 * @return the scarySpeed
	 */
	public double getScarySpeed() {
		return scarySpeed;
	}

	/**
	 * @return the actual seed position stored in this Zlife
	 */
	public Point getSeedPosition()
	{
		return seedPosition;
	}

	/**
	 * @return the state
	 */
	public CellState getState() {
		return state;
	}

	/**
	 * @return the zlifeColor
	 */
	public Color getZlifeColor() {
		return zlifeColor;
	}
	
	/**
	 * @return the Zlife's {@link Dna}
	 */
	public Dna getZlifeDna() {
		return ZlifeDna;
	}
	
	/**
	 * @return the alive
	 */
	public boolean isAlive() {
		return alive;
	}
	
	/**
	 * @return true if Zlife's state is BORED
	 */
	public boolean isBored()
	{
		 if (state==CellState.BORED)
			 return true;
			 else
				 return false;
	}

	/**
	 * @return true if Zlife state is set HORNY
	 */
	public boolean isHorny()
	{
		 if (state==CellState.HORNY)
			 return true;
			 else
				 return false;
	}
	
	/**
	 * @return true if Zlife state is set HUNGRY
	 */
	public boolean isHungry()
	{
		 if (state==CellState.HUNGRY)
			 return true;
			 else
				 return false;
	}
	
	/**
	 * @return true if ZLife is marked, false if not
	 */
	public boolean isMarked()
	{
		return marked;
	}

	/**
	 * @return true if Zlife state is set SCARY
	 */
	public boolean isScary()
	{
		 if (state==CellState.SCARY)
			 return true;
			 else
				 return false;
	}
	
	/**
	 * This method describes what the ZLife do in his life
	 */
	public void live()
	{
		if(isAlive())
		{
			switch(state)
			{
			case BORED:
				{
					if(energy>hungryEnergy && energy<hornyEnergy)
					{
						setSpeed((int) getBoredSpeed());
						break; //this.move();
					}
					
					if(energy<hungryEnergy)
					{   age();
						setSpeed((int) getHungrySpeed());
						setState(CellState.HUNGRY);
						break;
					}
					
					if(energy>hornyEnergy)
					{
						age();
						setSpeed((int) getHornySpeed());
						setState(CellState.HORNY);
						break;
					}
					
				}
			case HUNGRY:
				{
					 if (energy < hungryEnergy)
                     {
                           if(seedPosition.getX()==0 && seedPosition.getY()==0)
                                   {
                                           this.setSpeed(  (int) ((BoredSpeed + hungrySpeed)/2 ));
                                           break;
                                   }
                           else
                                   {
                                           this.setSpeed((int)getHungrySpeed());
                                           this.moveTo(getSeedPosition());
                                           seedPosition.setX(0);
                                       seedPosition.setY(0);
                                           break;
                                   }
                     }
                   if (energy > hungryEnergy)
                     {
                             //this.age();
                             this.setSpeed((int)getBoredSpeed());
                             setState(CellState.BORED);
                             this.setAngleRadians(Math.random()*Math.PI*2);
                             break;
                     }  
					
				}
			case HORNY:
				{
					if(energy>hornyEnergy)
						{
						this.setSpeed((int)getHornySpeed());
						break;
						}
					if(energy<hornyEnergy && energy>hungryEnergy)
					{
						//age();
						 this.setSpeed((int)getBoredSpeed());
						setState(CellState.BORED);
						this.setAngleRadians(Math.random()*Math.PI*2);
						break;
					}
					if(energy<hungryEnergy)
					{
						age();
						 this.setSpeed((int)getHungrySpeed());
						setState(CellState.HUNGRY);
						this.setAngleRadians(Math.random()*Math.PI*2);
						break;
					}
				}
				
			case SCARY:
				{   
					
					this.setSpeed((int)scarySpeed);
					
					//if ((int)energy == (int)((hungryEnergy)*1.5))
						//this.setAngleRadians(Math.random()*Math.PI*2);
					
					if(energy< (maxEnergy*0.10))
					{
						age();
						 this.setSpeed((int)getHungrySpeed());
						setState(CellState.HUNGRY);
						this.setAngleRadians(Math.random()*Math.PI*2);
						break;
					}
					
				}
			
			default:
				{
					setSpeed((int) getBoredSpeed());
					break; //this.move();
				}
				
			}
		}
		else
			{
			setAlive(false);
			//setAlive(false);
			}
	}
	
	/**
	 * Mark this ZLife with a green boundary rectangle.
	 * Useful to recognize a special ZLife
	 */
	public void marked()
	{
		marked = true;
	}
	
	/**
	 * Mark this ZLife woth a 'c' Colored rectangle
	 * @param c
	 */
	public void marked(Color c)
	{
		this.markedColor = c;
		marked();
	}
	
	@Override
	public void move()
	{
		if ( ACTIVE && this.getSpeed()> 0 )
  	  {  
  	      
  		  double nx = (this.vector.origin.x + this.getMySpeed() * Math.cos(this.vector.getDirectionRadians() ) );
  		  double ny = (this.vector.origin.y + this.getMySpeed() * Math.sin(this.vector.getDirectionRadians()));
  		  this.vector.setNewOrigin(nx,ny);
  		  
  		setChanged();
		notifyObservers("ZLife:Move");
  		  
		if(isScary())
			energy = (energy - realMetabolism*2 - (getSpeed()/50));
		else
  		  	energy = (energy - realMetabolism - (getSpeed()/100));
  		  
  		  if(energy<=0 || actualLifeCycle>lifeCycle)
  			 {setAlive(false);Deactivate();}
  		  else
  			  this.live();
  		
  	  }
	}
	
	/**
	 * Return a new Zlife with merged DNA
	 * @param z Other Zlife
	 * @return new Zlife
	 */
	public Zlife reproduction(Zlife z)
	{
		Dna newBornDna= Dna.merge(z.getZlifeDna(), this.getZlifeDna());
		
		double tempAngle = this.getAngle();
		this.setAngleRadians(z.getAngle());
		z.setAngleRadians(tempAngle);
		Zlife newCell = new Zlife(z.getDimension(), z.getX(),z.getY(),newBornDna);
		newCell.setAngleRadians( Math.random() * 2 * Math.PI );
		newCell.setEnergy( newCell.getHungryEnergy()*0.9); 
		z.setEnergy( z.getEnergy() - z.getRiproductionEnergy());
		this.setEnergy(this.getEnergy() - this.getRiproductionEnergy());
		newCell.setGenerationNumber( Math.max(z.getGenerationNumber(), this.getGenerationNumber())+1);
		
		return newCell;
	}
	
	/**
	 * Select this Zlife
	 */
	public void select(){SELECTED=true;}
	
	/**
	 * @param af set the new age factor for this Zlife
	 */
	public void setAgeFactor(double af)
	{
		if(af<0)
			af= Math.abs(af);
	  if(af > 1 )
			af = 1/af;
	  
	  ageFactor=af;
	}
	
	/**
	 * @param alive the alive to set
	 */
	public synchronized void setAlive(boolean alive) {
		this.alive = alive;
		if (!alive)
		{
			setChanged();
			notifyObservers(this);
		}
	}
	
	/**
	 * @param b the blue Component Zlife's color to set
	 */
	public void setB(int b) {
		if(b>=0 && b<=255)
			B = b;
		if(b<0)
			B=0;
		if(b>255)
			B = 255;
	}
	
	/**
	 * Set the ZLife's state as BORED
	 */
	public void setBored()
	{
		state=CellState.BORED;
		setChanged();
		notifyObservers("ZLife:ChangeState");
	}
	
	/**
	 * @param boredSpeed the boredSpeed to set
	 */
	public void setBoredSpeed(double boredSpeed) {
		if(boredSpeed<0)
			boredSpeed=0;
		if (boredSpeed>100)
			boredSpeed=100;
		
		BoredSpeed = boredSpeed;
	}
	
	/**
	 * @param c Color; Set the new Color of the sprite as a RGB values
	 */
	public void setColor(Color c)
	{
		zlifeColor=c;
		R = c.getRed();
		G = c.getGreen();
		B = c.getBlue();
	}
	
	


	/**
	 * @param dnaParam the dnaParam to set
	 * @see {@link Dna#setParam(double)}
	 */
	public void setDnaParam(double dnaParam) {
		this.ZlifeDna.setParam(dnaParam);
		this.dnaParam = this.ZlifeDna.getParam();
	}



	/**
	 * @param energy the energy to set
	 */
	public void setEnergy(double energy) 
	{
		if(energy<0)
			energy = 0;
		if(energy>this.getMaxEnergy())
			energy = this.getMaxEnergy();
		
			this.energy = energy;
	}

	/**
	 * @param maxEnergy the maxEnergy to set
	 */
	public void setMaxEnergy(double maxEnergy) {
		if(maxEnergy>0)
			this.maxEnergy = maxEnergy;
		else
			this.maxEnergy = Math.abs(maxEnergy);
	}

	/**
	 * @param g the g to set
	 */
	public void setG(int g) {
		if(g>=0 && g<=255)
			G = g;
		if(g<0)
			G=0;
		if(g>255)
			G = 255;
	}
	
	/**
	 * @param g The Generation Number to set
	 * @see Zlife#generationNumber
	 */
	public void setGenerationNumber(int g)
	{
		if (g>=0)
			generationNumber = g;
	}
	
	/**
	 * Set the Zlife state as HORNY
	 */
	public void setHorny()
	{
		state=CellState.HORNY;
		setChanged();
		notifyObservers("ZLife:ChangeState");
	}
	
	/**
	 * Set the Zlife state as HUNGRY
	 */
	public void setHungry()
	{
		state=CellState.HUNGRY;
		setChanged();
		notifyObservers("ZLife:ChangeState");
	}

	/**
	 * @param hornyEnergy the hornyEnergy to set
	 */
	public void setHornyEnergy(double hornyEnergy) {
		if(hornyEnergy>=0 && hornyEnergy<= this.getMaxEnergy() )
		this.hornyEnergy = hornyEnergy;
	}



	/**
	 * @param hungryEnergy the hungryEnergy to set
	 */
	public void setHungryEnergy(double hungryEnergy) {
		if(hungryEnergy>=0 && hungryEnergy<= this.getMaxEnergy() )
		this.hungryEnergy = hungryEnergy;
	}
	



	/**
	 * @param lifeCycle the lifeCycle to set
	 */
	public void setLifeCycle(int lifeCycle) {
		if(lifeCycle>0)
		this.lifeCycle = lifeCycle;
	}



	/**
	 * @param metabolism the metabolism to set
	 */
	public void setMetabolism(double metabolism) {
		if(metabolism>0 && metabolism<this.maxEnergy)
			this.metabolism = metabolism;
	}
	
	/**
	 * Set the Zlife's name both class and dna 
	 * @param name String Zlife's name
	 */
	@Override
	public void setName(String name)
	{
		this.Name = name;
		this.ZlifeDna.setName(name);
	}

	/**
	 * @param riproductionEnergy the riproductionEnergy to set
	 */
	public void setRiproductionEnergy(double riproductionEnergy) {
		if(riproductionEnergy>0 && riproductionEnergy<this.maxEnergy)
		this.riproductionEnergy = riproductionEnergy;
	}



	/**
	 * @param hungrySpeed the hungrySpeed to set
	 */
	public void setHungrySpeed(double hungrySpeed) {
		if(hungrySpeed<0)
			hungrySpeed=0;
		
		if( hungrySpeed>100)
			hungrySpeed=100;
		
			this.hungrySpeed = hungrySpeed;
			
		
	}



	/**
	 * @param hornySpeed the hornySpeed to set
	 */
	public void setHornySpeed(double hornySpeed) {
		if(hornySpeed<0)
			this.hornySpeed=0;
		
		if(hornySpeed>100)
			this.hornySpeed = 100;
		
			this.hornySpeed = hornySpeed;
		
	}



	/**
	 * @param r the Zlife's Color red component to set
	 */
	public void setR(int r) {
		if(r>=0 && r<=255)
			R = r;
		if(r<0)
			R=0;
		if(r>255)
			R = 255;
	}
	
	/**
	 * @param radius the radius to set
	 */
	public void setRadius(int radius)
	{
		if(radius<0)
			radius=0;
		
			this.radius=radius;
	}
	


	/**
	 * @param scarySpeed the scarySpeed to set
	 */
	public void setScarySpeed(double scarySpeed) {
		if(scarySpeed<0)
			scarySpeed=0;
		
		if( scarySpeed>100)
			scarySpeed=100;
		
		this.scarySpeed = scarySpeed;
	}
	
	/**
	 * @param p store the seed Position point for this Zlife
	 */
	public void setSeedPosition(Point p)
	{
		seedPosition = p;
	}
	
	/**
	 * @param state the state to set
	 */
	public void setState(CellState state) {
		this.state = state;
		setChanged();
		notifyObservers("ZLife:ChangeState");
	}
	
	/**
	 * Set the Zlife state as SCARY
	 */
	public void setScary()
	{
		state=CellState.SCARY;
		setChanged();
		notifyObservers("ZLife:ChangeState");
	}
	

	/**
	 * @param r the zlifeColor red component to set
	 *  @param g the zlifeColor green component to set
	 *   @param b the zlifeColor blue component to set
	 */
	public void setZlifeColor(int r, int g, int b) {
		setR(r);
		setG(g);
		setB(b);
		zlifeColor = new Color(R,G,B);
	}

	/**
	 * @param zlifeDna the the Zlife's {@link Dna} to set
	 */
	public void setZlifeDna(Dna zlifeDna) {
		ZlifeDna = zlifeDna;
	}
	
	@Override
	public void ThisIsMe(Graphics g2d)
	{
		g2d.setColor( getZlifeColor() );
		g2d.drawOval((int)this.getX() - this.radius , (int)this.getY() - this.radius, this.radius, this.radius);
		
		if(SELECTED)
		{
			g2d.setColor(Color.RED);
			g2d.drawOval((int)this.getX() - this.radius -5 , (int)this.getY() - this.radius - 5,this.radius + 10, this.radius + 10);
			
		}
		
		if(marked)
		{
			g2d.setColor(markedColor);
			g2d.drawRect((int)this.getX() - this.radius -7 , (int)this.getY() - this.radius - 7,this.radius + 14, this.radius + 14);
			
		}
		
		
	}
	
	@Override
	public String toString()
	{
		return "\nZlife name: \t" + getName() +
		"\nPosition: \t[" + (int)this.vector.origin.getX()+","+(int)this.vector.origin.getY()+"]" +
		"\nDirection: \t" + (int)this.vector.getDirectionDegrees()+"°"+
		"\nState: \t" + this.state.toString() +
		"\nSpeed: \t" + this.getSpeed() +
		"\nLifeCycle: \t" + actualLifeCycle + "/" + lifeCycle +
		"\nEnergy: \t" + Math.rint(energy*100)/100 +"/" + Math.rint(maxEnergy*100)/100 +
		"\nRadius: \t" + radius +
		"\nColor: \t["+this.R+" " + this.G +" "+this.B+"]" +
		"\n " + this.ZlifeDna.toString();
		
	}
	
	/**
	 * Set marked to false;
	 */
	public void unmarked()
	{
		marked = false;
	}
	
	/**
	 * COllision action performed when a Zlife is nearby Seed
	 * @param z Zetatron
	 * @param s Seed
	 */
	public void ZlifeEatingSeed(Zlife z, Seed s)
	{
		if (z.isBored())
		{
			z.setSeedPosition( s.getPosition() ) ;
		}
	
		if (z.isHungry())
		{
			s.eatMe();
			z.setEnergy(z.getMaxEnergy());
			z.setAngleRadians(Math.random()*2*Math.PI);
			//cell1.setHorny();
			z.live();
		}
	}

//PRIVATE AND PROTECTED METHODS *********************************************
	

	
	/**
	 * Create a new Zlife loading the default parameter values
	 */
	protected void loadDefaultValues()
	{
		dnaParam=0.2;
		
		energy=70.0;
		
		maxEnergy=100.0;
		
		hornyEnergy=90.0;
		
		hungryEnergy=30.0;
		
		lifeCycle=5;
		
		metabolism=0.002;
		
		BoredSpeed=40;

		riproductionEnergy=20.0;
		
		hungrySpeed=60;
		
		hornySpeed=80;
		
		scarySpeed=90;
		
		radius=10;
		
		ageFactor=0.10;
		
		R=125;
		G=125;
		B=125;
	}
	
	/**
	 * Set the parameter values using a previously created DNA.
	 * Use it if you already have a DNA (for example merging two DNA) and you
	 * want use it to create a new ZLife
	 */
	protected void loadDnaGenes()
	{
		setName( this.ZlifeDna.getName() );
		
		//load private double dnaParam;
		setDnaParam( this.ZlifeDna.getGene("dnaParam").doubleValue()  ); //0
		
		//private double maxEnergy;
		setMaxEnergy( this.ZlifeDna.getGene("maxEnergy").doubleValue()  ); //2
		
		//load private double energy;
		setEnergy( this.ZlifeDna.getGene("energy").doubleValue()  ); //1
		
		
		//load private double hornyEnergy;
		setHornyEnergy( this.ZlifeDna.getGene("hornyEnergy").doubleValue()  ); //3
		
		//load private double hungryEnergy;
		setHungryEnergy( this.ZlifeDna.getGene("hungryEnergy").doubleValue()  ); //4
		
		//load private int lifeCycle;
		setLifeCycle( this.ZlifeDna.getGene("lifeCycle").intValue()  ); //5
		
		//load private double metabolism;
		setMetabolism( this.ZlifeDna.getGene("metabolism").doubleValue()  ); //6
		
		//private double BoredSpeed;
		setBoredSpeed( this.ZlifeDna.getGene("BoredSpeed").doubleValue()  ); //7
		
		//private double riproductionEnergy;
		setRiproductionEnergy( this.ZlifeDna.getGene("riproductionEnergy").doubleValue()  );//8
		
		//private double hungrySpeed;
		setHungrySpeed( this.ZlifeDna.getGene("hungrySpeed").doubleValue()  );//9
		
		//load private double hornySpeed;
		setHornySpeed( this.ZlifeDna.getGene("hornySpeed").doubleValue()  ); //10
		
		//private double scarySpeed;
		setScarySpeed( this.ZlifeDna.getGene("scarySpeed").doubleValue()  ); //11
		
		//load private int R;
		setR( this.ZlifeDna.getGene("R").intValue()  ); //12
		//load private int G;
		setG( this.ZlifeDna.getGene("G").intValue()  ); //13
		//load private int B;
		setB( this.ZlifeDna.getGene("B").intValue()  ); //14
		
		//Load private int radius
		setRadius( this.ZlifeDna.getGene("radius").intValue()  ); //15
		
		//Load private int ageFactor
		setAgeFactor( this.ZlifeDna.getGene("ageFactor").doubleValue()  ); //16
	}

//OVERRIDED METHODS ***************************************************
	

	

}

