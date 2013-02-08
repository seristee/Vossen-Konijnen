package nl.hanze.t12.mvc.Controllers;

import java.applet.AudioClip;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import nl.hanze.t12.mvc.Models.Simulator;
import nl.hanze.t12.mvc.Views.View;


public class ControllerSimulatorView extends Controller 
{
	private Simulator simulator;
	private JButton btnStep1;
	private JButton btnStep100;
	private JButton btnPause;
	private JButton btnDiag;

	private JButton btnSettingsOk;
	private JButton btnSettingsCancel;
	private JFrame frameSettings;
	private JMenuItem close;
	private JMenuItem settings;
	private JCheckBoxMenuItem soundItem;
//	private AudioClip sound;

	private JButton btnDiagClose;
	private JButton btnDiagPie;
	private JButton btnDiagBar;

	private JButton btnCloseDialog;
	private JDialog fout;
	
	private ArrayList<View> views;
	
	public ControllerSimulatorView(Simulator m)
	{
		super(m);
		simulator = m;
		views = simulator.getViews();
		setActionListeners();
		
	}
	
	private void setActionListeners()
	{
		views.remove(1);
		for(View v : views)
		{
			// sound = ...getSound(); 
			
			soundItem = v.getSound();
			
			soundItem.addActionListener(this);
			
			frameSettings = v.getSettingsFrame();
			
			btnStep1 = v.getButtonStepOne();
			btnStep1.addActionListener(this);
			
			btnStep100 = v.getButtonStep100();
			btnStep100.addActionListener(this);
			
			btnPause = v.getButtonPause();
			btnPause.addActionListener(this);
			
			btnDiag = v.getButtonDiag();
			btnDiag.addActionListener(this);

			close = v.getClose();
			close.addActionListener(this);
			
			btnCloseDialog = v.getDialogCloseBtn();
			btnCloseDialog.addActionListener(this);
			
			btnSettingsOk = v.getOkSettingsButton();
			btnSettingsOk.addActionListener(this);			
			
			btnSettingsCancel = v.getCancelSettingsButton();
			btnSettingsCancel.addActionListener(this);
			
			
			settings = v.getSettings();
			settings.addActionListener(this);
			
			btnDiagClose = v.getButtonDiagClose();
			btnDiagClose.addActionListener(this);
			
			btnDiagBar = v.getButtonDiagBar();
			btnDiagBar.addActionListener(this);
			
			btnDiagPie = v.getButtonDiagPie();
			btnDiagPie.addActionListener(this);
			}
	}
	
	public void actionPerformed(ActionEvent e)
	{
//		System.out.println(e.toString());
		
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
			//simulator.getPercentages((ArrayList) simulator.getAnimals()); // JA IK WEET OOK WEL DAT NIET HOORT HET IS 3 UUR SNACHTS
			simulator.reset();
		}
		
		if (e.getSource() == btnDiag)
		{
			for(View v : views)
			{
				v.popView().setVisible(false);
		    	int[] percentages = simulator.getPercentages((ArrayList) simulator.getAnimals()); // DONT JUDGE MEEEE
		    	v.popView().getPanel().setAantallen(percentages);
		    	v.popView().getPanel().repaint();
		    	v.popView().setVisible(true);
			}
		}
		
		if (e.getSource() == btnDiagClose)
		{
			for(View v : views)
				v.popView().setVisible(false);
		}
		if (e.getSource() == btnDiagPie)
		{
			for(View v : views)
			{
				v.popView().setVisible(false);
		    	int[] percentages = simulator.getPercentages((ArrayList) simulator.getAnimals()); // DONT JUDGE MEEEE
		    	v.popView().getPanel().setAantallen(percentages);
		    	v.popView().getPanel().setMode('p');
		    	v.popView().getPanel().repaint();
		    	v.popView().setVisible(true);
			}
		}
		if (e.getSource() == btnDiagBar)
		{
			for(View v : views)
			{
				v.popView().setVisible(false);
		    	int[] percentages = simulator.getPercentages((ArrayList) simulator.getAnimals()); // DONT JUDGE MEEEE
		    	v.popView().getPanel().setAantallen(percentages);
		    	v.popView().getPanel().setMode('b');
		    	v.popView().getPanel().repaint();
		    	v.popView().setVisible(true);
			}
		}

		if (e.getSource() == close)
		{
			System.exit(0);
		}
		
		if (e.getSource() == settings)
		{
			for(View v : views) 
			{
				v.createSettingsDialog();
				v.setSettingsVisability(true);
			}
		}
		if (e.getSource() == btnSettingsOk)
		{
			for (View v : views)
			{
				try {
				simulator.setMaxAgeFox(new Integer(v.getTxtMaxAgeFox().getText()));
				simulator.setMaxAgeHunter(new Integer(v.getTxtMaxAgeHunter().getText()));
				simulator.setMaxAgeKillerBunny(new Integer(v.getTxtMaxAgeKillerBunny().getText()));
				simulator.setMaxAgeRabbit(new Integer(v.getTxtMaxAgeRabbit().getText()));
				v.setSettingsVisability(false);
				} catch (NumberFormatException error) {
					fout = new JDialog(v);
					JLabel label = new JLabel("Please fill out rounded numbers.");
					JPanel foutContent = new JPanel();
					
					foutContent.setLayout(null);
					fout.setBounds(20,20, 250, 80);
					label.setBounds(25,0, 210, 30);
					
//					fout.setResizable(false);
					fout.setAlwaysOnTop(true);
					
					foutContent.add(label);
					foutContent.add(v.getDialogCloseBtn());
					fout.setContentPane(foutContent);
					fout.setVisible(true);
				}
			}
		}
		if (e.getSource() == btnCloseDialog)
		{
			fout.dispose();
		}
		if (e.getSource() == btnSettingsCancel)
		{
			for (View v : views)
			{
				v.setSettingsVisability(false);
			}
		}
		if (e.getSource() == soundItem)
			simulator.playSound(soundItem.getState());
	}
	
}
