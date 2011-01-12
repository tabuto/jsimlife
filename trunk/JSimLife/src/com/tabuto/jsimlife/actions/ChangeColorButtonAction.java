/**
* @author Francesco di Dio
* Date: 11/gen/2011 16.36.24
* Titolo: ChangeColorButtonAction.java
* Versione: 0.2.0.b3 Rev.a:
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

package com.tabuto.jsimlife.actions;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JColorChooser;

/**
 * Class extends AbstractAction to perform the following Action:
 * Open a JColorChooser to set background color of a button
 * 
 * @author tabuto83
 * 
 * @see AbstractAction
 * @see JColorChooser
 * @see JButton
 */

public class ChangeColorButtonAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JButton colorButton;
	Component Root;
	String Title;
	
	/**
	 * Instantiate new ChangeColorButtonAction
	 * 
	 * @param root Component
	 * @param title String title of JColorChooser
	 * @param button JButton's background to set
	 */
	
	public ChangeColorButtonAction(Component root,String title, JButton button)
	{
		super();
		
		colorButton = button;
		Root = root;
		Title = title;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		Color color = JColorChooser.showDialog(
				 Root, Title ,colorButton.getBackground()
				  );
				 if (color!= null)
					 colorButton.setBackground(color);
		
		
	}

}
