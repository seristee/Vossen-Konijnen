 

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.util.LinkedHashMap;
import java.util.Map;

/**
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
  
    //private JPanel contentPane;
    private JButton btnStep1;
    private JButton btnStep100;
    private JButton btnPause;
    
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
    	
    	System.out.println(height);
    	System.out.println(width);
    	
    	//super.setSize(height, width);
    	//setResizable(false);
    	
    	//contentPane.setLayout(null);
    	
    	JButton btnStep1 = new JButton("Step 1");
//    	btnStep1.setLayout(null);
    	btnStep1.setBounds(0, 11, 89, 23);
    	
    	
    	JButton btnStep100 = new JButton("Step 100");
//    	btnStep100.setLayout(null);
    	btnStep100.setBounds(0, 45, 89, 23);
    	
    	
    	JButton btnPause = new JButton("Pause");
//    	btnPause.setLayout(null);
    	btnPause.setBounds(0, 59, 89, 23);
    	
    	
    	stats = new FieldStats();
        colors = new LinkedHashMap<Class, Color>();

        setTitle("Fox and Rabbit Simulation");
        
//        stepLabel = new JLabel(model.getSTEP_PREFIX(), JLabel.CENTER);
//        population = new JLabel(model.getPOPULATION_PREFIX(), JLabel.CENTER);
        stepLabel = new JLabel(model.getSTEP_PREFIX());
        stepLabel.setBounds((width/2)-30, 0, 60, 20);
        population = new JLabel(model.getPOPULATION_PREFIX());
        
        
        setLocation(100, 50);
        
        fieldView = new FieldView(height, width, m);

        Container contents = getContentPane();
        //contents.setLayout(null);
//        contents.add(btnStep1, BorderLayout.WEST);
//        contents.add(btnStep100, BorderLayout.WEST);
//        contents.add(btnPause, BorderLayout.WEST);
//        contents.add(stepLabel, BorderLayout.NORTH);
//        contents.add(fieldView, BorderLayout.CENTER);
//        contents.add(population, BorderLayout.SOUTH);
        contents.add(btnPause);
        contents.add(btnStep1);
        contents.add(btnStep100);
        contents.add(stepLabel);
        contents.add(fieldView);
        contents.add(population);
        pack();
//        contents.setBounds(0, 0, 800, 600);
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
                	//System.out.println(model.getColorEmpty().toString());
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
    
    public JButton getButtonStepOne()
    {
    	return btnStep1;
    	
    }
    
    /**
     * Provide a graphical view of a rectangular field. This is 
     * a nested class (a class defined inside a class) which
     * defines a custom component for the user interface. This
     * component displays the field.
     * This is rather advanced GUI stuff - you can ignore this 
     * for your project if you like.
     */
}