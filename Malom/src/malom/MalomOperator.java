package malom;
import java.util.ArrayList;
import java.util.List;

public class MalomOperator {
	int fromX;
	int fromY;
	int toX;
	int toY;
	TableType table;

	//levétel, letétel
	public MalomOperator(TableType t) {
		this.table = t;
	}

	//kő mozgása
	public MalomOperator(int x, int y, TableType t) {
		this.toX = y;
		this.toY = x;
		this.table = t;
	}

	//fájlbeolvasás
	public MalomOperator(int fx, int fy, int tx, int ty, TableType t) {
		this.fromX = fy;
		this.fromY = fx;
		this.toX = ty;
		this.toY = tx;
		this.table = t;
	}

	//adott helyre léphetünk-e
	public boolean isValidStep(int toX, int toY, TableType t,
			Integer roundCounter) {
		if (t.getTable()[toX][toY] == 0)
			return true;
		return false;
	}

	//adott sor/oszlop megtalálható-e
	private boolean isUnderTest(int targetx, int targety, int r1, int c1,
			int r2, int c2, int r3, int c3) {
		if (targetx == r1 || targetx  == r2 || targetx == r3)
			if (targety == c1 || targety == c2 || targety == c3)
				return true;

		return false;
	}

	//egy kőről megmondja, hogy malomban van-e
	public boolean isStoneInMalom(int row, int col, TableType t) {

		if (isUnderTest(row, col, 0, 0, 3, 0, 7, 0))
			if (isMalom(0, 0, 3, 0, 7, 0, t.getTable()))
				return true; // 1. oszlop

		if (isUnderTest(row, col, 1, 0, 3, 1, 6, 0))
			if (isMalom(1, 0, 3, 1, 6, 0, t.getTable()))
				return true; // 2. oszlop

		if (isUnderTest(row, col, 2, 0, 3, 2, 5, 0))
			if (isMalom(2, 0, 3, 2, 5, 0, t.getTable()))
				return true; // 3. oszlop

		if (isUnderTest(row, col, 0, 1, 1, 1, 2, 1))
			if (isMalom(0, 1, 1, 1, 2, 1, t.getTable()))
				return true; // 4. oszlop felső

		if (isUnderTest(row, col, 5, 1, 6, 1, 7, 0))
			if (isMalom(5, 1, 6, 1, 7, 0, t.getTable()))
				return true; // 4. oszlop alsó

		if (isUnderTest(row, col, 2, 2, 4, 0, 5, 2))
			if (isMalom(2, 2, 4, 0, 5, 2, t.getTable()))
				return true; // 5. oszlop

		if (isUnderTest(row, col, 1, 2, 4, 1, 6, 2))
			if (isMalom(1, 2, 4, 1, 6, 2, t.getTable()))
				return true; // 6. oszlop

		if (isUnderTest(row, col, 0, 2, 4, 2, 7, 2))
			if (isMalom(0, 2, 4, 2, 7, 2, t.getTable()))
				return true; // 7. oszlop

		return false;
	}

	//3 kőről eldönti hogy malomban vannak-e
	public boolean isMalom(int r1, int c1, int r2, int c2, int r3, int c3,
			Integer[][] table) {
		if (table[r1][c1] != 0) {
			if (table[r1][c1] == table[r2][c2])
				if (table[r2][c2] == table[r3][c3])
					return true;
		}
		return false;
	}

	//táblára egy kő letevését teszi lehetővé
	public void operatorPut(Integer roundCounter, PlayerType playerOne,
			PlayerType playerTwo) {
		if (isValidStep(toX, toY, table, roundCounter)) {
			table.setTable(toX, toY, (roundCounter - 1) % 2 + 1);
			if (roundCounter % 2 == 1) {
				playerOne.setOnBoardStones(playerOne.getOnBoardStones() + 1);
			} else {
				playerTwo.setOnBoardStones(playerTwo.getOnBoardStones() + 1);
			}
		}
	}

	//táblán egy kővel alrébb lép
	public boolean operatorMove(Integer roundCounter) {
		if (isValidStep(toX, toY, table, roundCounter)) {
			if (fromX == 0 && fromY == 0) {
				if (toX == 0 && toY == 1) {
					table.setTable(toX, toY, table.getTable()[fromX][fromY]);
					table.setTable(fromX, fromY, 0);
					return true;
				}
				if (toX == 3 && toY == 0) {
					table.setTable(toX, toY, table.getTable()[fromX][fromY]);
					table.setTable(fromX, fromY, 0);
					return true;
				}
			}

			if (fromX == 0 && fromY == 1) {
				if (toX == 0 && toY == 0) {
					table.setTable(toX, toY, table.getTable()[fromX][fromY]);
					table.setTable(fromX, fromY, 0);
					return true;
				}
				if (toX == 1 && toY == 1) {
					table.setTable(toX, toY, table.getTable()[fromX][fromY]);
					table.setTable(fromX, fromY, 0);
					return true;
				}
				if (toX == 0 && toY == 2) {
					table.setTable(toX, toY, table.getTable()[fromX][fromY]);
					table.setTable(fromX, fromY, 0);
					return true;
				}
			}

			if (fromX == 0 && fromY == 2) {
				if (toX == 0 && toY == 1) {
					table.setTable(toX, toY, table.getTable()[fromX][fromY]);
					table.setTable(fromX, fromY, 0);
					return true;
				}
				if (toX == 4 && toY == 2) {
					table.setTable(toX, toY, table.getTable()[fromX][fromY]);
					table.setTable(fromX, fromY, 0);
					return true;
				}
			}

			if (fromX == 1 && fromY == 0) {
				if (toX == 1 && toY == 1) {
					table.setTable(toX, toY, table.getTable()[fromX][fromY]);
					table.setTable(fromX, fromY, 0);
					return true;
				}
				if (toX == 3 && toY == 1) {
					table.setTable(toX, toY, table.getTable()[fromX][fromY]);
					table.setTable(fromX, fromY, 0);
					return true;
				}
			}

			if (fromX == 1 && fromY == 1) {
				if (toX == 0 && toY == 1) {
					table.setTable(toX, toY, table.getTable()[fromX][fromY]);
					table.setTable(fromX, fromY, 0);
					return true;
				}
				if (toX == 1 && toY == 0) {
					table.setTable(toX, toY, table.getTable()[fromX][fromY]);
					table.setTable(fromX, fromY, 0);
					return true;
				}
				if (toX == 1 && toY == 2) {
					table.setTable(toX, toY, table.getTable()[fromX][fromY]);
					table.setTable(fromX, fromY, 0);
					return true;
				}
				if (toX == 2 && toY == 1) {
					table.setTable(toX, toY, table.getTable()[fromX][fromY]);
					table.setTable(fromX, fromY, 0);
					return true;
				}
			}

			if (fromX == 1 && fromY == 2) {
				if (toX == 1 && toY == 1) {
					table.setTable(toX, toY, table.getTable()[fromX][fromY]);
					table.setTable(fromX, fromY, 0);
					return true;
				}
				if (toX == 4 && toY == 1) {
					table.setTable(toX, toY, table.getTable()[fromX][fromY]);
					table.setTable(fromX, fromY, 0);
					return true;
				}
			}

			if (fromX == 2 && fromY == 0) {
				if (toX == 2 && toY == 1) {
					table.setTable(toX, toY, table.getTable()[fromX][fromY]);
					table.setTable(fromX, fromY, 0);
					return true;
				}
				if (toX == 3 && toY == 2) {
					table.setTable(toX, toY, table.getTable()[fromX][fromY]);
					table.setTable(fromX, fromY, 0);
					return true;
				}
			}

			if (fromX == 2 && fromY == 1) {
				if (toX == 1 && toY == 1) {
					table.setTable(toX, toY, table.getTable()[fromX][fromY]);
					table.setTable(fromX, fromY, 0);
					return true;
				}
				if (toX == 2 && toY == 0) {
					table.setTable(toX, toY, table.getTable()[fromX][fromY]);
					table.setTable(fromX, fromY, 0);
					return true;
				}
				if (toX == 2 && toY == 2) {
					table.setTable(toX, toY, table.getTable()[fromX][fromY]);
					table.setTable(fromX, fromY, 0);
					return true;
				}
			}

			if (fromX == 2 && fromY == 2) {
				if (toX == 2 && toY == 1) {
					table.setTable(toX, toY, table.getTable()[fromX][fromY]);
					table.setTable(fromX, fromY, 0);
					return true;
				}
				if (toX == 4 && toY == 0) {
					table.setTable(toX, toY, table.getTable()[fromX][fromY]);
					table.setTable(fromX, fromY, 0);
					return true;
				}
			}

			if (fromX == 3 && fromY == 0) {
				if (toX == 0 && toY == 0) {
					table.setTable(toX, toY, table.getTable()[fromX][fromY]);
					table.setTable(fromX, fromY, 0);
					return true;
				}
				if (toX == 7 && toY == 0) {
					table.setTable(toX, toY, table.getTable()[fromX][fromY]);
					table.setTable(fromX, fromY, 0);
					return true;
				}
				if (toX == 3 && toY == 1) {
					table.setTable(toX, toY, table.getTable()[fromX][fromY]);
					table.setTable(fromX, fromY, 0);
					return true;
				}
			}

			if (fromX == 3 && fromY == 1) {
				if (toX == 3 && toY == 0) {
					table.setTable(toX, toY, table.getTable()[fromX][fromY]);
					table.setTable(fromX, fromY, 0);
					return true;
				}
				if (toX == 1 && toY == 0) {
					table.setTable(toX, toY, table.getTable()[fromX][fromY]);
					table.setTable(fromX, fromY, 0);
					return true;
				}
				if (toX == 3 && toY == 2) {
					table.setTable(toX, toY, table.getTable()[fromX][fromY]);
					table.setTable(fromX, fromY, 0);
					return true;
				}
				if (toX == 6 && toY == 0) {
					table.setTable(toX, toY, table.getTable()[fromX][fromY]);
					table.setTable(fromX, fromY, 0);
					return true;
				}
			}

			if (fromX == 3 && fromY == 2) {
				if (toX == 3 && toY == 1) {
					table.setTable(toX, toY, table.getTable()[fromX][fromY]);
					table.setTable(fromX, fromY, 0);
					return true;
				}
				if (toX == 2 && toY == 0) {
					table.setTable(toX, toY, table.getTable()[fromX][fromY]);
					table.setTable(fromX, fromY, 0);
					return true;
				}
				if (toX == 5 && toY == 0) {
					table.setTable(toX, toY, table.getTable()[fromX][fromY]);
					table.setTable(fromX, fromY, 0);
					return true;
				}
			}

			if (fromX == 4 && fromY == 0) {
				if (toX == 2 && toY == 2) {
					table.setTable(toX, toY, table.getTable()[fromX][fromY]);
					table.setTable(fromX, fromY, 0);
					return true;
				}
				if (toX == 5 && toY == 2) {
					table.setTable(toX, toY, table.getTable()[fromX][fromY]);
					table.setTable(fromX, fromY, 0);
					return true;
				}
				if (toX == 4 && toY == 1) {
					table.setTable(toX, toY, table.getTable()[fromX][fromY]);
					table.setTable(fromX, fromY, 0);
					return true;
				}
			}

			if (fromX == 4 && fromY == 1) {
				if (toX == 4 && toY == 0) {
					table.setTable(toX, toY, table.getTable()[fromX][fromY]);
					table.setTable(fromX, fromY, 0);
					return true;
				}
				if (toX == 1 && toY == 2) {
					table.setTable(toX, toY, table.getTable()[fromX][fromY]);
					table.setTable(fromX, fromY, 0);
					return true;
				}
				if (toX == 6 && toY == 2) {
					table.setTable(toX, toY, table.getTable()[fromX][fromY]);
					table.setTable(fromX, fromY, 0);
					return true;
				}
				if (toX == 4 && toY == 2) {
					table.setTable(toX, toY, table.getTable()[fromX][fromY]);
					table.setTable(fromX, fromY, 0);
					return true;
				}
			}

			if (fromX == 4 && fromY == 2) {
				if (toX == 4 && toY == 1) {
					table.setTable(toX, toY, table.getTable()[fromX][fromY]);
					table.setTable(fromX, fromY, 0);
					return true;
				}
				if (toX == 0 && toY == 2) {
					table.setTable(toX, toY, table.getTable()[fromX][fromY]);
					table.setTable(fromX, fromY, 0);
					return true;
				}
				if (toX == 7 && toY == 2) {
					table.setTable(toX, toY, table.getTable()[fromX][fromY]);
					table.setTable(fromX, fromY, 0);
					return true;
				}
			}

			if (fromX == 5 && fromY == 0) {
				if (toX == 3 && toY == 1) {
					table.setTable(toX, toY, table.getTable()[fromX][fromY]);
					table.setTable(fromX, fromY, 0);
					return true;
				}
				if (toX == 5 && toY == 1) {
					table.setTable(toX, toY, table.getTable()[fromX][fromY]);
					table.setTable(fromX, fromY, 0);
					return true;
				}
			}

			if (fromX == 5 && fromY == 1) {
				if (toX == 5 && toY == 0) {
					table.setTable(toX, toY, table.getTable()[fromX][fromY]);
					table.setTable(fromX, fromY, 0);
					return true;
				}
				if (toX == 6 && toY == 1) {
					table.setTable(toX, toY, table.getTable()[fromX][fromY]);
					table.setTable(fromX, fromY, 0);
					return true;
				}
				if (toX == 5 && toY == 2) {
					table.setTable(toX, toY, table.getTable()[fromX][fromY]);
					table.setTable(fromX, fromY, 0);
					return true;
				}
			}

			if (fromX == 5 && fromY == 2) {
				if (toX == 5 && toY == 1) {
					table.setTable(toX, toY, table.getTable()[fromX][fromY]);
					table.setTable(fromX, fromY, 0);
					return true;
				}
				if (toX == 4 && toY == 0) {
					table.setTable(toX, toY, table.getTable()[fromX][fromY]);
					table.setTable(fromX, fromY, 0);
					return true;
				}
			}

			if (fromX == 6 && fromY == 0) {
				if (toX == 3 && toY == 1) {
					table.setTable(toX, toY, table.getTable()[fromX][fromY]);
					table.setTable(fromX, fromY, 0);
					return true;
				}
				if (toX == 6 && toY == 1) {
					table.setTable(toX, toY, table.getTable()[fromX][fromY]);
					table.setTable(fromX, fromY, 0);
					return true;
				}
			}

			if (fromX == 6 && fromY == 1) {
				if (toX == 6 && toY == 0) {
					table.setTable(toX, toY, table.getTable()[fromX][fromY]);
					table.setTable(fromX, fromY, 0);
					return true;
				}
				if (toX == 5 && toY == 1) {
					table.setTable(toX, toY, table.getTable()[fromX][fromY]);
					table.setTable(fromX, fromY, 0);
					return true;
				}
				if (toX == 7 && toY == 1) {
					table.setTable(toX, toY, table.getTable()[fromX][fromY]);
					table.setTable(fromX, fromY, 0);
					return true;
				}
				if (toX == 6 && toY == 2) {
					table.setTable(toX, toY, table.getTable()[fromX][fromY]);
					table.setTable(fromX, fromY, 0);
					return true;
				}
			}

			if (fromX == 6 && fromY == 2) {
				if (toX == 6 && toY == 1) {
					table.setTable(toX, toY, table.getTable()[fromX][fromY]);
					table.setTable(fromX, fromY, 0);
					return true;
				}
				if (toX == 4 && toY == 1) {
					table.setTable(toX, toY, table.getTable()[fromX][fromY]);
					table.setTable(fromX, fromY, 0);
					return true;
				}
			}

			if (fromX == 7 && fromY == 0) {
				if (toX == 3 && toY == 0) {
					table.setTable(toX, toY, table.getTable()[fromX][fromY]);
					table.setTable(fromX, fromY, 0);
					return true;
				}
				if (toX == 7 && toY == 1) {
					table.setTable(toX, toY, table.getTable()[fromX][fromY]);
					table.setTable(fromX, fromY, 0);
					return true;
				}
			}

			if (fromX == 7 && fromY == 1) {
				if (toX == 7 && toY == 0) {
					table.setTable(toX, toY, table.getTable()[fromX][fromY]);
					table.setTable(fromX, fromY, 0);
					return true;
				}
				if (toX == 6 && toY == 1) {
					table.setTable(toX, toY, table.getTable()[fromX][fromY]);
					table.setTable(fromX, fromY, 0);
					return true;
				}
				if (toX == 7 && toY == 2) {
					table.setTable(toX, toY, table.getTable()[fromX][fromY]);
					table.setTable(fromX, fromY, 0);
					return true;
				}
			}

			if (fromX == 7 && fromY == 2) {
				if (toX == 7 && toY == 1) {
					table.setTable(toX, toY, table.getTable()[fromX][fromY]);
					table.setTable(fromX, fromY, 0);
					return true;
				}
				if (toX == 4 && toY == 2) {
					table.setTable(toX, toY, table.getTable()[fromX][fromY]);
					table.setTable(fromX, fromY, 0);
					return true;
				}
			}
		}
		return false;
	}

	//adott játékos minen köve malomban van-e
	public boolean allInMalom(Integer roundCounter) {
		boolean allInMalom = true;

		for (int r = 0; r < 7; r++) {
			for (int c = 0; c < 3; c++) {
				if (table.getTable()[r][c] == roundCounter % 2 + 1)
					if (!isStoneInMalom(r, c, table)) {
						allInMalom = false;
						break;
					} else {
						continue;
					}
			}
			if (!allInMalom)
				break;
			else
				continue;
		}

		return allInMalom;
	}

	//tábláról levesz egy követ
	public boolean operatorRemove(int X, int Y, Integer roundCounter,
			PlayerType playerOne, PlayerType playerTwo) {
		if (allInMalom(roundCounter)) {
			table.setTable(X, Y, 0);
			if (roundCounter % 2 + 1 == 1) {
				playerOne.setStones(playerOne.getStones() - 1);
				playerOne.setOnBoardStones(playerOne.getOnBoardStones() - 1);
				return true;
			} else {
				playerTwo.setStones(playerTwo.getStones() - 1);
				playerTwo.setOnBoardStones(playerTwo.getOnBoardStones() - 1);
				return true;
			}
		} else {
			if (!isStoneInMalom(X, Y, table)) {
				table.setTable(X, Y, 0);
				if (roundCounter % 2 + 1 == 1) {
					playerOne.setStones(playerOne.getStones() - 1);
					playerOne
							.setOnBoardStones(playerOne.getOnBoardStones() - 1);
					return true;
				} else {
					playerTwo.setStones(playerTwo.getStones() - 1);
					playerTwo
							.setOnBoardStones(playerTwo.getOnBoardStones() - 1);
					return true;
				}
			}
		}

		return false;
	}

	//táblán egy kővel ugrik
	public boolean operatorJump(Integer roundCounter) {
		if (isValidStep(toX, toY, table, roundCounter)) {
			table.setTable(toX, toY, table.getTable()[fromX][fromY]);
			table.setTable(fromX, fromY, 0);
			return true;
		}
		return false;
	}

	//paraméterül kapott számokat egy tömbbe helyezi
	private Integer[] createRow(int i1, int i2, int i3, int i4, int i5, int i6) {
		Integer[] res = new Integer[6];
		res[0] = i1;
		res[1] = i2;
		res[2] = i3;
		res[3] = i4;
		res[4] = i5;
		res[5] = i6;

		return res;
	}

	//táblán lévő malmok listája
	public List<Integer[]> getMalmok() {
		List<Integer[]> res = new ArrayList<Integer[]>();

		for (int i = 0; i < 8; i++) {
			if (isMalom(i, 0, i, 1, i, 2, table.getTable())) {
				res.add(createRow(i, 0, i, 1, i, 2));
			}
		}

		if (isMalom(0, 0, 3, 0, 7, 0, table.getTable()))
			res.add(createRow(0, 0, 3, 0, 7, 0)); // 1. oszlop

		if (isMalom(1, 0, 3, 1, 6, 0, table.getTable()))
			res.add(createRow(1, 0, 3, 1, 6, 0)); // 2. oszlop

		if (isMalom(2, 0, 3, 2, 5, 0, table.getTable()))
			res.add(createRow(2, 0, 3, 2, 5, 0)); // 3. oszlop

		if (isMalom(0, 1, 1, 1, 2, 1, table.getTable()))
			res.add(createRow(0, 1, 1, 1, 2, 1)); // 4. oszlop felső

		if (isMalom(5, 1, 6, 1, 7, 0, table.getTable()))
			res.add(createRow(5, 1, 6, 1, 7, 0)); // 4. oszlop alsó

		if (isMalom(2, 2, 4, 0, 5, 2, table.getTable()))
			res.add(createRow(2, 2, 4, 0, 5, 2)); // 5. oszlop

		if (isMalom(1, 2, 4, 1, 6, 2, table.getTable()))
			res.add(createRow(1, 2, 4, 1, 6, 2)); // 6. oszlop

		if (isMalom(0, 2, 4, 2, 7, 2, table.getTable()))
			res.add(createRow(0, 2, 4, 2, 7, 2)); // 7. oszlop

		return res;
	}

	//eldönti, hogy jött-e új malom
	public boolean hasNewMalom(List<Integer[]> malmok,
			List<Integer[]> previousMalmok) {
		Boolean[] hasPair = new Boolean[malmok.size()];
		for (int m = 0; m < malmok.size(); m++) {
			boolean has = false;
			for (Integer[] intarray : previousMalmok) {
				int i = 0;
				for (i = 0; i < 6; i++) {
					if (malmok.get(m)[i] == intarray[i])
						continue;
					else {
						break;
					}
				}
				if (i == 6)
					has = true;
			}
			hasPair[m] = has;
		}

		for (boolean b : hasPair) {
			if (!b)
				return true;
		}

		return false;
	}

	//adott játékos rakhat-e le követ
	public boolean canPut(Integer roundCounter, PlayerType playerOne,
			PlayerType playerTwo) {
		if (roundCounter % 2 == 1) {
			return playerOne.canPut();
		} else {
			return playerTwo.canPut();
		}
	}

	//adott játékos léphet-e
	public boolean canMove(Integer roundCounter, PlayerType playerOne,
			PlayerType playerTwo) {
		if (roundCounter % 2 == 1) {
			return playerOne.canMove();
		} else {
			return playerTwo.canMove();
		}
	}

	//adott játékos ugorhat-e
	public boolean canJump(Integer roundCounter, PlayerType playerOne,
			PlayerType playerTwo) {
		if (roundCounter % 2 == 1) {
			return playerOne.canJump();
		} else
			return playerTwo.canJump();
	}

	//adott játékos vehet-e le követ
	public boolean canRemove(Integer roundCounter, PlayerType playerOne,
			PlayerType playerTwo, Malom malom) {
		if (hasNewMalom(malom.malmok, malom.previousMalmok)) {
			return true;
		} else
			return false;
	}

	//adott játékosnak elfogytak-e a kövei
	public boolean notEnoughStones(Malom malom) {
		if (malom.roundCounter % 2 == 1)
			if (malom.playerOne.getStones() == 2)
				return true;
			else
				return false;
		else if (malom.playerTwo.getStones() == 2)
			return true;
		else
			return false;
	}

	//eldönti hogy vége van-e a játéknak
	public boolean isGameOver(Malom malom) {
		if (notEnoughStones(malom))
			return true;
		else {
			return false;
		}

	}
}