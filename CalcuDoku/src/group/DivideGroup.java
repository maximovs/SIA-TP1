package group;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


import board.Cell;

public class DivideGroup extends Group{

	public DivideGroup(int result, int n) {
		super(result, n);
	}

	@Override
	public boolean satisfies() {
		int r=0;

		return equalsResult(r);
	}

	@Override
	public List<Integer> getPossibles() {
		Set<Integer> s = new HashSet<>();
		if(getCellNumbers().isEmpty()){
			for(List<Integer> l: possibleLists){
				s.addAll(l);
			}
		}else{
			List<List<Integer>> lists = new ArrayList<>();

			for(List<Integer> l: possibleLists){

			}
			for(Integer i: getCellNumbers()){

			}
		}
		return new ArrayList<>(s);
	}

	@Override
	public void generatePossibles() {
		possibleLists = new ArrayList<>();
		ArrayList<Integer> aux;
		int r = getResult();
		for(int i = getBoardMax(); i>0 ; i--){
			if(i%r==0){
				aux = new ArrayList<>();
				aux.add(i);
				aux.add(i/r);
				possibleLists.add(aux);
			}
		}
	}

	@Override
	public void setCell(Cell c) {
		c.setGroup(this);
	}


}
