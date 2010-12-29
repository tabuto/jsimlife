/**
* @author Francesco di Dio
* Date: 24/dic/2010 23.07.36
* Titolo: JFrameAbout.java
* Versione: 0.1.12.2 Rev.a:
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
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextPane;

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
	
	private ResourceBundle resource;
	
	
	String Version="";
	JPanel viewer,south;
	BackgroundPanel logoPanel;
	JTextPane aboutInfo;
	
	/**
	 * Constructor parameters is the actual JSimLife version
	 * @param version String
	 */
	public JFrameAbout(String version,Locale locale)
	{
		super();
		resource = ResourceBundle.getBundle("StringAndLabels", locale );
		Version = version;
		this.setTitle(resource.getString( "jfa_title" ));
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
		
		aboutInfo = new JTextPane();
		aboutInfo.setBackground(this.getBackground());
		aboutInfo.setContentType("text/html");
		aboutInfo.setEditable(false);
		aboutText();
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
	private void aboutText()
	{
		String Version = resource.getString( "jfa_version" );
		String License = resource.getString( "jfa_license" );
		
		this.aboutInfo.setText(
		"<span style=\"font-family: Arial; font-weight: bold;\">JSimLife</span><br>"+
	    "<span style=\"font-family: Arial;\">"+Version+":&nbsp;" + this.Version +"&nbsp; </span><br>"+
		"<br style=\"font-family: Arial;\">"+
		"<span style=\"font-family: Arial;\">Copyright&nbsp; © 2010 Francesco di Dio </span><br style=\"font-family: Arial;\">"+
		"<br style=\"font-family: Arial;\">"+
		"<span style=\"font-family: Arial;\">"+License +"</span><br><br>"+
		"<a style=\"font-family: Arial;\" href=\"http://www.jsimlife.netsons.org/\">http://www.jsimlife.netsons.org/</a><br>"
		);
				
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
