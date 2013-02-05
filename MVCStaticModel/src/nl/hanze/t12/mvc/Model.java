package nl.hanze.t12.mvc;

import java.awt.Color;
import java.util.*;

public class Model {
	private int aantal;
	private ArrayList<View> views;

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
	
	public ArrayList<View> getViews() 
	{
		return views;
	}
	private void notifyViews() {
		for(View v: views) v.updateView();
	}
}
