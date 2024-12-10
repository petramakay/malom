package test;

import static org.junit.Assert.*;

import org.junit.Test;
import malom.Algoritmusok;
import javax.swing.*;
import java.awt.*;
import malom.Malom;

public class AlgoritmusokTest {

	@Test
	public void testCreateStone() {
		JLabel temp = new JLabel(new ImageIcon(Malom.class.getResource("/redstone.png")));
		temp.setBounds(45 * 3, 45 * 1, 30, 30);
		temp.setVisible(false);
		assertEquals(temp.getBounds(), Algoritmusok.createStone(1, 1, "r", "n").getBounds() );
	}
	
	@Test
	public void testGetCorrectDataForStones() {
		Integer[] data = {1,3};
		assertEquals(data, Algoritmusok.getCorrectDataForStones(1, 1));
	}
	
	@Test
	public void testGetStones() {
		assertEquals(120, Algoritmusok.getStones().size());
	}
	
}
