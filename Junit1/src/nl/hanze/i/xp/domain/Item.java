package nl.hanze.i.xp.domain;

import nl.hanze.i.xp.exception.*;

public class Item {
	private int IID;
	private String description;
	private int quantity;
	
	public Item() {}
	
	public Item(int IID, String description, int quantity) throws NotNegativeException {
		setIID(IID);
		setDescription(description);
		setQuantity(quantity);
	}
	
	public int getIID() {
		return IID;
	}
	
	public void setIID(int iid) {
		IID = iid;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) throws NotNegativeException {
		if (quantity<0) throw new NotNegativeException();
		this.quantity = quantity;
	}
	
	public boolean equals(Object o) {
		if (!(o instanceof Item)) {
			return false;
		} else {
			Item i=(Item) o;
			return i.IID==IID && i.description.equals(description) && i.quantity==quantity; 
		}
	}
}
