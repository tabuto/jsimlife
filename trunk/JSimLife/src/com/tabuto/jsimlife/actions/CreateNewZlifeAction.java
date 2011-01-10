/**
* @author Francesco di Dio
* Date: 29/dic/2010 22.07.06
* Titolo: CreateNewZlifeAction.java
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

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;



import com.tabuto.jsimlife.Zetatron;
import com.tabuto.jsimlife.Zlife;
import com.tabuto.jsimlife.Zretador;
import com.tabuto.jsimlife.views.JSLNewCellView;

/**
 * Class extends AbstractAction to perform the following Action:
 * get the JSLNewCellView selected parameters to create the right Zlife number 
 * 
 * @author tabuto83
 *
 * @see AbstractAction
 * @see JSLNewCellView
 */
public class CreateNewZlifeAction extends AbstractAction {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JSLNewCellView NewCellView;
	Zlife zlife;
	Zretador zretador;
	Zetatron zetatron;
	
	/**
	 * Instantiate new CreateNewZlifeAction
	 * @param newcellview JSLNewCellView game class
	 * @see JSLNewCellView
	 */
	public CreateNewZlifeAction(JSLNewCellView newcellview)
	{
		super();
		NewCellView = newcellview;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		checkValues();
		
		//ADD ZLife's gene to JSLNewCellView.ZlifeDna DNA
		NewCellView.ZlifeDna.setName( NewCellView.nameField.getText());
		NewCellView.ZlifeDna.add((double)NewCellView.dnaParamSlider.getValue()/100, "dnaParam", "The Dna fuzzy parameter");
		NewCellView.ZlifeDna.add((double)NewCellView.energySlider.getValue(), "energy", "energy's zlife");
		NewCellView.ZlifeDna.add((double)NewCellView.maxEnergySlider.getValue(), "maxEnergy", "The max energy avaible for zlife");
		NewCellView.ZlifeDna.add((double)NewCellView.hornyEnergySlider.getValue() , "hornyEnergy", "The energy needs to riproduction");
		NewCellView.ZlifeDna.add((double)NewCellView.hungryEnergySlider.getValue(), "hungryEnergy", "The energy because I'm hungry");
		NewCellView.ZlifeDna.add(NewCellView.lifeCycleSlider.getValue(), "lifeCycle", "The number of lifeCycle before die");
		NewCellView.ZlifeDna.add((double)NewCellView.metabolismSlider.getValue()/1000, "metabolism", "The energy for every move");
		NewCellView.ZlifeDna.add((double)NewCellView.boredSpeedSlider.getValue() , "BoredSpeed", "The speed when i'm bored");
		NewCellView.ZlifeDna.add((double)NewCellView.riproductionEnergySlider.getValue() , "riproductionEnergy", "The energy spend for embracing");
		NewCellView.ZlifeDna.add((double)NewCellView.hungrySpeedSlider.getValue(), "hungrySpeed", "Speed when i'm hungry");
		NewCellView.ZlifeDna.add((double)NewCellView.hornySpeedSlider.getValue(), "hornySpeed", "Speed when i'm horny");
		NewCellView.ZlifeDna.add((double)NewCellView.scarySpeedSlider.getValue(), "scarySpeed", "Speed when i'm scary");
		NewCellView.ZlifeDna.add(NewCellView.colorButton.getBackground().getRed(), "R", "Red component of Zlife color");
		NewCellView.ZlifeDna.add(NewCellView.colorButton.getBackground().getGreen(), "G", "Green component of Zlife color");
		NewCellView.ZlifeDna.add(NewCellView.colorButton.getBackground().getBlue(), "B", "Blue component of Zlife color");
		NewCellView.ZlifeDna.add(NewCellView.radiusSlider.getValue(), "radius", "Zlife's radius ");
		NewCellView.ZlifeDna.add((double)NewCellView.ageFactorSlider.getValue()/100 , "ageFactor", "Zlife's age Factor ");
		
		int choose = NewCellView.cellChooser.getSelectedIndex();
		
		/*
		 * Choosing the right Zlifes type: ZLife, Zretador or Zetatron
		 */
		if(choose==0)
		{ //Create ZLIFE
			NewCellView.newCell = new Zlife( NewCellView.Game.getDimension(), 
						Integer.valueOf(NewCellView.xField.getText() ) ,
						Integer.valueOf(NewCellView.yField.getText()),
						NewCellView.ZlifeDna);
				
		}
		else
			if (choose==1)
			{	//CREATE ZRETADOR
				NewCellView.newZretador = new Zretador( NewCellView.Game.getDimension(), 
						Integer.valueOf(NewCellView.xField.getText() ) ,
						Integer.valueOf(NewCellView.yField.getText()),
						NewCellView.ZlifeDna);
			}
			
		else
			if (choose==2)
			{//CREATE ZETATRON
				
				//Add Zetatron's DNA parameters
				//WEIGHTS
				double HN0w0 = 1.6472350208469893;
				double HN0w1 =  1.5259734987014437;
				
				double HN1w0 = 2.6628529707196837;
				double HN1w1 = -4.3804934387500865;
				
				double HN2w0 = -2.530569115172068;
				double HN2w1 = 4.769453786615226 ;
				
				double HN3w0 = -1.8361723886103454;
				double HN3w1 = -0.596045032335354 ;
				
				double HN4w0 = 1.6588830527078904;
				double HN4w1 = 1.9764676548651472;
				
				double ON0w0 = 0.05225449773499675;
				double ON0w1 = -7.567386009338051;
				double ON0w2 = 2.7268222830976008;
				double ON0w3 = -0.6308534288149321;
				double ON0w4 = 1.2607713913426353;
				
				double ON1w0 = -2.112897695747519 ;
				double ON1w1 = 3.493104440667188;
				double ON1w2 = 2.1065398047867707;
				double ON1w3 = 4.019529986557601;
				double ON1w4= -3.6030767528508028;
				
				double ON2w0 = 0.48460613526771457;
				double ON2w1 = 3.2167614616048157;
				double ON2w2 = -5.260496061985509;
				double ON2w3 = -4.254364190317208;
				double ON2w4 = 0.2761967817824116;
					
					//WEIGHTS
				NewCellView.ZlifeDna.add(HN0w0, "HN0w0", "Zetatron Neural Network weight");
				NewCellView.ZlifeDna.add(HN0w1, "HN0w1", "Zetatron Neural Network weight");
					
				NewCellView.ZlifeDna.add(HN1w0, "HN1w0", "Zetatron Neural Network weight");
				NewCellView.ZlifeDna.add(HN1w1, "HN1w1", "Zetatron Neural Network weight");
					
				NewCellView.ZlifeDna.add(HN2w0, "HN2w0", "Zetatron Neural Network weight");
				NewCellView.ZlifeDna.add(HN2w1, "HN2w1", "Zetatron Neural Network weight");
				
				NewCellView.ZlifeDna.add(HN3w0, "HN3w0", "Zetatron Neural Network weight");
				NewCellView.ZlifeDna.add(HN3w1, "HN3w1", "Zetatron Neural Network weight");
					
				NewCellView.ZlifeDna.add(HN4w0, "HN4w0", "Zetatron Neural Network weight");
				NewCellView.ZlifeDna.add(HN4w1, "HN4w1", "Zetatron Neural Network weight");
					
				NewCellView.ZlifeDna.add(ON0w0, "ON0w0", "Zetatron Neural Network weight");
				NewCellView.ZlifeDna.add(ON0w1, "ON0w1", "Zetatron Neural Network weight");
				NewCellView.ZlifeDna.add(ON0w2, "ON0w2", "Zetatron Neural Network weight");
				NewCellView.ZlifeDna.add(ON0w3, "ON0w3", "Zetatron Neural Network weight");
				NewCellView.ZlifeDna.add(ON0w4, "ON0w4", "Zetatron Neural Network weight");
					
				NewCellView.ZlifeDna.add(ON1w0, "ON1w0", "Zetatron Neural Network weight");
				NewCellView.ZlifeDna.add(ON1w1, "ON1w1", "Zetatron Neural Network weight");
				NewCellView.ZlifeDna.add(ON1w2, "ON1w2", "Zetatron Neural Network weight");
				NewCellView.ZlifeDna.add(ON1w3, "ON1w3", "Zetatron Neural Network weight");
				NewCellView.ZlifeDna.add(ON1w4, "ON1w4", "Zetatron Neural Network weight");
					
				NewCellView.ZlifeDna.add(ON2w0, "ON2w0", "Zetatron Neural Network weight");
				NewCellView.ZlifeDna.add(ON2w1, "ON2w1", "Zetatron Neural Network weight");
				NewCellView.ZlifeDna.add(ON2w2, "ON2w2", "Zetatron Neural Network weight");
				NewCellView.ZlifeDna.add(ON2w3, "ON2w3", "Zetatron Neural Network weight");
				NewCellView.ZlifeDna.add(ON2w4, "ON2w4", "Zetatron Neural Network weight");
					
				
				NewCellView.newZetatron = new Zetatron( NewCellView.Game.getDimension(), 
						Integer.valueOf(NewCellView.xField.getText() ) ,
						Integer.valueOf(NewCellView.yField.getText()),
						NewCellView.ZlifeDna);
			}
	
	//ADD CORRECT ZLIFE'S NUMBER
	//Check the Zlife's number to create
	for(int i=0;i< NewCellView.multiplierSlider.getValue()+1;i++)
	{
		
/*
 * Choosing the right Zlifes type: ZLife, Zredator or Zetatron?
 */
		//ZLIFE 
		if(choose==0)
		{
			NewCellView.newCell = new Zlife( NewCellView.Game.getDimension(), 
					Integer.valueOf(NewCellView.xField.getText() ) ,
					Integer.valueOf(NewCellView.yField.getText()),
					NewCellView.ZlifeDna);
			
		if(NewCellView.multiplierSlider.getValue()<2)
			NewCellView.newCell .setAngleDegree( NewCellView.directionSlider.getValue());
		else
			NewCellView.newCell.setAngleRadians(Math.random()*2*Math.PI );
		
		NewCellView.newCell.setName( NewCellView.nameField.getText());
		NewCellView.Game.addCell(NewCellView.newCell);
		NewCellView.newCell.live();
		
		
		}
		else
			//ZRETADOR
		if (choose==1)
		{	
			NewCellView.newZretador = new Zretador( NewCellView.Game.getDimension(), 
					Integer.valueOf(NewCellView.xField.getText() ) ,
					Integer.valueOf(NewCellView.yField.getText()),
					NewCellView.ZlifeDna);
			
			if(NewCellView.multiplierSlider.getValue()<2)
				NewCellView.newZretador.setAngleDegree( NewCellView.directionSlider.getValue());
			else
				NewCellView.newZretador.setAngleRadians(Math.random()*2*Math.PI );
		
			
			NewCellView.newZretador.setName( NewCellView.nameField.getText());
			NewCellView.Game.addZretador(NewCellView.newZretador);
			NewCellView.newZretador.live();
		}
		else
			//ZETATRON
		if (choose==2)
		{	
			NewCellView.newZetatron = new Zetatron( NewCellView.Game.getDimension(), 
					Integer.valueOf(NewCellView.xField.getText() ) ,
					Integer.valueOf(NewCellView.yField.getText()),
					NewCellView.ZlifeDna);
			
			if(NewCellView.multiplierSlider.getValue()<2)
				NewCellView.newZetatron.setAngleDegree( NewCellView.directionSlider.getValue());
			else
				NewCellView.newZetatron.setAngleRadians(Math.random()*2*Math.PI );
			
		
			NewCellView.newZetatron.setName( NewCellView.nameField.getText());
			NewCellView.Game.addZetatron(NewCellView.newZetatron);
			NewCellView.newZetatron.live();
		}
	}
	
	NewCellView.dispose();
	
}
	
	/**
	 * Check if insert values are correct
	 */
	protected void checkValues()
	{
		try
		{
			Integer.valueOf(NewCellView.xField.getText() );
		}
		catch (NumberFormatException e)
		{
			NewCellView.xField.setForeground(Color.red);
			JOptionPane.showMessageDialog(null, 
					NewCellView.resource.getString( "jfnc_inputErrorDesc" ), 
					NewCellView.resource.getString( "jfnc_inputError" ), 
					JOptionPane.ERROR_MESSAGE);
		}
		
		try
		{
			Integer.valueOf(NewCellView.yField.getText() );
		}
		catch (NumberFormatException e)
		{
			NewCellView.yField.setForeground(Color.red);
			JOptionPane.showMessageDialog(null, 
					NewCellView.resource.getString( "jfnc_inputErrorDesc" ), 
					NewCellView.resource.getString( "jfnc_inputError" ), 
					JOptionPane.ERROR_MESSAGE);
		}
	}


}


