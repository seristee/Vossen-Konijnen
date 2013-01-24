package nl.hanze.i.xp.domain;

import java.util.*;

public class Customer {
	private int CID;
	private String name;
	private List<Item> items;
	
	public Customer() {
		
	} 
	
	public int getCID() {
		return CID;
	}
	
	public void setCID(int cid) {
		CID = cid;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public List<Item> getItems() {
		return items;
	}
	
	public void setItem(Item item) {
		items.add(item);
	}
	
	public boolean equals(Object o) {
		if (!(o instanceof Customer)) {
			return false;
		} else {
			Customer c=(Customer) o;
			boolean eq=true;
			// check eerst items
			List<Item> items=c.getItems();
			eq=eq && this.items.equals(items);
			eq=eq && c.CID==CID && c.name.equals(name);
			return eq;
		}
	}
}


// items=new ArrayList<Item>();
