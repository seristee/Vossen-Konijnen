package nl.hanze.t12.mvc.Controllers;

import java.util.ArrayList;
import java.util.HashMap;

import nl.hanze.t12.mvc.Data.Animal;
import nl.hanze.t12.mvc.Models.Simulator;

public class DiagramController {
	private Simulator sim;
	
	public DiagramController(Simulator sim)
	{
		this.sim  = sim;
	}
	public HashMap<String, Integer> getPercentages(){
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		ArrayList<Animal> animal = (ArrayList) sim.getAnimals();
		for(int i = 0; i < animal.size(); i++)
		{
			map.put(animal.get(i).getName(), new Integer(10));
		}
		System.out.println("DING " + map.get("Hunter"));
		return map;
	}
}
