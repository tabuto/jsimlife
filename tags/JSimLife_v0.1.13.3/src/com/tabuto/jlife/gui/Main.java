/**
* @author Francesco di Dio
* Date: 28/dic/2010 15.46.39
* Titolo: Main.java
* Versione: 0.1.13.2 Rev.a:
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

package com.tabuto.jlife.gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
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
import com.tabuto.util.MyUtils;

//GAME ENTRY POINT
public class Main {
	
	static Locale locale;
	static JComboBox localeComboBox;
	static Configuration conf;
	static JLifeMainWindow main;
	static JFrame selectLocale;
	
	/**
	 * Perform a simple JFrame to choose the current Languages
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
			 	
			 	
			 	
			 	//launchGame(conf);
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
		JLifeMainWindow main = new JLifeMainWindow(conf);
		while(true){main.startNow();}
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