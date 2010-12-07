/**
* @author Francesco di Dio
* Date: 01/dic/2010 10.04.53
* Titolo: Zretador.java
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
  * Inserisci qui breve descrizione in italiano della classe
  */

package com.tabuto.jlife;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.io.Serializable;
import com.tabuto.jenetic.Dna;



public class Zretador extends Zlife implements Serializable{
	

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3656428284823615107L;


	public boolean attack = false;
	public Zlife preda;


	public Zretador(Dimension d, double x, double y, Dna zlifeDna)
	{
		super(d,x,y,zlifeDna);
	}
	
	public Zretador(Dimension d, double x, double y, int r)
	{
		super(d,x,y,r);
	}

	
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
						state =  CellState.HUNGRY;
						this.setAngleRadians(Math.random()*Math.PI*2);
						break;
					}
					
					if(energy>hornyEnergy)
					{
						age();
						setSpeed((int) getHornySpeed()+3);
						state =  CellState.HORNY;
						this.setAngleRadians(Math.random()*Math.PI*2);
						break;
					}
					
				}
			case HUNGRY:
				{
					this.setSpeed((int)hungrySpeed + 4);
					
					if (energy < hungryEnergy)
						{
								break;
						}
					
					if (energy > hungryEnergy)
					  {
						  this.age();
						  this.setSpeed((int)getBoredSpeed()+5);
						  state = CellState.BORED;
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
						state = CellState.BORED;
						this.setAngleRadians(Math.random()*Math.PI*2);
						break;
					}
					if(energy<hungryEnergy)
					{
						age();
						 this.setSpeed((int)getHungrySpeed()+6);
						state = CellState.HUNGRY;
						this.setAngleRadians(Math.random()*Math.PI*2);
						break;
					}
				}
				
			case SCARY:
				{   
				
						state = CellState.BORED;
						break;
				
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
	public void ThisIsMe(Graphics g2d) 
	{
		g2d.setColor( getZlifeColor() );
		g2d.fillRect((int)this.getX() - this.radius , (int)this.getY() - this.radius, this.radius, this.radius);
		
		if(SELECTED)
		{
			g2d.setColor(Color.RED);
			g2d.drawOval((int)this.getX() - this.radius -5 , (int)this.getY() - this.radius - 5,this.radius + 10, this.radius + 10);
			
		}
		
	}

}
