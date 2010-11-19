/**
* @author Francesco di Dio
* Date: 17/nov/2010 23.39.33
* Titolo: Zlife.java
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
  * Inserisci qui breve descrizione in italiano della classe
  */

package com.tabuto.jlife;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.io.Serializable;

import com.tabuto.j2dgf.Sprite;
import com.tabuto.jenetic.Dna;
import com.tabuto.jenetic.Gene;
import com.tabuto.util.Point;



/**
 * Class {@code Cell} represent an elementary life's form able to reproduce itself
 * using a {@link Dna} to transmit its Genetic Code!
 * Cell life is very simple, it move in search of food, because moving equals spend energy
 * When its energy is less than a {@link Cell#activityRatio} parameter, it can eat.
 * When its energy is more than a {@link Cell#activityRatio}, it's able to reproduce 
 * itself if it collides by another Cell.
 * <p>
 * Use Jenetic2.1
 * 
 * @author tabuto83
 * 
 * @version 0.1.5
 * 
 * @see Gene
 * @see Dna
 */
public class Zlife extends Sprite implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1705433665252708243L;
	
	private Dna ZlifeDna;
	
	private double dnaParam;
	
	private double energy;
	
	private double maxEnergy;
	
	private double hornyEnergy;
	
	private double hungryEnergy;
	
	private int lifeCycle;
	
	private double metabolism;
	
	private double BoredSpeed;

	private double riproductionEnergy;
	
	private double hungrySpeed;
	
	private double hornySpeed;
	
	private double scarySpeed;
	
	private int radius;
	
	private int R;
	private int G;
	private int B;
	
	//ACTUAL-PARAMETER: NO-DNA
	private enum CellState  {NULL, HUNGRY, HORNY, BORED, SCARY}
	public CellState state = CellState.BORED;
	private boolean alive=true;
	private boolean SELECTED = false;
	private Color zlifeColor;
	private int actualLifeCycle=0; //the actual age of Zlife
	private Point seedPosition = new Point();
	
	/**
	 * @return the Zlife's {@link Dna}
	 */
	public Dna getZlifeDna() {
		return ZlifeDna;
	}

	/**
	 * @param zlifeDna the the Zlife's {@link Dna} to set
	 */
	public void setZlifeDna(Dna zlifeDna) {
		ZlifeDna = zlifeDna;
	}

	/**
	 * @return the dnaParam
	 * @see {@link Dna#getParam()}
	 */
	public double getDnaParam() {
		return dnaParam;
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
	 * @return the energy
	 */
	public double getEnergy() {
		return energy;
	}

	/**
	 * @param energy the energy to set
	 */
	public void setEnergy(double energy) 
	{
		if(energy>=0 && energy<= this.getMaxEnergy() )
			this.energy = energy;
	}

	/**
	 * @return the maxEnergy
	 */
	public double getMaxEnergy() {
		return maxEnergy;
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
	 * @return the hornyEnergy
	 */
	public double getHornyEnergy() {
		return hornyEnergy;
	}

	/**
	 * @param hornyEnergy the hornyEnergy to set
	 */
	public void setHornyEnergy(double hornyEnergy) {
		if(hornyEnergy>=0 && hornyEnergy<= this.getMaxEnergy() )
		this.hornyEnergy = hornyEnergy;
	}

	/**
	 * @return the hungryEnergy
	 */
	public double getHungryEnergy() {
		return hungryEnergy;
	}

	/**
	 * @param hungryEnergy the hungryEnergy to set
	 */
	public void setHungryEnergy(double hungryEnergy) {
		if(hungryEnergy>=0 && hungryEnergy<= this.getMaxEnergy() )
		this.hungryEnergy = hungryEnergy;
	}

	/**
	 * @return the lifeCycle
	 */
	public int getLifeCycle() {
		return lifeCycle;
	}

	/**
	 * @param lifeCycle the lifeCycle to set
	 */
	public void setLifeCycle(int lifeCycle) {
		if(lifeCycle>0)
		this.lifeCycle = lifeCycle;
	}

	/**
	 * @return the metabolism
	 */
	public double getMetabolism() {
		return metabolism;
	}

	/**
	 * @param metabolism the metabolism to set
	 */
	public void setMetabolism(double metabolism) {
		if(metabolism>0 && metabolism<this.maxEnergy)
		this.metabolism = metabolism;
	}

	/**
	 * @return the boredSpeed
	 */
	public double getBoredSpeed() {
		return BoredSpeed;
	}

	/**
	 * @param boredSpeed the boredSpeed to set
	 */
	public void setBoredSpeed(double boredSpeed) {
		if(boredSpeed>0 && boredSpeed<=100)
		BoredSpeed = boredSpeed;
	}

	/**
	 * @return the riproductionEnergy
	 */
	public double getRiproductionEnergy() {
		return riproductionEnergy;
	}

	/**
	 * @param riproductionEnergy the riproductionEnergy to set
	 */
	public void setRiproductionEnergy(double riproductionEnergy) {
		if(riproductionEnergy>0 && riproductionEnergy<this.maxEnergy)
		this.riproductionEnergy = riproductionEnergy;
	}

	/**
	 * @return the hungrySpeed
	 */
	public double getHungrySpeed() {
		return hungrySpeed;
	}

	/**
	 * @param hungrySpeed the hungrySpeed to set
	 */
	public void setHungrySpeed(double hungrySpeed) {
		if(hungrySpeed>0 && hungrySpeed<=100)
		this.hungrySpeed = hungrySpeed;
	}

	/**
	 * @return the hornySpeed
	 */
	public double getHornySpeed() {
		return hornySpeed;
	}

	/**
	 * @param hornySpeed the hornySpeed to set
	 */
	public void setHornySpeed(double hornySpeed) {
		if(hornySpeed>0 && hornySpeed<=100)
		this.hornySpeed = hornySpeed;
	}

	/**
	 * @return the scarySpeed
	 */
	public double getScarySpeed() {
		return scarySpeed;
	}

	/**
	 * @param scarySpeed the scarySpeed to set
	 */
	public void setScarySpeed(double scarySpeed) {
		if(scarySpeed>0 && scarySpeed<=100)
		this.scarySpeed = scarySpeed;
	}
	
	public void setSeedPosition(Point p)
	{
		seedPosition = p;
	}
	
	public Point getSeedPosition()
	{
		return seedPosition;
	}

	/**
	 * @return the r
	 */
	public int getR() {
		return R;
	}

	/**
	 * @param r the r to set
	 */
	public void setR(int r) {
		if(r>=0 && r<=255)
			R = r;
		else
			R = Math.abs(r) % 255;
	}

	/**
	 * @return the radius
	 */
	public int getRadius(){
		return radius;
	}
	
	/**
	 * @param radius the radius to set
	 */
	public void setRadius(int radius)
	{
		if(radius>0)
			this.radius=radius;
	}
	
	/**
	 * @return the g
	 */
	public int getG() {
	return G;
	}

	/**
	 * @param g the g to set
	 */
	public void setG(int g) {
		if(g>=0 && g<=255)
			G = g;
		else
			G = Math.abs(g) % 255;
	}

	/**
	 * @return the b
	 */
	public int getB() {
		return B;
	}

	/**
	 * @param b the b to set
	 */
	public void setB(int b) {
		if(b>=0 && b<=255)
			B = b;
		else
			B = Math.abs(b) % 255;
	}

	/**
	 * @return the state
	 */
	public CellState getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(CellState state) {
		this.state = state;
	}

	/**
	 * @return the alive
	 */
	public boolean isAlive() {
		return alive;
	}
	
	public boolean isHorny()
	{
		 if (state==CellState.HORNY)
			 return true;
			 else
				 return false;
	}
	
	public void setHorny()
	{
		state=CellState.HORNY;
	}

	public boolean isHungry()
	{
		 if (state==CellState.HUNGRY)
			 return true;
			 else
				 return false;
	}
	
	public void setHungry()
	{
		state=CellState.HUNGRY;
	}
	
	public boolean isBored()
	{
		 if (state==CellState.BORED)
			 return true;
			 else
				 return false;
	}
	
	public void setBored()
	{
		state=CellState.BORED;
	}
	
	public boolean isScary()
	{
		 if (state==CellState.SCARY)
			 return true;
			 else
				 return false;
	}
	
	public void setScary()
	{
		state=CellState.SCARY;
	}
	
	/**
	 * @param alive the alive to set
	 */
	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	/**
	 * @return the zlifeColor
	 */
	public Color getZlifeColor() {
		return zlifeColor;
	}

	/**
	 * @param zlifeColor the zlifeColor to set
	 */
	public void setZlifeColor(int r, int g, int b) {
		setR(r);
		setG(g);
		setB(b);
		zlifeColor = new Color(R,G,B);
	}

	//CONSTRUCTOR
	
	public Zlife(Dimension d, double x, double y, Dna zlifeDna)
	{
		super(d,x,y);
		this.setZlifeDna(zlifeDna);
		loadDnaGenes();
		
		setAlive(true);
		setZlifeColor(R,G,B);
		setMaxOffset(getRadius(), getRadius(), getRadius(), getRadius());
		setState(CellState.BORED);
		
	}
	
	public Zlife(Dimension d, double x, double y, int r)
	{
		super(d,x,y);
		loadDefaultValues();
		setRadius(r);
		setMaxOffset(getRadius(), getRadius(), getRadius(), getRadius());
		
		setAlive(true);
		setZlifeColor(R,G,B);
		setState(CellState.BORED);
		ZlifeDna = new Dna();
		makeMyDna();
		
	}
	
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
						setSpeed((int) BoredSpeed);
						break; //this.move();
					}
					
					if(energy<hungryEnergy)
					{   age();
						state =  CellState.HUNGRY;
						break;
					}
					
					if(energy>hornyEnergy)
					{
						age();
						state =  CellState.HORNY;
						break;
					}
					
					
				}
			case HUNGRY:
				{
					if (energy < hungryEnergy)
					  {
						if(seedPosition.getX()==0 && seedPosition.getY()==0)
							{
								this.setSpeed(  (int)(BoredSpeed + hungrySpeed)/2 );
							}
						else
							{
								this.setSpeed((int)hungrySpeed);
								this.moveTo(getSeedPosition());
								break;
							}
					  }
					  else
					  {
						  this.age();
						  state = CellState.BORED;
						  break;
					  }  
				}
			case HORNY:
				{
					//TODO: try to riproduce until energy is below a certain value
					this.setSpeed((int)hornySpeed);
					break;
				}
				
			case SCARY:
				{
					//TODO: write a scary state
				}
			}
		}
		else
			setAlive(false);
	}
	
	public void age()
	{
		if(actualLifeCycle<=lifeCycle)
		{
			setMaxEnergy( getMaxEnergy() - getMaxEnergy()*0.10);
			this.setZlifeColor(this.R + (int)(10 - Math.random()*20), 
				      	  this.G + (int)(10 - Math.random()*20),
				          this.B + (int)(10 - Math.random()*20));
			actualLifeCycle++;
		}
		else
			setAlive(false);
	}
	
	private void makeMyDna()
	{
		this.ZlifeDna.add(getDnaParam(), "dnaParam", "The Dna fuzzy parameter");
		this.ZlifeDna.add(getEnergy(), "energy", "energy's zlife");
		this.ZlifeDna.add(getMaxEnergy(), "maxEnergy", "The max energy avaible for zlife");
		this.ZlifeDna.add(getHornyEnergy(), "hornyEnergy", "The energy needs to riproduction");
		this.ZlifeDna.add(getHungryEnergy(), "hungryEnergy", "The energy because I'm hungry");
		this.ZlifeDna.add(getLifeCycle(), "lifeCycle", "The number of lifeCycle before die");
		this.ZlifeDna.add(getMetabolism(), "metabolism", "The energy for every move");
		this.ZlifeDna.add(getBoredSpeed(), "BoredSpeed", "The speed when i'm bored");
		this.ZlifeDna.add(getRiproductionEnergy(), "riproductionEnergy", "The energy spend for embracing");
		this.ZlifeDna.add(getHungrySpeed(), "hungrySpeed", "Speed when i'm hungry");
		this.ZlifeDna.add(getHornySpeed(), "hornySpeed", "Speed when i'm horny");
		this.ZlifeDna.add(getScarySpeed(), "scarySpeed", "Speed when i'm scary");
		this.ZlifeDna.add(getR(), "R", "Red component of Zlife color");
		this.ZlifeDna.add(getG(), "G", "Green component of Zlife color");
		this.ZlifeDna.add(getB(), "B", "Blue component of Zlife color");
		this.ZlifeDna.add(getRadius(), "radius", "Zlife's radius ");
	}
	
	private void loadDefaultValues()
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
		
		R=125;
		G=125;
		B=125;
	}
	
	private void loadDnaGenes()
	{
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
	}

//OVERRIDED METHODS ***************************************************
	
	@Override
	public void move()
	{
		if ( ACTIVE && this.getSpeed()> 0 )
  	  {  
  	      
  		  double nx = (this.vector.origin.x + this.getMySpeed() * Math.cos(this.vector.getDirectionRadians() ) );
  		  double ny = (this.vector.origin.y + this.getMySpeed() * Math.sin(this.vector.getDirectionRadians()));
  		  this.vector.setNewOrigin(nx,ny);
  		  energy = (energy - metabolism);
  		  this.live();
  		
  	  }
	}
	
	public void ThisIsMe(Graphics g2d)
	{
		g2d.setColor( getZlifeColor() );
		g2d.drawOval((int)this.getX() - this.radius , (int)this.getY() - this.radius, this.radius, this.radius);
		
		if(SELECTED)
		{
			g2d.setColor(Color.RED);
			g2d.drawOval((int)this.getX() - this.radius , (int)this.getY() - this.radius, this.radius + 3, this.radius + 3);
		}
	}
	
}
