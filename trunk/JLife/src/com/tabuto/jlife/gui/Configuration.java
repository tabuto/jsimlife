/**
* @author Francesco di Dio
* Date: 16/nov/2010 14.55.28
* Titolo: Configuration.java
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
  * Classe che incapsula i parametri di configurazione, salvandoli in un file XML
  */

package com.tabuto.jlife.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

/**
 * This Class wrap some JSimLife parameter
 * @author tabuto83
 *
 */
public class Configuration {
	
	/**
	 * Language Setting
	 */
	private String LOCALE="";
	
	/**
	 * Default Save/Load directory PATH
	 */
	private String PATH="";
	
	private Color background;
	
	private Dimension playfield;
	
	private int MAX_ZLIFES;
	
	private int MAX_ZRETADOR;
	
	private int MAX_SEEDS;
	
	public Configuration()
	{
		try{this.load();}
			catch (IOException e) 
			{
				loadDefaultValues();
				this.save();
				try {
					this.load();
				} catch (IOException e1) {
					
					e1.printStackTrace();
				} catch (Throwable e1) {
					
					e1.printStackTrace();
				}
			} 
			catch (Throwable e) 
			{
				
				e.printStackTrace();
			}

	}
	
	public Color getBackgroundColor(){return background;}
	public String getLocale(){return this.LOCALE;}
	public int getMaxSeeds() {return MAX_SEEDS;}
	public int getMaxZlifes() {return MAX_ZLIFES;}
	public int getMaxZretador() {return MAX_ZRETADOR;}
	public String getPath(){return this.PATH;}
	public Dimension getPlayfieldDimension(){return this.playfield;}
	
	public void load() throws IOException, Throwable
	{
		
		  SAXBuilder builder = new SAXBuilder();
	      Document document;
	
				document = builder.build(new File("JSimLifeConf.xml"));
				Element root = document.getRootElement();
				List children = root.getChildren();
				Iterator iterator = children.iterator(); 

				//LOAD GAME SIZE
				Element gameSize = (Element)iterator.next(); 
				this.setPlayfieldDimension( (int)Double.parseDouble(gameSize.getAttributeValue("Width")), (int)Double.parseDouble(gameSize.getAttributeValue("Height")));
				
				//LOAD BACKGROUND COLOR
				Element backgroundColor = (Element)iterator.next(); 
				this.setBackgroundColor(  new Color(   Integer.parseInt(backgroundColor.getAttributeValue("R")),
		    		  									Integer.parseInt(backgroundColor.getAttributeValue("G")),
		    		  											 Integer.parseInt(backgroundColor.getAttributeValue("B"))
		    		   								));  
				//LOAD MAX SPRITE PARAMETER
				Element maxSprites = (Element)iterator.next(); 
				this.setMaxZlifes( Integer.parseInt(maxSprites.getAttributeValue("Zlifes")));
				this.setMaxZretador( Integer.parseInt(maxSprites.getAttributeValue("Zretador")));
				this.setMaxSeeds( Integer.parseInt(maxSprites.getAttributeValue("Seeds")));
	    
	}
	
	public void loadDefaultValues()
	{
		setPlayfieldDimension(1024,768);
		setBackgroundColor(Color.BLACK);
		
		setMaxZlifes(250);
		
		setMaxZretador(250);
		
		setMaxSeeds(50);
		
	}
	
	public void save()
	{
		  Element preferences = new Element("JSimLife-Preferences");
	      //Document
	      Document document = new Document(preferences);
	      
	      //ELEMENTS 1
	      Element gameSize = new Element("PanelSize");
	      gameSize.setAttribute("Width", String.valueOf((int)playfield.getWidth()));
	      gameSize.setAttribute("Height", String.valueOf((int)playfield.getHeight()));
	      preferences.addContent(gameSize);
	      
	      //ELEMENTS 2
	      Element backgroundColor = new Element("BackgroundColor");
	      backgroundColor.setAttribute("R", String.valueOf(background.getRed()));
	      backgroundColor.setAttribute("G", String.valueOf(background.getGreen()));
	      backgroundColor.setAttribute("B", String.valueOf(background.getBlue()));
	      preferences.addContent(backgroundColor);
	      
	      //ELEMENTS 2
	      Element maxZlifes = new Element("MaxSpriteNumber");
	      maxZlifes.setAttribute("Zlifes", String.valueOf( getMaxZlifes() ));
	      maxZlifes.setAttribute("Zretador", String.valueOf( getMaxZretador()) );
	      maxZlifes.setAttribute("Seeds", String.valueOf(getMaxSeeds()));
	      preferences.addContent(maxZlifes);
	      
	      
	      //WRITE FILE
	      XMLOutputter outputter = new XMLOutputter();
	      outputter.setFormat(Format.getPrettyFormat());
	      try {
			outputter.output(document, new FileOutputStream("JSimLifeConf.xml"));
		} catch (FileNotFoundException e) {
			
			System.out.println("File not Found");
				
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	public void setBackgroundColor(Color b){background = b;}
	public void setLocale(String locale){this.LOCALE=locale;}
	public void setMaxSeeds(int mAX_SEEDS) {MAX_SEEDS = mAX_SEEDS;}
	public void setMaxZlifes(int mAX_ZLIFES) {MAX_ZLIFES = mAX_ZLIFES;}
	public void setMaxZretador(int mAX_ZRETADOR) {MAX_ZRETADOR = mAX_ZRETADOR;}
	public void setPath(String p){this.PATH = p;}
	public void setPlayfieldDimension(Dimension d){playfield = d;}
	public void setPlayfieldDimension(int w, int h){playfield = new Dimension(w,h);}




	

	
	
	
	
}
