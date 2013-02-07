package nl.hanze.t12.mvc.Models;

import java.awt.Color;
import java.util.*;

import nl.hanze.t12.mvc.Views.View;


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
