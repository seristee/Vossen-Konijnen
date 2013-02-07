package nl.hanze.t12.mvc;

import java.util.List;
import java.util.Iterator;
import java.util.Random;

/**
 * A simple model of a hunter.
 * hunteres age, move, eat rabbits, and die.
 * 
 * @author David J. Barnes and Michael K��lling
 * @version 2011.07.31
 */
public class Hunter extends Animal
{
    // Characteristics shared by all hunters (class variables).
    
    // The age at which a hunter can start to breed.
    private static final int BREEDING_AGE = 15;
    // The age to which a hunter can live.
    private static final int MAX_AGE = 600;
    // The likelihood of a hunter breeding.
    private static final double BREEDING_PROBABILITY = 0;
    // The maximum number of births.
    private static final int MAX_LITTER_SIZE = 0;
    // The food value of a single rabbit. In effect, this is the
    // number of steps a hunter can go before it has to eat again.
    private static final int RABBIT_FOOD_VALUE = 9;
    // A shared random number generator to control breeding.
    private static final Random rand = Randomizer.getRandom();
    
    private final String naam = "Hunter";
    
    // Individual characteristics (instance fields).
    // The hunter's age.
    private int age;
    // The hunter's food level, which is increased by eating rabbits.
    private int killLevel;

    /**
     * Create a hunter. A hunter can be created as a new born (age zero
     * and not hungry) or with a random age and food level.
     * 
     * @param randomAge If true, the hunter will have random age and hunger level.
     * @param field The field currently occupied.
     * @param location The location within the field.
     */
    public Hunter(boolean randomAge, Field field, Location location)
    {
        super(field, location);
        if(randomAge) {
            age = rand.nextInt(MAX_AGE);
        }
        else {
            age = 0;
        }
        killLevel = 500;
    }
    
    /**
     * This is what the hunter does most of the time: it hunts for
     * rabbits. In the process, it might breed, die of hunger,
     * or die of old age.
     * @param field The field currently occupied.
     * @param newhunteres A list to return newly born hunteres.
     */
    public void act(List<Animal> newhunteres)
    {
        incrementAge();
        incrementHunger();
        if(isAlive()) {
            giveBirth(newhunteres);            
            // Move towards a source of food if found.
            Location newLocation = findFood();
            if(newLocation == null) { 
                // No food found - try to move to a free location.
                newLocation = getField().freeAdjacentLocation(getLocation());
            }
            // See if it was possible to move.
            if(newLocation != null) {
                setLocation(newLocation);
            }
            else {
                // Overcrowding.
                setDead();
            }
        }
    }

    /**
     * Increase the age. This could result in the hunter's death.
     */
    private void incrementAge()
    {
        age++;
        if(age > MAX_AGE) {
            setDead();
        }
    }
    
    /**
     * Make this hunter more hungry for kills. This could result in the hunter's departure.
     */
    private void incrementHunger()
    {
        killLevel--;
        if(killLevel <= 0) {
            setDead();
        }
    }
    
    /**
     * Look for rabbits adjacent to the current location.
     * Only the first live rabbit is eaten.
     * @return Where food was found, or null if it wasn't.
     */
    private Location findFood()
    {
        Field field = getField();
        List<Location> adjacent = field.adjacentLocations(getLocation());
        Iterator<Location> it = adjacent.iterator();
        while(it.hasNext()) {
            Location where = it.next();
            Object animal = field.getObjectAt(where);
            if(animal instanceof Fox) {
                Fox fox = (Fox) animal;
                if(fox.isAlive()) { 
                    fox.setDead();
                    killLevel = 500;
                    // Remove the dead rabbit from the field.
                    return where;
                }
            }
            else if(animal instanceof Rabbit) {
            	Rabbit rabbit = (Rabbit) animal;
            	if(rabbit.isAlive()) {
            		rabbit.setDead();
            		killLevel = 500;
            		
            		return where;
            	}
            }
        }
        return null;
    }
    
    /**
     * Check whether or not this hunter is to give birth at this step.
     * New births will be made into free adjacent locations.
     * @param newhunteres A list to return newly born hunteres.
     */
    private void giveBirth(List<Animal> newhunteres)
    {
        // New hunteres are born into adjacent locations.
        // Get a list of adjacent free locations.
        Field field = getField();
        List<Location> free = field.getFreeAdjacentLocations(getLocation());
        int births = breed();
        for(int b = 0; b < births && free.size() > 0; b++) {
            Location loc = free.remove(0);
            Hunter young = new Hunter(false, field, loc);
            newhunteres.add(young);
        }
    }
        
    /**
     * Generate a number representing the number of births,
     * if it can breed.
     * @return The number of births (may be zero).
     */
    private int breed()
    {
        int births = 0;
        if(canBreed() && rand.nextDouble() <= BREEDING_PROBABILITY) {
            births = rand.nextInt(MAX_LITTER_SIZE) + 1;
        }
        return births;
    }

    /**
     * A hunter can breed if it has reached the breeding age.
     */
    private boolean canBreed()
    {
        return false;
    }
    public String getName()
    {
    	return naam;
    }
}
