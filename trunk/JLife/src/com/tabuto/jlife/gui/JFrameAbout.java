/**
* @author Francesco di Dio
* Date: 09/dic/2010 23.07.36
* Titolo: JFrameAbout.java
* Versione: 0.1.12.1 Rev.a:
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
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * Simple JFrame showing Game Logo and other Information
 * @author tabuto83
 *
 */
public class JFrameAbout extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	String Version="";
	JPanel viewer,south;
	BackgroundPanel logoPanel;
	JTextArea aboutInfo;
	
	/**
	 * Constructor parameters is the actual JSimLife version
	 * @param version String
	 */
	public JFrameAbout(String version)
	{
		super("About JSimLife");
		Version = version;
		setSize(new Dimension(500,200));
		//this.setLocation( (this.getRootPane().getWidth()/2),(this.getRootPane().getHeight()*2) );
		setLocation(200,200);
		setLayout( new BorderLayout());
		JButton okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener()
		   {
			public void actionPerformed(ActionEvent actionEvent) 
				{
					dispose();
				}
		   });		   
		
		viewer = new JPanel();
		logoPanel =  new BackgroundPanel();
		south = new JPanel();
		
		aboutInfo = new JTextArea();
		aboutInfo.setBackground(this.getBackground());
		aboutInfo.setEditable(false);
		aboutInfo.setText(this.aboutText());
		viewer.add(aboutInfo);
		
		south.setLayout( new FlowLayout(FlowLayout.RIGHT));
		south.add(okButton);
		
		
		//logoPanel.setLayout( new FlowLayout(FlowLayout.LEFT));
		viewer.setLayout( new FlowLayout(FlowLayout.LEFT));
		
		add(logoPanel,BorderLayout.WEST);
		add(viewer,BorderLayout.EAST);
		add(south,BorderLayout.SOUTH);
		
		//ADD ICON TITLE
        this.setIconImage(java.awt.Toolkit.getDefaultToolkit().getImage
        		(this.getClass().getResource("icon/icon_alpha_48x48.gif")));
		
		this.pack();
		this.setVisible(true);
		
	}
	
	/**
	 * @return Info about this Game
	 */
	private String aboutText()
	{
		return " JSimLife \n" +
				"\n Version: " + this.Version + "\n" +
				"\n Copyright 2010 Francesco di Dio " +
				"\n\n This Software is released under the MIT licenses. " +
				"\n\n Visit http://code.google.com/p/jsimlife/";
				
	}
	
	/**
	 * Simple class extends JPanel to show a Image Background
	 * @author tabuto83
	 *
	 */
	class BackgroundPanel extends JPanel
	{
	  /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
	Image image;
	  public BackgroundPanel()
	  {
		  
	    try
	    {
	      image = javax.imageio.ImageIO.read(new java.net.URL(getClass().getResource("JSimLife-Logo-200x200.png"), "JSimLife-Logo-200x200.png"));
	    }
	    catch (Exception e) { e.getStackTrace();}
	    
	    this.setSize(new Dimension(200,200));
	    this.setPreferredSize(new Dimension(200,200));
	  }

	  @Override
	  protected void paintComponent(Graphics g)
	  {
	    super.paintComponent(g); 
	    if (image != null)
	      g.drawImage(image, 0,0,this.getWidth(),this.getHeight(),this);
	  }
	}
}
