/**
* @author Francesco di Dio
* Date: 10/gen/2011 17.05.07
* Titolo: SaveSimulationAction.java
* Versione: 0.2.0.1 Rev.a:
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

import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import com.tabuto.jsimlife.JSimLife;
import com.tabuto.jsimlife.views.JSLMainView;

/**
 * Class extends AbstractAction to perform the following Action:
 * Save a previously saved  JSimLife game file into file
 * 
 * @author tabuto83
 *
 * @see AbstractAction
 * @see JSimLife
 * @see JSLSimulationView#saveGame(com.tabuto.j2dgf.Game2D, String);
 */
public class SaveSimulationAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	JSLMainView mainView;
	
	/**
	 * Instantiate new SaveSimulationAction
	 * 
	 * @param mainview JSLMainView
	 * @see JSLMainView
	 */
	public SaveSimulationAction(JSLMainView mainview)
	{
		super();
		mainView = mainview;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		if (mainView.getGame().isSaved())
		{
			mainView.stopSimulation();
       	 	mainView.loading();
            mainView.getSimulationView().saveGame(mainView.getGame(), mainView.getGame().getName());  
            mainView.getGame().setSaved(true);
            mainView.startSimulation();
		}
		else
		{
				try {
					  
			         JFileChooser fileChooser = new JFileChooser();
			         File f = new File(mainView.getPreferences().getPath()+"/"+ "NewSimulation.jsl");
				     fileChooser.setCurrentDirectory(f);
			         fileChooser.setSelectedFile(f);
			         int n = fileChooser.showSaveDialog(mainView);
			         if (n == JFileChooser.APPROVE_OPTION) 
			         {    
			        	 mainView.stopSimulation();
			        	 mainView.loading();
			        	 mainView.getGame().setName(fileChooser.getSelectedFile().getAbsolutePath());
			        	 mainView.getGame().setSaved(true);
			        	 
			             mainView.getSimulationView().saveGame(mainView.getGame(), fileChooser.getSelectedFile().getAbsolutePath());  
			             mainView.startSimulation();
			             mainView.getGame().setSaved(true);
			         }
			         
			       } 
				catch (Exception ex) {}
		}
		
	}

}
