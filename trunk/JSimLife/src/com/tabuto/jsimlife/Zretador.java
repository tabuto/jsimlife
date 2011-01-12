/**
* @author Francesco di Dio
* Date: 01/dic/2010 10.04.53
* Titolo: Zretador.java
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


/**
 * Class {@code Zretador} represent an evolution of ZLife able to eat other Zlifes.
 * <p>
 * Use Jenetic2.1
 * Use J2DGF v.0.7.0
 * 
 * @author tabuto83
 * 
 * @version 0.2.0
 * 
 * @see Zlife
 * @see Gene
 * @see Dna
 * @see Sprite
 */
public class Zretador extends Zlife implements Serializable{
	

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3656428284823615107L;


	public boolean attack = false;
	public Zlife prey;
	public double distance = 200;

	public Color sight = Color.DARK_GRAY;

	/**
	 * Make a new Zlife using the {@link Dna} passed him as parameter.
	 * @param d Dimension of the Game playfield
	 * @param x double Coordinate
	 * @param y double coordinate
	 * @param zlifeDna Zlife's Dna
	 * @see Dna
	 */
	public Zretador(Dimension d, double x, double y, Dna zlifeDna)
	{
		super(d,x,y,zlifeDna);
	}
	

	/**
	 * @return String short description of Zretador class
	 */
	public static String getDescription()
	{
		return "Zretador is a simple simulation of a Zlifes predators. It looks" +
				"likes a Zlifes but its meal is Zlifes. When a hungry Zretador meet" +
				"a ZLife, it steals some energy until its hunger estingueshed." +
				"\nFor other aspect Zretador live its life exately like a Zlifes!";
	}
	
	/**
	 * @see Zlife#live()
	 */
	@Override
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
						setSpeed((int) getBoredSpeed()+1);
						break; //this.move();
					}
					
					if(energy<hungryEnergy)
					{   age();
						setSpeed((int) getHungrySpeed()+2);
						setState(CellState.HUNGRY);
						this.setAngleRadians(Math.random()*Math.PI*2);
						break;
					}
					
					if(energy>hornyEnergy)
					{
						age();
						setSpeed((int) getHornySpeed()+3);
						setState(CellState.HORNY);
						this.setAngleRadians(Math.random()*Math.PI*2);
						break;
					}
					
				}
			case HUNGRY:
				{
					this.setSpeed((int)hungrySpeed + 4);
					
					if (energy < hungryEnergy)
					{
							if(prey instanceof Zlife )
							{	
								if ( distance < prey.getPosition().getDistance(this.getPosition()) )
										{
									distance = prey.getPosition().getDistance(this.getPosition());
									moveTo(prey);
									
										}
								distance = prey.getPosition().getDistance(this.getPosition());
								attack=true;
								
								if (!prey.isAlive() || distance>200)
								{
									setPrey(null);
								}
								
								break;
							}
							
								
						
							
							
						break;
					}	
					
					if (energy > hungryEnergy)
					  {
						  setPrey(null);
						  //this.age();
						  this.setSpeed((int)getBoredSpeed()+5);
						  setState(CellState.BORED);
						  this.setAngleRadians(Math.random()*Math.PI*2);
						  break;
					  }  
					
				}
			case HORNY:
				{
					this.setSpeed((int)getHornySpeed());
					if(energy>hornyEnergy)
						{
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
						//age();
						 this.setSpeed((int)getHungrySpeed()+6);
						setState(CellState.HUNGRY);
						this.setAngleRadians(Math.random()*Math.PI*2);
						break;
					}
				}
				
			case SCARY:
				{   
					    this.setSpeed((int)getScarySpeed());
					    
					    if(energy<hungryEnergy)
						{
							age();
							 this.setSpeed((int)getHungrySpeed()+6);
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
  		  
		if(prey==null)
  		  energy = (energy - realMetabolism - (getSpeed()/100));
		if(prey!=null)
			 energy = (energy + realMetabolism + (getSpeed()/100));
		if(isScary())
			energy = (energy - realMetabolism*2 - (getSpeed()/50));
		
  		  if(energy<=0 || actualLifeCycle>lifeCycle)
  			 {setAlive(false);Deactivate();}
  		  else
  			  this.live();
  		
  	  }
	}
	
	/**
	 * Return a new Zretador with merged DNA
	 * @param z Other Zretador
	 * @return new Zretador
	 */
	public Zretador reproduction(Zretador z)
	{
		Dna newBornDna= Dna.merge(z.getZlifeDna(), this.getZlifeDna());
		
		double tempAngle = this.getAngle();
		this.setAngleRadians(z.getAngle());
		z.setAngleRadians(tempAngle);
		Zretador newCell = new Zretador(z.getDimension(), z.getX(),z.getY(),newBornDna);
		newCell.setAngleRadians( Math.random() * 2 * Math.PI );
		newCell.setEnergy( newCell.getHungryEnergy()*0.9); 
		z.setEnergy( z.getEnergy() - z.getRiproductionEnergy());
		this.setEnergy(this.getEnergy() - this.getRiproductionEnergy());
		newCell.setGenerationNumber( Math.max(z.getGenerationNumber(), this.getGenerationNumber())+1);
		
		return newCell;
	}
	
	/**
	 * Set the current prey for this Zretador
	 * @param z
	 */
	public void setPrey(Zlife z)
	{
		//If prey is not null update distance and set attack true
		if(z instanceof Zlife)
		{
			prey = z;
			distance = prey.getPosition().getDistance(this.getPosition());
			attack=true;
		}
		else 
			//IF not set prey null and attack false
		{
			prey = null;
			distance = 200;
			attack = false;
		}
	}
	
	@Override
	public void ThisIsMe(Graphics g2d) 
	{
		g2d.setColor( getZlifeColor() );
		g2d.fillRect((int)this.getX() - this.radius , (int)this.getY() - this.radius, this.radius, this.radius);
		
		if(SELECTED)
			//DRAW A RED CIRCLE
		{
			g2d.setColor(Color.RED);
			g2d.drawOval((int)this.getX() - this.radius -5 , (int)this.getY() - this.radius - 5,this.radius + 10, this.radius + 10);
			
		}
		
		if (attack)
		{  
			//DRAW A LINE from thiz zretador to zlife prey
			g2d.setColor(getZlifeColor());
			g2d.drawLine((int)getX(), (int)getY(), (int)prey.getX(), (int)prey.getY());
		}
		
		if(marked)
		{
			// DRAW a colored rectangle with choosed color
			g2d.setColor(markedColor);
			g2d.drawRect((int)this.getX() - this.radius -7 , (int)this.getY() - this.radius - 7,this.radius + 14, this.radius + 14);
			
		}
		
	}
	
	/**
	 * Collision Action performed when a Zlife is nearby Zretador
	 * @param z Zretador
	 * @param c Zlife
	 */
	public void ZretadorEatingZlife(Zretador z, Zlife c)
	{

		if(z.isHungry())
		{
			
			if (z.prey == null)
			{
				z.setPrey(c);
				if(c.getEnergy()>c.getMaxEnergy()*0.10)
					c.setScary();
				z.moveTo(c);
			}
			

		
			if( z.getPosition().getDistance( c.getPosition())< 20 )
			{
				z.setPrey(c);
				if(c.getEnergy()>c.getMaxEnergy()*0.10)
					c.setScary();
				z.moveTo(c);
				
			}
			
				if( z.getPosition().getDistance( c.getPosition())< (z.getRadius()+c.getRadius()) )
				{
					//z.setPrey(c);
					z.setEnergy( z.getMaxEnergy() );
					z.setAngleRadians(Math.random()*2*Math.PI);
					//Zlife hit but not necessary die
					c.setEnergy( c.getEnergy()-( (z.getRadius()+1)*10) );
					z.setPrey(null);
				}
				
				z.live();
				c.live();
		}
	}

}

