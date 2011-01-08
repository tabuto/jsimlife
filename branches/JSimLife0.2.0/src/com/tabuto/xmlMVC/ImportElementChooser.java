/**
* @author Francesco di Dio
* Date: 27/dic/2010 16.25.03
* Titolo: ImportElementChooser.java
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

import javax.swing.JPanel;




import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class ImportElementChooser extends JPanel{
	
	XMLView xmlView;
	
	ImportElementChooser(XmlNode rootNode, XMLView view) {
		
		xmlView = view;
		this.setLayout(new BorderLayout());
		
		Box buttonBox = Box.createVerticalBox();
		
		Icon addElementIcon = ImageFactory.getInstance().getIcon(ImageFactory.TWO_LEFT_ARROW);
		JButton addElementButton = new JButton("Add", addElementIcon);
		addElementButton.addActionListener(new InsertFieldsListener());
		buttonBox.add(addElementButton);
		
		Icon noIcon = ImageFactory.getInstance().getIcon(ImageFactory.N0);
		JButton doneButton = new JButton("Done", noIcon);
		doneButton.addActionListener(new DoneButtonListener());
		buttonBox.add(doneButton);
		
		JScrollPane importScrollPane = new JScrollPane(new TreeDisplay(rootNode), JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		this.add(buttonBox, BorderLayout.WEST);
		this.add(importScrollPane, BorderLayout.CENTER);
	}

	public class InsertFieldsListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			xmlView.importFieldsFromImportTree();
		}
	}
	
	public class DoneButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			xmlView.setEditingState(XMLView.EDITING_FIELDS);
			xmlView.updateAttributeEditor();
		}
	}
}
