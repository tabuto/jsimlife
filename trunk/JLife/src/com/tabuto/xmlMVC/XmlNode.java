

package com.tabuto.xmlMVC;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;

import javax.swing.Box;

import org.w3c.dom.Element;

public class XmlNode {
	
	XmlElement xmlElement;
	
	boolean highlighted = false; 	// is this node/xmlElement selected (displayed blue)
	
	Tree tree;		// class that manages tree structure (takes click commands)
	ArrayList<XmlNode> children;
	XmlNode parent;
	Box childBox;	// swing component that holds all subtree. Hide when collapse
	
	// constructor 
	public XmlNode(LinkedHashMap<String, String> allAttributesMap, XmlNode parent, Tree tree) {
		
		children = new ArrayList<XmlNode>();
		xmlElement = new XmlElement(allAttributesMap, this);
		this.parent = parent;
		this.tree = tree;
	}
	
	// this constructor used for root node (no parent)
	public XmlNode(LinkedHashMap<String, String> allAttributesMap,  Tree tree) {
		children = new ArrayList<XmlNode>();
		xmlElement = new XmlElement(allAttributesMap, this);
		this.parent = null;
		this.tree = tree;
	}
	// this constructor used for blank root node (no parent)
	public XmlNode( Tree tree) {
		children = new ArrayList<XmlNode>();
		xmlElement = new XmlElement(this);
		this.parent = null;
		this.tree = tree;
	}
	
	// constructor for blank xmlElement
	public XmlNode(XmlNode parent,  Tree tree) {
		
		children = new ArrayList<XmlNode>();
		xmlElement = new XmlElement(this);
		this.parent = parent;
		this.tree = tree;
	}
	
	public int getMyIndexWithinSiblings() {
		int index = parent.indexOfChild(this);
		return index;
	}
	
	public void setParent(XmlNode parent) {
		this.parent = parent;
	}

	
	public void addChild(XmlNode xmlNode) {
		children.add(xmlNode);
	}
	public void addChild(int index, XmlNode xmlNode) {
		children.add(index, xmlNode);
	}
	public void removeChild(XmlNode child) {
		children.remove(child);
	}
	public void removeChild(int index){
		children.remove(index);
	}
	public int indexOfChild(XmlNode child) {
		return children.indexOf(child);
	}
	public XmlNode getChild(int index) {
		return children.get(index);
	}

	public XmlElement getXmlElement() {
		return xmlElement;
	}
	public XmlNode getParentNode() {
		return parent;
	}
	
	public ArrayList<XmlNode> getChildren() {
		return children;
	}
	public void setChildBox(Box childBox) {
		this.childBox = childBox;
	}
	public Box getChildBox() {
		return childBox;
	}
	
	public void setHighlighted(boolean highlighted) {
		this.highlighted = highlighted;
		xmlElement.setHighlighted(highlighted);
	}
	public boolean getHighlighted() {
		return highlighted;
	}
	public void nodeClicked(boolean clearOthers) {
		tree.nodeSelected(this, clearOthers);
	}
	public void dataFieldUpdated() {
		tree.dataFieldUpdated();
	}
	public void hideChildren(boolean collapsed) {
		childBox.setVisible(!collapsed);
	}
}
