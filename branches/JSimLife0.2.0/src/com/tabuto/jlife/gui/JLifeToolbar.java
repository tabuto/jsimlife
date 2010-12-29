/**
* @author Francesco di Dio
* Date: 09/dic/2010 11.24.01
* Titolo: JLifeToolbar.java
* Versione: 0.1.13.2 Rev.a:
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

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import javax.swing.JToolBar;

import com.tabuto.jlife.statistic.JFrameStatistic;
import com.tabuto.jlife.statistic.Statistic;

/**
 * Simple toolbar used as a shortcut to execute most common commands
 * @author user
 *
 */
public class JLifeToolbar extends JToolBar implements ActionListener{ 

	

	JLifeMainWindow root;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	

	/*
	 * This String variables, identify the command to execute and the
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

	    private ResourceBundle resource;
	
	public JLifeToolbar(JLifeMainWindow root)
	{
		super();
		this.root = root;
		
		setPreferredSize(new Dimension(root.panel.getDimension().width,42));
		resource = ResourceBundle.getBundle("StringAndLabels", root.Preferences.getLocale() );
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
        button = makeNavigationButton(NEW, NEW,
        		resource.getString( "jltb_newTip" ), 
        		resource.getString( "jltb_new" )); 
        add(button);
        
        
        //exit button
        button = makeNavigationButton(EXIT, EXIT,
        		resource.getString( "jltb_exitTip" ), //TIP
        		resource.getString( "jltb_exit" )); //NAME
        add(button);
        
        //reset button
        button = makeNavigationButton(RESET, RESET,
        		resource.getString( "jltb_resetTip" ), //TIP
        		resource.getString( "jltb_reset" )); //NAME
        add(button);
        
        //pause button
        button = makeNavigationButton(PAUSE, PAUSE,
        		resource.getString( "jltb_pauseTip" ), //TIP
        		resource.getString( "jltb_pause" )); //NAME
        add(button);
        
        //start button
        button = makeNavigationButton(START, START,
        		resource.getString( "jltb_startTip" ), //TIP
        		resource.getString( "jltb_start" )); //NAME
        add(button);
        
        //Stop button
        button = makeNavigationButton(STEP, STEP,
        		resource.getString( "jltb_stepTip" ), //TIP
        		resource.getString( "jltb_step" )); //NAME
        add(button);
        
        //AddCell button
        button = makeNavigationButton(ADDCELL, ADDCELL,
        		resource.getString( "jltb_addCellTip" ), //TIP
        		resource.getString( "jltb_addCell" )); //NAME
        add(button);
        
        //Addseed button
        button = makeNavigationButton(ADDSEED, ADDSEED,
        		resource.getString( "jltb_addSeedTip" ), //TIP
        		resource.getString( "jltb_addSeed" )); //NAME
        add(button);
        
        //Chart button
        button = makeNavigationButton(CHART, CHART,
        		resource.getString( "jltb_chartTip" ), //TIP
        		resource.getString( "jltb_chart" )); //NAME
                                     
        add(button);
	}


	/*
	 * Add a JButton with an icon image
	 */
	protected JButton makeNavigationButton(String imageName,
            String actionCommand,
            String toolTipText,
            String altText) 
	
	{
			//Look for the image.
			String imgLocation = "icon/white/"
			+ imageName
			+ ".png";
			URL imageURL = JLifeToolbar.class.getResource(imgLocation);
			
			//Create and initialize the button.
			JButton button = new JButton();
			button.setActionCommand(actionCommand);
			button.setToolTipText(toolTipText);
			button.addActionListener(this);
			
			if (imageURL != null) {                      //image found
			button.setIcon(new ImageIcon(imageURL, altText));
			} else {                                     //no image found
			button.setText(altText);
			System.err.println("Resource not found: " + imgLocation);
			}
			
			return button;

}

	/*
	 * Perform the action passed by the ActionEvent using the String name above
	 * (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		 String cmd = e.getActionCommand();
	       

	        // Handle each button.
	              if (NEW.equals(cmd)) { //first button clicked
	            root.resetSimulation();
	        }else if (EXIT.equals(cmd)) { // second button clicked
	        	root.exitSimulation();
	        }else if (RESET.equals(cmd)) { // third button clicked
	        	root.resetSimulation();
	        } else if (PAUSE.equals(cmd)) { // third button clicked
	        	if(root.panel!=null)root.Game.deactivate();
	        } else if (START.equals(cmd)) { // third button clicked
	        	if(root.panel!=null)root.Game.activate();
	        } else if (STEP.equals(cmd)) { // third button clicked
	        	if(root.panel!=null)
	        		{
	        		root.panel.drawStuff( root.Game);
	        		root.panel.panelDraw();
	        		}
	        }else if (ADDCELL.equals(cmd)) { // third button clicked
	        	root.panel.addCell();
	        } else if (ADDSEED.equals(cmd)) { // third button clicked
	           root.panel.addSeed();
	        }else if (CHART.equals(cmd)) { // third button clicked
	        	new JFrameStatistic(new Statistic(root.Game));
	        }

		
	}
	
}
