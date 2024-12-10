package malom;
import java.util.List;
import java.util.Scanner;
import java.io.*;



public class Malom implements Serializable{
	public static TableType t;
	public static Integer roundCounter;
	public static PlayerType playerOne = new PlayerType();
	public static PlayerType playerTwo = new PlayerType();
	public static List<Integer[]> malmok;
	public static List<Integer[]> previousMalmok;
	
	public Malom() {
		t = new TableType();
		roundCounter = 1;
		malmok = new MalomOperator(t).getMalmok();
		previousMalmok = new MalomOperator(t).getMalmok();
	}
	
	public Malom(PlayerType playerOne, PlayerType playerTwo) {
		this.t = new TableType();
		this.playerOne = playerOne;
		this.playerTwo = playerTwo;
		this.roundCounter = 1;
	}
	
	public static void load() {
		try {
			String[] nevek = new String[2];
			Integer[] stones = new Integer[2];
			
			BufferedReader br = new BufferedReader(new FileReader("save.txt"));
			nevek[0] = br.readLine();
			nevek[1] = br.readLine();
			stones[0] = Integer.parseInt(br.readLine());
			stones[1] = Integer.parseInt(br.readLine());
			br.close();
			
			
			PlayerType playerOne = new PlayerType(nevek[0], 9, 0);
			PlayerType playerTwo = new PlayerType(nevek[1], 9, 0);
			MainScreen.start(playerOne, playerTwo);
		}catch(Exception e) {
		}
	}
	
	public void save() {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter("save.txt"));
			bw.write(playerOne.getName());
			bw.newLine();
			bw.write(playerTwo.getName());
			bw.newLine();
			bw.write(""+playerOne.getStones());
			bw.newLine();
			bw.write(""+playerTwo.getStones());
			bw.newLine();
			
			bw.close();
		}catch(Exception e) {
		}
	}
	
}