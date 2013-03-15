package board;

import group.Group;

public class Cell {
	private int number = 0;
	private Group group;
	public Cell() {
		
	}
	public void setGroup(Group g){
		group = g;
	}
	public Group getGroup(){
		return group;
	}
	
	public void setNumber(int number){
		this.number = number;
	}
	public int getNumber(){
		return number;
	}
}
