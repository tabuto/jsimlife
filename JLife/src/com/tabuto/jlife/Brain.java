/**
* @author Francesco di Dio
* Date: 17/dic/2010 16.58.07
* Titolo: Brain.java
* Versione: 0.1.12.2 Rev.a:
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

package com.tabuto.jlife;

import java.io.Serializable;
import java.util.Vector;

import org.neuroph.nnet.MultiLayerPerceptron;
import org.neuroph.util.NeuronProperties;
import org.neuroph.util.TransferFunctionType;
import org.neuroph.util.VectorParser;



/**
 * Brain class wraps a Percpetron Neural Network composed by 2 input neurons, 
 * 5 neurons in an hide layer and 3 output neurons.
 * The input values accepts the following Zlife's parameters:
 * energy/hungryEnergy and energy/HornyEnergy
 * The net output is the neurons max value. To each output value is associated
 * a Zlife's state. The net is provided by neuroph package and it was trained using
 * the neuroph tool easyNeuroph which return the net neuron's weights.
 * 
 * @author tabuto83
 * 
 * @version 0.1.12.2
 *
 */
public class Brain implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * MultiLayer Perceptron net provides by Neuroph package
	 * @see MultiLayerPerceptron
	 */
	public MultiLayerPerceptron myMlPerceptron; 
	
	/**
	 * The output neurons vector
	 */
	public Vector<Double> output = new Vector<Double>();
	
	/**
	 * String value of net's architecture
	 */
	String LayerString="2 5 3";
	
	
	Vector<Integer> layers = new Vector<Integer>();
	
	/**
	 * result of the net: 
	 * if reusult = 1 State = Horny
	 * if reusult = 2 State = Hungry
	 * if reusult = 3 State = Bored
	 */
	int result;
	
	/**
	 * Brain constructor
	 */
	public Brain()
	{
		//LAyerSize Vector
	
		
		//Properties: TransferFunction, not use BIAS! 
		NeuronProperties neuronProperties = new NeuronProperties(TransferFunctionType.SIGMOID, false);
	
		//create layers
		layers = VectorParser.parseInteger(LayerString);
		//create neural net
		myMlPerceptron = new MultiLayerPerceptron(layers,neuronProperties);
		myMlPerceptron.connectInputsToOutputs();
		//Init result
		result=0;
		
	}
	
	/**
	 * @return a integer values represent the future state of Zetatron
	 */
	public int think(double input1, double input2)
	{
		
		myMlPerceptron.setInput(input1, input2);
		myMlPerceptron.calculate();
		output = myMlPerceptron.getOutput();
				
		
		if ( (output.get(0) >= output.get(1)) && (output.get(0)>=output.get(2)))
				result = 1; //horny
		if ( (output.get(1) >= output.get(0)) && (output.get(1)>=output.get(2)))
				result = 2; //Hungry
		if ( (output.get(2) >= output.get(0)) && (output.get(2)>=output.get(1)))
			result= 3; //bored
		
		return result;
		/*
		int isHungry = (int) Math.rint( output.get(0) );
		int isHorny = (int) Math.rint( output.get(1) );
		int isBored = (int) Math.rint( output.get(2) );

		
		if ( isHungry==1  )
				return 1;  //IS HUNGRY
		else
			if(isHorny==1)
				return 2;  //IS HORNY
			else
			//if (isBored==1)
				return 3;   // IS BORED
		*/
		
	}

	/**
	 * Set the neural network's weights 
	 * @param weightsArray Vector contains the Neural Network's weights in the roght order
	 */
	public void setWeights( Vector<Double> weightsArray)
	{
        int i=0;
        
        //HIDDEN
		myMlPerceptron.getLayerAt(1).getNeuronAt(0).getWeightsVector().get(0).setValue( weightsArray.get(i++));
		myMlPerceptron.getLayerAt(1).getNeuronAt(0).getWeightsVector().get(1).setValue( weightsArray.get(i++));
		
		myMlPerceptron.getLayerAt(1).getNeuronAt(1).getWeightsVector().get(0).setValue(weightsArray.get(i++));
		myMlPerceptron.getLayerAt(1).getNeuronAt(1).getWeightsVector().get(1).setValue( weightsArray.get(i++));
		
		myMlPerceptron.getLayerAt(1).getNeuronAt(2).getWeightsVector().get(0).setValue(weightsArray.get(i++));
		myMlPerceptron.getLayerAt(1).getNeuronAt(2).getWeightsVector().get(1).setValue(weightsArray.get(i++));
		
		myMlPerceptron.getLayerAt(1).getNeuronAt(3).getWeightsVector().get(0).setValue(weightsArray.get(i++));
		myMlPerceptron.getLayerAt(1).getNeuronAt(3).getWeightsVector().get(1).setValue(weightsArray.get(i++));
		
		myMlPerceptron.getLayerAt(1).getNeuronAt(4).getWeightsVector().get(0).setValue(weightsArray.get(i++));
		myMlPerceptron.getLayerAt(1).getNeuronAt(4).getWeightsVector().get(1).setValue(weightsArray.get(i++));
		
		
		//OUTPUT
		myMlPerceptron.getLayerAt(2).getNeuronAt(0).getWeightsVector().get(0).setValue(weightsArray.get(i++));
		myMlPerceptron.getLayerAt(2).getNeuronAt(0).getWeightsVector().get(1).setValue(weightsArray.get(i++));
		myMlPerceptron.getLayerAt(2).getNeuronAt(0).getWeightsVector().get(2).setValue(weightsArray.get(i++));
		myMlPerceptron.getLayerAt(2).getNeuronAt(0).getWeightsVector().get(3).setValue(weightsArray.get(i++));
		myMlPerceptron.getLayerAt(2).getNeuronAt(0).getWeightsVector().get(4).setValue(weightsArray.get(i++));
	
		
		
		myMlPerceptron.getLayerAt(2).getNeuronAt(1).getWeightsVector().get(0).setValue(weightsArray.get(i++));
		myMlPerceptron.getLayerAt(2).getNeuronAt(1).getWeightsVector().get(1).setValue(weightsArray.get(i++));
		myMlPerceptron.getLayerAt(2).getNeuronAt(1).getWeightsVector().get(2).setValue(weightsArray.get(i++));
		myMlPerceptron.getLayerAt(2).getNeuronAt(1).getWeightsVector().get(3).setValue(weightsArray.get(i++));
		myMlPerceptron.getLayerAt(2).getNeuronAt(1).getWeightsVector().get(4).setValue(weightsArray.get(i++));

		
		myMlPerceptron.getLayerAt(2).getNeuronAt(2).getWeightsVector().get(0).setValue(weightsArray.get(i++));
		myMlPerceptron.getLayerAt(2).getNeuronAt(2).getWeightsVector().get(1).setValue(weightsArray.get(i++));
		myMlPerceptron.getLayerAt(2).getNeuronAt(2).getWeightsVector().get(2).setValue(weightsArray.get(i++));
		myMlPerceptron.getLayerAt(2).getNeuronAt(2).getWeightsVector().get(3).setValue(weightsArray.get(i++));
		myMlPerceptron.getLayerAt(2).getNeuronAt(2).getWeightsVector().get(4).setValue(weightsArray.get(i++));
	
	}
	
	/**
	 * Set the standard weights into a double vector and pass it 
	 * to the setWeight method
	 */
	public void setStandardWeights()
	{
		Vector<Double> standardWeights = new Vector<Double>();
		
		standardWeights.add( -1.7068557692874813); //HN0W0
		standardWeights.add( 2.532721864515596 ); //HN0W1
		
		standardWeights.add(1.3739744761584733); //HN1W0
		standardWeights.add(2.533733799728229 ); //HN1W1
		
		standardWeights.add(1.0686677673720573); //HN2W0
		standardWeights.add(-2.3162442286999982 ); //HN2W1
		
		standardWeights.add(-0.8606533194531912); //HN3W0
		standardWeights.add(-1.8601358721805068 ); //HN3W1
		
		standardWeights.add(-1.3242481251566245); //HN4W0
		standardWeights.add(1.2504634391663554 ); //HN4W1
		
		//****OUTPUT
		
	    standardWeights.add(1.5028770918715462 ); //ON0W0
		standardWeights.add(1.3764983304662988); //ON0W1
		standardWeights.add(-2.8171737895626094 ); //ON0W2
		standardWeights.add(-2.7828124962197407); //ON0W3
		standardWeights.add(-0.024573384830880406); //ON0W4
		
		standardWeights.add(0.5308116568826683 ); //ON1W0
		standardWeights.add(-2.856598990341598); //ON1W1
		standardWeights.add(0.6306841387877276);//ON1W2
		standardWeights.add(1.90708639017587); //ON1W3
		standardWeights.add(0.360862430510777); //ON1W4
			
		
		standardWeights.add(-2.3949325734028313); //ON2W0
		standardWeights.add(0.26523796670852556); //ON2W1
		standardWeights.add(1.9528769005568885 );//ON2W2
		standardWeights.add(-0.16678356868881347); //ON2W3
		standardWeights.add(-1.7886098464534785); //ON2W4
		
		
		this.setWeights(standardWeights);
		
	}
	
}
