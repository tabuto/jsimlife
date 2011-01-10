/**
* @author Francesco di Dio
* Date: 10/gen/2011 11.09.22
* Titolo: JSLNewSeedView.java
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


import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import com.tabuto.jsimlife.JSimLife;
import com.tabuto.jsimlife.Seed;
import com.tabuto.jsimlife.actions.CreateNewSeedAction;

/**
 * Class that extends JFrame to show a simple dialog uses to add some seed on
 * simulation panel
 * @author tabuto83
 * @see Seed
 *
 */
public class JSLNewSeedView extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ResourceBundle resource;
	
	/**
	 * Reference for the JSimLife game
	 */
	public JSimLife Game;

	JLabel labelX, labelY,labelDensity,labelRadius;
	public JTextField fieldX;
	public JTextField fieldY;
	public JTextField fieldDensity;
	public JTextField fieldRadius;
	JButton buttonOk, buttonCancel;
	
	public JSLNewSeedView(JSimLife game)
	{
		super();
		Game = game;
		resource = ResourceBundle.getBundle("StringAndLabels", Game.getConfiguration().getLocale() );
		setTitle(resource.getString( "asd_title" ));
		
		
		
		setPreferredSize(new Dimension(160, 180));
		setResizable(false);
		setAlwaysOnTop(true);
		setLocation(10, 330);
		setLayout( new FlowLayout());
		
		//ADD ICON
		 setIconImage(java.awt.Toolkit.getDefaultToolkit().getImage
		    		(this.getClass().getResource("/com/tabuto/jsimlife/icons/icon_alpha_48x48.gif")));
		
		 initComponent();
		 addComponent();
		 
	}
	
	/**
	 * Init component for this JFrame
	 */
	private void initComponent()
	{
		labelX = new JLabel(resource.getString( "asd_x" ));
		labelY= new JLabel(resource.getString( "asd_y" ));
		labelDensity = new JLabel(resource.getString( "asd_density" ));
		labelRadius = new JLabel(resource.getString( "asd_radius" ));
		fieldX = new JTextField(4);
		fieldY = new JTextField(4);
		fieldRadius = new JTextField(4);
		fieldDensity = new JTextField(4);
		buttonOk = new JButton(resource.getString( "asd_ok" ));
		buttonCancel = new JButton(resource.getString( "asd_cancel" ));
		fieldX.setText( Integer.toString((int)(Math.random()* Game.getDimension().getWidth())) );
		fieldY.setText( Integer.toString((int)(Math.random()* Game.getDimension().getHeight())) );
		fieldRadius.setText("50");
		fieldDensity.setText("50");
	}
	
	/**
	 * Add components to this JFrame
	 */
	public void addComponent()
	{
		add(labelDensity);
		add(fieldDensity);
		add(labelRadius);
		add(fieldRadius);
		add(labelX);
		add(fieldX);
		add(labelY);
		add(fieldY);
		add(buttonOk);
		add(buttonCancel);
		buttonOk.addActionListener(new CreateNewSeedAction(this));

		buttonCancel.addActionListener(new ActionListener()
		   {
			public void actionPerformed(ActionEvent actionEvent) 
				{
				dispose();
				} 
			});
	}

	
}
