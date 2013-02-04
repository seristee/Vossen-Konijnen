package nl.hanze.t12.mvc;

import javax.swing.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class Controller extends JPanel implements ActionListener {
	private Model model;
	private JButton mineen;
	private JButton pluseen;
	private JButton btnStep1;
	
	public Controller(Model model) {
		this.model=model;
		System.out.println(model.toString());
		
		setSize(450, 50);
		mineen=new JButton("-1");
		mineen.addActionListener(this);
		pluseen=new JButton("+1");
		pluseen.addActionListener(this);
		
		btnStep1 = this.model.getButtonStepOne();
		btnStep1.addActionListener(this);
		
		this.setLayout(null);
		add(mineen);
		add(pluseen);
		mineen.setBounds(160, 10, 50, 30);
		pluseen.setBounds(229, 10, 50, 30);
		setVisible(true);
		
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==mineen) {
			model.setAantal(model.getAantal()-1);
		}
		
		if (e.getSource()==pluseen) {
			model.setAantal(model.getAantal()+1);
		}
		
		if (e.getSource()==btnStep1){
			System.out.println("Dit werkt");
		}
	}
	
	
}
