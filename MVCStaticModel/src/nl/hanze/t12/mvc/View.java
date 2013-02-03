package nl.hanze.t12.mvc;

import java.awt.Color;
import java.util.Map;

import javax.swing.*;

//import nl.hanze.t12.mvc.SimulatorView.FieldView;

@SuppressWarnings("serial")
public class View extends JFrame {
  
    private Model model;
    
	public View(Model model) {
		this.model=model;
		model.addView(this);
		//setSize(200, 200);
		//setVisible(true);
	}
	
	public Model getModel() {
		return model;
	}
	
	public void setModel(Model model) {
		this.model=model;
	}
	
	public void updateView() {
		repaint();
	}
	
	
}
