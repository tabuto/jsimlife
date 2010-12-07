/**
* @author Francesco di Dio
* Date: 24/nov/2010 16.03.41
* Titolo: Seed.java
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
  * Rappresenta un pò di cibo
  */

package com.tabuto.jlife;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Vector;

import com.tabuto.j2dgf.Sprite;
import com.tabuto.util.Point;

/**
 * Class that represents some food. Every speed as a radius and a density.
 * The seeds quantity is equals to Density parameter.
 * @author tabuto83
 *
 */
public class Seed extends Sprite{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * The vector contains points represents a single seed
	 */
	Vector<Point> seeds = new Vector<Point>();
	
	/**
	 * Seed Radius
	 */
	private int R;
	
	/**
	 * Seed density: the single seed number 
	 */
	private int D;
	
	/**
	 * Color of the seed: default GREEN
	 */
	private Color seedColor;
	
	/**
	 * Max radius value
	 */
	private final int MAX_RADIUS=100;
	
	/**
	 * Max density values
	 */
	private final int MAX_DENSITY=100;
	
	/**
	 * Constructor a new seed 
	 * @param d double, Density or seed's number
	 * @param x int, X Coordinate
	 * @param y int, Y Coordinate
	 * @param radius int, radius or dimension of this Seed
	 * @param density double, the single seed number 
	 */
	public Seed(Dimension d,int x, int y,int radius, double density)
	{
		super(d, x,y);
		setRadius(radius);
		setDensity(density);
		this.setMaxOffset(radius, radius, radius, radius);
		this.setSpeed(0);
		seedColor = new Color(0,255,0);
		setMe();
	}
	
	/**
	 * Perform the eat action removing a point in the seeds vector
	 */
	public void eatMe()
	{
		if(!this.seeds.isEmpty() )
			{ 
			 this.seeds.remove( this.seeds.size() -1);
			 this.seeds.trimToSize();
			}
		else
			this.Deactivate();
		
	}
	
	/**
	 * Set the Density parametes
	 * @param r, int new Seed's Density
	 */
	public void setDensity(double d)
	{
		if(d>0 && d < MAX_DENSITY)
			this.D=(int) (d/2);
		else
			this.D=50;
	}
	
	/**
	 * Fill the seeds vector point with single seed
	 */
	public void setMe()
	{
		int xmin = (int) this.getX() - this.R /2;
		
		int ymin = (int) this.getY() - this.R/2;
		
		for(int i=0;i<this.D;i++)
		{
			seeds.add( new Point(xmin + Math.random()* (this.R) ,ymin + Math.random()*(this.R)));
		}
	}
	
	/**
	 * Set the Radius parametes
	 * @param r, int new Seed's Radius
	 */
	public void setRadius(int r)
	{
		if(r>0 && r < MAX_RADIUS)
			this.R = r;
		else
			this.R = 25;
	}
	
	/**
	 * Overrided ThisIsMe sprite methods
	 */
	@Override
	public void ThisIsMe(Graphics g)
	{
		g.setColor(seedColor);
		for(int i=0;i<this.seeds.size();i++)
		 g.drawRect((int) seeds.get(i).getX(),(int) seeds.get(i).getY(), 1, 1);
	}
	

	
	

	

}
