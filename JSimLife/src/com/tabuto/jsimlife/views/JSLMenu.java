/**
* @author Francesco di Dio
* Date: 29/dic/2010 16.42.05
* Titolo: JSLMenu.java
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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ResourceBundle;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import com.tabuto.jsimlife.Configuration;
import com.tabuto.jsimlife.actions.ExitSimulationAction;
import com.tabuto.jsimlife.actions.LaunchXMLEasyEditorAction;
import com.tabuto.jsimlife.actions.ResetSimulationAction;
import com.tabuto.jsimlife.actions.SetPreferencesAction;
import com.tabuto.jsimlife.actions.ShowAboutInfoAction;
import com.tabuto.jsimlife.views.JSLMainView;

/**
 * This class extends JMenuBar to add into JSLMainView a simple menù bar.
 * @author tabuto83
 * @see JSLMainView
 * @see JMenuBar
 *
 */
public class JSLMenu extends JMenuBar {
	
	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ResourceBundle resource;
	private Configuration preferences;
	private JSLMainView mainView;
	
	/**
	 * Create a new JMenuBar and add it into a JSLMainView
	 * @param mainview
	 */
	public JSLMenu(JSLMainView mainview)
	{
		super();
		mainView = mainview;
		preferences = mainView.getPreferences();
		resource = ResourceBundle.getBundle("StringAndLabels", preferences.getLocale());
		initMenu();
	}
	
	/**
	 * Init and add to JSLMenu the menù's items
	 */
	private void initMenu()
	{
		 setIgnoreRepaint(true);
	     
	        
	        //FILE MENU
	        JMenu filemenu = new JMenu("JSimLife");
	        filemenu.setMnemonic('J');
	        		//ITEMS
	        				//New ***********************************
					    	JMenuItem newSim = new JMenuItem(  resource.getString( "newLabel" ));
							newSim.setMnemonic('N');
							newSim.addActionListener(new ResetSimulationAction(resource.getString( "newLabel" ),mainView));
							filemenu.add( newSim );
	        				//Open ***********************************
							
							JMenuItem open = new JMenuItem( resource.getString( "openLabel" ));
							open.setMnemonic('O');
							//TODO: Add OpenSimulation Action
							//open.addActionListener(new OpenSimChooser());
							filemenu.add( open);
			
	        				//Save ***********************************
							JMenuItem save = new JMenuItem( resource.getString( "saveLabel" ));
							save.setMnemonic('S');
							save.addActionListener(new ActionListener()
							{
								public void actionPerformed( ActionEvent action )
									{
										//TODO: add save Simulation action
									}
							});
							filemenu.add( save );
			
	        				//Save as ***********************************
							JMenuItem saveAs = new JMenuItem( resource.getString( "saveAsLabel" ));
							saveAs.setMnemonic('N');
							//TODO: add saveAsSimulation action
							//saveAs.addActionListener(new SaveSimChooser());
							filemenu.add( saveAs );

							//PREFERENCES ***********************************
							JMenuItem preferences = new JMenuItem( resource.getString( "preferencesLabel" ));
							preferences.setMnemonic('P');
							preferences.addActionListener(new SetPreferencesAction(mainView.getGame()));
							filemenu.add( preferences );
							filemenu.getPopupMenu().setLightWeightPopupEnabled(false);
								
				        				//EXIT ***********************************
							JMenuItem exit = new JMenuItem( resource.getString( "exitLabel" ));
							exit.setMnemonic('Q');
							exit.addActionListener(new ExitSimulationAction(resource.getString("jltb_exit"),mainView));
					        		filemenu.add( exit );
					        filemenu.getPopupMenu().setLightWeightPopupEnabled(false);

	        	
	        // ACTION MENU
	        JMenu actionmenu = new JMenu( resource.getString( "actionLabel" ));
	        actionmenu.setMnemonic('A');
	        		//ITEMS
	        				//START ***********************************
				        	JMenuItem start = new JMenuItem( resource.getString( "startLabel" ));
				        	start.setMnemonic('S');
				        	start.setAccelerator(KeyStroke.getKeyStroke(
				      		         KeyEvent.VK_S, ActionEvent.CTRL_MASK));
				        	start.addActionListener(new ActionListener()
							{
				    			public void actionPerformed( ActionEvent action )
									{
				    					//ADD Start Simulation Action
									}
							});
				        	actionmenu.add(start);
	        		
	        				//STEP ***********************************
				        	JMenuItem step = new JMenuItem( resource.getString( "stepLabel" ));
				        	step.setMnemonic('E');
				        	step.setAccelerator(KeyStroke.getKeyStroke(
				      		         KeyEvent.VK_E, ActionEvent.CTRL_MASK));
				        	step.addActionListener(new ActionListener()
							{
				    			public void actionPerformed( ActionEvent action )
									{
				    					
				    				//ADD Step Simulation Action
										
									}
							});
				        	actionmenu.add(step);
	        	
	        				//STOP ***********************************
				        	JMenuItem stop = new JMenuItem( resource.getString( "stopLabel" ));
				        	stop.setMnemonic('T');
				        	stop.setAccelerator(KeyStroke.getKeyStroke(
				   		         KeyEvent.VK_T, ActionEvent.CTRL_MASK));
				        	stop.addActionListener(new ActionListener()
							{
				    			public void actionPerformed( ActionEvent action )
									{  
				    				//ADD Stop Simulation Action
									}
				    			});
				        	actionmenu.add(stop);
	        	
	        				//RESET ***********************************
				        	JMenuItem reset = new JMenuItem( resource.getString( "resetLabel" ));
				        	reset.setMnemonic(KeyEvent.VK_R);
				        	reset.setAccelerator(KeyStroke.getKeyStroke(
				        		         KeyEvent.VK_R, ActionEvent.CTRL_MASK));
				        	reset.addActionListener(new ResetSimulationAction(resource.getString( "newLabel" ),mainView));
				        	actionmenu.add(reset);
	        	
	        actionmenu.getPopupMenu().setLightWeightPopupEnabled(false);
	        
	     // TOOLS MENU
	        JMenu toolsmenu = new JMenu( resource.getString( "toolLabel" ));
	        toolsmenu.setMnemonic('T');
	        //ITEMS
				        	//XML VIEWER ***********************************
				        	JMenuItem xmleditor = new JMenuItem("XML Editor");
				        	xmleditor.addActionListener(new LaunchXMLEasyEditorAction(mainView));
				        	toolsmenu.add(xmleditor);
		
				        	//EASY NEURONS ***********************************
				        	JMenuItem easyneurons = new JMenuItem("Easy Neurons");
				        	easyneurons.addActionListener(new ActionListener()
				        	{
				        		public void actionPerformed( ActionEvent action )
									{
				        			 //TODO: launch easy neurons
									}
								});
				        	toolsmenu.add(easyneurons);
	        	
	        toolsmenu.getPopupMenu().setLightWeightPopupEnabled(false);
	        
	        
	        // ABOUT MENU
	        JMenu aboutmenu = new JMenu( resource.getString( "aboutLabel" ));
	        	//ITEMS
	        				//INFO ***********************************
				    		JMenuItem about = new JMenuItem( resource.getString( "infoLabel" ));
				    		about.addActionListener(new ShowAboutInfoAction(mainView) );
				    	aboutmenu.add(about);
	    	
	    	aboutmenu.getPopupMenu().setLightWeightPopupEnabled(false);
	    	
	    	
	    	
	    	//ADD THE MENU AT MENUBAR
	        add( filemenu);
	        add( actionmenu) ;
	        add( toolsmenu) ;
	        add( aboutmenu);
	        setVisible(true);
	        setIgnoreRepaint(true);
	        setFocusable(true);
	        
	}

}
