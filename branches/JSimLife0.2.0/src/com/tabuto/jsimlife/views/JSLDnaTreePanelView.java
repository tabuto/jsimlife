/**
* @author Francesco di Dio
* Date: 30/dic/2010 00.33.32
* Titolo: JSLDnaTreePanelView.java
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

import java.util.Iterator;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;

/**
 * Show a panel with a wrapped JTree able to display an XML documents such as a Dna
 * 
 * @author tabuto83
 *
 */
public class JSLDnaTreePanelView extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JTree xmlTree;

	//The XML document to be output to the JTree
	private Document xmlDoc;

	DefaultMutableTreeNode tn;

	/**
	 * Initialize the panel's component
	 */
	public JSLDnaTreePanelView(){
		 super();
		 xmlTree = new JTree();
		 //setPreferredSize(new Dimension(250, 450));
         tn= new DefaultMutableTreeNode("XML");
     	xmlTree.setName("XML Tree");
		JScrollPane scroll = new JScrollPane(xmlTree);
		scroll.setAutoscrolls(true);
		//add(scroll);
		add(xmlTree);
	}

	/**
	 * Clear the JTree
	 */
	public void close()
	{
		xmlTree.removeAll();
		  tn = null;
		  tn = new DefaultMutableTreeNode("Zlife's DNA");
		  //repaint();

	}
	
	/**
	 * Initialize and put into a Jtree the XML document
	 * @param doc XML JDOM Document
	 */
	public void initialize(Document doc){
		
		close();
		this.xmlDoc = doc;
        processElement(xmlDoc.getRootElement(), tn);
		((DefaultTreeModel)xmlTree.getModel()).setRoot(tn);
		setVisible(true);
	}


	/**
	 * Process all Document's elements 
	 * @param el Element
	 * @param dmtn DefaultMutableTreeNode
	 */
	 @SuppressWarnings("unchecked")
	private void processElement(Element el, DefaultMutableTreeNode dmtn) {
		 DefaultMutableTreeNode currentNode =
		 	new DefaultMutableTreeNode(el.getName());
		 String text = el.getTextNormalize();
		 if((text != null) && (!text.equals("")))
		 	currentNode.add(new DefaultMutableTreeNode(text));

		 processAttributes(el, currentNode);

		 Iterator children = el.getChildren().iterator();

		 while(children.hasNext())
		 	processElement((Element)children.next(), currentNode);

		 dmtn.add(currentNode);
	 }

	 /**
	  * Process attributes of a single node
	  * @param el Element
	  * @param dmtn DefaultMutableTreeNode
	  */
	 @SuppressWarnings("unchecked")
	private void processAttributes(Element el, DefaultMutableTreeNode dmtn) {
		 Iterator atts = el.getAttributes().iterator();

		 while(atts.hasNext()){
			 Attribute att = (Attribute) atts.next();
			 DefaultMutableTreeNode attNode =
			 	new DefaultMutableTreeNode("@"+att.getName());
			 attNode.add(new DefaultMutableTreeNode(att.getValue()));
			 dmtn.add(attNode);
		 }
	 }

}
