 

import java.awt.Color;
import java.util.Map;

import javax.swing.*;


@SuppressWarnings("serial")
public class View extends JFrame {
  
    private Model model;
    private JPanel contentPane;
    
	public View(Model model) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.model=model;
		System.out.println(model.toString());
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
