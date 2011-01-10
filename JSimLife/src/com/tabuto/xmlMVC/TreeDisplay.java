/**
* @author Francesco di Dio
* Date: 27/dic/2010 16.23.14
* Titolo: TreeDisplay.java
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



import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.Iterator;

public class TreeDisplay extends JPanel {
	
	private XMLView parentXMLView;
	
	int childLeftIndent = 40;
	
	Box verticalFormBox;
	
	TreeDisplay(XMLView parent) {
		
		parentXMLView = parent;
		
		this.setLayout(new BorderLayout());
		this.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		verticalFormBox = Box.createVerticalBox();
		this.add(verticalFormBox, BorderLayout.NORTH);
				
		// get the elementPanel JPanel from the xmlElement
		if (parentXMLView.getRootNode() != null) {
			JPanel newFormField = parentXMLView.getRootNode().getXmlElement().getFormField();
			verticalFormBox.add(newFormField);
			
			// pass the node and the Box that already contains it to buildFormTree()
			// this will get the nodes children and add them to the Box (within a new Box)
			buildFormTree(parentXMLView.getRootNode(), verticalFormBox);
		}
	}
	
	TreeDisplay(XmlNode rootNode) {
		
		this.setLayout(new BorderLayout());
		this.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		verticalFormBox = Box.createVerticalBox();
		this.add(verticalFormBox, BorderLayout.NORTH);
				
		// get the elementPanel JPanel from the xmlElement
		if (rootNode != null) {
			JPanel newFormField = rootNode.getXmlElement().getFormField();
			verticalFormBox.add(newFormField);
			
			// pass the node and the Box that already contains it to buildFormTree()
			// this will get the nodes children and add them to the Box (within a new Box)
			buildFormTree(rootNode, verticalFormBox);
		}
	}
	
//	 this will get the node's children and add them to the Box (within a new Box)
	public void buildFormTree(XmlNode dfNode, Box verticalBox) {
		
		ArrayList<XmlNode> children = dfNode.getChildren();
		
		Box childBox = Box.createVerticalBox();
		childBox.setBorder(BorderFactory.createEmptyBorder(0, childLeftIndent, 0, 0));
		// the node gets a ref to the Box (used for collapsing. Box becomes hidden)
		dfNode.setChildBox(childBox);
		//	set visibility of the childBox wrt collapsed boolean of xmlElement
		//	 & sets collapse button visible if dataFieldNode has children
		dfNode.getXmlElement().refreshTitleCollapsed();
	
		// for each child, get their JPanel, add it to the childBox
		for (XmlNode child: children){
			JPanel newFormField = child.getXmlElement().getFormField();
			childBox.add(newFormField);
			// recursively build the tree below each child
			buildFormTree(child, childBox);
		}
		// add the new childBox to it's parent
		verticalBox.add(childBox);
	}
	
	public void refreshForm() {
		// update reference to the root
		XmlNode protocolRootNode = parentXMLView.getRootNode();
		
		verticalFormBox.setVisible(false);	// otherwise if the new form is smaller, old one still visible
		
		this.remove(verticalFormBox);
		
		verticalFormBox = Box.createVerticalBox();
		
		JPanel newFormField = protocolRootNode.getXmlElement().getFormField();
		verticalFormBox.add(newFormField);
		
		buildFormTree(protocolRootNode, verticalFormBox);
		
		this.add(verticalFormBox, BorderLayout.NORTH);
		this.getParent().getParent().validate();
		this.invalidate();
		this.repaint();
		
	}
	
}
