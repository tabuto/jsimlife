/**
* @author Francesco di Dio
* Date: 29/dic/2010 23.11.05
* Titolo: LoadDnaAction.java
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

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.io.File;
import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import com.tabuto.jsimlife.views.JSLNewCellView;

/**
 * Class extends AbstractAction to perform the following Action:
 * Loading a DNA's XML file into JSLNewCellView using JFileChooser
 * 
 * @author tabuto83
 *
 * @see AbstractAction
 * @see JSLNewCellView
 * @see JFileChooser
 */
public class LoadDnaAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JSLNewCellView newCellView;
	
	/**
	 * Instantiate new LoadDnaAction
	 * 
	 * @param newcellview JSLNewCellView
	 * @see JSLNewCellView
	 */
	public LoadDnaAction(JSLNewCellView newcellview)
	{
		super();
		newCellView = newcellview;
	}
	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		//Dna ZlifeDna; //TEMP DNA
		try{
		  JFileChooser fileChooser = new JFileChooser();
	      File f = new File(newCellView.Game.getPath()+"/.");
	      fileChooser.setCurrentDirectory(f);
          int n = fileChooser.showOpenDialog(newCellView);
          if (n == JFileChooser.APPROVE_OPTION) 
          	{
        	  
        	  newCellView.ZlifeDna.loadXML( 
    					  fileChooser.getSelectedFile().getAbsolutePath()
    					  );
          	}
		}catch (Exception ex) {}
		
          
          //SET THE SLIDER WITH loaded DNA's values
		newCellView.nameField.setText( newCellView.ZlifeDna.getName());
          
		newCellView.dnaParamSlider.setValue((int) (newCellView.ZlifeDna.getGene("dnaParam").doubleValue() *100 ));
		newCellView.energySlider.setValue((int) (newCellView.ZlifeDna.getGene("energy").doubleValue() ));
		newCellView.maxEnergySlider.setValue((int) (newCellView.ZlifeDna.getGene("maxEnergy").doubleValue() ));
		newCellView.hornyEnergySlider.setValue((int) (newCellView.ZlifeDna.getGene("hornyEnergy").doubleValue() ));
		newCellView.hungryEnergySlider.setValue((int) (newCellView.ZlifeDna.getGene("hungryEnergy").doubleValue() ));
		newCellView.lifeCycleSlider.setValue( (newCellView.ZlifeDna.getGene("lifeCycle").intValue()));
		newCellView.metabolismSlider.setValue((int) (newCellView.ZlifeDna.getGene("metabolism").doubleValue()*1000 ));
		newCellView.boredSpeedSlider.setValue((int) (newCellView.ZlifeDna.getGene("BoredSpeed").doubleValue() ));
		newCellView.riproductionEnergySlider.setValue((int) (newCellView.ZlifeDna.getGene("riproductionEnergy").doubleValue() ));
		newCellView.hungrySpeedSlider.setValue((int) (newCellView.ZlifeDna.getGene("hungrySpeed").doubleValue() ));
		newCellView.hornySpeedSlider.setValue((int) (newCellView.ZlifeDna.getGene("hornySpeed").doubleValue() ));
		newCellView.scarySpeedSlider.setValue((int) (newCellView.ZlifeDna.getGene("scarySpeed").doubleValue() ));
         int R = newCellView.ZlifeDna.getGene("R").intValue();
         int G = newCellView.ZlifeDna.getGene("G").intValue();
         int B = newCellView.ZlifeDna.getGene("B").intValue();
         newCellView.colorButton.setBackground(new Color(R,G,B));
         newCellView.radiusSlider.setValue( (newCellView.ZlifeDna.getGene("radius").intValue()));
         newCellView.ageFactorSlider.setValue((int) (newCellView.ZlifeDna.getGene("ageFactor").doubleValue() *100 ));
		
	}

}
