package nl.hanze.i.xp.test;

import org.junit.*;
import static org.junit.Assert.*;
import nl.hanze.i.xp.domain.*;
import nl.hanze.i.xp.dao.*;
import nl.hanze.i.xp.exception.*;
import nl.hanze.i.xp.logic.*;

public class CustomerBLTest {
	private CustomerBL custBL; 
	private Customer customer;
	
	@Before
	public void setUp() throws Exception {
		CustomerDAO custDAO=new CustomerDAOMock();
		custBL=new CustomerBL(custDAO);
		customer=new Customer();
		customer.setCID(1);
		customer.setName("chris");
		customer.setItem(new Item(1, "pen", 2));
		customer.setItem(new Item(2, "laptop", 3));
	}

	@Test
	public void testConstructor() {
		assertNotNull(custBL.getCustDAO());
	}
	
	@Test
	public void testMostItems() throws NotNegativeException {
		Customer c=custBL.getMostItems();
		assertEquals(c, customer);
	}
}
