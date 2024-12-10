package malom;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Algoritmusok {
	
	public static JLabel createStone(int row, int col, String c, String a) {
		Integer[] data = getCorrectDataForStones(row, col);
  
		JLabel temp = new JLabel();
		if ("s".equals(a)) {
			if ("r".equals(c)) {
				temp = new JLabel(new ImageIcon(
						Malom.class.getResource("/redstoneSelected.png")));
				temp.setBounds(45 * data[1], 45 * data[0], 30, 30);
				temp.setVisible(false);
			} else {
				temp = new JLabel(new ImageIcon(
						Malom.class.getResource("/bluestoneSelected.png")));
				temp.setBounds(45 * data[1], 45 * data[0], 30, 30);
				temp.setVisible(false);
			}
		}
		if ("n".equals(a)) {
			if ("r".equals(c)) {
				temp = new JLabel(new ImageIcon(
						Malom.class.getResource("/redstone.png")));
				temp.setBounds(45 * data[1], 45 * data[0], 30, 30);
				temp.setVisible(false);
			} else {
				temp = new JLabel(new ImageIcon(
						Malom.class.getResource("/bluestone.png")));
				temp.setBounds(45 * data[1], 45 * data[0], 30, 30);
				temp.setVisible(false);
			}
		}
		if ("p".equals(a)) {
			temp = new JLabel(new ImageIcon(
					Malom.class.getResource("/possible.png")));
			temp.setBounds(45 * data[1], 45 * data[0], 30, 30);
			temp.setVisible(false);
		}
		return temp;
	}

	public static Integer[] getCorrectDataForStones(int r, int c) {
		Integer[] res = new Integer[2];
		int col = -1;
		switch (r) {
		case 0:
		case 7:
			switch (c) {
			case 0:
				col = 0;
				break;
			case 1:
				col = 3;
				break;
			case 2:
				col = 6;
				break;
			}
			break;

		case 1:
		case 6: {
			switch (c) {
			case 0:
				col = 1;
				break;
			case 1:
				col = 3;
				break;
			case 2:
				col = 5;
				break;
			}
			break;
		}

		case 2:
		case 5: {
			switch (c) {
			case 0:
				col = 2;
				break;
			case 1:
				col = 3;
				break;
			case 2:
				col = 4;
				break;
			}
			break;
		}

		case 3: {
			switch (c) {
			case 0:
				col = 0;
				break;
			case 1:
				col = 1;
				break;
			case 2:
				col = 2;
				break;
			}
			break;
		}

		case 4: {
			switch (c) {
			case 0:
				col = 4;
				break;
			case 1:
				col = 5;
				break;
			case 2:
				col = 6;
				break;
			}
			break;
		}
		}

		res[0] = r < 4 ? r : r - 1;
		res[1] = col;

		return res;
	}

	//létrehozza az összes követ
	public static List<StoneType> getStones() {
		List<StoneType> res = new ArrayList<StoneType>();

		for (int r = 0; r < 8; r++) {
			for (int c = 0; c < 3; c++) {
				res.add(new StoneType("n", "r", false, r, c));
				res.add(new StoneType("n", "b", false, r, c));
				res.add(new StoneType("s", "r", false, r, c));
				res.add(new StoneType("s", "b", false, r, c));
				res.add(new StoneType("n", "p", false, r, c));
			}
		}

		return res;
	}

	//eldönti egy kőről hogy azt keressük-e
	private static boolean isWanted(StoneType st, int r, int c, TableType t) {
		String color = t.getTable()[r][c] == 1 ? "r" : "b";
		if (st.getRow() == r && st.getCol() == c) {
			if (st.getColor().equals(color)) {
				if (st.getState().equals("n"))
					return true;
			}
		}

		return false;
	}

	//megadja hogy az adott pozíción követ választottak-e
	public static boolean isClicked(StoneType st, MouseEvent me) {
		Integer[] data = Algoritmusok.getCorrectDataForStones(st.getRow(),
				st.getCol());
		int laMidX = (int) ((data[1]) * 45 + 15);
		int laMidY = (int) ((data[0]) * 45 + 15 + 20);

		if (Math.abs(laMidX - me.getX()) < 15) {
			if (Math.abs(laMidY - me.getY()) < 15) {
				return true;
			}
		}
		return false;
	}

	//kővel való ugrás
	public static boolean jumpStone(MouseEvent me) {
		if (MainScreen.haveSelected) {
			for (StoneType st : MainScreen.stones) {
				if (MainScreen.malom.roundCounter % 2 == 1) {
					if (st.getState().equals("n"))
						if (st.getColor().equals("r"))
							if (isClicked(st, me)) {
								MalomOperator mo = new MalomOperator(
										MainScreen.selected.getCol(),
										MainScreen.selected.getRow(),
										st.getCol(), st.getRow(),
										MainScreen.malom.t);
								if (mo.operatorJump(MainScreen.malom.roundCounter)) {
									st.setVisible(true);
									for (StoneType sta : MainScreen.stones) {
										if (sta.equals(MainScreen.selected)) {
											sta.setVisible(false);
										}
									}
									return true;
								} else {
									JOptionPane.showMessageDialog(null,
											"Nem léphetsz oda");
									return false;
								}
							}
				} else {
					if (st.getState().equals("n"))
						if (st.getColor().equals("b"))
							if (isClicked(st, me)) {
								MalomOperator mo = new MalomOperator(
										MainScreen.selected.getCol(),
										MainScreen.selected.getRow(),
										st.getCol(), st.getRow(),
										MainScreen.malom.t);
								if (mo.operatorJump(MainScreen.malom.roundCounter)) {
									st.setVisible(true);
									for (StoneType sta : MainScreen.stones) {
										if (sta.equals(MainScreen.selected)) {
											sta.setVisible(false);
										}
									}
									return true;
								} else {
									JOptionPane.showMessageDialog(null,
											"Nem léphetsz oda");
									return false;
								}
							}
				}
			}
		} else {
			for (StoneType st : MainScreen.stones) {
				if (MainScreen.malom.roundCounter % 2 == 1) {
					if (st.getState().equals("s"))
						if (st.getColor().equals("r"))
							if (isClicked(st, me)) {
								MainScreen.haveSelected = true;
								MainScreen.selected = st;
								st.setVisible(true);
								for (StoneType sta : MainScreen.stones) {
									if (sta.getState().equals("n")) {
										if (sta.getColor().equals("r")) {
											if (isClicked(sta, me)) {
												sta.setVisible(false);
											}
										}
									}
								}
								return false;
							}
				} else {
					if (st.getState().equals("s"))
						if (st.getColor().equals("b"))
							if (isClicked(st, me)) {
								MainScreen.haveSelected = true;
								MainScreen.selected = st;
								st.setVisible(true);
								for (StoneType sta : MainScreen.stones) {
									if (sta.getState().equals("n")) {
										if (sta.getColor().equals("b")) {
											if (isClicked(sta, me)) {
												sta.setVisible(false);
											}
										}
									}
								}
								return false;
							}
				}
			}
		}
		return false;
	}

	//kővel alrébb lép
	public static boolean moveStone(MouseEvent me) {
		if (MainScreen.haveSelected) {
			for (StoneType st : MainScreen.stones) {
				if (MainScreen.malom.roundCounter % 2 == 1) {
					if (st.getState().equals("n"))
						if (st.getColor().equals("r"))
							if (isClicked(st, me)) {
								MalomOperator mo = new MalomOperator(
										MainScreen.selected.getCol(),
										MainScreen.selected.getRow(),
										st.getCol(), st.getRow(),
										MainScreen.malom.t);
								if (mo.operatorMove(MainScreen.malom.roundCounter)) {
									st.setVisible(true);
									for (StoneType sta : MainScreen.stones) {
										if (sta.equals(MainScreen.selected)) {
											sta.setVisible(false);
										}
									}
									return true;
								} else {
									JOptionPane.showMessageDialog(null,
											"Nem léphetsz oda");
									return false;
								}
							}
				} else {
					if (st.getState().equals("n"))
						if (st.getColor().equals("b"))
							if (isClicked(st, me)) {
								MalomOperator mo = new MalomOperator(
										MainScreen.selected.getCol(),
										MainScreen.selected.getRow(),
										st.getCol(), st.getRow(),
										MainScreen.malom.t);
								if (mo.operatorMove(MainScreen.malom.roundCounter)) {
									st.setVisible(true);
									for (StoneType sta : MainScreen.stones) {
										if (sta.equals(MainScreen.selected)) {
											sta.setVisible(false);
										}
									}
									return true;
								} else {
									JOptionPane.showMessageDialog(null,
											"Nem léphetsz oda");
									return false;
								}
							}
				}
			}
		} else {
			for (StoneType st : MainScreen.stones) {
				if (MainScreen.malom.roundCounter % 2 == 1) {
					if (st.getState().equals("s"))
						if (st.getColor().equals("r"))
							if (isClicked(st, me)) {
								MainScreen.haveSelected = true;
								MainScreen.selected = st;
								st.setVisible(true);
								for (StoneType sta : MainScreen.stones) {
									if (sta.getState().equals("n")) {
										if (sta.getColor().equals("r")) {
											if (isClicked(sta, me)) {
												sta.setVisible(false);
											}
										}
									}
								}
								return false;
							}
				} else {
					if (st.getState().equals("s"))
						if (st.getColor().equals("b"))
							if (isClicked(st, me)) {
								MainScreen.haveSelected = true;
								MainScreen.selected = st;
								st.setVisible(true);
								for (StoneType sta : MainScreen.stones) {
									if (sta.getState().equals("n")) {
										if (sta.getColor().equals("b")) {
											if (isClicked(sta, me)) {
												sta.setVisible(false);
											}
										}
									}
								}
								return false;
							}
				}
			}
		}
		return false;
	}

	//követ letesz
	public static void putStone(MouseEvent me) {
		for (StoneType st : MainScreen.stones) {
			if (MainScreen.malom.roundCounter % 2 == 1) {
				if (st.getState().equals("n"))
					if (st.getColor().equals("r"))
						if (isClicked(st, me)) {
							MalomOperator mo = new MalomOperator(st.getCol(),
									st.getRow(), MainScreen.malom.t);
							st.setVisible(true);
							mo.operatorPut(MainScreen.malom.roundCounter,
									MainScreen.malom.playerOne,
									MainScreen.malom.playerTwo);
						}

			} else {
				if (st.getState().equals("n"))
					if (st.getColor().equals("b"))
						if (isClicked(st, me)) {
							MalomOperator mo = new MalomOperator(st.getCol(),
									st.getRow(), MainScreen.malom.t);
							st.setVisible(true);
							mo.operatorPut(MainScreen.malom.roundCounter,
									MainScreen.malom.playerOne,
									MainScreen.malom.playerTwo);
						}
			}
		}
	}

	//követ elvesz
	public static boolean removeStone(MouseEvent me) {
		for (StoneType st : MainScreen.stones) {
			if (MainScreen.malom.roundCounter % 2 == 1) {
				if (st.getState().equals("n"))
					if (st.getColor().equals("b"))
						if (isClicked(st, me)) {
							MalomOperator mo = new MalomOperator(
									MainScreen.malom.t);
							if (!mo.operatorRemove(st.getRow(), st.getCol(),
									MainScreen.malom.roundCounter,
									MainScreen.malom.playerOne,
									MainScreen.malom.playerTwo)) {
								JOptionPane.showMessageDialog(null,
										"Nem veheted le azt a követ.");
								return false;
							} else {
								st.setVisible(false);
								return true;
							}
						}
			} else {
				if (st.getState().equals("n"))
					if (st.getColor().equals("r"))
						if (isClicked(st, me)) {
							MalomOperator mo = new MalomOperator(
									MainScreen.malom.t);
							if (!mo.operatorRemove(st.getRow(), st.getCol(),
									MainScreen.malom.roundCounter,
									MainScreen.malom.playerOne,
									MainScreen.malom.playerTwo)) {
								JOptionPane.showMessageDialog(null,
										"Nem veheted le azt a követ.");
								return false;
							} else {
								st.setVisible(false);
								return true;
							}
						}
			}
		}
		return false;
	}

}

