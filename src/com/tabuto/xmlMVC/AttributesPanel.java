/**
* @author Francesco di Dio
* Date: 27/dic/2010 16.19.17
* Titolo: AttributesPanel.java
* Versione: 0.1 Rev.a:
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

package com.tabuto.xmlMVC;



import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.w3c.dom.*;

public class AttributesPanel extends JPanel{
	
	public static final Dimension MINIMUM_SIZE = new Dimension(290,300);
	
	JPanel attributeFieldsPanel;
	AttributeEditor nameFieldEditor;
	
	Box customAttributesBox;
	
	ArrayList<AttributeEditor> customAttributesFields = new ArrayList<AttributeEditor>();
	

	boolean textChanged;
	
	//XMLView xmlView; 	// the UI container for displaying this panel
	
	XmlElement xmlElement;
	
	public AttributesPanel() {	// a blank 
		this.setPreferredSize(MINIMUM_SIZE);
		this.setMinimumSize(MINIMUM_SIZE);
	}
	
	public AttributesPanel(XmlElement xmlElement) {
		this.xmlElement = xmlElement;
		buildPanel();
		
	}
	
	public void buildPanel() {
		
		attributeFieldsPanel = new JPanel();	// a sub-panel to hold all components
		attributeFieldsPanel.setLayout(new BoxLayout(attributeFieldsPanel, BoxLayout.Y_AXIS));
		attributeFieldsPanel.setBorder(new EmptyBorder(5, 5, 5,5));
		
		nameFieldEditor = new AttributeEditor("Element Name", xmlElement.getName());
		attributeFieldsPanel.add(nameFieldEditor);
		
		this.setLayout(new BorderLayout());
		this.add(attributeFieldsPanel, BorderLayout.NORTH);
		
		this.setPreferredSize(MINIMUM_SIZE);
		this.setMinimumSize(MINIMUM_SIZE);
		this.validate();
		
		
		customAttributesBox = Box.createVerticalBox();
		
		displayAllAttributes();
		
		attributeFieldsPanel.add(customAttributesBox);
	}
	
	
	public void displayAllAttributes() {
		LinkedHashMap<String, String> allAttributes = xmlElement.getAllAttributes();
		
		Iterator keyIterator = allAttributes.keySet().iterator();
		
		while (keyIterator.hasNext()) {
			String name = (String)keyIterator.next();
			String value = allAttributes.get(name);
			
			// don't repeat the name
			if (name.equals(XmlElement.ELEMENT_NAME) || (name.equals(XmlElement.TEXT_NODE_VALUE))) continue;
			if (value == null) continue;
			
			AttributeEditor attributeEditor = new AttributeEditor(name, value);
			// keep a list of fields
			customAttributesFields.add(attributeEditor);
			customAttributesBox.add(attributeEditor);
		}
		
		JButton addAttributeButton = new JButton("Add Attribute");
		addAttributeButton.addActionListener(new AddAttributeListener());
		customAttributesBox.add(addAttributeButton);
	}
	
	
	public class AddAttributeListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			addAttribute();
		}
	
	}
	
	public void addAttribute() {
		
		String attributeName = (String)JOptionPane.showInputDialog(
                this,
                "Enter new attribute name:",
                "Attribute Name",
                JOptionPane.PLAIN_MESSAGE,
                null, // icon
                null,"");

		if (attributeName != null) {
			attributeName = attributeName.trim();
			attributeName = attributeName.replace(" ", "");
			if (attributeName.length() > 0)	{
				xmlElement.setAttribute(attributeName, "");
				xmlElementUpdated();
			}
		}
	}
	
	
//	called by xmlElement when something changes. 
	public void xmlElementUpdated() {
		nameFieldEditor.setTextFieldText(xmlElement.getAttribute(XmlElement.ELEMENT_NAME));
		
		customAttributesBox.removeAll();
		customAttributesFields.clear();
		
		displayAllAttributes();
		customAttributesBox.validate();
	}
	
	// called when focus lost
	public void updateDataField() {
		
		String elementName = nameFieldEditor.getTextFieldText();
		elementName = elementName.replace(" ", "");
		
		xmlElement.setName(elementName, false);
		
		updateModelsOtherAttributes();	// saves all other attributes
		
		xmlElement.notifyDataFieldObservers();
	}
	
//	called when focus lost.
	public void updateModelsOtherAttributes() {	
		
		for (AttributeEditor field: customAttributesFields) {
			xmlElement.setAttribute(field.getAttributeName(), field.getTextFieldText(), false);
		}
		
	}
	public class AttributeEditor extends JPanel implements ActionListener{
			
		JTextField attributeTextField;
		String attributeName;
		// constructor creates a new panel and adds a name and text field to it.
		public AttributeEditor(String label, String Value) {
			attributeName = label;
			this.setBorder(new EmptyBorder(3,3,3,3));
			JLabel attributeNameLabel = new JLabel(attributeName + ": ");
			attributeTextField = new JTextField(Value);
			attributeTextField.setColumns(15);
			this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
			attributeTextField.addKeyListener(new textChangedListener());
			attributeTextField.addFocusListener(new focusChangedListener());
			Icon deleteIcon = ImageFactory.getInstance().getIcon(ImageFactory.N0);
			JButton deleteButton = new JButton(deleteIcon);
			deleteButton.setBorder(new EmptyBorder(0,5,0,0));
			deleteButton.addActionListener(this);
			deleteButton.setToolTipText("Delete this attribute");
			
			this.add(attributeNameLabel);
			this.add(attributeTextField);
			
			if (!attributeName.equals("Element Name"))
				this.add(deleteButton);
		}
			
		public String getTextFieldText() {
				return attributeTextField.getText();
		}
		public String getAttributeName() {
			return attributeName;
		}
		public void setTextFieldText(String text) {
			attributeTextField.setText(text);
		}

		public void actionPerformed(ActionEvent e) {
			xmlElement.setAttribute(attributeName, null);
		}
	}

	
	public class textChangedListener implements KeyListener {
		
		public void keyTyped(KeyEvent event) {
			textChanged = true;

			char keyChar = event.getKeyChar();
			int keyCharacter = (int)keyChar;
			if (keyCharacter == 10) {	// == "Enter"
				updateDataField();
			}

		}
		public void keyPressed(KeyEvent event) {}
		public void keyReleased(KeyEvent event) {}
	
	}
	
	public class focusChangedListener implements FocusListener {
		
		public void focusLost(FocusEvent event) {
			if (textChanged) {
				updateDataField();
				textChanged = false;
			}
		}
		public void focusGained(FocusEvent event) {}
	}

}
