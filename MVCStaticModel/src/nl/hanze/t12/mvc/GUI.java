import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;

import SimulatorView.FieldView;


public class GUI extends JFrame {

	private JPanel contentPane;
	private FieldView fieldview;
	private JLabel stepLabel, population;
	
	private final String STEP_PREFIX = "Step: ";
    private final String POPULATION_PREFIX = "Population: ";
	
	// Colors used for empty locations.
    private static final Color EMPTY_COLOR = Color.white;

    // Color used for objects that have no defined color.
    private static final Color UNKNOWN_COLOR = Color.gray;
    
    // A map for storing colors for participants in the simulation
    private Map<Class, Color> colors;
    // A statistics object computing and storing simulation information
    private FieldStats stats;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					GUI frame = new GUI(); //als ik hier waarden invul krijg ik op regel 43 error
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public GUI(int height, int width) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 689, 549);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		stats = new FieldStats();
        colors = new LinkedHashMap<Class, Color>();

        setTitle("Fox and Rabbit Simulation");
        stepLabel = new JLabel(STEP_PREFIX);
        population = new JLabel(POPULATION_PREFIX);
		
		JButton btnStep = new JButton("step 1");
		btnStep.setBounds(10, 11, 89, 23);
		contentPane.add(btnStep);
		
		JButton btnStep_1 = new JButton("step 100");
		btnStep_1.setBounds(10, 45, 89, 23);
		contentPane.add(btnStep_1);
		
		fieldview = new FieldView(height, width);
		fieldview.setBounds(100, 100, 400, 400);
		contentPane.add(fieldview);
		
		stepLabel.setBounds(272, 476, 185, 23);
		contentPane.add(stepLabel);
		
		population.setBounds(272, 11, 214, 23);
		contentPane.add(population);
		
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
            return UNKNOWN_COLOR;
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
            
        stepLabel.setText(STEP_PREFIX + step);
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
                    fieldView.drawMark(col, row, EMPTY_COLOR);
                }
            }
        }
        stats.countFinished();

        population.setText(POPULATION_PREFIX + stats.getPopulationDetails(field));
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
    
    /**
     * Provide a graphical view of a rectangular field. This is 
     * a nested class (a class defined inside a class) which
     * defines a custom component for the user interface. This
     * component displays the field.
     * This is rather advanced GUI stuff - you can ignore this 
     * for your project if you like.
     */
//    private class FieldView extends JPanel
//    {
//        private final int GRID_VIEW_SCALING_FACTOR = 6;
//
//        private int gridWidth, gridHeight;
//        private int xScale, yScale;
//        Dimension size;
//        private Graphics g;
//        private Image fieldImage;
//
//        /**
//         * Create a new FieldView component.
//         */
//        public FieldView(int height, int width)
//        {
//            gridHeight = height;
//            gridWidth = width;
//            size = new Dimension(0, 0);
//        }
//
//        /**
//         * Tell the GUI manager how big we would like to be.
//         */
//        public Dimension getPreferredSize()
//        {
//            return new Dimension(gridWidth * GRID_VIEW_SCALING_FACTOR,
//                                 gridHeight * GRID_VIEW_SCALING_FACTOR);
//        }
//
//        /**
//         * Prepare for a new round of painting. Since the component
//         * may be resized, compute the scaling factor again.
//         */
//        public void preparePaint()
//        {
//            if(! size.equals(getSize())) {  // if the size has changed...
//                size = getSize();
//                fieldImage = fieldView.createImage(size.width, size.height);
//                g = fieldImage.getGraphics();
//
//                xScale = size.width / gridWidth;
//                if(xScale < 1) {
//                    xScale = GRID_VIEW_SCALING_FACTOR;
//                }
//                yScale = size.height / gridHeight;
//                if(yScale < 1) {
//                    yScale = GRID_VIEW_SCALING_FACTOR;
//                }
//            }
//        }
//        
//        /**
//         * Paint on grid location on this field in a given color.
//         */
//        public void drawMark(int x, int y, Color color)
//        {
//            g.setColor(color);
//            g.fillRect(x * xScale, y * yScale, xScale-1, yScale-1);
//        }
//
//        /**
//         * The field view component needs to be redisplayed. Copy the
//         * internal image to screen.
//         */
//        public void paintComponent(Graphics g)
//        {
//            if(fieldImage != null) {
//                Dimension currentSize = getSize();
//                if(size.equals(currentSize)) {
//                    g.drawImage(fieldImage, 0, 0, null);
//                }
//                else {
//                    // Rescale the previous image.
//                    g.drawImage(fieldImage, 0, 0, currentSize.width, currentSize.height, null);
//                }
//            }
//        }
//    }
//}
//
