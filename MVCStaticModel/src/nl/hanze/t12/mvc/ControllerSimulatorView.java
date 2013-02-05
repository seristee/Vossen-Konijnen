package nl.hanze.t12.mvc;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JButton;

public class ControllerSimulatorView extends Controller 
{
	private Model model;
//	private JButton btnStep1;
//	private JButton btnStep100;
//	private JButton btnPause;
	private ArrayList<View> views;
	
	public ControllerSimulatorView(Model m)
	{
		super(m);
		model = m;
		views = model.getViews();
		System.out.println(views.toString());
		this.setActionListeners();
	}
	
	private void setActionListeners()
	{
		for(View v : views)
		{
			//JButton btnStep1 = v.getButtonStepOne();
			//System.out.println(btnStep1.toString());
			//btnStep1.addActionListener(this);
			//btnStep100 = v.getButtonStep100();
			//btnPause = v.getButtonPause();
			//v.getButtonStepOne().addActionListener(this);
		}
	}
	
	public void actionPerformed(ActionEvent e)
	{
//		if (e.getSource() == btnStep1) 
//		{
//			System.out.println("1 stap verder");
//		}
		
		
	}
}
