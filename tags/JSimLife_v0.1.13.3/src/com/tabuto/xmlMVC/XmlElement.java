

package com.tabuto.xmlMVC;



import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.concurrent.LinkedBlockingQueue;

import javax.swing.JPanel;

import org.w3c.dom.*;

public class XmlElement {
	
	// attribute values
	boolean childElementsCollapsed = false;
	
	public static final String ELEMENT_NAME = "elementName";
	public static final String TEXT_NODE_VALUE = "textNodeValue";
	
	public static final String TRUE = "true";
	public static final String FALSE = "false";

	// Element has attributes stored in LinkedHashMap
	LinkedHashMap<String, String> allAttributesMap;
	
	// the two JPanels that display the xmlElement, and hold optional attributes
	ElementPanel elementPanel;
	AttributesPanel attributesPanel;
	
	// the node of the xmlElement tree structure that holds this datafield
	XmlNode node;
	
	public XmlElement(XmlNode node) {
		this.node = node;

		allAttributesMap = new LinkedHashMap<String, String>();
		setName("element", false);	// have to have a name
		
		elementPanel = new ElementPanel(this);
		attributesPanel = new AttributesPanel(this);
	}
	
	public XmlElement(LinkedHashMap<String, String> allAttributesMap, XmlNode node) {
		
		this.node = node;
		
		this.allAttributesMap = allAttributesMap;

		elementPanel = new ElementPanel(this);
		attributesPanel = new AttributesPanel(this);
	}
	
	public void setAttribute(String name, String value, boolean notifyObservers) {
		allAttributesMap.put(name, value);
		if (notifyObservers) notifyDataFieldObservers();
	}
	
	public void setAttribute(String name, String value) {
		allAttributesMap.put(name, value);
		notifyDataFieldObservers();
	}
	
	public String getAttribute(String name) {
		return allAttributesMap.get(name);
	}
	
	
	
	// export method. return a LinkedHashMap with all name-value 
	public LinkedHashMap<String, String> getAllAttributes() {

		return allAttributesMap;
	}

	// called when changes are made to allAttributesMap
	public void notifyDataFieldObservers() {
		// System.out.println("XmlElement.NOTIFYXMLOBSERVERS! " + getName());
		elementPanel.dataFieldUpdated();
		attributesPanel.xmlElementUpdated();
		node.dataFieldUpdated();
	}
	
	public void setHighlighted(boolean highlighted) {
		elementPanel.setHighlighted(highlighted);
	}
	
	public JPanel getFieldEditor() {
		return attributesPanel;
	}
	
	public JPanel getFormField() {
		return elementPanel;
	}
	
	public String getName() {
		if ((getAttribute(XmlElement.ELEMENT_NAME) != null) && (getAttribute(XmlElement.ELEMENT_NAME).length() > 0))
			return getAttribute(XmlElement.ELEMENT_NAME);
		else return "element";		// have to return SOME text or elementPanel panel may be v.v.small!
	}
	//	 used to update xmlElement etc when attributeEditor panel is edited
	public void setName(String newName, boolean notifyObservers) {
		setAttribute(XmlElement.ELEMENT_NAME, newName, notifyObservers);
	}
	
	public XmlNode getNode() {
		return node;
	}
	
	public boolean hasChildren() {
		boolean hasChildren = (!(getNode().children.isEmpty()));
		return hasChildren;
	}

	public void refreshTitleCollapsed() {
		elementPanel.refreshTitleCollapsed();
	}
	public void setExperimentalEditing(boolean enabled) {
		elementPanel.setExperimentalEditing(enabled);
	}
	public void formFieldClicked(boolean clearOthers) {
		node.nodeClicked(clearOthers);
	}
	
	// hides the children
	public void hideChildren(boolean collapsed) {
		node.hideChildren(collapsed);
	}
	
	public boolean getChildElementsCollapsed() {
		return childElementsCollapsed;
	}
	public void setChildElementsCollapsed(boolean collapsed) {
		childElementsCollapsed = collapsed;
	}

}
