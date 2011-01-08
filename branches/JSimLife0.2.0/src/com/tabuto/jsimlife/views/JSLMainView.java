/**
* @author Francesco di Dio
* Date: 29/dic/2010 16.57.35
* Titolo: JLSMainView.java
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

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.image.BufferStrategy;
import java.util.ResourceBundle;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;
import com.tabuto.jsimlife.Configuration;
import com.tabuto.jsimlife.JSLStatistic;
import com.tabuto.jsimlife.JSimLife;

/**
 * Class that creates the main window in which appears all the Game components:
 * <ul> <li> JSLMenu: a menu bar;<br>
 * <li> JSLToolbar: a toolbar;<br>
 * <li> JSLSimulationView: the canvas panel where Zlifes lives;<br>
 * <li> JSLControlPanelView: the panel where some statistical information are displayed;<br>
 * <li> JSLStatusBar: the panel displayed some game informations;<br>
 * </ul>
 * This class uses the Configuration class in order to store some game parameters such as
 * the canvas panel size, the background color of canvas panel, etc...
 * 
 * @author tabuto83
 * @see Configuration
 * @see JSimLife
 * @see JSLSimulationView
 *
 */
public class JSLMainView extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	BufferStrategy bs;      //BufferStrategy
    //int W=1024,H=768;       //Window Frame Size
    Dimension d;            //Dimension of window size
    private static final String version =" v.0.2.0";
    private static final String title="JSimLife";
    
    public ResourceBundle resource;
    
    //SIMULATION COMPONENT
    private Configuration preferences;
    private JSimLife Game;
    private JSLMenu menu;
    private JSLSimulationView SimulationPanel;
    private JScrollPane scroller;
    private JSLToolbar toolbar;
    private JSLControlPanelView cp_east;
    private JSLSelectedZlifeView zlifeView;
    private JSLStatusBar bottom;
    
    
    //SIMULATION STATUS VARIABLES
    boolean PLAY = true;
    boolean STOP = false;
    
    
    /**
     * Create a new MainView frame using 'conf' Configuration 
     * @param conf Configuration
     */
    public JSLMainView(Configuration conf) 
    {
    	preferences = conf;
    	resource = ResourceBundle.getBundle("StringAndLabels", preferences.getLocale());
    	d = preferences.getPlayfieldDimension();
    	
    	setTitle(title + version);
    	setExtendedState(this.getExtendedState()|JFrame.MAXIMIZED_BOTH);
    	setSize(d.width,d.height);
        setResizable(true);  
    	@SuppressWarnings("unused")
		BufferStrategy bs;
		createBufferStrategy(1);
		bs = getBufferStrategy();
        setLayout(new BorderLayout());
    	initSimulation();
    	
    	//Add MENU BAR
    	menu = new JSLMenu(this);
    	setJMenuBar(menu);
    	
    	//ADD ICON TITLE
        this.setIconImage(java.awt.Toolkit.getDefaultToolkit().getImage
        		(this.getClass().getResource("../icons/icon_alpha_48x48.gif")));
    	
    	pack();        
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);       
    }
    
    /**
     * @return The JSimLife game
     */
    public JSimLife getGame(){return Game;}
    
    /**
     * @return the JSLStatistic object
     */
    public JSLStatistic getStatistic(){return cp_east.getStatistic();}
    
    /**
     * @return the game preferences as Configuration
     */
    public Configuration getPreferences(){return preferences;}
    
    /**
     * @return the canvas panel as a JSLSimulationView
     */
    public JSLSimulationView getSimulationView()
    {
    	return SimulationPanel;
    }
    
    /**
     * Init the Game parameters and components
     */
    public void initSimulation()
    {
    	PLAY=true;
    	
    	Game = new JSimLife(this.d);
    	Game.setPath(preferences.getPath());
    	Game.setConfiguration(preferences);
    	
    	SimulationPanel = new JSLSimulationView(d,Game); //Declare the DrawingPanel
    	SimulationPanel.setBackgroundColor(preferences.getBackgroundColor());
    	SimulationPanel.setFocusable(true);
    	scroller = new JScrollPane(SimulationPanel);
    	scroller.setPreferredSize(new Dimension(600,600));
    	
    	toolbar = new JSLToolbar(this);
    	
    	  //INIT GAME
        Game.initGame();
    	
    	cp_east = new JSLControlPanelView(Game);
    	cp_east.setVisible(true);
    	
    	zlifeView= new JSLSelectedZlifeView(Game);
    	
    	bottom = new JSLStatusBar(Game);
    	bottom.setVisible(true); 
    	
    	//ADD PANELS TO THE FRAME
        
	    this.getContentPane().add(toolbar,BorderLayout.PAGE_START);
	    this.getContentPane().add(scroller,BorderLayout.CENTER);
	    this.getContentPane().add( cp_east, BorderLayout.LINE_END);
	    this.getContentPane().add( bottom,BorderLayout.PAGE_END);
         
         //ADD GAME OBSERVER
         Game.addObserver(cp_east);
         Game.addObserver(zlifeView);
         Game.addObserver(bottom);
    }
    
    /**
     * @return {@code true} if simulation running or {@code false} if not.
     */
    public boolean isRunning()
    {
    	return PLAY;
    }
    
    /**
     * The Game to set
     * @param g JSimLife
     */
    public void setGame(JSimLife g)
    {
    	Game = g;
    }
    
    /**
     * Start the simulation
     */
    public void startSimulation()
    {
    	PLAY = true;
    	STOP = false;
    	Game.activate();
    }
    
    /**
     * Stop/pause the simulation
     */
    public void stopSimulation()
    {
    	PLAY = false;
    	STOP = true;
    	Game.deactivate();
    }
    
    /**
     * Running the game in a endless loop
     */
    public void run()
    {
    	if(SimulationPanel instanceof JSLSimulationView)
    		while(PLAY){ SimulationPanel.run(Game); };   
    }
    
    /**
     * @return the current JSimLife version number
     */
    public String getVersion(){return version;}
}
