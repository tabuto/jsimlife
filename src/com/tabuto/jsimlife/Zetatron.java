/**
* @author Francesco di Dio
* Date: 17/dic/2010 17.56.38
* Titolo: Zetatron.java
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

import com.tabuto.jenetic.Dna;


/**
 * Class Zetatron extends ZLife in order to add a neural network (using Brain class)
 * to change its state.
 * 
 * @author tabuto83
 *
 * @version 0.2.0
 * 
 * @see ZetatronBrain
 * @see Zlife
 */
public class Zetatron extends Zlife {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Vector contains the net weights
	 */
	protected Vector<Double> weights = new Vector<Double>();
	
	/**
	 * The Zetatron brain
	 * @see Brain
	 */
	protected ZetatronBrain ZBrain = new ZetatronBrain();
	
	/**
	 * Zetatron's actual aim. If hungry it follows the Zredator "preda"
	 */
	public Zretador preda;

	
	/*
	 * WEIGHTS
	 * H= hidden O= output N= neurons w= weight
	 * so HN2w1 is the 1st weight's connection of 2nd NEURONS into HIDDEN layer
	 */
	private double HN0w0;
	private double HN0w1;
	
	private double HN1w0;
	private double HN1w1;
	
	private double HN2w0;
	private double HN2w1;
	
	private double HN3w0;
	private double HN3w1;
	
	private double HN4w0;
	private double HN4w1;
	
	private double ON0w0;
	private double ON0w1;
	private double ON0w2;
	private double ON0w3;
	private double ON0w4;
	
	private double ON1w0;
	private double ON1w1;
	private double ON1w2;
	private double ON1w3;
	private double ON1w4;
	
	private double ON2w0;
	private double ON2w1;
	private double ON2w2;
	private double ON2w3;
	private double ON2w4;
	
	/**
	 * Make a new Zetatron using the {@link Dna} passed him as parameter.
	 * @param d Dimension of the Game playfield
	 * @param x double Coordinate
	 * @param y double coordinate
	 * @param zlifeDna Zlife's Dna
	 * @see Dna
	 */
	public Zetatron(Dimension d, double x, double y, Dna zlifeDna) {
		super(d, x, y, zlifeDna);
		
		this.setZlifeDna(zlifeDna);
		this.loadDnaGenes();	
		
		this.setVectorWeights();
		ZBrain.setWeights(weights);
	}
	
	/**
	 * This method simulate the Zlife Age's process 
	 */
	@Override
	public void age()
	{
		if(actualLifeCycle<=lifeCycle)
		{
			int colorFactor = (int)(255/(lifeCycle*2+1));
			setMetabolism( getMetabolism()*getAgeFactor());
			//setMaxEnergy( getMaxEnergy() - getMaxEnergy()*getAgeFactor());
			this.setZlifeColor(this.R + colorFactor, 
				      	  	   this.G + colorFactor,
				               this.B + colorFactor);
			actualLifeCycle++;
		}
		else
			{setAlive(false); Deactivate();}
	}
	
	/**
	 * @return String short description of Zretador class
	 */
	public static String getDescription()
	{
		return "Zetatron is an advanced ZLife with a Neural Network brain" +
				"which decide the Zetatron state in terms of two input parameter:" +
				"\nenergy/hungryEnergy and energy/hornyEnergy" +
				"\nZetatron eat seed but also zlifes and zretador";
	}
	
	/**
	 * @see Zlife#live()
	 */
	@Override
	public void live()
	{
		if(isAlive())
		{
			thinkState();
			
			switch(state)
			{
			case BORED:
				{
						setSpeed((int) getBoredSpeed()+1);
						break; //this.move();
				}
			case HUNGRY:
				{
					this.setSpeed((int)hungrySpeed + 4);
					break;
				}
			case HORNY:
				{
					this.setSpeed((int)getHornySpeed());
					break;
				}
				
			case SCARY:
				{   
				
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
	
	
	/**
	 * Create a new Zetatron loading the default parameter values
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
		
		
		//WEIGHTS
		HN0w0 = 1.6472350208469893;
		HN0w1 =  1.5259734987014437;
		
		HN1w0 = 2.6628529707196837;
		HN1w1 = -4.3804934387500865;
		
		HN2w0 = -2.530569115172068;
		HN2w1 = 4.769453786615226 ;
		
		HN3w0 = -1.8361723886103454;
		HN3w1 = -0.596045032335354 ;
		
		HN4w0 = 1.6588830527078904;
		HN4w1 = 1.9764676548651472;
		
		ON0w0 = 0.05225449773499675;
		ON0w1 = -7.567386009338051;
		ON0w2 = 2.7268222830976008;
		ON0w3 = -0.6308534288149321;
		ON0w4 = 1.2607713913426353;
		
		ON1w0 = -2.112897695747519 ;
		ON1w1 = 3.493104440667188;
		ON1w2 = 2.1065398047867707;
		ON1w3 = 4.019529986557601;
		ON1w4= -3.6030767528508028;
		
		ON2w0 = 0.48460613526771457;
		ON2w1 = 3.2167614616048157;
		ON2w2 = -5.260496061985509;
		ON2w3 = -4.254364190317208;
		ON2w4 = 0.2761967817824116;
		
		
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

		//WEIGHTS
		HN0w0 = this.ZlifeDna.getGene("HN0w0").doubleValue();
		HN0w1 = this.ZlifeDna.getGene("HN0w1").doubleValue();
		
		HN1w0 = this.ZlifeDna.getGene("HN1w0").doubleValue();
		HN1w1 = this.ZlifeDna.getGene("HN1w1").doubleValue();
		
		HN2w0 = this.ZlifeDna.getGene("HN2w0").doubleValue();
		HN2w1 = this.ZlifeDna.getGene("HN2w1").doubleValue();
		
		HN3w0 = this.ZlifeDna.getGene("HN3w0").doubleValue();
		HN3w1 = this.ZlifeDna.getGene("HN3w1").doubleValue();
		
		HN4w0 = this.ZlifeDna.getGene("HN4w0").doubleValue();
		HN4w1 = this.ZlifeDna.getGene("HN4w1").doubleValue();
		
		ON0w0 = this.ZlifeDna.getGene("ON0w0").doubleValue();
		ON0w1 = this.ZlifeDna.getGene("ON0w1").doubleValue();
		ON0w2 = this.ZlifeDna.getGene("ON0w2").doubleValue();
		ON0w3 = this.ZlifeDna.getGene("ON0w3").doubleValue();
		ON0w4 = this.ZlifeDna.getGene("ON0w4").doubleValue();
		
		ON1w0 = this.ZlifeDna.getGene("ON1w0").doubleValue();
		ON1w1 = this.ZlifeDna.getGene("ON1w1").doubleValue();
		ON1w2 = this.ZlifeDna.getGene("ON1w2").doubleValue();
		ON1w3 = this.ZlifeDna.getGene("ON1w3").doubleValue();
		ON1w4 = this.ZlifeDna.getGene("ON1w4").doubleValue();
		
		ON2w0 = this.ZlifeDna.getGene("ON2w0").doubleValue();
		ON2w1 = this.ZlifeDna.getGene("ON2w1").doubleValue();
		ON2w2 = this.ZlifeDna.getGene("ON2w2").doubleValue();
		ON2w3 = this.ZlifeDna.getGene("ON2w3").doubleValue();
		ON2w4 = this.ZlifeDna.getGene("ON2w4").doubleValue();
		
	}
	
	
	/**
	 * Add to this ZLifeDNA the Current Genes. USe it if you want to create
	 * a new Zlife with your own parameter
	 */
	protected void makeMyDna()
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
		this.ZlifeDna.add(getAgeFactor(), "ageFactor", "Zlife's age Factor ");
		
		this.ZlifeDna.add(HN0w0, "HN0w0", "Zetatron Neural Network weight");
		this.ZlifeDna.add(HN0w1, "HN0w1", "Zetatron Neural Network weight");
		
		this.ZlifeDna.add(HN1w0, "HN1w0", "Zetatron Neural Network weight");
		this.ZlifeDna.add(HN1w1, "HN1w1", "Zetatron Neural Network weight");
		
		this.ZlifeDna.add(HN2w0, "HN2w0", "Zetatron Neural Network weight");
		this.ZlifeDna.add(HN2w1, "HN2w1", "Zetatron Neural Network weight");
	
		this.ZlifeDna.add(HN3w0, "HN3w0", "Zetatron Neural Network weight");
		this.ZlifeDna.add(HN3w1, "HN3w1", "Zetatron Neural Network weight");
		
		this.ZlifeDna.add(HN4w0, "HN4w0", "Zetatron Neural Network weight");
		this.ZlifeDna.add(HN4w1, "HN4w1", "Zetatron Neural Network weight");
		
		this.ZlifeDna.add(ON0w0, "ON0w0", "Zetatron Neural Network weight");
		this.ZlifeDna.add(ON0w1, "ON0w1", "Zetatron Neural Network weight");
		this.ZlifeDna.add(ON0w2, "ON0w2", "Zetatron Neural Network weight");
		this.ZlifeDna.add(ON0w3, "ON0w3", "Zetatron Neural Network weight");
		this.ZlifeDna.add(ON0w4, "ON0w4", "Zetatron Neural Network weight");
		
		this.ZlifeDna.add(ON1w0, "ON1w0", "Zetatron Neural Network weight");
		this.ZlifeDna.add(ON1w1, "ON1w1", "Zetatron Neural Network weight");
		this.ZlifeDna.add(ON1w2, "ON1w2", "Zetatron Neural Network weight");
		this.ZlifeDna.add(ON1w3, "ON1w3", "Zetatron Neural Network weight");
		this.ZlifeDna.add(ON1w4, "ON1w4", "Zetatron Neural Network weight");
		
		this.ZlifeDna.add(ON2w0, "ON2w0", "Zetatron Neural Network weight");
		this.ZlifeDna.add(ON2w1, "ON2w1", "Zetatron Neural Network weight");
		this.ZlifeDna.add(ON2w2, "ON2w2", "Zetatron Neural Network weight");
		this.ZlifeDna.add(ON2w3, "ON2w3", "Zetatron Neural Network weight");
		this.ZlifeDna.add(ON2w4, "ON2w4", "Zetatron Neural Network weight");
		
	}
	
	/**
	 * @param alive the alive to set
	 */
	@Override
	public synchronized void setAlive(boolean alive) {
		this.alive = alive;
	
		if (!alive)
		{
			setChanged();
			notifyObservers(this);
		}
		
	}
	
	/**
	 * Add weights into weights's vector
	 */
	private void setVectorWeights()
	{
		weights.add( HN0w0);
		weights.add( HN0w1);
		
		weights.add( HN1w0);
		weights.add( HN1w1);
		
		weights.add( HN2w0);
		weights.add( HN2w1);
		
		weights.add( HN3w0);
		weights.add( HN3w1);
		
		weights.add( HN4w0);
		weights.add( HN4w1);
		
		weights.add( ON0w0);
		weights.add( ON0w1);
		weights.add( ON0w2);
		weights.add( ON0w3);
		weights.add( ON0w4);
		
		weights.add( ON1w0);
		weights.add( ON1w1);
		weights.add( ON1w2);
		weights.add( ON1w3);
		weights.add( ON1w4);
		
		weights.add( ON2w0);
		weights.add( ON2w1);
		weights.add( ON2w2);
		weights.add( ON2w3);
		weights.add( ON2w4);
		
	}
	
	//calculate the current state with brain
	/**
	 * Calculate the current state as the result of Brain's think method
	 * @see Brain
	 * @see Brain#think(double, double)
	 */
	private void thinkState()
	{
		CellState futureState = this.state; 
		
		int mind = ZBrain.think( energy/hungryEnergy , energy/hornyEnergy);
		
		switch(mind)
		{
		case 1: {futureState = CellState.HORNY;break;}
		case 2: {futureState = CellState.HUNGRY;break;}
		case 3: {futureState = CellState.BORED;break;}
		
		}
		
		
		if(futureState != state)
		{
		age();
		setState(futureState);
		}
		
	}
	
	@Override
	public void ThisIsMe(Graphics g2d) 
	{
		g2d.setColor( getZlifeColor() );
		g2d.drawRect((int)this.getX() - this.radius , (int)this.getY() - this.radius, this.radius, this.radius);
		//g2d.drawLine((int)(this.getX() - this.radius*1.5) , (int)(this.getY() - this.radius*1.5), this.radius, this.radius);
		
		if(SELECTED)
		{
			g2d.setColor(Color.RED);
			g2d.drawOval((int)this.getX() - this.radius -5 , (int)this.getY() - this.radius - 5,this.radius + 10, this.radius + 10);
			
		}
		
	}
	
	
}

