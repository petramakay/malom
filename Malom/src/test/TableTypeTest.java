package test;
import malom.TableType;

import static org.junit.Assert.*;

import org.junit.Test;

public class TableTypeTest {

	@Test
	public void testgettable() {
		TableType table = new TableType();
		Integer[][] tmp = table.getTable();
		assertEquals((int)tmp[1][1], (int)0);
	}
	
	@Test
	public void testsettable() {
		TableType table = new TableType();
		table.setTable(1, 1, 2);
		Integer[][] tmp = table.getTable();
		assertEquals((int)tmp[1][1], (int)2);
	}

}
