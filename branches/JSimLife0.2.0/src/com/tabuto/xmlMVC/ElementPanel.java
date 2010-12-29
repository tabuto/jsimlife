/**
* @author Francesco di Dio
* Date: 27/dic/2010 16.18.49
* Titolo: ElementPanel.java
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
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import org.w3c.dom.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

// ElementPanel dictates how datField is displayed as a row in the complete form
// This ElementPanel superclass merely arranges Name and Description (as a toolTip)
// Subclasses have eg TextFields etc

public class ElementPanel extends JPanel {
	
	// swing components
	Box horizontalBox;
	Component leftIndent = Box.createHorizontalStrut(0);
	JButton collapseButton;	
	JButton addTextValueButton;
	JTextField textValueField;
	JLabel nameLabel;

	Icon collapsedIcon;
	Icon notCollapsedIcon;
	
	public static final Dimension MINSIZE = new Dimension(30, 25);
	
	XmlElement xmlElement;
	
	public ElementPanel(XmlElement xmlElement) {
		
		this.xmlElement = xmlElement;
		
		// build the elementPanel panel
		Border eb = BorderFactory.createEmptyBorder(1, 3, 1, 3);
		this.setBorder(eb);
		this.setLayout(new BorderLayout());
		this.addMouseListener(new formPanelMouseListener());
		this.setMinimumSize(MINSIZE);
		
		horizontalBox = Box.createHorizontalBox();
		
		boolean subStepsCollapsed = xmlElement.getChildElementsCollapsed();
		
		collapseButton = new JButton();
		collapseButton.setVisible(false);	// only made visible if hasChildren
		collapseButton.setBackground(null);
		collapsedIcon = ImageFactory.getInstance().getIcon(ImageFactory.COLLAPSED_ICON);
		notCollapsedIcon = ImageFactory.getInstance().getIcon(ImageFactory.NOT_COLLAPSED_ICON);
		if (subStepsCollapsed) collapseButton.setIcon(collapsedIcon);
		else collapseButton.setIcon(notCollapsedIcon);
		collapseButton.setToolTipText("Collapse or expand child elements");
		collapseButton.setBorder(new EmptyBorder(2,2,2,2));
		collapseButton.addActionListener(new CollapseListener());
		horizontalBox.add(collapseButton);
		
		nameLabel = new JLabel(xmlElement.getName());
		nameLabel.setBorder(new EmptyBorder(3,0,3,0));
		nameLabel.addMouseListener(new formPanelMouseListener());
		
		addTextValueButton = new JButton("Add Text Value");
		addTextValueButton.setFont(XMLView.FONT_SMALL);
		addTextValueButton.addActionListener(new AddTextValueListener());
		addTextValueButton.setBackground(null);
		addTextValueButton.setVisible(false);	// only visible when element highlighted
		
		textValueField = new JTextField();
		textValueField.addFocusListener(new FocusLostUpdateListener());
		
		// add componenents	
		horizontalBox.add(leftIndent);
		horizontalBox.add(nameLabel, BorderLayout.WEST);
		horizontalBox.add(Box.createHorizontalStrut(10));
		
		if (xmlElement.getAttribute(XmlElement.TEXT_NODE_VALUE) == null) {
			horizontalBox.add(addTextValueButton);
		} else {
			horizontalBox.add(textValueField);
			textValueField.setText(xmlElement.getAttribute(XmlElement.TEXT_NODE_VALUE));
		}

		this.setBackground(null);
		this.add(horizontalBox, BorderLayout.NORTH);
	}
	
	// called by xmlElement to notfiy observers that something has changed.
	public void dataFieldUpdated() {
		setNameText(xmlElement.getName());
	}

	// overridden by subclasses if they have a value and text field
	public void setValue(String newValue) {}
	
	// this methods called when user updates the attributeEditor panel
	public void setNameText(String name) {
		nameLabel.setText(name);
	}
	
	
	
	// called when user clicks on panel
	public void setHighlighted(boolean highlight) {
		if (highlight) { 
			this.setBackground(XMLView.BLUE_HIGHLIGHT);
			addTextValueButton.setVisible(true);
		}
		else {
			this.setBackground(null);
			addTextValueButton.setVisible(false);
		}
	}
	
	public class formPanelMouseListener implements MouseListener {
		public void mouseEntered(MouseEvent event) {}
		public void mouseExited(MouseEvent event) {}
		public void mousePressed(MouseEvent event) {}
		public void mouseReleased(MouseEvent event) {}
		
		public void mouseClicked(MouseEvent event) {
			
			int clickType = event.getModifiers();
			if (clickType == XMLView.SHIFT_CLICK) {
				panelClicked(false);
			} else
				panelClicked(true);
		}
	}	
	
	public class FocusLostUpdateListener implements FocusListener {
		public void focusLost(FocusEvent event) {
			updateDataField();
		}
		public void focusGained(FocusEvent event) {}
	}
	
	// called when focus lost from text field
	public void updateDataField() {
		xmlElement.setAttribute(XmlElement.TEXT_NODE_VALUE, textValueField.getText());
	}
	
	public void panelClicked(boolean clearOthers) {
		xmlElement.formFieldClicked(clearOthers);
	}
	
	//public void checkForChildren() {
	//	refreshHasChildren(xmlElement.hasChildren());
	//}

	public void refreshHasChildren(boolean hasChildren) {
		if (!hasChildren) {
			xmlElement.setChildElementsCollapsed(false);
			collapseButton.setIcon(notCollapsedIcon);
		}
		collapseButton.setVisible(hasChildren);
	}
	
	public class CollapseListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			boolean collapsed = xmlElement.getChildElementsCollapsed();
	
			collapsed = (!collapsed);
			
			xmlElement.setChildElementsCollapsed(collapsed);
			
			if (collapsed){
				collapseButton.setIcon(collapsedIcon);
			} else {
				collapseButton.setIcon(notCollapsedIcon);
			}
			refreshTitleCollapsed();
		
		}
	}
	
	// called when elementPanel panel is loaded into UI, so that it is displayed correctly
	// also called when user collapses or expands sub-steps
	public void refreshTitleCollapsed() {
		boolean subStepsCollapsed = xmlElement.getChildElementsCollapsed();
		// tells node whether to hide the childBox containing child panels
		xmlElement.hideChildren(subStepsCollapsed);
		
		refreshHasChildren(xmlElement.hasChildren());
	}
	
	public class AddTextValueListener implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			xmlElement.setAttribute(XmlElement.TEXT_NODE_VALUE, "");
			horizontalBox.add(textValueField);
			horizontalBox.remove(addTextValueButton);
		}
		
	}
	
	// overridden by subclasses that have input components, 
	// .. to disable them when in template-edit mode
	public void setExperimentalEditing(boolean enabled) {}

}
