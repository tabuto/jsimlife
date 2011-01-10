/**
* @author Francesco di Dio
* Date: 10/gen/2011 11.43.53
* Titolo: CreateNewSeedAction.java
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
import java.util.ResourceBundle;
import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

import com.tabuto.jsimlife.JSimLife;
import com.tabuto.jsimlife.Seed;
import com.tabuto.jsimlife.views.JSLNewSeedView;

/**
 * Class uses from JSLNewSeedView in order to add some seed into the Game
 * @author tabuto83
 * 
 * @see {@link JSimLife#addSeed(int, int, int, double)}
 * @see JSLNewSeedView
 *
 */
public class CreateNewSeedAction extends AbstractAction {



	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	JSLNewSeedView newSeedDialog;
	private ResourceBundle resource;
	
	public CreateNewSeedAction(JSLNewSeedView newseeddialog)
	{
		super();
		newSeedDialog = newseeddialog;
		resource = ResourceBundle.getBundle("StringAndLabels", newSeedDialog.Game.getConfiguration().getLocale() );
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		if (checkValues())
		{
		Seed newSeed = new Seed( newSeedDialog.Game.getDimension(), 
									Integer.valueOf(newSeedDialog.fieldX.getText() ) ,
									Integer.valueOf(newSeedDialog.fieldY.getText()),
									Integer.valueOf(newSeedDialog.fieldRadius.getText()),
									(double)Integer.valueOf(newSeedDialog.fieldDensity.getText())
									);
		((JSimLife)newSeedDialog.Game).addSeed(newSeed);
		newSeedDialog.dispose();
		}
		
	}
	
	/**
	 * @return true if values are correctly written
	 */
	private boolean checkValues()
	{
		boolean flag=false;
		
		//Check if values are correctly written
		try
		{
			Integer.valueOf(newSeedDialog.fieldX.getText() );
			Integer.valueOf(newSeedDialog.fieldY.getText() );
			Integer.valueOf(newSeedDialog.fieldRadius.getText() );
			Integer.valueOf(newSeedDialog.fieldDensity.getText() );
			flag = true;
		}
		catch (NumberFormatException e)
		{
			JOptionPane.showMessageDialog(newSeedDialog, 
					resource.getString( "asd_valueMisformedDesc" ), 
					resource.getString( "asd_valueMisformed" ), 
					JOptionPane.ERROR_MESSAGE);
		}
		
		//Check if coordinate values are inside playfield dimension
		if (Integer.valueOf(newSeedDialog.fieldX.getText())>=0 && 
				Integer.valueOf(newSeedDialog.fieldX.getText())<= newSeedDialog.Game.getDimension().getWidth()
			&& Integer.valueOf(newSeedDialog.fieldY.getText())>=0 && 
			Integer.valueOf(newSeedDialog.fieldY.getText())<= newSeedDialog.Game.getDimension().getHeight())
				
				flag = true;
			else
			{   JOptionPane.showMessageDialog(newSeedDialog,
					resource.getString( "asd_ValueOutOfRange" ),
					resource.getString( "asd_ValueOutOfRangeDesc" ),
					JOptionPane.ERROR_MESSAGE);
				flag = false;
			}
		
				
		return flag;		
	}

}
