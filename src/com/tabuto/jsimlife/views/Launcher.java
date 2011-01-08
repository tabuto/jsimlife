/**
* @author Francesco di Dio
* Date: 28/dic/2010 15.46.39
* Titolo: Launcher.java
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

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.SplashScreen;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.tabuto.jsimlife.Configuration;
import com.tabuto.util.MyUtils;

//GAME ENTRY POINT
public class Launcher {
	
	static Locale locale;
	static JComboBox localeComboBox;
	static Configuration conf;
	static JSLMainView main;
	static JFrame selectLocale;
	static SplashScreen screen;

	
	/**
	 * Perform a simple JFrame to choose the current Languages
	 * displayed just the first time launching JSimLife
	 */
	private static void selectLocale(){
		
		selectLocale = new JFrame("Select Language");
		selectLocale.setPreferredSize(new Dimension (250,80));
		selectLocale.setLocation(200, 200);
		selectLocale.setAlwaysOnTop(true);
	
		localeComboBox = new JComboBox();	
		localeComboBox.addItem(Locale.US);
		localeComboBox.addItem(Locale.ITALY);
		
		//Set the defaul languages
		locale = Locale.US;
		
		//If selection change, change the actual locale language
		localeComboBox.addItemListener( new ItemListener()
		{
			@Override
			public void itemStateChanged(ItemEvent event)
			{
				 if ( event.getStateChange() == ItemEvent.SELECTED )
				 {
				 	locale = (Locale) localeComboBox.getSelectedItem();
				 	
				 	
				 }
			}
		}
			);
		
		JButton okButton = new JButton("OK");
		
		//If okButton pressed, start new Configuration, set the new Default languages
		//and start the game
		okButton.addActionListener(new ActionListener()
		   {
			public void actionPerformed(ActionEvent actionEvent) 
				{
				conf = new Configuration(); 
				conf.setLocale(locale);
				conf.save();
				
				JOptionPane.showMessageDialog(null, "The changes will be available on the next restart" ,
						"Apply Changes",
											JOptionPane.PLAIN_MESSAGE);
				
			 	selectLocale.dispose();

				}
		   });
		
		selectLocale.setLayout(new FlowLayout());
		selectLocale.add(new JLabel("Select Locale"));
		selectLocale.add(localeComboBox);
		selectLocale.add(okButton);
		
		//ADD ICON
		selectLocale.pack();
		selectLocale.setVisible(true);
	}
	
	/**
	 * Launch the Game using the actual Configuration
	 * @param conf
	 */
	public static void launchGame(Configuration conf)
	{
		main = new JSLMainView(conf);
		while(true){main.run();}
	}
	
	/**
	 * JSimLife Entry Point
	 * @param args
	 */
	public static void main(String[] args) 
	{
				/*
				 * Check Version routine
				 */
			if ( MyUtils.isVersionHigherThan(1.6) )
			 {
					//IF is first time running JSimLife
					if (!Configuration.isPresent())
					{
						selectLocale();
					}
					
					conf = new Configuration(); 
					launchGame(conf);
		    }
		  
			else
					JOptionPane.showMessageDialog(null, 
		 				"Your Java version lower than "+ 1.6 + ". To run this software you need JRE v.1.6 or higher. Update your Java virtual machine", 
		 				"Version Control", 
		 				JOptionPane.WARNING_MESSAGE);

	}
	



}
