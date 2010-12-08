/**
* @author Francesco di Dio
* Date: 02/dic/2010 15.18.55
* Titolo: JLifeMainWindow.java
* Versione: 0.1.11 Rev.a:
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

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;


import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;
import javax.swing.WindowConstants;

import com.tabuto.jlife.JLife;




/**
 * The game main JFrame. It contains the menubar, the Canvas panel, and the control panels.
 * It allow user to save or load, start or stop simulation.
 * @author user
 *
 */
public class JLifeMainWindow extends JFrame {
	
	public Configuration Preferences;
	
	private static final long serialVersionUID = 1L;
	BufferStrategy bs;      //BufferStrategy
    int W=1024,H=768;       //Window Frame Size
    Dimension d;            //Dimension of window size
    private static final String version =" v.0.1.12";
    private static final String title="JSimLife";
    
    //GAME STATUS VARIABLES
    boolean PLAY = true;
    boolean STOP = false;
    boolean simLoad = false;
    boolean saved = false;
    
    //DECLARE GUI PANELS AND ELEMENTS
    public static JLife Game; //GAME MODEL
    public Simulation panel; //THE PANEL
    private JMenuBar j2dmenubar;  //THE MENUBAR
    public JLifeToolbar toolbar; //THE TOOLBAR
    //public JLifeLeftControlPanel cp_west;
    public JLifeRightControlPanel cp_east;
    JLifeBottomPanel bottom;
    JScrollPane scroller;
    JLifeShowZlife ZlifeView; //Frame that let you to view the selected Zlife
    
    public JLifeMainWindow(Configuration conf)
    {
    	Preferences = conf;
    	//d = new Dimension(W,H);
    	d = Preferences.getPlayfieldDimension();
    	this.setExtendedState(this.getExtendedState()|JFrame.MAXIMIZED_BOTH);

    	
    	j2dmenubar = new JMenuBar();
        setTitle(title + version);
        setSize(d.width,d.height);
        setResizable(true);  
    	@SuppressWarnings("unused")
		BufferStrategy bs;
		createBufferStrategy(1);
		bs = getBufferStrategy();
        setLayout(new BorderLayout());
       
        //INIT ALL COMPONENT
        newSimulation();
     
        //ADD the menu
        addMenu();
        this.setJMenuBar(j2dmenubar);
        
        //ADD ICON TITLE
        this.setIconImage(java.awt.Toolkit.getDefaultToolkit().getImage
        		(this.getClass().getResource("icon/icon_alpha_48x48.gif")));

   
    
        
        pack();        
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);       
    }
    
    //Start the game
    public void startNow()
    {
    	if(panel instanceof Simulation)
    		while(PLAY){ panel.run(Game); };   
    }

    //ADDMENU ROUTINES
    private void addMenu()
    {
    	 //MENU
        //CREATE THE MENU BAR
        
        j2dmenubar.setIgnoreRepaint(true);
     
        
        //FILE MENU
        JMenu filemenu = new JMenu("JSimLife");
        filemenu.setMnemonic('J');
        		//ITEMS
        				//New ***********************************
    	JMenuItem newSim = new JMenuItem("New");
		newSim.setMnemonic('N');
		newSim.addActionListener(new ActionListener()
		{
			public void actionPerformed( ActionEvent action )
				{
					resetSimulation();
				}
		});
		filemenu.add( newSim );
        				//Open ***********************************
		JMenuItem open = new JMenuItem("Open");
		open.setMnemonic('O');
		open.addActionListener(new OpenSimChooser());
		filemenu.add( open);
		
        				//Save ***********************************
		JMenuItem save = new JMenuItem("Save");
		save.setMnemonic('S');
		save.addActionListener(new ActionListener()
		{
			public void actionPerformed( ActionEvent action )
				{
					if( panel.Game.isSaved() )
						panel.saveGame(Game, panel.getFileName(Game));
					else
						new OpenSimChooser();
				}
		});
		filemenu.add( save );
		
        				//Save as ***********************************
		JMenuItem saveAs = new JMenuItem("Save As");
		saveAs.setMnemonic('N');
		saveAs.addActionListener(new SaveSimChooser());
		filemenu.add( saveAs );

						//PREFERENCES ***********************************
		JMenuItem preferences = new JMenuItem("Preferences");
		preferences.setMnemonic('P');
		preferences.addActionListener(new ActionListener()
						{
							public void actionPerformed( ActionEvent action )
								{ 
									new JFramePreferences(Preferences);
								}
						});
		filemenu.add( preferences );
		filemenu.getPopupMenu().setLightWeightPopupEnabled(false);
		
        				//EXIT ***********************************
		JMenuItem exit = new JMenuItem("Exit");
		exit.setMnemonic('Q');
		exit.addActionListener(new ActionListener()
				{
        			public void actionPerformed( ActionEvent action )
						{ exitSimulation();
						}
				});
        		filemenu.add( exit );
        filemenu.getPopupMenu().setLightWeightPopupEnabled(false);

        	
        // ACTION MENU
        JMenu actionmenu = new JMenu("Action");
        actionmenu.setMnemonic('A');
        		//ITEMS
        				//START ***********************************
        	JMenuItem start = new JMenuItem("Start");
        	start.setMnemonic('S');
        	start.setAccelerator(KeyStroke.getKeyStroke(
      		         KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        	start.addActionListener(new ActionListener()
			{
    			public void actionPerformed( ActionEvent action )
					{if(panel!=null)Game.activate();}
			});
        	actionmenu.add(start);
        		
        				//STEP ***********************************
        	JMenuItem step = new JMenuItem("Step");
        	step.setMnemonic('E');
        	step.setAccelerator(KeyStroke.getKeyStroke(
      		         KeyEvent.VK_E, ActionEvent.CTRL_MASK));
        	step.addActionListener(new ActionListener()
			{
    			public void actionPerformed( ActionEvent action )
					{
    				if(panel!=null){} //panel.Step();
						}
					}
			);
        	actionmenu.add(step);
        	
        				//STOP ***********************************
        	JMenuItem stop = new JMenuItem("Stop");
        	stop.setMnemonic('T');
        	stop.setAccelerator(KeyStroke.getKeyStroke(
   		         KeyEvent.VK_T, ActionEvent.CTRL_MASK));
        	stop.addActionListener(new ActionListener()
			{
    			public void actionPerformed( ActionEvent action )
					{  if (panel!=null)
							Game.deactivate(); 
					}
    			});
        	actionmenu.add(stop);
        	
        				//RESET ***********************************
        	JMenuItem reset = new JMenuItem("Reset");
        	reset.setMnemonic(KeyEvent.VK_R);
        	reset.setAccelerator(KeyStroke.getKeyStroke(
        		         KeyEvent.VK_R, ActionEvent.CTRL_MASK));
        	reset.addActionListener(new ActionListener()
        									{
        	      public void actionPerformed( ActionEvent action )
        	      								{
        										//panel.deleteStuff();
        	    	  							resetSimulation();
        	      								}
        									});
        	actionmenu.add(reset);
        	
        actionmenu.getPopupMenu().setLightWeightPopupEnabled(false);
        
        // ABOUT MENU
        JMenu aboutmenu = new JMenu("About");
        	//ITEMS
        				//INFO ***********************************
    		JMenuItem about = new JMenuItem("Info");
    		about.addActionListener(new ActionListener()
			{
    			
      	      public void actionPerformed( ActionEvent action )
      	      								{
      	    	  new JFrameAbout(version);
      	      								}
      									});
    	aboutmenu.add(about);
    	
    	aboutmenu.getPopupMenu().setLightWeightPopupEnabled(false);
    	
    	//ADD THE MENU AT MENUBAR
        j2dmenubar.add( filemenu);
        j2dmenubar.add( actionmenu) ;
        j2dmenubar.add( aboutmenu);
        j2dmenubar.setVisible(true);
        j2dmenubar.setIgnoreRepaint(true);
        j2dmenubar.setFocusable(true);
        
    }
    
    
    //Create new Simulation
    public void newSimulation()
    {
    	PLAY=true;
    	Game = new JLife(this.d);
    	panel = new Simulation(d,Game); //Declare the DrawingPanel
    	panel.setBackgroundColor(Preferences.getBackgroundColor());
    	toolbar = new JLifeToolbar(this);
    	//cp_west = new JLifeLeftControlPanel(d);
    	cp_east = new JLifeRightControlPanel(d, panel.Game);
    	ZlifeView= new JLifeShowZlife(panel.Game);
    	scroller = new JScrollPane(panel);
    	scroller.setPreferredSize(new Dimension(600,600));
    	//cp_west.setCanvasPanel(panel);
    	bottom = new JLifeBottomPanel(d,Game);
    	bottom.setVisible(true); 
    	
    	 //cp_west.setVisible(true);
         //cp_west.setCanvasPanel(panel);
         cp_east.setVisible(true);
         panel.setFocusable(true);
         
         //ADD PANELS TO THE FRAME
         this.getContentPane().add(toolbar,BorderLayout.PAGE_START);
         this.getContentPane().add(scroller,BorderLayout.CENTER);
         //this.getContentPane().add( cp_west, BorderLayout.LINE_START);
         this.getContentPane().add( cp_east, BorderLayout.LINE_END);
         this.getContentPane().add( bottom, BorderLayout.PAGE_END);
         
         //INIT GAME
         Game.initGame();
         
         //ADD GAME OBSERVER
         Game.addObserver(cp_east);
         Game.addObserver(ZlifeView);
         Game.addObserver(bottom);
    }
    
   
    //RESET SIMULATION
    public void resetSimulation()
    {
    	if (JOptionPane.showConfirmDialog(this,
				"The actual Simulation is going to be lost, are you sure?",
				"Confirm new Simulation",
				 JOptionPane.YES_NO_CANCEL_OPTION,
				 JOptionPane.QUESTION_MESSAGE)==JOptionPane.YES_OPTION)
	{
    		PLAY=false;this.panel.Game.reset();  
    		PLAY=true;
	}
    		else
    	{};

    }
    
    //QUIT SIMULATION
    public void exitSimulation()
    {
    	if (JOptionPane.showConfirmDialog(this,
				"The actual Simulation is going to be closed, are you sure?",
				"Confirm Close Simulation",
				 JOptionPane.YES_NO_CANCEL_OPTION,
				 JOptionPane.QUESTION_MESSAGE)==JOptionPane.YES_OPTION)
    			{
    				Game = null;
    				System.exit(0);  
    			}
    	else
    	{};

    }
    	
    //INNER PRIVATE CLASSES
    
    /**
     * OPen a JFileChooser to choose a previously load file
     * and set the new Simulation as the actual simulation
     */
    public class OpenSimChooser implements ActionListener 
    {

        public void actionPerformed(ActionEvent e) 
        {
          try{
  
            JFileChooser fileChooser = new JFileChooser();
   
            int n = fileChooser.showOpenDialog(JLifeMainWindow.this);
            if (n == JFileChooser.APPROVE_OPTION) 
            	{	
            	JLifeMainWindow.this.Game.deactivate();
            	JLifeMainWindow.this.Game = 
      			  (JLife) panel.loadGame( 
      					  fileChooser.getSelectedFile().getAbsolutePath()
      					  );
            
            	
            	
            	panel.Game = JLifeMainWindow.this.Game;
            	ZlifeView.Game = JLifeMainWindow.this.Game;
            	cp_east.setGame( JLifeMainWindow.this.Game);
            	bottom.setGame(JLifeMainWindow.this.Game);
            	
            	JLifeMainWindow.this.Game.addObserver(cp_east);
            	JLifeMainWindow.this.Game.addObserver(ZlifeView);
            	JLifeMainWindow.this.Game.addObserver(bottom);
            	}
          	 
          	} catch (Exception ex) {}
        }
    }
        
    
    //INNER PRIVATE CLASSES
    /**
     * Open a JFileChooser to Save the actual simulation
     */
    public class SaveSimChooser implements ActionListener {

        public void actionPerformed(ActionEvent e) {
          try {
  
            JFileChooser fileChooser = new JFileChooser();
   
            int n = fileChooser.showSaveDialog(JLifeMainWindow.this);
            if (n == JFileChooser.APPROVE_OPTION) 
            { 
            	
            	JLifeMainWindow.this.Game.setName(fileChooser.getSelectedFile().getAbsolutePath());
            	JLifeMainWindow.this.panel.saveGame
            	 (JLifeMainWindow.this.Game,JLifeMainWindow.this.panel.getFileName(JLifeMainWindow.this.Game ) );
            }
           
            
          } catch (Exception ex) {}
        }
      }
    
}//END CLASS