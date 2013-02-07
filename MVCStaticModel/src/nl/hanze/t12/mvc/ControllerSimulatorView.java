package nl.hanze.t12.mvc;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JButton;


public class ControllerSimulatorView extends Controller 
{
	private Simulator model;
	private JButton btnStep1;
	private JButton btnStep100;
	private JButton btnPause;
	private ArrayList<View> views;
	
	public ControllerSimulatorView(Simulator m)
	{
		super(m);
		model = m;
		views = model.getViews();
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
			
		}
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == btnStep1) 
		{
			//System.out.println("1 stap verder");
			model.simulateOneStep();
		}
		
		if (e.getSource() == btnStep100)
		{
			//System.out.println("100 stappen verder");
			model.simulate(100);
		}
		
		if (e.getSource() == btnPause)
		{
			System.out.println("Pauzeer");
		}
		
	}
}
