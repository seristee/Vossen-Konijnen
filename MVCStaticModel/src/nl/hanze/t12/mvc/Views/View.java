package nl.hanze.t12.mvc.Views;

import java.awt.Color;
import java.util.Map;

import javax.swing.*;

import nl.hanze.t12.mvc.Models.Model;



@SuppressWarnings("serial")
public class View extends JFrame {
  
    private Model model;
    private JPanel contentPane;
	private JButton btnStep1;
	private JButton btnStep100;
	private JButton btnPause;
	private JButton btnDiag;
	private JMenuItem close;
	private JMenuItem settings;
	private JCheckBoxMenuItem soundItem;
    
	public View(Model model) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.model=model;
//		System.out.println(model.toString());
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
	
    public JButton getButtonStepOne()
    {
    	return btnStep1;
    }
    
    public JButton getButtonStep100()
    {
    	return btnStep100;
    }
    
    public JButton getButtonPause()
    {
    	return btnPause;
    }
    
    public JButton getButtonDiag()
    {
    	return btnDiag;
    }

    public JMenuItem getClose()
    {
    	return close;
    }
    
    public JMenuItem getSound()
    {
    	return soundItem;
    }
    
    public JMenuItem getSettings()
    {
    	return settings;
    }
 
}
