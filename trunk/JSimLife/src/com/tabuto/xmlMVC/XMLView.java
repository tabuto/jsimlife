/**
* @author Francesco di Dio
* Date: 27/dic/2010 16.23.00
* Titolo: XMLView.java
* Versione: 0.1.13.2 Rev.a:
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


import javax.swing.Box;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.*;
import java.io.File;

public class XMLView implements XMLUpdateObserver, SelectionObserver {
	
	XMLModel xmlModel;

	XmlNode xmlRootNode;
	
	XmlNode rootOfImportTree;
	
	// state changes 
	int editingState = EDITING_FIELDS;
	public static final int EDITING_FIELDS = 0;
	public static final int IMPORTING_FIELDS = 1;
	
	public static final int CONTROL_CLICK = 18;
	public static final int SHIFT_CLICK = 17;
	public static final Font FONT_H1 = new Font("SansSerif", Font.BOLD, 18);
	public static final Font FONT_SMALL = new Font("SansSerif", Font.PLAIN, 12);
	public static final Font FONT_TINY = new Font("SansSerif", Font.PLAIN, 9);
	public static final Font FONT_INVISIBLE = new Font("Sanserif", Font.PLAIN, 1);
	public static final Color BLUE_HIGHLIGHT = new Color(181,213,255);
	
	public static final int BUTTON_SPACING = 5;
	
	
	// UI containers
	JFrame XMLFrame;		// top-level frame
	
	JPanel toolbarAndScrollPane;	// top-level panel that holds toolbar(NORTH), splitPane(CENTRE).
	JSplitPane splitPane;			// holds scrollPane(LEFT) and attributeEditor(RIGHT)
	
	JScrollPane XMLScrollPane;	// left-hand pane containing the tree-display
	TreeDisplay treeDisplay;	// lays out the tree hierarchy
	JPanel attributeEditor; 	// shows attributes in right-hand panel
	
	
	// buttons
	JButton addElement;
	JButton deleteElement;
	JButton promoteElement;
	JButton demoteElement;
	JButton duplicateElement;

	JMenuItem saveFileMenuItem;
	JMenuItem addElementMenuItem;
	JMenuItem deleteElementMenuItem;
	JMenuItem moveElementUpMenuItem;
	JMenuItem moveElementDownMenuItem;
	JMenuItem promoteElementMenuItem;
	JMenuItem demoteElementMenuItem;
	JMenuItem duplicateElementMenuItem;
	JMenuItem importElementsMenuItem;
	
	
	static final int windowHeight = 460;
    static final int panelWidth = 530;
    
	
    public XMLView(XMLModel xmlModel) {
    	
    	this.xmlModel = xmlModel;
    	
    	xmlModel.addXMLObserver(this);
    	xmlModel.addSelectionObserver(this);
    	
    	xmlRootNode = xmlModel.getRootNode();
    	
    	buildUI();
    	
    }
    
	public void buildUI() {
		
		XMLFrame = new JFrame("JEX - Java Easy Xml editor");
		
		// icons
		Icon printIcon = ImageFactory.getInstance().getIcon(ImageFactory.PRINT_ICON);
		Icon newFileIcon = ImageFactory.getInstance().getIcon(ImageFactory.NEW_FILE_ICON);
		Icon openFileIcon = ImageFactory.getInstance().getIcon(ImageFactory.OPEN_FILE_ICON);
		Icon saveIcon = ImageFactory.getInstance().getIcon(ImageFactory.SAVE_ICON);
		Icon saveFileAsIcon= ImageFactory.getInstance().getIcon(ImageFactory.SAVE_FILE_AS_ICON);
		Icon addIcon = ImageFactory.getInstance().getIcon(ImageFactory.ADD_ICON);
		Icon deleteIcon = ImageFactory.getInstance().getIcon(ImageFactory.DELETE_ICON);
		Icon moveUpIcon = ImageFactory.getInstance().getIcon(ImageFactory.MOVE_UP_ICON);
		Icon moveDownIcon = ImageFactory.getInstance().getIcon(ImageFactory.MOVE_DOWN_ICON);
		Icon promoteIcon = ImageFactory.getInstance().getIcon(ImageFactory.PROMOTE_ICON);
		Icon demoteIcon = ImageFactory.getInstance().getIcon(ImageFactory.DEMOTE_ICON);
		Icon duplicateIcon = ImageFactory.getInstance().getIcon(ImageFactory.DUPLICATE_ICON);
		Icon importElementsIcon = ImageFactory.getInstance().getIcon(ImageFactory.IMPORT_ICON);
		
		// Build menus
		JMenuBar menuBar = new JMenuBar();
		EmptyBorder menuItemBorder = new EmptyBorder(0,5,0,5);
		
		// File menu
		JMenu fileMenu = new JMenu("File");
		fileMenu.setBorder(menuItemBorder);
		JMenuItem openFile = new JMenuItem("Open File..", openFileIcon);
		JMenuItem newBlankFileMenuItem = new JMenuItem("New...", newFileIcon);
		saveFileMenuItem = new JMenuItem("Save", saveIcon);
		JMenuItem saveFile = new JMenuItem("Save As...", saveFileAsIcon);
		JMenuItem printFile= new JMenuItem("Print", printIcon);
		
		openFile.addActionListener(new openFileListener());
		newBlankFileMenuItem.addActionListener(new NewFileListener());
		saveFile.addActionListener(new SaveCurrentFileListener());
		printFile.addActionListener(new PrintExperimentListener());
		
		fileMenu.add(openFile);
		fileMenu.add(newBlankFileMenuItem);
		fileMenu.add(saveFileMenuItem);
		fileMenu.add(saveFile);
		fileMenu.add(printFile);
		menuBar.add(fileMenu);
		
		
		// edit menu
		JMenu editMenu = new JMenu("Edit");
		editMenu.setBorder(menuItemBorder);

		addElementMenuItem = new JMenuItem("Add Element", addIcon);
		deleteElementMenuItem = new JMenuItem("Delete Element", deleteIcon);
		moveElementUpMenuItem = new JMenuItem("Move Element Up", moveUpIcon);
		moveElementDownMenuItem = new JMenuItem("Move Element Down", moveDownIcon);
		promoteElementMenuItem = new JMenuItem("Promote Element (indent to left)", promoteIcon);
		demoteElementMenuItem = new JMenuItem("Demote Element (indent to right)", demoteIcon);
		duplicateElementMenuItem = new JMenuItem("Duplicate Element", duplicateIcon);
		importElementsMenuItem = new JMenuItem("Import Elements from file", importElementsIcon);
		
		saveFileMenuItem.addActionListener(new SaveFileAsListener());
		addElementMenuItem.addActionListener(new AddElementListener());
		deleteElementMenuItem.addActionListener(new deleteDataFieldListener());
		moveElementUpMenuItem.addActionListener(new MoveFieldUpListener());
		moveElementDownMenuItem.addActionListener(new MoveFieldDownListener());
		promoteElementMenuItem.addActionListener(new PromoteFieldListener());
		demoteElementMenuItem.addActionListener(new DemoteFieldListener());
		duplicateElementMenuItem.addActionListener(new DuplicateFieldListener());
		importElementsMenuItem.addActionListener(new InsertElementsFromFileListener());
		
		editMenu.add(addElementMenuItem);
		editMenu.add(deleteElementMenuItem);
		editMenu.add(moveElementUpMenuItem);
		editMenu.add(moveElementDownMenuItem);
		editMenu.add(promoteElementMenuItem);
		editMenu.add(demoteElementMenuItem);
		editMenu.add(duplicateElementMenuItem);
		editMenu.add(importElementsMenuItem);
		menuBar.add(editMenu);
		
		
		
//		 Make a nice border
		EmptyBorder eb = new EmptyBorder(BUTTON_SPACING,BUTTON_SPACING,BUTTON_SPACING,BUTTON_SPACING);
		EmptyBorder noLeftPadding = new EmptyBorder (0, 2, BUTTON_SPACING, BUTTON_SPACING);
		EmptyBorder noRightPadding = new EmptyBorder (0, BUTTON_SPACING, BUTTON_SPACING, 2);
	
		
		// Build left side with form UI

		
		treeDisplay = new TreeDisplay(this);
		
		XMLScrollPane = new JScrollPane(treeDisplay, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		XMLScrollPane.setFocusable(true);
		XMLScrollPane.addMouseListener(new ScrollPaneMouseListener());
		XMLScrollPane.setPreferredSize(new Dimension( panelWidth, windowHeight));
		XMLScrollPane.setMinimumSize(new Dimension( panelWidth, windowHeight));
		XMLScrollPane.setMaximumSize(new Dimension( panelWidth, windowHeight));
		
		// toolbar buttons
		
		JButton printButton = new JButton(printIcon);
		printButton.addActionListener(new PrintExperimentListener());
		printButton.setToolTipText("Print - Displays in web browser for printing");
		printButton.setBorder(eb);
		
		JButton newFileButton2 = new JButton(newFileIcon);
		newFileButton2.setToolTipText("New XML file");
		newFileButton2.addActionListener(new NewFileListener());
		newFileButton2.setBorder(eb);
		
		JButton openFileButton2 = new JButton(openFileIcon);
		openFileButton2.setToolTipText("Open file");
		openFileButton2.addActionListener(new openFileListener());
		openFileButton2.setBorder(eb);
		
		JButton saveButton = new JButton(saveIcon);
		saveButton.setToolTipText("Save");
		saveButton.setBorder(eb);
		saveButton.addActionListener(new SaveCurrentFileListener());
		
		JButton saveAsButton = new JButton(saveFileAsIcon);
		saveAsButton.setToolTipText("Save As..");
		saveAsButton.setBorder(eb);
		saveAsButton.addActionListener(new SaveFileAsListener());
		

		addElement = new JButton(addIcon);
		addElement.setToolTipText("Add an element");
		addElement.addActionListener(new AddElementListener());
		addElement.setBorder(eb);
		
		deleteElement = new JButton(deleteIcon);
		deleteElement.setToolTipText("Delete the highlighted elements");
		deleteElement.addActionListener(new deleteDataFieldListener());
		deleteElement.setBorder(eb);
		
		JButton moveUpButton = new JButton(moveUpIcon);
		moveUpButton.setToolTipText("Move the element up");
		moveUpButton.addActionListener(new MoveFieldUpListener());
		moveUpButton.setBorder(noRightPadding);
		
		JButton moveDownButton = new JButton(moveDownIcon);
		moveDownButton.setToolTipText("Move the element down");
		moveDownButton.addActionListener(new MoveFieldDownListener());
		moveDownButton.setBorder(noLeftPadding);
		
		promoteElement = new JButton(promoteIcon);
		promoteElement.setToolTipText("Indent elements to left.  To select multiple elements use Shift-Click");
		promoteElement.addActionListener(new PromoteFieldListener());
		promoteElement.setBorder(noRightPadding);
		
		demoteElement = new JButton(demoteIcon);
		demoteElement.setToolTipText("Indent elements to right");
		demoteElement.addActionListener(new DemoteFieldListener());
		demoteElement.setBorder(noLeftPadding);
		
		duplicateElement = new JButton(duplicateIcon);
		duplicateElement.setToolTipText("Duplicate the selected element. To select multiple elements use Shift-Click");
		duplicateElement.addActionListener(new DuplicateFieldListener());
		duplicateElement.setBorder(eb);
		
		JButton importElemetsButton = new JButton(importElementsIcon);
		importElemetsButton.setToolTipText("Import elements from another document");
		importElemetsButton.addActionListener(new InsertElementsFromFileListener());
		importElemetsButton.setBorder(eb);
		
		Box toolBar = Box.createHorizontalBox();
		toolBar.add(newFileButton2);
		toolBar.add(openFileButton2);
		toolBar.add(saveButton);
		toolBar.add(saveAsButton);
		toolBar.add(printButton);
		toolBar.add(addElement);
		toolBar.add(deleteElement);
		toolBar.add(moveUpButton);
		toolBar.add(moveDownButton);
		toolBar.add(promoteElement);
		toolBar.add(demoteElement);
		toolBar.add(duplicateElement);
		toolBar.add(importElemetsButton);
		toolBar.add(Box.createHorizontalGlue());
		
		attributeEditor = new AttributesPanel();
		
		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, XMLScrollPane, attributeEditor);
		// splitPane.setDividerLocation(1);
		
		toolbarAndScrollPane = new JPanel();
		toolbarAndScrollPane.setLayout(new BorderLayout());
		toolbarAndScrollPane.add(toolBar, BorderLayout.NORTH);
		toolbarAndScrollPane.add(splitPane, BorderLayout.CENTER);

		
		XMLFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		XMLFrame.setJMenuBar(menuBar);
		XMLFrame.getContentPane().add("Center", toolbarAndScrollPane);
		XMLFrame.pack();
		XMLFrame.setLocation(200, 100);
		XMLFrame.setVisible(true);
		XMLFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);    
		
		 //ADD ICON TITLE
		XMLFrame.setIconImage(java.awt.Toolkit.getDefaultToolkit().getImage
        		(this.getClass().getResource("icon_alpha_48x48.gif")));
		
		// an intro splash-screen to get users started
//		Custom button text
		
		Icon bigStartIcon = ImageFactory.getInstance().getIcon(ImageFactory.BIG_SOURCE_ICON);
		Object[] options = {
		                    "Open existing file",
		                    "Cancel"};
		int n = JOptionPane.showOptionDialog(XMLFrame, "<html>Welcome to the JEX, Java Easy Xml editor. <br>"
		    + "Please choose an otpion to get you started.</html>", "Welcome",
		    JOptionPane.YES_NO_CANCEL_OPTION,
		    JOptionPane.QUESTION_MESSAGE,
		    bigStartIcon,
		    options,
		    options[0]);
		
			if (n == 0) openFile();
	}

	
	// called by NotifyXMLObservers in XMLModel. 
	public void xmlUpdated() {
		xmlRootNode = xmlModel.getRootNode();
		treeDisplay.refreshForm();		// refresh the form
		
		if (editingState == EDITING_FIELDS) {  // ie. not showing import tree
			updateAttributeEditor();
		}
	}
	
	public void deleteDataFields() {
		
		Object[] options = {"Delete all sub-elements",
                "Move sub-elements up",
                "Cancel"};
		int result = JOptionPane.showOptionDialog(XMLFrame, 
				"<html>The elements you wish to delete may contain<br>"
				+ "sub-elements. Do you wish to delete them or move<br>"
				+ "them to a higher level in the hierarchy?</html>", "Delete Options",
		JOptionPane.YES_NO_CANCEL_OPTION,
 	    JOptionPane.QUESTION_MESSAGE,
 	    null,
 	    options,
 	    options[0]);

		
		if (result == 0) {	// delete all
			xmlModel.deleteDataFields(false);
			setXmlEdited(true);
		}
		if (result == 1) {	// shift children to be siblings, then delete
			xmlModel.deleteDataFields(true);
			setXmlEdited(true);
		}
	}
	
	public void promoteDataFields() {
		xmlModel.promoteDataFields();
		setXmlEdited(true);
	}
	
	public void demoteDataFields() {
		xmlModel.demoteDataFields();
		setXmlEdited(true);
	}
	
	public void moveFieldsUp() {
		// if the highlighted fields have a preceeding sister, move it below the highlighted fields
		xmlModel.moveFieldsUp();
		}
	public void moveFieldsDown() {
		xmlModel.moveFieldsDown();
	}
	
	public void addDataField() {
		// get selected Fields and add xmlElement after last seleted one		
		// create method returns the new xmlElement
		xmlModel.addDataField();

		setXmlEdited(true);
		
	}
	
	
	// open a blank xml file (after checking you want to save!)
	public void newXmlFile() {
		if (xmlModel.isCurrentFileEdited()) {
			int result = JOptionPane.showConfirmDialog
				(toolbarAndScrollPane, "Save the current file before opening a new one?");
			if (result == JOptionPane.YES_OPTION) {
				saveFileAs();
			} else if (result == JOptionPane.CANCEL_OPTION) {
				return;
			}
		}
		xmlModel.openBlankXmlFile();
		setXmlEdited(false);	// at least until user starts editing
	}
	
	
	//open a file
	public void openFile() {
		
		// check whether you want to save edited file
		if (xmlModel.isCurrentFileEdited()) {
			int result = JOptionPane.showConfirmDialog
				(toolbarAndScrollPane, "Save the current file before opening a new one?");
			if (result == JOptionPane.YES_OPTION) {
				saveFileAs();
			} else if (result == JOptionPane.CANCEL_OPTION) {
				return;
			}
		}
		
		// Create a file chooser
		final JFileChooser fc = new JFileChooser();
		fc.setFileFilter(new openFileFilter());
		
		fc.setCurrentDirectory(xmlModel.getCurrentFile());
		int returnVal = fc.showOpenDialog(XMLFrame);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
            File xmlFile = fc.getSelectedFile();
            openThisFile(xmlFile);
            
            setXmlEdited(false);
		}
	}
	
	public void openFile(File file) {
		
//		 check whether you want to save edited file
		if (xmlModel.isCurrentFileEdited()) {
			int result = JOptionPane.showConfirmDialog
				(toolbarAndScrollPane, "Save the current file before opening a new one?");
			if (result == JOptionPane.YES_OPTION) {
				saveFileAs();
			} else if (result == JOptionPane.CANCEL_OPTION) {
				return;
			}
		}
		 openThisFile(file);
		 
	}
	
	public void openThisFile(File file) {
		 xmlModel.openXMLFile(file);
		 setXmlEdited(false);
	}
	
	
	public class PrintExperimentListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			
			// Can't work out how to export xsl file into .jar
			// xmlModel.transformXmlToHtml();
			
			// use this method for now
			//HtmlOutputter.outputHTML(xmlRootNode);
		}
	}
	
	public class NewFileListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			newXmlFile();
		}
	}
	
	public class AddElementListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			addDataField();
		}
	}
	public class DuplicateFieldListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			duplicateFields();
		}
	}
	
	public class MoveFieldUpListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			moveFieldsUp();
		}
	}	
	public class MoveFieldDownListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			moveFieldsDown();
		}
	}
	public class PromoteFieldListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			promoteDataFields();
		}
	}
	public class DemoteFieldListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			demoteDataFields();
		}
	}
	
	public class deleteDataFieldListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			deleteDataFields();
		}
	}
	
	public class openFileListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			openFile();
		}
	}
	
	
	public class InsertElementsFromFileListener implements ActionListener {
		public void actionPerformed(ActionEvent event){
			insertElementsFromFile();
		}
	}
	
	
	public class SaveCurrentFileListener implements ActionListener {
		
		public void actionPerformed(ActionEvent event) {
			
				File currentFile = xmlModel.getCurrentFile();
				if (currentFile.exists()) {
					xmlModel.saveTreeToXmlFile(xmlModel.getCurrentFile());
					JOptionPane.showMessageDialog
    					(XMLFrame, "File saved.");
				}
				else saveFileAs();
		}
	}
	
	public class SaveFileAsListener implements ActionListener {
		
		public void actionPerformed(ActionEvent event) {
			
			saveFileAs();
	        
		}
	}
	
	
	public class openFileFilter extends FileFilter {
		public boolean accept(File file) {
			boolean recognisedFileType = 
				//	allows "MS Windows" to see directories (otherwise only .xml is visible)
				((file.getName().endsWith(".xml")) || (file.isDirectory()));
			return recognisedFileType;
		}
		
		public String getDescription() {
			return " .xml files only";
		}
	}
	
	
	public void saveFileAs() {
		
		final JFileChooser fc = new JFileChooser();
		fc.setFileFilter(new openFileFilter());
		fc.setCurrentDirectory(xmlModel.getCurrentFile());
		int returnVal = fc.showSaveDialog(XMLFrame);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
            File xmlFile = fc.getSelectedFile();  // this may be a directory! 
            
            if (xmlFile.isDirectory()) {
                JOptionPane.showMessageDialog(XMLFrame, "Please choose a file name (not a directory)");
                // try again! 
                saveFileAs();
                return;
            }
            
            // now check if the file exists. If so, take appropriate action
            if (xmlFile.exists()) {
            		//  Check if OK to overwrite
            		int result = JOptionPane.showConfirmDialog(toolbarAndScrollPane, "File exists. Overwrite it?");
            		if (!(result == JOptionPane.YES_OPTION)) 	// if not yes, then forget it!
            			return;
            		
            }
            
            xmlModel.saveTreeToXmlFile(xmlFile);
            
		}
	}
	
	public void setXmlEdited(boolean xmlEdited) {
		xmlModel.setXmlEdited(xmlEdited);
	}
	
	// selection observer method, fired when tree changes selection
	public void selectionChanged() {
		if (editingState == EDITING_FIELDS) {
			updateAttributeEditor();
		}
	}
	
//	 refresh the right panel with details of a new xmlElement.
	public void updateAttributeEditor() {
		
		JPanel newDisplay = xmlModel.getAttributeEditorToDisplay();
		updateFieldEditor(newDisplay);
	}
	
	public void updateFieldEditor(JPanel newDisplay) {
		attributeEditor.setVisible(false);
		//int divLoc = splitPane.getDividerLocation();
		toolbarAndScrollPane.remove(attributeEditor);
		
		attributeEditor = newDisplay;
			
		toolbarAndScrollPane.add(attributeEditor, BorderLayout.EAST);
		//splitPane.setRightComponent(attributeEditor);
		//splitPane.setDividerLocation(divLoc);
		toolbarAndScrollPane.validate();
		attributeEditor.validate();
		attributeEditor.setVisible(true);	// need to hide, then show to get refresh to work
	}
	
	public void insertElementsFromFile() {
//		 Create a file chooser
		final JFileChooser fc = new JFileChooser();
		fc.setFileFilter(new openFileFilter());
		
		fc.setCurrentDirectory(xmlModel.getCurrentFile());
		int returnVal = fc.showOpenDialog(XMLFrame);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
           File xmlFile = fc.getSelectedFile();	
           
           Tree importTree = xmlModel.getTreeFromNewFile(xmlFile); 
           xmlModel.setImportTree(importTree);
           
           rootOfImportTree = importTree.getRootNode();
           
           JPanel importTreeView = new ImportElementChooser(rootOfImportTree, this);
           
           updateFieldEditor(importTreeView);
           
           editingState = IMPORTING_FIELDS;
		}
	}
	
	public void setEditingState(int newState) {
		editingState = newState;
		
		// if finished importing, kill the reference to imported data
		if (editingState != IMPORTING_FIELDS) 
			xmlModel.setImportTree(null);
	}
	
	public void importFieldsFromImportTree() {
		xmlModel.importElementsFromImportTree();
	}
	
	public void duplicateFields() {
		// get selected Fields, duplicate and add after last selected one
		xmlModel.duplicateDataFields();
			
		// refresh UI after adding all (not after each time)
		xmlModel.notifyXMLObservers();
		setXmlEdited(true);
	}

	
	public XmlNode getRootNode() {
		return xmlRootNode;
	}
	
	// used to draw focus away from attributeEditor panel, so that updateDataField() is called
	public class ScrollPaneMouseListener implements MouseListener {
		public void mouseEntered(MouseEvent event) {}
		public void mouseExited(MouseEvent event) {}
		public void mousePressed(MouseEvent event) {}
		public void mouseReleased(MouseEvent event) {}
		public void mouseClicked(MouseEvent event) {
			XMLScrollPane.requestFocusInWindow();
		}
	}	
	
}

