package malom;
import java.io.*;
import javax.swing.JLabel;

public class StoneType implements Serializable{
	private JLabel label;
	private String state;
	private String color;
	private Boolean visible;
	private Integer row;
	private Integer col;


	public JLabel getLabel() {
		return label;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	public String getColor() {
		return color;
	}


	public void setColor(String color) {
		this.color = color;
	}


	public Boolean getVisible() {
		return visible;
	}


	public void setVisible(Boolean visible) {
		this.visible = visible;
		this.label.setVisible(visible);
	}


	public Integer getRow() {
		return row;
	}
	

	public Integer getCol() {
		return col;
	}


	public StoneType(String state, String color, Boolean visible,
			Integer row, Integer col) {
		super();
		this.state = state;
		this.color = color;
		this.visible = visible;
		this.row = row;
		this.col = col;
		this.label = Algoritmusok.createStone(row, col, color, state);
		this.label.setVisible(visible);
	}


}