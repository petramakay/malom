package test;
import malom.PlayerType;

import static org.junit.Assert.*;

import org.junit.Test;

public class PlayerTypeTest {

	@Test
	public void testgetstones() {
		PlayerType player = new PlayerType();
		assertEquals((int)9, (int)player.getStones());
	}
	
	@Test
	public void testsetstones() {
		PlayerType player = new PlayerType("próba", 9, 0);
		player.setStones(5);
		assertEquals((int)5, (int)player.getStones());
	}

	@Test
	public void testgetonboardstones() {
		PlayerType player = new PlayerType("próba", 9, 0);
		assertEquals((int)0, (int)player.getOnBoardStones());
	}
	
	@Test
	public void testsetonboardstones() {
		PlayerType player = new PlayerType("próba", 9, 0);
		player.setOnBoardStones(2);
		assertEquals((int)2, (int)player.getOnBoardStones());
	}
	
	@Test
	public void testsetname() {
		PlayerType player = new PlayerType("próba", 9, 0);
		player.setName("próba1");
		assertEquals("próba1", player.getName());
	}
	
	@Test
	public void testcanjump1() {
		PlayerType player = new PlayerType("próba", 3, 3);
		assertEquals(true, player.canJump());
	}
	
	@Test
	public void testcanjump2() {
		PlayerType player = new PlayerType("próba", 4, 4);
		assertEquals(false, player.canJump());
	}
	
	@Test
	public void testcanmove1() {
		PlayerType player = new PlayerType("próba", 9, 9);
		assertEquals(true, player.canMove());
	}
	
	@Test
	public void testcanmove2() {
		PlayerType player = new PlayerType("próba", 9, 7);
		assertEquals(false, player.canMove());
	}
	
	@Test
	public void testcanput1() {
		PlayerType player = new PlayerType("próba", 9, 0);
		assertEquals(true, player.canPut());
	}
	
	@Test
	public void testcanput2() {
		PlayerType player = new PlayerType("próba", 9, 9);
		assertEquals(false, player.canPut());
	}
	
}
