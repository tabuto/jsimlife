/**
* @author Francesco di Dio
* Date: 29/dic/2010 23.18.03
* Titolo: AddCellAction.java
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
import com.tabuto.jsimlife.views.JSLMainView;
import com.tabuto.jsimlife.views.JSLNewCellView;

/**
 * Class extends AbstractAction to perform the following Action:
 * Open the JSLNeeCellView
 * 
 * @author tabuto83
 *
 * @see AbstractAction
 * @see JSLNewCellView
 */
public class AddCellAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JSLMainView mainView;
	
	/**
	 * Instantiate new AddCellAction
	 * @param mainView JSLMainView 
	 * @see JSimLife
	 */
	public AddCellAction(JSLMainView mainview)
	{
		super();
		mainView = mainview;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		JSLNewCellView newCellFrame = new JSLNewCellView(mainView.getGame());
		newCellFrame.pack();
		newCellFrame.setVisible(true);
		
	}

	
	
}
