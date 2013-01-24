package nl.hanze.i.xp.dao;

import java.util.*;
import nl.hanze.i.xp.domain.*;
import nl.hanze.i.xp.exception.*;

public class CustomerDAOMock implements CustomerDAO {

	@Override
	public List<Customer> getAllCustomers() throws NotNegativeException  {
		Customer c1=new Customer();
		c1.setCID(1);
		c1.setName("chris");
		c1.setItem(new Item(1, "pen", 2));
		c1.setItem(new Item(2, "laptop", 3));
		Customer c2=new Customer();
		c2.setCID(2);
		c2.setName("dick");
		c2.setItem(new Item(3, "pen", 1));
		c2.setItem(new Item(4, "laptop", 0));
		
		List<Customer> custs=new ArrayList<Customer>();
		
		custs.add(c1);
		custs.add(c2);
		
		return custs;
		
	}

}
