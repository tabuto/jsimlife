/**
* @author Francesco di Dio
* Date: 10/gen/2011 14.43.42
* Titolo: JSLSplashScreenView.java
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

package com.tabuto.jsimlife.views;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.SplashScreen;

/**
 * Class managing SplashScreen object. To show splash screen JSimLife must be run with the flag: -splash:SplashScreen.jpeg
 * 
 * @author tabuto83
 * @see SplashScreen
 *
 */
public class JSLSplashScreenView {

	/**
	 * Default Splash Screen
	 */
	static SplashScreen splash;
	String splashVersion = "2.0";
	/**
	 * The Max number of element to load. Use to calculate the actual loading percent
	 */
	int ElementMaxNumber;
	
	/**
	 * Default color of the loading bar
	 */
	Color colorBar = new Color(235,157,49);
	
	/**
	 * Build a new SplashScreen using 0 as ELementMaxNumber.
	 * Use {@link JSLSplashScreenView#setElementMaxNumber(int)} to set the ElementMaxNumber
	 */
	public JSLSplashScreenView()
	{
		ElementMaxNumber=0;
		
		splash = SplashScreen.getSplashScreen();
		drawVersionString(splashVersion);
		showSplashScreen();
	}
	
	/**
	 * Build a new JSLSplashScreenView
	 * @param max The ElementMaxNumber to set
	 * @see JSLSplashScreenView#ElementMaxNumber
	 */
	public JSLSplashScreenView(int max)
	{
		setElementMaxNumber(max);
		splash = SplashScreen.getSplashScreen();
		drawVersionString(splashVersion);
		showSplashScreen();
	}
	
	/**
	 * Render the current SplashScreen showing the 'load' String as the 'current' element number to load
	 * @param load  Loading Message
	 * @param current The current element number to load
	 */
	 public void renderSplashFrame(String load,int current) {
		 try{
		 	Graphics2D g = splash.createGraphics();
		 	//DRAW LOADING MESSAGE
	        g.setColor(Color.WHITE);
	        g.setComposite(AlphaComposite.Clear);
	        g.fillRect(260,400,200,40);
	        g.setPaintMode();
	        g.drawString(load+"...", 260, 418);
	        //DRAW LOADING BAR
	        g.setColor(colorBar);
	        g.drawRect(0,422, 600, 19);
	        //FILL LOADING BAR IN PERCENT
	        g.fillRect(0,422,((int)(getLoadingPercent(current)*6)) , 19);
	        //DRAW LOADING PERCENT
	        g.setColor(Color.WHITE);
	        g.drawString(""+ getLoadingPercent(current)+"%",300,437 );
	        
	        splash.update();
		 }
		 catch (NullPointerException e) {
			 System.out.println("SplashScreen: Unable to draw SplashScreen");
         }
		 
	    }
	
	 /**
	  * Show the SplashScreen if correctly loaded
	  */
	private static void showSplashScreen()
	 {
		 
	        if (splash == null) {
	            System.out.println("SplashScreen.getSplashScreen() returned null");
	            return;
	        }
	        Graphics2D g = splash.createGraphics();
	        if (g == null) {
	            System.out.println("SplashScreen: Graphics g is null");
	            return;
	        }
	            splash.update();
	            try {
	                Thread.sleep(500);
	            	}
	            catch(InterruptedException e) {
	            }
	 }
	
	/**
	 * Close the SplashScreen
	 */
	public void close()
	{
		 try {
             Thread.sleep(1000);
         	}
         catch(InterruptedException e) {
         }
		splash.close();
	}
	
	/**
	 * @param max ElementMaxNumber to set
	 * @see JSLSplashScreenView#ElementMaxNumber
	 */
	public void setElementMaxNumber(int max)
	{
		ElementMaxNumber = max;
	}
	
	/**
	 * @param current The current element in which calculate actual loading percent
	 * @return The actual loading percent in a double value
	 */
	public double getLoadingPercent(int current)
	{
		return (Math.rint((double)current/(double)ElementMaxNumber*100));	
	}
	
	/**
	 * Draw the version number as a String into SplashScreen
	 * @param version String number version
	 */
	public void drawVersionString(String version)
	{
		Graphics2D g = splash.createGraphics();
        g.setColor(colorBar);
        g.setFont(new Font("arial",Font.PLAIN,26));
        g.drawString(version, 470, 340);
        splash.update();
	}

}
