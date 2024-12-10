package malom;
public class PlayerType{
	private String name;
	private int stones;
	private int onBoard;
	
	public PlayerType() {
		this.stones = 9;
		this.onBoard = 0;
	}
	
	public PlayerType(String name, int stones, int onBoard) {
		this.name = name;
		this.stones = stones;
		this.onBoard = onBoard;
	}
	
	
	public void setStones(int count) {
		stones = count;
	}
	
	public int getStones() {
		return stones;
	}
	
	public int getOnBoardStones() {
		return onBoard;
	}
	
	public void setOnBoardStones(int count) {
		onBoard = count;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public boolean canPut() {
		if ((stones - onBoard) > 0)
			return true;
		else
			return false;
	}
	
	public boolean canMove() {
		if ((stones == onBoard) && stones > 3)
			return true;
		else
			return false;
	}
	
	public boolean canJump() {
		if (stones <= 3) {
			return true;
		}
		return false;
	}
	
}