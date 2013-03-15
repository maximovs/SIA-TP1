package group;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import board.Cell;

public abstract class Group {
	private int result;
	//board max number
	private int boardMax;
	private List<Cell> cells = new ArrayList<>();
	protected List<List<Integer>> possibleLists;
	
	public Group(int result, int boardMax) {
		this.result= result;
		this.boardMax = boardMax;
		generatePossibles();
	}
	public boolean equalsResult(int r){
		return result==r;
	}
	public int getResult(){
		return result;
	}
	public int getBoardMax(){
		return boardMax;
	}
	public abstract boolean satisfies();
	
	public void addCell(Cell c) {
		cells.add(c);
		setCell(c);
	}
	
	public void setCell(Cell c){
		c.setGroup(this);
	}
	
	public void addCells(List<Cell> cells){
		for(Cell c:cells){
			addCell(c);
		}
	}
	
	public List<Integer> getCellNumbers(){
		List<Integer> l = new ArrayList<>();
		for(Cell c: cells){
			if(c.getNumber()!=0){
				l.add(c.getNumber());
			}
		}
		return l;
	}
	
	public List<Integer> getPossibles() {
		Set<Integer> s = new HashSet<>();
		List<List<Integer>> lists ;
		if(getCellNumbers().isEmpty()){
			lists = possibleLists;
		}else{
			lists = new ArrayList<>();
			for(List<Integer> l: possibleLists){
				lists.add(l);
				for(Integer i: getCellNumbers()){
					if(!l.contains(i)){
						lists.remove(l);
						break;
					}
				}
			}
		}
		for(List<Integer> l: lists){
			s.addAll(l);
		}
		return new ArrayList<>(s);
	}
	
	public List<List<Integer>> getPossibleLists(){
		return possibleLists;	
	}
	
	protected abstract void generatePossibles();
}
