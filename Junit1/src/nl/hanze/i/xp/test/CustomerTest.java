package nl.hanze.i.xp.test;

import nl.hanze.i.xp.domain.*;
import nl.hanze.i.xp.exception.*;
import org.junit.*;
import static org.junit.Assert.*;

public class CustomerTest {
	private Customer customer;
	
	@Before
	public void setUp() throws Exception {
		customer=new Customer();
		customer.setCID(1);
		customer.setName("Chris");
		customer.setItem(new Item(1, "pen", 2));
		customer.setItem(new Item(2, "laptop", 1));
		customer.setItem(new Item(3, "muis", 1));
	}

	@Test
	public void testItemCount() {
		assertEquals(customer.getItems().size(), 3);
	}
	
	@Test
	public void testItemsList() throws NotNegativeException {
		Item[] items=customer.getItems().toArray(new Item[0]);
		assertArrayEquals(new Item[] {new Item(1, "pen", 2), new Item(2, "laptop", 1) , new Item(3, "muis", 1)}, items);
	}
	
	@Test 
	public void testItemsNotNull() {
		assertNotNull(customer.getItems());
	}
	
	@Test
	public void equalityTest() throws NotNegativeException {
		Customer c=new Customer();
		c.setCID(1);
		c.setName("Chris");
		c.setItem(new Item(1, "pen", 2));
		c.setItem(new Item(2, "laptop", 1));
		c.setItem(new Item(3, "muis", 1));
		assertEquals(customer, c);
	}
	
}
