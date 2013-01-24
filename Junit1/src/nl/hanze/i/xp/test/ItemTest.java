package nl.hanze.i.xp.test;

import static org.junit.Assert.*;
import org.junit.*;
import nl.hanze.i.xp.domain.*; 
import nl.hanze.i.xp.exception.*;

public class ItemTest {
	private Item item;
	
	@Before
	public void setUp() throws Exception {
		item=new Item();
		item.setIID(1);
		item.setDescription("Pen");
		item.setQuantity(2);
	}

	@Test
	public void testQuantityNotNegative2() throws NotNegativeException {
		item.setQuantity(3);
	}
	
	@Test
	public void testIID() {
		assertEquals(item.getIID(), 1);
	}
	
	@Test
	public void testDescription() {
		assertEquals(item.getDescription(), "Pen");
	}
	
	@Test
	public void testQuantity() {
		assertEquals(item.getQuantity(), 2);
	}
	
	@Test(expected=nl.hanze.i.xp.exception.NotNegativeException.class)
	public void testQuantityNotNegative1() throws NotNegativeException {
		item.setQuantity(-1);
	}

	@Test
	public void testNonDefaultConstructor1() throws NotNegativeException {
		item=new Item(45, "laptop", 1);
		assertEquals(item.getIID(), 45);
		assertEquals(item.getDescription(), "laptop");
		assertEquals(item.getQuantity(), 1);
	}

	@Test(expected=nl.hanze.i.xp.exception.NotNegativeException.class)
	public void testNonDefaultConstructor2() throws NotNegativeException {
		item=new Item(45, "laptop", -1);
	}
	
	@Test
	public void equalityTest() throws NotNegativeException {
		assertEquals(item, new Item(1,"Pen",2));
	}
}
