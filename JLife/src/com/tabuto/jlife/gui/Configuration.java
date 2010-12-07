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

/**
 * NOT YET IMPLEMENTED
 * @author user
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
	
	
	public void setLocale(String locale){this.LOCALE=locale;}
	public void setPath(String p){this.PATH = p;}
	
	public String getLocale(){return this.LOCALE;}
	public String getPath(){return this.PATH;}
}
