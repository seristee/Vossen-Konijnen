package nl.hanze.t12.mvc.Controllers;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JButton;

import nl.hanze.t12.mvc.Models.Simulator;
import nl.hanze.t12.mvc.Views.View;


public class ControllerSimulatorView extends Controller 
{
	private Simulator simulator;
	private JButton btnStep1;
	private JButton btnStep100;
	private JButton btnPause;
	private JButton btnDiag;
	private ArrayList<View> views;
	
	public ControllerSimulatorView(Simulator m)
	{
		super(m);
		simulator = m;
		views = simulator.getViews();
		this.setActionListeners();
	}
	
	private void setActionListeners()
	{
		views.remove(1);
		for(View v : views)
		{
			btnStep1 = v.getButtonStepOne();
			btnStep1.addActionListener(this);
			
			btnStep100 = v.getButtonStep100();
			btnStep100.addActionListener(this);
			
			btnPause = v.getButtonPause();
			btnPause.addActionListener(this);
			
			btnDiag = v.getButtonDiag();
			btnDiag.addActionListener(this);
			
		}
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == btnStep1) 
		{
			simulator.simulateOneStep();
		}
		
		if (e.getSource() == btnStep100)
		{
			simulator.simulate(100);
		}
		
		if (e.getSource() == btnPause)
		{
			System.out.println("Pauzeer");
		}
		
		if (e.getSource() == btnDiag)
		{
			System.out.println("IK BEN EEN DIAGRAM YO");
		}
		/*if (e.getSource() == btnCloseDiag)
		{
			System.out.println("DIAGRAM! DISENGAGE");
		}
		*/
	}
}
