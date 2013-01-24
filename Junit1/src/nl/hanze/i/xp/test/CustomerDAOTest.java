package nl.hanze.i.xp.test;


import org.junit.*;
import static org.junit.Assert.*; 
import nl.hanze.i.xp.dao.*;
import nl.hanze.i.xp.domain.*;
import nl.hanze.i.xp.exception.*;

import java.util.*;

public class CustomerDAOTest {
	private CustomerDAO custDAO;
	
	@Before
	public void setUp() throws Exception {
		custDAO=new CustomerDAOMock();
	}

	@Test
	public void testLoad()throws NotNegativeException {
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
		
		List<Customer> custs1=new ArrayList<Customer>();
		custs1.add(c1);
		custs1.add(c2);
		
		List<Customer> custs2=custDAO.getAllCustomers();
		assertEquals(custs1, custs2);
	}


	
}
