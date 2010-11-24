/**
* @author Francesco di Dio
* Date: 24/nov/2010 15.18.55
* Titolo: JLifeMainWindow.java
* Versione: 0.1.8 Rev.a:
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





public class JLifeMainWindow extends JFrame {
	
	private static final long serialVersionUID = 1L;
	BufferStrategy bs;      //BufferStrategy
    int W=1024,H=668;       //Window Frame Size
    Dimension d;            //Dimension of window size
    private static final String version =" v.0.1.8 BETA";
    private static final String title="JSimLife";
    boolean PLAY = true;
    boolean STOP = false;
    
    boolean simLoad = false;
    boolean saved = false;
    
    //GUI PANELS AND ELEMENTS
    Simulation panel;
    JMenuBar j2dmenubar;
    JLifeLeftControlPanel cp_west;
    JLifeRightControlPanel cp_east;
    JLifeBottomPanel bottom;
    JScrollPane scroller;
    
    public JLifeMainWindow()
    {
    	d = new Dimension(W,H);
    
    	bottom = new JLifeBottomPanel(d);
    	j2dmenubar = new JMenuBar();
    	
    	
    	
        setTitle(title + version);
        setSize(d.width,d.height);
        setResizable(true);  
    	@SuppressWarnings("unused")
		BufferStrategy bs;
		createBufferStrategy(1);
		bs = getBufferStrategy();
        setLayout(new BorderLayout());
       
        this.newSimulation();
        bottom.setVisible(true);
        addMenu();
        
        //ADD ICON TITLE
        this.setIconImage(java.awt.Toolkit.getDefaultToolkit().getImage
        		(this.getClass().getResource("icon_alpha_48x48.gif")));

   
        this.getContentPane().add( bottom, BorderLayout.PAGE_END);
        this.setJMenuBar(j2dmenubar);
        pack();        
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);       
    }
    
    //Start the game
    public void startNow()
    {
    	if(panel instanceof Simulation)
    		while(PLAY){ panel.run(); };   
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
        				//New
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
        				//Open
		JMenuItem open = new JMenuItem("Open");
		open.setMnemonic('O');
		open.addActionListener(new OpenSimChooser());
		filemenu.add( open);
		
        				//Save
		JMenuItem save = new JMenuItem("Save");
		save.setMnemonic('S');
		save.addActionListener(new ActionListener()
		{
			public void actionPerformed( ActionEvent action )
				{
					if( panel.Game.isSaved() )
						panel.saveGame();
					else
						new OpenSimChooser();
				}
		});
		filemenu.add( save );
		
        				//Save as
		JMenuItem saveAs = new JMenuItem("Save As");
		saveAs.setMnemonic('N');
		saveAs.addActionListener(new SaveSimChooser());
		filemenu.add( saveAs );
        				//EXIT
        		JMenuItem exit = new JMenuItem("Exit");
        		exit.setMnemonic('Q');
        		exit.addActionListener(new ActionListener()
				{
        			public void actionPerformed( ActionEvent action )
						{
        					exitSimulation();
        					
						}
				});
        		filemenu.add( exit );
        		
         filemenu.getPopupMenu().setLightWeightPopupEnabled(false);

        	
        // ACTION MENU
        JMenu actionmenu = new JMenu("Action");
        actionmenu.setMnemonic('A');
        		//ITEMS
        				//START
        	JMenuItem start = new JMenuItem("Start");
        	start.setMnemonic('S');
        	start.setAccelerator(KeyStroke.getKeyStroke(
      		         KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        	start.addActionListener(new ActionListener()
			{
    			public void actionPerformed( ActionEvent action )
					{if(panel!=null)panel.Play();}
			});
        	actionmenu.add(start);
        		
        				//STEP
        	JMenuItem step = new JMenuItem("Step");
        	step.setMnemonic('E');
        	step.setAccelerator(KeyStroke.getKeyStroke(
      		         KeyEvent.VK_E, ActionEvent.CTRL_MASK));
        	step.addActionListener(new ActionListener()
			{
    			public void actionPerformed( ActionEvent action )
					{if(panel!=null)panel.Step();}
			});
        	actionmenu.add(step);
        	
        				//STOP
        	JMenuItem stop = new JMenuItem("Stop");
        	stop.setMnemonic('T');
        	stop.setAccelerator(KeyStroke.getKeyStroke(
   		         KeyEvent.VK_T, ActionEvent.CTRL_MASK));
        	stop.addActionListener(new ActionListener()
			{
    			public void actionPerformed( ActionEvent action )
					{  if (panel!=null) panel.Stop(); }
			});
        	actionmenu.add(stop);
        	
        				//RESET
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
        				//INFO
    		JMenuItem about = new JMenuItem("Info");
    		about.addActionListener(new ActionListener()
			{
    			
      	      public void actionPerformed( ActionEvent action )
      	      								{
      	    	  		JOptionPane.showMessageDialog(null, 
      	    	  				"JUniverse "+ version+" is written by tabuto83", 
      	    	  				"About", 
      	    	  				JOptionPane.PLAIN_MESSAGE);
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
    
    
    //Menu Controls
    
    public void newSimulation()
    {
    	PLAY=true;
    	panel = new Simulation(1024,1024); //Declare the DrawingPanel
    	cp_west = new JLifeLeftControlPanel(d);
    	cp_east = new JLifeRightControlPanel(d, panel.Game);
    	scroller = new JScrollPane(panel);
    	cp_west.setCanvasPanel(panel);
    	
    	 cp_west.setVisible(true);
         //cp_west.setCanvasPanel(panel);
         cp_east.setVisible(true);
         
         panel.setFocusable(true);
         this.getContentPane().add(scroller,BorderLayout.CENTER);
         this.getContentPane().add( cp_west, BorderLayout.LINE_START);
         this.getContentPane().add( cp_east, BorderLayout.LINE_END);
         panel.initStuff();
         panel.Game.addObserver(cp_east);
    }
    
   
        
    public void resetSimulation()
    {
    	if (JOptionPane.showConfirmDialog(this,
				"The actual Simulation is going to be lost, are you sure?",
				"Confirm new Simulation",
				 JOptionPane.YES_NO_CANCEL_OPTION,
				 JOptionPane.QUESTION_MESSAGE)==JOptionPane.YES_OPTION)
	{PLAY=false; this.panel.Game.reset();  }
    	else
    	{};

    }
    
    public void exitSimulation()
    {
    	if (JOptionPane.showConfirmDialog(this,
				"The actual Simulation is going to be closed, are you sure?",
				"Confirm Close Simulation",
				 JOptionPane.YES_NO_CANCEL_OPTION,
				 JOptionPane.QUESTION_MESSAGE)==JOptionPane.YES_OPTION)
    			{
    				panel = null;
    				System.exit(0);  
    			}
    	else
    	{};

    }
    	
    //INNER PRIVATE CLASSES
    private class OpenSimChooser implements ActionListener 
    {

        public void actionPerformed(ActionEvent e) 
        {
          try{
  
            JFileChooser fileChooser = new JFileChooser();
   
            int n = fileChooser.showOpenDialog(JLifeMainWindow.this);
            if (n == JFileChooser.APPROVE_OPTION) 
            	{	
        		  JLifeMainWindow.this.panel.loadGame( fileChooser.getSelectedFile().getAbsolutePath());
        		  JLifeMainWindow.this.cp_east.setGame(panel.Game);
        		  JLifeMainWindow.this.panel.Game.addObserver(cp_east);
            	}
          	 
          	} catch (Exception ex) {}
        }
    }
        
    
    //INNER PRIVATE CLASSES
    private class SaveSimChooser implements ActionListener {

        public void actionPerformed(ActionEvent e) {
          try {
  
            JFileChooser fileChooser = new JFileChooser();
   
            int n = fileChooser.showSaveDialog(JLifeMainWindow.this);
            if (n == JFileChooser.APPROVE_OPTION) 
            {
            	JLifeMainWindow.this.panel.Game.setName(fileChooser.getSelectedFile().getAbsolutePath());
            	JLifeMainWindow.this.panel.saveGame();
            }
           
            
          } catch (Exception ex) {}
        }
      }
    
}//END CLASS