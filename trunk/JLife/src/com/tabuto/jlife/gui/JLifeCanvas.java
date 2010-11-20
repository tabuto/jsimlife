/**
* @author Francesco di Dio
* Date: 20/nov/2010 18.38.01
* Titolo: JLifeCanvas.java
* Versione: 0.1.7 Rev.a:
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


import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;



import com.tabuto.j2dgf.gui.J2DCanvasPanel;



/**
 * 
 * @author user
 * @Deprecated
 */
@Deprecated
public class JLifeCanvas extends J2DCanvasPanel implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3243620944955104850L;


	
public JLifeCanvas(int width, int height)
	{
	
	super(width, height);
	this.setName("NewSimulation.sim");
	}

public void initStuff()
{
	
}



public void save()
{
	 FileOutputStream fos = null;
    ObjectOutputStream out = null;
	 try
	 {
		 fos = new FileOutputStream(this.getName());
		 out = new ObjectOutputStream(fos);
		 out.writeObject(this);
		 out.close();
	 }
	  catch(IOException ex)
	  		{
		  		ex.printStackTrace();
			}
}





}