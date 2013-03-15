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
