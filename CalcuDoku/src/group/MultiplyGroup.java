package group;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import board.Cell;

public class MultiplyGroup extends Group{
	private List<Cell> cells;
	
	public MultiplyGroup(int result, int n, int size) {
		super(result, n, size);
	}
	
	@Override
	public boolean satisfies() {
		int r=1;
		for(Cell c:cells){
			r*=c.getNumber();
		}
		return equalsResult(r);
	}


	@Override
	protected void generatePossibles() {
		int[] values = new int[getBoardMax()];
		for(int i=0; i < values.length; i++)
			values[i] = i+1;
		possibleLists = new ArrayList<List<Integer>>();
		generatePossibles(values, 0, 1, new LinkedList<Integer>());
	}


	@SuppressWarnings("unchecked")
	private void generatePossibles(int[] values, int index, int prod, 
			LinkedList<Integer> stack) {
		if(prod == getResult() && stack.size() == size())
			possibleLists.add((List<Integer>) stack.clone());

		if(prod*values[index] > getResult() || stack.size() >= size())
			return;
		for(int i = index; i < values.length; i++) {
			stack.push(values[i]);
			generatePossibles(values, i, prod*values[i], stack);
			stack.pop();
		}
	}
	
}
