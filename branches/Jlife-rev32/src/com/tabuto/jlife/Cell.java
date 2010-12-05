/**
* @author Francesco di Dio
* Date: 17/nov/2010 16.03.41
* Titolo: Cell.java
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
  * Rappresenta una forma di vita elementare.
  * La classe Cell ha diversi stati interni di comportamento:
  * HUNGRY -> va in cerca di cibo: se ha in memoria una posizione di cibo va li, altrimenti cerca
  * HORNY -> va in cerca di partner
  * BORED -> cazzeggia 
  * SCARED -> cambia continuamente direzione
  * 
  * Tutti questi stati sono altamente parametrizzati con variabili contenute nel DNA
  * di modo che è possibile osservare variazioni significative al variare delle generazioni
  * 
  * Ogni singolo movimento,e le diverse azioni consumano energie allo sprite, 
  * ogni sprite ha un tot numero di energie totali da spendere, finite le quali muore.
  * 
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
 * Use Jenetic2.0
 * 
 * @author tabuto83
 * 
 * @version 0.1.5
 * 
 * @see Gene
 * @see Dna
 * 
 * @deprecated
 */
public class Cell extends Sprite implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4799690079392608540L;

	

	private Dna CellDna;
	
	//GENETIC PARAMETERS
	
	private double energy=50; 
	
	private int mass=1;
	
	private int lifeCycle=20;
	
	private int ageFactor=20;
	
	public int radius;
	
	public Color cellColor;
	
	private int R=125,G=125,B=125;
	
	private int activityRatio=30; 
	
	
	
	//Energy spend for every move
	private double metabolism = 0.002;
	//parametro di mutazione genetica
	private double dnaParam = 0.5;
	//Velocità quando annoiato
	private double BoredSpeed = 20.0;
	//Massima energia della cella
	private double maxEnergy = 100.0;
	//energia spesa per la riproduzione
	private double riproductionEnergy = 29.0;
	
	private double hungrySpeed=60.0;
	
	private double hornySpeed=80.0;
	
	
	//STATE FLAG
	private enum CellState  {NULL, HUNGRY, HORNY, BORED, SCARY}
	public CellState state = CellState.BORED;
	private boolean alive=true;
	private boolean SELECTED = false;
	
	//ACTUALS PARAMETERS (NON - GENETICS)
	private int ActualLifeCycle=0;
	private Point seedPosition = new Point();
	private String Name ="";
	
	
	public Cell(Dimension d, double posX, double posY, int r)
	
	{   super(d,posX,posY);
		this.radius = r;
		
		 CellDna=new Dna();
		 
		
		 this.loadToDna();
		
		 
		 this.cellColor=new Color(R,G,B);
		 this.setMaxOffset(r, r, r, r);
		
	}
	
	public Cell(Dimension d,double x, double y,Dna newDna)
	{
		
	    this(  d ,
	    		x,
	    		y,
	    		newDna.getGene("RADIUS").intValue() );
	    
	    this.CellDna = newDna;
	    
	    this.setDna();
		
	    this.setColor(this.R,this.G,this.B);
		this.setMaxOffset(this.radius,this.radius,this.radius, this.radius);
	    
	}
	
	public void setDna()
	{
		
	    this.radius = this.CellDna.getGene("RADIUS").intValue(); //2
	    this.alive = this.CellDna.getGene("isALIVE").booleanValue(); //3
	    this.R = this.CellDna.getGene("Red").intValue(); //4
	    this.G = this.CellDna.getGene("Green").intValue(); //5
	    this.B = this.CellDna.getGene("Blue").intValue(); //6
	    this.ageFactor = this.CellDna.getGene("AGE").intValue(); //7
	    this.energy = this.CellDna.getGene("ENERGY").doubleValue(); //8
	    this.mass = this.CellDna.getGene("MASS").intValue(); //9
	    this.lifeCycle = this.CellDna.getGene("LIFE").intValue(); //10
	    this.activityRatio = this.CellDna.getGene("A-RATIO").intValue();//11
	    this.metabolism = this.CellDna.getGene("METABOLISM").doubleValue();//12
	    this.dnaParam = this.CellDna.getGene("PARAM").doubleValue();//13
	    this.BoredSpeed = this.CellDna.getGene("B-SPEED").doubleValue();//14
	    this.maxEnergy = this.CellDna.getGene("MAXENERGY").doubleValue();//15
	    this.riproductionEnergy = this.CellDna.getGene("R-ENERGY").doubleValue();//16
	    this.hungrySpeed = this.CellDna.getGene("H-SPEED").doubleValue();//17
	    this.hornySpeed = this.CellDna.getGene("HO-SPEED").doubleValue();//18
	    
	    this.CellDna.setParam(this.dnaParam);
	    
	}
	
	
	public void ThisIsMe(Graphics g2d)
	{
		g2d.setColor( this.cellColor );
		g2d.drawOval((int)this.getX() - this.radius , (int)this.getY() - this.radius, this.radius, this.radius);
		
		if(SELECTED)
		{
			g2d.setColor(Color.RED);
			g2d.drawOval((int)this.getX() - this.radius , (int)this.getY() - this.radius, this.radius + 3, this.radius + 3);
		}
	}
	
	
	
	//***************************************************************
	// SETTERS AND GETTERS
	
	public Dimension getDimension(){return this.d;}
	
	public double getEnergy(){return energy;}
	public void setEnergy(double e)
	{
		if(e>=0 && e<=this.maxEnergy)
			this.energy=e;
	}
	
	public void setColor(int r,int g, int b)
	{
		if(r>255) {this.R=255;}
		if(r<0) {this.R=0;}
		
		if(g>255) {this.G=255;}
		if(g<0) {this.G=0;}
		
		if(b>255) {this.B=255;}
		if(b<0) {this.B=0;}
		
		this.cellColor=new Color(this.R,this.G,this.B);
		
	
	}
	
	public Color getColor(){return this.cellColor;}
	
	//MAIN METHOD LIVE *************************************************************
	//This method update the Sprite vector (so the position of the Cell!)
	public void live()
	{
		if (this.isAlive())
		{
			switch (this.state)
			{
				case BORED:
					{
						if(this.energy>this.activityRatio)
							{
							this.setSpeed((int)this.BoredSpeed);
						    break; //this.move();
							}
						else
						{   this.age();
							this.state =  CellState.HUNGRY;
							break;
						}
					}
					
				case HUNGRY:
				{
				  if (this.energy < this.activityRatio)
				  {
					if(this.seedPosition.getX()==0 && this.seedPosition.getY()==0)
						{
							this.setSpeed(  (int)(this.BoredSpeed + this.hungrySpeed)/2 );
					     	//this.move();
						}
					else
						{
							this.setSpeed((int)this.hungrySpeed);
							this.moveTo(this.seedPosition);
							break;
						}
				  }
				  else
				  {
					  this.age();
					  this.state = CellState.BORED;
					  break;
				  }  
					
				}
					
				case HORNY:
				{
					this.setSpeed((int)this.hornySpeed);
					break;
					//this.move();
				}
				
				case SCARY:
				{
					 if (this.energy > this.activityRatio)
					  {
					this.setAngleRadians(this.getAngle() + Math.PI);
					this.setSpeed(100);
					break;
					//this.move();
					  }
					 
					 if (this.energy < this.activityRatio)
					  {
					this.setAngleRadians(this.getAngle() + Math.PI);
					this.setSpeed(50);
					//this.move();
					this.age();
					this.setState(CellState.BORED);
					break;
					  }
					 
				}
		
			}
		}
		
		else
			//DEAD
			this.Deactivate();
	}
	
	
	//this method simulate the age action for the cell
	public void age()
	{
		if(this.ActualLifeCycle<=this.lifeCycle)
		{
			this.energy = this.energy - this.ageFactor;
			this.setColor(this.R - this.ageFactor, 
				      	  this.G + this.ageFactor,
				          this.B );
		}
		else
			this.ActualLifeCycle++;
	}
	
	//Check the life parameter to control if cell can still live
	public boolean isAlive()
	{
		if (this.energy>0 && this.ActualLifeCycle<this.lifeCycle)
			this.alive=true;
		else
			this.alive=false;
		
		return alive;
	}
	
	public void setName(String n)
	{
		this.Name=n;
	}
	
	public String getName(){return this.Name;}
	
	public CellState getState(){return this.state;}
	
	public void setState(CellState cs){state=cs;}
	
	
	public boolean isHungry()
	{
		if (this.state == CellState.HUNGRY)
			return true;
		else
			return false;
	}
	public void setHungry(){this.state = CellState.HUNGRY;}
	
	public boolean isHorny()
	{
		if (this.state == CellState.HORNY)
			return true;
		else
			return false;
	}
	public void setHorny(){this.state = CellState.HORNY;}
	
	public void setScary(){this.state = CellState.SCARY;}
	public boolean isScary()
	{
		if (this.state == CellState.SCARY)
			return true;
		else
			return false;
	}
	
	
	public boolean isBored()
	{
		if (this.state == CellState.BORED)
			return true;
		else
			return false;
	}
	public void setBored(){this.state = CellState.BORED;}
	

	
	public boolean isSelected(){return this.SELECTED;}
	public void setSelect(boolean h){this.SELECTED = h;}
	
	public Dna getDna(){return this.CellDna;}
	
	public void setSeedPosition(Point s){this.seedPosition = s;}
	
	public double getDnaParam(){return this.dnaParam;}
	public void setDnaParam(double p)
	{
		if(p>=0 && p<=1)
			this.dnaParam=p;
	}
	
	public double getBoredSpeed(){return this.BoredSpeed;}
	public void setBoredSpeed(double bs)
	{
		if (bs>=0 && bs<= this.maxEnergy)
			this.BoredSpeed=bs;
	}
	
	public double getMaxEnergy(){return this.maxEnergy;}
	public void setMaxEnergy(double max)
	{
		if (max>0)
			this.maxEnergy=max;
	}
	
	public double getRiproductionEnergy(){return this.riproductionEnergy;}
	public void setRiproductionEnergy(double re)
	{
		if(re>=0 && re<this.maxEnergy)
			this.riproductionEnergy=re;
	}
	
	public double getMetabolism(){return this.metabolism;}
	
	//PRIVATE AND PROTECTED METHODS
	private void loadToDna()
	{
		 CellDna.setName("Cell: " + this.Name + "Dna");
		 CellDna.setParam(this.getDnaParam());
		 
		 
		 CellDna.add((int)this.d.getWidth(),"WIDTH","Cell's Sprite Playfield WIDTH"); //0
		 CellDna.add((int)this.d.getHeight(),"HEIGHT","Cell's Sprite Playfield HEIGHT"); //1
		 CellDna.add(this.radius,"RADIUS","Cell's Sprite Radius"); //2
		 CellDna.add(this.alive,"isALIVE","Cell's Live Status"); //3
		 CellDna.add(this.R,"Red","Cell's Red Component Color");//4
		 CellDna.add(this.G,"Green","Cell's Green Component Color");//5
		 CellDna.add(this.B,"Blue","Cell's Blue Component Color");//6
		 CellDna.add(this.ageFactor,"AGE","Age Factor");//7
		 CellDna.add(this.energy,"ENERGY","Energy quantity");//8
		 CellDna.add(this.mass,"MASS","Cell's Mass");//9
		 CellDna.add(this.lifeCycle,"LIFE","Life Number Cycle");//10
		 CellDna.add(this.activityRatio,"A-RATIO","Activity Ratio Parameter");//11
		 CellDna.add(this.getMetabolism(),"METABOLSIM","Energy spend every move");//12
		 CellDna.add(this.getDnaParam(),"PARAM","DnaParam");//13
		 CellDna.add(this.getBoredSpeed(),"B-SPEED","Speed in bored state");//14
		 CellDna.add(this.getBoredSpeed(),"MAXENERGY","Max energy level");//15
		 CellDna.add(this.getRiproductionEnergy(),"R-ENERGY","Energy spend to riproduction");//16
		 CellDna.add(this.hungrySpeed,"H-SPEED","Speed in hungry state");//17
		 CellDna.add(this.hornySpeed,"HO-SPEED","Speed in horny state");//18
		 
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
  		  this.energy = (this.energy - this.metabolism);
  		  this.live();
  		
  	  }
	}
	
	
	public String toString()
	{
		return "\nCell name:" + this.Name +
				"\nPosition: " + this.vector.origin.toString()+
				"\nDirection: " + this.vector.getDirectionDegrees()+"°"+
				"\nActual State: " + this.state.toString() +
				"\nRadius: " + this.radius +
				"\nColor: ["+this.R+" " + this.G +" "+this.B+"]";
	}

}



