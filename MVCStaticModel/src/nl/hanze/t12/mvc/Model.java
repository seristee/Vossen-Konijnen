package nl.hanze.t12.mvc;

import java.awt.Color;
import java.util.*;

public class Model {
	private int aantal;
	private List<View> views;
//	private Simulator simulator;
	
    // Colors used for empty locations.
    private static Color EMPTY_COLOR = Color.white; // check

    // Color used for objects that have no defined color.
    private static Color UNKNOWN_COLOR = Color.gray;
    
    private String STEP_PREFIX = "Step: ";
	private String POPULATION_PREFIX = "Population: ";
	
	
	public Model() {
		views=new ArrayList<View>();
	}
	
	public int getAantal() {
		return aantal;
	}
	
	public void setAantal(int aantal) {
		if (aantal>=0 && aantal <=360) {
			this.aantal=aantal;
			notifyViews();
		}
	}
	
	public void addView(View view) {
		views.add(view);
	}
	
	private void notifyViews() {
		for(View v: views) v.updateView();
	}
	
//	public Color getColorEmpty()
//	{
//		return EMPTY_COLOR;
//	}
//	
//	public void setColorEmpty(Color c)
//	{
//		EMPTY_COLOR = c;
//	}
//	
//	public Color getColorUnknown()
//	{
//		return UNKNOWN_COLOR;
//	}
//	
//	public void setColorUnknow(Color c)
//	{
//		UNKNOWN_COLOR = c;
//	}
//	
//	public Simulator getSimulator()
//	{
//		return simulator;
//	}
//	
//    public String getSTEP_PREFIX() 
//    {
//		return STEP_PREFIX;
//	}
//
//	public String getPOPULATION_PREFIX() 
//	{
//		return POPULATION_PREFIX;
//	}
//	
//	public void setSTEP_PREFIX(String s)
//	{
//		STEP_PREFIX = s;
//	}
//	
//	public void setPOPULATION_PREFIX(String s)
//	{
//		POPULATION_PREFIX = s;
//	}
}
