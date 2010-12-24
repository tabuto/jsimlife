/**
* @author Francesco di Dio
* Date: 24/dic/2010 15.26.42
* Titolo: JLifeRightControlPanel.java
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

package com.tabuto.jlife.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import com.tabuto.j2dgf.gui.J2DControlPanel;
import com.tabuto.jlife.JLife;
import com.tabuto.jlife.statistic.Statistic;


/**
 * SImple panel shows some Statistical info;
 * @author tabuto83
 *
 */
public class JLifeRightControlPanel extends J2DControlPanel implements Observer{
	
 static JLife game;
 private Timer timer;
 private TimerTask statTask;
	/**
	 * 
	 */
	private static final long serialVersionUID = 8686747746289847490L;
	
	 JLabel CountLabel = new JLabel("Count");
	 JButton Count = new JButton("Refresh");
	 JButton Statistic = new JButton("Refresh Statistics");
	 JLabel StatisticInfoLabel = new JLabel("Show Statistical Info");
	 JButton graphButton = new JButton("Charts");
	 JTextArea ZlifeInfo = new JTextArea();
	 JScrollPane ZlifeInfoScroll = new JScrollPane(ZlifeInfo);
	 JTextField CellCountField = new JTextField(4);
	
	Statistic jls;
	
	/*
	 * The constructor copy a reference to the actual Game, and update
	 * using a timer the statistical info.
	 */
	public JLifeRightControlPanel(Dimension d, JLife game)
	{
		super(d);
		this.setLayout(new FlowLayout());
		addContent();
		this.game=game;
		jls= new Statistic(game);
		
		timer = new Timer();
		statTask = new StatisticTask();
		timer.schedule( statTask, 500,1500 );
		ZlifeInfo.setBackground(Color.BLACK);
		ZlifeInfo.setForeground(Color.GREEN);
		ZlifeInfo.setFont(new Font("Courier", Font.BOLD, 10));
		
	}

	//Add component to this panel
	protected void addContent()
	{
		 this.add(StatisticInfoLabel);
		 
		ZlifeInfoScroll.setPreferredSize(new Dimension(this.getWidth(), 300));
		 this.add(ZlifeInfoScroll);
		 /*
		  * When Statistic button pressed, refresh statistic
		  */
		 this.add(Statistic);
		 Statistic.addActionListener(new ActionListener()
			{
	 			public void actionPerformed( ActionEvent action )
						{
 					   	jls.calculateStatistics();
 					 
	 				ZlifeInfo.setText( 
			   				jls.toString());
			   	ZlifeInfo.setCaretPosition(0);
						}
				});
		 
	}
	
	//Return the actual cell count
	private void setCellCount()
	{
		CellCountField.setText(  Integer.toString(game.getActualCellCount() ) );
	}
	
	//Set the Game. Useful when load a new Game to reference the new one
	public void setGame(JLife g)
	{
		game = g;
		//Set the new Game for statistic too
		jls.setGame(game);
		
	}


	
	@Override
	/**
	 * When Game cell count change, update statistic!
	 */
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
	
	/*
	 * Implements a timer task to calculate statistic every Timer check
	 */
	public class StatisticTask extends TimerTask
	{

		public StatisticTask()
		{
			super();
		}
		
		@Override
		public void run() 
		{
			JLifeRightControlPanel.this.jls.calculateStatistics();
			
			JLifeRightControlPanel.this.ZlifeInfo.setText( 
					JLifeRightControlPanel.this.jls.toString()
														  );
			JLifeRightControlPanel.this.ZlifeInfo.setCaretPosition(0);
				
		}
		
		
	}
}