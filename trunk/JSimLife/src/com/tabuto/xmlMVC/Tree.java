/**
* @author Francesco di Dio
* Date: 27/dic/2010 16.20.56
* Titolo: Tree.java
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



import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;

import javax.swing.JPanel;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Tree {
	
	private XmlNode rootNode;		// the root of the xmlElement tree. 
	
	private ArrayList<XmlNode> highlightedFields;
	
	public final static String ELEMENT = "element";
	
	private SelectionObserver selectionObserver;
	private XMLUpdateObserver xmlUpdateObserver;
	
	
	public Tree(Document document, SelectionObserver selectionObserver, XMLUpdateObserver xmlObserver) {
		
		this.selectionObserver = selectionObserver;
		this.xmlUpdateObserver = xmlObserver;
		highlightedFields = new ArrayList<XmlNode>();
		
		Element rootElement = document.getDocumentElement();
			
		LinkedHashMap<String, String> allAttributes = new LinkedHashMap<String, String>();

		parseElementToMap(rootElement, allAttributes);
			 
		rootNode = new XmlNode(allAttributes, this);
			
		buildTreeFromDOM(rootNode, rootElement);
	}
	
	public Tree(Document document) {
		highlightedFields = new ArrayList<XmlNode>();
		
		Element rootElement = document.getDocumentElement();
			
		LinkedHashMap<String, String> allAttributes = new LinkedHashMap<String, String>();

		parseElementToMap(rootElement, allAttributes);
			 
		rootNode = new XmlNode(allAttributes, this);
			
		buildTreeFromDOM(rootNode, rootElement);
	}
	
	public Tree(SelectionObserver selectionObserver, XMLUpdateObserver xmlObserver) {
		rootNode = new XmlNode(this);
		this.selectionObserver = selectionObserver;
		this.xmlUpdateObserver = xmlObserver;
		highlightedFields = new ArrayList<XmlNode>();
	}
	
//	 start a blank protocol
	public void openBlankXmlFile() {
		
		// the root of the xmlElement tree
		rootNode = new XmlNode(this);
		XmlElement rootField = rootNode.getXmlElement();
		
		rootField.setName("rootElement", true);
		
		XmlNode newNode = new XmlNode(rootNode, this);// make a new default-type field
		rootNode.addChild(newNode);
	}
	
	
	public void buildTreeFromDOM(XmlNode dfNode, Element inputElement) {
		
		NodeList children = inputElement.getChildNodes();
		
		for (int i=0; i < children.getLength(); i++) {
			
			// skip any empty (text) nodes
			Node node = children.item(i);
	
			 
			 if (node != null && (node.getNodeType() == Node.ELEMENT_NODE)) {
				 Element element = (Element)node; 
				 LinkedHashMap<String, String> allAttributes = new LinkedHashMap<String, String>();

				 parseElementToMap(element, allAttributes);
				 
				String textValue = node.getTextContent().trim();
				if (textValue.length() > 0){
					allAttributes.put(XmlElement.TEXT_NODE_VALUE, textValue);
				 }
				
				 XmlNode newNode = new XmlNode(allAttributes, dfNode, this);
				 dfNode.addChild(newNode);
				 buildTreeFromDOM(newNode, element);
			 }
			 
			 
		}
		
	}
	
	public void parseElementToMap(Element element, LinkedHashMap<String, String> allAttributes) {
		 String attributeValue;
		 String attribute;
		 
		 NamedNodeMap attributes = element.getAttributes();
		 for (int i=0; i<attributes.getLength(); i++) {
			 attribute = attributes.item(i).getNodeName();
			 attributeValue = attributes.item(i).getNodeValue();
	
			 if (attributeValue != null) {
				allAttributes.put(attribute, attributeValue);
			 }
		 }
		 
		 String elementName = element.getNodeName();
		 // if the xml file's elements don't have "elementName" attribute, use the <tagName>
		 if (allAttributes.get(XmlElement.ELEMENT_NAME) == null) {
			 allAttributes.put(XmlElement.ELEMENT_NAME, elementName);
		 }
	}


//	 duplicate a xmlElement and add it at after last selected
	public void duplicateAndInsertElements() {
		
		if (highlightedFields.isEmpty()) return;
		
		// highlighted fields change while adding. Make a copy first
		ArrayList<XmlNode> tempArray = new ArrayList<XmlNode>(highlightedFields);
		
		copyAndInsertElements(tempArray);
	}
	
	
	// returns a duplicate node with no parent
	public XmlNode duplicateDataFieldNode(XmlNode highlightedField) {
		LinkedHashMap<String, String> allAttributes = highlightedField.getXmlElement().getAllAttributes();
//		 get all attributes of the datafield
		LinkedHashMap<String, String> newAttributes = new LinkedHashMap<String, String>(allAttributes);
		
		XmlNode newNode = new XmlNode(newAttributes, this);
		
		return newNode;
	}

	public void duplicateDataFieldTree(XmlNode oldNode, XmlNode newNode) {
		
		ArrayList<XmlNode> children = oldNode.getChildren();
		if (children.size() == 0) return;
			
			for (XmlNode child: children){
			 
				XmlNode newChild = duplicateDataFieldNode(child);
				newChild.setParent(newNode);
				
				newNode.addChild(newChild);
				
				duplicateDataFieldTree(child, newChild);
			}
	}

	public void insertProtocolFromNewFile(Document document) {
	
		if (highlightedFields.size() == 0) return;
	
		XmlNode lastSelectedNode = highlightedFields.get(highlightedFields.size()-1);

		XmlNode parentNode = lastSelectedNode.getParentNode();
		int indexToInsert = lastSelectedNode.getMyIndexWithinSiblings() + 1;
	
		Element protocol = document.getDocumentElement();
	
		LinkedHashMap<String, String> allAttributes = new LinkedHashMap<String, String>();
		parseElementToMap(protocol, allAttributes);
	
		XmlNode newNode = new XmlNode(allAttributes, parentNode, this);
		parentNode.addChild(indexToInsert, newNode);
	
		buildTreeFromDOM(newNode, protocol);
	}
	
	
	public void demoteDataFields() {
		
		if (highlightedFields.isEmpty()) return;
		
		// fields need to become children of their preceeding sibling (if they have one)
		XmlNode firstNode = highlightedFields.get(0);
		int indexOfFirstSibling = firstNode.getMyIndexWithinSiblings();
		
		// if no preceeding sibling, can't demote
		if (indexOfFirstSibling == 0) return;
		
		XmlNode parentNode = firstNode.getParentNode();
		XmlNode preceedingSiblingNode = parentNode.getChild(indexOfFirstSibling-1);
		
		// move nodes
		for (XmlNode highlightedField: highlightedFields) {
			preceedingSiblingNode.addChild(highlightedField);
			highlightedField.setParent(preceedingSiblingNode);
		}
//		 delete them from the end (reverse order)
		for (int i=highlightedFields.size()-1; i>=0; i--) {
			parentNode.removeChild(highlightedFields.get(i));
		}
	}
	
	public void promoteDataFields() {
		
		if (highlightedFields.size() < 1) return;
		XmlNode node = highlightedFields.get(0);
		XmlNode parentNode = node.getParentNode();
		XmlNode grandParentNode = parentNode.getParentNode();
		// if parent is root (grandparent null) then can't promote
		if (grandParentNode == null) return;
		
		// any fields that are children of the last to be promoted, 
		// must first become children of that node. 
		XmlNode lastNode = highlightedFields.get(highlightedFields.size()-1);
		XmlNode lastNodeParent = lastNode.getParentNode();
		
		int indexOfLast = lastNodeParent.indexOfChild(lastNode);
		int numChildren = lastNodeParent.getChildren().size();
		
		// copy children in correct order
		for (int i=indexOfLast+1; i< numChildren; i++) {
			XmlNode nodeToCopy = lastNodeParent.getChild(i);
			lastNode.addChild(nodeToCopy);
			nodeToCopy.setParent(lastNode);
		}
		// delete them from the end (reverse order)
		for (int i=numChildren-1; i>indexOfLast; i--) {
			lastNodeParent.removeChild(lastNodeParent.getChild(i));
		}
		
		// loop backwards so that the top field is last added, next to parent
		for (int i=highlightedFields.size()-1; i >=0; i--) {
			promoteDataField(highlightedFields.get(i));
		}
	}
	
	// promotes a xmlElement to become a sibling of it's parent
	public void promoteDataField(XmlNode node) {
		
		XmlNode parentNode = node.getParentNode();
		XmlNode grandParentNode = parentNode.getParentNode();
		
		// if parent is root (grandparent null) then can't promote
		if (grandParentNode == null) return;
		
		int indexOfParent = grandParentNode.indexOfChild(parentNode);
		
		grandParentNode.addChild(indexOfParent + 1, node);	// adds after parent
		node.setParent(grandParentNode);
		parentNode.removeChild(node);
	}
	
//	 if the highlighted fields have a preceeding sister, move it below the highlighted fields
	public void moveFieldsUp() {
		
		if (highlightedFields.size() == 0) return;
		
		int numFields = highlightedFields.size();

		XmlNode firstNode = highlightedFields.get(0);
		int firstNodeIndex = firstNode.getMyIndexWithinSiblings();
		if (firstNodeIndex < 1) return;		// can't move fields up.
		
		XmlNode parentNode = firstNode.getParentNode();
		XmlNode preceedingNode = parentNode.getChild(firstNodeIndex - 1);
		// add the preceeding node after the last node
		parentNode.addChild(firstNodeIndex + numFields, preceedingNode);
		parentNode.removeChild(preceedingNode);
	}
	
//	 if the highlighted fields have a preceeding sister, move it below the highlighted fields
	public void moveFieldsDown() {
		
		if (highlightedFields.size() == 0) return;
		
		int numFields = highlightedFields.size();

		XmlNode lastNode = highlightedFields.get(numFields-1);
		XmlNode parentNode = lastNode.getParentNode();
		
		int lastNodeIndex = lastNode.getMyIndexWithinSiblings();
		if (lastNodeIndex == parentNode.getChildren().size() - 1) return;	// can't move fields down.
	
		XmlNode succeedingNode = parentNode.getChild(lastNodeIndex + 1);
		// add the succceeding node before the first node
		int indexToMoveTo = lastNodeIndex - numFields + 1;
		parentNode.addChild(indexToMoveTo, succeedingNode);
		// remove the succeeding node (now 1 more position down the list - after inserting above)
		parentNode.removeChild(lastNodeIndex + 2);
	}
	
	public void buildDOMfromTree(Document document) {

		try {
			// DocumentBuilder db = dbf.newDocumentBuilder();
			//document = db.newDocument();
			XmlElement rootElement = rootNode.getXmlElement();
			
			String elementName = rootElement.getName();
			if (elementName == null) elementName = "rootElement";
			Element element = document.createElement(elementName);  

			// get all attributes of the element
			LinkedHashMap<String, String> allAttributes = rootElement.getAllAttributes();
			parseAttributesMapToElement(allAttributes, element);
			
			document.appendChild(element);
			System.out.println("Tree.buildDOMfromTree appendedChild: " + element.getNodeName());
			
			buildDOMchildrenFromTree(document, rootNode, element);
			
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
	}
	
	public void buildDOMchildrenFromTree(Document document, XmlNode rootNode, Element rootElement) {
		
		ArrayList<XmlNode> childNodes = rootNode.getChildren();
		if (childNodes.size() == 0) return;

		
		for (XmlNode child: childNodes) {
			
			XmlElement xmlElement = child.getXmlElement();
			
			String elementName = xmlElement.getName();
			if (elementName == null) elementName = "element";
			
			Element element = document.createElement(elementName);
			
			LinkedHashMap<String, String> allAttributes = xmlElement.getAllAttributes();
			parseAttributesMapToElement(allAttributes, element);
			
			element.setTextContent(xmlElement.getAttribute(XmlElement.TEXT_NODE_VALUE));
			
			rootElement.appendChild(element);
			
			buildDOMchildrenFromTree(document, child, element);
		}  // end for
	}
	
	public void parseAttributesMapToElement(LinkedHashMap<String, String> allAttributes, Element element) {
		
		Iterator keyIterator = allAttributes.keySet().iterator();
		
		while (keyIterator.hasNext()) {
			String key = (String)keyIterator.next();
			String value = allAttributes.get(key);
			
			if (value != null) {
				
				if ((!key.equals(XmlElement.TEXT_NODE_VALUE)) && (!key.equals(XmlElement.ELEMENT_NAME)))
				element.setAttribute(key, value);
				
				// System.out.println("Tree.parseAttributesMapToElement key = " + key + ", value = " + value);
			}
		}
	}
	
	// add a blank xmlElement
	public void addElement() {
		XmlNode newNode = new XmlNode(rootNode, this);// make a new default-type field
		addElement(newNode);
	}
	
	
//	 copy and add new elements
	// used by import, paste, and duplicate functions
	public void copyAndInsertElements(ArrayList<XmlNode> XmlNodes) {

		if (XmlNodes.isEmpty()) return;
		
		//remember the first node added, so all new nodes can be selected when done
		XmlNode firstNewNode = null;
		
		for (int i=0; i< XmlNodes.size(); i++){
			
			XmlNode newNode = duplicateDataFieldNode(XmlNodes.get(i));
			duplicateDataFieldTree(XmlNodes.get(i), newNode);
			
			addElement(newNode);	// adds after last selected field.
			
			if (i == 0) firstNewNode = newNode;
		}
		
		nodeSelected(firstNewNode, false);   // will select the range 
	}
	
//	 copy and add new xmlElement
	public void copyAndInsertElement(XmlNode node) {
			
		XmlNode newNode = duplicateDataFieldNode(node);
		duplicateDataFieldTree(node, newNode);
			
		addElement(newNode);
		
	}
	
//	 add a new xmlElement after the last highlighted xmlElement, then highlights the new field
	public void addElement(XmlNode newNode) {
		
		// get selected Fields and add xmlElement after last seleted one

		XmlNode lastDataField = null;
		
		if (highlightedFields.size() > 0) {
			lastDataField = highlightedFields.get(highlightedFields.size()-1);
		} else {
			// add after last child of protocol (if there are any!)
			int numChildren = getRootNode().getChildren().size();
			if (numChildren > 0) 
				lastDataField = rootNode.getChild(numChildren - 1);
			// otherwise, lastDataField is null, and new xmlElement will be 1st child of protocol
		}
		
		// if no xmlElement selected (none exist!), add at top (1st child of protocol)
		if (lastDataField == null) {
			newNode.setParent(rootNode);
			rootNode.addChild(newNode);
			nodeSelected(newNode, true);
		}
		else {
		// otherwise, add after the xmlElement.
			XmlNode parentNode = lastDataField.getParentNode();
			int indexToInsert = lastDataField.getMyIndexWithinSiblings() + 1;

			newNode.setParent(parentNode);
			parentNode.addChild(indexToInsert, newNode);
			
			nodeSelected(newNode, true);
		}
	}
	
//	 delete the highlighted dataFields
	public void deleteElements(boolean saveChildren) {
		for (XmlNode node: highlightedFields) {
			
			if (saveChildren) promoteAllChildrenToSiblings(node);

			XmlNode parentNode = node.getParentNode();
			parentNode.removeChild(node);
		}
		highlightedFields.clear();
	}
	
	public void promoteAllChildrenToSiblings(XmlNode node) {
		
		ArrayList<XmlNode> children = node.getChildren();
		XmlNode parentNode = node.getParentNode();
		int nodeIndex = node.getMyIndexWithinSiblings();
		
		for (int i=children.size() -1; i >=0; i--) {
			parentNode.addChild(nodeIndex + 1, children.get(i));
			children.get(i).setParent(parentNode);
			node.removeChild(children.get(i));
		}
		
	}
	
	
	public void nodeSelected(XmlNode selectedNode, boolean clearOthers) {
		
		// always need to deselect rootNode
		rootNode.setHighlighted(false);
		if (selectedNode.getParentNode() == null) {
			rootNode.setHighlighted(true);
		}
		
		if (clearOthers) {
			// clear highlighting from all other xmlElement panels
			for (XmlNode highlightedNode: highlightedFields) {
				highlightedNode.setHighlighted(false);
			} 
			highlightedFields.clear();
			
		} else {	
			// if user tries to select multiple fields, they must have same parent
			// otherwise duplicate and delete operations become very confusing!
			// if no parent node, then this is protocol root node.
			
			XmlNode clickedNodeParent = selectedNode.getParentNode();
			
			for (int i=highlightedFields.size()-1; i>=0; i--) {
				XmlNode parent = highlightedFields.get(i).getParentNode();
				
				// if parent of an already selected field is not the same as..
				// the clicked-field's parent, de-select it.
				if (!(parent.equals(clickedNodeParent))) {
					highlightedFields.get(i).setHighlighted(false);
					highlightedFields.remove(i);
				}
			}
		}
		
		// add xmlElement to selected fields (if not protocol (root) field)
		if (selectedNode.getParentNode() != null) 
			addToHighlightedFields(selectedNode);
		
		if (selectionObserver != null) selectionObserver.selectionChanged();
	}
	
//	 need to make sure that highlighted fields (siblings) are sorted in their sibling order
	// and that only consecutive siblings are selected
	public void addToHighlightedFields(XmlNode xmlNode) {
		
		// if empty, just add
		if (highlightedFields.size() == 0)  {
			highlightedFields.add(xmlNode);
			xmlNode.setHighlighted(true);
		}
		
		// need to highlight all fields between currently selected fields and newly selected field
		else {
			int siblingIndex = xmlNode.getMyIndexWithinSiblings();
			
			// get the max and min indexes of highlighted fields
			int highlightedIndexMax = highlightedFields.get(0).getMyIndexWithinSiblings();
			int highlightedIndexMin = highlightedFields.get(0).getMyIndexWithinSiblings();
			for (XmlNode highlightedField: highlightedFields) {
				int index  = highlightedField.getMyIndexWithinSiblings();
				if (index > highlightedIndexMax) highlightedIndexMax = index;
				if (index < highlightedIndexMin) highlightedIndexMin = index;
			}
			
			XmlNode parentNode = xmlNode.getParentNode();
			
			// if so, add at end of list or at the start
			if (siblingIndex > highlightedIndexMax) {
				for (int i=highlightedIndexMax +1; i<siblingIndex + 1 ; i++) {
					XmlNode siblingDataFieldNode = parentNode.getChild(i);
					highlightedFields.add(siblingDataFieldNode);
					siblingDataFieldNode.setHighlighted(true);
				}
			}
			if (siblingIndex < highlightedIndexMin) {
				for (int i=highlightedIndexMin -1; i>siblingIndex - 1 ; i--) {
					XmlNode siblingDataFieldNode = parentNode.getChild(i);
					highlightedFields.add(0, siblingDataFieldNode);
					siblingDataFieldNode.setHighlighted(true);
				}
			}
		}
		
	}
	

	public XmlNode getRootNode() {
		return rootNode;
	}
	
	public JPanel getAttributeEditorToDisplay() {
		
		JPanel currentFieldEditor;
		
		if (highlightedFields.size() == 1) {
			currentFieldEditor = highlightedFields.get(0).getXmlElement().getFieldEditor();
		}
		else if (rootNode.getHighlighted()) {
			currentFieldEditor = rootNode.getXmlElement().getFieldEditor();
		}
		else
			currentFieldEditor = new AttributesPanel();
		
		return currentFieldEditor;
	}
	
	public void dataFieldUpdated() {
		if (xmlUpdateObserver != null) xmlUpdateObserver.xmlUpdated();
	}
	
	public ArrayList<XmlNode> getHighlightedFields() {
		return highlightedFields;
	}
}
