package nl.hanze.t12.mvc;

import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.Color;


/**
 * A simple predator-prey simulator, based on a rectangular field
 * containing rabbits and foxes.
 * 
 * @author David J. Barnes and Michael K��lling
 * @version 2011.07.31
 */
public class Simulator extends Model
{
    // Colors used for empty locations.
    private static Color EMPTY_COLOR = Color.white; // check

    // Color used for objects that have no defined color.
    private static Color UNKNOWN_COLOR = Color.gray;
    
    private String STEP_PREFIX = "Step: ";
	private String POPULATION_PREFIX = "Population: ";
	
    // Constants representing configuration information for the simulation.
    // The default width for the grid.
    private static final int DEFAULT_WIDTH = 100;
    // The default depth of the grid
    private static final int DEFAULT_DEPTH = 100;
    // The probability that a fox will be created in any given grid position.
    private static final double FOX_CREATION_PROBABILITY = 0.02;
    // The probability that a rabbit will be created in any given grid position.
    private static final double RABBIT_CREATION_PROBABILITY = 0.045;
    // The probability that a killerbunny will be created in any given grid position.
    private static final double KILLERBUNNY_CREATION_PROBABILITY = 0.005;
    // The probability that a hunter will be created in any given grid position.    
    private static final double HUNTER_CREATION_PROBABILITY = 0.01;
    
    // List of animals in the field.
    private List<Animal> animals;
    // The current state of the field.
    private Field field;
    // The current step of the simulation.
    private int step;
    // A graphical view of the simulation.
    private SimulatorView view;
    
    /**
     * Construct a simulation field with default size.
     */
    public Simulator()
    {
    	this(DEFAULT_DEPTH, DEFAULT_WIDTH);
    }
    
    /**
     * Create a simulation field with the given size.
     * @param depth Depth of the field. Must be greater than zero.
     * @param width Width of the field. Must be greater than zero.
     */
    public Simulator(int depth, int width)
    {
        if(width <= 0 || depth <= 0) {
            System.out.println("The dimensions must be greater than zero.");
            System.out.println("Using default values.");
            depth = DEFAULT_DEPTH;
            width = DEFAULT_WIDTH;
        }
        
        animals = new ArrayList<Animal>();
        field = new Field(depth, width);

        // Create a view of the state of each location in the field.
        view = new SimulatorView(depth, width, this);
        super.addView(view);
      
        // bruin is(156, 93, 82);
        //Color konijn = new Color( 139, 69, 19 );
        //Color vos    = new Color( 255, 69, 0 );
        
        /** Color parameters
         *  @param red
         *  @param green 
         *  @param blue
         *  Color(red, green, blue) 
         *  values ranges between 0 and 255 (Just like HTML / CSS)
         *  
         */
        
        Color konijn = new Color(0,0,255); 
        Color vos = new Color(0,255,0);
        Color killerBunny = new Color(255,0,0);
       // Color hunter = new Color(112,193,112);
        Color hunter = new Color(255,0,255);
        
        view.setColor(Rabbit.class, konijn);
        view.setColor(Fox.class, vos);
        view.setColor(KillerBunny.class, killerBunny);
        view.setColor(Hunter.class, hunter);
        
        // Setup a valid starting point.
        reset();
        makeDiagramController();
    }
    
    public void makeDiagramController(){
    	DiagramController x = new DiagramController(this);
    	x.getPercentages();
    }
    /**
     * Run the simulation from its current state for a reasonably long period,
     * (100 steps).
     */
    public void runLongSimulation()
    {
        simulate(100);
    }
    
    /**
     * Run the simulation from its current state for the given number of steps.
     * Stop before the given number of steps if it ceases to be viable.
     * @param numSteps The number of steps to run for.
     */
    public void simulate(int numSteps)
    {
    	if(numSteps == 0)
    	{
	    	while(true)
	    	{
	    		simulateOneStep();
	    	}
    	}
    	else
    		for(int step = 1; step <= numSteps && view.isViable(field); step++) {
    			simulateOneStep();
    		}
    }
    
    /**
     * Run the simulation from its current state for a single step.
     * Iterate over the whole field updating the state of each
     * fox and rabbit.
     */
    public void simulateOneStep()
    {
        step++;

        //  Provide space for newborn animals.
        List<Animal> newAnimals = new ArrayList<Animal>();        
        // Let all rabbits act.
        for(Iterator<Animal> it = animals.iterator(); it.hasNext(); ) {
            Animal animal = it.next();
            animal.act(newAnimals);
            if(! animal.isAlive()) {
                it.remove();
            }
        }
               
        // Add the newly born foxes and rabbits (and killerbunnies) to the main lists.
        animals.addAll(newAnimals);

        view.showStatus(step, field);
        
    }
        
    /**
     * Reset the simulation to a starting position.
     */
    public void reset()
    {
        step = 0;
        animals.clear();
        populate();
        
        // Show the starting state in the view.
        view.showStatus(step, field);
    }
    
    /**
     * Randomly populate the field with foxes and rabbits.
     */
    private void populate()
    {
        Random rand = Randomizer.getRandom();
        field.clear();
        for(int row = 0; row < field.getDepth(); row++) {
            for(int col = 0; col < field.getWidth(); col++) {
                if(rand.nextDouble() <= FOX_CREATION_PROBABILITY) {
                    Location location = new Location(row, col);
                    Fox fox = new Fox(true, field, location);
                    animals.add(fox);
                }
                else if(rand.nextDouble() <= RABBIT_CREATION_PROBABILITY) {
                    Location location = new Location(row, col);
                    Rabbit rabbit = new Rabbit(true, field, location);
                    animals.add(rabbit);
                }
                else if(rand.nextDouble() <= KILLERBUNNY_CREATION_PROBABILITY) {
                    Location location = new Location(row, col);
                    KillerBunny killerBunny = new KillerBunny(true, field, location);
                    animals.add(killerBunny);
                }
//                else if(rand.nextDouble() <= HUNTER_CREATION_PROBABILITY) {
//                    Location location = new Location(row, col);
//                    Hunter hunter = new Hunter(true, field, location);
//                    animals.add(hunter);
//                }
                //else  leave the location empty.
            }
        }
    }

	public static Color getColorEmpty() {
		return EMPTY_COLOR;
	}

	public static void setColorEmpty(Color c) {
		EMPTY_COLOR = c;
	}

	public static Color getColorUnknown() {
		return UNKNOWN_COLOR;
	}

	public static void setColorUnknow(Color c) {
		UNKNOWN_COLOR = c;
	}

	public String getSTEP_PREFIX() {
		return STEP_PREFIX;
	}

	public void setSTEP_PREFIX(String s) {
		STEP_PREFIX = s;
	}

	public String getPOPULATION_PREFIX() {
		return POPULATION_PREFIX;
	}

	public void setPOPULATION_PREFIX(String s) {
		POPULATION_PREFIX = s;
	}
	public int getHeight()
	{
		return DEFAULT_DEPTH;
	}
	public int getWidth()
	{
		return DEFAULT_WIDTH;
	}
	public List<Animal> getAnimals()
	{
		return this.animals;
	}
	
}
