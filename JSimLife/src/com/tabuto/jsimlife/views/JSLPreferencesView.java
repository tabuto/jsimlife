/**
* @author Francesco di Dio
* Date: 29/dic/2010 23.29.52
* Titolo: JSLPreferencesView.java
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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import com.tabuto.jsimlife.Configuration;
import com.tabuto.jsimlife.actions.SavePreferencesAction;

/**
 * Show a simple JFrame allows users change the game's configuration parameters
 * 
 * @author tabuto83
 * 
 * @see Configuration
 * 
 *
 */
public class JSLPreferencesView extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Configuration preferences;
	public ResourceBundle resource;
	
	//COMPONENTS
	private JPanel north, south;
	
	private JLabel SimSizeLabel;
	public JTextField widthField, heightField;
	
	private JLabel backgroundcolorLabel;
	public JButton colorButton;
	
	private JLabel MaxZlifesLabel,MaxZetatronLabel, MaxZretadorsLabel, MaxSeedsLabel;
	public JTextField MaxZlifesField,MaxZetatronField ,MaxZretadorsField, MaxSeedsField;
	
	private JLabel pathLabel;
	public JTextField pathField;
	
	private JLabel localeLabel;
	public JComboBox localeComboBox;
	
	@SuppressWarnings("unused")
	private JButton  okButton, cancelButton;
	
	private int width = 450;
	private int height = 320;
	
	/**
	 * Creates a new JSLPreferencesView using 'conf' Configuration
	 * @param conf Configuration
	 * @see Configuration
	 */
	public JSLPreferencesView(Configuration conf)
	{
		super();
		preferences = conf;
		resource = ResourceBundle.getBundle("StringAndLabels", preferences.getLocale() );
		this.setTitle(resource.getString( "prf_title" ));
		setPreferredSize(new Dimension(width,height));
		setLayout( new BorderLayout());
		setLocation((int)(preferences.getPlayfieldDimension().getWidth()/2-(width/2)),
					(int)(preferences.getPlayfieldDimension().getHeight()/2 -(height/2)) );
		/*
		 * NORTH PANEL has a GridLayout to divide panel into two row.
		 * If Parameter has more than one field or label, you must wrap they
		 * into a panel. See the setConfigValue() method to see how.
		 */
		north = new JPanel();
		north.setLayout( new GridLayout(0,2,5,5));
		
		south = new JPanel();
		south.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		//ADD CONFIGURATION COMPONENTS TO NORTH PANEL
		addPanelSizeComponents();
		addColorButton();
		addMaxSpritesComponent();
		addPathDirectory();
		addLanguageChooser();
		
		//ADD COMPONENTS TO THE SOUTH PANEL
		JButton okButton = new JButton(resource.getString( "prf_save" ));
		JButton cancelButton = new JButton(resource.getString( "prf_cancel" ));
		
		okButton.addActionListener(new SavePreferencesAction(this));
		cancelButton.addActionListener(new ActionListener()
		   {
			public void actionPerformed(ActionEvent actionEvent) 
				{ 
				dispose();
				}
		   });
		
		//ADD ICON
		this.setIconImage(java.awt.Toolkit.getDefaultToolkit().getImage
        		(this.getClass().getResource("/com/tabuto/jsimlife/icons/icon_alpha_48x48.gif")));
		
		
		south.add(okButton);
		south.add(cancelButton);
		
		add(north, BorderLayout.CENTER);
		add(south, BorderLayout.PAGE_END);
		
		pack();
		setVisible(true);
		
	}
	
	public Configuration getPreferences(){return preferences;}
	
	//ADD COMPONENTS METHODS
	private void addPanelSizeComponents()
	{
		/*
		 * Panel size has 3 components: JLabel display the parameter name
		 * and 2 field display panel width and height.
		 * subGroup panel wrap the JTextField into one.
		 * Then add to northPanel the label and the subGroup panel.
		 */
		JPanel subGroup = new JPanel();
		subGroup.setLayout(new FlowLayout(FlowLayout.LEFT));
		SimSizeLabel = new JLabel(" "+resource.getString( "prf_simPanelSize"));
		widthField = new JTextField(5);
		heightField= new JTextField(5);
		widthField.setText( String.valueOf( (int)preferences.getPlayfieldDimension().getWidth() ) );
		heightField.setText( String.valueOf((int) preferences.getPlayfieldDimension().getHeight()) );
		
		subGroup.add(widthField);
		subGroup.add(heightField);
		
		north.add(SimSizeLabel);
		north.add(subGroup);
	}
	
	private void addColorButton()
	{
		JPanel subGroup = new JPanel();
		subGroup.setLayout(new FlowLayout(FlowLayout.LEFT));
		backgroundcolorLabel = new JLabel(" "+resource.getString( "prf_simBGColor"));
		final Color panelColor = preferences.getBackgroundColor();
		colorButton = new JButton("");
		colorButton.setBackground(panelColor);
		
		colorButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent actionEvent)
			{
				 Color color = JColorChooser.showDialog(
				 rootPane, resource.getString( "prf_selectColor"),panelColor
				  );
				 if (color!= null)
				 {
					 colorButton.setBackground(color);
					 //config.setBackgroundColor(color );
				 }
			}
		
		});
		colorButton.setToolTipText(" "+resource.getString("prf_selectBGColor"));
		north.add(backgroundcolorLabel);
		subGroup.add(colorButton);
		north.add(subGroup);
		
	}
	
	private void addLanguageChooser()
	{
		JPanel subGroup = new JPanel();
		subGroup.setLayout(new FlowLayout(FlowLayout.LEFT));
		localeLabel = new JLabel(" "+ resource.getString( "prf_chooseLanguage"));
		
		localeComboBox = new JComboBox();
		localeComboBox.addItem(Locale.US);
		localeComboBox.addItem(Locale.ITALY);
		
		localeComboBox.setSelectedItem( preferences.getLocale());
		
		north.add(localeLabel);
		subGroup.add(localeComboBox);
		north.add(subGroup);
	}
	
	private void addMaxSpritesComponent()
	{

		JPanel group1 = new JPanel();
		group1.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		JPanel group2 = new JPanel();
		group2.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		JPanel group3 = new JPanel();
		group3.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		JPanel group4 = new JPanel();
		group4.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		MaxZlifesLabel = new JLabel(" "+ resource.getString( "prf_maxZlifeNumber"));
	    MaxZlifesField = new JTextField(5);
	    
	    MaxZetatronLabel = new JLabel(" "+ resource.getString( "prf_maxZretadorNumber"));
	    MaxZetatronField = new JTextField(5);
	    
	    MaxZretadorsLabel = new JLabel(" "+ resource.getString( "prf_maxZetatronNumber"));
	    MaxZretadorsField = new JTextField(5);
	    
	    MaxSeedsLabel = new JLabel(" "+ resource.getString( "prf_maxSeedNumber"));
	    MaxSeedsField = new JTextField(5);
	    
	    
	    MaxZlifesField.setText( String.valueOf( preferences.getMaxZlifes())); 
	    MaxZetatronField.setText( String.valueOf(preferences.getMaxZetatron() ) );
	    MaxZretadorsField.setText( String.valueOf(preferences.getMaxZretador() ) );
	    MaxSeedsField.setText( String.valueOf(preferences.getMaxSeeds() ) );
	    
	    north.add(MaxZlifesLabel);
	    group1.add(MaxZlifesField);
	    north.add(group1);
	    
	    north.add(MaxZetatronLabel);
	    group4.add(MaxZetatronField);
	    north.add(group4);
	    
	    north.add(MaxZretadorsLabel);
	    group2.add(MaxZretadorsField);
	    north.add(group2);
	    
	    north.add(MaxSeedsLabel);
	    group3.add(MaxSeedsField);
	    north.add(group3);
	    
	    
	}
	
	private void addPathDirectory()
	{
		JPanel field = new JPanel();
		field.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		pathLabel = new JLabel(" "+ resource.getString( "prf_jsimlifeCurrentDir"));
	    pathField = new JTextField(15);
	    pathField.setAutoscrolls(true);
	    pathField.setText( preferences.getPath()); 
	    
	    pathField.addMouseListener(new MouseListener()
		   {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				 try{
					  
			            JFileChooser fileChooser = new JFileChooser(" "+ resource.getString( "prf_selectWorkspace"));
			   
			            
			            File f = new File(pathField.getText()+"/.");
			            fileChooser.setCurrentDirectory(f);
			            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			            fileChooser.setAcceptAllFileFilterUsed(false);

			            
			            int n = fileChooser.showOpenDialog(JSLPreferencesView.this);
			            fileChooser.setLocation(100, 100);
			            if (n == JFileChooser.APPROVE_OPTION) 
			            	{	
			      					  pathField.setText(
			      							  fileChooser.getCurrentDirectory().getAbsolutePath()
			      							  );	  
			            	}
			          } catch (Exception ex) {}
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {}

			@Override
			public void mouseExited(MouseEvent arg0) {}

			@Override
			public void mousePressed(MouseEvent arg0) {}

			@Override
			public void mouseReleased(MouseEvent arg0) {}
		   });
	    
	    north.add(pathLabel);
	    field.add(pathField);
	    north.add(field);
	    
	}

	
}
