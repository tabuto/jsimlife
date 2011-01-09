/**
* @author Francesco di Dio
* Date: 24/Dic/2010 16.03.41
* Titolo: Seed.java
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
import java.util.Vector;

import com.tabuto.j2dgf.Sprite;
import com.tabuto.util.Point;

/**
 * Class that represents some food. Every speed as a radius and a density.
 * The seeds quantity is equals to Density parameter.
 * @author tabuto83
 * @see Sprite
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
	 * Build  new seed 
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
	 * Perform eat action removing a point in the seeds vector
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
	 * @return the actual seeds number
	 */
	public int getQuantity()
	{
		return seeds.size();
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

