package nl.hanze.t12.mvc.Data;

import java.util.List;
import java.util.Iterator;
import java.util.Random;

import nl.hanze.t12.mvc.Logic.Field;
import nl.hanze.t12.mvc.Logic.Location;
import nl.hanze.t12.mvc.Logic.Randomizer;

public class KillerBunny extends Animal
{
	
    // Characteristics shared by all foxes (class variables).
    
    // The age at which a killerbunny can start to breed.
    private static final int BREEDING_AGE = 20;
    // The age to which a killerbunny can live.
    private static int MAX_AGE = 25;
    // The likelihood of a killerbunny breeding.
    private static final double BREEDING_PROBABILITY = 0.08;
    // The maximum number of births.
    private static final int MAX_LITTER_SIZE = 1;
    // The food value of a single rabbit. In effect, this is the
    // number of steps a killerbunny can go before it has to eat again.
    private static final int RABBIT_FOOD_VALUE = 15;
    private static final int FOX_FOOD_VALUE = 15;
    private static final int HUNTER_FOOD_VALUE = 1000;
    // A shared random number generator to control breeding.
    private static final Random rand = Randomizer.getRandom();
    private final String name = "KillerBunny";
    
    // Individual characteristics (instance fields).
    // The age of the killerbunny
    private int age;
    // The killerbunny's food level, which is increased by eating rabbits.
    private int foodLevel;

    /**
     * Create a killerbunny. A killerbunny can be created as a new born (age zero
     * and not hungry) or with a random age and food level.
     * 
     * @param randomAge If true, the killbunny will have random age and hunger level.
     * @param field The field currently occupied.
     * @param location The location within the field.
     */
    public KillerBunny(boolean randomAge, Field field, Location location)
    {
        super(field, location);
        if(randomAge) {
            age = rand.nextInt(MAX_AGE);
            //foodLevel = rand.nextInt(RABBIT_FOOD_VALUE);
            foodLevel = rand.nextInt(HUNTER_FOOD_VALUE);
        } 
        else {
            age = 0;
            foodLevel = RABBIT_FOOD_VALUE;
        }
    }
    
    /**
     * This is what the killerbunny does most of the time: it hunts for
     * food. In the process, it might breed, die of hunger,
     * or die of old age.
     * @param field The field currently occupied.
     * @param newKillerBunnies A list to return newly born killerbunnies.
     */
    public void act(List<Animal> newKillerBunnies)
    {
        incrementAge();
        incrementHunger();
        if(isAlive()) {
            giveBirth(newKillerBunnies);            
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
     * Increase the age. This could result in the killerbunnies death.
     */
    private void incrementAge()
    {
        age++;
        if(age > MAX_AGE) {
            setDead();
        }
    }
    
    /**
     * Make this killerbunny more hungry. This could result in the killerbunnies death.
     */
    private void incrementHunger()
    {
        foodLevel--;
        if(foodLevel <= 0) {
            setDead();
        }
    }
    
    /**
     * Look for food adjacent to the current location.
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
            if(animal instanceof Rabbit) {
                Rabbit rabbit = (Rabbit) animal;
                if(rabbit.isAlive()) { 
                    rabbit.setDead();
                    foodLevel += RABBIT_FOOD_VALUE;
                    // Remove the dead rabbit from the field.
                    return where;
                }    
            } else if(animal instanceof Fox) {
            	Fox fox = (Fox) animal;
            		if(fox.isAlive()) {
            			fox.setDead();
            			foodLevel += FOX_FOOD_VALUE;
            			return where;
            		}

//            if(animal instanceof Rabbit) {
//                Rabbit rabbit = (Rabbit) animal;
//                if(rabbit.isAlive()) { 
//                    rabbit.setDead();
//                    foodLevel += RABBIT_FOOD_VALUE;
//                    // Remove the dead rabbit from the field.
//                    return where;
//                }    
//            } else if(animal instanceof Fox) {
//            	Fox fox = (Fox) animal;
//            		if(fox.isAlive()) {
//            			fox.setDead();
//            			foodLevel += FOX_FOOD_VALUE;
//            			return where;
//            		}

//            } else if (animal instanceof Hunter) {
//            	Hunter hunter = (fox) animal;
//				if(hunter.isAlive()) {
//            		hunter.setDead();
//            		foodLevel += HUNTER_FOOD_VALUE;
//            		return where;
//            }

        }

//            }
        }
        return null;
    }

//            }
    /**
     * Check whether or not this killerbunny is to give birth at this step.
     * New births will be made into free adjacent locations.
     * @param newKillerBunnies A list to return newly born killerbunnies.
     */
    private void giveBirth(List<Animal> newKillerBunnies)
    {
        // New killerbunnies are born into adjacent locations.
        // Get a list of adjacent free locations.
        Field field = getField();
        List<Location> free = field.getFreeAdjacentLocations(getLocation());
        int births = breed();
        for(int b = 0; b < births && free.size() > 0; b++) {
            Location loc = free.remove(0);
            KillerBunny young = new KillerBunny(false, field, loc);
            newKillerBunnies.add(young);
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
     * A killerbunny can breed if it has reached the breeding age.
     */
    private boolean canBreed()
    {
        return age >= BREEDING_AGE;
    }
    
    @Override
    public String getName()
    {
    	return name;
    
    }
    
    public static int getMaxAge() 
    {
    	return MAX_AGE;
    }
    
    public static void setMaxAge(int i)
    {
    	MAX_AGE = i;
    }
}