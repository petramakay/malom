package test;

import static org.junit.Assert.*;

import org.junit.Test;
import malom.MalomOperator;
import malom.PlayerType;
import malom.TableType;
import malom.Malom;

public class MalomOperatorTest {

	@Test
	public void testkonstruktor1() {
		Malom tmp = new Malom();
		MalomOperator mo1 = new MalomOperator(tmp.t);
		MalomOperator mo2 = new MalomOperator(tmp.t);
		
		assertEquals(mo1.canJump(tmp.roundCounter, tmp.playerOne, tmp.playerTwo), mo2.canJump(tmp.roundCounter, tmp.playerOne, tmp.playerTwo));
	}
	
	@Test
	public void testkonstruktor2() {
		Malom tmp = new Malom();
		MalomOperator mo1 = new MalomOperator(2, 2, tmp.t);
		MalomOperator mo2 = new MalomOperator(2, 2, tmp.t);
		assertEquals(mo1.canMove(tmp.roundCounter, tmp.playerOne, tmp.playerTwo), mo2.canMove(tmp.roundCounter, tmp.playerOne, tmp.playerTwo));
	}

	@Test
	public void testkonstruktor3() {
		Malom tmp = new Malom();
		MalomOperator mo1 = new MalomOperator(2,2,3,3, tmp.t);
		MalomOperator mo2 = new MalomOperator(2,2,3,3, tmp.t);
		assertEquals(mo1.canPut(tmp.roundCounter, tmp.playerOne, tmp.playerTwo), mo2.canPut(tmp.roundCounter, tmp.playerOne, tmp.playerTwo));
	}
	
	@Test
	public void testisValidStep1() {
		Malom tmp = new Malom();
		MalomOperator mo = new MalomOperator(0,0,0,1,tmp.t);
		assertEquals(true, mo.isValidStep(0, 1, tmp.t, tmp.roundCounter));
	}
	
	@Test
	public void testisValidStep2() {
		Malom tmp = new Malom();
		tmp.t.setTable(1, 1, 2);
		MalomOperator mo = new MalomOperator(tmp.t);
		assertEquals(false, mo.isValidStep(1, 1, tmp.t, tmp.roundCounter));
	}
	
	@Test
	public void testisStoneInMalom1() {
		Malom tmp = new Malom();
		tmp.t.setTable(0, 0, 2);
		tmp.t.setTable(3, 0, 2);
		tmp.t.setTable(7, 0, 2);
		MalomOperator mo = new MalomOperator(tmp.t);
		assertEquals(true, mo.isStoneInMalom(0, 0, tmp.t));
	}
	
	@Test
	public void testisStoneInMalom2() {
		Malom tmp = new Malom();
		MalomOperator mo = new MalomOperator(tmp.t);
		assertEquals(false, mo.isStoneInMalom(0, 0, tmp.t));
	}
	
	@Test
	public void testisStoneInMalom3() {
		Malom tmp = new Malom();
		tmp.t.setTable(0, 2, 2);
		tmp.t.setTable(4, 2, 2);
		tmp.t.setTable(7, 2, 2);
		MalomOperator mo = new MalomOperator(tmp.t);
		assertEquals(true, mo.isStoneInMalom(7, 2, tmp.t));
	}
	
	@Test
	public void testisMalom() {
		Malom tmp = new Malom();
		tmp.t.setTable(0, 0, 2);
		tmp.t.setTable(3, 0, 2);
		tmp.t.setTable(7, 0, 2);
		MalomOperator mo = new MalomOperator(tmp.t);
		assertEquals(true, mo.isMalom(0, 0, 3, 0, 7, 0, tmp.t.getTable()));
	}
	
	@Test
	public void testisGameOver1() {
		Malom tmp = new Malom();
		tmp.playerOne.setStones(2);
		tmp.playerOne.setOnBoardStones(2);
		MalomOperator mo = new MalomOperator(tmp.t);
		assertEquals(true, mo.isGameOver(tmp));
	}
	
	@Test
	public void testisGameOver2() {
		Malom tmp = new Malom();
		tmp.playerOne.setStones(9);
		tmp.playerTwo.setStones(9);
		MalomOperator mo = new MalomOperator(tmp.t);
		assertEquals(false, mo.isGameOver(tmp));
	}
	
	@Test
	public void testoperatorPut1() {
		Malom m = new Malom();
		new MalomOperator(1,1, m.t).operatorPut(m.roundCounter, m.playerOne, m.playerTwo);
		assertEquals(true, m.t.getTable()[1][1] == 1);
	}
	
	@Test
	public void testoperatorPut2() {
		Malom m = new Malom();
		new MalomOperator(1,1, m.t).operatorPut(m.roundCounter, m.playerOne, m.playerTwo);
		assertEquals(false, m.t.getTable()[1][1] == 0);
	}
	
	@Test
	public void testoperatorMove1() {
		Malom m = new Malom();
		m.t.setTable(3, 0, 2);
		m.t.setTable(3, 1, 2);
		m.t.setTable(3, 2, 2);
		m.t.setTable(2, 1, 2);
		assertEquals(true, new MalomOperator(1, 2, 2, 2, m.t).operatorMove(m.roundCounter));
	}
	
	@Test
	public void testoperatorMove2() {
		Malom m = new Malom();
		m.t.setTable(3, 0, 2);
		m.t.setTable(3, 1, 2);
		m.t.setTable(3, 2, 2);
		m.t.setTable(2, 1, 2);
		assertEquals(true, new MalomOperator(1, 2, 2, 2, m.t).operatorMove(m.roundCounter));
	}
	
	@Test
	public void testoperatorMove3() {
		Malom m = new Malom();
		m.t.setTable(3, 0, 2);
		m.t.setTable(3, 1, 2);
		m.t.setTable(3, 2, 2);
		m.t.setTable(2, 1, 2);
		assertEquals(true, new MalomOperator(0, 0, 1, 0, m.t).operatorMove(m.roundCounter));
	}
	
	@Test
	public void testoperatorMove4() {
		Malom m = new Malom();
		m.t.setTable(3, 1, 2);
		m.t.setTable(3, 2, 2);
		m.t.setTable(2, 1, 2);
		assertEquals(true, new MalomOperator(0, 0, 0, 3, m.t).operatorMove(m.roundCounter));
	}
	
	@Test
	public void testoperatorMove5() {
		Malom m = new Malom();
		m.t.setTable(3, 1, 2);
		m.t.setTable(3, 2, 2);
		m.t.setTable(2, 1, 2);
		assertEquals(true, new MalomOperator(2, 7, 1, 7, m.t).operatorMove(m.roundCounter));
	}
	
	@Test
	public void testoperatorMove6() {
		Malom m = new Malom();
		m.t.setTable(3, 1, 2);
		m.t.setTable(3, 2, 2);
		m.t.setTable(2, 1, 2);
		assertEquals(true, new MalomOperator(2, 6, 1, 4, m.t).operatorMove(m.roundCounter));
	}
	
	@Test
	public void testOperatorRemove() {
		Malom m = new Malom();
		m.t.setTable(3, 0, 2);
		m.t.setTable(3, 1, 2);
		m.t.setTable(3, 2, 2);
		m.t.setTable(2, 1, 2);
		
		assertEquals(true, new MalomOperator(m.t).operatorRemove(3, 0, m.roundCounter, m.playerOne, m.playerTwo));
	}
	
	@Test
	public void testOperatorJump() {
		Malom m = new Malom();
		m.t.setTable(3, 0, 2);
		m.t.setTable(3, 1, 2);
		m.t.setTable(3, 2, 2);
		m.t.setTable(2, 1, 2);
		
		assertEquals(true, new MalomOperator(1, 2, 2, 2, m.t).operatorJump(m.roundCounter));
	}
	
	@Test
	public void testnotEnoughStone1() {
		Malom m = new Malom();
		m.playerTwo.setStones(2);
		MalomOperator mo = new MalomOperator(m.t);
		assertEquals(false, mo.notEnoughStones(m));
	}
	
	@Test
	public void testnotEnoughStone2() {
		Malom m = new Malom();
		m.roundCounter = 0;
		m.playerTwo.setStones(2);
		MalomOperator mo = new MalomOperator(m.t);
		assertEquals(true, mo.notEnoughStones(m));
	}
	
	
}
