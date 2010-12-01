/**
* @author Francesco di Dio
* Date: 29/nov/2010 15.26.42
* Titolo: JLifeRightControlPanel.java
* Versione: 0.1.9 Rev.a:
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

package com.tabuto.jlife.gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import com.tabuto.j2dgf.gui.J2DControlPanel;
import com.tabuto.jlife.JLife;
import com.tabuto.jlife.Zlife;
import com.tabuto.jlife.Zretador;



public class JLifeRightControlPanel extends J2DControlPanel implements Observer{
	
 static JLife game;
	/**
	 * 
	 */
	private static final long serialVersionUID = 8686747746289847490L;
	
	 JLabel CountLabel = new JLabel("Count");
	 JButton Count = new JButton("Refresh");
	 JButton Statistic = new JButton("Statistics");
	 JLabel SelectZlife = new JLabel("Display Info on selected Zlife");
	 JButton displayInfo = new JButton("Refresh");
	 JTextArea ZlifeInfo = new JTextArea();
	 JScrollPane ZlifeInfoScroll = new JScrollPane(ZlifeInfo);
	 JTextField CellCountField = new JTextField(4);
	
	JLifeStatistic jls;
	public JLifeRightControlPanel(Dimension d, JLife game)
	{
		super(d);
		this.setLayout(new FlowLayout());
		addContent();
		this.game=game;
		jls= new JLifeStatistic(game);
		
		
	}

	protected void addContent()
	{
		
		 this.add(CountLabel);
		 
		 this.add(CellCountField);
		 CellCountField.setEditable(false);
		 
		 this.add(SelectZlife);
		 
		ZlifeInfoScroll.setPreferredSize(new Dimension(this.getWidth(), 300));
		//ZlifeInfo.setLineWrap(true);
		 
		 displayInfo.addActionListener(new ActionListener()
			{
	 			public void actionPerformed( ActionEvent action )
						{
	 					   	if(game.getSelectedCell()!=null)
	 					   	{
	 					   		if(game.getSelectedCell() instanceof Zlife)
	 					   	jls.calculateStatistics(game.cellsGroup);
	 					   	if(game.getSelectedCell() instanceof Zretador)
		 					   	jls.calculateStatistics(game.zretadorGroup);
	 					   		
	 					   		ZlifeInfo.setText( 
	 					   			jls.toString());
	 					   	ZlifeInfo.setCaretPosition(0);
	 					   	}
						}
				});
		 
		
		 this.add(ZlifeInfoScroll);
		 this.add(displayInfo);
		 
		 this.add(Statistic);
		 Statistic.addActionListener(new ActionListener()
			{
	 			public void actionPerformed( ActionEvent action )
						{
	 				if(game.getSelectedCell() instanceof Zlife)
 					   	jls.calculateStatistics(game.cellsGroup);
 					   	if(game.getSelectedCell() instanceof Zretador)
	 					   	jls.calculateStatistics(game.zretadorGroup);
	 				ZlifeInfo.setText( 
			   				jls.toString());
			   	ZlifeInfo.setCaretPosition(0);
						}
				});
		 
	}
	
	private void setCellCount()
	{
		CellCountField.setText(  Integer.toString(game.getActualCellCount() ) );
	}
	
	public void setGame(JLife game)
	{
		this.game = game;
		jls.setGame(game);
	}


	
	@Override
	public void update(Observable o, Object arg) {
		
		if (arg instanceof String)
		{
			String message = (String) arg;
			if(message.equalsIgnoreCase("CountChange"))
				{ 
				setCellCount();
				}
		}
	}
}