package group;

import java.util.List;

import board.Cell;

public abstract class Group {
	private int result;
	//board max number
	private int n;
	public Group(int result, int n) {
		this.result= result;
		this.n = n;
	}
	public boolean equalsResult(int r){
		return result==r;
	}
	public int getBoardMax(){
		return n;
	}
	public abstract boolean satisfies();
	public abstract void addCell(Cell c);
	public void addCells(List<Cell> cells){
		for(Cell c:cells){
			addCell(c);
		}
	}
	public abstract List<Integer> getPossibles();
}
