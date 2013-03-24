package sia.group;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;



public class AddGroup extends Group{

	public AddGroup(int result, int n, int size) {
		super(result, n, size );
	}

	@Override
	public boolean satisfies() {
		int r=0;
		for(Integer i:getCellNumbers()){
			r+=i;
		}
		return equalsResult(r);
	}



	@Override
	protected void generatePossibles() {
		int[] values = new int[getBoardMax()];
		for(int i=0; i < values.length; i++)
			values[i] = i+1;
		possibleLists = new ArrayList<List<Integer>>();
		generatePossibles(values, 0, getResult(), new LinkedList<Integer>());
	}


	@SuppressWarnings("unchecked")
	private void generatePossibles(int[] values, int index, int sum, 
			LinkedList<Integer> stack) {
		if(sum == 0 && stack.size() == size())
			possibleLists.add((List<Integer>) stack.clone());

		if(values[index] > sum || stack.size() >= size())
			return;
		for(int i = index; i < values.length; i++) {
			stack.push(values[i]);
			generatePossibles(values, i, sum - values[i], stack);
			stack.pop();
		}
	}

	@Override
	protected String Operation() {
		return "+";
	}

}
