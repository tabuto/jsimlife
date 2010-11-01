package com.tabuto.jlife;

import java.awt.Dimension;
import java.awt.Graphics2D;

import com.tabuto.j2dgf.Sprite;
import com.tabuto.jenetic.Dna;

public class Cell extends Sprite
{

	private Dna CellDna;
	
	private int energy=50; 
	
	private int mass=1;
	
	private int lifeCycle=20;
	
	private boolean alive=true;
	
	public Sprite cellSprite;
	
	public int radius;
	
	
	
	public Cell(Dimension d, double posX, double posY, int r)
	
	{   super(d,posX,posY);
		CellDna=new Dna();
		

		 CellDna.add(this.d.getWidth(),"Cell's Sprite Playfield WIDTH"); //0
		 CellDna.add(this.d.getHeight(),"Cell's Sprite Playfield HEIGHT"); //1
		 CellDna.add(posX,"Cell's Sprite X Position"); //2
		 CellDna.add(posY,"Cell's Sprite Y Position"); //3
		 CellDna.add(r,"Cell's Sprite Radius"); //4
		 CellDna.add(alive,"Cell's Live Status"); //4
		 radius = r;
	}
	
	public Cell(Dna newDna)
	{
		
	    this(  new Dimension( (int )newDna.getGeneDouble(0), (int) newDna.getGeneDouble(1) ),
	    		newDna.getGeneDouble(2),
	    		newDna.getGeneDouble(3),
	    		(int) newDna.getGeneDouble(4) );
	    
	    this.CellDna = newDna;
	    
	    
	}
	
	
	public void ThisIsMe(Graphics2D g2d)
	{
		g2d.drawOval((int)this.getX() - this.radius , (int)this.getY() - this.radius, this.radius, this.radius);
		
	}
	
	
	
	//***************************************************************
	// SETETRS AND GETTERS
	
	public int getEnergy(){return energy;}
	public void setEnergy(int e)
	{
		if(e>=0 && e<=100)
			this.energy=e;
	}
	
	
	
	
}



