/**
* @author Francesco di Dio
* Date: 24 Novembre 2010 18.14
* Titolo: Gene.java
* Versione: 0.2.2 Rev.a:
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

package com.tabuto.jenetic;
import java.util.Vector;

/*
 * Ogni gene rappresenta un informazione elementare.
 * Un Gene è un tipo generico che può contenere e restituire un qualunque oggetto,
 * tuttavia la combinazione Genetica è consentita solo con i tipi primitivi:
 * Byte, int, double, boolean. Quando un gene è costituito da un tipo elementare
 * automaticamente la classe Gene lo riconosce e consente la combinazione Genetica,
 * per gli altri tipi ciò non avviene
 */

/**
 * {@code Gene<E>} is a Generics Class wrapper for any object type. 
 * It can contains and returns objects. 
 * {@code Gene} auto recognize the wrapped Object {@code Class} and set {@link Gene#mergeable}
 * flag as true in order to let {@link Dna} Class make a Gene Recombination;
 * This is possible only if the wrapped Object is a primitive type such as: Integer, Double, Boolean, Byte.
 * 
 * @author tabuto83
 * 
 * @version 0.2.2
 * 
 * @see com.tabuto.jenetic.dna
 */
public class Gene<E> extends Vector<E> {

    /**
	 * 
	 */
	private static final long serialVersionUID = 6683866692764850494L;

	/**
	 * The generic size one Vector contains wrapped Object
	 */
	private Vector<E> ob = new Vector<E>(1); 

	/**
	 * Flag to determinate if this Gene supported Genetic Recombination.
	 * Only the primitive types allows it;
	 */
    private boolean mergeable;

    /**
     * String indicates the Class Type of the Wrapped Object
     */
    private String Type="";
    
    /**
     * The Name of Gene
     */
    private String Name="";
    
    /**
     * A Short Description of this Gene
     */
	private String summary;

	/*
	 * *****************************************************
	 * CONSTRUCTOR METHODS
	 * *****************************************************
	 */
	
	/**
	 * Constructs a new generic {@code Gene} instance 
	 */
	public Gene()
	{
		this.mergeable=false;
		this.summary="";
	}
	
	/**
	 * Constructs a new {@code Gene} instance containing the Object {@code <E>}
	 * @param o Any Object we want wrap in a Gene
	 */
	public Gene(E object)
	{
		this();
		this.setValue(object);
	}
	
	/**
	 * Constructs a new {@code Gene} instance containing the Object {@code <E>}
	 * and the Gene's name 
	 * @param o Any Object we want wrap in a Gene
	 * @param name the Gene's Name
	 */
	public Gene(E object, String name)
	{   
		this(object);
		this.Name = name;
	}
	
	/**
	 * Constructs a new {@code Gene} instance containing the Object {@code <E>},
	 * Gene's name and Gene's Description
	 * @param o Any Object we want wrap in a Gene
	 * @param name the Gene's Name
	 */
	public Gene(E object, String name, String description)
	{   
		this(object);
		this.Name = name;
		this.setDescription(description);
	}
	
	/*
	 * *****************************************************
	 * PUBLIC METHODS (INTERFACE)
	 * *****************************************************
	 */
	
	/**
	 * Try to convert the actual Object into a Boolean value.
	 * @throws NumberFormatException
	 * @return Boolean value of Gene'e wrapped Object
	 */
	public boolean booleanValue() 
	{
		try{
			return Boolean.parseBoolean(this.getOb().toString());
			   }
			catch(NumberFormatException e)
			{
				System.out.println("Number format Exception: can't Convert this Gene as a Byte");
				System.out.println("This Gene contains a "+this.getType()+" value.");
				System.out.println("Use the appropriate Gene."+this.getType()+"Value method.");
				e.printStackTrace() ;
				System.exit(1);
				return false;
			}
	}
	
	/**
	 * Try to convert the actual Object into a {@link Byte} value.
	 * @throws NumberFormatException
	 * @return Byte value of Gene'e wrapped Object
	 */
	public Byte byteValue() 
	{
		try{
			return Byte.parseByte(this.getOb().toString());
			   }
			catch(NumberFormatException e)
			{
				System.out.println("Number format Exception: can't Convert this Gene as a Byte");
				System.out.println("This Gene contains a "+this.getType()+" value.");
				System.out.println("Use the appropriate Gene."+this.getType()+"Value method.");
				e.printStackTrace() ;
				System.exit(1);
				return 0;
			}
	}
	
	/**
	 * Try to convert the actual Object into a {@link Double} value.
	 * @throws NumberFormatException
	 * @return double value of Gene'e wrapped Object
	 */
	public double doubleValue() 
	{
		try{
		return Double.parseDouble(this.getOb().toString());	
		   }
		catch(NumberFormatException e)
		{
			System.out.println("Number format Exception: can't Convert this Gene as a Double");
			System.out.println("This Gene contains a "+this.getType()+" value.");
			System.out.println("Use the appropriate Gene."+this.getType()+"Value method.");
			e.printStackTrace() ;
			System.exit(1);
			return 0;
		}
	}
	
	/**
	 * @return a short Description of this Gene
	 */
	public String getDescription(){return this.summary;}
	
	/**
	 * @return the name of this Gene
	 */
	public String getName(){return this.Name;}
	
	/**
	 * @return the Object currently wrap in a Gene
	 */
	public E getOb(){
		return this.ob.get(0);
		}
	
	/**
	 * @return The type of the Object currently wrap into this Gene
	 */
	public String getType(){return this.Type;}
	
	/**
	 * Try to convert the actual Object into a {@link Integer} value.
	 * @throws NumberFormatException
	 * @return Integer value of Gene'e wrapped Object
	 */
	public int intValue()
	{
		try{
			return Integer.parseInt( this.getOb().toString());
			   }
			catch(NumberFormatException e)
			{
				System.out.println("Number format Exception: can't Convert this Gene as a Integer");
				System.out.println("This Gene contains a "+this.getType()+" value.");
				System.out.println("Use the appropriate Gene."+this.getType()+"Value method.");
				e.printStackTrace() ;
				System.exit(1);
				return 0;
			}
	}
	
	/**
	 * @return the Current {@link Gene#mergeable} state 
	 */
	public boolean isMeargeable(){return this.mergeable; }
	
	/**
	 * Set a short Description of this Gene
	 * @param s a short Gene's description
	 */
	public void setDescription(String s){this.summary=s;}
	
	/**
	 * Set this gene able (or not) to recombine itself with another one.
	 * @see Gene#mergeable
	 * @param m Boolean
	 */
	public void setMergeable(boolean m){this.mergeable=m;}
	
	/**
	 * Set the name of this Gene
	 * @param name 
	 */
	public void setName(String name){this.Name=name;}
	
	/**
	 * Add the Generic Object {@code <E>} to this Gene
	 * @param o Any Object we want wrap in a Gene
	 */
	public void setValue(E o)
	{
		if (this.ob.size()<1)
			{
			this.ob.add(o);
			this.setTypeName(o);
			this.autoDetectMergeable();
			}
	}
	/**
	 * Try to convert the actual Object into a {@link String} value.
	 * @throws NumberFormatException
	 * @return String value of Gene'e wrapped Object
	 */
	public String stringValue()
	{
		try{
			return  this.getOb().toString();
			   }
			catch(NumberFormatException e)
			{
				System.out.println("Can't Convert this Gene as a String");
				System.out.println("This Gene contains a "+this.getType()+" value.");
				e.printStackTrace() ;
				System.exit(1);
				return "";
			}
	}
	
	public String toString()
	{
		   return ("\nName: " + this.Name
				         + "\nGene Type: " + this.Type 		 
		          		 + "\nValue: " + this.getOb().toString()
		   				 + "\nDescription: " + this.summary);
	}
	

	/*
	 * *****************************************************
	 * DEPRECATED METHODS
	 * *****************************************************
	 */
	
	//Not permitted Methods in Gene.java: use setValue
	/**
	 * Not Avaible in {@link Gene}
	 */
	@Deprecated
	public void add(){};
	/**
	 * Not Avaible in {@link Gene}
	 */
	@Override
	@Deprecated
	public boolean add(E e){return false;}
	
	/**
	 * Not Avaible in {@link Gene}
	 */
	@Override
	@Deprecated
	public void add(int i,E e){}
	/**
	 * Not Avaible in {@link Gene}
	 */
	@Override
	@Deprecated
	public void addElement(E e){}
	
	/*
	 * *****************************************************
	 * PRIVATE METHODS
	 * *****************************************************
	 */
	
	/**
	 * Autocomplete the field {@link Gene#Type} with the Type of wrapped object
	 * @param e Generic Object
	 */
	private void setTypeName(E e)
	{
		String t = e.getClass().getCanonicalName();
		this.Type=t.substring( t.lastIndexOf('.')+1 );
		
	}
	
	/**
	 * If Gene's wrapped Object is a primitive type, such as: Double,
	 * Integer or Boolean set {@link Gene#setMergeable(boolean)} as true
	 */
	private void autoDetectMergeable()
	{
		if(this.Type.equalsIgnoreCase("Double")  ||
		   this.Type.equalsIgnoreCase("double ") ||
		   this.Type.equalsIgnoreCase("Integer") ||
		   this.Type.equalsIgnoreCase("int")	 ||
		   this.Type.equalsIgnoreCase("Boolean") ||
		   this.Type.equalsIgnoreCase("bool")    
		   )
			this.mergeable=true;
		else
			this.mergeable=false;
	}

	

}
