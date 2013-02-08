package nl.hanze.t12.mvc.Controllers;

import java.applet.AudioClip;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
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
	private AudioClip sound;
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
			
			
			btnStep1 = v.getButtonStepOne();
			btnStep1.addActionListener(this);
			
			btnStep100 = v.getButtonStep100();
			btnStep100.addActionListener(this);
			
			btnPause = v.getButtonPause();
			btnPause.addActionListener(this);
			
			btnDiag = v.getButtonDiag();
			btnDiag.addActionListener(this);
			
			try {
				close = v.getClose();
				close.addActionListener(this);
			} catch (NullPointerException e) {
				
			}
			
			btnSettingsOk = v.getOkSettingsButton();
			//System.out.println(btnSettingsOk.toString());
			btnSettingsOk.addActionListener(this);

			
			
			btnSettingsCancel = v.getOkSettingsButton();
			btnSettingsCancel.addActionListener(this);
			
			
			settings = v.getSettings();
			settings.addActionListener(this);
			
				//soundItem = v.getSound();
				//soundItem.addActionListener(this);
			}
	}
	
	public void actionPerformed(ActionEvent e)
	{
		System.out.println(e.toString());
		
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
			System.out.println("Pauzeer");
		}
		
		if (e.getSource() == btnDiag)
		{
			System.out.println("IK BEN EEN DIAGRAM YO");
		}
		if (e.getSource() == close)
		{
			System.out.println("shutting down...");
		}
		if (e.getSource() == settings)
		{
			for(View v : views) 
			{
				v.createSettingsDialog();
				v.setSettingsVisability();
			}
		}
		if (e.getSource() == btnSettingsOk)
		{
			System.out.println("Er is op OK gedrukt!");
		}
		
		if (e.getSource() == btnSettingsCancel)
		{
			System.out.println("Er is op cancel gedrukt!");
		}
		
//		if (e.getSource == soundItem)
//		{
//			if (soundItem.getState() == true) {
//				sound.play();
//			} else {
//				sound.stop();
//			}
//		}
	}
}
