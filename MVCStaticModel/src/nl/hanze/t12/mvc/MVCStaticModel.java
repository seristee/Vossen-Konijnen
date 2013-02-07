package nl.hanze.t12.mvc;

import javax.swing.*;

import nl.hanze.t12.mvc.Controllers.Controller;
import nl.hanze.t12.mvc.Controllers.ControllerSimulatorView;
import nl.hanze.t12.mvc.Models.Model;
import nl.hanze.t12.mvc.Models.Simulator;

public class MVCStaticModel {
	private Model model;
	//private JFrame screen;
	//private View countview;
	//private View pieview;
	//private SimulatorView simulatorView;
	private Controller controller;
	private Simulator simulator;
	
	public MVCStaticModel() {

		//controller = new Controller(simulator); // Passes the Simulator model to a controller		
		
		simulator = new Simulator(); // <- extends model
		controller = new ControllerSimulatorView(simulator);
		simulator.runLongSimulation(); // Finally runs the simulator
	}
}
