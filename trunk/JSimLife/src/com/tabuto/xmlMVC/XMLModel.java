/**
* @author Francesco di Dio
* Date: 27/dic/2010 16.23.53
* Titolo: XMLModel.java
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
import java.io.*;

import javax.swing.JPanel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class XMLModel implements XMLUpdateObserver, SelectionObserver{
	
	public static final String INPUT = "input";
	public static final String PROTOCOL = "protocol";
		
	private Document document; 
	private Document outputDocument;
	private Tree tree;			// tree model of template
	private Tree importTree;	// tree of a file used for importing fields
	
	private File currentFile;
	private boolean currentFileEdited;
	
	private ArrayList<XMLUpdateObserver> xmlObservers;
	private ArrayList<SelectionObserver> selectionObservers = new ArrayList<SelectionObserver>();;
	
	//TODO:delete this main and add JEX to a tool menu in JSimLifeMainWindows
	/*
	public static void main(String args[]) {
		new XMLModel("newfile");
	}
	*/
	
	// default constructor, instantiates empty ArrayList, then creates new View. 
	public XMLModel(String path) {
		currentFile = new File(path);
		
		xmlObservers = new ArrayList<XMLUpdateObserver>();
		
		initialiseBlankDOMdoc();
		
		tree = new Tree(this, this);

		new XMLView(this);
	}	
	
	public void openXMLFile(File xmlFile) {

		currentFile = xmlFile;
		
		readXMLtoDOM(xmlFile);	// overwrites document
		
		tree = new Tree(document, this, this);
		
		document = null;

		notifyXMLObservers();
	}
	
	public void notifyXMLObservers() {
		for (XMLUpdateObserver xmlObserver: xmlObservers) {
			xmlObserver.xmlUpdated();
		}
	}
	public void xmlUpdated() {
		currentFileEdited = true;
		notifyXMLObservers();
	}
	public void selectionChanged() {
		notifySelectionObservers();
	}
	public void notifySelectionObservers(){
		for (SelectionObserver selectionObserver: selectionObservers) {
			selectionObserver.selectionChanged();
		}
	}
	
	public void addXMLObserver(XMLUpdateObserver newXMLObserver) {
		xmlObservers.add(newXMLObserver);
	}
	public void addSelectionObserver(SelectionObserver newSelectionObserver) {
		selectionObservers.add(newSelectionObserver);
	}
	
	// used by default constructor to initialise blank document
	public void initialiseBlankDOMdoc() {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			document = builder.newDocument();
		} catch (ParserConfigurationException e) {

			e.printStackTrace();
		};
	}
	
	public void readXMLtoDOM(File xmlFile) {
		DocumentBuilderFactory factory =
            DocumentBuilderFactory.newInstance();
        //factory.setValidating(true);   
        //factory.setNamespaceAware(true);
        
        try {
           DocumentBuilder builder = factory.newDocumentBuilder();

           builder.setErrorHandler(
                   new org.xml.sax.ErrorHandler() {
                       // ignore fatal errors (an exception is guaranteed)
                       public void fatalError(SAXParseException exception)
                       throws SAXException {
                       }

                       // treat validation errors as fatal
                       public void error(SAXParseException e)
                       throws SAXParseException
                       {
                         throw e;
                       }

                       // dump warnings too
                       public void warning(SAXParseException err)
                       throws SAXParseException
                       {
                         System.out.println("** Warning"
                            + ", line " + err.getLineNumber()
                            + ", uri " + err.getSystemId());
                         System.out.println("   " + err.getMessage());
                       }
                   }
                 ); 

           document = builder.parse( xmlFile );
           
        } catch (SAXException sxe) {
            // Error generated during parsing)
            Exception  x = sxe;
            if (sxe.getException() != null)
                x = sxe.getException();
            x.printStackTrace();

         } catch (ParserConfigurationException pce) {
             // Parser with specified options can't be built
             pce.printStackTrace();

         } catch (IOException ioe) {
            // I/O error
            ioe.printStackTrace();
         }
	}
	
	public Tree getTreeFromNewFile(File xmlFile) {
		
		readXMLtoDOM(xmlFile);	// overwrites document
		
		Tree tree = new Tree(document);
		
		document = null; 	// release the memory
		
		return tree;
	}
	
	public void setImportTree(Tree tree) {
		
		importTree = tree;
		
		if (tree == null) return;
		
	}
	
	// import the selected nodes of the tree, or if none selected, import it all!
	public void importElementsFromImportTree() {
		if (importTree.getHighlightedFields().size() > 0) {
			tree.copyAndInsertElements(importTree.getHighlightedFields());
		}
			
		else {
			tree.copyAndInsertElement(importTree.getRootNode()); 
		}
		
		notifyXMLObservers();
		}
	
	public void writeTreeToDOM() {
		
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			outputDocument = db.newDocument();
			Element protocol = outputDocument.createElement(PROTOCOL);
		} catch (Exception ex) { 
			ex.printStackTrace();
		}
		
		tree.buildDOMfromTree(outputDocument);
	} 
	
	public void transformXmlToHtml() {
		
		File outputXmlFile = new File("file");
		
		saveTreeToXmlFile(outputXmlFile);
		
		// opens the HTML in a browser window
		XmlTransform.transformXMLtoHTML(outputXmlFile);
	}

	public void saveTreeToXmlFile(File outputFile) {
		
		writeTreeToDOM();
		
		try {
			
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			Source source = new DOMSource(outputDocument);
			Result output = new StreamResult(outputFile);
			transformer.transform(source, output);
			
			setCurrentFile(outputFile);	// remember the current file. 
			
			currentFileEdited = false;
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void printDOM( Document docToPrint) {
		try {
			
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			Source source = new DOMSource( docToPrint );
			Result output = new StreamResult( System.out );
			transformer.transform(source, output);
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		System.out.println("");
	}

	
	// start a blank Xml file
	public void openBlankXmlFile() {
		tree.openBlankXmlFile();
		setCurrentFile(new File(""));	// no current file
		notifyXMLObservers();
	}

	
	// add a new xmlElement after the specified xmlElement
	public void addDataField() {
		tree.addElement();
		notifyXMLObservers();
	}
	
	// duplicate a xmlElement and add it at specified index
	public void duplicateDataFields() {
		tree.duplicateAndInsertElements();
		notifyXMLObservers();
	}
	
	
	
	// delete the highlighted dataFields
	public void deleteDataFields(boolean saveChildren) {
		tree.deleteElements(saveChildren);
		notifyXMLObservers();
	}
	
	public void demoteDataFields() {
		tree.demoteDataFields();
		notifyXMLObservers();
	}
	
	public void promoteDataFields() {
		tree.promoteDataFields();
		notifyXMLObservers();
	}
	
//	 if the highlighted fields have a preceeding sister, move it below the highlighted fields
	public void moveFieldsUp() {
		tree.moveFieldsUp();
		notifyXMLObservers();
	}
	
//	 if the highlighted fields have a preceeding sister, move it below the highlighted fields
	public void moveFieldsDown() {
		tree.moveFieldsDown();
		notifyXMLObservers();
	}
	
	public void setXmlEdited(boolean xmlEdited) {
		currentFileEdited = xmlEdited;
	}
	public boolean isCurrentFileEdited() {
		return currentFileEdited;
	}

	// called when saving, then used to set protocolFileName attribute
	public void setCurrentFile(File file) {
		currentFile = file;
	}
	public File getCurrentFile() {
		return currentFile;
	}
	
	
	public XmlNode getRootNode() {
		return tree.getRootNode();
	}
	public JPanel getAttributeEditorToDisplay() {
		return tree.getAttributeEditorToDisplay();
	}
}
