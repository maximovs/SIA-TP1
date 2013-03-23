package sia.board;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import sia.group.Group;

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

	public int groupsLeft(){
		int count = 0;
		for(Group g: groups){
			if(!g.satisfies()){
				count++;
			}
		}
		return count;
	}
	
	public int rowsAndColsLeft(){
		int count = 0;
		ArrayList<Integer> numbers = null;
		boolean completed;
		for(int i = 0; i < size; i++){
			completed = true;
			for(int j = 0; j < size; j++){
				if(cells[i][j].getNumber() == 0){
					completed = false;
				}
			}
			if(completed == true){
				count++;
			}
		}
		for(int j = 0; j < size; j++){
			completed = true;
			for(int i = 0; i < size; i++){
				if(cells[i][j].getNumber() == 0){
					completed = false;
				}
			}
			if(completed == true){
				count++;
			}
		}
		return size + size - count;
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

	public int getSize(){
		return size;
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

	public void printBoard() {
		for(int i =0; i < size; i++){
			System.out.print("-----");
		}
		System.out.println("-");
		
		for(int i = 0; i < size; i++){			
			System.out.print("|");
			for(int j = 0; j < size; j++){
				System.out.print(" " + cells[i][j].getNumber() + " ");
				if(cells[i][j].getNumber() < 10) System.out.print(" ");
				if((j+1 < size) && (cells[i][j].getGroup() != cells[i][j+1].getGroup())){
					System.out.print("|");
				} else if(j+1 < size){
					System.out.print(" ");
				}
			
			}
			System.out.println("|");
			if(i+1<size){
				System.out.print("|");
				for(int j = 0; j < size; j++){
				
					if((i+1 < size) && (cells[i][j].getGroup() != cells[i+1][j].getGroup())){
						System.out.print("----");
					} else {
						System.out.print("    ");
					}
					if((j+1 < size) && (cells[i][j].getGroup() != cells[i][j+1].getGroup())){
						System.out.print("|");
					} else if(j+1 < size){
						System.out.print("-");
					}
				}
				System.out.println("|");
			}			
		}
		for(int i =0; i < size; i++){
			System.out.print("-----");
		}
		System.out.println("-");
	}
	
	public void printGroups(){
		for(Group g: this.groups){
			System.out.println(g);
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
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(cells);
		result = prime * result + ((groups == null) ? 0 : groups.hashCode());
		result = prime * result + size;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Board other = (Board) obj;
		if (!Arrays.deepEquals(cells, other.cells))
			return false;
		if (groups == null) {
			if (other.groups != null)
				return false;
		} else if (!groups.equals(other.groups))
			return false;
		if (size != other.size)
			return false;
		return true;
	}

}
