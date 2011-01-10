/**
* @author Francesco di Dio
* Date: 29/dic/2010 20.45.32
* Titolo: JSLToolbar.java
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

package com.tabuto.jsimlife.views;

import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;

import com.tabuto.jsimlife.actions.AddCellAction;
import com.tabuto.jsimlife.actions.AddSeedAction;
import com.tabuto.jsimlife.actions.ExitSimulationAction;
import com.tabuto.jsimlife.actions.PauseSimulationAction;
import com.tabuto.jsimlife.actions.ResetSimulationAction;
import com.tabuto.jsimlife.actions.ShowChartViewAction;
import com.tabuto.jsimlife.actions.StartSimulationAction;
import com.tabuto.jsimlife.actions.StepSimulationAction;



/**
 * Simple Class extends JToolBar uses to show several JSimLife button 
 * that performs JSimLife's Actions
 * 
 * @author tabuto83
 *	
 */
public class JSLToolbar extends JToolBar{
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JSLMainView root;
	private ResourceBundle resource;
	
	/*
	 * This String variables, identify the
	 * file name of button icon images
	 */
	   	static final private String NEW = "new"; 
	   	static final private String EXIT = "close";
	    static final private String RESET = "reset";
	    static final private String PAUSE = "pause";
	    static final private String START = "start";
	    static final private String STEP = "step";
	    static final private String ADDCELL = "addCell";
	    static final private String ADDSEED = "addSeed";
	    static final private String CHART = "chart";
	
	public JSLToolbar(JSLMainView mainView)
	{
		super();
		root = mainView;
		resource = ResourceBundle.getBundle("StringAndLabels", root.getPreferences().getLocale() );
		addButtons();
	}

	/*
	 * ADD BUTTONS TO THE TOOLBAR 
	 * using the makeNavigationButton methods
	 */
	protected void addButtons() 
	{
        JButton button = null;

        //new button
        button = makeNavigationButton(NEW, (new ResetSimulationAction( resource.getString("jltb_new"),root )),
        		resource.getString( "jltb_newTip" ), 
        		resource.getString( "jltb_new" )); 
        add(button);
        
        
        //exit button
        button = makeNavigationButton(EXIT, (new ExitSimulationAction( resource.getString("jltb_exit"),root )),
        		resource.getString( "jltb_exitTip" ), //TIP
        		resource.getString( "jltb_exit" )); //NAME
        add(button);
        
        //reset button
        button = makeNavigationButton(RESET, (new ResetSimulationAction( resource.getString("jltb_reset"),root )),
        		resource.getString( "jltb_resetTip" ), //TIP
        		resource.getString( "jltb_reset" )); //NAME
        add(button);
        
        //pause button
        button = makeNavigationButton(PAUSE, (new PauseSimulationAction( resource.getString("jltb_pause"),root )),
        		resource.getString( "jltb_pauseTip" ), //TIP
        		resource.getString( "jltb_pause" )); //NAME
        add(button);
        
        //start button
        button = makeNavigationButton(START, (new StartSimulationAction( resource.getString("jltb_start"),root )),
        		resource.getString( "jltb_startTip" ), //TIP
        		resource.getString( "jltb_start" )); //NAME
        add(button);
        
        //Stop button
        button = makeNavigationButton(STEP, (new StepSimulationAction( resource.getString("jltb_step"),root )),
        		resource.getString( "jltb_stepTip" ), //TIP
        		resource.getString( "jltb_step" )); //NAME
        add(button);
        
        //AddCell button
        button = makeNavigationButton(ADDCELL, (new AddCellAction( root.getGame()) ),
        		resource.getString( "jltb_addCellTip" ), //TIP
        		resource.getString( "jltb_addCell" )); //NAME
        add(button);
        
        //Addseed button
        button = makeNavigationButton(ADDSEED, (new AddSeedAction( resource.getString("jltb_reset"),root.getGame() )),
        		resource.getString( "jltb_addSeedTip" ), //TIP
        		resource.getString( "jltb_addSeed" )); //NAME
        add(button);
        
        //Chart button
       
        button = makeNavigationButton(CHART, (new ShowChartViewAction( root )),
        		resource.getString( "jltb_chartTip" ), //TIP
        		resource.getString( "jltb_chart" )); //NAME
                                     
        add(button);
        
	}
	
	/**
	 * Add a JButton to this Toolbar
	 * @param imageName Icon file name
	 * @param actionCommand Action to execute
	 * @param toolTipText 
	 * @param altText
	 * @return JButton
	 */
	protected JButton makeNavigationButton(String imageName,
            AbstractAction actionCommand,
            String toolTipText,
            String altText) 
	
	{
			//Look for the image.
			String imgLocation = "../icons/"
			+ imageName
			+ ".png";
			URL imageURL = JSLToolbar.class.getResource(imgLocation);
			
			//Create and initialize the button.
			JButton button = new JButton();
			button.setToolTipText(toolTipText);
			button.addActionListener(actionCommand);
			
			if (imageURL != null) {                      //image found
			button.setIcon(new ImageIcon(imageURL, altText));
			} else {                                     //no image found
			button.setText(altText);
			System.err.println("Resource not found: " + imgLocation);
			}
			
			return button;

	}

}
