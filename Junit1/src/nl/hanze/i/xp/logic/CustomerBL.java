package nl.hanze.i.xp.logic;

import nl.hanze.i.xp.dao.*;
import nl.hanze.i.xp.domain.*;
import nl.hanze.i.xp.exception.*;

import java.util.*;

public class CustomerBL {
	private CustomerDAO custDAO;
	
	public CustomerBL(CustomerDAO custDAO) {
		this.custDAO=custDAO;
	}

	public CustomerDAO getCustDAO() {
		return custDAO;
	}

	public void setCustDAO(CustomerDAO custDAO) {
		this.custDAO = custDAO;
	}
	
	public Customer getMostItems() throws NotNegativeException {
		Customer temp=null;
		int max=Integer.MIN_VALUE;
		
		List<Customer> cs=custDAO.getAllCustomers();
		for(Customer c: cs) {
			List<Item> items=c.getItems();
			int num=0;
			for(Item item: items) { 
				num+=item.getQuantity();
			}
			if (num>max) { temp=c; }
		}
		
		return temp;
	}
}


// max=num;
