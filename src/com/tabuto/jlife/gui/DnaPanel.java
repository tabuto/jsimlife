/**
* @author Francesco di Dio
* Date: 23/dic/2010 21.01.42
* Titolo: DnaPanel.java
* Versione: 0.12.2 Rev.a:
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

package com.tabuto.jlife.gui;



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
 * DnaPanel provides a panel display the DNA's XML format in a dynamic JTree component
 * @author tabuto
 *
 */
public class DnaPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JTree xmlTree;

	//The XML document to be output to the JTree
	private Document xmlDoc;

	DefaultMutableTreeNode tn;

	/**
	 * Constructor of the panel initialize the panel's component
	 */
	public DnaPanel(){
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






