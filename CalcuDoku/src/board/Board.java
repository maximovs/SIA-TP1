package board;

import group.Group;

import java.util.ArrayList;
import java.util.List;

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
	
	public void setCellValue(int i, int j, int value){
		cells[i][j].setNumber(value);
	}
	
	public boolean completed(){
		for(int i = 0; i < size; i++){
			for(int j = 0; j < size; j++){
				if(cells[i][j].getNumber() == 0){
					return false;
				}
			}
		}
		return true;
	}
	
	public boolean isFinished(){
		if(!completed()){
			return false;
		}
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
	
	private List<Integer> getPossiblesInColumn(int j){
		List<Integer> possibles = new ArrayList<>();
		for(int i = 1; i<size+1;i++) possibles.add(i);
		for(int i = 0;i<size; i++){
			Integer aux;
			if((aux = cells[i][j].getNumber())!=0){
				possibles.remove(aux);
			}
		}
		return possibles;
	}
	
	private List<Integer> getPossiblesInRow(int i){
		List<Integer> possibles = new ArrayList<>();
		for(int j = 1; j<size+1;j++) possibles.add(j);
		for(int j = 0;j<size; j++){
			Integer aux;
			if((aux = cells[i][j].getNumber())!=0){
				possibles.remove(aux);
			}
		}
		return possibles;
	}
	
	public List<Integer> getPossibbles(int i, int j){
		List<Integer> possibles = new ArrayList<>();
		List<Integer> rowPossibles = getPossiblesInRow(i);
		List<Integer> colPossibles = getPossiblesInColumn(j);
		for(Integer possible: cells[i][j].getGroup().getPossibles()){
			if(rowPossibles.contains(possible) && colPossibles.contains(possible)){
				possibles.add(possible);
			}
		}
		return possibles;
	}

}
