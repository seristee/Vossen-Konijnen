package nl.hanze.t12.mvc;

import java.awt.event.ActionEvent;

public class ControllerSimulatorView extends Controller{

	public ControllerSimulatorView(Model model)
	{
		super(model);
	}
	
	
	public void actionPerformed(ActionEvent e)
	{
		if ((boolean) e.getSource()) 
		{
			System.out.println("1 stap verder");
		}
		
	}
}
