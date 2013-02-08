package nl.hanze.t12.mvc.Views;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;


import nl.hanze.t12.mvc.Logic.Field;
import nl.hanze.t12.mvc.Logic.FieldStats;
import nl.hanze.t12.mvc.Models.Simulator;


import java.util.LinkedHashMap;
import java.util.Map;

/**
 * A graphical view of the simulation grid.
 * A graphical view of the simulation grid.
 * The view displays a colored rectangle for each location 
 * representing its contents. It uses a default background color.
 * Colors for each type of species can be defined using the
 * setColor method.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 2011.07.31
 */
public class SimulatorView extends View
{
    private JLabel stepLabel, population;
    private FieldView fieldView;
    // A map for storing colors for participants in the simulation
    private Map<Class, Color> colors;
    // A statistics object computing and storing simulation information
    private FieldStats stats;
    
    private Simulator model;
  
    private JButton btnStep1;
	private JButton btnStep100;
    private JButton btnReset;
	private JButton btnDiag;
	private JMenuBar menuBarTop;
	private JMenuItem close;
	private JCheckBoxMenuItem sound;
	private JMenuItem settings;

    //private JPanel contentPane;

	private JButton btnDiagPie;
	private JButton btnDiagBar;
	private JButton btnDiagClose;

    private JLabel labeli;
    private ImageIcon imagei;
    private JFrame frameSettings;
    private JButton btnSettingsOk;
    private JButton btnSettingsCancel;
    
    private DiagramView popView;
    
    /**
     * Create a view of the given width and height.
     * @param height The simulation's height.
     * @param width  The simulation's width.
     * @param model The model of this simulation instance
     */
    public SimulatorView(int height, int width, Simulator m)
    {	
    	super(m);
    	model = m;
    	
        createSettingsDialog();
   
    	setResizable(false);
    	
    	// Creates the menuBarTop
    	menuBarTop = new JMenuBar();
    	
    	JMenu file = new JMenu("File");
    	JMenuItem close = new JMenuItem("Close");
    	file.add(close);
    	
    	JMenu options = new JMenu("Options");
    	
    	settings = new JMenuItem("Settings");
    	sound = new JCheckBoxMenuItem("Sound", true);
    	
    	options.add(sound);
    	options.add(settings);
    	
    	
    	menuBarTop.add(file);
    	menuBarTop.add(options);
    	
    	
    	// Sets the MenuBar
    	setJMenuBar(menuBarTop);
    	
    	this.popView = new DiagramView(this);
	
    	btnStep1 = new JButton("Step 1");
    	btnStep1.setBounds(0, 11, 89, 23);
    	
    	
    	btnStep100 = new JButton("Step 100");
    	btnStep100.setBounds(0, 45, 89, 23);
    	
    	btnReset = new JButton("Reset");
    	btnReset.setBounds(0, 59, 89, 23);
    	
    	btnDiag = new JButton("Populatie");
    	btnDiag.setBounds(0, 80, 89, 23);
    	
    	imagei = new ImageIcon(getClass().getResource("/grass.png"));
		labeli = new JLabel(imagei);
    	
    	stats = new FieldStats();
        colors = new LinkedHashMap<Class, Color>();

        setTitle("Fox and Rabbit Simulation");
        
        stepLabel = new JLabel(model.getSTEP_PREFIX(), JLabel.CENTER);
        population = new JLabel(model.getPOPULATION_PREFIX(), JLabel.CENTER);
        
        setLocation(100, 50);
        
        fieldView = new FieldView(m.getHeight(), m.getWidth(), m);
        JPanel subPanel = new JPanel();
        subPanel.setPreferredSize(new Dimension(100, this.HEIGHT));
        
        subPanel.add(btnStep1);
        subPanel.add(btnStep100);
        subPanel.add(btnReset);
        subPanel.add(btnDiag);
        add(labeli);
        
        Container contents = getContentPane();
        contents.add(stepLabel, BorderLayout.NORTH);
        contents.add(fieldView, BorderLayout.EAST);
        contents.add(population, BorderLayout.SOUTH);
        contents.add(subPanel, BorderLayout.WEST);
        
        setBackground(Color.GREEN); //speciaal voor Sander :3
        pack();
        setVisible(true);
        
    }
    
    /**
     * Define a color to be used for a given class of animal.
     * @param animalClass The animal's Class object.
     * @param color The color to be used for the given class.
     */
    public void setColor(Class animalClass, Color color)
    {
        colors.put(animalClass, color);
    }

    /**
     * @return The color to be used for a given class of animal.
     */
    private Color getColor(Class animalClass)
    {
        Color col = colors.get(animalClass);
        if(col == null) {
            // no color defined for this class
            //return UNKNOWN_COLOR;
        	return model.getColorUnknown();
        }
        else {
            return col;
        }
    }

    /**
     * Show the current status of the field.
     * @param step Which iteration step it is.
     * @param field The field whose status is to be displayed.
     */
    public void showStatus(int step, Field field)
    {
        if(!isVisible()) {
            setVisible(true);
        }
            
        stepLabel.setText(model.getSTEP_PREFIX() + step);
        
        stats.reset();
        
        fieldView.preparePaint();

        for(int row = 0; row < field.getDepth(); row++) {
            for(int col = 0; col < field.getWidth(); col++) {
                Object animal = field.getObjectAt(row, col);
                if(animal != null) {
                    stats.incrementCount(animal.getClass());
                    fieldView.drawMark(col, row, getColor(animal.getClass()));
                }
                else {
                	fieldView.drawMark(col, row, model.getColorEmpty());
                }
            }
        }
        stats.countFinished();

        population.setText(model.getPOPULATION_PREFIX() + stats.getPopulationDetails(field));
        fieldView.repaint();
    }

    /**
     * Determine whether the simulation should continue to run.
     * @return true If there is more than one species alive.
     */
    public boolean isViable(Field field)
    {
        return stats.isViable(field);
    }
    
    @Override
    public JButton getButtonStepOne()
    {
    	return btnStep1;
    }
    
    @Override
    public JButton getButtonStep100()
    {
    	return btnStep100;
    }
    @Override
    public JButton getButtonPause()
    {
    	return btnReset;
    }
    @Override
    public JButton getButtonDiag()
    {
    	return btnDiag;
    }
    @Override
    public JMenuItem getSound()
    {
    	return sound;
    }
    @Override
    public JMenuItem getSettings()
    {
    	return settings;
    }
    
    @Override
    public JButton getOkSettingsButton()
    {
    	return btnSettingsOk;
    }
    
    @Override
    public JButton getCancelSettingsButton()
    {
    	return btnSettingsCancel;
    }
    @Override
    public JFrame getSettingsFrame()
    {
    	return frameSettings;
    }
    
    public void setSettingsVisability()
    {
    	frameSettings.setVisible(true);
    }
    
    @Override
    public void createSettingsDialog()
    {
    	frameSettings = new JFrame("Settings");
    	//frameSettings.setResizable(false);
    	
    	JLabel lblMaxAgeFox = new JLabel("Maximum age fox :");
    	lblMaxAgeFox.setBounds(0, 0, 80, 30);
    	
    	JLabel lblMaxAgeKillerBunny = new JLabel("Maximum age killerbunny :");
    	lblMaxAgeKillerBunny.setBounds(0, 20, 80, 30);
    	
    	JLabel lblMaxAgeRabbit = new JLabel("Maximum age rabbit :");
    	lblMaxAgeRabbit.setBounds(0, 40, 80, 30);
    	
    	JLabel lblMaxAgeHunter = new JLabel("Maximum age hunter :");
    	lblMaxAgeHunter.setBounds(0, 60, 80, 30);
    	
    	JTextField txtMaxAgeFox = new JTextField(model.getMaxAgeFox()); // todo get Values
    	txtMaxAgeFox.setBounds(0, 0, 80, 30);
    	
    	JTextField txtMaxAgeKillerBunny = new JTextField(model.getMaxAgeKillerBunny());
    	txtMaxAgeKillerBunny.setBounds(0, 20, 80, 30);
    	
    	JTextField txtMaxAgeRabbit = new JTextField(model.getMaxAgeRabbit());
    	txtMaxAgeRabbit.setBounds(0, 40, 80, 30);
    	
    	JTextField txtMaxAgeHunter = new JTextField(model.getMaxAgeHunter());
    	txtMaxAgeHunter.setBounds(0, 60, 80, 30);
    	
    	btnSettingsOk = new JButton("OK");
    	btnSettingsOk.setBounds(40,100,60,20);
    	
    	btnSettingsCancel = new JButton("Cancel");
    	btnSettingsCancel.setBounds(110, 100, 60, 20);
    	
    	// 2 subpanels!
    	JPanel left = new JPanel();
    	JPanel right = new JPanel();
    	
    	left.setPreferredSize(new Dimension(180, 130));
    	
    	right.setPreferredSize(new Dimension(180, 130));
    	right.setLayout(null);
    	
    	left.add(lblMaxAgeFox);
    	left.add(lblMaxAgeKillerBunny);
    	left.add(lblMaxAgeRabbit);
    	left.add(lblMaxAgeHunter);
    	
    	right.add(txtMaxAgeFox);
    	right.add(txtMaxAgeKillerBunny);
    	right.add(txtMaxAgeRabbit);
    	right.add(txtMaxAgeHunter);
    	right.add(btnSettingsOk);
    	right.add(btnSettingsCancel);
    	
    	Container contents = frameSettings.getContentPane();
        contents.add(left, BorderLayout.WEST);
        contents.add(right, BorderLayout.EAST);
    	
    	frameSettings.pack();
    }
    

    public DiagramView popView()
    {
    	return popView;
    }
    
    public void popFrameSetVisible(boolean x)
    {
    	this.popView.setVisible(x);
    	System.out.println("x");
    }
    public void setDiagramToBar()
    {
    	System.out.println("Test Bar");
    }
    public void setDiagramToPie()
    {
    	System.out.println("Test Pie");
    }
    public JButton getButtonDiagClose(){ return popView.getClose(); }
    public JButton getButtonDiagBar() { return popView.getBarBtn(); }
    public JButton getButtonDiagPie() { return popView.getPieBtn(); }

    /**
     * Provide a graphical view of a rectangular field. This is 
     * a nested class (a class defined inside a class) which
     * defines a custom component for the user interface. This
     * component displays the field.
     * This is rather advanced GUI stuff - you can ignore this 
     * for your project if you like.
     */
}