/**
* @author Francesco di Dio
* Date: 30/dic/2010 00.35.38
* Titolo: JSLSelectedZlifeView.java
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
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import com.tabuto.jsimlife.JSimLife;
import com.tabuto.jsimlife.actions.SaveSelectedZLifeAction;

/**
 * Show a JFrame contains several information about selected ZLife.
 * The JSLSelectedZLifeView appears when user click on a Zlife and update information
 * when Zlife change its state or if user select another Zlifes.
 * JSLSelectedZlifeView also display the Zlife's DNA as a JTree using the JSLDnaTreePanelView.
 * JSLSelectedZlifeView uses Observer pattern and observes the JSimLife observable class.
 * 
 * @author tabuto83
 * @see Zlife
 * @see JSLDnaTreePanelView
 * @see Dna
 *
 */
public class JSLSelectedZlifeView extends JFrame implements Observer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public JSimLife Game;
	
	private JLabel nameLabel, positionLabel, directionLabel;
	private JLabel stateLabel, speedLabel, lifeCycleLabel;
	private JLabel energyLabel, radiusLabel, colorLabel;
	private JLabel generationLabel;
	
	private JTextField nameField, positionField, directionField;
	private JTextField stateField, speedField, lifeCycleField;
	private JTextField energyField, radiusField;
	private JTextField generationField;

	private JButton colorButton;
	private JButton saveButton;
	
	private JPanel north,dnaPanel,south;
	private JSLDnaTreePanelView dnaTreePanel;
	private JScrollPane ZlifeDnaScroll; 
	
	private ResourceBundle resource;
	
	/**
	 * Create a new Zlife's view
	 * @param game JSimLife
	 */
	public JSLSelectedZlifeView(JSimLife game)
	{
		super();
		Game = game;
		resource = ResourceBundle.getBundle("StringAndLabels", Game.getConfiguration().getLocale());
		this.setTitle(resource.getString( "jlsz_title"));
		
		/*
		 * North panel display information about Zlife life's parameters 
		 */
		north = new JPanel();
		/*
		 *dnaPanel show Zlife's DNA as a JTree 
		 */
		dnaPanel = new JPanel();
		
		/*
		 * south panel show some JButton Controls
		 */
		south = new JPanel();
		
		/*
		 * The object that takes an XML documents and display it as a JTree
		 */
		dnaTreePanel = new JSLDnaTreePanelView();
		
		dnaPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		dnaPanel.setBackground(Color.WHITE);
		dnaPanel.add(dnaTreePanel);
		
		ZlifeDnaScroll = new JScrollPane(dnaPanel);
		
		setLayout( new BorderLayout());
		setPreferredSize(new Dimension(250,500));
		setResizable(false);
		initComponent();
		
		setAlwaysOnTop(true);
		
		//ADD ICON
		this.setIconImage(java.awt.Toolkit.getDefaultToolkit().getImage
        		(this.getClass().getResource("/com/tabuto/jsimlife/icons/icon_alpha_48x48.gif")));
	}
	
	
	/**
	 * Init all the Class's components and add it to the correct panel
	 */
	private void initComponent()
	{
		north.setLayout( new GridLayout(0,2,5,5));
		
		nameLabel = new JLabel(resource.getString( "jlsz_name"));
		positionLabel = new JLabel(resource.getString( "jlsz_position"));
		directionLabel = new JLabel(resource.getString( "jlsz_direction"));
		stateLabel = new JLabel(resource.getString( "jlsz_state"));
		speedLabel = new JLabel(resource.getString( "jlsz_speed"));
		lifeCycleLabel = new JLabel(resource.getString( "jlsz_lifeCycle"));
		energyLabel = new JLabel(resource.getString( "jlsz_energy"));
		radiusLabel = new JLabel(resource.getString( "jlsz_radius"));
		colorLabel = new JLabel(resource.getString( "jlsz_color"));
		generationLabel= new JLabel(resource.getString( "jlsz_generation"));
		
		
		
		nameField  = new JTextField(15);
		nameField.setEditable(false);
		positionField  = new JTextField(10);
		positionField.setEditable(false);
		directionField  = new JTextField(4);
		directionField.setEditable(false);
		stateField  = new JTextField(10);
		stateField.setEditable(false);
		speedField  = new JTextField(6);
		speedField.setEditable(false);
		lifeCycleField= new JTextField(3);
		lifeCycleField.setEditable(false);
		energyField  = new JTextField(10);
		energyField.setEditable(false);
		radiusField  = new JTextField(4);
		radiusField.setEditable(false);
		generationField= new JTextField(4);
		generationField.setEditable(false);
		
		colorButton = new JButton("");
		

		
		//ADD NORTH
		north.add(nameLabel);
		north.add(nameField);
		
		north.add(positionLabel);
		north.add(positionField);
		
		north.add(directionLabel);
		north.add(directionField);
		
		north.add(stateLabel);
		north.add(stateField);
		
		north.add(speedLabel);
		north.add(speedField);
		
		north.add(lifeCycleLabel);
		north.add(lifeCycleField);
		
		north.add(energyLabel);
		north.add(energyField);
		
		north.add(radiusLabel);
		north.add(radiusField);
		
		north.add(generationLabel);
		north.add(generationField);
	
		north.add(colorLabel);
		north.add(colorButton);
	
		saveButton = new JButton("Save");
		saveButton.addActionListener(new SaveSelectedZLifeAction(Game,this));
		south.add(saveButton);
		
		this.add(north,BorderLayout.NORTH);
		this.add(ZlifeDnaScroll,BorderLayout.CENTER);
		this.add(south,BorderLayout.SOUTH);
	}
	

	/**
	 * Check if this Frame is visible
	 */
	private void checkVisibility()
	{
		if(!this.isVisible())
		{
			
			this.pack();
			this.setVisible(true);
		}
	}
	
	/**
	 * Update the information about selected ZLife
	 */
	public void refresh()
	{
		try{
		nameField.setText( Game.getSelectedCell().getName());
		positionField.setText("["+ (int)Game.getSelectedCell().getPosition().getX() +", "+ (int)Game.getSelectedCell().getPosition().getY() +"]"  );
		directionField.setText(""+ (int) Math.toDegrees( Game.getSelectedCell().getAngle()));
		stateField.setText( Game.getSelectedCell().getState().toString());
		lifeCycleField.setText(""+Game.getSelectedCell().getActualLifeCycle()+"/"+ Game.getSelectedCell().getLifeCycle());
		speedField.setText(""+Game.getSelectedCell().getSpeed());
		energyField.setText(""+Math.rint(Game.getSelectedCell().getEnergy()*100)/100+"/"+Math.rint(Game.getSelectedCell().getMaxEnergy()*100/100) );
		radiusField.setText( ""+Math.rint(Game.getSelectedCell().getRadius()*100)/100);
		generationField.setText(""+Game.getSelectedCell().getGenerationNumber());
		
		colorButton.setBackground(new Color(Game.getSelectedCell().getR(),
											Game.getSelectedCell().getG(),
											Game.getSelectedCell().getB()));
		}
		catch(NullPointerException e)
		{
			e.printStackTrace();
		}

		
	}
	
	/**
	 * Set the Dna text to the JTextPane
	 */
	private void  setDnaPanel()
	{
		dnaTreePanel.initialize( Game.getSelectedCell().getZlifeDna().toXML());
	}
	
	/**
	 * Set the Current Game
	 * @param game JSimLife
	 */
	public void setGame(JSimLife game)
	{
		Game = game;
		
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		
		if(arg1 instanceof String)
		{
			String message = (String) arg1;
			if(message.equalsIgnoreCase("SelectionChange"))
			{
				if(Game.getSelectedCell()!=null)
				{
					refresh();
					setDnaPanel();
					checkVisibility();
				}
				else
				{
					if(this.isVisible())
					{
						this.setVisible(false);
						this.dnaTreePanel.close();
					}
				}
			}
			
			if(message.equalsIgnoreCase("Zlife:ChangeState"))
			{
				if(Game.getSelectedCell()!=null && this.isVisible())
				{
					refresh();
					
				}
			}
			
			if(message.equalsIgnoreCase("SeedSelected"))
			{
				JOptionPane.showMessageDialog(this, 
						"Seed quantity: "+ Game.getSelectedSeed().getQuantity(), 
						"Message", JOptionPane.INFORMATION_MESSAGE);	
			}
			
			
		}
			
	}
		
}


