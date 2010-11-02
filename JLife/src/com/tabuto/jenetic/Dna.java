/**
* @author Francesco di Dio
* Date: 21 Ottobre 2010 18.14
* Titolo: Dna.java
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
 * Classe che rappresenta un semplice modello di DNA come un vettore di Geni.
 * CI sono due tipi di gene (geneD e gebeB) i quali rispettivamente contengono o 
 * una variabile double o boolean. Ogni gene contiene inoltre una descrizione in formato String
 * sul tipo di informazione che contiene.
 * Il Dna è quindi semplicemente un oggetto contenitore di Geni con la capacità di memorizzare e/o 
 * caricare tutto il "genoma" su di un file XML usando i metodi saveDna o loadDna.
 * Aggiungere un gene al DNA è semplicissimo: basta usare i metodi add().
 */



package com.tabuto.jenetic;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;


import org.jdom.*;
import org.jdom.input.SAXBuilder;
import org.jdom.output.*;

/**
 * Class <code> Dna</code>
 * <p>
 * <code>Dna</code> is a class that represent a simple model of DNA as a {@link Gene} Vector.
 * Add a {@link Gene} into <code>Dna</code> is simple by using the {@link Dna#add(Gene)} ,{@link Dna#add(Double)}
 * {@link Dna#add(Boolean)} methods.
 * So the <code>Dna</code> is just a information's object container, able to save or load itself using an XML file
 * thanks to methods {@link Dna#saveDna(String)} and {@link Dna#loadDna(String)}
 * <code>Dna</code> class also provides a {@link Dna#merge(Dna)} method simulating the Dna Genetic Recombination.
 * During this process it's possible introduce some "noise" using the {@link Dna#param} variable.
 *
 * @author tabuto83
 * 
 * @version 0.1.0
 * 
 * @see com.tabuto.jenetic.Gene
 */

public class Dna implements Serializable{
	
	/**
	 * The serial Version UID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * The Vector Gene is a dinamic array where {@link Gene} are stored
	 */
	public Vector<Gene> DNA;
	/**
	 * This variable represent the percent of mutation introduced by merge method.
	 * The param value should be between 0 and 1.
	 */
	private double param;
	
	/**
	 * CONSTRUCTOR
	 * <p>
	 * Dna()
	 * <p>
	 * This method instance a new empty <code>Dna</code> Object.
	 */
	public Dna()
	{
		this.DNA = new Vector<Gene>();
		this.param = 0;
	}
	
	/**
	 * CONSTRUCTOR
	 * <p>
	 * Dna( double parameter)
	 * <p>
	 * This method instance a new empty <code>Dna</code> Object with a parameter for Genetic Recombination.
	 * 
	 * @param p <code>double</code> Dna Parameter {@link Dna#param}
	 */
	public Dna(double p)
	{
		this.DNA = new Vector<Gene>();
		this.param = p;
	}
	
	/**
	 * CONSTRUCTOR
	 * <p>
	 * Dna( double parameter)
	 * <p>
	 * This method instance a new  <code>Dna</code> Object using a previously saved XML file
	 * with a parameter for Genetic Recombination.
	 * 
	 * @param fileName <code>String</code> 
	 */
	public Dna(String fileName)
	{
		 DNA = new Vector<Gene>();
		 param = 0;
		 this.load(fileName);
	}
	
	/**
	 * CONSTRUCTOR
	 * <p>
	 * Dna( double parameter)
	 * <p>
	 * This method instance a new  <code>Dna</code> Object using a previously saved XML file.
	 * 
	 * @param fileName <code>String</code> 
	 * @param p <code>double</code> Dna Parameter {@link Dna#param}
	 */
	public Dna(String fileName, double p)
	{
		DNA = new Vector<Gene>();
		param = p;
		this.load(fileName);
	}

	/**
	 * Set the {@link Dna#param} variable of Dna.
	 * @param p <code>double</code> set new {@link Dna#param}
	 */
	public void setParam(double p)
	{this.param=Math.abs(p);}
	
	/**
	 * Return the {@link Dna#param} variable of Dna.
	 * @return <code>double</code> Dna Parameter {@link Dna#param}
	 */
	public double getParam() {return param;}
	
	/**
	 * Append a new {@link Gene} into <code>Dna</code> Vector
	 * @param g <code>Gene</code> newGene
	 */
	public void add(Gene g)
	{
		this.DNA.add(g);
	}
	
	/**
	 * Add a new {@link Gene} into <code>Dna</code> Vector with double value.
	 * @param d <code>double</code> Gene Value
	 */
	public void add(double d)
	{
		this.DNA.add( new Gene(d) );
	}
	
	/**
	 * Add a new {@link Gene} into <code>Dna</code> Vector with double value.
	 * @param d <code>double</code> Gene Value
	 * @param s <code>String</code> Gene's Description
	 */
	public void add(double d, String s)
	{
		this.DNA.add( new Gene(d,s) );

	}
	
	/**
	 * Add a new {@link Gene} into <code>Dna</code> Vector with boolean value.
	 * @param b <code>boolean</code> Gene Value
	 */
	public void add(boolean b)
	{
		this.DNA.add( new Gene(b) );
	}
	
	/**
	 * Add a new {@link Gene} into <code>Dna</code> Vector with boolean value.
	 * @param b <code>boolean</code> Gene Value
	 * @param s <code>String</code> Gene's Description
	 */
	public void add(boolean b, String s)
	{
		this.DNA.add( new Gene(b,s) );
	}
	
	/**
	 * Return the {@link Gene} at specific index
	 * @param index <code>int</code> Dna's Vecotor Index
	 * @return g <code> Gene </code>
	 */
	public Gene getGene(int index)
	{
		return this.DNA.get(index);
	}
	
	/**
	 * Return the {@link Gene} double's value at specific index.
	 * @param index <code>int</code> Dna's Vecotor Index
	 * @return d <code>double</code>
	 */
	public double getGeneDouble(int index)
	{
		return this.DNA.get(index).getMeD();
	}
	
	public boolean getGeneBoolean(int index)
	{
		return this.DNA.get(index).getMeB();
	}
	
	
	public int getSize(){return this.DNA.size();}
	
	
	/**
	 * This method merge this Dna with other and return a new Dna Code.
	 * This method add some random error in the merging process to simulate a 
	 * genetic mutation;
	 * @param otherDna
	 * @return new Dna
	 */
	public Dna merge(Dna otherDna)
	{
	     Dna result = new Dna();
	     int size, i=0;
	     
	     if ( this.getSize() == otherDna.getSize() )
	    	 { 
	    	 	size = this.getSize();
	    	 	for(i=0;i<size;i++)
	    	 	{
	    	 		result.add(   combineGene( this.DNA.get(i) , otherDna.DNA.get(i) )  );
	    	 	}
	    	 }
	     
	     return result;
	}
	
	private Gene combineGene(Gene g1, Gene g2)
	{
		Gene G = new Gene();
		
		if (g1.isBool() && g2.isBool() )
			G.setGene( g1.isBool() & g2.isBool() );
		
		if (g1.isDouble() && g2.isDouble() )
			if (g2.getMeD() >= g1.getMeD() )
				G.setGene( g1.getMeD() + Math.random() * g2.getMeD() +  (  g1.getMeD() + Math.random() * g2.getMeD()    )* param   ); 
			else
				G.setGene( g2.getMeD() + Math.random() * g1.getMeD() +  (  g2.getMeD() + Math.random() * g1.getMeD()    )* param   ); 
		
		return G;
	}
	

	/**
	 * Returns a XML file represent this DNA with description List of ist genes
	 * @param fileName <code>String</code> File Name
	 * @see Dna#load(String)
	 */
	public void toXML(String fileName)
	{

		try
		{
		 //Elemento radice
	      Element DnaRoot = new Element("DNA");
	      //Documento
	      Document document = new Document(DnaRoot);
	      
	      for (int i=0; i< this.DNA.size();i++)
	      {
	    	  Element gene = new Element("gene");
	    	  
	    	  gene.setAttribute("index", String.valueOf(i));
	    	  
	    	  if ( this.DNA.get(i).isBool() )
	    		  {
	    		  gene.setAttribute("Type", "Bool" );
	    	  	  gene.setAttribute("value", String.valueOf( this.DNA.get(i).getMeB() ) );
	    		  }
	    	  
	    	  if ( this.DNA.get(i).isDouble() )
	    	  {
	    		  gene.setAttribute("Type", "Double" );
	    	  	  gene.setAttribute("value", String.valueOf( this.DNA.get(i).getMeD() ) );
	    	  }
	    	 DnaRoot.addContent( gene );
	     }  
	    	  
	      XMLOutputter outputter = new XMLOutputter();
	      outputter.setFormat(Format.getPrettyFormat());
	      outputter.output(document, new FileOutputStream(fileName+".xml"));
	     
		  }//endof Try	
	      
		  catch (IOException e) {
		      System.err.println("Errore durante il parsing del documento");
		      e.printStackTrace();
		    } 
	}

	/**
	 * Load a list of Genes from an XML file previously saved using {@link Dna#save(String)}
	 * @param fileName <code>String</code> File Name
	 */
	public void load(String fileName)
	{
		try
		{
			  SAXBuilder builder = new SAXBuilder();
		      Document document = builder.build(new File(fileName+".xml"));
		      
		      Element root = document.getRootElement();
		      List children = root.getChildren();
		      Iterator iterator = children.iterator(); 
		      
		      while(iterator.hasNext())
		      {
		          Element gene = (Element)iterator.next(); 
		          
		          if ( gene.getAttributeValue("Type").equalsIgnoreCase("Bool") )
		         	 this.DNA.add(new Gene( Boolean.parseBoolean(gene.getAttributeValue("value") )  ));
		        	
		          if ( gene.getAttributeValue("Type").equalsIgnoreCase("Double") )
		        	  this.DNA.add(new Gene( Double.parseDouble(gene.getAttributeValue("value") )  ));
		      }	
		}
		
		catch(Exception e)
		{
			 System.err.println("Errore durante la lettura dal file");
		      e.printStackTrace(); 
		}
	}
	
	/**
	 * Save a copy of DNA object in a binary file with .dna extension. It can be load using the following code:
	 *  <pre>
	 *  Dna MyNewDna = null;
	 *  FileInputStream fis = null;
	 *  ObjectInputStream in = null;
	 *  try
	 *     {
	 *     fis = new FileInputStream(filename);
	 *     in = new ObjectInputStream(fis);
	 *     MyNewDna = (Dna)in.readObject();
	 *     in.close();
	 *     }
	 *     catch(IOException ex)
	 *     {
	 *     ex.printStackTrace();
	 *     }
	 *     catch(ClassNotFoundException ex)
	 *     {
	 *     ex.printStackTrace();
	 *     }
	 *     </pre>
	 *
	 * @param filename
	 */
	public void save(String filename)
	{
		 FileOutputStream fos = null;
	     ObjectOutputStream out = null;
		 try
		 {
			 fos = new FileOutputStream(filename+".dna");
			 out = new ObjectOutputStream(fos);
			 out.writeObject(this);
			 out.close();
		 }
		  catch(IOException ex)
		  		{
			  		ex.printStackTrace();
	 			}
	}
	
	public String toString()
	{
		String output="\nDNA Descriptor:";
		for(int i=0;i<this.DNA.size();i++)
			output+=this.DNA.get(i).toString();
		
		return output + "\nSize:" + this.getSize();
	}
	
}

