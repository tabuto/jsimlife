/**
* @author Francesco di Dio
* Date: 29/dic/2010 23.58.01
* Titolo: JSLControlPanelView.java
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

package com.tabuto.jsimlife.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import com.tabuto.j2dgf.gui.J2DControlPanel;
import com.tabuto.jsimlife.JSLStatistic;
import com.tabuto.jsimlife.JSimLife;

/**
 * Simple class extends J2DControlPanel and display a simple JTable contains 
 * some statistical data. JSLControlPanel observes JSimLife game and uses the 
 * {@link JSLStatistic#calculateStatistics()} methods to update the information.
 * @author tabuto83
 * 
 * @see JSLStatistic
 * @see JSimLife
 *
 */
public class JSLControlPanelView extends J2DControlPanel implements Observer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JSimLife Game;
	
	private Timer timer;
	private TimerTask statTask;
	
	private JPanel StatisticPanel;
	private JLabel StatisticInfoLabel;
	public JTextArea StatisticTextArea = new JTextArea();
	public JTable StatisticTable;
	
	
	JScrollPane StatisticScroll; 
	private ResourceBundle resource;
	
	private JSLStatistic jls;
	
	
	/**
	 * Creates a new control panel using the JSimLife game passing as parameters
	 * @param game JSimLife
	 */
	public JSLControlPanelView(JSimLife game) {
		super(game.getDimension());
		setLayout(new BorderLayout());
		setPanelWidth(350);
		Game = game;
		resource = ResourceBundle.getBundle("StringAndLabels", Game.getConfiguration().getLocale());
		jls= new JSLStatistic(Game);
		
		
		StatisticPanel = new JPanel();
		StatisticPanel.setLayout(new FlowLayout());
		StatisticPanel.setSize(new Dimension(1000,150));
		//StatisticPanel.setPreferredSize(new Dimension(this.getWidth()+100,200));
		
		StatisticTable = new JTable(jls.StatisticData);
		
		StatisticTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		StatisticTable.setSize(1000, 150);
		StatisticTable.setEnabled(false);
		//StatisticScroll = new JScrollPane(StatisticTable);
		
		//StatisticScroll.setAutoscrolls(true);
		//StatisticScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		//StatisticScroll = new JScrollPane(StatisticTextArea);
		
		
		StatisticInfoLabel = new JLabel(resource.getString( "stat_title" ));
		StatisticTextArea.setBackground(Color.BLACK);
		StatisticTextArea.setForeground(Color.GREEN);
		StatisticTextArea.setFont(new Font("Courier", Font.BOLD, 10));
		
	
		
		timer = new Timer();
		statTask = new StatisticTask();
		timer.schedule( statTask, 500,1500 );
		
		addContent();
		
	}

	@Override
	protected void addContent() {
		
		
		 //StatisticScroll.setPreferredSize(new Dimension(this.getWidth(), 300));
		 //StatisticPanel.add(StatisticScroll,BorderLayout.CENTER);
		 
		 StatisticScroll = new JScrollPane(StatisticTable);
		 StatisticScroll.setSize(1000, 150);
		 StatisticPanel.add(StatisticScroll);
		 add(StatisticInfoLabel, BorderLayout.NORTH);
		 
		add(new JScrollPane(StatisticPanel),BorderLayout.CENTER);
		 
		
		 
		
		 
	}

	/**
	 * @return the JSLStatistic object
	 */
	public JSLStatistic getStatistic(){return jls;}
	
	/**
	 * The JSimLife game to set. 
	 * Useful when load a new Game to reference the new one.
	 * @param game JSimLife
	 */
	public void setGame(JSimLife game)
	{
		Game = game;
		//Set the new Game for statistic too
		jls.setGame(game);
		
	}
	
	@Override
	public void update(Observable arg0, Object arg1) 
	{
		
		
	}
	
	
	/**
	 * Implements a timer task to calculate statistic every Timer check
	 * @see TimerTask
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
			JSLControlPanelView.this.jls.calculateStatistics();
			
			JSLControlPanelView.this.StatisticTextArea.setText( 
					JSLControlPanelView.this.jls.toString()
														  );
			JSLControlPanelView.this.StatisticTextArea.setCaretPosition(0);
				
		}
		
		
	}

}
