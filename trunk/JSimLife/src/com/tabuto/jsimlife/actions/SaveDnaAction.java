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
import com.tabuto.jsimlife.Configuration;


/**
 * Class extends AbstractAction to perform the following Action:
 * Save the DNA into an XML file using the default preferences path
 * 
 * @author tabuto83
 *
 * @see AbstractAction
 * @see Dna#toXML(String)
 * @see Configuration
 */
public class SaveDnaAction extends AbstractAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Dna dnaToSave;
	private Configuration preferences;
	Component root;
	
	/**
	 * Instantiate new SaveDnaAction
	 * 
	 * @param conf Configuration
	 * @param dnatosave Dna
	 */
	public SaveDnaAction(Configuration conf, Dna dnatosave, Component parent)
	{
		dnaToSave=dnatosave;
		preferences=conf;
		root = parent;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		 try {
			  
	         JFileChooser fileChooser = new JFileChooser();
	         File f = new File(preferences.getPath()+"/"+ dnaToSave.getName());
		     fileChooser.setCurrentDirectory(f);
	         fileChooser.setSelectedFile(f);
	         int n = fileChooser.showSaveDialog(root);
	         if (n == JFileChooser.APPROVE_OPTION) 
	         {        	
	         	
	         	dnaToSave.toXML(fileChooser.getSelectedFile().getAbsolutePath());
	            
	         }
	        
	         
	       } catch (Exception ex) {}
			
	}

}

