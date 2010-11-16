/**
* @author Francesco di Dio
* Date: 11/nov/2010 16.03.41
* Titolo: Cell.java
* Versione: 0.1.2 Rev.a:
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
 * @version 0.1.2
 * 
 * @see Gene
 * @see Dna
 */
public class Cell extends Sprite implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4799690079392608540L;

	private enum CellState  {NULL, HUNGRY, HORNY, BORED, SCARY}

	private Dna CellDna;
	
	//GENETIC PARAMETERS
	
    
	
	private int energy=50; 
	
	private int mass=1;
	
	private int lifeCycle=20;
	
	private int ageFactor=20;
	
	public int radius;
	
	public Color cellColor;
	
	private int R=255,G=255,B=255;
	
	private int activityRatio=30;
	
	private double metabolism=0.005;
	
	//STATE FLAG
	private boolean RIPRODUCTION = false;
	private boolean HUNGRY = false;
	private boolean alive=true;
	private boolean SELECTED = false;
	
	//ACTUALS PARAMETERS (NON - GENETICS)
	private int ActualLifeCycle=0;
	private Point seedPosition = new Point();
	private String Name ="";
	
	
	public Cell(Dimension d, double posX, double posY, int r)
	
	{   super(d,posX,posY);
		CellDna=new Dna();
		
		 CellDna.add(this.d.getWidth(),"WIDTH","Cell's Sprite Playfield WIDTH"); //0
		 CellDna.add(this.d.getHeight(),"HEIGHT","Cell's Sprite Playfield HEIGHT"); //1
		 //CellDna.add(posX,"X","Cell's Sprite X Position"); //2
		 //CellDna.add(posY,"Y","Cell's Sprite Y Position"); //3
		 CellDna.add(r,"RADIUS","Cell's Sprite Radius"); //2
		 CellDna.add(alive,"isALIVE","Cell's Live Status"); //3
		 CellDna.add(this.R,"Red","Cell's Red Component Color");//4
		 CellDna.add(this.G,"Green","Cell's Green Component Color");//5
		 CellDna.add(this.B,"Blue","Cell's Blue Component Color");//6
		 CellDna.add(this.ageFactor,"AGE","Age Factor");//7
		 CellDna.add(this.energy,"ENERGY","Energy quantity");//8
		 CellDna.add(this.mass,"MASS","Cell's Mass");//9
		 CellDna.add(this.lifeCycle,"LIFE","Life Number Cycle");//10
		 CellDna.add(this.activityRatio,"A-RATIO","Activity Ratio Parameter");//11
		
		 
		 this.radius = r;
		 this.cellColor=new Color(R,G,B);
		 this.setMaxOffset(r, r, r, r);
	}
	
	public Cell(double x, double y,Dna newDna)
	{
		
	    this(  new Dimension( (newDna.getGene("WIDTH").intValue()), newDna.getGene("HEIGHT").intValue() ),
	    		x,
	    		y,
	    		newDna.getGene("R").intValue() );
	    
	    this.CellDna = newDna;
	    
	    this.R = this.CellDna.getGene("Red").intValue();
	    this.G = this.CellDna.getGene("Green").intValue();
	    this.B = this.CellDna.getGene("Blue").intValue();
	    
	    this.mass = this.CellDna.getGene("MASS").intValue();
	    this.lifeCycle = this.CellDna.getGene("LIFE").intValue();
	    this.ageFactor = this.CellDna.getGene("AGE").intValue();
	    this.energy = this.CellDna.getGene("ENERGY").intValue();
	    this.activityRatio = this.CellDna.getGene("A-RATIO").intValue();
	     
		this.cellColor=new Color(this.R,this.G,this.B);
		this.setMaxOffset(this.radius,this.radius,this.radius, this.radius);
	    
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
	
	public int getEnergy(){return energy;}
	public void setEnergy(int e)
	{
		if(e>=0 && e<=100)
			this.energy=e;
	}
	
	public void setColor(int r,int g, int b)
	{
		this.cellColor=new Color(r,g,b);
		
	
	}
	
	public Color getColor(){return this.cellColor;}
	
	//This method update the Sprite vector (so the position of the Cell!)
	public void live()
	{
		if (this.isAlive())
		{
			if (energy>this.activityRatio)
					{
					  RIPRODUCTION=true;
					  HUNGRY=false;
					}
			else
					{
					RIPRODUCTION=false;
					HUNGRY = true;
					}
			
			if (energy<this.activityRatio)
				{
				  RIPRODUCTION=false;
				  HUNGRY=true;
				}
			else
				{
				RIPRODUCTION=true;
				HUNGRY = false;
				}
			
			if(HUNGRY)
			  this.moveTo(seedPosition.getX(),seedPosition.getY());
			else
				this.move();
			
		}
		
		else
			//DEAD
			this.Deactivate();
	}
	
	
	//this method simulate the age action for the cell
	public void age()
	{
		while(this.ActualLifeCycle<=this.lifeCycle)
		{
			this.energy = this.energy - this.ageFactor;
			this.setColor(this.R - this.ageFactor, 
				      	  this.G - this.ageFactor,
				          this.B - this.ageFactor);
		}
		this.ActualLifeCycle++;
	}
	
	//Check the life parameter to control if cell can still live
	public boolean isAlive()
	{
		if (this.energy>0 && this.ActualLifeCycle<this.lifeCycle)
			this.alive=false;
		else
			this.alive=true;
		
		return alive;
	}
	
	public void setName(String n)
	{
		this.Name=n;
	}
	
	public String getName(){return this.Name;}
	
	public boolean isHungry(){return this.HUNGRY;}
	public void setHangry(boolean h){this.HUNGRY = h;}
	
	public boolean isRiproduction(){return this.RIPRODUCTION;}
	public void setRiproduction(boolean r){this.RIPRODUCTION = r;}
	
	public boolean isSelected(){return this.SELECTED;}
	public void setSelect(boolean h){this.SELECTED = h;}
	
	public Dna getDna(){return this.CellDna;}
	
	
	
}



