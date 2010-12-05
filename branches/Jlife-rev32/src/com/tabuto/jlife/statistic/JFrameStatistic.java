/**
* @author Francesco di Dio
* Date: 05/dic/2010 16.59.47
* Titolo: JFrameStatistic.java
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

package com.tabuto.jlife.statistic;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.tabuto.jlife.JLife;



public class JFrameStatistic extends JFrame{

	private JPanel graphPanel;
	Statistic GameStatistic;
	Font axisFont = new Font("Helvetica", Font.PLAIN,  10);
	/**
	 * 
	 */
	private static final long serialVersionUID = -7506591100511422140L;

	public JFrameStatistic(Statistic s)
	{
		super("Statistics");
		GameStatistic = s;

    }
	
	
	/**
	 * Draw a bar chart represent the Cell's number in that STATE sorted by Cell Type.
	 * 
	 * @param d Panel Dimension where Chart will be draw
	 */
	public void drawStateBarChart(JPanel p, Graphics g)
	{
		GameStatistic.calculateStatistics();
		int GroupNumber = GameStatistic.GroupNumber;
		
		int[] horny = new  int[GroupNumber];
		int[] hungry= new  int[GroupNumber];
		int[] scaryZlifes= new  int[GroupNumber];
		int[] bored= new  int[GroupNumber];
		
		//Draw AXIS Y
		g.setColor( new Color(30,30,30));
		g.drawLine(20, 20, 20, p.getHeight() - 20);
		//Draw AXIS X
		g.drawLine(20, p.getHeight() - 20, p.getWidth() - 20, p.getHeight() - 20);
		
		g.setFont(axisFont);

		g.drawString("0",2, p.getHeight()-2);
		
		
	}
	
	
	

	


}
