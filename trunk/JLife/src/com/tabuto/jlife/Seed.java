/**
* @author Francesco di Dio
* Date: 07/nov/2010 16.03.41
* Titolo: Seed.java
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
  * Rappresenta un pò di cibo
  */

package com.tabuto.jlife;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Vector;

import com.tabuto.j2dgf.Sprite;
import com.tabuto.util.Point;


public class Seed extends Sprite{
	
	Vector<Point> seeds = new Vector<Point>();
	private int R;
	private int D;
	private Color seedColor;
	
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
	
	public void ThisIsMe(Graphics g)
	{
		g.setColor(seedColor);
		for(int i=0;i<this.seeds.size();i++)
		 g.drawRect((int) seeds.get(i).getX(),(int) seeds.get(i).getY(), 1, 1);
	}
	
	public void setRadius(int r)
	{
		if(r>0 && r < 50)
			this.R = r;
		else
			this.R = 25;
	}
	
	public void setDensity(double d)
	{
		if(d>0 && d < 100)
			this.D=(int) (d/2);
		else
			this.D=50;
	}
	
	public void setMe()
	{
		int xmin = (int) this.getX() - this.R /2;
		
		int ymin = (int) this.getY() - this.R/2;
		
		for(int i=0;i<this.D;i++)
		{
			seeds.add( new Point(xmin + Math.random()* (this.R) ,ymin + Math.random()*(this.R)));
		}
	}
	
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
	

}
