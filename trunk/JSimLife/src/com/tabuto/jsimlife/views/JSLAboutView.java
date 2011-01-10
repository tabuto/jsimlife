/**
* @author Francesco di Dio
* Date: 29/dic/2010 23.49.24
* Titolo: JSLAboutView.java
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
 * Show a simple frame in order to display a logo and an HTML textPane contains
 * some JSimLife information such as:
 * <ul><li> Version <br>
 * <li> Copyright info <br>
 * <li> License <br>
 * <li> Web site <br></li>
 * <br>
 * 
 * @author tabuto83
 *
 */
public class JSLAboutView extends JFrame {
	
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
	 * @param locale Locale, the language parameter
	 */
	public JSLAboutView(String version,Locale locale)
	{
		super();
		resource = ResourceBundle.getBundle("StringAndLabels", locale );
		Version = version;
		this.setTitle(resource.getString( "jfa_title" ));
		setSize(new Dimension(500,200));
		//this.setLocation( (this.getRootPane().getWidth()/2),(this.getRootPane().getHeight()*2) );
		setLocation(200,200);
		setUndecorated(true);
		setResizable(false);
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
        		(this.getClass().getResource("/com/tabuto/jsimlife/icons/icon_alpha_48x48.gif")));
		
		this.pack();
		this.setVisible(true);
		
	}
	
	/**
	 * Info about this Game
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
	      image = javax.imageio.ImageIO.read(
	    		  new java.net.URL(
	    				  getClass().getResource("/com/tabuto/jsimlife/icons/JSimLife-Logo-200x200.png"), 
	    				  "JSimLife-Logo-200x200.png"));
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
