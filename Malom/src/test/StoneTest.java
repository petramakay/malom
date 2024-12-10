package test;
import malom.StoneType;

import static org.junit.Assert.*;

import org.junit.Test;

public class StoneTest {

	@Test
	public void testgetvisible() {
		StoneType stone = new StoneType("n", "b", true, 2, 2);
		assertEquals(true, stone.getVisible());
	}
	
	@Test
	public void testsetvisible() {
		StoneType stone = new StoneType("n", "b", true, 2, 2);
		stone.setVisible(false);
		assertEquals(false, stone.getVisible());
	}
	
	@Test
	public void testgetcolor() {
		StoneType stone = new StoneType("n", "b", true, 2, 2);
		assertEquals("b", stone.getColor());
	}
	
	@Test
	public void testsetcolor() {
		StoneType stone = new StoneType("n", "b", true, 2, 2);
		stone.setColor("r");
		assertEquals("r", stone.getColor());
	}
	
	@Test
	public void testgetstate() {
		StoneType stone = new StoneType("n", "b", true, 2, 2);
		assertEquals("n", stone.getState());
	}
	
	@Test
	public void testsetstate() {
		StoneType stone = new StoneType("n", "b", true, 2, 2);
		stone.setState("s");
		assertEquals("s", stone.getState());
	}
	
	@Test
	public void testgetrow() {
		StoneType stone = new StoneType("n", "b", true, 2, 2);
		assertEquals((int)2, (int)stone.getRow());
	}
	
	@Test
	public void testgetcol() {
		StoneType stone = new StoneType("n", "b", true, 2, 2);
		assertEquals((int)2, (int)stone.getCol());
	}

}
