/**
* @author Francesco di Dio
* Date: 29/dic/2010 23.11.05
* Titolo: LoadDnaAction.java
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

package com.tabuto.jsimlife.actions;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.AbstractAction;
import javax.swing.JFileChooser;

import com.tabuto.jsimlife.views.JSLNewCellView;


public class LoadDnaAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JSLNewCellView newCellView;
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
