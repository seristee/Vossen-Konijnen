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
	JButton btnDiagPie = new JButton();
	JButton btnDiagBar = new JButton();
	JButton btnDiagClose = new JButton();

    
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
    	return null;
    }
    
    public JButton getButtonStep100()
    {
    	return btnStep100;
    }
    
    public JButton getButtonPause()
    {
    	return null;
    }
    
    public JButton getButtonDiag()
    {
    	return null;
    }

    public JMenuItem getClose()
    {
    	return null;
    }
    
    public JMenuItem getSound() { return null; }
    
    public JMenuItem getSettings() { return null; }
    
    public void createSettingsDialog() 
    { 
    	
    }
    
    public JButton getOkSettingsButton()
    {
    	return null;
    }
    
    public JButton getCancelSettingsButton()
    {
    	return null;
    }
    
    public JFrame getSettingsFrame()
    {
    	return null;
    }
    
    public void setSettingsVisability()
    {
    
    }
    public JButton getButtonDiagClose(){ return null; }
    public JButton getButtonDiagBar() { return null; }
    public JButton getButtonDiagPie() { return null; }
    public DiagramView popView(){ return null; }
}
