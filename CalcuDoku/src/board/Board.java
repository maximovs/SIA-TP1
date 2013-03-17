package board;

import group.Group;

import java.util.ArrayList;

public class Board {
	
	int size;
	Cell[][] cells;
	ArrayList<Group> groups;
	
	public Board(int size){
		this.size = size;
		cells = new Cell[size][size];
		groups = new ArrayList<Group>();
		for(int i = 0; i < size; i++){
			for(int j = 0; j < size; j++){
				cells[i][j] = new Cell();
				}
			}
	}
	
	public boolean completed(){
		for(int i = 0; i < size; i++){
			for(int j = 0; i < size; j++){
				if(cells[i][j].getNumber() == 0){
					return false;
				}
			}
		}
		return false;
	}
	
	public boolean isFinished(){
		boolean cols = columnsFinished();
		boolean rows = rowsFinished();
		boolean groups = groupsFinished();
		return cols && rows && groups;
	}

	private boolean groupsFinished() {
		for(Group g: groups){
			if(!g.satisfies()){
				return false;
			}
		}
		return true;
	}

	private boolean rowsFinished() {
		ArrayList<Integer> numbers = null;
		for(int i = 0; i < size; i++){
			numbers = new ArrayList<>();
			for(int j = 0; j < size; j++){
				Integer cellNumber = cells[i][j].getNumber();
				if(numbers.contains(cellNumber)){
					return false;
				}
				numbers.add(cellNumber);
			}
		}
		return true;
	}

	private boolean columnsFinished() {
		ArrayList<Integer> numbers = null;
		for(int j = 0; j < size; j++){
			numbers = new ArrayList<>();
			for(int i = 0; i < size; i++){
				Integer cellNumber = cells[i][j].getNumber();
				if(numbers.contains(cellNumber)){
					return false;
				}
				numbers.add(cellNumber);
			}
		}
		return true;
	}

	public void addGroup(Group group) {
		groups.add(group);
	}

	public Cell getCell(int x, int y) {
		return cells[x][y];
	}
	
	public boolean isCorrect(){
		for(int i = 0; i < size; i++){
			for(int j = 0; j < size; j++){
				if(cells[i][j].getGroup() == null){
					return false;
				}
			}
		}
		for(Group g: groups){
			if(g.getPossibleLists().isEmpty()){
				return false;
			}
		}
		
		return true;
	}

	public void print() {
		for(int i = 0; i < size; i++){
			for(int j = 0; j < size; j++){
				System.out.print(cells[i][j].getGroup().getResult());
				}
				System.out.println("");
			}
	}
	


}
