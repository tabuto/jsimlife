/**
* @author Francesco di Dio
* Date: 27/dic/2010 14.55.28
* Titolo: Configuration.java
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
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;


import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

/**
 * This Class wrap some JSimLife parameter. 
 * Each parameter are its own setter and getter methods and a default values that configuration
 * auto load if they run for the first time.
 * Parameters actually present into JSimLife Configuration is:
 * <ul>
 * <li> Background Color <br>
 * <li> Locale Language preferences <br>
 * <li> JSimLife default path <br>
 * <li> Playfield dimension (width and height)<br>
 * <li> Max Zlife number <br>
 * <li> Max Zretador number <br>
 * <li> Max Zetatron number <br>
 * <li> Max seeds number <br></ul>
 * 
 * @author tabuto83
 *
 */
public class Configuration implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Language Setting
	 */
	private Locale LOCALE;
	
	/**
	 * Default Save/Load directory PATH
	 */
	private String PATH="";
	
	/**
	 * The playfield color background
	 */
	private Color background;
	
	/**
	 * The playfield Dimension
	 * @see Dimension
	 */
	private Dimension playfield;
	
	/**
	 * Zlifes max number
	 */
	private int MAX_ZLIFES;
	/**
	 * Zretador max number
	 */
	private int MAX_ZRETADOR;
	
	/**
	 * Zetatron max number
	 */
	private int MAX_ZETATRON;
	
	/**
	 * Seeds max number
	 */
	private int MAX_SEEDS;
	
	/**
	 * Constructor try to load the JSimLifeConf.xml file, if it does not
	 * exist, Configuration load the default values and create a new 
	 * SimLifeConf.xml with JSimLife's default values
	 */
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
	
	/*
	 * GETTERS FOR THE GAME PARAMETER
	 */
	public Color getBackgroundColor(){return background;}
	public Locale getLocale(){return LOCALE;}
	public int getMaxSeeds() {return MAX_SEEDS;}
	public int getMaxZlifes() {return MAX_ZLIFES;}
	public int getMaxZetatron() {return MAX_ZETATRON;}
	public int getMaxZretador() {return MAX_ZRETADOR;}
	public String getPath(){return this.PATH;}
	public Dimension getPlayfieldDimension(){return this.playfield;}
	
	/**
	 * Read a previously saved SimLifeConf.xml file and
	 * set the relative values.
	 */
	@SuppressWarnings("unchecked")
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
				this.setMaxZetatron( Integer.parseInt(maxSprites.getAttributeValue("Zetatron")));
				this.setMaxZretador( Integer.parseInt(maxSprites.getAttributeValue("Zretador")));
				this.setMaxSeeds( Integer.parseInt(maxSprites.getAttributeValue("Seeds")));
				
				//LOAD WORKINGSPACE
				Element workingspace = (Element) iterator.next();
				this.setPath( workingspace.getAttributeValue("CurrentDefaultPath"));
	    
				//LOAD LOCALE
				Element locale = (Element) iterator.next();
				this.setLocale(LocaleParseString( locale.getAttributeValue("CurrentLocale")));
				
	}
	
	/**
	 * Load the parameter's default values
	 */
	public void loadDefaultValues()
	{
		setPlayfieldDimension(1024,768);
		setBackgroundColor(Color.BLACK);
		setPath( System.getProperty("user.dir"));
		setMaxZlifes(250);
		setMaxZetatron(125);
		setMaxZretador(250);
		
		setMaxSeeds(50);
		setLocale(Locale.US);
		
	}
	
	/**
	 * Save the current Configuration's parameters into a SimLifeConf.xml file
	 */
	public void save()
	{
		  Element preferences = new Element("JSimLife-Preferences");
	      //Document
	      Document document = new Document(preferences);
	      
	      //ELEMENTS 1: PanelSize
	      Element gameSize = new Element("PanelSize");
	      gameSize.setAttribute("Width", String.valueOf((int)playfield.getWidth()));
	      gameSize.setAttribute("Height", String.valueOf((int)playfield.getHeight()));
	      preferences.addContent(gameSize);
	      
	      //ELEMENTS 2: BackGround Color
	      Element backgroundColor = new Element("BackgroundColor");
	      backgroundColor.setAttribute("R", String.valueOf(background.getRed()));
	      backgroundColor.setAttribute("G", String.valueOf(background.getGreen()));
	      backgroundColor.setAttribute("B", String.valueOf(background.getBlue()));
	      preferences.addContent(backgroundColor);
	      
	      //ELEMENTS 2 MAX SPRITES NUMBER
	      Element maxZlifes = new Element("MaxSpriteNumber");
	      maxZlifes.setAttribute("Zlifes", String.valueOf( getMaxZlifes() ));
	      maxZlifes.setAttribute("Zetatron", String.valueOf( getMaxZetatron()) );
	      maxZlifes.setAttribute("Zretador", String.valueOf( getMaxZretador()) );
	      maxZlifes.setAttribute("Seeds", String.valueOf(getMaxSeeds()));
	      preferences.addContent(maxZlifes);
	      
	      //ELEMENTS 3 PATH DIR
	      Element workingspace = new Element("Workingspace");
	      workingspace.setAttribute("CurrentDefaultPath", getPath());
	      preferences.addContent(workingspace);
	      
	      //ELEMENT 4 LOCALE
	      Element locale = new Element("Locale");
	      locale.setAttribute("CurrentLocale", LOCALE.toString());
	      preferences.addContent(locale);
	      
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
	
	/*
	 * SETTERS FOR THE CONFIGURATION PARAMETERS
	 */
	public void setBackgroundColor(Color b){background = b;}
	public void setLocale(Locale locale){this.LOCALE=locale;}
	public void setMaxSeeds(int mAX_SEEDS) {MAX_SEEDS = mAX_SEEDS;}
	public void setMaxZetatron(int mAX_ZETATRON) {MAX_ZETATRON = mAX_ZETATRON;}
	public void setMaxZlifes(int mAX_ZLIFES) {MAX_ZLIFES = mAX_ZLIFES;}
	public void setMaxZretador(int mAX_ZRETADOR) {MAX_ZRETADOR = mAX_ZRETADOR;}
	public void setPath(String p){this.PATH = p;}
	public void setPlayfieldDimension(Dimension d){playfield = d;}
	public void setPlayfieldDimension(int w, int h){playfield = new Dimension(w,h);}
	

	/**
	 * Parse a String value with the respective Locale object
	 * @param locale String rapresentation of a Locale object
	 * @return Locale
	 */
	public Locale LocaleParseString(String locale)
	{
		Locale l=Locale.US;
		
		if (locale.equalsIgnoreCase("en_US"))
				l =  Locale.US;
		else
			if (locale.equalsIgnoreCase("it_IT"))
				l = Locale.ITALY;
		return l;
	}
	
	/**
	 * @return {@code true} if JSimLifeConf.xml file is present and {@code false} if not
	 */
	public static boolean isPresent()
	{
		 SAXBuilder builder = new SAXBuilder();
	      @SuppressWarnings("unused")
		Document document;
	
				try {
					document = builder.build(new File("JSimLifeConf.xml"));
				} catch (JDOMException e) {
					
					e.printStackTrace();
				} catch (IOException e) {
					
					return false;
				}
		return true;
	}
}

