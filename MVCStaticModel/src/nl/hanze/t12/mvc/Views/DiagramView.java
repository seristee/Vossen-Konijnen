package nl.hanze.t12.mvc.Views;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class DiagramView extends JFrame{
	
	private JFrame frame;
	private ChartPanel panel;
	private JButton btnDiagPie;
	private JButton btnDiagBar;
	private JButton btnDiagClose;
	private View masterView;
	private Graphics graphic;
	
	/**
	 * Create population frame with the option of loading a pie diagram and a bar diagram.
	 */
	public DiagramView(View view)
	{
		frame = new JFrame();
		this.masterView = view;
		frame.setTitle("Population Charts");
		frame.setAlwaysOnTop(false);
		frame.setResizable(false);
		
		frame.setBounds(100,100,550,450);
		panel = new ChartPanel();
		panel.setLayout(null);
		
		btnDiagPie = new JButton("Pie chart");
		btnDiagBar = new JButton("Bar chart");
		btnDiagClose = new JButton("Close");
		
		btnDiagPie.setBounds(6, 6, 117, 29);
		btnDiagBar.setBounds(6, 41, 117, 29);
		btnDiagClose.setBounds(6, 380, 117, 29);
		
		panel.add(btnDiagPie);
		panel.add(btnDiagBar);
		panel.add(btnDiagClose);
		
		//System.out.println(graphic.toString());
		
		frame.setContentPane(panel);
	}
	public void setVisible(boolean x)
    {
    	frame.setVisible(x);
    }
	public ChartPanel getPanel()
	{
		return panel;
	}
	public JButton getClose()
	{
		return btnDiagClose;
	}
	public JButton getBarBtn()
	{
		return btnDiagBar;
	}
	public JButton getPieBtn()
	{
		return btnDiagPie;
	}
}
