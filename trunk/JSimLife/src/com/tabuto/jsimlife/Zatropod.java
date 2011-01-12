/**
* @author Francesco di Dio
* Date: 12/gen/2011 11.31.02
* Titolo: Zatropod.java
* Versione: 0.2.0.3b Rev.a:
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

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Vector;

import com.tabuto.j2dgf.Sprite;
import com.tabuto.jenetic.Dna;


public class Zatropod extends Zlife {

	
	Vector<Sprite> spriteVector = new Vector<Sprite>();
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Zatropod(Dimension d, double x, double y, Dna zlifeDna) {
		super(d, x, y, zlifeDna);
		
	}

	


public class Limb extends Sprite 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Zatropod head;
	
	//private double distance;
	
	public Limb(Dimension dim, double posX, double posY, Zatropod z) {
		super(dim, posX, posY);
		head = z;
		
	}

	@Override
	protected void ThisIsMe(Graphics g) {
		
		g.setColor(head.getZlifeColor());
		g.drawRoundRect((int) getX(), (int) getY(), 
						head.getRadius(), head.getRadius(), 
						(int)(head.getRadius()/3), (int)(head.getRadius()/3));
		
	}
	
}


} //END ZATROPOD
