/**
* @author Francesco di Dio
* Date: 29/dic/2010 23.31.50
* Titolo: SavePreferencesAction.java
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

package com.tabuto.jsimlife.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;
import com.tabuto.jsimlife.Configuration;
import com.tabuto.jsimlife.views.JSLPreferencesView;

/**
 * Class extends AbstractAction to perform the following Action:
 * Save the current preferences into an XML file
 * 
 * @author tabuto83
 *
 * @see AbstractAction
 * @see JSLPreferencesView
 * @see Configuration
 */
public class SavePreferencesAction extends AbstractAction {

	JSLPreferencesView preferencesView;
	Configuration preferences;
	
	/**
	 * Instantiate new SavePreferencesAction
	 * 
	 * @param preferencesview JSLPreferencesView 
	 */
	public SavePreferencesAction(JSLPreferencesView preferencesview)
	{
		super();
		preferencesView = preferencesview;
		preferences = preferencesView.getPreferences();
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		preferences.setPlayfieldDimension( Integer.parseInt(preferencesView.widthField.getText()),  
				  Integer.parseInt(preferencesView.heightField.getText()) );

		preferences.setBackgroundColor(  preferencesView.colorButton.getBackground());

		preferences.setMaxZlifes(Integer.parseInt(preferencesView.MaxZlifesField.getText()));

		preferences.setMaxZretador(Integer.parseInt(preferencesView.MaxZretadorsField.getText()));

		preferences.setMaxSeeds(Integer.parseInt(preferencesView.MaxSeedsField.getText()));

		preferences.setPath( preferencesView.pathField.getText());

		preferences.setLocale( preferences.LocaleParseString(preferencesView.localeComboBox.getSelectedItem().toString() ));
		
		preferences.save();
		JOptionPane.showMessageDialog(preferencesView, preferencesView.resource.getString( "prf_restartMsg") ,
				preferencesView.resource.getString( "prf_applyChanges"),
										JOptionPane.PLAIN_MESSAGE);
		preferencesView.dispose();
	}

}
