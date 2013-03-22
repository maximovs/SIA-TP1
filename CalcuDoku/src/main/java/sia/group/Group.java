package sia.group;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import sia.board.Cell;


public abstract class Group {
	private int result;
	private int quantCells;
	private int boardMax;
	private List<Cell> cells = new ArrayList<>();
	protected List<List<Integer>> possibleLists;
	
	public Group(int result, int boardMax, int size) {
		this.result= result;
		this.boardMax = boardMax;
		this.quantCells = size;
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
	
	public int size(){
		return quantCells;
	}
	
	public List<Cell> getCells(){
		return cells;
	}
	
	public List<List<Integer>> getPossibleLists(){
		return possibleLists;	
	}
	
	protected abstract void generatePossibles();
	@Override
	public String toString() {
		return "Group [op= "+this.Operation()+", result=" + result + "]";
	}
	protected abstract String Operation();
	
	
}
