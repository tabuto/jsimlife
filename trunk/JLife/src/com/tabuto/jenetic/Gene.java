/**
* @author Francesco di Dio
* Date: 21 Ottobre 2010 18.14
* Titolo: Gene.java
* Versione: 0.1 Rev.a:
*/

package com.tabuto.jenetic;

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
 * Classe che rappresenta un semplice modello di DNA come un vettore di Geni.
 * CI sono due tipi di gene (geneD e gebeB) i quali rispettivamente contengono o 
 * una variabile double o boolean. Ogni gene contiene inoltre una descrizione in formato String
 * sul tipo di informazione che contiene.
 * Il Dna è quindi semplicemente un oggetto contenitore di Geni con la capacità di memorizzare e/o 
 * caricare tutto il "genoma" su di un file XML usando i metodi saveDna o loadDna.
 * Aggiungere un gene al DNA è semplicissimo: basta usare i metodi add().
 */

/*
 * Ogni gene rappresenta un informazione elementare, che può essere una stringa
 * un double, un intero. 
 */


/**
 * Class <code> Dna</code>
 * <p>
 * <code>Gene</code> is a class that represent a simple model of Gene.
 * Each <code>Gene</code>  can contains two value type: Boolean or Double and a description about itself.
 * The <code>Gene</code> class can return his own value using the methods {@link Gene#getMeB()} for boolean values or
 * {@link Gene#getMeD()} for double values or {@link Gene#getMeI()} for int value.
 *
 * @author tabuto83
 * 
 * @version 0.1.0
 * 
 * @see com.tabuto.jenetic.Dna
 */

public class Gene {

	private double DOUBLE;
	private boolean BOOL;
	
	private boolean isDouble = false;
	private boolean isBool = false;

	private String Descr;

	public Gene()
	{
		DOUBLE=0;
		BOOL=false;
		Descr=" ";
	}
	
	
	public Gene(double d)
	{
		this();
		DOUBLE=d;
		isDouble=true;
	}
	
	public Gene(double d, String s)
	{
		this();
		DOUBLE=d;
		isDouble=true;
		Descr = s;
	}
	
	
	public Gene(boolean b)
	{   
		this();
		BOOL=b;
		isBool=true;
	}
	
	public Gene(boolean b, String s)
	{   
		this();
		BOOL=b;
		isBool=true;
		Descr = s;
	}
	
	public void setGene(boolean b){BOOL = b; isBool=true;}
	
	public void setGene(double d){DOUBLE = d; isDouble=true;}
	
	public void setGene(int i){DOUBLE= i;isDouble=true;}
	
	public int getMeI(){return (int)DOUBLE; }
	
	public double getMeD(){return DOUBLE;}
	
	public boolean getMeB(){return BOOL;}
	
	public boolean isBool(){return isBool;}
	public boolean isDouble(){return isDouble;}
	
	public void setDescription(String s){Descr=s;}
	public String getDescription(){return Descr;}
	
	public String toString()
	{
		String output;
	   if (this.isBool)
		   {output="\nGene Type: Boolean"
		          +"\nGene Value: " + String.valueOf(BOOL)
		          + "\nDescription: " + Descr ;
		   }
	   else 
		   {  output="\nGene Type: Double"
		          +"\nGene Value: " + String.valueOf(DOUBLE)
		          + "\nDescription: " + Descr ;
		   }
	   return output;
	}

}

