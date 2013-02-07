package nl.hanze.t12.mvc.Logic;

import java.util.Random;

/**
 * Provide control over the randomization of the simulation.
 * 
 * @author David J. Barnes and Michael Keelling
 * @version 2011.07.31
 */
public class Randomizer
{
//	The default seed for control of randomization.
//	private static Random randomGenerator = new Random();
//	private static int randInt = randomGenerator.nextInt();
//	private static final int SEED = randInt;
//  private static int SEED = 1111;
//	//private static final int SEED = rand.setSeed(rand.nextInt(9999));
	private static Random randomGenerator = new Random();
	private static int randInt = randomGenerator.nextInt();
	private static final int SEED = randInt;
    private static Random rand = new Random(SEED);
    private static final boolean useShared = true;

    /**
     * Constructor for objects of class Randomizer
     */
    public Randomizer()
    { 
    }

    /**
     * Provide a random generator.
     * @return A random object.
     */
    public static Random getRandom()
    {
        if(useShared) {
            return rand;
        }
        else {
            return new Random();
        }
    }
    
    /**
     * Reset the randomization.
     * This will have no effect if randomization is not through
     * a shared Random generator.
     */
    public static void reset()
    {
        if(useShared) {
            rand.setSeed(SEED);
        }
    }
}
