/**
* @author Francesco di Dio
* Date: 09/dic/2010 15.46.39
* Titolo: Main.java
* Versione: 0.1.12.1 Rev.a:
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

import javax.swing.JOptionPane;
import com.tabuto.util.MyUtils;

//GAME ENTRY POINT
public class Main {
	
	public static void main(String[] args) {

		/*
		 * Check Version routine
		 */
	if ( MyUtils.isVersionHigherThan(1.6) )
	 {
		//LOAD CONFIGURATION
		Configuration conf = new Configuration(); 
		//INSTANCIATE NEW GAME
		JLifeMainWindow main = new JLifeMainWindow(conf);
		//START THE GAME
		while(true){main.startNow();}
    }
  
else
	JOptionPane.showMessageDialog(null, 
 				"Your Java version lower than "+ 1.6 + ". To run this software you need JRE v.1.6 or higher. Update your Java virtual machine", 
 				"Version Control", 
 				JOptionPane.WARNING_MESSAGE);

	}
}