/**
* @author Francesco di Dio
* Date: 10/gen/2011 17.11.17
* Titolo: LoadSimulationAction.java
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
 * Loading a JSimLife game file into Simulation
 * 
 * @author tabuto83
 *
 * @see AbstractAction
 * @see JSimLife
 * @see JSLSimulationView#saveGame(com.tabuto.j2dgf.Game2D, String);
 */
public class LoadSimulationAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	JSLMainView mainView;
	
	/**
	 * Instantiate new LoadSimulationAction
	 * 
	 * @param mainview JSLMainView
	 * @see JSLMainView
	 */
	public LoadSimulationAction(JSLMainView mainview)
	{
		mainView = mainview;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		mainView.stopSimulation();
    	mainView.loading();
		try{
			  
            JFileChooser fileChooser = new JFileChooser();
   
            File f = new File( mainView.getPreferences().getPath()+"/.");
            fileChooser.setCurrentDirectory(f);
            
            int n = fileChooser.showOpenDialog(mainView);
            if (n == JFileChooser.APPROVE_OPTION) 
                {       
        			//mainView.getGame().deactivate();
            		mainView.setGame(
                          (JSimLife) mainView.getSimulationView().loadGame( 
                                          fileChooser.getSelectedFile().getAbsolutePath()
                                          ));
            		mainView.getGame().setSaved(true);
            		mainView.startSimulation();
            	}
               
                } catch (Exception ex) {}
	}
}
