package board;

import group.Group;

public class Cell {
	private int number;
	private Group group;
	public Cell() {
		
	}
	public void setGroup(Group g){
		group = g;
	}
	
	public void setNumber(int number){
		this.number = number;
	}
	public int getNumber(){
		return number;
	}
}
