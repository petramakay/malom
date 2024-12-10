package malom;
public class TableType {
	private Integer[][] table = new Integer[8][3];

	public TableType() {
		for (Integer[] i : table)
			for (int k = 0; k < i.length; k++) {
				i[k] = 0;
			}
	}

	public void setTable(int rowNum, int colNum, int value) {
		table[rowNum][colNum] = value;
	}
	

	public Integer[][] getTable() {
		return table;
	}
}