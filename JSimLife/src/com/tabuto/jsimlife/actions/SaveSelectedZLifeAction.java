/**
* @author Francesco di Dio
* Date: 29/dic/2010 16.30.49
* Titolo: SaveSelectedZLifeAction.java
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

package com.tabuto.jsimlife.actions;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.io.File;
import javax.swing.AbstractAction;
import javax.swing.JFileChooser;

import com.tabuto.jenetic.Dna;
import com.tabuto.jsimlife.JSimLife;


/**
 * Class extends AbstractAction to perform the following Action:
 * Save the selected Zlife's Dna into an XML file using a JFileChooser
 * 
 * @author tabuto83
 *
 * @see AbstractAction
 * @see JSimLife
 * @see Dna#toXML(String)
 */
public class SaveSelectedZLifeAction extends AbstractAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JSimLife Game;
	private Component root;
	/**
	 * Instantiate new SaveSelectedZLifeAction
	 * 
	 * @param game JSimLife 
	 */
	public SaveSelectedZLifeAction(JSimLife game, Component parent)
	{
		Game=game;
		root = parent;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		 try {
			  
	         JFileChooser fileChooser = new JFileChooser();
	         File f = new File(Game.getPath()+"/"+ Game.getSelectedCell().getName());
		     fileChooser.setCurrentDirectory(f);
	         fileChooser.setSelectedFile(f);
	         int n = fileChooser.showSaveDialog(root);
	         if (n == JFileChooser.APPROVE_OPTION) 
	         {        	
	         	
	         	Game.getSelectedCell().getZlifeDna().toXML(fileChooser.getSelectedFile().getAbsolutePath());
	            
	         }
	        
	         
	       } catch (Exception ex) {}
			
	}

}
