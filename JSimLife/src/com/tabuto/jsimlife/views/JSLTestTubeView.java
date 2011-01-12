/**
* @author Francesco di Dio
* Date: 11/gen/2011 15.10.54
* Titolo: JSLTestTubeView.java
* Versione: 0.2.0.b3 Rev.a:
*/


/*
 * Copyright (c) 2011 Francesco di Dio.
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

package com.tabuto.jsimlife.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.tabuto.jsimlife.JSimLife;

/**
 * NOT YET USED
 * @author tabuto83
 *
 */
public class JSLTestTubeView extends JFrame implements Runnable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JSimLife testTubeGame;
	private JSLSimulationView testTube;
	
	BufferStrategy bs;      //BufferStrategy
	
	JPanel north;
	JPanel center;
	JPanel south;
	
	Dimension tubeDimension = new Dimension(150,400);
	
	Thread t;
	
	public JSLTestTubeView()
	{
		
		super();
		t = new Thread(this);
		setTitle("Test Tube");
		
		createBufferStrategy(1);
		bs = getBufferStrategy();
		
		setPreferredSize(new Dimension((int)tubeDimension.getWidth()+5, 
									(int)tubeDimension.getHeight()+5 ) );
		setLayout( new BorderLayout());
		north = new JPanel();
		center = new JPanel();
		south = new JPanel();
		
		initComponent();
		
		
		
		//ADD ICON
		this.setIconImage(java.awt.Toolkit.getDefaultToolkit().getImage
        		(this.getClass().getResource("/com/tabuto/jsimlife/icons/icon_alpha_48x48.gif")));
		
		
		pack();
		setVisible(true);
		
		testTube.run(testTubeGame);
		//runTestTube();
		
	}
	 
	public void initComponent()
	{
		testTubeGame = new JSimLife(tubeDimension);
		testTube = new JSLSimulationView(tubeDimension, testTubeGame);
		testTubeGame.initGame();
		testTube.setBackground(Color.WHITE);
		testTube.setFocusable(true);
		
		center.add(testTube);
		add(center,BorderLayout.CENTER);
	}
	
	public void runTestTube()
	{
		if(testTube instanceof JSLSimulationView)
    		while(testTubeGame.isActive()){ testTube.run(testTubeGame);};   
	}
	
	
	public void active()
	{
		testTubeGame.activate();
	}

	public void deactive()
	{
		testTubeGame.deactivate();
	}

	@Override
	public void run() {
		
		
    		
    	 
	}
}
